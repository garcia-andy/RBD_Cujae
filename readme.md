Los árboles rojo y negro (RBT, por sus siglas en inglés) son un tipo de árbol binario de búsqueda autoequilibrado. Son útiles en la informática para mantener un conjunto de datos ordenados, permitiendo una inserción, eliminación y búsqueda eficientes. Aquí te explico sus características y cómo funcionan:
Características

    Nodos y colores:
        Cada nodo es rojo o negro.
        La raíz es siempre negra.
        Las hojas (nodos nulos) son negras.

    Propiedades del árbol rojo-negro:
        Propiedad 1: Cada nodo es rojo o negro.
        Propiedad 2: La raíz es negra.
        Propiedad 3: Todas las hojas (NIL) son negras.
        Propiedad 4: Si un nodo es rojo, ambos hijos son negros.
        Propiedad 5: Para cada nodo, todos los caminos simples desde el nodo hasta sus descendientes NIL contienen el mismo número de nodos negros.

Operaciones básicas

    Inserción:
        Se inserta como en un árbol binario de búsqueda.
        El nuevo nodo se colorea de rojo.
        Después de la inserción, se realizan ajustes para mantener las propiedades del árbol. Esto puede incluir recoloreado y rotaciones.

    Eliminación:
        Se elimina como en un árbol binario de búsqueda.
        Si el nodo eliminado o su sustituto es negro, se realizan ajustes para mantener las propiedades del árbol. Esto también puede incluir recoloreado y rotaciones.

Rotaciones

Las rotaciones son operaciones que cambian la estructura del árbol sin afectar el orden in-order de los nodos.

    Rotación a la izquierda:
        Se mueve el nodo hacia la izquierda.
        El hijo derecho del nodo se convierte en el nuevo padre del nodo, y el nodo se convierte en el hijo izquierdo del nuevo padre.

    Rotación a la derecha:
        Se mueve el nodo hacia la derecha.
        El hijo izquierdo del nodo se convierte en el nuevo padre del nodo, y el nodo se convierte en el hijo derecho del nuevo padre.

Ejemplo de inserción

Supongamos que queremos insertar un nodo con valor 7 en un árbol rojo-negro:

    Se inserta 7 como en un árbol binario de búsqueda.
    El nodo 7 se colorea de rojo.
    Si el padre de 7 es negro, no se necesita ningún ajuste.
    Si el padre de 7 es rojo, se aplican las reglas de ajuste (recolorado y rotaciones) para mantener las propiedades del árbol.

Beneficios

    Eficiencia: Las operaciones de inserción, eliminación y búsqueda tienen un tiempo de complejidad O(log n) en el peor de los casos.
    Equilibrio: El árbol se mantiene equilibrado, evitando que las operaciones se degraden a O(n), como puede ocurrir en un árbol binario de búsqueda no balanceado.

------------------------------------------------------------------------------------


----------------------------------------------------------------------------------

Sí, en un árbol rojo-negro, cada nodo tiene dos hijos nulos que se consideran nodos negros. Estos nodos nulos son una parte crucial del mantenimiento de las propiedades del árbol. Aquí tienes una explicación más detallada:
Nodos NIL (Nulos) en Árboles Rojo-Negro

    Representación de Hojas:
        En un árbol rojo-negro, todas las hojas (nodos sin hijos) se representan mediante nodos nulos (NIL).
        Estos nodos NIL se consideran negros para mantener la propiedad 5 (el número de nodos negros en cualquier camino desde un nodo hasta sus hojas es el mismo).

    Propiedad 5:
        La propiedad 5 establece que todos los caminos simples desde un nodo hasta sus hojas deben contener el mismo número de nodos negros.
        Para cumplir esta propiedad, cada nodo no terminal (nodo con al menos un hijo) debe tener dos hijos. Si un nodo no tiene hijos reales, se le asignan dos nodos NIL negros.

Inserción de un Nodo

