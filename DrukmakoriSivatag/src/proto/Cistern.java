package proto;

/**
 * A ciszternákért felelős osztály
 */
public class Cistern extends FieldNode implements Tickable {
    private int drainedWater;
    private boolean pipeAvailable;

    // TODO: Nem tudom hogy ezt a pipe available-t jól csináltam-e

    /**
     * Konstruktor
     * A ciszternába befolyt vizet (drainedWater) 0-ra inicalizálja.
     * A cső elérhatőségét (pumpAvailable) hamisra állítja. Csövet nem vehet fel a
     * Szerelő.
     */
    public Cistern() {
        drainedWater = 0;
        pipeAvailable = false;
    }

    /**
     * Függvény, ami a ciszternába folyt vizet növeli a paraméterként átadott
     * értékkel.
     *
     * @param amount az az int mennyiség, amivel növeli a ciszternába folyt vizet.
     */
    void addDrainedWater(int amount) {
        drainedWater += amount;
    }

    /**
     * Egy cső felvevése a mezőről, ha nem megy akkor null értékkel tér vissza.
     *
     * @return az elvett cső vagy null
     */
    @Override
    public Pipe takePipe() {
        if (!pipeAvailable) {
            return null;
        }
        return new Pipe();
    }

    /**
     * Egy pumpa felvevése a mezőről, ha nem megy akkor null értékkel tér vissza.
     *
     * @return az elvett pumpa vagy null
     */
    @Override
    public Pump takePump() {
        // TODO: Pumpát mindig felvehet?
        Pump newPump = new Pump();
        return newPump;
    }

    /**
     * Függvény, ami lekérdezi a ciszternába folyt víz mennyiségét.
     *
     * @return a ciszternába folyt víz mennyisége.
     */
    public int getDrainedWater() {
        return drainedWater;
    }

    /**
     * Egy időegység elteltét jelenti.
     * Az időegységnek megfelelő mennyiségű víz elfolyik a ciszternához kapcsolt
     * csővekből a ciszternába.
     * Ezzel a mennyiséggel nő a cisztárnába befolyt víz mennyisége.
     */
    @Override
    public void tick() {
        // TODO: Minden tick után már van nála cső?
        pipeAvailable = true;

        for (Pipe pipe : pipes) {
            int drained = pipe.drain(Integer.MAX_VALUE);
            addDrainedWater(drained);
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

        return "Cistern " +
                Proto.findName(this) +
                " with ends: " +
                super.toString() +
                " pipe available: " +
                (pipeAvailable ? "true" : "false") +
                " drained water: " +
                getDrainedWater() +
                " standing players: " +
                playerList;
    }
}