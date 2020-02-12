import xlwt, xlrd
import numpy as np
import funs
import rwdata as rw
import time,datetime
import tensorflow as tf
import matplotlib.pyplot as plt
from keras.layers.core import Dense, Dropout
from keras.layers.recurrent import LSTM
from keras.layers import Input
from keras.models import Model
from keras import backend as K
import xlsxwriter
import os,sys
from sklearn.externals import joblib

if __name__ == '__main__':
    K.clear_session()
    tf.reset_default_graph()
    savepath = './data1'
    folder = os.path.exists(savepath)
    if not folder:
        os.makedirs(savepath)
        print('Make a new folder!')
    else:
        print('This already has a folder!')
    readstart = time.clock()
    # 导入各机组历史健康状态数据。
    # 101001 其中1代表几号机组，01代表机组对应模型：01电气抽水，02电气发电，03电气暂态，
    # 11机械抽水，12机械发电，13机械暂态，21水力抽水，22水力发电，23水力暂态。 001代表模型的更新代数
    host = '218.197.228.243'; user = 'remote'; password = '123456'; bool_port = 'XJdatabase_boolean'; float_port = 'XJdatabase_core';  result_port = 'try'; pump_state = 826; power_state = 822;
    inputlist10 = [346,347,4597,4,5,24]; outputlist10 = [92,96,425,426,416,417,418]; healthyear = [2018]; healthmonth = [[11]]; sampleInterval = 60 * 60
    [Inputdata101,Inputdata102] = funs.get_steady_health_hisdata(host, user, password, bool_port, float_port, pump_state,power_state, inputlist10,healthyear, healthmonth, sampleInterval)
    [Outputdata101,Outputdata102] = funs.get_steady_health_hisdata(host, user, password, bool_port, float_port, pump_state,power_state, outputlist10,healthyear, healthmonth, sampleInterval)
    readend = time.clock()
    print('Read data time：%s Seconds'%(readend - readstart))
    # 输入输出归一化
    [x_trains101,x_tests101,x_maxmins101] = funs.healthydata_normalizaton(Inputdata101)
    [y_trains101,y_tests101,y_maxmins101] = funs.healthydata_normalizaton(Outputdata101)
    [x_trains102, x_tests102, x_maxmins102] = funs.healthydata_normalizaton(Inputdata102)
    [y_trains102, y_tests102, y_maxmins102] = funs.healthydata_normalizaton(Outputdata102)
    # 保存归一化标准
    update_time = 1; update_times = str(update_time)
    if update_time <10:
        update_times = '00' + update_times
    elif 10 <= update_time < 100:
        update_times = '0' + update_times
    joblib.dump(update_time, savepath + '/update_time.pkl')
    joblib.dump(x_maxmins101, savepath + '/healthy_x_maxmins101' + update_times + '.pkl')
    joblib.dump(y_maxmins101, savepath + '/healthy_y_maxmins101' + update_times + '.pkl')
    joblib.dump(x_maxmins102, savepath + '/healthy_x_maxmins102' + update_times + '.pkl')
    joblib.dump(y_maxmins102, savepath + '/healthy_y_maxmins102' + update_times + '.pkl')
    # 输出加权
    y_train101= funs.output_weight(y_trains101); y_test101 = funs.output_weight(y_tests101)
    y_train102 = funs.output_weight(y_trains102); y_test102 = funs.output_weight(y_tests102)
    # 转置
    x_trains101 = np.array(x_trains101).T; x_tests101 = np.array(x_tests101).T
    y_train101 = np.array(y_train101).T; y_test101 = np.array(y_test101).T
    x_trains102 = np.array(x_trains102).T; x_tests102 = np.array(x_tests102).T
    y_train102 = np.array(y_train102).T; y_test102 = np.array(y_test102).T
    # 建立健康状态模型
    healthymodel101 = funs.build_healthy_model(x_train= x_trains101, x_test=x_tests101, y_train=y_train101,y_test= y_test101,
                                               epochs =1000,batch_size=64,validation_split= 0.05,savepath=savepath,modelname= str(101),modeltime= update_times)
    healthymodel102 = funs.build_healthy_model(x_train=x_trains102, x_test=x_tests102, y_train=y_train102,y_test=y_test102,
                                               epochs=1000, batch_size=64, validation_split=0.05, savepath=savepath,modelname=str(102), modeltime=update_times)
    # 健康模型预测及误差
    [predict_train101, predict_test101, MAPE_train101, MAPE_test101, RMSE_train101,RMSE_test101] = funs.healthymodel_predict(
        healthymodel = healthymodel101, x_train = x_trains101, x_test = x_tests101, y_train = y_train101, y_test = y_test101)
    [predict_train102, predict_test102, MAPE_train102, MAPE_test102, RMSE_train102,RMSE_test102] = funs.healthymodel_predict(
        healthymodel = healthymodel102, x_train = x_trains102, x_test = x_tests102, y_train = y_train102, y_test = y_test102)
    # 健康模型预测结果绘图
    funs.plot_healthymodelresult(y_train = y_train101, y_test = y_test101, predict_train = predict_train101, predict_test = predict_test101)
    funs.plot_healthymodelresult(y_train = y_train102, y_test = y_test102, predict_train = predict_train102, predict_test = predict_test102)
    # 获取劣化数据及模型
    # 导入各机组历史劣化状态数据
    deteryear = [2019]; determonth = [[3,4,5]]
    [D_Inputdata101, D_Inputdata102] = funs.get_steady_deter_hisdata(host, user, password, bool_port, float_port, pump_state,power_state, inputlist10, deteryear, determonth)
    [D_Outputdatas101, D_Outputdatas102] = funs.get_steady_deter_hisdata(host, user, password, bool_port, float_port, pump_state, power_state, outputlist10, deteryear, determonth)
    # 输入输出归一化
    D_inputdata101 = funs.deterdata_normalization(D_Inputdata101, x_maxmins101)
    D_inputdata102 = funs.deterdata_normalization(D_Inputdata102, x_maxmins102)
    D_outputdatas101 = funs.deterdata_normalization(D_Outputdatas101, y_maxmins101)
    D_outputdatas102 = funs.deterdata_normalization(D_Outputdatas102, y_maxmins102)
    # 输出加权
    D_outputdata101 = funs.output_weight(D_outputdatas101)
    D_outputdata102 = funs.output_weight(D_outputdatas102)
    # 转置
    D_outputdata101 = np.array(D_outputdata101).T
    D_outputdata102 = np.array(D_outputdata102).T
    # 构造劣化序列
    deterlist101 = funs.build_deterlist(healthymodel101, D_inputdata101, D_outputdata101)
    deterlist102 = funs.build_deterlist(healthymodel102, D_inputdata102, D_outputdata102)
    # 构造历史劣化数据表并存储
    starttime101 = np.array(Inputdata101).shape[1]
    starttime102 = np.array(Inputdata102).shape[1]
    funs.build_deterdata_table(host= host,user= user,password= password,port= result_port,tablename= '101'+ update_times)
    funs.build_deterdata_table(host= host,user= user,password= password,port= result_port,tablename= '102'+ update_times)
    funs.hisdeterdata_starage(host= host,user= user,password= password,port= result_port,hisdeter_data= deterlist101,tablename='101'+ update_times,starttime= starttime101)
    funs.hisdeterdata_starage(host= host,user= user,password= password,port= result_port,hisdeter_data= deterlist102,tablename='102'+ update_times,starttime= starttime102)
    # 预测劣化序列
    deter_epochs = 300; timesteps = 12; pre_steps = 4; vmd_n = 3; vmd_alpha = 1000;thre_tablename = "tre_deter_threshold"
    [pre_test_result101, deter_testdata101, deter_startime101] = funs.deterlist_predict_model(epochs=deter_epochs,timesteps= timesteps,pre_steps=pre_steps,
                                                                                              vmd_k= vmd_n, vmd_alpha= vmd_alpha, deter_data0= deterlist101)
    [pre_test_result102, deter_testdata102, deter_startime102] = funs.deterlist_predict_model(epochs=deter_epochs,timesteps=timesteps,pre_steps=pre_steps,
                                                                                              vmd_k= vmd_n, vmd_alpha= vmd_alpha, deter_data0= deterlist102)
    # 构建预测结果表并存储
    funs.build_deterpre_table(host= host,user= user,password= password,port= result_port,tablename= '101'+ update_times)
    funs.build_deterpre_table(host= host,user= user,password= password,port= result_port,tablename= '102'+ update_times)
    funs.deterpre_starage(host= host,user= user,password= password,port= result_port,tablename= '101'+ update_times,
                          thre_table_name= thre_tablename,predeter_result=pre_test_result101, pretime= deter_startime101 + starttime101)
    funs.deterpre_starage(host=host, user=user, password=password, port=result_port, tablename='102' + update_times,
                          thre_table_name=thre_tablename, predeter_result=pre_test_result102, pretime= deter_startime102 + starttime102)