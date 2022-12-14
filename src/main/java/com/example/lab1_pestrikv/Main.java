package com.example.lab1_pestrikv;

import java.util.Arrays;
import java.util.OptionalDouble;

import static com.example.lab1_pestrikv.algorithms.binaryImplementation.binarySearch;
//import static com.example.lab1_pestrikv.algorithms.exponentialImplementation.exponentialSearch;
import static com.example.lab1_pestrikv.algorithms.exponentialImplementation.exponential;
import static com.example.lab1_pestrikv.generatedMatrixes.firstMatrixGeneration;
import static com.example.lab1_pestrikv.generatedMatrixes.secondMatrixGeneration;
import static com.example.lab1_pestrikv.algorithms.linearImplementation.linearSearch;

public class Main {
    public static void main(String[] args) {
        int columns = (int) Math.pow(2, 13); //input rows value
        int firstGenerationTarget = 2 * columns + 1;
        int secondGenerationTarget = 16 * columns + 1;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < Math.pow(2,5); j++) {
//                System.out.print(firstMatrixGeneration(rows,(int)Math.pow(2,5))[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 1; i <= 13; i++) {
            scoring((int) Math.pow(2, i), columns, firstGenerationTarget, secondGenerationTarget, false);
        }
        for (int i = 1; i <= 13; i++) {
            scoring((int) Math.pow(2, i), columns, firstGenerationTarget, secondGenerationTarget, true);
        } //we need to take last 13 results in console, it is solves java in-time compilation problem
    }

    public static void scoring(int rows, int columns,
                               int firstGenerationTarget, int secondGenerationTarget, boolean print) {
        long[] binaryResults = new long[100];
        long[] expResults = new long[100];
        long[] linearResults = new long[100];
        long[] secondExpResults = new long[100];
        for (int i = 0; i < 100; i++) {
            long start;
            long end;
            int[][] firstMatrix = firstMatrixGeneration(rows, columns);
            int[][] secondMatrix = secondMatrixGeneration(rows, columns);
            start = System.nanoTime();
            int binValue = binarySearch(firstMatrix, firstGenerationTarget);
            end = System.nanoTime();
            binaryResults[i] = end - start;

            start = System.nanoTime();
//            int expValue = exponentialSearch(firstMatrix, firstGenerationTarget, 2);
            int expValue = exponential(firstMatrix,firstGenerationTarget);
            end = System.nanoTime();
            expResults[i] = end - start;

            start = System.nanoTime();
            int linearValue = linearSearch(firstMatrix, firstGenerationTarget);
            end = System.nanoTime();
            linearResults[i] = end - start;

            start = System.nanoTime();
//            int secondExpVariationValue = exponentialSearch(secondMatrix, secondGenerationTarget, 2);
            int secondExpVariationValue = exponential(secondMatrix,secondGenerationTarget);
            end = System.nanoTime();
            secondExpResults[i] = end - start;
        }
        OptionalDouble binAvg = Arrays.stream(binaryResults).average();
        OptionalDouble expAvg = Arrays.stream(expResults).average();
        OptionalDouble linAvg = Arrays.stream(linearResults).average();
        OptionalDouble secondExpAvg = Arrays.stream(secondExpResults).average();
        if (print)
            System.out.println("bin " + binAvg.getAsDouble() + "ns "
                    + "exp " + expAvg.getAsDouble() + "ns "
                    + "standard " + linAvg.getAsDouble() + "ns "
                    + "second exp " + secondExpAvg.getAsDouble() + "ns");
    }
}

