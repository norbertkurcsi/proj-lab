package GUI;

import javax.swing.*;

import GUI.actions.*;
import proto.Mechanic;
import proto.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private static Image background = new ImageIcon(Controller.assetsPath + "panel.png").getImage();
    private static Image pumpImage = new ImageIcon(Controller.assetsPath + "pump.png").getImage();
    private static Image pipeImage = new ImageIcon(Controller.assetsPath + "pipe.png").getImage();
    private static Image mechanicImage = new ImageIcon(Controller.assetsPath + "mechanic.png").getImage();
    private static Image saboteurImage = new ImageIcon(Controller.assetsPath + "saboteur.png").getImage();

    private JPanel actionPanel = new JPanel();
    private JPanel inventoryPanel = new JPanel();
    private JPanel scorePanel = new JPanel();

    public MenuPanel() {
        super();

        this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5));
        this.setPreferredSize(getMinimumSize());
        this.setLayout(new GridLayout(2, 3));

        actionPanel.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5 ));
        actionPanel.setMinimumSize(getPreferredSize());
        inventoryPanel.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5 ));
        inventoryPanel.setMinimumSize(getPreferredSize());
        scorePanel.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5 ));
        scorePanel.setMinimumSize(getPreferredSize());

        JLabel actionLabel = new JLabel("          Actions");
        JLabel inventoryLabel = new JLabel("        Inventory");
        JLabel scoreLabel = new JLabel("          Score");

        //Font beallitas
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Chalkduster.ttf")).deriveFont(30f);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            actionLabel.setFont(customFont);
            inventoryLabel.setFont(customFont);
            scoreLabel.setFont(customFont);
        } catch (IOException|FontFormatException e) {
            throw new RuntimeException(e);
        }
        actionPanel.add(new MoveButton());
        actionPanel.add(new FixPipeButton());
        actionPanel.add(new FixPumpButton());
        actionPanel.add(new BreakPipeButton());
        actionPanel.add(new ChangeFlowButton());
        actionPanel.add(new MakeStickyButton());
        actionPanel.add(new MakeSlipperyButton());
        actionPanel.add(new ConnectPipeButton());
        actionPanel.add(new DisconnectPipeButton());
        actionPanel.add(new PickupPumpButton());
        actionPanel.add(new PickupPipeButton());
        actionPanel.add(new PlacePipeButton());
        actionPanel.add(new PlacePumpButton());
        JButton inventoryPump = new JButton(){
            protected void paintComponent(Graphics g) {
                Player player = Controller.instance.selectedPlayer;
                if (!(player instanceof Mechanic))
                    return;
                if(((Mechanic) player).getPump() != null){
                    Window.getGraphics2D(g).drawImage(pumpImage, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
                }
            }
        };
        JButton inventoryPipe = new JButton() {
            protected void paintComponent(Graphics g) {
                Player player = Controller.instance.selectedPlayer;
                if (!(player instanceof Mechanic))
                    return;
                if(((Mechanic) player).getPipe() != null){
                    Window.getGraphics2D(g).drawImage(pipeImage, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
                }
            }
        };
        inventoryPanel.add(inventoryPump);
        inventoryPanel.add(inventoryPipe);

        JButton scoreMechanic = new JButton() {
            protected void paintComponent(Graphics g) {
                    Window.getGraphics2D(g).drawImage(mechanicImage, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
            }
        };
        JButton scoreSaboteur = new JButton() {
            protected void paintComponent(Graphics g) {
                Window.getGraphics2D(g).drawImage(saboteurImage, 0, 0, Window.BUTTONSIZE - 10, Window.BUTTONSIZE - 10, null);
            }
        };
        //TODO hozzaadni a pontszamokat
        scorePanel.add(scoreMechanic);
        scorePanel.add(scoreSaboteur);


        this.setOpaque(false);
        actionPanel.setOpaque(false);
        inventoryPanel.setOpaque(false);
        scorePanel.setOpaque(false);
        this.add(actionLabel);
        this.add(inventoryLabel);
        this.add(scoreLabel);
        this.add(actionPanel);
        this.add(inventoryPanel);
        this.add(scorePanel);

    }

    public void updateActions() {
        for (Component component : actionPanel.getComponents()) {
            if (!(component instanceof ActionButton))
                continue;

            ActionButton btn = (ActionButton) component;
            boolean canPerf = btn.canPerform();
            btn.setEnabled(canPerf);
            btn.setVisible(canPerf);

        }
    }

    public void updateInventory() {
        inventoryPanel.validate();
        inventoryPanel.repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        Window.getGraphics2D(g).drawImage(background, -10, -10, getWidth() + 50, getHeight() + 20, null);
    }
}
