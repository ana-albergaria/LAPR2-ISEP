package app.domain.model;

import app.domain.interfaces.SubMaxSumAlgorithms;
import com.isep.mdis.Sum;

/**
 * Finds the contiguous subsequence with maximum sum of an interval, through a benchmark algorithm.
 *
 * @author Marta Ribeiro 1201592
 */
public class BenchmarkAlgorithm implements SubMaxSumAlgorithms {
    public BenchmarkAlgorithm(){
    }
    public int[] findSubMaxSum(int[] interval){
        int[] worstSubInt = Sum.Max(interval);
        return worstSubInt;
    }
}
