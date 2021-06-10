package app.domain.adapters;

import app.domain.interfaces.SortAlgorithm;
import app.domain.model.Client;
import app.domain.sort.algorithms.BubbleSort;
import app.domain.sort.algorithms.MergeSort;

import java.util.Comparator;
import java.util.List;

public class MergeSortAdapter implements SortAlgorithm {
    @Override
    public void sortClientsList(List<Client> clients, Comparator<Client> comparator) {
        System.out.println("QUi");
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(clients, comparator);
    }
}

