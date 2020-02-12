import xlwt, xlrd ,pymysql
import numpy as np
import time,datetime
import os,sys
import vmd
import tensorflow as tf
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from keras.layers.core import Dense, Dropout
from keras.layers.recurrent import LSTM
from keras.layers import Input
from keras.models import Model
from keras import backend as K


def read_bool_data(host, user, password, port,table_id,table_year,table_month):
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    if table_month < 10:
        table_month = str(0) + str(table_month)
    else:
        table_month = str(table_month)
    table_name = 'bool'+ '_' + str(table_id) + '_'+ str(table_year) + '_' + table_month
    sql = "SELECT * FROM " + str(table_name)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        print('fetch data!')
    except:
        print('Uable to fetch data!')
    conn.close()
    return results

def read_float_data(host, user, password, port,table_id,table_year,table_month):
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    if table_month < 10:
        table_month = str(0) + str(table_month)
    else:
        table_month = str(table_month)
    table_name = 'float'+ '_' + str(table_id) + '_'+ str(table_year) + '_' + table_month
    sql = "SELECT * FROM " + str(table_name)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        print('fetch data!')
    except:
        print('Uable to fetch data!')
    conn.close()
    return results

def steady_health_datalist(float_result,bool_result,sample_interval):
    stateflag = 0
    timelist1 = [];timelist2 =[]
    steady_list =[]
    for i in range(len(bool_result)):
        if i == len(bool_result)-1 and stateflag == 0 and bool_result[i][1] == 1:
            continue
        elif i == len(bool_result)-1 and stateflag == 1 and bool_result[i][1] ==1:
            stateflag = 1
            timelist1.append(bool_result[i][0])
        elif bool_result[i][1] ==1 and stateflag ==0:
            stateflag = 1;
            timelist1.append(bool_result[i][0])
        elif bool_result[i][1] ==0 and stateflag == 1:
            stateflag = 0
            timelist1.append(bool_result[i][0])
#     取开机后一小时到关机前10分钟的稳态数据的平均值
    tdim = int(len(timelist1) / 2)
    for i in range(tdim):
        temptime = timelist1[2 * i]
        temptime = temptime + 60 *60
        if temptime > timelist1[2 * i + 1] - 600:
            continue
        else:
            timelist2.append(temptime)
            temptime = timelist1[2 * i + 1] - 600
            timelist2.append(temptime)

    if len(timelist2) ==0:
        return steady_list
    aver_time, aver_index = average_time(timelist2,float_result,sampleInterval=sample_interval)
    if aver_time == []:
        return steady_list
    for i in range(len(aver_time)):
        for j in range(len(aver_time[i])-1):
            start_index = aver_index[i][j]
            end_index = aver_index[i][j+1]
            sum_value = 0; aver_value = 0
            for k in range(start_index,end_index):
                sum_value = sum_value + float_result[k][1]
            aver_value = sum_value/(end_index - start_index)
            start_time = aver_time[i][j]
            local_time = time.localtime(start_time)
            local_timesd = time.strftime("%Y-%m-%d %H:%M:%S", local_time)
            steady_list.append([local_timesd,aver_value])
    return steady_list

def average_time(timelist,float_result,sampleInterval):
    dim = len(timelist)/2
    dim = int(dim)
    aver_time =[]
    aver_index = []
    if dim == 0:
        return aver_time
    for i in range(dim):
        aver_time.append([])
        aver_index.append([])
        if (timelist[2*i + 1]-timelist[2*i]) < sampleInterval:
            aver_time[i].append(timelist[2*i])
            aver_time[i].append(timelist[2*i + 1])
            for m in range(2):
                tempt = aver_time[i][m]
                for k in range(len(float_result)):
                    if float_result[k][0] == tempt:
                        aver_index[i].append(k)
        else:
            tempt = (timelist[2*i+1]-timelist[2*i])/sampleInterval
            tempt = int(tempt+1)
            for j in range(tempt):
                temp_time = timelist[2*i] + sampleInterval*j
                aver_time[i].append(temp_time)
                for k in range(len(float_result)):
                    if float_result[k][0] == temp_time:
                        aver_index[i].append(k)
    return aver_time,aver_index

