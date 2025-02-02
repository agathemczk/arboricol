package com.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E extends Comparable<E>> {
    private BinaryNode<E> root;

    public final void add(final E... values) {
        for (final E value : values) {
            this.add(value);
        }
    }

    public final void add(final E value) {
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

    public String prefix(){
        StringBuilder sb = new StringBuilder();
        if(this.root == null) return sb.toString();
        return this.root.prefix(sb).toString();
    }

    public String postfix() {
        StringBuilder sb = new StringBuilder();
        if (this.root == null) return sb.toString();
        return this.root.postfix(sb).toString();
    }

    public String infix() {
        StringBuilder sb = new StringBuilder();
        if (this.root == null) return sb.toString();
        return this.root.infix(sb).toString();
    }

    public final String toPrettyString() {
        if (this.root == null) return "";
        return this.root.toPrettyString();
    }

    /* public String lateral() {
        StringBuilder sb = new StringBuilder();
        if (this.root == null) return sb.toString();

        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            BinaryNode<E> node = queue.poll();
            sb.append(node.getValue()).append(" ");
            if (node.getLeft() != null) queue.add(node.getLeft());
            if (node.getRight() != null) queue.add(node.getRight());
        }
        return sb.toString();
    } */

    public final String toTreeString() {
        if (this.root == null) return "";
        return this.root.toTreeString();
    }

    public final int getHeight() {
        if(this.root == null) return 0;
        return this.root.getHeight();
    }

    public final boolean isAVL() {
        return BinaryNode.isAVL(this.root);
    }

    public void testRotate() {
        this.root.rotateRightLeftChild();
        this.root.rotateLeftLeftChild();
    }

}