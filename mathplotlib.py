import pandas as pd
import numpy as math
from matplotlib import pyplot as plt
plt.rcParams["figure.figsize"] = [7.00, 3.50]
plt.rcParams["figure.autolayout"] = True
x = [[0.005],[0.01],[0.02],[0.05],[0.1]]
df0 = pd.read_csv("class Math.RungeKutta2.csv")
df1 = pd.read_csv("class Math.ImprovedEuler.csv")
df2 = pd.read_csv("class Math.RungeKutta4.csv")
df3 = pd.read_csv("class Math.Euler.csv")
df0 = df0.values.tolist()
df1 = df1.values.tolist()
df2 = df2.values.tolist()
df3 = df3.values.tolist()

#print("Contents in csv file:\n", df1)
plt.plot(x,df3, label="Euler Slope: " + str((math.log10(df3[0][0])-math.log10(df3[4][0]))/(math.log10(x[0][0])-math.log10(x[4][0]))))
plt.plot(x,df0, label="Rk2   Slope: " + str((math.log10(df0[0][0])-math.log10(df0[4][0]))/(math.log10(x[0][0])-math.log10(x[4][0]))))
#plt.plot(x,df1, label="ImprovedEuler  Slope: " + str((math.log10(df1[0][0])-math.log10(df1[4][0]))/(math.log10(x[0][0])-math.log10(x[4][0]))))
plt.plot(x,df2, label="RK4   Slope: " + str((math.log10(df2[1][0])-math.log10(df2[4][0]))/(math.log10(x[1][0])-math.log10(x[4][0]))))

plt.legend(loc="lower right")

print((math.log10(df0[0][0])-math.log10(df0[4][0]))/(math.log10(x[0][0])-math.log10(x[4][0])))
#slope, intercept = np.polyfit(np.log(df2), np.log(x), 1)
plt.ylabel('RelativeError')
plt.xlabel('StepSize')
plt.title('Ode Solvers proof of correctness')
plt.yscale('log')
plt.xscale('log')
plt.show()