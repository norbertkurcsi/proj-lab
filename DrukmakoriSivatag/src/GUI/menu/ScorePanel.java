package GUI.menu;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ScorePanel extends JPanel {
    public ScorePanel() {
        super();
        this.setOpaque(false);
        JLabel title = new JLabel("Scores");
        MenuPanel.setFontTitle(title);
        this.add(title);
    }
}
