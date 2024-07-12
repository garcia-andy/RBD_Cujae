Lo siento por la confusión, parece que hubo un error en la explicación. Vamos a corregirlo y aclarar el proceso de la rotación a la izquierda con más detalle.
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
     \
      T2

En el diagrama, xx se convierte en el hijo izquierdo de yy, y T2T2 (que es el hijo izquierdo de yy antes de la rotación) se convierte en el hijo derecho de xx.
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

La rotación a la derecha se realiza en un nodo yy cuando su hijo izquierdo xx sube para convertirse en el nuevo padre de yy, y yy se convierte en el hijo derecho de xx.
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

En el diagrama, yy se convierte en el hijo derecho de xx, y T2T2 (que es el hijo derecho de xx antes de la rotación) se convierte en el hijo izquierdo de yy.
Implementación de la Rotación a la Derecha

java

private void rightRotate(RD_Node<T> y) {
    RD_Node<T> x = y.getLeft(); // x es el hijo izquierdo de y
    y.setLeft(x.getRight()); // el hijo derecho de x se convierte en el hijo izquierdo de y
    if (x.getRight() != TNull) {
        x.getRight().setParent(y); // actualizar el padre del hijo derecho de x
    }
    x.setParent(y.getParent()); // el padre de y se convierte en el padre de x
    if (y.getParent() == null) {
        this.root = x; // si y es la raíz, x se convierte en la nueva raíz
    } else if (y == y.getParent().getRight()) {
        y.getParent().setRight(x); // si y es el hijo derecho, x se convierte en el hijo derecho
    } else {
        y.getParent().setLeft(x); // si y es el hijo izquierdo, x se convierte en el hijo izquierdo
    }
    x.setRight(y); // y se convierte en el hijo derecho de x
    y.setParent(x); // x se convierte en el padre de y
}

Resumen de las Rotaciones

Las rotaciones en un árbol Rojo-Negro son operaciones locales que se utilizan para mantener el equilibrio del árbol. Después de una inserción o eliminación, el árbol puede necesitar una serie de rotaciones y cambios de color para asegurar que sigue cumpliendo las propiedades del árbol Rojo-Negro:

    Ningún nodo es rojo si su padre es rojo.
    Todos los caminos desde un nodo dado hasta sus nodos hoja contienen el mismo número de nodos negros.

Implementar correctamente estas rotaciones es esencial para mantener el balance del árbol y asegurar una complejidad de tiempo de búsqueda, inserción y eliminación de O(log⁡n)O(logn).
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    