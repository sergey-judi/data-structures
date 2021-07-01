package com.judi;

public class Main {
  public static void main(String[] args) {
    testQueue();
  }

  private static void testQueue() {
    Queue<Integer> queue = new Queue<>(Integer.class, 4);
    System.out.println(queue);

    queue.push(1);
    queue.pop();
    queue.push(2);
    queue.pop();
    queue.push(3);
    queue.push(4);
    queue.push(1);
    queue.push(2);

    System.out.println(queue);

    queue.pop();
    queue.pop();

    System.out.println(queue);
    queue.resize();
    queue.resize();
    System.out.println(queue);
  }
}
