// Edited by Xiao Guan, August 15, 2017

// This file implements the class "DataHandler" to read the data and do basic operations.


// Modifications:
// Adding loadPriceData function
// Adding getPrice function
// Adding computeAverage function
// Adding computeMovingAverage function
// Adding insertPrice function
// Adding correctPrice function
// Adding writeFile function


// Import java libraries
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  // Transform string to date using designated format
import java.util.ArrayList;
import java.util.Date;  // Used to deal with date
import java.util.LinkedList; // Used to define data structure
import java.util.List;


/**
 * @author xiaog
 *
 */
public class DataHandler {
	// Create the class member data
	// The data table is structured as LinkedList
    public List<DataEntity> list = new LinkedList<DataEntity>();  // A list of Data Entity object.
    public String sortPreference = "ascending";
    public String sortAttribute = "date";
    private BufferedReader reader = null;
    private BufferedReader readerc = null;

    /**
     * loadPriceData
     *
     * @param fileName
     * @param sortMethod : currently support "quicksort" and "bubblesort"
     * @param sortPrefer : currently support "ascending" and "descending"
     * @param sortAttr : currently only support "date" and "price"
     * @param csvSplitBy: the delimiter of the file
     * @return
     */
    public List<DataEntity> loadPriceData(String fileName, String sortMethod, String sortPrefer, String sortAttr, String csvSplitBy) {
        sortPreference = sortPrefer;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); // Ignore the title line
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] item = line.split(csvSplitBy);  //Usually delimiter is ","
                DataEntity dataEntity = new DataEntity();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
                // In this program we fix the format of the table.
                if (item.length == 7) {
                    dataEntity.setDate(sdf.parse(item[0]));
                    dataEntity.setOpen(Double.parseDouble(item[1]));
                    dataEntity.setHigh(Double.parseDouble(item[2]));
                    dataEntity.setLow(Double.parseDouble(item[3]));
                    dataEntity.setClose(Double.parseDouble(item[4]));
                    dataEntity.setVolume(Long.parseLong(item[5]));
                    dataEntity.setAdjClose(Double.parseDouble(item[6]));
                    list.add(dataEntity);
                }
            }
        } catch (ParseException e) {
        	e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (reader != null){
        		try {
        			reader.close();
        		} catch (IOException e){
        			e.printStackTrace();
        		}
        	}
        }
        
        if (sortMethod == "quicksort") {
            QuickSort.quickSort(list, sortPrefer, sortAttr);
        } else if (sortMethod == "bubblesort") {
            BubbleSort.bubbleSort(list, sortPrefer, sortAttr);
        }
        //System.out.println(list.get(0).getAdjClose());
        return list;
    }

    /**
     * getPrices
     *
     * @param fromDate MM/dd/yy
     * @param toDate MM/dd/yy
     * @return
     */
    public List<Double> getPrices(String fromDate, String toDate) {
        List<Double> priceList = new ArrayList<Double>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Date start = null;
        Date end = null;
        try {
            start = sdf.parse(fromDate);
            end = sdf.parse(toDate);
            for (DataEntity dataEntity : list) {
                if (!dataEntity.getDate().before(start) && !dataEntity.getDate().after(end)) {
                    priceList.add(dataEntity.getAdjClose());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return priceList;
    }

    /**
     * computeAverage  
     *
     * @param fromDate  MM/dd/yy
     * @param toDate    MM/dd/yy
     * @return
     */
    public double computeAverage(String fromDate, String toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        double sum = 0;
        double ave = 0;
        int n = 0;
        Date start = null;
        Date end = null;
        try {
            start = sdf.parse(fromDate);
            end = sdf.parse(toDate);
            for (DataEntity dataEntity : list) {
                if (!dataEntity.getDate().before(start) && !dataEntity.getDate().after(end)) {
                    sum += dataEntity.getAdjClose();
                    ++n;
                }
            }
            if (n != 0) {
                ave = sum / n;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.round(ave * 100) / 100.0;   //Rounding the error
    }

    /**
     * computeMax  
     *
     * @param fromDate  MM/dd/yy
     * @param toDate    MM/dd/yy
     * @return
     */
    public double computeMax(String fromDate, String toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        double max = 0;
        Date start = null;
        Date end = null;
        try {
            start = sdf.parse(fromDate);
            end = sdf.parse(toDate);
            
            /* The computation of max takes linear time in this case,
             * it has to go through the linked list. However, it will be more
             * efficient to develop another one to first judge if the list is sorted or
             * not.
             */
            for (DataEntity dataEntity : list) {
                if (!dataEntity.getDate().before(start) && !dataEntity.getDate().after(end)) {
                    if (dataEntity.getAdjClose() > max) {
                        max = dataEntity.getAdjClose();
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return max;
    }

    /**
     * computeMovingAverage
     *
     * @param windowSize 
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<Double> computeMovingAverage(int windowSize, String fromDate, String toDate) {
        List<Double> MovingAverageList = new ArrayList<Double>();
        List<Double> priceList = getPrices(fromDate, toDate);
        int size = priceList.size();
        /* Just to be careful that when we calculate the moving average, the list
         * should be usually sorted ascendingly at first.  
         */
        if (size < windowSize) {
            return null;
        } else {
        	double sum = 0;
            for (int i = 0; i <= size - windowSize; i++) {
                double ave = 0;
                if (i == 0) {
                	// The sum will adapt to the increment of i. When the index i increments,
                	// the sum will throw away the first summand and add the next one.
                	for (int j = i; j < i + windowSize; j++) {
                		sum += priceList.get(j);
                	}
                	ave = sum/windowSize;
                } else {
                	sum = sum - priceList.get(i-1) + priceList.get(i+windowSize-1);
                	ave = sum/windowSize;            
                }
                MovingAverageList.add(Math.round(ave*100d)/100d);
            }
        }
        return MovingAverageList;
    }


    public void insertPrice(String date, double open, double high, double low, double close, long volume, double adjClose) {
        DataEntity dataEntity = new DataEntity();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        try {
            dataEntity.setDate(sdf.parse(date));
            dataEntity.setOpen(open);
            dataEntity.setHigh(high);
            dataEntity.setLow(low);
            dataEntity.setClose(close);
            dataEntity.setVolume(volume);
            dataEntity.setAdjClose(adjClose);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDate().equals(dataEntity.getDate())) {
                    list.set(i, dataEntity);
                    return;
                }
                // The followings specify the cases where the inserted date is not
                // in the list.
                if (sortPreference == "ascending") {
                    if (list.get(i).getDate().after(dataEntity.getDate())) {
                        list.add(i, dataEntity);
                        return;
                    }
                    if (i == list.size() - 1) {  
                        list.add(i + 1, dataEntity);
                    }
                } else if (sortPreference == "descending") {
                    if (list.get(i).getDate().before(dataEntity.getDate())) {
                        list.add(i, dataEntity);
                        return;
                    }
                    if (i == list.size() - 1) {
                        list.add(i + 1, dataEntity);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method uses the insertPrice to override the incorrect prices
     * @param fileName: the original filename
     * @param SplitBy: delimiter of the file
     */
    public void correctPrices(String fileName, String SplitBy) {
        try {
            readerc = new BufferedReader(new FileReader(fileName));
            readerc.readLine(); 
            String line = null;
            int i = 0;
            while ((line = readerc.readLine()) != null) {
                String item[] = line.split(SplitBy);  //delimiter of the document
                if (item.length == 7) {
                    insertPrice(item[0], Double.parseDouble(item[1]), Double.parseDouble(item[2]), Double.parseDouble(item[3]), Double.parseDouble(item[4]),
                            Long.parseLong(item[5]), Double.parseDouble(item[6]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * writeFile
     *
     * @param fileName
     * @param content
     */
    public void writeFile(String fileName, String content) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.append(content+'\n');
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
