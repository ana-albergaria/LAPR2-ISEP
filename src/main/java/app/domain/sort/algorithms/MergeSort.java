package app.domain.sort.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        if (list.size() < 2) {
            return;
        }
        int mid = list.size()/2;
        List<T> left = new ArrayList<T>(list.subList(0, mid));
        List<T> right = new ArrayList<T>(list.subList(mid, list.size()));

        sort(left, comparator);
        sort(right, comparator);
        merge(left, right, list, comparator);
    }

    private <T> void merge(
            List<T> left, List<T> right, List<T> list,Comparator<? super T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int listIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex),right.get(rightIndex)) < 0) {
                list.set(listIndex++, left.get(leftIndex++));
            } else {
                list.set(listIndex++, right.get(rightIndex++));
            }
        }
        while (leftIndex < left.size()) {
            list.set(listIndex++, left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            list.set(listIndex++, right.get(rightIndex++));
        }
    }

}
