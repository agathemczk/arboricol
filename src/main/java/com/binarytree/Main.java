package com.binarytree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> myBinaryTree = new BinaryTree<>();
        myBinaryTree.add(5, 3, 7, 2, 4, 6, 8, 9, 10);
        System.out.println(myBinaryTree.toTreeString());
        System.out.println("Hauteur de l'arbre : " + myBinaryTree.getHeight());
        System.out.println("Arbre AVL ? " + myBinaryTree.isAVL());
    }
}