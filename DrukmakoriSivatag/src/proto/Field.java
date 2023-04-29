package proto;

import java.util.ArrayList;
import java.util.List;

/**
 * Egy mezőt reprezentáló absztrakt osztály.
 */
public abstract class Field {
    protected List<Player> players;

    /**
     * Konstruktor.
     */
    public Field() {
        players = new ArrayList<>();
    }

    /**
     * Hozzáadja a paraméterként kapott játékost, amelyik a mezőre lép, a játékosok
     * listájához
     *
     * @param p - A játékos amelyet hozzáadjuk a listához.
     * @return referencia saját magára
     */
    public Field addPlayer(Player p) {
        players.add(p);
        return this;
    }

    /**
     * A paraméterként kapott játékos ellép a pályaelemről és ezt kitörli a
     * játékosok listájából.
     *
     * @param p - A törlendő játékos.
     * @return true-t térít vissza
     */
    public boolean removePlayer(Player p) {
        players.remove(p);
        return true;
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
        return null;
    }

    /**
     * Egy pumpa felvevése a mezőről, ha nem megy akkor null értékkel tér vissza.
     *
     * @return az elvett pumpa vagy null
     */
    public Pump takePump() {
        return null;
    }
}
