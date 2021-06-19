package app.domain.adapters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class BruteForceAlgorithmAdapterTest {

    private app.domain.adapters.BenchmarkAlgorithmAdapter bfa;
    private int min;
    private int max;

    @Before
    public void setUp() {
        bfa = new app.domain.adapters.BenchmarkAlgorithmAdapter();
        min = -99;
        max = 99;
    }

    //Test 3
    @Test
    public void checkIfSubMaxSumIsFound(){
        int[] example1 = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
        int[] example1Result = new int[]{51, -9, 44, 74, 4};
        int[] obtainedResult = bfa.findSubMaxSum(example1);
        Assert.assertArrayEquals(example1Result, obtainedResult);
    }

    //Test 4
    @Test
    public void checkIfSubMaxSumIsFound2(){
        int[] example2 = new int[]{17, -2, 4, 20, -44, 30};
        int[] example2Result = new int[]{17, -2, 4, 20};
        int[] obtainedResult = bfa.findSubMaxSum(example2);
        Assert.assertArrayEquals(example2Result, obtainedResult);
    }

    @Test
    public void runtimeTestAForBruteForce30Ints(){
        int num = 30;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBruteForce60Ints(){
        int num = 60;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBruteForce240Ints(){
        int num = 240;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBruteForce480Ints(){
        int num = 480;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBruteForce900Ints(){
        int num = 900;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBruteForce1800Ints(){
        int num = 1800;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBruteForce3000Ints(){
        int num = 3000;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

    @Test
    public void runtimeTestAForBruteForce6000Ints(){
        int num = 6000;
        int[] input = new int[num];
        Random rand = new Random();
        int rand_int;
        for (int i = 0; i < num; i++) {
            rand_int = rand.nextInt(max+1-min)+min;
            input[i]=rand_int;
        }
        int[] output = bfa.findSubMaxSum(input);
    }

}