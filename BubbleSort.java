
import java.util.List;


public class BubbleSort {
    /**
     * Bubble Sort
     * @param list : a list of DataEntity Objects
     * @param sortPrefer: "ascending" and "descending"
     * @param sortAttr: "date" and "price"
     */
	public static void exch(List<DataEntity> list, int i, int j) {
		DataEntity temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
    public static void bubbleSort(List<DataEntity> list, String sortPrefer, String sortAttr) {
        if (sortAttr == "date") {
            if (sortPrefer == "ascending") {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if (list.get(i).getDate().after(list.get(j).getDate())) {
                            exch(list,i,j);
                        }
                    }
                }
            } else if (sortPrefer == "descending") {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if (list.get(i).getDate().before(list.get(j).getDate())) {
                        	exch(list,i,j);
                        }
                    }
                }
            }
        } else if (sortAttr == "price") {
            if (sortPrefer == "ascending") {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if (list.get(i).getAdjClose() > list.get(j).getAdjClose()) {
                        	exch(list,i,j);
                        }
                    }
                }
            } else if (sortPrefer == "descending") {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if (list.get(i).getAdjClose() < list.get(j).getAdjClose()) {
                        	exch(list,i,j);
                        }
                    }
                }
            }
        }
    }
}
