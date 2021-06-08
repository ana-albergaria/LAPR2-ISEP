package app.domain.model;

import app.domain.interfaces.SubMaxSumAlgorithms;

import java.util.ArrayList;

/**
 * Finds the contiguous subsequence with maximum sum of an interval, through a brute-force algorithm.
 *
 * @author Marta Ribeiro 1201592
 */
public class BruteForceAlgorithm implements SubMaxSumAlgorithms {
    public BruteForceAlgorithm(){
    }
    public int[] findSubMaxSum(int[] interval){
        ArrayList<Integer> subMaxSum = new ArrayList<>();
        int sumValue = 0;
        int num = 0;
        for (int i = 0; i < interval.length; i++) {
            for (int j = 0; j < interval.length; j++) {
                if (i<j) {
                    for (int k = i; k <= j; k++) {
                        num=num+interval[k];
                    }
                    if (num > sumValue) {
                        subMaxSum.clear();
                        for (int l = i; l <= j; l++) {
                            subMaxSum.add(interval[l]);
                        }
                        sumValue=num;
                    }
                    num=0;
                }
            }
        }
        int[] finalSubMaxSum = new int[subMaxSum.size()];
        for (int i = 0; i < finalSubMaxSum.length; i++) {
            finalSubMaxSum[i] = subMaxSum.get(i).intValue();
        }
        return finalSubMaxSum;
    }
}
