package ru.job4j.linked;

public class Node<T> {
    private final Node<T> next = null;
    private final T value = null;

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

}