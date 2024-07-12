package org.rbtree.tree;

public class RedBlackTree<T extends Comparable<T>> extends AutoBalancedTree<T> {

    public RedBlackTree()
    { super(); }

    // @param: x, The node which the leftRotate is to be performed on.
    // Updates the numLeft & numRight values affected by leftRotate.
    protected void leftRotateFixup(RedBlackNode<T> x){
        // Case 1: Only x, x.right and x.right.right always are not nil.
        if (isNil(x.left) && isNil(x.right.left)){
            x.numLeft = 0;
            x.numRight = 0;
            x.right.numLeft = 1;
        }

        // Case 2: x.right.left also exists in addition to Case 1
        else if (isNil(x.left) && !isNil(x.right.left)){
            x.numLeft = 0;
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 2 + x.right.left.numLeft +
                    x.right.left.numRight;
        }

        // Case 3: x.left also exists in addition to Case 1
        else if (!isNil(x.left) && isNil(x.right.left)){
            x.numRight = 0;
            x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;

        }
        // Case 4: x.left and x.right.left both exist in addition to Case 1
        else{
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
                    x.right.left.numLeft + x.right.left.numRight;
        }

    }// end leftRotateFixup(RedBlackNode x)


    // @param: y, the node around which the rightRotate is to be performed.
    // Updates the numLeft and numRight values affected by to rotate
    protected void rightRotateFixup(RedBlackNode<T> y){

        // Case 1: Only y, y.left and y.left.left exists.
        if (isNil(y.right) && isNil(y.left.right)){
            y.numRight = 0;
            y.numLeft = 0;
            y.left.numRight = 1;
        }

        // Case 2: y.left.right also exists in addition to Case 1
        else if (isNil(y.right) && !isNil(y.left.right)){
            y.numRight = 0;
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 2 + y.left.right.numRight +
                    y.left.right.numLeft;
        }

        // Case 3: y.right also exists in addition to Case 1
        else if (!isNil(y.right) && isNil(y.left.right)){
            y.numLeft = 0;
            y.left.numRight = 2 + y.right.numRight +y.right.numLeft;

        }

        // Case 4: y.right & y.left.right exist in addition to Case 1
        else{
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 3 + y.right.numRight +
                    y.right.numLeft +
                    y.left.right.numRight + y.left.right.numLeft;
        }

    }// end rightRotateFixup(RedBlackNode y)

