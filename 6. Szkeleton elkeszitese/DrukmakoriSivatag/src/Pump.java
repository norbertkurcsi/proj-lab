public class Pump extends Field implements Tickable{
    private boolean isBroken;
    private int maxVolume;
    private int currentVolume;

    public void breakPump() {

    }

    public int addVolume(int amount) {
        return 0;
    }
    public int decreaseVolume(int amount) {
        return 0;
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
