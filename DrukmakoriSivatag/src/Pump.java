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
        Skeleton.callFunction(this, "breakPump", null);
        Skeleton.endFunction();
    }

    public void addVolume(Integer amount) {
        Skeleton.callFunction(this, "addVolume", new Object[] { amount });
        Skeleton.endFunction();
    }

    public void decreaseVolume(Integer amount) {
        Skeleton.callFunction(this, "decreaseVolume", new Object[] { amount });
        Skeleton.endFunction();
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
        boolean isBroken = Skeleton.yesNoQuestion("Would you like to break the pump?");
        if (isBroken) {
            breakPump();
            Skeleton.endFunction();
            return;
        }

        Integer amount = Skeleton.numberQuestion("How much do you want to pump?");
        Skeleton.names.put(amount, "amount");
        Integer drainedAmount = 0;
        if(pipeIn != null) {
            drainedAmount = pipeIn.drain(amount);
            Skeleton.names.put(drainedAmount, "drainedAmount");
            addVolume(drainedAmount);
        }

        Integer currentVolume = Integer.valueOf(drainedAmount);
        Skeleton.names.put(currentVolume, "currentVolume");
        if(pipeOut != null) {
            Integer flowAmount  = pipeOut.flow(currentVolume);
            Skeleton.names.put(flowAmount, "flowAmount");
            decreaseVolume(flowAmount);
        }

        Skeleton.endFunction();
    }

}
