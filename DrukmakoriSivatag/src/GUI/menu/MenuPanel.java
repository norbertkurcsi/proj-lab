package GUI.menu;

import javax.swing.*;

import GUI.Controller;
import GUI.Window;
import GUI.actions.*;
import proto.Mechanic;
import proto.Player;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private static Image background = new ImageIcon(Controller.assetsPath + "panel.png").getImage();

    private ActionPanel actionPanel = new ActionPanel();
    private InventoryPanel inventoryPanel = new InventoryPanel();
    private JPanel scorePanel = new ScorePanel();

    public MenuPanel() {
        super();

        this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5));
        this.setPreferredSize(getMinimumSize());
        this.setLayout(new GridLayout(1, 3));
        this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5));
        this.setPreferredSize(getMinimumSize());

        this.add(actionPanel);
        this.add(inventoryPanel);
        this.add(scorePanel);
    }

    public void update() {
        actionPanel.update();
        inventoryPanel.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        Window.getGraphics2D(g).drawImage(background, -10, -10, getWidth() + 50, getHeight() + 20, null);
    }

    public static void setFontTitle(JLabel title) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Chalkduster.ttf")).deriveFont(30f);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            title.setFont(customFont);
        } catch (IOException|FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
