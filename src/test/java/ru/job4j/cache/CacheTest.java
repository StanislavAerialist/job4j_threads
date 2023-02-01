package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CacheTest {
    @Test
    void whenAddIsTrue() {
        Cache cache = new Cache();
        boolean rsl = cache.add(new Base(1, 1));
        assertThat(rsl).isTrue();
        assertThat(cache.get(1)).isEqualTo(new Base(1, 1));
    }

    @Test
    void whenAddIsFalse() {
        Cache cache = new Cache();
        cache.add(new Base(1, 1));
        boolean rsl = cache.add(new Base(1, 9));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenUpdateIsTrue() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 5);
        base1.setName("User1");
        cache.add(base1);
        Base base2 = new Base(1, 1);
        base2.setName("User2");
        boolean rsl = cache.update(base2);
        assertThat(rsl).isTrue();
        assertThat(cache.get(1).getName()).isEqualTo("User2");
        assertThat(cache.get(1).getVersion()).isEqualTo(6);
    }

    @Test
    void whenDeleted() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        cache.add(base1);
        cache.delete(base1);
        assertThat(cache.get(base1.getId())).isNull();
    }
}