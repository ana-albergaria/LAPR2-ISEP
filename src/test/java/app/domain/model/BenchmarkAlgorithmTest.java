package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BenchmarkAlgorithmTest {

    private BenchmarkAlgorithm bma;
    private int[] example;
    private int[] exampleResult;

    @Before
    public void setUp() {
        bma = new BenchmarkAlgorithm();
        example = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
        exampleResult = new int[]{51, -9, 44, 74, 4};
    }

    //Test 1
    @Test
    public void checkIfSubMaxSumIsFound(){
        int[] obtainedResult = bma.findSubMaxSum(example);
        Assert.assertArrayEquals(exampleResult, obtainedResult);
    }

}