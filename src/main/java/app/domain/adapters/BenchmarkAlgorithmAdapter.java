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
        long start1 = System.nanoTime();
        int[] result = Sum.Max(interval);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in milli seconds(Benchmark Algorithm): "+ (end1-start1)/* + "ns for input size:" + */);
        return result;
    }

}