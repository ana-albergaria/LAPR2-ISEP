package app.domain.adapters;

import app.domain.interfaces.SubMaxSumAlgorithms;
import com.isep.mdis.Sum;

/**
 * Finds the contiguous subsequence with maximum sum of an interval, through a benchmark algorithm.
 *
 * @author Marta Ribeiro 1201592
 */
public class BenchmarkAlgorithmAdapter implements SubMaxSumAlgorithms {

    /**
     * Method for getting the contiguous subsequence with maximum sum of an interval, through the benchmark algorithm
     * @param interval the interval to be analysed
     * @return the contiguous subsequence with maximum sum
     */
    @Override
    public int[] findSubMaxSum(int[] interval){
        return Sum.Max(interval);
    }

}