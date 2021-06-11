package app.domain.adapters;

import app.domain.interfaces.SubMaxSumAlgorithms;
import app.domain.model.BruteForceAlgorithm;

/**
 * Finds the contiguous subsequence with maximum sum of an interval, through a brute-force algorithm.
 *
 * @author Marta Ribeiro 1201592
 */
public class BruteForceAlgorithmAdapter implements SubMaxSumAlgorithms {
    public BruteForceAlgorithmAdapter(){
    }

    @Override
    public int[] findSubMaxSum(int[] interval){
        return BruteForceAlgorithm.Max(interval);
    }
}
