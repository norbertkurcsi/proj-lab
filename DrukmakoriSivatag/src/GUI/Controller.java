package GUI;

import proto.*;
import proto.Spring;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Controller {
    public static String assetsPath = "drukmakor_assets/";
    public static Controller instance = new Controller();
    private Window window = new Window();
    public HashMap<Player, Viewable> players = new HashMap<>();
    public HashMap<Field, Viewable> fields = new HashMap<>();

    public Player selectedPlayer = null;
    public List<Field> selectedFields = new ArrayList<>();

    public void selectPlayer(Player selected) {
        selectedPlayer = selected;
        selectedFields.clear();
        window.updateActions();
    }

    public boolean isFieldSelected(Field field) {
        return fields.containsKey(field);
    }

    public boolean isPlayerSelected(Player player) {
        return player.equals(selectedPlayer);
    }

    public void selectField(Field selected) {
        boolean removed = selectedFields.remove(selected);
        if (!removed) {
            selectedFields.add(selected);
        }
        window.updateActions();
    }

    public void breakPipe() {
        Pipe pipe = (Pipe) selectedPlayer.getPosition();
        selectedPlayer.breakPipe(pipe);
        endAction();
    }

    public void fixPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.fixPipe((Pipe) mechanic.getPosition());
        endAction();
    }

    public void fixPump() {
        if (selectedPlayer == null)
            return;
        Pump pump = (Pump) selectedPlayer.getPosition();
        ((Mechanic) selectedPlayer).fixPump(pump);
        endAction();
    }

    public void movePlayer() {
        selectedPlayer.moveTo(selectedFields.get(0));
        endAction();
    }

    public void changeFlow() {
        Pump pump = (Pump) selectedPlayer.getPosition();
        selectedPlayer.setPumpDirection(pump, (Pipe) selectedFields.get(0), (Pipe) selectedFields.get(1));
        endAction();
    }

    public void makeSticky() {
        Pipe pipe = (Pipe) selectedPlayer.getPosition();
        selectedPlayer.makeSticky(pipe);
        endAction();
    }

    public void makeSlippery() {
        Saboteur saboteur = (Saboteur) selectedPlayer;
        saboteur.makeSlippery((Pipe) saboteur.getPosition());
        endAction();
    }

    public void connectPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.connectPipe((Pipe)selectedPlayer.getPosition(), (FieldNode) selectedFields.get(0));
        endAction();
    }

    public void disconnectPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.disconnectPipe((Pipe) selectedPlayer.getPosition(), (FieldNode) selectedFields.get(0));
        endAction();
    }

    public void pickupPump() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.pickupPump();
        endAction();
    }

    public void pickupPipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        mechanic.pickupPipe();
        endAction();
    }

    public void placePump() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        Pipe pipe = (Pipe) mechanic.getPosition();
        Pump pump = mechanic.getPump();
        Pipe newPipe = mechanic.placePump(pump, pipe);
        PumpView pumpView = new PumpView(new Point(fields.get(pipe).getPosition()), pump);
        addField(pump, pumpView);
        PipeView newPipeView = new PipeView(newPipe);
        addField(newPipe, newPipeView);
        endAction();
    }

    public void placePipe() {
        Mechanic mechanic = (Mechanic) selectedPlayer;
        Pipe pipe = mechanic.getPipe();
        mechanic.placePipe((FieldNode)mechanic.getPosition());
        PipeView pipeView = new PipeView(pipe);
        addField(pipe, pipeView);
        endAction();
    }

    private void tick() {
        // Contains the fields that we need to tick
        Queue<FieldNode> toSee = new LinkedList<>();

        // Gets the springs and adds them to be the first fields that get ticked
        for (Field field : fields.keySet()) {
            if (field instanceof Spring)
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

    public void addField(Field field, Viewable view) {
        fields.put(field, view);
        window.addViewable(view);
    }

    public void addPlayer(Player player, Viewable view) {
        players.put(player, view);
        window.addViewable(view);
    }

    private void endAction() {
        tick();
        selectedPlayer = null;
        selectedFields.clear();
        window.updateAllViews();
        window.updateActions();
    }

    // Kezdo palya felepitese
    public void initModel() {
        // m - model, v - view
        Spring mSpring = new Spring();
        Cistern mCistern = new Cistern();

        Pipe mPipe1 = new Pipe();
        Pipe mPipe2 = new Pipe();
        Pipe mPipe3 = new Pipe();
        Pump mPump1 = new Pump();
        Pump mPump2 = new Pump();

        mPipe1.connect(mPump1);
        mPipe1.connect(mPump2);
        mPump1.connect(mPipe1);
        mPump2.connect(mPipe1);

        mPipe2.connect(mSpring);
        mSpring.connect(mPipe2);
        mPipe2.connect(mPump1);
        mPump1.connect(mPipe2);

        mPipe3.connect(mCistern);
        mPipe3.connect(mPump2);
        mCistern.connect(mPipe3);
        mPump2.connect(mPipe3);

        PumpView vPump1 = new PumpView(new Point(100, 300), mPump1);
        PumpView vPump2 = new PumpView(new Point(550, 350), mPump2);
        addField(mPump1, vPump1);
        addField(mPump2, vPump2);

        SpringView vSpring = new SpringView(new Point(300, 30), mSpring);
        CisternView vCistern = new CisternView(new Point(400, 450), mCistern);
        addField(mSpring, vSpring);
        addField(mCistern, vCistern);

        PipeView vPipe1 = new PipeView(mPipe1);
        addField(mPipe1, vPipe1);
        PipeView vPipe2 = new PipeView(mPipe2);
        addField(mPipe2, vPipe2);
        PipeView vPipe3 = new PipeView(mPipe3);
        addField(mPipe3, vPipe3);

        Mechanic mMech1 = new Mechanic();
        mMech1.moveTo(mPump1);
        MechanicView vMech1 = new MechanicView(mMech1);

        Saboteur mSab1 = new Saboteur();
        mSab1.moveTo(mPump2);
        SaboteurView vSab1 = new SaboteurView(vPump2.getPosition(), mSab1);

        // FOR TEST
        Pipe mPipe4 = new Pipe();
        mPipe4.connect(mPump1);
        mPipe4.connect(mCistern);
        mPump1.connect(mPipe4);
        mCistern.connect(mPipe4);
        PipeView vPipe4 = new PipeView(mPipe4);
        addField(mPipe4, vPipe4);
        //---

        addPlayer(mSab1, vSab1);
        addPlayer(mMech1, vMech1);
    }

    public static void main(String args[]) {
        Controller.instance.initModel();
    }
}
