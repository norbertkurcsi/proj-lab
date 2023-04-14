import java.util.ArrayList;
import java.util.List;

public abstract class Field {
    protected List<Player> players;

    public Field() {
        players = new ArrayList<>();
    }

    public boolean addPlayer(Player p) {
        players.add(p);
        return true;
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    public int getNumberOfPlayers() {
        return 0;
    }

    public Pipe takePipe() {
        return null;
    }

    public Pump takePump() {
        return null;
    }
}
