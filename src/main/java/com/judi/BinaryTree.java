package com.judi;

import java.util.Comparator;

public class BinaryTree<T> {

  private final int ARE_EQUAL = 0;
  private final Comparator<T> comparator;
  private TreeNode<T> head;
  private int nodesAmount;

  public BinaryTree(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  @SafeVarargs
  public static <E> BinaryTree<E> of(Comparator<E> comparator, E... nodesData) {
    var constructedTree = new BinaryTree<>(comparator);
    for (E nodeData : nodesData) {
      constructedTree.add(nodeData);
    }
    return constructedTree;
  }

  public int calculateBalanceFactor() {
    return calculateBalanceFactor(this.head);
  }

  private int calculateBalanceFactor(TreeNode<T> node) {
    return height(node.getRight()) - height(node.getLeft());
  }

  public void rotateRight(T data) {
    rotateRight(findNode(data));
  }

  private void rotateRight(TreeNode<T> node) {
    if (node == null) {
      return;
    }

    TreeNode<T> parentNode = findNodeParent(node);

    TreeNode<T> leftNode = node.getLeft();
    node.setLeft(leftNode.getRight());
    leftNode.setRight(node);

    if (node == this.head) {
      this.head = leftNode;
      return;
    }

    if (comparator.compare(node.getData(), parentNode.getData()) >= ARE_EQUAL) {
      parentNode.setRight(leftNode);
    } else {
      parentNode.setLeft(leftNode);
    }
  }

  public void rotateLeft(T data) {
    rotateLeft(findNode(data));
  }

  private void rotateLeft(TreeNode<T> node) {
    if (node == null) {
      return;
    }

    TreeNode<T> parentNode = findNodeParent(node);

    TreeNode<T> rightNode = node.getRight();
    node.setRight(rightNode.getLeft());
    rightNode.setLeft(node);

    if (node == this.head) {
      this.head = rightNode;
      return;
    }

    if (comparator.compare(node.getData(), parentNode.getData()) >= ARE_EQUAL) {
      parentNode.setRight(rightNode);
    } else {
      parentNode.setLeft(rightNode);
    }
  }

  private void balance(TreeNode<T> node) {
    if (this.calculateBalanceFactor(node) == 2) {
      if (this.calculateBalanceFactor(node.getRight()) < 0) {
        rotateRight(node.getRight());
      }
      rotateLeft(node);
    }

    if (this.calculateBalanceFactor(node) == -2) {
      if (this.calculateBalanceFactor(node.getLeft()) > 0) {
        rotateLeft(node.getLeft());
      }
      rotateRight(node);
    }
  }

  public void add(T data) {
    TreeNode<T> newNode = new TreeNode<>(data);

    if (this.head == null) {
      this.head = newNode;
      this.nodesAmount++;
      return;
    }

    TreeNode<T> tempNode = this.head;

    while (tempNode != null) {
      if (comparator.compare(newNode.getData(), tempNode.getData()) >= ARE_EQUAL) {

        if (tempNode.getRight() != null) {
          tempNode = tempNode.getRight();
        } else {
          tempNode.setRight(newNode);
          this.nodesAmount++;
          this.balance(this.head);
          return;
        }

      } else {

        if (tempNode.getLeft() != null) {
          tempNode = tempNode.getLeft();
        } else {
          tempNode.setLeft(newNode);
          this.nodesAmount++;
          this.balance(this.head);
          return;
        }

      }
    }

    this.balance(this.head);
  }

  public void remove(T data) {
    TreeNode<T> nodeToRemove = findNode(data);
    if (nodeToRemove != null) {
      if (nodeToRemove == this.head) {
        if (this.nodesAmount == 1) {
          this.head = null;
          this.nodesAmount--;
          return;
        }

        TreeNode<T> minNode = min(this.head.getRight());
        if (minNode == null) {
          minNode = this.head.getLeft();
        }
        TreeNode<T> minNodeParent = findNodeParent(minNode);

        if (minNodeParent.getLeft() != null
                && comparator.compare(minNode.getData(), minNodeParent.getLeft().getData()) == 0) {
          minNodeParent.setLeft(null);
        } else if (minNodeParent.getRight() != null
                && comparator.compare(minNode.getData(), minNodeParent.getRight().getData()) == 0) {
          minNodeParent.setRight(null);
        }

        minNode.setLeft(this.head.getLeft());
        minNode.setRight(this.head.getRight());

        this.head = minNode;
      } else {
        recursiveRemove(this.head, nodeToRemove);
      }
      this.nodesAmount--;
    }
  }

  private TreeNode<T> recursiveRemove(TreeNode<T> subRoot, TreeNode<T> node) {
    if (subRoot == null) {
      return null;
    }

    if (comparator.compare(node.getData(), subRoot.getData()) < ARE_EQUAL) {
      subRoot.setLeft(recursiveRemove(subRoot.getLeft(), node));
    } else if (comparator.compare(node.getData(), subRoot.getData()) > ARE_EQUAL) {
      subRoot.setRight(recursiveRemove(subRoot.getRight(), node));
    } else if ((subRoot.getLeft() != null) && (subRoot.getRight() != null)){
      TreeNode<T> minNode = min(subRoot.getRight());
      subRoot.setRight(recursiveRemove(subRoot.getRight(), minNode));
    } else {
      if (subRoot.getLeft() != null) {
        subRoot = subRoot.getLeft();
      } else if (subRoot.getRight() != null) {
        subRoot = subRoot.getRight();
      } else {
        subRoot = null;
      }
    }

    return subRoot;
  }

  private TreeNode<T> min(TreeNode<T> node) {
    if (node == null || node.getLeft() == null) {
      return node;
    }
    return min(node.getLeft());
  }

  private TreeNode<T> findNodeParent(TreeNode<T> node) {

    if (node == null || comparator.compare(node.getData(), this.head.getData()) == ARE_EQUAL) {
      return null;
    }

    TreeNode<T> parent = this.head;
    TreeNode<T> tempNode = this.head;
    TreeNode<T> child;

    while (tempNode != null) {

      if (comparator.compare(node.getData(), tempNode.getData()) > ARE_EQUAL) {

        child = tempNode.getRight();
        if (comparator.compare(child.getData(), node.getData()) != ARE_EQUAL) {
          parent = parent.getRight();
        } else {
          return parent;
        }
        tempNode = tempNode.getRight();

      } else if (comparator.compare(node.getData(), tempNode.getData()) < ARE_EQUAL) {

        child = tempNode.getLeft();
        if (comparator.compare(child.getData(), node.getData()) != ARE_EQUAL) {
          parent = parent.getLeft();
        } else {
          return parent;
        }
        tempNode = tempNode.getLeft();

      }
    }
    return null;
  }

  public TreeNode<T> findNode(T data) {
    TreeNode<T> node = this.head;

    while (node != null) {

      if (comparator.compare(data, node.getData()) > ARE_EQUAL) {
        node = node.getRight();
      } else if (comparator.compare(data, node.getData()) < ARE_EQUAL) {
        node = node.getLeft();
      } else {
        return node;
      }
    }
    return null;
  }

  public int count() {
    return this.nodesAmount;
  }

  public int height() {
    return height(this.head) - 1;
  }

  private int height(TreeNode<T> node) {
    if (node == null) {
      return 0;
    }

    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
  }

  public void inorder() {
    this.inorder(this.head);
  }

  private void inorder(TreeNode<T> node) {
    if (node != null) {
      this.inorder(node.getLeft());
      System.out.println(node.getData());
      this.inorder(node.getRight());
    }
  }

  @Override
  public String toString() {
    return "BinaryTree{" +
            "head=" +  ((this.head != null) ? this.head.toString() : null) +
            ", nodesAmount=" + nodesAmount +
            ", height=" + this.height() +
            '}';
  }
}
