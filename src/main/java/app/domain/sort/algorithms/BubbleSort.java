package app.domain.sort.algorithms;

import java.util.Comparator;
import java.util.List;

public class BubbleSort {
    public <T> void bubbleSortArrayList(List<T> list, Comparator<? super T> comparator) {
        T temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size()-1; i++) {
                if (comparator.compare(list.get(i),list.get(i + 1)) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }
}
