package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;

    static final int BUTTONSIZE = 50;

    JPanel map;
    MenuPanel menu;

    private static Image background = new ImageIcon(Controller.assetsPath + "background.png").getImage();

    public Window() {
        super();
        this.setLayout(new BorderLayout());
        map = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                setImage(g).drawImage(background, 0, 0, Window.WIDTH, Window.HEIGHT, null);
            }
        };
        map.setLayout(null);
        map.setBounds(0,0, Window.WIDTH, Window.HEIGHT);
        add(map);
        menu = new MenuPanel();
        //add(menu, BorderLayout.SOUTH);
    }

    public void addViewsToMap(Component[] views) {
        for(Component v : views) {
            map.add(v);
        }
    }

    public void updateAllViews() {
        if(map != null) map.repaint();
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
