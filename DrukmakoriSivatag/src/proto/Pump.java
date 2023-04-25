package proto;

/**
 * A Pump osztálya.
 */
public class Pump extends FieldNode implements Tickable {
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

        // TODO: Hogy fogjuk megadni hogy mennyi víz fér a pumpába?
        maxVolume = 500;
        currentVolume = 0;

        pipeIn = null;
        pipeOut = null;
    }

    // TODO
    public void setWaterVolume(int amount) {
        if (maxVolume < amount)
            throw new IllegalArgumentException("Can't put more water into pump than the maximum allowed volume");
        if (amount < 0)
            throw new IllegalArgumentException("Water volume in pump can't be less than 0");

        currentVolume = amount;
    }

    //TODO
    public void setMaxVolume(int amount) {
        if (amount < currentVolume)
            throw new IllegalArgumentException("Max volume cannot be less than the current volume");
        if (amount < 0)
            throw new IllegalArgumentException("Max volume of pump can't be less than 0");

        maxVolume = amount;
    }

    /**
     * Hozzáad egy adott mennyiségű vizet a pumpa tartájához.
     * 
     * @param amount A hozzáadandó vízmennyiség.
     */
    void addVolume(int amount) {
        if (maxVolume - currentVolume < amount)
            throw new IllegalArgumentException("Can't add more water to pump than the volume of its storage");
        currentVolume += amount;
    }

    /**
     * Csökkenti a pumpában található vízmennyiséget.
     * 
     * @param amount A csökkenteni kívánt mennyiség.
     */
    void decreaseVolume(Integer amount) {
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
        isBroken = true;
    }

    /**
     * Átállítja a pumpa ki- és bemenetét.
     * 
     * @param in  Az új bemenet.
     * @param out Az új kimenet.
     */
    public void changeFlow(Pipe in, Pipe out) {
        // TODO: Ellenőrizzük hogy rá van csatlakozva-e a pumpára?
        pipeIn = in;
        pipeOut = out;
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

        // TODO: Sorsoljunk hogy törjük vagy ne törjük el a pumpát

        if (pipeIn != null) {
            int drained = pipeIn.drain(maxVolume - currentVolume);
            addVolume(drained);
        }

        if (pipeOut != null) {
            int pushedOut = pipeOut.flow(currentVolume);
            decreaseVolume(pushedOut);
        }
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
