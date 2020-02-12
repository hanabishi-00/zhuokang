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
from keras.models import load_model
from keras import backend as K
import xlsxwriter
import os,sys
from sklearn.externals import joblib

def daily_running():
    K.clear_session()
    tf.reset_default_graph()
    savepath = './data1'
    folder = os.path.exists(savepath)
    if not folder:
        os.makedirs(savepath)
        print('Make a new folder!')
    else:
        print('This already has a folder!')
    # 导入各机组历史健康状态数据。
    # 101001 其中1代表几号机组，01代表机组对应模型：01电气抽水，02电气发电，03电气暂态，
    # 11机械抽水，12机械发电，13机械暂态，21水力抽水，22水力发电，23水力暂态。 001代表模型的更新代数
    host = '218.197.228.243'; user = 'remote'; password = '123456'; bool_port = 'XJdatabase_boolean'; float_port = 'XJdatabase_core';  result_port = 'try'; pump_state = 826; power_state = 822;
    inputlist10 = [346,347,4597,4,5,24]; outputlist10 = [92,96,425,426,416,417,418]; healthyear = [2018]; healthmonth = [[11]]; sampleInterval = 60 * 60
    # 获取当天新增数据，导入健康状态模型，计算劣化度，并做预测
    [real_Inputdata101, real_Inputdata102] = funs.get_steady_deter_realdata(host, user, password, bool_port, float_port,pump_state, power_state, inputlist10)
    [real_Outputdatas101, real_Outputdatas102] = funs.get_steady_deter_realdata(host, user, password, bool_port, float_port, pump_state, power_state, outputlist10)
    # 归一化 ,导入归一化标准及健康模型
    update_time = joblib.load(savepath + '/update_time.pkl')
    update_times = str(update_time)
    if update_time < 10:
        update_times = '00' + update_times
    elif 10 <= update_time < 100:
        update_times = '0' + update_times
    x_maxmins101 = joblib.load(savepath + '/healthy_x_maxmins101' + update_times + '.pkl')
    y_maxmins101 = joblib.load(savepath + '/healthy_y_maxmins101' + update_times + '.pkl')
    x_maxmins102 = joblib.load(savepath + '/healthy_x_maxmins102' + update_times + '.pkl')
    y_maxmins102 = joblib.load(savepath + '/healthy_y_maxmins102' + update_times + '.pkl')
    healthymodel101 = load_model(savepath + '/healthymodel' + str(101) + update_times + '.h5')
    healthymodel102 = load_model(savepath + '/healthymodel' + str(102) + update_times + '.h5')
    real_inputdata101 = funs.deterdata_normalization(real_Inputdata101, x_maxmins101)
    real_inputdata102 = funs.deterdata_normalization(real_Inputdata102, x_maxmins102)
    real_outputdatas101 = funs.deterdata_normalization(real_Outputdatas101, y_maxmins101)
    real_outputdatas102 = funs.deterdata_normalization(real_Outputdatas102, y_maxmins102)
    # 输出加权
    real_outputdata101 = funs.output_weight(real_outputdatas101)
    real_outputdata102 = funs.output_weight(real_outputdatas102)
    # 转置
    real_outputdata101 = np.array(real_outputdata101).T
    real_outputdata102 = np.array(real_outputdata102).T
    # 构造实时劣化序列
    real_deterlist101 = funs.build_deterlist(healthymodel101, real_inputdata101, real_outputdata101)
    real_deterlist102 = funs.build_deterlist(healthymodel102, real_inputdata102, real_outputdata102)
    # 存储实时劣化序列
    starttime101 = funs.read_deterlist(host= host,user= user,password= password,port= result_port,tablename= '101'+ update_times,length=1)[0][0]
    starttime102 = funs.read_deterlist(host= host,user= user,password= password,port= result_port,tablename= '102'+ update_times,length=1)[0][0]
    funs.hisdeterdata_starage(host=host, user=user, password=password, port=result_port, hisdeter_data=real_deterlist101, tablename='101' + update_times, starttime=starttime101)
    funs.hisdeterdata_starage(host=host, user=user, password=password, port=result_port, hisdeter_data=real_deterlist102, tablename='102' + update_times, starttime=starttime102)
    # 预测劣化序列,先读取历史序列
    deter_len = 200; deter_epochs = 300; timesteps = 12; pre_steps = 4; vmd_n = 3; vmd_alpha = 1000; thre_tablename = "tre_deter_threshold"
    deterlist101 = funs.read_deterlist(host=host, user=user, password=password, port=result_port,tablename='101'+ update_times, length=deter_len)
    deterlist102 = funs.read_deterlist(host=host, user=user, password=password, port=result_port,tablename='102'+ update_times, length=deter_len)
    [pre_test_result101, deter_testdata101, deter_startime101] = funs.deterlist_predict_model(epochs=deter_epochs,timesteps=timesteps, pre_steps=pre_steps,
                                                                                              vmd_k=vmd_n,vmd_alpha=vmd_alpha, deter_data0=deterlist101)
    [pre_test_result102, deter_testdata102, deter_startime102] = funs.deterlist_predict_model(epochs=deter_epochs,timesteps=timesteps, pre_steps=pre_steps,
                                                                                              vmd_k=vmd_n,vmd_alpha=vmd_alpha, deter_data0=deterlist102)
    # 预测结果存储
    len101 = len(real_outputdata101); len102 = len(real_outputdata102)
    real_pre_result101 = pre_test_result101[-len101:]
    real_pre_result102 = pre_test_result102[-len102:]
    pre_starttime101 = funs.read_pre_deterlist(host=host, user=user, password=password, port=result_port, tablename='101' + update_times,length=1)[0][0]
    pre_starttime102 = funs.read_pre_deterlist(host=host, user=user, password=password, port=result_port, tablename='102' + update_times,length=1)[0][0]
    funs.deterpre_starage(host=host, user=user, password=password, port=result_port,
                              hisdeter_data=real_pre_result101, tablename='101' + update_times, starttime=pre_starttime101)
    funs.deterpre_starage(host=host, user=user, password=password, port=result_port,
                              hisdeter_data=real_pre_result102, tablename='102' + update_times, starttime=pre_starttime102)