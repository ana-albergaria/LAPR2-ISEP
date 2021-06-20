package app.domain.adapters;

import app.domain.interfaces.SubMaxSumAlgorithms;
import app.thirdparty.BruteForceAlgorithm;

import java.io.Serializable;

/**
 * Finds the contiguous subsequence with maximum sum of an interval, through a brute-force algorithm.
 *
 * @author Marta Ribeiro 1201592
 */
public class BruteForceAlgorithmAdapter implements SubMaxSumAlgorithms, Serializable {

    /**
     * Method for getting the contiguous subsequence with maximum sum of an interval, through the brute-force algorithm
     * @param interval the interval to be analysed
     * @return the contiguous subsequence with maximum sum
     */
    @Override
    public int[] findSubMaxSum(int[] interval){
        long start2 = System.nanoTime();
        int[] result = BruteForceAlgorithm.Max(interval);
        long end2 = System.nanoTime();
        System.out.println("Elapsed Time in milli seconds(BruteForce Algorithm): "+ (end2-start2) + "ns for input size:" + interval.length);
        return result;
    }

}
