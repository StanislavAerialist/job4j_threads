package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = findElement(matrix, i);
        }
        return rsl;
    }

    private static Sums findElement(int[][] matrix, int index) {
        Sums rsl = new Sums();
        int rowSum = 0;
        int colSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            rowSum = rowSum + matrix[index][i];
            colSum = colSum + matrix[i][index];
        }
        rsl.setRowSum(rowSum);
        rsl.setColSum(colSum);
        return rsl;
    }
    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = findElementAsync(matrix, i).get();
        }
        return rsl;
    }

    private static CompletableFuture<Sums> findElementAsync(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> findElement(matrix, index));
    }
}