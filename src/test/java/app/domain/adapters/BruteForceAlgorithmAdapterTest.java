package app.domain.adapters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BruteForceAlgorithmAdapterTest {

    private app.domain.adapters.BenchmarkAlgorithmAdapter bfa;
    private int[] example1;
    private int[] example2;
    private int[] example1Result;
    private int[] example2Result;

    @Before
    public void setUp() {
        bfa = new app.domain.adapters.BenchmarkAlgorithmAdapter();
        example1 = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
        example1Result = new int[]{51, -9, 44, 74, 4};
        example2 = new int[]{17, -2, 4, 20, -44, 30};
        example2Result = new int[]{17, -2, 4, 20};
    }

    //Test 3
    @Test
    public void checkIfSubMaxSumIsFound(){
        int[] obtainedResult = bfa.findSubMaxSum(example1);
        Assert.assertArrayEquals(example1Result, obtainedResult);
    }

    //Test 4
    @Test
    public void checkIfSubMaxSumIsFound2(){
        int[] obtainedResult = bfa.findSubMaxSum(example2);
        Assert.assertArrayEquals(example2Result, obtainedResult);
    }

}