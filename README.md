DECISIONES DE DESARROLLO:

Elegí usar el nil en lugar de los punteros nulos normales porque hace que
removeFixup() sea más fácil y eficiente.  Cada RedBlackNode instanciado tiene
todos sus indicadores apuntando a cero.  La raíz en todo momento tendrá su
puntero principal a cero.

Después de insertar un elemento usando insert(), siempre llamamos a insertFixup()
para garantizar que se mantengan las propiedades rojo-negro.  Mientras que al eliminar,
nosotros solo llamamos a deleteFixup cuando una determinada condición 
(x == NEGRO) sea verdadera.

Como sólo nos interesa eliminar la clave del árbol, comenzaremos
nuestra función de eliminación (RedBlackNode v) con una llamada a buscar (v.key) que
asegúrese de que estamos eliminando el nodo correcto.

Este valor se actualiza cuando las funciones insertan y mantienen un nodo
leftRotateFixup(RedBlackNode) y rightRotateFixup(RedBlackNode) que actualizan
estas variables cuando ocurre una rotación. Este valor también se actualiza durante el
eliminación de un nodo mediante la función llamada fixNodeData(RedBlackNode, int).

Mi función size() verifica el tamaño de las variables raíces numLeft y numRight,
agréguelos y agregue uno para devolver la respuesta.  Esta operación se realiza en
O(1) tiempo.

En el programa, estoy comprobando el caso en el que un RedBlackNode en particular tiene
un puntero que apunta a cero, ya que esta operación es muy común, tengo un
función llamada isNil(RedBlackNode), que devuelve un valor booleano de si
el argumento es nulo o no.  He elegido que mi función de búsqueda sea
iterativo cuando fácilmente podría haber sido recursivo porque una búsqueda 
iterativa siempre es más rápida que una recursiva.

Se considera que los RedBlackNodes duplicados son ligeramente mayores que su
contraparte con la misma llave.  La función insert() se encarga de esto
al tener varios casos en su bucle while, uno para < y otro para =>.  
La función fixNodeData() se encarga de esto durante la eliminación, 
ya que también tiene dos casos.

---------------------------------------------------------

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

-----------------------------------------------------------
Rotaciones
-----------------------------------------------------------

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
       /    \
      T2    T3

Resumen de las Rotaciones

Las rotaciones en un árbol Rojo-Negro son operaciones locales que se utilizan para mantener el equilibrio del árbol. Después de una inserción o eliminación, el árbol puede necesitar una serie de rotaciones y cambios de color para asegurar que sigue cumpliendo las propiedades del árbol Rojo-Negro:

    Ningún nodo es rojo si su padre es rojo.
    Todos los caminos desde un nodo dado hasta sus nodos hoja contienen el mismo número de nodos negros.

Implementar correctamente estas rotaciones es esencial para mantener el balance del árbol y asegurar una complejidad de tiempo de búsqueda, inserción y eliminación de O(log⁡n)O(logn).