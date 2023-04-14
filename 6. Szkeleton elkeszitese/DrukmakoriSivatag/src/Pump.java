public class Pump extends FieldNode implements Tickable{
    private Pipe pipeIn;
    private Pipe pipeOut;
    private boolean isBroken;
    private int maxVolume;
    private int currentVolume;

    /**
     * Konstruktor
     * @param maxVolume - a pumpa maximális kapacítása
     */
    public Pump(int maxVolume) {
        this.isBroken = false;
        this.maxVolume = maxVolume;
        this.currentVolume = 0;
    }

    /**
     * A pumpa meghibásodik.
     */
    public void breakPump() {
        this.isBroken = true;
    }

    public int addVolume(int amount) {
        if((this.currentVolume + amount) > maxVolume) {
            throw new RuntimeException("Meghaladtad a maximális mennyiséget!");
        }
        return this.currentVolume + amount;
    }
    public int decreaseVolume(int amount) {
        return this.currentVolume - amount;
    }
    public void changeFlow(Pipe in, Pipe out) {

    }
    public void repair() {
        this.isBroken = true;
    }
    public boolean addPlayer(Player p) {
        return false;
    }
    public void tick() {

    }

}
