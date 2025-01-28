package com.binarytree;

public class BinaryTree<E extends Comparable<E>> {
    private BinaryNode<E> root;

    public void add(final E... values) {
        for (final E value : values) {
            this.add(value);
        }
    }

    public void add(final E value) {
        if (this.root == null) {
            this.root = new BinaryNode<>(value);
        } else {
            this.root.add(value);
        }
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + this.root +
                '}';
    }

    public String toPrettyString() {
        if (this.root == null) return "";
        return this.root.toPrettyString();
    }

    public String toTreeString() {
        if (this.root == null) return "";
        return this.root.toTreeString();
    }
}