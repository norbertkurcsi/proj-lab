public class Cistern extends FieldNode implements Tickable{
    private int drainedWater;
    private boolean pipeAvailable;
    public void addDrainedWater(int amount){

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
        Skeleton.callFunction(this, "tick", null);
        Skeleton.endFunction();
    }
}
