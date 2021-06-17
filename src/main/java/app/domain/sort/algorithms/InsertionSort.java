package app.domain.sort.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InsertionSort {
    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        System.out.println("laricel");
        for (int j = 1; j < list.size(); j++) {
            T current = list.get(j);
            int i = j-1;
            while ((i >= 0) && ((comparator.compare(list.get(i),current)) > 0)) {
                list.set(i+1, list.get(i));
                i--;
            }
            list.set(i+1, current);
        }
    }

}
