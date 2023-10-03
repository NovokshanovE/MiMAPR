# МиМАПР

## Типы элементов
1. E
2. C
3. R
4. L
5. I

## Библиотека которая лежит в основе решателя
https://commons.apache.org/

## Nodal
Базис узлового метода составляют переменные типа потенциала, 
в дальнейшем узловые потенциалы. В основе узлового метода лежит 
уравнение равновесия
$I(\phi^*) = 0$ - сумма переменных типа потока в узлах 
эквивалентной схемы равна нулю. Данное выражение представ 
собой систему нелинейных алгебраических уравнений, 
для решения которой используется метод Ньютона.


## Пример работы программы
```shell
Start...
Input numbers of nodes:2
Create scheme
Add new node to scheme
Add new node to scheme
Input numbers of elements: 4
Input type of new element:
1
Input value of new element:
12,0
Input start node of new element:
0
Input finish node of new element:
1
Input name of new element:
R
Add new elem to 0--Type:1

Add new elem to 1--Type:1

Add new elem to Scheme
Add new element to schemeInput type of new element:
2
Input value of new element:
10,1
Input start node of new element:
1
Input finish node of new element:
2
Input name of new element:
C
Add new elem to 1--Type:2

Add new elem to 2--Type:2

Add new elem to Scheme
Add new element to schemeInput type of new element:
3
Input value of new element:
12,2
Input start node of new element:
1
Input finish node of new element:
2
Input name of new element:
E
Add new elem to 1--Type:3

Add new elem to 2--Type:3

Add new elem to Scheme
Add new element to schemeInput type of new element:
4
Input value of new element:
2,0
Input start node of new element:
2
Input finish node of new element:
0
Input name of new element:
L
Add new elem to 2--Type:4

Add new elem to 0--Type:4

Add new elem to Scheme
Add new element to schemeNumber of sources of EMF: 1
Number of current sources: 0
Node: 0
------------------------------
Name: R
Value:12.000000
Type:1
Node start: 0  Node finish: 1
------------------------------
------------------------------
Name: L
Value:2.000000
Type:4
Node start: 2  Node finish: 0
------------------------------
Node: 1
------------------------------
Name: R
Value:12.000000
Type:1
Node start: 0  Node finish: 1
------------------------------
------------------------------
Name: C
Value:10.100000
Type:2
Node start: 1  Node finish: 2
------------------------------
------------------------------
Name: E
Value:12.200000
Type:3
Node start: 1  Node finish: 2
------------------------------
Node: 2
------------------------------
Name: C
Value:10.100000
Type:2
Node start: 1  Node finish: 2
------------------------------
------------------------------
Name: E
Value:12.200000
Type:3
Node start: 1  Node finish: 2
------------------------------
------------------------------
Name: L
Value:2.000000
Type:4
Node start: 2  Node finish: 0
------------------------------
```
