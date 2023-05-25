package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;

    static final int BUTTONSIZE = 50;

    public JScrollPane scrollableMap;
    MenuPanel menu;

    public Window() {
        super();
        this.setLayout(new BorderLayout());
        scrollableMap = new JScrollPane(new JPanel());
        scrollableMap.setLayout(null);
        scrollableMap.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollableMap.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollableMap, BorderLayout.CENTER);

        menu = new MenuPanel();
        add(menu, BorderLayout.SOUTH);
    }

    public void addViewsToMap(JButton[] views) {
        for(JButton v : views) {
            scrollableMap.add(v);
        }
    }

    public void updateAllViews() {
        for (Viewable view : Controller.instance.fields.values()) 
            view.update();

        for (Viewable view : Controller.instance.players.values()) 
            view.update();
    }

    static public Graphics2D setImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2d;
    }

    public void updateActions() {
        menu.updateActions();
    }

    public void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Drukmakori sivatag");
        setVisible(true);
        setResizable(false);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(getSize());
    }
}
