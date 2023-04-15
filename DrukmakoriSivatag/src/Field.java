import java.util.ArrayList;
import java.util.List;

public abstract class Field {
    protected List<Player> players;

    /**
     * Konsturktor.
     */
    public Field() {
        players = new ArrayList<>();
    }


    /**
     * Hozzáadja a paraméterként kapott játékost, amelyik a mezőre lép, a játékosok listájához
     *
     * @param p - A játékos amelyet hozzáadjuk a listához.
     * @return
     */
    public boolean addPlayer(Player p) {
        Skeleton.callFunction(this, "addPlayer", new Object[]{p});
//        players.add(p);
        Skeleton.endFunction();
        return true;
    }

    /**
     *  A paraméterként kapott játékos ellép a pályaelemről és ezt kitörli a játékosok listájából.
     *
     * @param p - A törlendő játékos.
     */
    public void removePlayer(Player p) {
        Skeleton.callFunction(this, "removePlayer", new Object[]{p});
//        players.remove(p);
        Skeleton.endFunction();
    }

    /**
     * Visszatéríti a pályaelemen álló játékosok számát.
     *
     * @return a pályaelemen álló játékosok száma.
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Egy cső felvevése a mezőről, ha nem megy akkor null értékkel tér vissza.
     *
     * @return az elvett cső vagy null
     */
    public Pipe takePipe() {
        Skeleton.callFunction(this, "takePipe", null);
        boolean canTakePipe = Skeleton.yesNoQuestion("Were there available pipes?");
        if(canTakePipe) {
            int volume = Skeleton.numberQuestion("Enter the pump's maximum volume");
            Skeleton.endFunction();
            Pipe newPipe = new Pipe(volume);
            Skeleton.names.put(newPipe, "newPipe");
            return newPipe;
        }
        Skeleton.endFunction();
        return null;
    }

    /**
     * Egy pumpa felvevése a mezőről, ha nem megy akkor null értékkel tér vissza.
     *
     * @return az elvett pumpa vagy null
     */
    public Pump takePump() {
        boolean canTakePump = Skeleton.yesNoQuestion("Were there available pumps?");
        if (canTakePump) {
            int volume = Skeleton.numberQuestion("Enter the pump's maximum volume");
            return new Pump(volume);
        }
        return null;
    }
}
