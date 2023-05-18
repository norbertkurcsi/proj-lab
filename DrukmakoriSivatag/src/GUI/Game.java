package GUI;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;

    JScrollPane scrollableMap;
    JPanel menu;
    public Game() {
        super();
        this.setLayout(new BorderLayout());
        scrollableMap = new JScrollPane(new Map());
        scrollableMap.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollableMap.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollableMap, BorderLayout.CENTER);

        menu = new Menu();
        add(menu, BorderLayout.SOUTH);

        initialize();
    }

    static public Graphics2D setImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2d;
    }

    private void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Drukmakori sivatag");
        setVisible(true);
        setResizable(false);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(getSize());
    }

    public static void main(String args[]) {
        new Game();
    }
}

