public class Cistern extends FieldNode implements Tickable{
    private int drainedWater;
    private boolean pipeAvailable;
    public void addDrainedWater(int amount){

    }

    public Pump takePump(){
        return null;
    }

    public int getDrainedWater(){
        return drainedWater;
    }

    public String GetClass(){
        return "Cistern";
    }

    @Override
    public void tick() {
        Skeleton.callFunction(this, "tick", null);
        for (Pipe pipe : pipes){
            addDrainedWater(pipe.drain(pipe.getVolume()));
        }
        Skeleton.endFunction();
    }
}
