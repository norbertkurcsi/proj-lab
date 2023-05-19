package GUI;

import proto.*;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    public static Window window;

    public static HashMap<Viewable, Object> views = new HashMap<>();

    private static Viewable selectedPlayerView = null;
    private static Action selectedAction = null;
    private static Viewable selectedMapElement = null;

    public static Viewable getViewOfObject(Object o) {
        if(!views.containsValue(o)) return null;
        for(Map.Entry<Viewable, Object> e : views.entrySet()) {
            if(e.getValue().equals(o)) return e.getKey();
        }
        return null;
    }

    public static void selectPlayerView(Viewable selected) {
        selectedPlayerView = selected;
    }

    public static void selectMapElement(Viewable selected) {
        selectedMapElement = selected;
    }

    public static void movePlayer() {
        if(selectedPlayerView == null || selectedMapElement == null) {
            return;
        }
        Player p = (Player)selectedPlayerView.getModelObject();
        Field f = (Field)selectedMapElement.getModelObject();
        p.moveTo(f);
        selectedPlayerView = null;
        selectedMapElement = null;
        window.updateAllViews();
    }

    public static void changeFlow() {
        if(selectedPlayerView == null || selectedMapElement == null) {
            return;
        }
        // TODO
        Player p = (Player)selectedPlayerView.getModelObject();

        selectedPlayerView = null;
        selectedMapElement = null;
        window.updateAllViews();
    }

    public static void fixPump() {


        selectedPlayerView = null;
        selectedMapElement = null;
        window.updateAllViews();
    }

    public static void breakPipe() {
        if(selectedPlayerView == null) return;
        Player p = (Player)selectedPlayerView.getModelObject();
        p.breakPipe((Pipe)p.getPosition());

        selectedPlayerView = null;
        selectedMapElement = null;
        window.updateAllViews();
    }

    public static void fixPipe() {
        if(selectedPlayerView == null) return;
        Mechanic m = (Mechanic) selectedPlayerView.getModelObject();
        m.fixPipe((Pipe)m.getPosition());

        selectedPlayerView = null;
        selectedMapElement = null;
        window.updateAllViews();
    }

    // Kezdo palya felepitese
    public static void initModel() {
        // m - model, v - view
        Pump mPump1 = new Pump();
        Pump mPump2 = new Pump();
        Pipe mPipe1 = new Pipe();
        mPipe1.connect(mPump1);
        mPipe1.connect(mPump2);
        mPump1.connect(mPipe1);
        mPump2.connect(mPipe1);


        PumpView vPump1 = new PumpView(new FieldPosition(100, 100), mPump1);
        PumpView vPump2 = new PumpView(new FieldPosition(450, 400), mPump2);
        PipeView vPipe1 = new PipeView(vPump1.getPosition(), vPump2.getPosition(), mPipe1);

        Mechanic mMech1 = new Mechanic();
        mMech1.moveTo(mPump1);
        MechanicView vMech1 = new MechanicView(vPump1.getPosition(), mMech1);

        Saboteur mSab1 = new Saboteur();
        mSab1.moveTo(mPump2);
        SaboteurView vSab1 = new SaboteurView(vPump2.getPosition(), mSab1);

        views.put(vSab1, mSab1);
        views.put(vMech1, mMech1);
        views.put(vPipe1, mPipe1);
        views.put(vPump1, mPump1);
        views.put(vPump2, mPump2);

    }

    public static void main(String args[]) {
        initModel();
        window = new Window();
    }
}