Cuando se inserta un nuevo nodo en un árbol rojo-negro, se le asignan automáticamente dos hijos NIL negros para asegurar que el árbol mantenga su estructura correcta y las propiedades necesarias. Aquí tienes un ejemplo paso a paso:

    Insertar un Nodo Nuevo:
        El nuevo nodo se inserta como un nodo rojo.
        Se asignan dos hijos NIL negros al nuevo nodo.

    Ajustes Posteriores:
        Después de la inserción, se pueden necesitar ajustes (rotaciones y/o recoloreado) para asegurar que las propiedades del árbol rojo-negro se mantengan.

Ejemplo de Inserción

Supongamos que queremos insertar un nodo con valor 20 en un árbol rojo-negro:

    Paso 1: Inserción Inicial:
        Se inserta el nodo 20 como rojo.
        El nodo 20 tiene dos hijos NIL negros.

    plaintext

        20 (rojo)
       /   \
     NIL   NIL (negros)

    Paso 2: Ajustes si son necesarios:
        Dependiendo de la configuración del árbol después de la inserción, pueden ser necesarios ajustes adicionales para mantener las propiedades del árbol rojo-negro.

Visualización

En un árbol rojo-negro, la representación gráfica incluiría los nodos NIL negros para todas las hojas. Por ejemplo, en un árbol simple con tres nodos, uno negro y dos rojos:

plaintext

       10 (negro)
      /    \
   5 (rojo)  15 (rojo)
  / \       /  \
NIL NIL   NIL  NIL (negros)

En este árbol, todos los nodos NIL negros se incluyen para asegurar que todas las propiedades del árbol rojo-negro se mantengan.
Resumen

    Los nodos NIL negros se asignan automáticamente a cualquier nodo que se inserte en un árbol rojo-negro.
    Estos nodos NIL son esenciales para mantener las propiedades del árbol, especialmente la propiedad 5.
    Cada inserción implica no solo la adición del nuevo nodo rojo sino también la asignación de sus dos hijos NIL negros.

Esta estructura asegura que el árbol se mantenga equilibrado y que todas las operaciones (inserción, eliminación, búsqueda) se realicen de manera eficiente.
---------------------------------------------------------------
public class RedBlackTree {
    private final int RED = 1;
    private final int BLACK = 0;

    private Node root;
    private Node TNULL;

    public RedBlackTree() {
        TNULL = new Node(0);
        TNULL.color = BLACK;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    // Rotaciones
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Insertar un nodo
    public void insert(int key) {
        Node node = new Node(key);
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = RED;

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }

    // Orden en el árbol
    public void inOrder() {
        inOrderHelper(this.root);
    }

    private void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    // Verifica si el árbol contiene un nodo con el valor dado
    public boolean contains(int key) {
        return searchTreeHelper(this.root, key) != TNULL;
    }

    private Node searchTreeHelper(Node node, int key) {
        if (node == TNULL || key == node.data) {
            return node;
        }

        if (key < node.data) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(55);
        tree.insert(40);
        tree.insert(65);
        tree.insert(60);
        tree.insert(75);
        tree.insert(57);

        tree.inOrder();
    }
}

--------------------------------------------------
`public class RedBlackTree {
    private final int RED = 1;
    private final int BLACK = 0;

    private Node root;
    private Node TNULL;

    public RedBlackTree() {
        TNULL = new Node(0);
        TNULL.color = BLACK;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    // Rotaciones
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Insertar un nodo de forma recursiva
    private Node insertRecursive(Node root, Node node) {
        if (root == TNULL) {
            return node;
        }

        if (node.data < root.data) {
            root.left = insertRecursive(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = insertRecursive(root.right, node);
            root.right.parent = root;
        }

        return root;
    }

    // Insertar un nodo y balancear el árbol
    public void insert(int key) {
        Node node = new Node(key);
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = RED;

        root = insertRecursive(root, node);
        fixInsert(node);
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }

    // Orden en el árbol
    public void inOrder() {
        inOrderHelper(this.root);
    }

    private void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    // Verifica si el árbol contiene un nodo con el valor dado
    public boolean contains(int key) {
        return searchTreeHelper(this.root, key) != TNULL;
    }

    private Node searchTreeHelper(Node node, int key) {
        if (node == TNULL || key == node.data) {
            return node;
        }

        if (key < node.data) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(55);
        tree.insert(40);
        tree.insert(65);
        tree.insert(60);
        tree.insert(75);
        tree.insert(57);

        tree.inOrder();
    }
}
`

-----------------------------------------------------------
Rotaciones
-----------------------------------------------

Las rotaciones en un árbol Rojo-Negro son operaciones cruciales para mantener el equilibrio del árbol después de las inserciones y eliminaciones. Hay dos tipos principales de rotaciones: la rotación a la izquierda (left rotation) y la rotación a la derecha (right rotation). A continuación, te explico cómo funcionan ambas rotaciones y cómo se implementan.
Rotación a la Izquierda (Left Rotation)

La rotación a la izquierda se realiza en un nodo xx cuando su hijo derecho yy sube para convertirse en el nuevo padre de xx, y xx se convierte en el hijo izquierdo de yy.
Diagrama de la Rotación a la Izquierda

Antes de la rotación:

markdown

