package ru.job4j.pools;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return "Sums{" + "rowSum=" + rowSum
                    + ", colSum=" + colSum + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

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
        return CompletableFuture.supplyAsync(() -> {
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
        });
    }
}