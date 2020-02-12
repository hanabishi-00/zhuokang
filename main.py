import xlwt, xlrd
import numpy as np
import funs
import rwdata as rw
import time,datetime
import tensorflow as tf
import xlsxwriter
import os,sys
from sklearn.externals import joblib
import matplotlib.pyplot as plt
import main1, main2
from keras.layers.core import Dense, Dropout
from keras.layers.recurrent import LSTM
from keras.layers import Input
from keras.models import Model
from keras import backend as K

while True:
    while True:
        now =  datetime.datetime.now()
        if now.hour == 0 and now.minute == 0:
            break
        time.sleep(60)
    main2.daily_running()