def get_listrow(row,list1):
    b = []
    b = [x[row] for x in  list1]
    return  b

def steady_deter_datalist(float_result,bool_result):
    stateflag = 0
    timelist1 = []; timelist2 = []; steady_list = []
    for i in range(len(bool_result)):
        if i == len(bool_result) - 1 and stateflag == 0 and bool_result[i][1] == 1:
            continue
        elif i == len(bool_result) - 1 and stateflag == 1 and bool_result[i][1] == 1:
            stateflag = 1
            timelist1.append(bool_result[i][0])
        elif bool_result[i][1] == 1 and stateflag == 0:
            stateflag = 1
            timelist1.append(bool_result[i][0])
        elif bool_result[i][1] == 0 and stateflag == 1:
            stateflag = 0
            timelist1.append(bool_result[i][0])
    #     取开机后一小时到关机前10分钟的稳态数据的平均值
    tdim = int(len(timelist1) / 2)
    for i in range(tdim):
        temptime = timelist1[2 * i]
        temptime = temptime + 60 * 60
        if temptime > timelist1[2 * i + 1] - 600:
            continue
        else:
            timelist2.append(temptime)
            temptime = timelist1[2 * i + 1] - 600
            timelist2.append(temptime)
    if len(timelist2) == 0:
        return steady_list
    # 得到时间对应的索引
    time_index = []
    for i in range(len(timelist2)):
        for j in range(len(float_result)):
            if (timelist2[i] == float_result[j][0]):
                time_index.append(j)
    dim = int(len(time_index) / 2)
    for i in range(dim):
        start_index = time_index[2 * i]
        end_index = time_index[2 * i + 1]
        sum_value = 0;
        aver_value = 0
        for j in range(start_index, end_index):
            sum_value = sum_value + float_result[j][1]
        aver_value = sum_value / (end_index - start_index)
        start_time = timelist2[2 * i]
        local_time = time.localtime(start_time)
        local_timesd = time.strftime("%Y-%m-%d %H:%M:%S", local_time)
        steady_list.append([local_timesd, aver_value])
    return steady_list

#   获取历史抽水、发电下稳态健康模型数据
def get_steady_health_hisdata(host, user, password, bool_port, float_port, pump_state,power_state, datalist,
                     year, month, sampleInterval):
    data_pump = []; data_power = []; result_pump = []; result_power = []
    for index in range(len(datalist)):
        data_pump.append([])
        data_power.append([])
    for i in range(len(datalist)):
        tempdata_pump = [];tempdata_power = []; temp_pump = []; temp_power = []
        for j in range(len(year)):
            for k in range(len(month)):
                y = year[k]; m = month[j][k]
                pump_bool_result = read_bool_data(host, user, password, bool_port,pump_state,y, m)
                power_bool_result = read_bool_data(host, user, password, bool_port,power_state,y, m)
                float_result = read_float_data(host, user, password, float_port, datalist[i] ,y,m)
                tempdata_pump = steady_health_datalist(float_result,pump_bool_result,sampleInterval)
                tempdata_power = steady_health_datalist(float_result,power_bool_result,sampleInterval)
                data_pump[i] = data_pump[i] + tempdata_pump
                data_power[i] = data_power[i] + tempdata_power
        temp_pump = get_listrow(1,data_pump[i])
        temp_power = get_listrow(1,data_power[i])
        result_pump.append(temp_pump)
        result_power.append(temp_power)
    return result_pump, result_power

