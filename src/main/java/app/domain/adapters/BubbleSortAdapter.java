package app.domain.adapters;

import app.domain.interfaces.SortAlgorithm;
import app.domain.model.Client;
import app.domain.sort.algorithms.BubbleSort;

import java.util.Comparator;
import java.util.List;

public class BubbleSortAdapter implements SortAlgorithm {
    @Override
    public void sortClientsList(List<Client> clients, Comparator<Client> comparator) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSortArrayList(clients, comparator);
    }
}
