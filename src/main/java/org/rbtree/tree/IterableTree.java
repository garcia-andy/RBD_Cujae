package org.rbtree.tree;

import java.util.LinkedList;
import java.util.List;

public abstract class IterableTree<T extends  Comparable<T>> {

    protected abstract boolean isNil(RedBlackNode<T> n);
    protected abstract RedBlackNode<T> getRoot();

    /* IN ORDER */

    private List<T> inOrderKeysHelper(RedBlackNode<T> currentNode)
    { return inOrderNodeHelper(currentNode).stream().map(n -> n._value).toList(); }

    /**
     * Helper method for in order traversal.
     * Returns list of nodes
     *
     * @param currentNode node to begin traversal at
     * @return List of Nodes in order
     */
    private List<RedBlackNode<T>> inOrderNodeHelper(RedBlackNode<T> currentNode) {
        LinkedList<RedBlackNode<T>> list = new LinkedList<>();
        // Base case: null nodes not added to list
        if (isNil(currentNode)) {
            return list;
        }
        // Adds keys of left subtree, root, and then keys of right subtree:
        list.addAll(inOrderNodeHelper(currentNode.left));
        list.add(currentNode);
        list.addAll(inOrderNodeHelper(currentNode.right));
        return list;
    }

    /**
     * Returns in order list of keys.
     */
    public List<T> getInOrderKeys() {
        return inOrderKeysHelper(getRoot());
    }

    /**
     * Returns in order list of Nodes.
     */
    public List<RedBlackNode<T>> getInOrder() {
        return inOrderNodeHelper(getRoot());
    }

    /* PRE ORDER */

    /**
     * Helper method for preorder.
     *
     * @param currentNode node to begin traversal at
     * @return list of keys according to pre-order traversal order
     */
    private List<RedBlackNode<T>> preOrderNodeHelper(RedBlackNode<T> currentNode) {
        LinkedList<RedBlackNode<T>> list = new LinkedList<>();
        // Base case: null nodes not added to list
        if (isNil(currentNode)) {
            return list;
        }
        // Adds root, keys of left subtree, and then keys of right subtree:
        list.add(currentNode);
        list.addAll(preOrderNodeHelper(currentNode.left));
        list.addAll(preOrderNodeHelper(currentNode.right));
        return list;
    }

    /**
     * Helper method for preorder.
     *
     * @param currentNode node to begin traversal at
     * @return list of keys according to pre-order traversal order
     */
    private List<T> preOrderKeysHelper(RedBlackNode<T> currentNode)
    { return preOrderNodeHelper(currentNode).stream().map(n -> n._value).toList(); }

    /**
     * Returns list of keys according to preorder.
     */
    public List<T> getPreOrderKeys() {
        return preOrderKeysHelper(getRoot());
    }

    /**
     * Returns list of Nodes according to preorder.
     */
    public List<RedBlackNode<T>> getPreOrder() {
        return preOrderNodeHelper(getRoot());
    }

    /* POST ORDER */

    /**
     * Helper method for returning postorder.
     *
     * @param currentNode node traversal begins at
     * @return list of nodes in postorder
     */
    private List<RedBlackNode<T>> postOrderNodesHelper(RedBlackNode<T> currentNode) {
        LinkedList<RedBlackNode<T>> list = new LinkedList<>();
        // Base case: null nodes not added to list
        if (isNil(currentNode)) {
            return list;
        }
        // Adds keys of left subtree, keys of right subtree, and then the root:
        list.addAll(postOrderNodesHelper(currentNode.left));
        list.addAll(postOrderNodesHelper(currentNode.right));
        list.add(currentNode);
        return list;
    }

    /**
     * Helper method for returning postorder.
     *
     * @param currentNode node traversal begins at
     * @return list of keys in postorder
     */
    private List<T> postOrderKeysHelper(RedBlackNode<T> currentNode)
    { return postOrderNodesHelper(currentNode).stream().map(n -> n._value).toList(); }

    /**
     * Returns list of keys in postorder.
     */
    public List<T> getPostOrderKeys() {
        return postOrderKeysHelper(getRoot());
    }

    /**
     * Returns list of nodes in postorder.
     */
    public List<RedBlackNode<T>> getPostOrder() {
        return postOrderNodesHelper(getRoot());
    }

    /*
    * UTILITIES
    * */

    /**
     * Determines length of String used to represent each node in tree. Equal the maximum length of
     * String representation of keys, plus 2 for the characters which will denote Red or Black.
     *
     * @return length of String used to represent each node in tree
     */
    protected int charsPerKey() {
        final int NUM_OF_SPECIAL_CHARS = 2; // Number of chars used to indicate Red or Black
        List<RedBlackNode<T>> inOrderList = getInOrder(); // List of nodes in order
        // Determines maximum length of keys
        int maxKeyLength = 0;
        for (RedBlackNode<T> node : inOrderList) {
            if (node._value.toString().length() > maxKeyLength) {
                maxKeyLength = node._value.toString().length();
            }
        }
        return maxKeyLength + NUM_OF_SPECIAL_CHARS; // Returns max length plus 2
    }

}
