public class Cistern extends FieldNode implements Tickable {
    private int drainedWater;
    private boolean pipeAvailable;

    public void addDrainedWater(int amount) {
        Skeleton.callFunction(this, "addDrainedWater", new Object[] { amount });
        // drainedWater += amount;
        Skeleton.endFunction();
    }

    public Pipe takePipe() {
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

    public Pump takePump() {
        Skeleton.callFunction(this, "takePump", null);
        boolean pumpAvailable = Skeleton.yesNoQuestion("Were there available pumps?");
        if (pumpAvailable) {
            int volume = Skeleton.numberQuestion("Enter the pump's maximum volume");
            Skeleton.endFunction();
            Pump newPump = new Pump(volume);
            Skeleton.names.put(newPump, "newPump");
            return newPump;
        }
        Skeleton.endFunction();
        return null;
    }

    public int getDrainedWater() {
        return drainedWater;
    }

    @Override
    public void tick() {
        Skeleton.callFunction(this, "tick", null);
        for (Pipe pipe : pipes) {
            addDrainedWater(pipe.drain(Integer.MAX_VALUE));
        }
        Skeleton.endFunction();
    }
}
