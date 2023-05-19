package proto;

import java.util.Random;

/**
 * A Pump osztálya.
 */
public class Pump extends FieldNode implements Tickable {
    static Random random = new Random();
    static final int MAX_VOLUME = 500;
    boolean isBroken;
    int maxVolume;
    int currentVolume;

    /**
     * A pumpa bemeneti csöve
     */
    private Pipe pipeIn;
    /**
     * A pumpa kimeneti csöve
     */
    private Pipe pipeOut;

    /**
     * Konstruktor
     */
    public Pump() {
        isBroken = false;

        maxVolume = MAX_VOLUME;
        currentVolume = 0;

        pipeIn = null;
        pipeOut = null;
    }

    /**
     * Beállítja a paraméterként kapott értékkel a pumpa tartályában lévő aktuális vízmennyiséget.
     * @param amount - a pumpa tartályában lévő vízmennyiség
     */
    public void setWaterVolume(int amount) {
        if (maxVolume < amount)
            throw new IllegalArgumentException("Can't put more water into pump than the maximum allowed volume");
        if (amount < 0)
            throw new IllegalArgumentException("Water volume in pump can't be less than 0");

        currentVolume = amount;
    }

    /**
     * Beállítja a paraméterként kapott értékkel a pumpa tartályának a maximális kapacitását.
     *
     * @param amount - a pumpa maximális kapacitása
     */
    public void setMaxVolume(int amount) {
        if (amount < currentVolume)
            throw new IllegalArgumentException("Max volume cannot be less than the current volume");
        if (amount < 0)
            throw new IllegalArgumentException("Max volume of pump can't be less than 0");

        maxVolume = amount;
    }

    /**
     * Hozzáad egy adott mennyiségű vizet a pumpa tartájához.
     * TODO: szandekosan van package lathatosag
     * @param amount A hozzáadandó vízmennyiség.
     */
    public void addVolume(int amount) {
        if (maxVolume - currentVolume < amount)
            throw new IllegalArgumentException("Can't add more water to pump than the volume of its storage");
        currentVolume += amount;
    }

    /**
     * Csökkenti a pumpában található vízmennyiséget.
     * TODO: szandekosan van package lathatosag
     * @param amount A csökkenteni kívánt mennyiség.
     */
    public void decreaseVolume(Integer amount) {
        if (currentVolume < amount)
            throw new IllegalArgumentException("There can't be less water than 0 in the pump");
        currentVolume -= amount;
    }

    /**
     * A pumpa meghibásodik.
     */
    public void breakPump() {
        isBroken = true;
    }

    /**
     * Amennyiben a pumpa el volt romolva, megjavul.
     */
    public void repair() {
        isBroken = false;
    }

    /**
     * Átállítja a pumpa ki- és bemenetét.
     * 
     * @param in  Az új bemenet.
     * @param out Az új kimenet.
     */
    public void changeFlow(Pipe in, Pipe out) {
        if(pipes.contains(in) && pipes.contains(out)) {
            pipeIn = in;
            pipeOut = out;
        }
    }

    /**
     * Egy időegység elteltét jelenti.
     * A pumpa véletlenszerűen elromolhat. Amennyiben a pumpa nincs elromolja, akkor
     * a bemeneti csőből a kimeneti csövőbe pumpál
     * adott mennyiségű vizet.
     */
    @Override
    public void tick() {
        if (isBroken) {
            return;
        }

        if(Proto.isRandom) {
            if(random.nextInt(0, 2) == 0) {
                breakPump();
            }
        }

        if (pipeIn != null) {
            int drained = pipeIn.drain(maxVolume - currentVolume);
            addVolume(drained);
        }

        if (pipeOut != null) {
            int pushedOut = pipeOut.flow(currentVolume);
            decreaseVolume(pushedOut);
        }
    }

    //TODO
    public boolean isBroken() {
        return isBroken;
    }

    @Override
    public String toString() {
        String playerList = "";
        if (players.isEmpty()) playerList = "null";
        else {
            for (Player p : players) {
                playerList += (Proto.findName(p) + ", ");
            }
            playerList = playerList.substring(0, playerList.length() - 2);
        }

        return "Pump " +
                Proto.findName(this) +
                " with ends: " +
                super.toString() +
                " input: " +
                Proto.findName(this.pipeIn) +
                " output: " +
                Proto.findName(this.pipeOut) +
                " broken: " +
                (isBroken ? "true" : "false") +
                " max volume: " +
                this.maxVolume +
                " current volume: " +
                this.currentVolume +
                " standing players: " +
                playerList;
    }

}