# 获取历史抽水、发电下稳态劣化模型数据
def get_steady_deter_hisdata(host, user, password, bool_port, float_port, pump_state,power_state, datalist,
                     year, month):
    data_pump = []; data_power = []; result_pump = []; result_power = []
    for index in range(len(datalist)):
        data_pump.append([])
        data_power.append([])
    for i in range(len(datalist)):
        tempdata_pump = [];tempdata_power = []; temp_pump = []; temp_power = []
        for j in range(len(year)):
            for k in range(len(month)):
                y = year[k]; m = month[j][k]
                pump_bool_result = read_bool_data(host, user, password, bool_port,pump_state,y, m)
                power_bool_result = read_bool_data(host, user, password, bool_port,power_state,y, m)
                float_result = read_float_data(host, user, password, float_port, datalist[i] ,y,m)
                tempdata_pump = steady_deter_datalist(float_result,pump_bool_result)
                tempdata_power = steady_deter_datalist(float_result,power_bool_result)
                data_pump[i] = data_pump[i] + tempdata_pump
                data_power[i] = data_power[i] + tempdata_power
        temp_pump = get_listrow(1,data_pump[i])
        temp_power = get_listrow(1,data_power[i])
        result_pump.append(temp_pump)
        result_power.append(temp_power)
    return result_pump, result_power

def maponezero(data, direction="normal", maxmin=None):
    if direction == "normal":
        maxval = np.max(data)
        minval = np.min(data)
        data = (data - minval) / (maxval - minval)
        return [data, [maxval, minval]]
    if direction == "apply":
        maxval = maxmin[0]
        minval = maxmin[1]
        data = (data - minval) / (maxval - minval)
        return data
    if direction == "reverse":
        maxval = maxmin[0]
        minval = maxmin[1]
        data = data * (maxval - minval) + minval
        return data

# 数据归一化
def healthydata_normalizaton(ini_data):
    #  80%用来训练、数据归一化
    ini_data = np.array(ini_data)
    per = int(ini_data.shape[1] * 0.8)
    x_train0 = ini_data[:,0:per]
    x_test0 = ini_data[:,per:]
    x_train1 = []; x_test1 = []; x_maxmin1 = []
    for i in range(x_train0.shape[0]):
         x_traini = []; x_maxmini = []
         x_traini = x_train0[i, :]
         [x_traini, x_maxmini] = maponezero(x_traini)
         x_train1.append(x_traini)
         x_maxmin1.append(x_maxmini)
    for i in range(x_test0.shape[0]):
        x_testi = []; x_testi = x_test0[i, :]
        x_testi = maponezero(x_testi, 'apply', x_maxmin1[i])
        x_test1.append(x_testi)
    return x_train1,x_test1,x_maxmin1

# 输出加权
def output_weight(outputdata):
    outputdata = np.array(outputdata)
    output_data = outputdata.sum(0)/outputdata.shape[0]
    return output_data

def build_healthy_model(x_train, x_test, y_train, y_test, epochs, batch_size,
                        validation_split, savepath, modelname, modeltime):
    input_shape = np.array(x_train).shape[1]
    output_shape = np.array(y_train).shape[1]
    #  搭建模型
    data_input = Input(shape=(input_shape,), name='train_input')
    layer1 = Dense(64, activation='relu')(data_input)
    layer1 = Dense(32, activation='relu')(layer1)
    layer1 = Dense(16, activation='relu')(layer1)
    layer1 = Dense(8, activation='relu')(layer1)
    layer1 = Dense(4, activation='relu')(layer1)
    layer1 = Dense(2, activation='relu')(layer1)
    data_output = Dense(output_shape, activation='linear')(layer1)
    model1 = Model(inputs=data_input, outputs=data_output)
    model1.compile(loss='mse', optimizer='rmsprop')
    #  训练模型
    model1.fit(x =x_train,y = y_train,epochs = epochs,
               batch_size= batch_size,validation_split= validation_split)
    # 保存模型
    model1.save(savepath + '/healthymodel' + modelname + modeltime + '.h5' )
    return model1

