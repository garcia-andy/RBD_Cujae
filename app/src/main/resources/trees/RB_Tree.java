package trees;

import enums.COLORNODE;
import nodes.RB_Node;

public class RB_Tree<T extends Comparable<T>>{
    public final COLORNODE BLACK = COLORNODE.BLACK;
    public final COLORNODE RED = COLORNODE.RED;

    private RB_Node<T> root;
    private RB_Node<T> TNull;

    public RB_Tree(T value){
        this.TNull = new RB_Node<T>(value);
        this.TNull.setColor(BLACK);
        this.root = TNull;
    }

    private void rotate_left(RB_Node<T> x){
        RB_Node<T> y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft() != TNull) y.getLeft().setFather(x);
        
        y.setFather(x.getFather());
        if(x.getFather() == null) {this.root = y;}
        else if(x.getFather().getLeft() == x){x.getFather().setLeft(y);}
        else {x.getFather().setRight(y);}
        y.setLeft(x);
        x.setFather(y);
    }
    private void rotate_right(RB_Node<T> y){
        RB_Node<T> x = y.getLeft();
        y.setLeft(x.getRight());
        if(x.getRight() != TNull) x.getRight().setFather(y);
        x.setFather(y.getFather());
        if(y.getFather() == null) this.root = x;
        else if (y.getFather().getLeft() == y)
        y.getFather().setLeft(x);
        else y.getFather().setRight(x);
        x.setRight(y);
        y.setFather(x);
    }

    private RB_Node<T> insert(RB_Node<T> root, RB_Node<T> node){
        if(root == TNull) return node;
        if(node.getValue().compareTo(node.getValue()) < 0){
            root.setLeft(insert(root.getLeft(), node));
            root.getLeft().setFather(root);
        }else{
            root.setRight(insert(root.getRight(), node));
            root.getRight().setFather(root);
        }
        return root;
    }

    public void insert(T value){
        RB_Node<T> node = new RB_Node<T>(value);

        root = insert(root, node);
        balance(node);
    }
    public T search(T key){
        return  search(root, key).getValue();
    }
    private RB_Node<T> search(RB_Node<T> node, T key){
        if(node == TNull || key.equals(node.getValue()))
            return node;
            if (key.compareTo(node.getValue()) < 0) {
                return search(node.getLeft(), key);
            }
            return search(node.getRight(), key);

    }
    private void replace_branch(RB_Node<T> u, RB_Node<T> v) {
        if (u.getFather() == null)
            root = v;
        else if (u == u.getFather().getLeft())
            u.getFather().setLeft(v);
        else
            u.getFather().setRight(v);
        
        v.setFather(u.getFather());
    }

    private void balance(RB_Node<T> x){
        RB_Node<T> y;
        while (x.getFather().getColor() == RED) {

            if(x.getFather() == x.getFather().getFather().getRight()){//si el padre es hijo derecho
                y = x.getFather().getLeft(); 
                if(y.getColor() == RED){
                    y.setColor(BLACK);
                    x.getFather().setColor(BLACK);
                    x.getFather().getFather().setColor(RED);
                    x = x.getFather().getFather();
                } else {
                    if(x == x.getFather().getLeft()){
                        x = x.getFather();
                        rotate_right(x);
                    }
                    x.getFather().setColor(BLACK);
                    x.getFather().getFather().setColor(RED);
                    rotate_left(x.getFather().getFather());
                }
            }else {
                y = x.getFather().getRight();
                if(y.getColor() == RED){
                    y.setColor(BLACK);
                    x.getFather().setColor(BLACK);
                    x.getFather().getFather().setColor(RED);
                    x = x.getFather().getFather();
                } else {
                    if(x == x.getFather().getRight()){
                        x = x.getFather();
                        rotate_left(x);
                    }
                    x.getFather().setColor(BLACK);
                    x.getFather().getFather().setColor(RED);
                    rotate_right(x.getFather().getFather());
                }
            }
            if(x == root) break;
        }
        root.setColor(BLACK);
    }
}
