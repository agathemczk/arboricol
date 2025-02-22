package com.binarytree;
import lombok.Getter;

class BinaryNode<E extends Comparable<E>> {
    private static final String GAP_START_LEFT = "╰";
    private static final String GAP_START_RIGHT = "╭";
    private static final String GAP = " ";
    private static final String GAP_END = "─";
    @Getter
    private E value;
    @Getter
    private int balanceFactor;
    private BinaryNode<E> left;
    private BinaryNode<E> right;

    BinaryNode(final E value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.balanceFactor = 0;
    }

    void add(final E value) {
        switch (value.compareTo(this.value)) {
            case 1 -> this.right = this.add(this.right, value);
            case -1 -> this.left = this.add(this.left, value);
        }
    }

    private BinaryNode<E> add(final BinaryNode<E> child, final E value) {
        if (child == null) return new BinaryNode<>(value);
        child.add(value);
        this.calculateBalanceFactor();
        return child;
    }

    public StringBuilder prefix(final StringBuilder sb) {
        sb.append(this.value).append(" ");
        if (this.left != null) this.left.prefix(sb);
        if (this.right != null) this.right.prefix(sb);
        return sb;
    }

    public StringBuilder postfix(final StringBuilder sb) {
        if (this.left != null) this.left.prefix(sb);
        if (this.right != null) this.right.prefix(sb);
        sb.append(this.value).append(" ");
        return sb;
    }

    public StringBuilder infix(final StringBuilder sb) {
        sb.append(this.value).append(" ");
        if (this.right != null) this.right.prefix(sb);
        if (this.left != null) this.left.prefix(sb);
        return sb;
    }

    private static <E extends Comparable<E>> int getHeight(final BinaryNode<E> child) {
        if (child== null) return 0;
        return child.getHeight();
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "value=" + this.getValue() +
                ", left=" + this.left +
                ", right=" + this.right +
                '}';
    }

    String toPrettyString() {
        return "[" + this.value +
                (((this.left == null) && (this.right == null)) ? "" :
                        "(" + ((this.left == null) ? "-" : this.left.toPrettyString()) +
                                ", " + ((this.right == null) ? "-" : this.right.toPrettyString()) + ")") + "]";
    }

    String toTreeString() {
        return this.toTreeString(0, true);
    }

    String toTreeString(final int gap, final boolean isLeft) {       // infixe
        StringBuilder stringBuilder = new StringBuilder();

        if (this.right != null) stringBuilder.append(this.right.toTreeString(gap + 1, false));
        if (gap >= 1) {
            stringBuilder.append(BinaryNode.GAP.repeat((gap - 1) * 2));
            stringBuilder.append(isLeft ? BinaryNode.GAP_START_LEFT : BinaryNode.GAP_START_RIGHT);
            stringBuilder.append(BinaryNode.GAP_END);
        }
        stringBuilder.append(this.value);
        stringBuilder.append("(").append(this.balanceFactor).append(")");
        stringBuilder.append("\n");
        if (this.left != null) stringBuilder.append(this.left.toTreeString(gap + 1, true));

        return stringBuilder.toString();
    }

    int getHeight() {
        return 1 + Math.max(BinaryNode.getHeight(this.left),
                BinaryNode.getHeight(this.right));
    }

    private void calculateBalanceFactor(){
        this.balanceFactor = BinaryNode.getHeight(this.right) - BinaryNode.getHeight(this.left);
    }

    public static <E extends Comparable<E>> boolean isAVL(final BinaryNode<E> node) {
        if (node == null) return true;
        return node.isAVL();
    }

    private boolean isAVL() {
        if(Math.abs(this.balanceFactor) > 1) return false;
        return BinaryNode.isAVL(this.left) && BinaryNode.isAVL(this.right);
    }

    private static <E extends Comparable<E>> void setLeft(final BinaryNode<E> node, final BinaryNode<E> child) {
        if (node == null) throw new IllegalArgumentException("node == null");
        node.left = child;
    }

    private static <E extends Comparable<E>> void setRight(final BinaryNode<E> node, final BinaryNode<E> child) {
        if (node == null) throw new IllegalArgumentException("node == null");
        node.right = child;
    }

    private static <E extends Comparable<E>> BinaryNode<E> getRight(final BinaryNode<E> node) {
        return (node == null) ? null : node.right;
    }

    private static <E extends Comparable<E>> BinaryNode<E> getLeft(final BinaryNode<E> node) {
        return (node == null) ? null : node.left;
    }

    void rotateRightLeftChild() {
        BinaryNode<E> pivot = this.left;
        BinaryNode<E> child = BinaryNode.getLeft(pivot);
        BinaryNode<E> grandChild = BinaryNode.getRight(child);
        this.left = child;
        BinaryNode.setRight(child,pivot);
        BinaryNode.setLeft(pivot,grandChild);
    }

    void rotateLeftLeftChild() {
        BinaryNode<E> pivot = this.left;
        BinaryNode<E> child = BinaryNode.getRight(pivot);
        BinaryNode<E> grandChild = BinaryNode.getLeft(child);
        this.left = child;
        BinaryNode.setLeft(child,pivot);
        BinaryNode.setRight(pivot,grandChild);
    }

    /*
    void rotateRightRightChild() {
        BinaryNode<E> pivot = this.right;
        BinaryNode<E> child = BinaryNode.getLeft(pivot);
        BinaryNode<E> grandChild = BinaryNode.getRight(child);
        this.right = child;
        BinaryNode.setRight(child,pivot);
        BinaryNode.setLeft(pivot,grandChild);
    }

    void rotateLeftRightChild() {
        BinaryNode<E> pivot = this.right;
        BinaryNode<E> child = BinaryNode.getLeft(pivot);
        BinaryNode<E> grandChild = BinaryNode.getRight(child);
        this.right = child;
        BinaryNode.setLeft(child,pivot);
        BinaryNode.setRight(pivot,grandChild);
    }
    */

}