# 计算健康状态模型预测结果，及误差
def healthymodel_predict(healthymodel,x_train,x_test,y_train,y_test):
    # 对训练集和测试集进行预测
    predict_train = healthymodel.predict(x_train)
    predict_train = np.reshape(predict_train, (predict_train.shape[0],))
    predict_test = healthymodel.predict(x_test)
    predict_test = np.reshape(predict_test, (predict_test.shape[0],))
    # 计算相对平均误差
    MAPE_train = np.sum(np.abs(y_train - predict_train) / y_train) / len(y_train)
    MAPE_test = np.sum(np.abs(y_test - predict_test) / y_test) / len(y_test)
    print('MAPE_train1', MAPE_train, '\n', 'MAPE_test1', MAPE_test)
    # 计算均方根误差
    RMSE_train = np.sqrt(np.sum((y_train - predict_train) ** 2) / len(y_train))
    RMSE_test = np.sqrt(np.sum((y_test - predict_test) ** 2) / len(y_test))
    print('RMSE_train1', RMSE_train, '\n', 'RMSE_test1', RMSE_test)
    return predict_train, predict_test,MAPE_train,MAPE_test,RMSE_train,RMSE_test

# 健康状态模型预测结果绘图
def plot_healthymodelresult(y_train, y_test, predict_train, predict_test):
    plt.figure('trains', facecolor='white')
    plt.rcParams['font.sans-serif'] = ['SimHei']
    plt.rcParams['axes.unicode_minus'] = False
    plt.plot(y_train, 'r', label='真实值')
    plt.plot(predict_train, 'g', label='预测值')
    plt.xlabel("时间", fontsize=20)
    plt.ylabel("劣化度", fontsize=20)
    plt.xticks(fontsize=20)
    plt.yticks(fontsize=20)
    font1 = {'size': 20, }
    plt.legend(prop=font1)

    plt.figure('tests ', facecolor='white')
    plt.rcParams['font.sans-serif'] = ['SimHei']
    plt.rcParams['axes.unicode_minus'] = False
    plt.plot(y_test, 'r', label='真实值')
    plt.plot(predict_test, 'g', label='预测值')
    plt.xlabel("时间", fontsize=20)
    plt.ylabel("劣化度", fontsize=20)
    plt.xticks(fontsize=20)
    plt.yticks(fontsize=20)
    font1 = {'size': 20, }
    plt.legend(prop=font1)
    plt.show()

def deterdata_normalization(ini_data,maxmin):
    result = []
    for i in range(ini_data.shape[0]):
        ini_datai = [];
        ini_datai = ini_data[i, :]
        ini_datai = maponezero(ini_datai, 'apply', maxmin[i])
        result.append(ini_datai)
    return result

# 构造劣化序列
def build_deterlist(healthymodel, deterinput, deteroutput):
    # 预测
    predict_data = healthymodel.predict(deterinput)
    predict_data = np.reshape(predict_data, (predict_data.shape[0],))
    deter_data = abs((deteroutput - predict_data) / predict_data)
    return deter_data

# 构造历史劣化序列表
def build_deterdata_table(host, user, password, port, tablename):
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    sql = "CREATE TABLE tre_deterdata_" + tablename + "(time INT NOT NULL,deter FLOAT NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;"
    # sql = "CREATE TABLE tre_deterdata_101001(time INT NOT NULL,deter FLOAT NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;"
    cursor.execute(sql)
    conn.close()

# 历史劣化序列存储
def hisdeterdata_starage(host, user, password, port,hisdeter_data, tablename, starttime):
    if not isinstance(hisdeter_data,list):
        hisdeter_data = hisdeter_data.tolist()
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    sql = "INSERT INTO tre_deterdata_" + tablename + "(time,deter) VALUES(%s, %s)"
    for i in range(len(hisdeter_data)):
        temptime = starttime + i + 1
        tempdeter = hisdeter_data[i]
        cursor.execute(sql, (temptime, tempdeter))
        conn.commit()
    conn.close()

