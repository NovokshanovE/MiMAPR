
import pprint
import matplotlib.pyplot as plt

with open('../key.txt') as f:
    key = f.read()
    
    
with open("../"+key) as f:
    data_string = f.readlines()


data = []
for line in data_string:
    data.append(line.split(' '))
    


time = [t[0] for t in data]
data_y = []
for i in range(1, len(data[0])):
    data_y.append([t[i] for t in data])
    

# for i in range(1,2):
plt.figure()
plt.plot(time, data_y[5])
    


plt.show()
    