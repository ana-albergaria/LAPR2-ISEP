package app.domain.model;

import java.util.ArrayList;

/**
 * Finds the contiguous subsequence with maximum sum of an interval, through a brute-force algorithm.
 *
 * @author Marta Ribeiro 1201592
 */
public class BruteForceAlgorithm {
    public BruteForceAlgorithm(){
    }

    public static int[] Max(int[] seq){
        ArrayList<Integer> subMaxSum = new ArrayList<>();
        int sumValue = 0;
        int num = 0;
        for (int i = 0; i < seq.length; i++) {
            for (int j = 0; j < seq.length; j++) {
                if (i<j) {
                    for (int k = i; k <= j; k++) {
                        num=num+seq[k];
                    }
                    if (num > sumValue) {
                        subMaxSum.clear();
                        for (int l = i; l <= j; l++) {
                            subMaxSum.add(seq[l]);
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
