import java.util.ArrayList;
import java.util.List;

public interface  Node {
    List<Pipe> pipes = new ArrayList<>();

    public void connect(Pipe p);

    public void disconnect(Pipe p);
}
