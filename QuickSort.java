
import java.util.List;
import java.util.Random;

public class QuickSort {
    /**
     * Quicksorting
     * @param list
     * @param sortPrefer
     * @param sortAttr
     */
    public static void quickSort(List<DataEntity> list, String sortPrefer, String sortAttr) {
        if (list != null) {
            disorderSort(list);
            if (sortPrefer == "ascending") {
                quickSortAsc(list, 0, list.size() - 1, sortAttr);
            } else if (sortPrefer == "descending") {
                quickSortDesc(list, 0, list.size() - 1, sortAttr);
            }
        }
    }

    /**
     * Quicksorting in descending order
     * @param list
     * @param high
     * @param low
     * @param sortAttr
     */
    public static void quickSortDesc(List<DataEntity> list, int high, int low, String sortAttr) {
        int i, j;
        DataEntity temp;
        i = high;  // largest index
        j = low;   //smallest index
        temp = list.get(i); // take the first elements。
        if (sortAttr == "price") {
            while (i < j) {   
                while (i < j && temp.getAdjClose() > list.get(j).getAdjClose())
                    j--;     
                if (i < j) {
                    list.set(i, list.get(j));
                    i++;
                }
                while (i < j && temp.getAdjClose() < list.get(i).getAdjClose())
                    i++;
                if (i < j) {
                    list.set(j, list.get(i));
                    j--;
                }
            }
        }else if (sortAttr == "date"){
            while (i < j) {
                while (i < j && temp.getDate().after(list.get(j).getDate())) 
                    j--;
                if (i < j) {
                    list.set(i, list.get(j));
                    i++;
                }
                while (i < j && temp.getDate().before(list.get(i).getDate())) //在数组的前端扫描
                    i++;
                if (i < j) {
                    list.set(j, list.get(i));
                    j--;
                }
            }
        }
        list.set(i, temp);
        if (high < i) 
            quickSortDesc(list, high, i - 1, sortAttr);
        if (i < low)  
            quickSortDesc(list, i + 1, low, sortAttr);

    }

    /**
     * Quicksorting in ascending order
     * @param list
     * @param low
     * @param high
     * @param sortAttr
     */
    public static void quickSortAsc(List<DataEntity> list, int low, int high, String sortAttr) {
        int i, j;
        DataEntity temp;
        i = low;//lower index
        j = high;//upper index
        temp = list.get(i);  //take the first element as standard
        if (sortAttr == "price") {
            while (i < j) {   
                while (i < j && temp.getAdjClose() < list.get(j).getAdjClose()) 
                    j--;  
                if (i < j) {
                    list.set(i, list.get(j));
                    i++;
                }
                while (i < j && temp.getAdjClose() > list.get(i).getAdjClose()) 
                    i++;
                if (i < j) {
                    list.set(j, list.get(i));
                    j--;
                }
            }
        }else if (sortAttr == "date"){
            while (i < j) {
                while (i < j && temp.getDate().before(list.get(j).getDate())) 
                    j--;
                if (i < j) {
                    list.set(i, list.get(j));
                    i++;
                }
                while (i < j && temp.getDate().after(list.get(i).getDate()))
                    i++;
                if (i < j) {
                    list.set(j, list.get(i));
                    j--;
                }
            }
        }
        list.set(i, temp);   //Put temp at the right place
        if (low < i)
            quickSortAsc(list, low, i - 1, sortAttr);
        if (i < high)
            quickSortAsc(list, j + 1, high, sortAttr); 
    }

    public static void disorderSort(List<DataEntity> list) {    //
        Random random = new Random();
        for(int i = 0; i < list.size(); i++) {
            int j = random.nextInt(list.size());
            DataEntity temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}

