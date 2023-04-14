public class Pump extends Field implements Tickable{
    private boolean isBroken;
    private int maxVolume;
    private int currentVolume;

    public Pump(int maxVolume) {
        this.isBroken = false;
        this.maxVolume = maxVolume;
        this.currentVolume = 0;
    }
    public void breakPump() {

    }

    public int addVolume(int amount) {
        if((this.currentVolume + amount) > maxVolume)
            throw new RuntimeException("Meghaladtad a maximális mennyiséget!");
        return this.currentVolume + amount;
    }
    public int decreaseVolume(int amount) {
        return this.currentVolume - amount;
    }
    public void changeFlow(Pipe in, Pipe out) {

    }
    public void repair() {

    }
    public boolean addPlayer(Player p) {
        return false;
    }
    public void tick() {

    }
}