def vmd_decomposition( data, pre_seq_len= 0 , pre_step =0 , vmd_k=0, vmd_alpha=1000):
    print('> vmd processing...')
    # VMD分解 多步单点
    alpha = vmd_alpha  # moderate bandwidth constraint
    tau = 0  # noise-tolerance (no strict fidelity enforcement)
    K = vmd_k  # 3modes
    DC = 0  # no DC part imposed
    init = 1  # initialize omegas uniformly
    tol = 1e-7
    REI = np.inf
    tauo = 0
    for tau in [x / 10 for x in range(11)]:
        [u, u_hat, omega] = vmd.vmd(data, alpha, tau, K, DC, init, tol)
        temp = np.sqrt(np.sum((np.sum(u, 0) - data) ** 2, 0) / len(data))  # sum(a,0)才等于matlab中的sum
        if temp < REI:
            REI = temp
            tauo = tau
    tau = tauo
    [u, u_hat, omega] = vmd.vmd(data, alpha, tau, K, DC, init, tol)
    print('> vmd processed...')
    # 处理数据
    x_trains = []
    y_trains = []
    x_tests = []
    y_tests = []
    x_pre_tests = []
    for i in range(vmd_k + 1):
        result = []
        if i < vmd_k:
            seq = u[i]
        else:
            seq = data
        for index in range(len(seq) - pre_seq_len- pre_step +1):
            result.append(seq[index:index + pre_seq_len + pre_step])  # 每一次添加一个列表作为元素，表示矩阵的一行0;12+4
        result = np.array(result)  # 用numpy对其进行矩阵化
        # 划分训练集和测试集，并添加预测集
        row = round(0.8 * result.shape[0])
        print('vmd_row   ',row)
        x_train = result[:int(row), :pre_seq_len]
        y_train = result[:int(row), -1]
        x_test = result[int(row):, :pre_seq_len]
        y_test = result[int(row):, -1]
        # 新增预测集
        temp_test = []
        for i in range(pre_step):
            temp_test.append(result[-1, i + 1: i + pre_seq_len + 1])
        temp_test = np.array(temp_test)
        x_pre_test = np.concatenate((x_test, temp_test))
        x_train = np.reshape(x_train, (x_train.shape[0], x_train.shape[1], 1))
        x_test = np.reshape(x_test, (x_test.shape[0], x_test.shape[1], 1))
        x_pre_test = np.reshape(x_pre_test, (x_pre_test.shape[0],x_pre_test.shape[1],1))
        x_trains.append(x_train)
        y_trains.append(y_train)
        x_tests.append(x_test)
        x_pre_tests.append(x_pre_test)
        y_tests.append(y_test)
    return [x_trains, y_trains, x_tests, y_tests,x_pre_tests]

