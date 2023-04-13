import java.util.List;
import java.util.ArrayList;

public class Pipe {
    private boolean isBroken;
    private int maxVolume;
    private int currentVolume;
    private int wastedWater;

    public void breakPipe() {

    }

    public void repair() {

    }

    public int flow(int amount) {
        return 0;
    }

    public int drain(int amount) {
        return 0;
    }

    public int getVolume() {
        return 0;
    }

    public boolean addPlayer(Player p) {
        return false;
    }

    public boolean connect(Node n) {
        return false;
    }

    public boolean disconnect(Node n) {
        return false;
    }

    public int getCapacity() {
        return  0;
    }

    public List<Node> getEnds() {
        return new ArrayList<Node>();
    }

    public Pipe cut() {
        return new Pipe();
    }
}
