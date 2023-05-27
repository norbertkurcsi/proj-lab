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
    }
}
