import java.util.List;

public abstract class Field {
    protected List<Player> players;

    public abstract boolean addPlayer(Player p);

    public void removePlayer(Player p) {
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
