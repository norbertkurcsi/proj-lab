package GUI.menu;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Controller;
import GUI.Window;
import GUI.actions.PlacePipeButton;
import GUI.actions.PlacePumpButton;
import proto.Mechanic;

public class InventoryPanel extends JPanel {
    private static Image pumpImage = new ImageIcon(Controller.assetsPath + "pump.png").getImage();
    private static Image pipeImage = new ImageIcon(Controller.assetsPath + "pipe.png").getImage();

    private PlacePipeButton pipeButton = new PlacePipeButton() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            g2d.drawImage(pipeImage, 0, 0, w, h, null);
            if (this.getModel().isRollover() || !isEnabled()) {
                g.setColor(new Color(0, 0, 0, 50));
                g.fillRoundRect(0, 0, w, h, 25, 25);
            }
        }
    };

    private PlacePumpButton pumpButton = new PlacePumpButton() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            g2d.drawImage(pumpImage, 0, 0, w, h, null);
            if (this.getModel().isRollover() || !isEnabled()) {
                g.setColor(new Color(0, 0, 0, 50));
                g.fillRoundRect(0, 0, w, h, 25, 25);
            }
        }
    };

    public InventoryPanel() {
        super();
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // this.setBorder(new EmptyBorder(2, 0, 0, 0));

        JLabel title = new JLabel("Inventory");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        pipeButton.setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        pipeButton.setRolloverEnabled(true);
        pipeButton.setVisible(false);

        pumpButton.setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        pumpButton.setRolloverEnabled(true);
        pumpButton.setVisible(false);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(pipeButton);
        buttonsPanel.add(pumpButton);

        MenuPanel.setFontTitle(title);
        this.add(title);
        this.add(buttonsPanel);
        update();
    }

    public void update() {
        if (Controller.instance == null)
            return;

        if (!(Controller.instance.selectedPlayer instanceof Mechanic)) {
            pipeButton.setVisible(false);
            pumpButton.setVisible(false);
            return;
        }

        Mechanic mechanic = (Mechanic) Controller.instance.selectedPlayer;
        if (mechanic.getPipe() != null) {
            pipeButton.setVisible(true);
            if (pipeButton.canPerform()) {
                pipeButton.setEnabled(true);
            } else {
                pipeButton.setEnabled(false);
            }
        } else {
            pipeButton.setVisible(false);
        }

        if (mechanic.getPump() != null) {
            pumpButton.setVisible(true);
            if (pumpButton.canPerform()) {
                pumpButton.setEnabled(true);
            } else {
                pumpButton.setEnabled(false);
            }
        } else {
            pumpButton.setVisible(false);
        }
    }
}