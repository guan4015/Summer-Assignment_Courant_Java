# Summer Assignment -- Java

This project first reads the S&P 500 file in .csv format and then sorting them according to specified attributes. It then can be used to retrieve the adjusted close prices in certain periods, compute the average and moving average of adjusted close prices and obtain the maximum of adjusted close prices in certain period.  

## Getting Started

This project consists of five .java files.

*  The DataEntity.java defines the object for the input of each line in .csv file.
*  The DataHandler.jave creates the linkedlist made up of DataEntity and contains methods: loadPriceData, getPrices, computeAverage, computeMax, computeMovingAverage, insertPrice, correctPrice, writeFile.
*  The BubbleSort.java contains the bubblesorting algorithm with DataEntity typed linkedlist as input.
*  The QuickSort.java contains the bubblesorting algorithm with DataEntity typed linkedlist as input.
*  The Main.java is used to test the functionalities defined in the rest java files.

### Prerequisites

This project needs the two additional .csv files, namely prices.csv and corrections.csv to run the Main.java functions. They are included in the folder /input. 

### Installing

To run the Main.java file, you should place all the java files either under the directory of source or in certain packages. What's more, the two .csv files should be put under the project main directory (Note that this is not the /bin but the one /ProjectName/). 


## Running the tests

Notice that all the functionalities are tested in the Main.java file. If you have ecclipse, then you can click "run" to execute the "Main.java" program and it should out a txt file which records the prices history, average prices, moving average etc. 

### Break down into subtests

*  The first job is to load the prices. We intialize a DataHandler object called "datapreprocess", then the following code is used to extract the S&P 500 prices. The first argument is the path of the file. The second argument is the method for sorting the file. The third specifies which order the sorted file should be. The fourth is the attribute on which the sorting is performed. The fifth is the delimiter of the file.

```
datapreprocess.loadPriceData("prices.csv", "bubblesort", "ascending", "date", ",");
```

*   The following member function corrects the flaw prices. The corrections are firstly recorded in .csv file and then implemented into the object datapreprocess to modify the prices. 

```
datapreprocess.correctPrices("corrections.csv", ",");
```

*   The following method get the historical prices between the time s1 and s2.

```
datapreprocess.getPrices(s1, s2)
```
*   The following method computes the average adjusted close prices between sa1 and sa2.

```
datapreprocess.computeAverage(sa1, sa2)
```
*   The following method computes the maximum of adjusted close prices between sm1 and sm2

```
datapreprocess.computeMax(sm1,sm2)
```
*   The following method computes the moving average of adjusted close prices between smv1 and smv2
```
datapreprocess.computeMovingAverage(windowsize, smv1,smv2)
```

## Authors

* **Xiao Guan** - *Initial work* - [XiaoGuan_Summer Assignment](https://github.com/guan4015/Summer-Assignment_Courant_Java/)


## Acknowledgments

* 

