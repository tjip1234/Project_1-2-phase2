import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
plt.rcParams["figure.figsize"] = [7.00, 3.50]
plt.rcParams["figure.autolayout"] = True
x = [0.0001,0.1]
xi = np.arange(0.0001,0.1,0.0001)
df0 = pd.read_csv("class Math.RungeKutta2.csv")
#df1 = pd.read_csv("class Math.BackwardsEuler.csv")
df2 = pd.read_csv("class Math.RungeKutta4.csv")
df3 = pd.read_csv("class Math.Euler.csv")
print(xi)
#print("Contents in csv file:\n", df1)
plt.plot(df0, label="Rk2")
#plt.plot(df1, label="BackwardsEuler")
plt.plot(df2, label="RK4")
plt.plot(df3, label="Euler")
plt.legend(loc="upper right")

plt.ylabel('RelativeError')
plt.xlabel('StepSize')
plt.title('Bots compared with steps')
plt.yscale('log')
plt.xscale('log')
plt.show()