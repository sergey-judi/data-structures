package com.judi;

public class TreeNode<T> {

  private TreeNode<T> left;
  private TreeNode<T> right;
  private final T data;

  public TreeNode(T data) {
    this.data = data;
  }

  public T getData() {
    return this.data;
  }

  public TreeNode<T> getLeft() {
    return this.left;
  }

  public TreeNode<T> getRight() {
    return this.right;
  }

  public void setLeft(TreeNode<T> newNode) {
    this.left = newNode;
  }

  public void setRight(TreeNode<T> newNode) {
    this.right = newNode;
  }

  @Override
  public String toString() {
    return "TreeNode{" +
            "left=" + ((left != null) ? left.getData() : null) +
            ", right=" + ((right != null) ? right.getData() : null) +
            ", data=" + data +
            '}';
  }
}
