/**
 * A ciszternákért felelős osztály
 */
public class Cistern extends FieldNode implements Tickable{
    private int drainedWater;
    private boolean pipeAvailable;

    /**
     * Konstruktor
     * A ciszternába befolyt vizet (drainedWater) 0-ra inicalizálja.
     * A cső elérhatőségét (pumpAvailable) hamisra állítja. Csövet nem vehet fel a Szerelő.
     */
    public Cistern(){
        Skeleton.callFunction(this, "create", null);
        drainedWater = 0;
        pipeAvailable = false;
        Skeleton.endFunction();
    }

    /**
     * Függvény, ami a ciszternába folyt vizet növeli a paraméterként átadott értékkel.
     * @param amount az az int mennyiség, amivel növeli a ciszternába folyt vizet.
     */
    public void addDrainedWater(int amount) {
        Skeleton.callFunction(this, "addDrainedWater", new Object[] { amount });
        // drainedWater += amount;
        Skeleton.endFunction();
    }

    /**
     *{inheritDoc}
     */
    @Override
    public Pipe takePipe(){
        Skeleton.callFunction(this, "takePipe", null);
        pipeAvailable = Skeleton.yesNoQuestion("Were there available pipes?");
        if (pipeAvailable) {
            // int volume = Skeleton.numberQuestion("Enter the pipe's maximum volume");
            Skeleton.endFunction();
            Pipe newPipe = new Pipe();
            Skeleton.names.put(newPipe, "newPipe");
            return newPipe;
        }
        Skeleton.endFunction();
        return null;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public Pump takePump(){
        Skeleton.callFunction(this, "takePump", null);
        boolean pumpAvailable = Skeleton.yesNoQuestion("Were there available pumps?");
        if (pumpAvailable) {
            int volume = Skeleton.numberQuestion("Enter the pump's maximum volume");
            Skeleton.endFunction();
            Pump newPump = new Pump();
            Skeleton.names.put(newPump, "newPump");
            return newPump;
        }
        Skeleton.endFunction();
        return null;
    }

    /**
     * Függvény, ami lekérdezi a ciszternába folyt víz mennyiségét.
     * @return a ciszternába folyt víz mennyisége.
     */
    public int getDrainedWater(){
        Skeleton.callFunction(this, "getDrainedWater()", null);
        Skeleton.endFunction();
        return drainedWater;
    }

    /**
     * Egy időegység elteltét jelenti.
     * Az időegységnek megfelelő mennyiségű víz elfolyik a ciszternához kapcsolt csővekből a ciszternába.
     * Ezzel a mennyiséggel nő a cisztárnába befolyt víz mennyisége.
     */
    @Override
    public void tick() {
        Skeleton.callFunction(this, "tick", null);
        for (Pipe pipe : pipes) {
            addDrainedWater(pipe.drain(Integer.MAX_VALUE));
        }
        Skeleton.endFunction();
    }
}
