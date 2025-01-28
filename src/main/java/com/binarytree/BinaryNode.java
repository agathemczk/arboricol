package com.binarytree;
import lombok.Getter;

class BinaryNode<E extends Comparable<E>> {
    private static final String GAP_START_LEFT = "╭";
    private static final String GAP_START_RIGHT = "╰";
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

        if (this.left != null) stringBuilder.append(this.left.toTreeString(gap + 1, true));
        if (gap >= 1) {
            stringBuilder.append(BinaryNode.GAP.repeat((gap - 1) * 2));
            stringBuilder.append(isLeft ? BinaryNode.GAP_START_LEFT : BinaryNode.GAP_START_RIGHT);
            stringBuilder.append(BinaryNode.GAP_END);
        }
        stringBuilder.append(this.value);
        stringBuilder.append("(").append(this.balanceFactor).append(")");
        stringBuilder.append("\n");
        if (this.right != null) stringBuilder.append(this.right.toTreeString(gap + 1, false));

        return stringBuilder.toString();
    }

    int getHeight() {
        return 1 + Math.max(BinaryNode.getHeight(this.left),
                BinaryNode.getHeight(this.right));
    }

    private void calculateBalanceFactor(){
        this.balanceFactor = BinaryNode.getHeight(this.right) - BinaryNode.getHeight(this.left);
    }

}