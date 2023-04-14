import java.util.ArrayList;
import java.util.List;

public class  FieldNode extends Field{
    protected List<Pipe> pipes = new ArrayList<>();

    /**
     * A csomóponthoz csatlakoztatja a paraméterként kapott csövet
     *
     * @param p - A cső amelyiket csatlakoztatjuk.
     */
    public void connect(Pipe p){

    }

    /**
     * Lecsatlakoztatja a csomópontról a paraméterként kapott csövet.
     *
     * @param p - A cső amelyiket lecsatlakoztatjuk.
     */
    public void disconnect(Pipe p){

    }
}
