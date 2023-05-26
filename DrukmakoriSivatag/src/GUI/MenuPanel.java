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
        this.setLayout(new GridLayout(2, 3));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);

        actionPanel.setPreferredSize(new Dimension(Window.WIDTH / 3, Window.HEIGHT / 5 ));
        actionPanel.setMinimumSize(getPreferredSize());
        inventoryPanel.setPreferredSize(new Dimension(Window.WIDTH / 3, Window.HEIGHT / 5 ));
        inventoryPanel.setMinimumSize(getPreferredSize());
        scorePanel.setPreferredSize(new Dimension(Window.WIDTH / 3, Window.HEIGHT / 5 ));
        scorePanel.setMinimumSize(getPreferredSize());

        JLabel actionLabel = new JLabel("Actions");
        actionLabel.setFont(new Font("SignPainter", Font.PLAIN, 35));
        JLabel inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setFont(new Font("SignPainter", Font.PLAIN, 35));
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("SignPainter", Font.PLAIN, 35));

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

    @Override
    public void paintComponent(Graphics g) {
        Window.getGraphics2D(g).drawImage(background, -10, -10, getWidth() + 50, getHeight() + 20, null);
    }
}
