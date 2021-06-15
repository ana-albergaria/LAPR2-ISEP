package app.domain.sort.algorithms;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BubbleSort {
    public <T> void bubbleSortArrayList(List<T> list, Comparator<? super T> comparator) {
        T temp;
        int k = list.size();
        for (int i = 0; i < k-1; i++) {
            for (int j = 0; j < (k-i-1); j++) {
                if (comparator.compare(list.get(j),list.get(j + 1)) > 0) {
                    temp = list.get(j); // temp = arr[j]
                    list.set(j, list.get(j + 1)); // arr[j] = arr[j+1]
                    list.set(j + 1, temp); // arr[j+1] =temp = arr[j]
                }
            }
        }
    }
}