def deterlist_predict_model(epochs,timesteps, pre_steps, vmd_k,vmd_alpha,deter_data0):
    deter_data0 = np.array(deter_data0)
    flag = 0
    # vmd分解的数据长度为偶数
    if (len(deter_data0) % 2 == 0):
        deter_data = deter_data0
    elif (len(deter_data0) % 2 == 1):
        deter_data = deter_data0[1:]
        flag = 1
    # vmd 分解
    [X_trains, Y_trains, X_tests, Y_tests, X_pre_tests] = vmd_decomposition(deter_data, pre_seq_len=timesteps,
                        pre_step=pre_steps, vmd_k=vmd_k, vmd_alpha=vmd_alpha)
    x_trains = []; y_trains = []
    x_tests = []; y_tests = []; x_pre_tests = []
    x_maxmins = []; y_maxmins = []
    # 归一化
    for k in range(vmd_k):
        [x_train, x_maxmin] = maponezero(X_trains[k])
        [y_train, y_maxmin] = maponezero(Y_trains[k])
        x_test = maponezero(X_tests[k], "apply", x_maxmin)
        x_pre_test = maponezero(X_pre_tests[k],"apply",x_maxmin)
        y_test = maponezero(Y_tests[k], "apply", y_maxmin)
        x_trains.append(x_train)
        y_trains.append(y_train)
        x_tests.append(x_test)
        x_pre_tests.append(x_pre_test)
        y_tests.append(y_test)
        x_maxmins.append(x_maxmin)
        y_maxmins.append(y_maxmin)
    y_trains_sum = 0;
    y_tests_sum = 0;
    predict_trains_sum = 0;
    predict_tests_sum = 0
    predict_pre_tests_sum = 0
    for k in range(vmd_k):
        error_times = 0
        while True:
            # clear model data and state to avoid memory leak
            K.clear_session()
            tf.reset_default_graph()
            print('>compiling...', k)
            start_time = time.time()
            #   build model
            ipt = Input(shape=(timesteps, 1), name='train_input')
            lstm = LSTM(output_dim=64, return_sequences=False)(ipt)
            ds = Dense(32, activation='relu')(lstm)
            ds = Dense(16, activation='relu')(ds)
            ds = Dense(8, activation='relu')(ds)
            ds = Dense(4, activation='relu')(ds)
            opt = Dense(1, activation='linear')(ds)
            model2 = Model(inputs=ipt, outputs=opt)
            model2.compile(loss='mse', optimizer='rmsprop')
            # train model
            model2.fit(x=x_trains[k], y=y_trains[k], epochs=epochs,
                       batch_size=32, validation_split=0.05)
            # predict model
            predict_trains = model2.predict(x_trains[k])
            predict_tests = model2.predict(x_tests[k])
            predict_pre_tests = model2.predict(x_pre_tests[k])
            predict_trains = np.reshape(predict_trains, (predict_trains.shape[0],))
            predict_tests = np.reshape(predict_tests, (predict_tests.shape[0],))
            predict_pre_tests = np.reshape(predict_pre_tests,(predict_pre_tests.shape[0],))
            #   反归一化
            predict_trains = maponezero(predict_trains, 'reverse', y_maxmins[k])
            predict_tests = maponezero(predict_tests, 'reverse', y_maxmins[k])
            predict_pre_tests = maponezero(predict_pre_tests, 'reverse',y_maxmins[k])
            MAE_trains = np.sum(np.abs(Y_trains[k] - predict_trains)) / len(Y_trains[k])
            MAE_tests = np.sum(np.abs(Y_tests[k] - predict_tests)) / len(Y_tests[k])

            MAPE_trains = np.sum(np.abs(Y_trains[k] - predict_trains) / Y_trains[k]) / len(Y_trains[k])
            MAPE_tests = np.sum(np.abs(Y_tests[k] - predict_tests) / Y_tests[k]) / len(Y_tests[k])
            #  print(Y_trains,'\n',Y_trains[k])
            if MAE_trains < 0.005 or error_times > 3:
                predict_trains_sum += predict_trains
                predict_tests_sum += predict_tests
                predict_pre_tests_sum += predict_pre_tests
                y_trains_sum += Y_trains[k]
                y_tests_sum += Y_tests[k]
                print('error_times:  ', error_times)
                print('mae_trains ' + str(k) + ': ', MAE_trains)
                print('mae_tests ' + str(k) + ': ', MAE_tests)
                print('are_trains ' + str(k) + ':', MAPE_trains)
                print('are_tests ' + str(k) + ':', MAPE_tests)
                plt.figure('train predict of imf' + str(k), facecolor='white')
                plt.plot(Y_trains[k], 'r', label='true data')
                plt.plot(predict_trains, 'g', label='predict data')
                plt.legend()
                plt.figure('test predict of imf' + str(k), facecolor='white')
                plt.plot(Y_tests[k], 'r', label='true data')
                plt.plot(predict_tests, 'g', label='predict data')
                plt.legend()
                break
            error_times += 1
            print('error_times:  ', error_times)
            print('mae_trains ' + str(k) + ': ', MAE_trains)
            print('mae_tests ' + str(k) + ': ', MAE_tests)
            print('are_trains ' + str(k) + ':', MAPE_trains)
            print('are_tests ' + str(k) + ':', MAPE_tests)
    print('Traing duration(s) :', time.time() - start_time)
    y_test_data = deter_data[-len(predict_tests_sum):]
    deter_starttime = len(deter_data0) - len(predict_tests_sum)
    # if flag ==1:
    #     predict_result = np.concatenate((deter_data0[:timesteps + pre_steps], predict_trains_sum, predict_tests_sum))
    # else:
    #     predict_result = np.concatenate((deter_data0[:timesteps + pre_steps -1], predict_trains_sum, predict_tests_sum))
    return predict_pre_tests_sum, y_test_data, deter_starttime

