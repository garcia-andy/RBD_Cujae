package org.rbtree.tree;

import java.util.*;

public abstract class AutoBalancedTree<T extends Comparable<T>> extends IterableTree<T> {
    // Root initialized to nil. (nil = Null Node)
    protected final RedBlackNode<T> nil = new RedBlackNode<>();
    protected RedBlackNode<T> root = nil;

    public AutoBalancedTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    protected RedBlackNode<T> getRoot()
    { return root; }

    /* IMPLEMENTATION REQUIRE */
    protected abstract void leftRotateFixup(RedBlackNode<T> x);
    protected abstract void rightRotateFixup(RedBlackNode<T> y);
    protected abstract void insertFixup(RedBlackNode<T> z);
    protected abstract void fixNodeData(RedBlackNode<T> x, RedBlackNode<T> y);
    protected abstract void removeFixup(RedBlackNode<T> x);


    public void insert(T value) {
        insert(new RedBlackNode<>(value));
    }

    // @param: z, the node to be inserted into the Tree rooted at root
    // Inserts z into the appropriate position in the RedBlackTree while
    // updating numLeft and numRight values.
    private void insert(RedBlackNode<T> z) {

        // Create a reference to root & initialize a node to nil
        RedBlackNode<T> y = nil;
        RedBlackNode<T> x = root;

        // While we haven't reached an end of the tree keep
        // trying to figure out where z should go
        while (!isNil(x)){
            y = x;

            // if z.key is < than the current key, go left
            if (z._value.compareTo(x._value) < 0){

                // Update x.numLeft as z is < than x
                x.numLeft++;
                x = x.left;
            }

            // else z.key >= x.key so go right.
            else{

                // Update x.numGreater as z is => x
                x.numRight++;
                x = x.right;
            }
        }
        // y will hold z's parent
        z.parent = y;

        // Depending on the value of y.key, put z as the left or
        // right child of y
        if (isNil(y))
            root = z;
        else if (z._value.compareTo(y._value) < 0)
            y.left = z;
        else
            y.right = z;

        // Initialize z's children to nil and z's color to red
        z.left = nil;
        z.right = nil;
        z.color = NodeColor.RED;

        // Call insertFixup(z)
        insertFixup(z);

    }// end insert(RedBlackNode z)

    // @return: the node with the smallest key rooted at node
    public RedBlackNode<T> treeMinimum(){return treeMinimum(root);}

    // @param: node, a RedBlackNode
    // @return: node, the node with the smallest key rooted at node
    public RedBlackNode<T> treeMinimum(RedBlackNode<T> node){
        // while there is a smaller key, keep going left
        while (!isNil(node.left))
            node = node.left;
        return node;
    }// end treeMinimum(RedBlackNode node)

    // @param: x, a RedBlackNode whose successor we must find
    // @return: return's the node the with the next largest key
    // from x.key
    public RedBlackNode<T> treeSuccessor(RedBlackNode<T> x){

        // if x.left is not nil, call treeMinimum(x.right) and
        // return its value
        if (!isNil(x.left) )
            return treeMinimum(x.right);

        RedBlackNode<T> y = x.parent;

        // while x is its parent's right child...
        while (!isNil(y) && x == y.right){
            // Keep moving up in the tree
            x = y;
            y = y.parent;
        }
        // Return successor
        return y;
    }// end treeMinimum(RedBlackNode x)


    // @param: z, the RedBlackNode which is to be removed from the tree
    // Remove's z from the RedBlackTree rooted at root
    public void remove(T v){

        RedBlackNode<T> z = search(v);
        if( z == null ) return;

        // Declare variables
        RedBlackNode<T> x = nil;
        RedBlackNode<T> y = nil;

        // if either one of z's children is nil, then we must remove z
        if (isNil(z.left) || isNil(z.right))
            y = z;

            // else we must remove the successor of z
        else y = treeSuccessor(z);

        // Let x be the left or right child of y (y can only have one child)
        if (!isNil(y.left))
            x = y.left;
        else
            x = y.right;

        // link x's parent to y's parent
        x.parent = y.parent;

        // If y's parent is nil, then x is the root
        if (isNil(y.parent))
            root = x;

            // else if y is a left child, set x to be y's left sibling
        else if (!isNil(y.parent.left) && y.parent.left == y)
            y.parent.left = x;

            // else if y is a right child, set x to be y's right sibling
        else if (!isNil(y.parent.right) && y.parent.right == y)
            y.parent.right = x;

        // if y != z, transfer y's satellite data into z.
        if (y != z){
            z._value = y._value;
        }

        // Update the numLeft and numRight numbers which might need
        // updating due to the deletion of z.key.
        fixNodeData(x,y);

        // If y's color is black, it is a violation of the
        // RedBlackTree properties so call removeFixup()
        if (y.color == NodeColor.BLACK)
            removeFixup(x);
    }// end remove(RedBlackNode z)

    // @param: x, The node which the lefRotate is to be performed on.
    // Performs a leftRotate around x.
    protected void leftRotate(RedBlackNode<T> x){

        // Call leftRotateFixup() which updates the numLeft
        // and numRight values.
        leftRotateFixup(x);

        // Perform the left rotate as described in the algorithm
        // in the course text.
        RedBlackNode<T> y = x.right;
        x.right = y.left;

        // Check for existence of y.left and make pointer changes
        if (!isNil(y.left))
            y.left.parent = x;
        y.parent = x.parent;

        // x's parent is null
        if (isNil(x.parent))
            root = y;
            // x is the left child of it's parent
        else if (x.parent.left == x)
            x.parent.left = y;
            // x is the right child of its parent.
        else
            x.parent.right = y;

        // Finish of the leftRotate
        y.left = x;
        x.parent = y;
    }// end leftRotate(RedBlackNode x)

