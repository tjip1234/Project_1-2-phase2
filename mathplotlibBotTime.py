import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
plt.rcParams["figure.figsize"] = [7.00, 3.50]
plt.rcParams["figure.autolayout"] = True


x = [[1]]
for i in range(24):
    print(x[0][0])
    x.append([x[i][0]/1.2])


df0 = pd.read_csv("class Bots.HillClimbImprovedTime.csv")
df1 = pd.read_csv("class Bots.ParticleSwarmTime.csv")
df2 = pd.read_csv("class Bots.RuleBOtTime.csv")

df0 = df0.values.tolist()
df1 = df1.values.tolist()
df2 = df2.values.tolist()


#print("Contents in csv file:\n", df1)
plt.plot(x,df0, label="HillClimberImproved")
plt.plot(x,df1, label="ParticleSwarm")
plt.plot(x,df2, label="RuleBot")

plt.legend(loc="upper right")
#slope, intercept = np.polyfit(np.log(df2), np.log(x), 1)
plt.ylabel('Hits')
plt.xlabel('Size of Hole')
plt.title('Bots on Easy Terrain')
plt.yscale('log')
plt.show()