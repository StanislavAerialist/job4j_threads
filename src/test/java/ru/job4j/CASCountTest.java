package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {

    @Test
    public void whenIncrement() {
        CASCount count = new CASCount();
        count.increment();
        assertThat(count.get()).isEqualTo(1);
    }

    @Test
    public void whenGet() {
        CASCount count = new CASCount();
        assertThat(count.get()).isEqualTo(0);
    }

    @Test void when2IncrementAndGet() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        int rsl = count.get();
        assertThat(rsl).isEqualTo(2);
    }

}