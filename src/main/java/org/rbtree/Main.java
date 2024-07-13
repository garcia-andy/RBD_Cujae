package org.rbtree;

import org.rbtree.tree.RedBlackTree;

public class Main {
    public static void main(String[] args) {
        var tree = new RedBlackTree<Integer>();

        tree.insert(5);

        System.out.println("Single Tree: " + tree.toString());
        tree.insert(20);
        tree.insert(4);
        tree.insert(6);

        tree.insert(2);
        tree.insert(8);

        tree.insert(3);
        tree.insert(7);

        tree.insert(1);
        tree.insert(9);

        System.out.println("Arbol:\n" + tree.toString());

        System.out.println("Verify Insertion:\n");
        for(Integer i: tree.getInOrderKeys())
            System.out.println("." + i);
        for(int i=0; i < 100; i++){
            var n = tree.search(i);
            System.out.println(
                    "Exist " + i + "?: " + (n != null) + " " + ((n != null) ? n.color : "NIL")
            );
        }

        System.out.println("\nRemoving Data");
        tree.remove(2);
        tree.remove(6);
        tree.remove(0);

        System.out.println("Verify Remove:\n");
        for(int i=0; i < 100; i++){
            var n = tree.search(i);
            System.out.println(
                    "Exist " + i + "?: " + (n != null) + " " + ((n != null) ? n.color : "NIL")
            );
        }


        for(Integer i: tree.getInOrderKeys())
            System.out.println("." + i);
    }
}