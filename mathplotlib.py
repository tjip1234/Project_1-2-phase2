import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
plt.rcParams["figure.figsize"] = [7.00, 3.50]
plt.rcParams["figure.autolayout"] = True
x = [[0.000001],[0.00001],[0.0001],[0.001],[0.01]]
xi = np.arange(0.0001,0.1,0.0001)
df0 = pd.read_csv("class Math.RungeKutta2.csv")
df1 = pd.read_csv("class Math.ImprovedEuler.csv")
df2 = pd.read_csv("class Math.RungeKutta4.csv")
df3 = pd.read_csv("class Math.Euler.csv")
df0 = df0.values.tolist()
df1 = df1.values.tolist()
df2 = df2.values.tolist()
df3 = df3.values.tolist()
print((np.log(df1[4])-np.log(df1[0]))/(np.log(x[4])-np.log(x[0])))
print((np.log(df2[4])-np.log(df2[0]))/(np.log(x[4])-np.log(x[0])))
print(df0)
print(x)
#print("Contents in csv file:\n", df1)
plt.plot(x,df0, label="Rk2")
plt.plot(x,df1, label="ImprovedEuler")
plt.plot(df2, label="RK4")
plt.plot(x,df3, label="Euler")
plt.legend(loc="upper right")
#slope, intercept = np.polyfit(np.log(df2), np.log(x), 1)
plt.ylabel('RelativeError')
plt.xlabel('StepSize')
plt.title('Bots compared with steps')
plt.yscale('log')
plt.xscale('log')
plt.show()