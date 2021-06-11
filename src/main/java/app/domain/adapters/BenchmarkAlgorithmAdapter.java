package app.domain.adapters;

import app.domain.interfaces.SubMaxSumAlgorithms;
import com.isep.mdis.Sum;

/**
 * Finds the contiguous subsequence with maximum sum of an interval, through a benchmark algorithm.
 *
 * @author Marta Ribeiro 1201592
 */
public class BenchmarkAlgorithmAdapter implements SubMaxSumAlgorithms {
    public BenchmarkAlgorithmAdapter(){
    }

    @Override
    public int[] findSubMaxSum(int[] interval){
        return Sum.Max(interval);
    }
}