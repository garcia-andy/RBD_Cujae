package nodes;
import enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RB_Node<T extends Comparable<T>> {
    private COLORNODE color;
    private RB_Node<T> left;
    private RB_Node<T> right;
    private RB_Node<T> father;
    private T value;

    public RB_Node(T value){
        this.value = value;
    }
}

