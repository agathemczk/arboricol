package com.binarytree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> myBinaryTree = new BinaryTree<>();
        myBinaryTree.add(5, 3, 7, 2, 4, 6, 9, 8, 10);
        System.out.println(myBinaryTree.toTreeString());
        System.out.println("Hauteur de l'arbre : " + myBinaryTree.getHeight());
    }
}