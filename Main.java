// Edited by Xiao Guan, August 29, 2017

// This file tests the usage of Datahandler.

// Modifications:
// Change the input of correctprices


import java.util.List;

public class Main{

    public static void main(String[] args){

        //Create an object of class DataHandler
        DataHandler datapreprocess = new DataHandler();
        
        // Call the loadPriceData to load data and sort the data.
        datapreprocess.loadPriceData("prices.csv", "bubblesort", "ascending", "date", ",");
        
        //Call the correctPrices to modify the prices
        datapreprocess.correctPrices("corrections.csv", ",");
        
        
        //Retrieve the AdjClosePrices during specified period.
        String s1 = "08/15/2004";
        String s2 = "08/20/2004";
        List<Double> priceList = datapreprocess.getPrices(s1, s2);
        // Record result
        String printString = String.format("\n The Prices of SPY between %s and %s are: " + priceList.toString().replaceAll("\\[|\\]", "") + ".\n", s1,s2);
        datapreprocess.writeFile("results.txt", printString);
        
         // Compute the average AdjClosePrice for specified period.
        String sa1 = "08/15/2004";
        String sa2 = "09/15/2004";
        double average = datapreprocess.computeAverage(sa1, sa2);
        // Record result
        printString = String.format("\n The Average Price of SPY between %s and %s is: " + average + "\n.", sa1,sa2 );
        datapreprocess.writeFile("results.txt", printString);
        
        // Compute the max AdjClosePrice for specified period.
        String sm1 = "04/15/2004";
        String sm2 = "06/15/2004";
        double max = datapreprocess.computeMax(sm1,sm2);
        // Record result
        printString = String.format("\n The Maximum Price of SPY between %s and %s is: " + max + ".\n", sm1,sm2);
        datapreprocess.writeFile("results.txt", printString);
        
        // Compute the moving average for specified period given the window size
        String smv1 = "08/15/2004";
        String smv2 = "09/15/2004";
        int windowsize = 10;
        priceList = datapreprocess.computeMovingAverage(windowsize, smv1,smv2);
        // Record the result
        printString = String.format("\n The Moving Average of SPY between %s and %s for WindowSize %s is: " + priceList.toString().replaceAll("\\[|\\]","") + ".\n", smv1,smv2,Integer.toString(windowsize));
        datapreprocess.writeFile("results.txt", printString);
        // The first output number should be 96.04 since the sum of the ten adjusted close prices starting from 8/15/2004 is 960.4
   }
}

