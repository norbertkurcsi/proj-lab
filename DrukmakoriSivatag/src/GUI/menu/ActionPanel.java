package GUI.menu;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.actions.*;

public class ActionPanel extends JPanel {
    private JPanel buttonsPanel = new JPanel();

    public ActionPanel() {
        super();

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // this.setBorder(new EmptyBorder(2, 0, 0, 0));

        JLabel title = new JLabel("Actions");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Chalkduster.ttf")).deriveFont(30f);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            title.setFont(customFont);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        
        this.add(title);

        buttonsPanel.setOpaque(false);

        buttonsPanel.add(new MoveButton());
        buttonsPanel.add(new FixPipeButton());
        buttonsPanel.add(new FixPumpButton());
        buttonsPanel.add(new BreakPipeButton());
        buttonsPanel.add(new ChangeFlowButton());
        buttonsPanel.add(new MakeStickyButton());
        buttonsPanel.add(new MakeSlipperyButton());
        buttonsPanel.add(new ConnectPipeButton());
        buttonsPanel.add(new DisconnectPipeButton());
        buttonsPanel.add(new PickupPumpButton());
        buttonsPanel.add(new PickupPipeButton());

        this.add(buttonsPanel);

        update();
    }

    public void update() {
        // Adjust ActionButtons to only display those that can be performed
        for (Component component : buttonsPanel.getComponents()) {
            if (!(component instanceof ActionButton))
                continue;

            ActionButton btn = (ActionButton) component;
            btn.setVisible(btn.canPerform());
        }
    }
}
