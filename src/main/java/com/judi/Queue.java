package com.judi;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Queue<T> {

  private final Class<T> entityClass;
  private T[] queue;
  private int occupiedAmount;
  private int head;
  private int tail;
  private int capacity = 16;

  @SuppressWarnings("unchecked")
  public Queue(Class<T> c) {
    this.entityClass = c;
    this.queue = (T[]) Array.newInstance(this.entityClass, this.capacity);
  }

  @SuppressWarnings("unchecked")
  public Queue(Class<T> c, int capacity) {
    this.capacity = capacity;
    this.entityClass = c;
    this.queue = (T[]) Array.newInstance(this.entityClass, this.capacity);
  }

  public boolean push(T element) {
    if (!this.isFull()) {
      this.queue[this.tail] = element;
      this.tail = ++this.tail % this.queue.length;
      this.occupiedAmount++;
      return true;
    }
    return false;
  }

  public T pop() {
    if (!this.isEmpty()) {
      T poppedElement = this.queue[this.head];
      this.queue[this.head] = null;
      this.head = ++this.head % this.queue.length;
      this.occupiedAmount--;
      return poppedElement;
    }
    return null;
  }

  public boolean isFull() {
    return this.occupiedAmount == this.queue.length;
  }

  public boolean isEmpty() {
    return this.occupiedAmount == 0;
  }

  public void resize() {
    this.resize(this.capacity * 2);
  }

  @SuppressWarnings("unchecked")
  public void resize(int newCapacity) {
    if (newCapacity <= capacity) {
      return;
    }

    if (this.isEmpty()) {
      this.capacity = newCapacity;
      this.queue = (T[]) Array.newInstance(this.entityClass, this.capacity);
      this.head = 0;
      this.tail = 0;
      return;
    }

    T[] resizedQueue = (T[]) Array.newInstance(this.entityClass, newCapacity);

    int resizedQueueIndex = 0;
    int i = this.head;

    while (resizedQueueIndex < this.occupiedAmount) {
      resizedQueue[resizedQueueIndex] = this.queue[i];
      i = ++i % this.queue.length;
      resizedQueueIndex++;
    }

    this.capacity = newCapacity;
    this.queue = resizedQueue;
    this.head = 0;
    this.tail = 0;
  }

  @Override
  public String toString() {
    return "MyQueue{" +
            "queue=" + Arrays.toString(queue) +
            ", occupied=" + occupiedAmount +
            ", head=" + head +
            ", tail=" + tail +
            ", capacity=" + capacity +
            '}';
  }
}