# 构造劣化预测表
def build_deterpre_table(host, user, password, port, tablename):
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    sql = "CREATE TABLE tre_deterpre_" + tablename + "(pretime INT NOT NULL,predeter FLOAT NOT NULL,warnsig TINYINT NOT NULL) " \
                                        "ENGINE=InnoDB DEFAULT CHARSET=utf8;"
    cursor.execute(sql)
    conn.close()

# 保存劣化预测结果
def deterpre_starage(host, user, password, port, tablename,thre_table_name,predeter_result,pretime):
    if not isinstance(predeter_result,list):
        predeter_result = predeter_result.tolist()
    pre_threshold = read_threshold(host, user, password, port, thre_table_name,tablename)
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    sql = "INSERT INTO tre_deterpre_" + tablename + "(pretime,predeter,warnsig) VALUES(%s,%s,%s)"
    for i in range(len(predeter_result)):
        warnsig = 0
        tem_time = pretime + i +1
        if predeter_result[i] > pre_threshold:
            warnsig = 1
        cursor.execute(sql,(tem_time,predeter_result[i],warnsig))
        conn.commit()
    conn.close()

# 读取不同劣化模型阈值
def read_threshold(host, user, password, port, tablename,model_number):
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    sql = "SELECT * FORM " + tablename + " WHERE model_number == " + model_number
    cursor.execute(sql)
    deter_threshold = cursor.fetchall()
    conn.close()
    return deter_threshold

# 返回当前和上一天的日期
def get_nowlastday():
    now = datetime.datetime.now()
    now_year = now.year
    now_month = now.month
    now_day = now.day
    last = now - datetime.timedelta(days=1)
    last_year = last.year
    last_month = last.month
    last_day = last.day
    return now_year,now_month,now_day,last_year,last_month,last_day

# 返回前一天的状态数据
def read_real_bool_data(host, user, password, port, table_id):
    [now_year, now_month, now_day, last_year, last_month, last_day] = get_nowlastday()
    tss1 = str(last_year) + '-' + str(last_month) + '-' + str(last_day) + ' 00:00:00'
    tss2 = str(now_year) + '-' + str(now_month) + '-' + str(now_day) + ' 00:00:00'
    timearray1 = time.strptime(tss1, '%Y-%m-%d %H:%M:%S')
    timearray2 = time.strptime(tss2, '%Y-%m-%d %H:%M:%S')
    timestamp1 = int(time.mktime(timearray1))
    timestamp2 = int(time.mktime(timearray2))
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    if last_month < 10:
        last_month = str(0) + str(last_month)
    else:
        last_month = str(last_month)
    table_name = 'bool' + '_' + str(table_id) + '_' + str(last_year) + '_' + last_month
    sql = "SELECT * FROM " + str(table_name) + " WHERE t >=" + timestamp1 +" and t <= " + timestamp2
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        print('fetch data!')
    except:
        print('Uable to fetch data!')
    conn.close()
    return results

