import java.util.ArrayList;
import java.util.List;

public class Pipe extends Field {
    private boolean isBroken;
    private final int maxVolume;
    private int currentVolume;
    private int wastedWater;

    private List<FieldNode> ends;

    /**
     * Creates a new pipe.
     * 
     * @param maxVolume The maximum amount of water that the pipe can store.
     */
    public Pipe(int maxVolume) {
        this.isBroken = false;
        this.currentVolume = 0;
        this.wastedWater = 0;
        this.maxVolume = maxVolume;
        this.ends = new ArrayList<>();
    }

    /**
     * Breaks the pipe so that all the water flows out of it.
     */
    public void breakPipe() {
        isBroken = true;
        wastedWater += currentVolume;
        currentVolume = 0;
    }

    /**
     * Repairs the pipe so that water can flow through it again.
     */
    public void repair() {
        isBroken = false;
    }

    /**
     * Fills the pipe with water.
     * 
     * @param amount The amount of water that we fill the pipe with.
     * @return The amount of water that was successfully flown into the pipe.
     */
    public int flow(int amount) {
        int consumed = Math.min(maxVolume - currentVolume, amount);
        if (isBroken) {
            wastedWater += consumed;
            return consumed;
        }
        currentVolume += consumed;
        return consumed;
    }

    /**
     * Drains the water from the pipe.
     * 
     * @param amount The amount of water that we want to drain.
     * @return The amount of water that was successfully drained from the pipe.
     */
    public int drain(int amount) {
        int drained = Math.min(currentVolume, amount);
        currentVolume -= drained;
        return drained;
    }

    @Override
    public boolean addPlayer(Player p) {
        boolean hasPlayer = Skeleton.yesNoQuestion("Van már játékos a csövön?");
        if (hasPlayer) {
            return false;
        }
        return super.addPlayer(p);
    }

    /**
     * Sets the specified node as one of the pipe's end.
     * 
     * @param n The specified Node to connect.
     * @return Returns whether the pipe could be connected to the Node.
     */
    public boolean connect(FieldNode n) {
        if (2 <= ends.size()) {
            return false;
        }
        return ends.add(n);
    }

    /**
     * Removes the specified node from the pipe's ends.
     * 
     * @param n The specified Node to disconnect.
     * @return Returns whether the operation was successful.
     */
    public boolean disconnect(FieldNode n) {
        boolean hasPlayer = Skeleton.yesNoQuestion("Van már játékos a csövön?");
        boolean hasWater = Skeleton.yesNoQuestion("Van-e víz a csőben?");

        if (hasPlayer || hasWater) {
            return false;
        }
        return ends.remove(n);
    }

    /**
     * Returns the amount of water that is in the pipe.
     */
    public int getVolume() {
        return currentVolume;
    }

    /**
     * Returns the amount of water that can still flow into the pipe.
     */
    public int getCapacity() {
        return maxVolume - currentVolume;
    }

    public List<FieldNode> getEnds() {
        return ends;
    }

    /**
     * Cuts the pipe in half, producing a new pipe.
     * 
     * @return Returns the newly created pipe or if the cut could not be made null.
     */
    public Pipe cut() {
        boolean hasPlayer = Skeleton.yesNoQuestion("Van már játékos a csövön?");
        boolean hasWater = Skeleton.yesNoQuestion("Van-e víz a csőben?");

        if (ends.size() != 2 || hasPlayer || hasWater) {
            return null;
        }

        FieldNode node = this.ends.get(0);
        Pipe newPipe = new Pipe(maxVolume);

        Skeleton.callFunction(node, "node", "Disconnect");
        node.disconnect(this);
        Skeleton.endFunction();

        Skeleton.callFunction(this, "pipe", "Disconnect");
        this.disconnect(node);
        Skeleton.endFunction();

        Skeleton.callFunction(newPipe, "newPipe", "Connect");
        newPipe.connect(node);
        Skeleton.endFunction();

        Skeleton.callFunction(node, "node", "Connect");
        node.connect(newPipe);
        Skeleton.endFunction();

        return newPipe;
    }
}
