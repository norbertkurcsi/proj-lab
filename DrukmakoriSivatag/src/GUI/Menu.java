package GUI;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu() {
        super();
        setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5));
        setPreferredSize(getMinimumSize());
    }
}
