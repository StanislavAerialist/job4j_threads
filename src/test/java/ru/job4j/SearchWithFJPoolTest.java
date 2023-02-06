package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.SearchWithFJPool.search;

class SearchWithFJPoolTest {

    @Test
    public void whenIntAndRecursive() {
        Integer[] array = {12, 1, 44, 3, 7, 11, 86, 105, 9, 81, 93, 77};
        Integer element = 105;
        Integer rsl = search(array, element);
        assertThat(rsl).isEqualTo(7);
    }

    @Test
    public void whenIntAndLine() {
        Integer[] array = {12, 1, 44, 3, 7, 11};
        Integer element = 7;
        Integer rsl = search(array, element);
        assertThat(rsl).isEqualTo(4);
    }

    @Test
    public void whenIntAndNotFound() {
        Integer[] array = {12, 1, 44, 3, 7, 11};
        Integer element = 8;
        Integer rsl = search(array, element);
        assertThat(rsl).isEqualTo(-1);
    }

    @Test
    public void whenObjectAndRecursive() {
        Object[] array = {"Java", 'h', 12, 17, 23, 42, "Java Developer", true, false, 'c', 'z'};
        Object element = "Java Developer";
        Integer rsl = search(array, element);
        assertThat(rsl).isEqualTo(6);
    }

    @Test
    public void whenObjectAndLine() {
        Object[] array = {"Java", 'h', 12, 17, 23, 42, "Java Developer"};
        Object element = "Java Developer";
        Integer rsl = search(array, element);
        assertThat(rsl).isEqualTo(6);
    }

    @Test
    public void whenObjectAndNotFound() {
        Object[] array = {"Java", 'h', 12, 17, 23, 42, "Java Developer"};
        Object element = true;
        Integer rsl = search(array, element);
        assertThat(rsl).isEqualTo(-1);
    }
}