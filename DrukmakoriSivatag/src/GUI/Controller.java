package GUI;

import proto.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
