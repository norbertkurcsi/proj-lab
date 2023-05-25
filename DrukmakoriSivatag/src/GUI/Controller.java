package GUI;

import proto.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Controller {
    public static Controller instance = new Controller();

    public static String assetsPath = "drukmakor_assets/";

    private Window window = new Window();
    public HashMap<Player, Viewable> players = new HashMap<>();
    public HashMap<Field, Viewable> fields = new HashMap<>();

    private Player selectedPlayer = null;
    private List<Field> selectedFields = new ArrayList<>();

    public void selectPlayer(Player selected) {
        selectedPlayer = selected;
        selectedFields.clear();
    }

    public void selectField(Field selected) {
        boolean removed = selectedFields.remove(selected);
        if (!removed) {
            selectedFields.add(selected);
        }
    }

    public void breakPipe() {
        Pipe pipe = (Pipe) selectedPlayer.getPosition();
        selectedPlayer.breakPipe(pipe);
        tick();
        window.updateAllViews();
    }

    public void connectPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.connectPipe((Pipe) selectedFields.get(0), (FieldNode) selectedFields.get(1));
        tick();
        window.updateAllViews();
    }

    public void disconnectPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.disconnectPipe((Pipe) selectedFields.get(0), (FieldNode) selectedFields.get(1));
        tick();
        window.updateAllViews();
    }

    public void fixPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.fixPipe((Pipe) mechanic.getPosition());
        tick();
        window.updateAllViews();
    }

    public void makeSlippery() {
        Saboteur saboteur = (Saboteur) selectedPlayer;
        saboteur.makeSlippery((Pipe) saboteur.getPosition());
        tick();
        window.updateAllViews();
    }

    public void makeSticky() {
        Pipe pipe = (Pipe) selectedPlayer.getPosition();
        selectedPlayer.makeSticky(pipe);
        tick();
        window.updateAllViews();
    }

    public void movePlayer() {
        selectedPlayer.moveTo(selectedFields.get(0));
        tick();
        window.updateAllViews();
    }

    public void pickupPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.pickupPipe();
        tick();
        window.updateAllViews();
    }

    public void placePipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.placePipe((FieldNode) selectedFields.get(0));
        tick();
        window.updateAllViews();
    }

    private void tick() {
        // Contains the fields that we need to tick
        Queue<FieldNode> toSee = new LinkedList<>();

        // Gets the springs and adds them to be the first fields that get ticked
        for (Field field : fields.keySet()) {
            if (field instanceof Cistern)
                toSee.add((FieldNode) field);
        }

        // Contains all of the fields that have 
        // been ticked so we don't tick a field twice
        Set<FieldNode> ticked = new HashSet<>();
        while (0 < toSee.size()) {
            FieldNode node = toSee.poll();
            node.tick();
            ticked.add(node);

            for (FieldNode neighbour : node.getConnectedNodes()) {
                if (!ticked.contains(neighbour)) {
                    toSee.add(neighbour);
                }
            }
        }

        // Tick all pipes
        for (Field field : fields.keySet()) {
            if (field instanceof Pipe)
                ((Pipe) field).tick();
        }
    }

    public void fixPump() {
        if(selectedPlayer == null) return;
        Pump pump = (Pump) selectedPlayer.getPosition();
        ((Mechanic)selectedPlayer).fixPump(pump);
        endAction();
    }

    public void pickupPump() {
        if(selectedPlayer == null) return;
        ((Mechanic)selectedPlayer).pickupPump();
        endAction();
    }

    public void placePump() {
        if(selectedPlayer != null && selectedFields != null && selectedFields.size() == 1) {
            Pipe pipe = (Pipe)selectedPlayer.getPosition();
            Pump pump = ((Mechanic)selectedPlayer).getPump();
            Pipe newPipe = ((Mechanic)selectedPlayer).placePump(pump, pipe);
            // TODO parameterek
            PipeView newPipeView = new PipeView(newPipe);
            fields.put(newPipe, newPipeView);
        }
        endAction();
    }

    public void changeFlow() {
        if(selectedPlayer != null && selectedFields != null && selectedFields.size() == 2) {
            Pump pump = (Pump) selectedPlayer.getPosition();
            selectedPlayer.setPumpDirection(pump, (Pipe) selectedFields.get(0), (Pipe) selectedFields.get(1));
        }
        endAction();
    }

    public void endAction() {
        tick();
        selectedPlayer = null;
        window.updateAllViews();
    };

    // Kezdo palya felepitese
    public void initModel() {
        // m - model, v - view
        // Pump mPump1 = new Pump();
        // Pump mPump2 = new Pump();
        // Pipe mPipe1 = new Pipe();
        // mPipe1.connect(mPump1);
        // mPipe1.connect(mPump2);
        // mPump1.connect(mPipe1);
        // mPump2.connect(mPipe1);

        // PumpView vPump1 = new PumpView(new FieldPosition(100, 100), mPump1);
        // PumpView vPump2 = new PumpView(new FieldPosition(450, 400), mPump2);
        // PipeView vPipe1 = new PipeView(vPump1.getPosition(), vPump2.getPosition(),
        // mPipe1);

        // Mechanic mMech1 = new Mechanic();
        // mMech1.moveTo(mPump1);
        // MechanicView vMech1 = new MechanicView(vPump1.getPosition(), mMech1);

        // Saboteur mSab1 = new Saboteur();
        // mSab1.moveTo(mPump2);
        // SaboteurView vSab1 = new SaboteurView(vPump2.getPosition(), mSab1);

        // views.put(vSab1, mSab1);
        // views.put(vMech1, mMech1);
        // views.put(vPipe1, mPipe1);
        // views.put(vPump1, mPump1);
        // views.put(vPump2, mPump2);
    }

    public static void main(String args[]) {
        Controller.instance.initModel();
    }
}
