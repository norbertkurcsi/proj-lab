package GUI.menu;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
    public ScorePanel() {
        super();
        this.setOpaque(false);

        this.add(new JLabel("Scores"));
    }
}
