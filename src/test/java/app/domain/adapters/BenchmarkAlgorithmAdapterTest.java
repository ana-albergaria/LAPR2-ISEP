package app.domain.adapters;

import app.domain.adapters.BenchmarkAlgorithmAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BenchmarkAlgorithmAdapterTest {

    private BenchmarkAlgorithmAdapter bma;
    private int[] example1;
    private int[] example2;
    private int[] example1Result;
    private int[] example2Result;

    @Before
    public void setUp() {
        bma = new BenchmarkAlgorithmAdapter();
        example1 = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
        example1Result = new int[]{51, -9, 44, 74, 4};
        example2 = new int[]{17, -2, 4, 20, -44, 30};
        example2Result = new int[]{17, -2, 4, 20};
    }

    //Test 1
    @Test
    public void checkIfSubMaxSumIsFound1(){
        int[] obtainedResult = bma.findSubMaxSum(example1);
        Assert.assertArrayEquals(example1Result, obtainedResult);
    }

    //Test 2
    @Test
    public void checkIfSubMaxSumIsFound2(){
        int[] obtainedResult = bma.findSubMaxSum(example2);
        Assert.assertArrayEquals(example2Result, obtainedResult);
    }

}