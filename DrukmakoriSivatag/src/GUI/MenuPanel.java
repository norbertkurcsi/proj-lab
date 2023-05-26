package GUI;

import proto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MenuPanel extends JPanel {

    private JPanel inventoryPanel;
    private JPanel actionPanel;

    private JSplitPane splitPanel;

    public MenuPanel() {
        super();
        setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5));
        setPreferredSize(getMinimumSize());
        inventoryPanel = new JPanel();
        actionPanel = new JPanel();
        actionPanel.setBackground(Color.GREEN);
//        actionPanel.setBounds(0,0, getWidth()/2, getHeight());
        inventoryPanel.setBackground(Color.CYAN);
//        inventoryPanel.setBounds(getWidth() / 2, 0, getWidth(), getHeight());
        splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, actionPanel, inventoryPanel);
        splitPanel.setBounds(0, 0, getWidth(), getHeight());
        setLayout(new BorderLayout());
        add(splitPanel, BorderLayout.CENTER);
        initializeActions();
    }

    private HashMap<String, JButton> actions = new HashMap<>();

    public void updateActions() {
        // Deaktiváljuk az összes akciót
        disableAllActions();
        if (Controller.instance.selectedPlayer == null) {
            return;
        }
        Player player = Controller.instance.selectedPlayer;
        Field position = player.getPosition();
        // Ha a játékos csövön áll
        if (position instanceof Pipe) {
            // Ha a cső nem ragadós, azzá lehet tenni
            if (!((Pipe) position).isSticky())
                actions.get("MAKESTICKY").setEnabled(true);
            // Ha a cső nem törött, el lehet törni
            if (!((Pipe) position).isBroken())
                actions.get("BREAKPIPE").setEnabled(true);
        }
        // Ha ki van választva egy field, oda lehet lépni
        if (Controller.instance.selectedFields.size() == 1) {
            Field selected = Controller.instance.selectedFields.get(0);
            //TODO csak szomszedra tudjon lepni
            actions.get("MOVE").setEnabled(true);
        }
        // Ha ki van választva két field
        if (Controller.instance.selectedFields.size() == 2) {
            Field selected0 = Controller.instance.selectedFields.get(0);
            Field selected1 = Controller.instance.selectedFields.get(1);
            // Ha a kiválasztott fieldek csövek, és pumpán állunk, át lehet állítani
            if (selected0 instanceof Pipe && selected1 instanceof Pipe && player.getPosition() instanceof Pump) {
                // TODO kell nézni, hogy a pumpára vannak-e csatlakoztatva a csövek
                actions.get("CHANGEFLOW").setEnabled(true);
            }
        }
        // Ha a kiválasztott játékos mechanic
        if (player instanceof Mechanic) {
            if (position instanceof Cistern) {
                // Ha ciszternán áll, mindig fel tud venni pumpát
                actions.get("PICKUPPUMP").setEnabled(true);
                // Ha van cső, azt is fel tud venni
                if (((Cistern) position).isPipeAvailable()) actions.get("PICKUPPIPE").setEnabled(true);
            }
            // Ha csövön áll
            if (position instanceof Pipe) {
                // Ha van pumpája, le tudja tenni
                if (((Mechanic) player).getPump() != null)
                    actions.get("PLACEPUMP").setEnabled(true);
                // Ha el van törve, meg tudja javítani
                if (((Pipe) position).isBroken())
                    actions.get("FIXPIPE").setEnabled(true);
            }
            // Ha pumpán áll, és el van törve, meg tudja javítani
            if (position instanceof Pump && ((Pump) position).isBroken()) {
                actions.get("FIXPUMP").setEnabled(true);
            }
            // Ha FieldNodeon áll, és van csöve, le tudja tenni
            if (position instanceof FieldNode && ((Mechanic) player).getPipe() != null) {
                actions.get("PLACEPIPE").setEnabled(true);
            }
            // Ha ki van választva két field
            if (Controller.instance.selectedFields.size() == 2) {
                Field selected0 = Controller.instance.selectedFields.get(0);
                Field selected1 = Controller.instance.selectedFields.get(1);
                // Egy csövet tud fel- vagy lecsatlakoztatni egy FieldNodehoz
                if (selected0 instanceof Pipe && selected1 instanceof FieldNode) {
                    //TODO if( megnezni, h csatlakozva vannak-e )
                    //HA NEM
                    actions.get("CONNECT").setEnabled(true);
                    //HA IGEN
                    actions.get("DISCONNECT").setEnabled(true);
                }
            }
            return;
        }
        // Ha a kiválasztott játékos saboteur, Pipeon áll és az nem csúszós, azzá teheti
        if (player instanceof Saboteur && position instanceof Pipe && !((Pipe) position).isSlippery()) {
            actions.get("MAKESLIPPERY");
        }
    }

    private void disableAllActions() {
        for (Map.Entry<String, JButton> action : actions.entrySet()) {
            action.getValue().setEnabled(false);
        }
    }

    private void initializeActions() {
        JButton moveButton = new JButton("MOVE");
        actions.put("MOVE", moveButton);
        moveButton.addActionListener((ActionEvent e) -> {
            Controller.instance.movePlayer();
        });
        actionPanel.add(moveButton);

        JButton fixPipe = new JButton("FIXPIPE");
        actions.put("FIXPIPE", fixPipe);
        fixPipe.addActionListener((ActionEvent e) -> {
            Controller.instance.fixPipe();
        });
        actionPanel.add(fixPipe);

        JButton fixPump = new JButton("FIXPUMP");
        actions.put("FIXPUMP", fixPump);
        fixPump.addActionListener((ActionEvent e) -> {
            Controller.instance.fixPump();
        });
        actionPanel.add(fixPump);

        JButton breakPipe = new JButton("BREAKPIPE");
        actions.put("BREAKPIPE", breakPipe);
        breakPipe.addActionListener((ActionEvent e) -> {
            Controller.instance.breakPipe();
        });
        actionPanel.add(breakPipe);

        JButton changeFlow = new JButton("CHANGEFLOW");
        actions.put("CHANGEFLOW", changeFlow);
        changeFlow.addActionListener((ActionEvent e) -> {
            Controller.instance.changeFlow();
        });
        actionPanel.add(changeFlow);

        JButton makeSticky = new JButton("STICKY");
        actions.put("MAKESTICKY", makeSticky);
        makeSticky.addActionListener((ActionEvent e) -> {
            Controller.instance.makeSticky();
        });
        actionPanel.add(makeSticky);

        JButton makeSlippery = new JButton("SLIPPERY");
        actions.put("MAKESLIPPERY", makeSlippery);
        makeSlippery.addActionListener((ActionEvent e) -> {
            Controller.instance.makeSlippery();
        });
        actionPanel.add(makeSlippery);

        JButton connectPipe = new JButton("CONNECT");
        actions.put("CONNECT", connectPipe);
        connectPipe.addActionListener((ActionEvent e) -> {
            Controller.instance.connectPipe();
        });
        actionPanel.add(connectPipe);

        JButton disconnectPipe = new JButton("DISCONNECT");
        actions.put("DISCONNECT", disconnectPipe);
        disconnectPipe.addActionListener((ActionEvent e) -> {
            Controller.instance.disconnectPipe();
        });
        actionPanel.add(disconnectPipe);

        JButton pickupPump = new JButton("PICKUPPUMP");
        actions.put("PICKUPPUMP", pickupPump);
        pickupPump.addActionListener((ActionEvent e) -> {
            Controller.instance.pickupPump();
        });
        actionPanel.add(pickupPump);

        JButton pickupPipe = new JButton("PICKUPPIPE");
        actions.put("PICKUPPIPE", pickupPipe);
        pickupPipe.addActionListener((ActionEvent e) -> {
            Controller.instance.pickupPipe();
        });
        actionPanel.add(pickupPipe);
        disableAllActions();
    }
}
