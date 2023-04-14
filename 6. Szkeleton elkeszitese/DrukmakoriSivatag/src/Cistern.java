public class Cistern extends Field implements Tickable{
    private int drainedWater;
    private boolean pipeAvailable;
    public void addDrainedWater(int amount){

    }
    public boolean addPlayer(Player p){
        return false;
    }

    public Pipe takePipe(){
        return null;
    }
    public Pump takePump(){
        return null;
    }

    public int getDrainedWater(){
        return 0;
    }

    public String GetClass(){
        return "Cistern";
    }

    @Override
    public void tick() {

    }
}
