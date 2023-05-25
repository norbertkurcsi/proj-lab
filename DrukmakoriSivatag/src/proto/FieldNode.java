package proto;

import java.util.ArrayList;
import java.util.List;

/**
 * Egy csomópontot reprezentáló absztrakt osztály.
 */
public abstract class FieldNode extends Field implements Tickable {
    /**
     * A csomóponthoz csatlakoztatott csövek.
     */
    protected List<Pipe> pipes = new ArrayList<>();

    /**
     * A csomóponthoz csatlakoztatja a paraméterként kapott csövet
     *
     * @param p - A cső amelyiket csatlakoztatjuk.
     */
    public void connect(Pipe p) {
        pipes.add(p);
    }

    /**
     * Lecsatlakoztatja a csomópontról a paraméterként kapott csövet.
     *
     * @param p - A cső amelyiket lecsatlakoztatjuk.
     */
    public void disconnect(Pipe p) {
        pipes.remove(p);
    }

    public FieldNode[] getConnectedNodes() {
        FieldNode[] nodes = new FieldNode[pipes.size()];
        for (int i = 0; i < pipes.size(); i++) {
            for (FieldNode end : pipes.get(i).getEnds()) {
                if (end != this) {
                    nodes[i] = end;
                    break;
                }
            }
        }
        return nodes;
    }

    public abstract void tick();

    @Override
    public String toString() {
        if (pipes.isEmpty())
            return "null";
        String s = "";
        for (Pipe p : pipes) {
            s += (Proto.findName(p) + ", ");
        }
        return s.substring(0, s.length() - 2);
    }
}