    // @param: x, The node which the rightRotate is to be performed on.
    // Updates the numLeft and numRight values affected by the Rotate.
    protected void rightRotate(RedBlackNode<T> y){

        // Call rightRotateFixup to adjust numRight and numLeft values
        rightRotateFixup(y);

        // Perform the rotate as described in the course text.
        RedBlackNode<T> x = y.left;
        y.left = x.right;

        // Check for existence of x.right
        if (!isNil(x.right))
            x.right.parent = y;
        x.parent = y.parent;

        // y.parent is nil
        if (isNil(y.parent))
            root = x;

            // y is a right child of its parent.
        else if (y.parent.right == y)
            y.parent.right = x;

            // y is a left child of its parent.
        else
            y.parent.left = x;
        x.right = y;

        y.parent = x;

    }// end rightRotate(RedBlackNode y)


    // @param: key, the key whose node we want to search for
    // @return: returns a node with the key, key, if not found, returns null
    // Searches for a node with key k and returns the first such node, if no
    // such node is found returns null
    public RedBlackNode<T> search(T key){

        // Initialize a pointer to the root to traverse the tree
        RedBlackNode<T> current = root;

        // While we haven't reached the end of the tree
        while (!isNil(current)){

            // If we have found a node with a key equal to key
            if (current._value.equals(key))

                // return that node and exit search(int)
                return current;

                // go left or right based on value of current and key
            else if (current._value.compareTo(key) < 0)
                current = current.right;

                // go left or right based on value of current and key
            else
                current = current.left;
        }

        // we have not found a node whose key is "key"
        return null;


    }// end search(int key)

    public boolean contains(T value) {
        RedBlackNode<T> node = root;
        boolean ret = false;

        if (node == null || value == null) ret = false;
        else while (!isNil(node)) {

            // Compare current value to the value in the node.
            int cmp = value.compareTo(node._value);

            // Dig into left subtree.
            if (cmp < 0) node = node.left;

                // Dig into right subtree.
            else if (cmp > 0) node = node.right;

                // Found value in tree.
            else {
                ret = true;
                break;
            }
        }
        return ret;
    }

    // @param: key, any Comparable object
    // @return: return's the number of elements greater than key
    public int numGreater(T key){
        // Call findNumGreater(root, key) which will return the number
        // of nodes whose key is greater than key
        return findNumGreater(root,key);
    }// end numGreater(int key)

    // @param: key, any Comparable object
    // @return: return's teh number of elements smaller than key
    public int numSmaller(T key){
        // Call findNumSmaller(root,key) which will return
        // the number of nodes whose key is greater than key
        return findNumSmaller(root,key);
    }// end numSmaller(int key)

    // @param: node, the root of the tree, the key who we must
    // compare other node key's to.
    // @return: the number of nodes greater than key.
    protected int findNumGreater(RedBlackNode<T> node, T key){

        // Base Case: if node is nil, return 0
        if (isNil(node))
            return 0;
            // If key is less than node.key, all elements right of node are
            // greater than key, add this to our total and look to the left
        else if (key.compareTo(node._value) < 0)
            return 1+ node.numRight + findNumGreater(node.left,key);

            // If key is greater than node.key, then look to the right as
            // all elements to the left of node are smaller than key
        else
            return findNumGreater(node.right,key);

    }// end findNumGreater(RedBlackNode, int key)

    // @param: node, the root of the tree, the key who we must compare other
    // node key's to.
    // @return: the number of nodes smaller than key.
    protected int findNumSmaller(RedBlackNode<T> node, T key){

        // Base Case: if node is nil, return 0
        if (isNil(node)) return 0;

            // If key is less than node.key, look to the left as all
            // elements on the right of node are greater than key
        else if (key.compareTo(node._value) <= 0)
            return findNumSmaller(node.left,key);

            // If key is larger than node.key, all elements to the left of
            // node are smaller than key, add this to our total and look
            // to the right.
        else
            return 1+ node.numLeft + findNumSmaller(node.right,key);

    }// end findNumSmaller(RedBlackNode nod, int key)

    // @param: node, the RedBlackNode we must check to see whether it's nil
    // @return: return's true of node is nil and false otherwise
    protected boolean isNil(RedBlackNode<T> node){

        // return appropriate value
        return node == nil;

    }// end isNil(RedBlackNode node)

    // @return: return's the size of the tree
    // Return's the # of nodes including the root which the RedBlackTree
    // rooted at root has.
    public int size(){

        // Return the number of nodes to the root's left + the number of
        // nodes on the root's right + the root itself.
        return root.numLeft + root.numRight + 1;
    }// end size()

    /* UTILITIES FOR PRINT THE TREE */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\t\t");
        if( !isNil(root) ) {
            appendNodeToString(root, builder);
            appendNodeToStringRecursive(root, builder);
        }
        return builder.toString();
    }

    private void appendNodeToStringRecursive(RedBlackNode<T> node, StringBuilder builder) {
        if (!isNil(node.left)) {
            builder.append("\n\tL{");
            appendNodeToString(node.left, builder);
            builder.append('}');
        }
        if (!isNil(node.right)) {
            builder.append("\t\t\tR{");
            appendNodeToString(node.right, builder);
            builder.append("}");
        }
        if( !isNil(node) ){
            appendNodeToStringRecursive(node.left, builder);
            appendNodeToStringRecursive(node.right, builder);
        }
    }

    protected void appendNodeToString(RedBlackNode<T> node, StringBuilder builder) {
        if( node._value != null )
            builder.append(node._value);
    }
}
