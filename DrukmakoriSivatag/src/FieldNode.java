import java.util.ArrayList;
import java.util.List;

public abstract class  FieldNode extends Field{
    protected List<Pipe> pipes = new ArrayList<>();

    /**
     * A csomóponthoz csatlakoztatja a paraméterként kapott csövet
     *
     * @param p - A cső amelyiket csatlakoztatjuk.
     */
    public void connect(Pipe p){
        Skeleton.callFunction(this, "connect", null);
        pipes.add(p);
        Skeleton.endFunction();
    }

    /**
     * Lecsatlakoztatja a csomópontról a paraméterként kapott csövet.
     *
     * @param p - A cső amelyiket lecsatlakoztatjuk.
     */
    public void disconnect(Pipe p){
        Skeleton.callFunction(this, "disconnect", null);
        if(pipes != null) {
            pipes.remove(p);
        }
        Skeleton.endFunction();
    }
}
