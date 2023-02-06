package ru.job4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchWithFJPool<T> extends RecursiveTask<Integer> {
    public static final int MIN_SIZE_ARRAY = 9;
    private final T[] array;
    private final int from;
    private final int to;
    private final T element;

    public SearchWithFJPool(T[] array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= MIN_SIZE_ARRAY) {
            return lSearch();
        }
        int mid = (from + to) / 2;
        SearchWithFJPool<T> leftArr = new SearchWithFJPool<T>(array, from, mid, element);
        SearchWithFJPool<T> rightArr = new SearchWithFJPool<T>(array, mid + 1, to, element);
        leftArr.fork();
        rightArr.fork();
        return Math.max(leftArr.join(), rightArr.join());
    }

    private Integer lSearch() {
        int rsl = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(element)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
    public static <T> Integer search(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchWithFJPool<>(array, 0, array.length - 1, element));
    }
}