    x
     \
      y
     / \
    T2  T3

Después de la rotación:

markdown

      y
     / \
    x   T3
   / \
 T2  T1

Implementación de la Rotación a la Izquierda

java

private void leftRotate(RD_Node<T> x) {
    RD_Node<T> y = x.getRight(); // y es el hijo derecho de x
    x.setRight(y.getLeft()); // el hijo izquierdo de y se convierte en el hijo derecho de x
    if (y.getLeft() != TNull) {
        y.getLeft().setParent(x); // actualizar el padre del hijo izquierdo de y
    }
    y.setParent(x.getParent()); // el padre de x se convierte en el padre de y
    if (x.getParent() == null) {
        this.root = y; // si x es la raíz, y se convierte en la nueva raíz
    } else if (x == x.getParent().getLeft()) {
        x.getParent().setLeft(y); // si x es el hijo izquierdo, y se convierte en el hijo izquierdo
    } else {
        x.getParent().setRight(y); // si x es el hijo derecho, y se convierte en el hijo derecho
    }
    y.setLeft(x); // x se convierte en el hijo izquierdo de y
    x.setParent(y); // y se convierte en el padre de x
}

Rotación a la Derecha (Right Rotation)

La rotación a la derecha se realiza en un nodo xx cuando su hijo izquierdo yy sube para convertirse en el nuevo padre de xx, y xx se convierte en el hijo derecho de yy.
Diagrama de la Rotación a la Derecha

Antes de la rotación:

markdown

      y
     / \
    x   T3
   / \
 T1  T2

Después de la rotación:

markdown

    x
   / \
  T1  y
     / \
    T2  T3

Implementación de la Rotación a la Derecha

java

private void rightRotate(RD_Node<T> x) {
    RD_Node<T> y = x.getLeft(); // y es el hijo izquierdo de x
    x.setLeft(y.getRight()); // el hijo derecho de y se convierte en el hijo izquierdo de x
    if (y.getRight() != TNull) {
        y.getRight().setParent(x); // actualizar el padre del hijo derecho de y
    }
    y.setParent(x.getParent()); // el padre de x se convierte en el padre de y
    if (x.getParent() == null) {
        this.root = y; // si x es la raíz, y se convierte en la nueva raíz
    } else if (x == x.getParent().getRight()) {
        x.getParent().setRight(y); // si x es el hijo derecho, y se convierte en el hijo derecho
    } else {
        x.getParent().setLeft(y); // si x es el hijo izquierdo, y se convierte en el hijo izquierdo
    }
    y.setRight(x); // x se convierte en el hijo derecho de y
    x.setParent(y); // y se convierte en el padre de x
}

Resumen de las Rotaciones

Las rotaciones en un árbol Rojo-Negro son operaciones locales que se utilizan para mantener el equilibrio del árbol. Después de una inserción o eliminación, el árbol puede necesitar una serie de rotaciones y cambios de color para asegurar que sigue cumpliendo las propiedades del árbol Rojo-Negro:

    Ningún nodo es rojo si su padre es rojo.
    Todos los caminos desde un nodo dado hasta sus nodos hoja contienen el mismo número de nodos negros.

Implementar correctamente estas rotaciones es esencial para mantener el balance del árbol y asegurar una complejidad de tiempo de búsqueda, inserción y eliminación de O(log⁡n)O(logn).