# 返回前一天的浮点数据
def read_real_float_data(host, user, password, port,table_id):
    [now_year, now_month, now_day, last_year, last_month, last_day] = get_nowlastday()
    tss1 = str(last_year) + '-' + str(last_month) + '-' + str(last_day) + ' 00:00:00'
    tss2 = str(now_year) + '-' + str(now_month) + '-' + str(now_day) + ' 00:00:00'
    timearray1 = time.strptime(tss1, '%Y-%m-%d %H:%M:%S')
    timearray2 = time.strptime(tss2, '%Y-%m-%d %H:%M:%S')
    timestamp1 = int(time.mktime(timearray1))
    timestamp2 = int(time.mktime(timearray2))
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    if last_month < 10:
        last_month = str(0) + str(last_month)
    else:
        last_month = str(last_month)
    table_name = 'float' + '_' + str(table_id) + '_' + str(last_year) + '_' + last_month
    sql = "SELECT * FROM " + str(table_name) + " WHERE t >=" + timestamp1 +" and t <= " + timestamp2
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        print('fetch data!')
    except:
        print('Uable to fetch data!')
    conn.close()
    return results

# 读取抽水发电态下过去一天的数据
def get_steady_deter_realdata(host, user, password, bool_port, float_port, pump_state,power_state, datalist):
    data_pump = []; data_power = []; result_pump = []; result_power = []
    pump_bool_result = read_real_bool_data(host, user, password, bool_port, pump_state)
    power_bool_result = read_real_bool_data(host, user, password, bool_port, power_state)
    for index in range(len(datalist)):
        data_pump.append([])
        data_power.append([])
    for i in range(len(datalist)):
        tempdata_pump = [];tempdata_power = []; temp_pump = []; temp_power = []
        float_result = read_real_float_data(host, user, password, float_port, datalist[i])
        tempdata_pump = steady_deter_datalist(float_result,pump_bool_result)
        tempdata_power = steady_deter_datalist(float_result,power_bool_result)
        data_pump[i] = data_pump[i] + tempdata_pump
        data_power[i] = data_power[i] + tempdata_power
        temp_pump = get_listrow(1,data_pump[i])
        temp_power = get_listrow(1,data_power[i])
        result_pump.append(temp_pump)
        result_power.append(temp_power)
    return result_pump, result_power

# 读取劣化表格记录
def read_deterlist(host, user, password, port,tablename,length):
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    sql = "SELECT * FROM tre_deterdata_" + str(tablename) + " order by time desc"
    cursor.execute(sql)
    results1 = cursor.fetchmany(length)
    conn.close()
    results2 = results1[::-1]
    return results2

# 读取劣化预测结果表格记录
def read_pre_deterlist(host, user, password, port,tablename,length):
    conn = pymysql.connect(host, user, password, port)
    cursor = conn.cursor()
    sql = "SELECT * FROM tre_deterpre_" + str(tablename) + " order by time desc"
    cursor.execute(sql)
    results1 = cursor.fetchmany(length)
    conn.close()
    results2 = results1[::-1]
    return results2

# a = [1,2,3]
# starti = 32
# a = read_lastrow(host="localhost",user="root",password="root", port="try", tablename="101001")

# host = '218.197.228.243'; user = 'remote'; password = '123456'; bool_port = 'XJdatabase_boolean'; float_port = 'XJdatabase_core';
# pump_state = 826; power_state = 822; datalist = [346];year = [2018]; month = [[11]]; sampleInterval = 60* 60
# test1 = get_steady_health_hisdata(host, user, password, bool_port, float_port, pump_state,power_state, datalist,
#                      year, month, sampleInterval)

# savepath = '../健康劣化趋势模型/data2'
# ao1 = joblib.load(savepath +'/active_power.pkl')
# print(ao1)