package app.domain.adapters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BenchmarkAlgorithmAdapterTest {

    private static BenchmarkAlgorithmAdapter bma;
    private static int min;
    private static int max;

    @BeforeClass
    public static void setUp() {
        bma = new BenchmarkAlgorithmAdapter();
        min = -99;
        max = 99;
    }

    //Test 1
    /*@Test
    public void checkIfSubMaxSumIsFound1(){
        int[] example1 = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
        int[] example1Result = new int[]{51, -9, 44, 74, 4};
        int[] obtainedResult = bma.findSubMaxSum(example1);
        Assert.assertArrayEquals(example1Result, obtainedResult);
    }

    //Test 2
    @Test
    public void checkIfSubMaxSumIsFound2(){
        int[] example2 = new int[]{17, -2, 4, 20, -44, 30};
        int[] example2Result = new int[]{17, -2, 4, 20};
        int[] obtainedResult = bma.findSubMaxSum(example2);
        Assert.assertArrayEquals(example2Result, obtainedResult);
    }*/

    @Test
    public void runtimeTestAForBenchmark1Ints(){
        int num = 1;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBenchmark30Ints(){
        int num = 30;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestBForBenchmark60Ints(){
        int num = 60;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestCForBenchmark240Ints(){
        int num = 240;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestDForBenchmark480Ints(){
        int num = 480;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestEForBenchmark900Ints(){
        int num = 900;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestFForBenchmark1800Ints(){
        int num = 1800;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestGForBenchmark3000Ints(){
        int num = 3000;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestHForBenchmark6000Ints(){
        int num = 6000;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bma.findSubMaxSum(input);
    }

}