import pprint
import matplotlib.pyplot as plt
import pandas as pd
with open('../key.txt') as f:
    key = f.read()
    
with open('../names.txt') as f:
    name = f.read().split(',')  
print(name)
# with open("../"+key) as f:
#     data_string = f.readlines()
# Чтение данных из файла
data = pd.read_csv('../'+key, sep=',', header=None)
# print(data['Y'])
# Создание графика
print(data)
n = data.columns.size
for i in range(1, n):
    plt.figure()
    plt.plot(data[0], data[i])


    # Добавление подписей
    plt.xlabel('time')
    plt.ylabel('value')
    plt.title(name[i])
    plt.savefig(name[i], dpi=200)

# Отображение графика
# plt.show()