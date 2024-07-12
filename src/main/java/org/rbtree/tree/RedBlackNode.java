package org.rbtree.tree;

public class RedBlackNode<T extends Comparable<T>> {
    public T _value;

    RedBlackNode<T> parent;

    // Children
    RedBlackNode<T> left;
    RedBlackNode<T> right;

    // the number of elements to the left
    public int numLeft = 0;
    // the number of elements to the right
    public int numRight = 0;
    // the color of a node
    public NodeColor color;

    RedBlackNode(){
        color = NodeColor.BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    // Constructor which sets key to the argument.
    RedBlackNode(T value){
        this();
        _value = value;
    }
}
