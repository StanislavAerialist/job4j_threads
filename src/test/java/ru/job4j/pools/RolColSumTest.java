package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.pools.RolColSum.Sums;
import static ru.job4j.pools.RolColSum.sum;
import static ru.job4j.pools.RolColSum.asyncSum;

class RolColSumTest {

    @Test
    public void whenMatrix2() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        Sums[] sum = sum(matrix);
        Sums[] asyncSum = asyncSum(matrix);
        Sums expected = new RolColSum.Sums();
        expected.setColSum(6);
        expected.setRowSum(7);

        assertThat(sum[1]).isEqualTo(expected);
        assertThat(asyncSum[1]).isEqualTo(expected);
    }

    @Test
    public void whenMatrix3() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] sum = sum(matrix);
        Sums[] asyncSum = asyncSum(matrix);
        Sums expected = new RolColSum.Sums();
        expected.setColSum(12);
        expected.setRowSum(6);

        assertThat(sum[0]).isEqualTo(expected);
        assertThat(asyncSum[0]).isEqualTo(expected);
    }
}