package proto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Egy csövet reprezentáló osztály.
 */
public class Pipe extends Field implements Tickable {
    private Random random = new Random();

    private boolean isBroken;

    private int maxVolume;
    private int currentVolume;

    private int wastedWater;

    private int timeUntilBreakable;
    private int slipperyUntil;
    private int stickyUntil;

    private List<FieldNode> ends;

    /**
     * Egy új csövet készít.
     */
    public Pipe() {
        super();

        isBroken = false;

        // TODO: Megkapja paraméternek ezt vagy mindegyikhez legyen ugyanaz?
        maxVolume = 500;
        currentVolume = 0;

        wastedWater = 0;

        timeUntilBreakable = 0;
        slipperyUntil = 0;
        stickyUntil = 0;

        ends = new ArrayList<>(2);
    }

    /**
     * Beállítja hogy mennyi víz van a csőben.
     */
    void setWaterVolume(int amount) {
        if (maxVolume < amount)
            throw new IllegalArgumentException("Can't put more water into pipe than the maximum allowed volume");
        if (amount < 0)
            throw new IllegalArgumentException("Water volume in pipe can't be less than 0");

        currentVolume = amount;
    }

    // TODO: Dokumentálni kell majd
    // TODO: Mekkora értékre állítsa?
    void makeSlippery() {
        slipperyUntil = 3;
    }

    // TODO: Dokumentálni kell majd
    // TODO: Mekkora értékre állítsa?
    void makeSticky() {
        stickyUntil = 3;
    }

    /**
     * Kilyukasztja a csövet, úgy hogy az összes benne lévő és rajta átfolyó víz
     * kifolyik belőle.
     */
    public void breakPipe() {
        if (0 < timeUntilBreakable)
            return;
        isBroken = true;
    }

    /**
     * Ha van lyuk a csövön akkor megfoltozza azt.
     */
    public void repair() {
        isBroken = false;

        // TODO: Milyen értékek közé ekerjuk majd szorítani?
        timeUntilBreakable = random.nextInt(1, 3);
    }

    /**
     * Vizet tölt a csőbe.
     * 
     * @param amount A víz mennyisége amit a csőbe szeretnénk tölteni.
     * @return A annak a víznek a mennyisége amit sikeresen betöltöttünk a csőbe.
     */
    public int flow(int amount) {
        // TODO: Egy olyan csőbe aminek nincs lekötve mindkét vége ha vizet töltenek
        // akkor kifolyik?
        if (isBroken || ends.size() != 2) {
            wastedWater += amount;
            return amount;
        }

        int consumed = Math.min(maxVolume - currentVolume, amount);
        currentVolume += consumed;
        return consumed;
    }

    /**
     * Kiszívja a vizet a csőből.
     * 
     * @param amount A víz mennyisége amit ki akarunk szívni.
     * @return A víz mennyisége amit sikeresen leszívtunk.
     */
    public int drain(int amount) {
        int drained = Math.min(currentVolume, amount);
        return drained;
    }

    /**
     * Hozzáadja a paraméterként kapott játékost, amelyik a mezőre lép, a játékosok
     * listájához
     *
     * @param p - A játékos amelyet hozzáadjuk a listához.
     * @return A hozzáadás sikeressége.
     */
    @Override
    public Field addPlayer(Player p) {
        // TODO: Ha van rajta ember akkor is el tud csúszni?
        int standing = getNumberOfPlayers();
        if (0 < standing) {
            return null;
        }

        if (0 < slipperyUntil) {
            Field slipTo = ends.get(random.nextInt(ends.size()));
            slipTo.addPlayer(p);
            return slipTo;
        }

        super.addPlayer(p);
        return this;
    }

    @Override
    public boolean removePlayer(Player p) {
        if (0 < stickyUntil)
            return false;

        super.removePlayer(p);
        return true;
    }

    /**
     * Beállítja a megadott csomópontot a cső egyik végének.
     * 
     * @param n A megadott csomópont amihez csatlakozni szeretnénk.
     * @return Visszaadja, hogy sikerült-e csatlakozni a csomóponthoz.
     */
    public boolean connect(FieldNode n) {
        if (ends.size() == 2)
            throw new IllegalArgumentException("A pipe can't have more than two ends");

        if (currentVolume != 0 || getNumberOfPlayers() != 0) {
            return false;
        }

        ends.add(n);
        return true;
    }

    /**
     * Eltávolítja a megadott csomópontot a cső végei közül.
     * 
     * @param n A csomópont amit el akarunk távolítani.
     * @return Visszaadja, hogy sikerült-e eltávolítani a csomópontot.
     */
    public boolean disconnect(FieldNode n) {
        if (currentVolume != 0 || getNumberOfPlayers() != 0) {
            return false;
        }

        return ends.remove(n);
    }

    /**
     * Félbevágja a csövet, ezzel egy új csövet készítve.
     * 
     * @return Visszaadja az ujonnan elkészült csövet, ha sikerült a félbevágás
     *         különben pedig null-t ad vissza.
     */
    public Pipe cut() {
        if (currentVolume != 0 || ends.size() != 2) {
            return null;
        }

        FieldNode fn = ends.get(1);

        this.disconnect(fn);
        fn.disconnect(this);

        Pipe newPipe = new Pipe();
        newPipe.connect(fn);
        fn.connect(newPipe);

        return newPipe;
    }

    @Override
    public void tick() {
        if (0 < timeUntilBreakable)
            timeUntilBreakable--;

        if (0 < slipperyUntil)
            slipperyUntil--;

        if (0 < stickyUntil)
            stickyUntil--;
    }
}
