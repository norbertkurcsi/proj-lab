package GUI;

import javax.swing.*;

import GUI.actions.*;

import java.awt.*;

public class MenuPanel extends JPanel {

    private static Image background = new ImageIcon(Controller.assetsPath + "panel.png").getImage();

    private JPanel actionPanel = new JPanel();
    private JPanel inventoryPanel = new JPanel();
    private JPanel scorePanel = new JPanel();

    public MenuPanel() {
        super();

        this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5));
        this.setPreferredSize(getMinimumSize());
        this.setLayout(new BorderLayout());

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
        actionPanel.setOpaque(false);

        this.add(actionPanel);
        // this.add(inventoryPanel, BorderLayout.CENTER);
        // this.add(scorePanel, BorderLayout.LINE_END);
    }

    public void updateActions() {
        for (Component component : actionPanel.getComponents()) {
            if (!(component instanceof ActionButton))
                continue;

            ActionButton btn = (ActionButton) component;
            btn.setEnabled(btn.canPerform());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Window.getGraphics2D(g).drawImage(background, -10, -10, getWidth() + 50, getHeight() + 20, null);
    }
}
