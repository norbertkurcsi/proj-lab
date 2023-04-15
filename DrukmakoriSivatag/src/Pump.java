public class Pump extends FieldNode implements Tickable {
    private Pipe pipeIn;
    private Pipe pipeOut;
    private boolean isBroken;
    private int maxVolume;
    private int currentVolume;

    /**
     * Konstruktor
     */
    public Pump() {
        Skeleton.callFunction(this, "create", null);
        Skeleton.endFunction();
    }

    /**
     * A pumpa meghibásodik.
     */
    public void breakPump() {
        ;
        Skeleton.callFunction(this, "breakPump", null);
        Skeleton.endFunction();
    }

    public int addVolume(Integer amount) {
        Skeleton.callFunction(this, "addVolume", new Object[] { amount });
        int volume = Skeleton.numberQuestion("How much will be the current volume?");
        Skeleton.endFunction();
        return volume;
    }

    public int decreaseVolume(int amount) {
        Skeleton.callFunction(this, "decreaseVolume", new Object[] { amount });
        int volume = Skeleton.numberQuestion("How much will be the current volume?");
        Skeleton.endFunction();
        return volume;
    }

    public void changeFlow(Pipe in, Pipe out) {
        pipeIn = in;
        pipeOut = out;
        Skeleton.callFunction(this, "changeFlow", new Object[] { in, out });
        Skeleton.endFunction();
    }

    public void repair() {
        Skeleton.callFunction(this, "repair", null);
        Skeleton.endFunction();
    }

    public void tick() {
        Skeleton.callFunction(this, "tick", null);
        boolean isBroken = Skeleton.yesNoQuestion("Is the pump broken?");
        if (isBroken) {
            Skeleton.endFunction();
            return;
        }

        int amountDrain = Skeleton.numberQuestion("How much do you want to pump?");
        int gotWater = pipeIn.drain(amountDrain);
        pipeOut.flow(gotWater);

        Skeleton.endFunction();
    }

}
