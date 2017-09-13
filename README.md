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

*  The first job is to load the prices. We intialize a DataHandler object called datapreprocess, then the following code is used to extract the S&P 500 prices. The first argument is the path of the file. The second argument is the method for sorting the file. The third specifies which order the sorted file should be. The fourth is the attribute on which the sorting is performed. The fifth is the delimiter of the file.

```
datapreprocess.loadPriceData("prices.csv", "bubblesort", "ascending", "date", ",");
```

*   

```
datapreprocess.correctPrices("corrections.csv", ",");
```



### And coding style tests

Explain what these tests test and why

```
Give an example
```



## Authors

* **Xiao Guan** - *Initial work* - [XiaoGuan_Summer Assignment](https://github.com/guan4015/Summer-Assignment_Courant_Java/)


## Acknowledgments

* 

