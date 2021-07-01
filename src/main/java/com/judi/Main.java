package com.judi;

import java.util.Comparator;

public class Main {
  public static void main(String[] args) {
    testBinaryTree();
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

  private static void testBinaryTree() {
    BinaryTree<Float> tree = new BinaryTree<>(Comparator.comparing(Float::floatValue)) {{
      add(-1.0f);
      add(-2.0f);
      add(2.0f);
      add(1.0f);
      add(3.0f);
      add(1.5f);
      add(0.8f);
    }};

    // Float[] nodesData = {-1.0f,  -2.0f,  2.0f,  1.0f,  3.0f,  1.5f,  1.25f};
    // BinaryTree<Float> tree = BinaryTree.of(Comparator.comparing(Float::floatValue), nodesData);

    // Integer[] nodesData = {-1, -2, 7, 5, 4, 6, 8};
    // BinaryTree<Integer> tree = BinaryTree.of(Comparator.comparing(Integer::intValue), nodesData);

    System.out.println(tree);
    tree.inorder();
    System.out.println("Balance factor: " + tree.calculateBalanceFactor());
  }
}