    // @param: z, the node which was inserted and may have caused a violation
    // of the RedBlackTree properties
    // Fixes up the violation of the RedBlackTree properties that may have
    // been caused during insert(z)
    protected void insertFixup(RedBlackNode<T> z){

        RedBlackNode<T> y = nil;
        // While there is a violation of the RedBlackTree properties.
        while (z.parent.color == NodeColor.RED){

            // If z's parent is the left child of its parent.
            if (z.parent == z.parent.parent.left){

                // Initialize y to z 's cousin
                y = z.parent.parent.right;

                // Case 1: if y is red...recolor
                if (y.color == NodeColor.RED){
                    z.parent.color = NodeColor.BLACK;
                    y.color = NodeColor.BLACK;
                    z.parent.parent.color = NodeColor.RED;
                    z = z.parent.parent;
                }
                // Case 2: if y is black & z is a right child
                else if (z == z.parent.right){

                    // leftRotate around z's parent
                    z = z.parent;
                    leftRotate(z);
                }

                // Case 3: else y is black & z is a left child
                else{
                    // recolor and rotate round z's grandpa
                    z.parent.color = NodeColor.BLACK;
                    z.parent.parent.color = NodeColor.RED;
                    rightRotate(z.parent.parent);
                }
            }

            // If z's parent is the right child of its parent.
            else{

                // Initialize y to z's cousin
                y = z.parent.parent.left;

                // Case 1: if y is red...recolor
                if (y.color == NodeColor.RED){
                    z.parent.color = NodeColor.BLACK;
                    y.color = NodeColor.BLACK;
                    z.parent.parent.color = NodeColor.RED;
                    z = z.parent.parent;
                }

                // Case 2: if y is black and z is a left child
                else if (z == z.parent.left){
                    // rightRotate around z's parent
                    z = z.parent;
                    rightRotate(z);
                }
                // Case 3: if y  is black and z is a right child
                else{
                    // recolor and rotate around z's grandpa
                    z.parent.color = NodeColor.BLACK;
                    z.parent.parent.color = NodeColor.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        // Color root black at all times
        root.color = NodeColor.BLACK;

    }// end insertFixup(RedBlackNode z)

    // @param: y, the RedBlackNode which was actually deleted from the tree
    // @param: key, the value of the key that used to be in y
    protected void fixNodeData(RedBlackNode<T> x, RedBlackNode<T> y){

        // Initialize two variables which will help us traverse the tree
        RedBlackNode<T> current;
        RedBlackNode<T> track;


        // if x is nil, then we will start updating at y.parent
        // Set track to y, y.parent's child
        if (isNil(x)){
            current = y.parent;
            track = y;
        }

        // if x is not nil, then we start updating at x.parent
        // Set track to x, x.parent's child
        else{
            current = x.parent;
            track = x;
        }

        // while we haven't reached the root
        while (!isNil(current)){
            // if the node we deleted has a different key than
            // the current node
            if (y._value != current._value) {

                // if the node we deleted is greater than
                // current.key then decrement current.numRight
                if (y._value.compareTo(current._value) > 0)
                    current.numRight--;

                // if the node we deleted is less than
                // current.key then decrement current.numLeft
                if (y._value.compareTo(current._value) < 0)
                    current.numLeft--;
            }

            // if the node we deleted has the same key as the
            // current node we are checking
            else{
                // the cases where the current node has any nil
                // children and update appropriately
                if (isNil(current.left))
                    current.numLeft--;
                else if (isNil(current.right))
                    current.numRight--;

                    // the cases where current has two children, and
                    // we must determine whether track is its left
                    // or right child and update appropriately
                else if (track == current.right)
                    current.numRight--;
                else if (track == current.left)
                    current.numLeft--;
            }

            // update track and current
            track = current;
            current = current.parent;

        }

    }//end fixNodeData()

    // @param: x, the child of the deleted node from remove(RedBlackNode v)
    // Restores the Red Black properties that may have been violated during
    // the removal of a node in remove(RedBlackNode v)
    protected void removeFixup(RedBlackNode<T> x){

        RedBlackNode<T> w;

        // While we haven't fixed the tree completely...
        while (x != root && x.color == NodeColor.BLACK){

            // if x is its parent's left child
            if (x == x.parent.left){

                // set w = x's sibling
                w = x.parent.right;

                // Case 1, w's color is red.
                if (w.color == NodeColor.RED){
                    w.color = NodeColor.BLACK;
                    x.parent.color = NodeColor.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                // Case 2, both of w's children are black
                if (w.left.color == NodeColor.BLACK &&
                        w.right.color == NodeColor.BLACK){
                    w.color = NodeColor.RED;
                    x = x.parent;
                }
                // Case 3 / Case 4
                else{
                    // Case 3, w's right child is black
                    if (w.right.color == NodeColor.BLACK){
                        w.left.color = NodeColor.BLACK;
                        w.color = NodeColor.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // Case 4, w = black, w.right = red
                    w.color = x.parent.color;
                    x.parent.color = NodeColor.BLACK;
                    w.right.color = NodeColor.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            }
            // if x is its parent's right child
            else{

                // set w to x's sibling
                w = x.parent.left;

                // Case 1, w's color is red
                if (w.color == NodeColor.RED){
                    w.color = NodeColor.BLACK;
                    x.parent.color = NodeColor.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                // Case 2, both of w's children are black
                if (w.right.color == NodeColor.BLACK &&
                        w.left.color == NodeColor.BLACK){
                    w.color = NodeColor.RED;
                    x = x.parent;
                }

                // Case 3 / Case 4
                else{
                    // Case 3, w's left child is black
                    if (w.left.color == NodeColor.BLACK){
                        w.right.color = NodeColor.BLACK;
                        w.color = NodeColor.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }

                    // Case 4, w = black, and w.left = red
                    w.color = x.parent.color;
                    x.parent.color = NodeColor.BLACK;
                    w.left.color = NodeColor.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }// end while

        // set x to black to ensure there is no violation of
        // RedBlack tree Properties
        x.color = NodeColor.BLACK;
    }// end removeFixup(RedBlackNode x)

    @Override
    protected void appendNodeToString(RedBlackNode<T> node, StringBuilder builder) {
        builder.append(node._value).append(node.color == NodeColor.RED ? "[R]" : "[B]");
    }

}// end class RedBlackTree
