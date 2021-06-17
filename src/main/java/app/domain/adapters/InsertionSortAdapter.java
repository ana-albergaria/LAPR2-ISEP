package app.domain.adapters;

import app.domain.interfaces.SortAlgorithm;
import app.domain.model.Client;
import app.domain.sort.algorithms.InsertionSort;

import java.util.Comparator;
import java.util.List;

public class InsertionSortAdapter implements SortAlgorithm {
    @Override
    public void sortClientsList(List<Client> clients, Comparator<Client> comparator) {
        System.out.println("QUi");
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(clients, comparator);
    }
}

