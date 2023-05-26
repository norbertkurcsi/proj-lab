package GUI;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;

public class Window extends JFrame {
    static final int WIDTH = 1280;
    static final int HEIGHT = 800;

    static final int BUTTONSIZE = 50;

    private static Image background = new ImageIcon(Controller.assetsPath + "background.png").getImage();
    private static HashMap<Class<?>, Integer> zOrder = new HashMap<>();
    static {
        zOrder.put(MechanicView.class, 2);
        zOrder.put(SaboteurView.class, 2);
        zOrder.put(PumpView.class, 1);
        zOrder.put(CisternView.class, 1);
        zOrder.put(SpringView.class, 1);
        zOrder.put(PipeView.class, 0);
    }

    private MenuPanel menu = new MenuPanel();
    private JLayeredPane map = new JLayeredPane() {
        @Override
        public void paintComponent(Graphics g) {
            getGraphics2D(g).drawImage(background, 0, 0, Window.WIDTH, Window.HEIGHT, null);
        }
    };

    public Window() {
        super();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Drukmakori sivatag");
        this.setVisible(true);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(getSize());

        map.setLayout(null);
        map.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);

        this.setLayout(new BorderLayout());
        this.add(map);
        this.add(menu, BorderLayout.SOUTH);
    }

    public void addViewable(Viewable view) {
        Component component = (Component) view;
        map.add(component, zOrder.get(view.getClass()));
        map.validate();
        map.repaint();
    }

    public void updateAllViews() {
        map.repaint();
        for (Viewable view : Controller.instance.fields.values())
            view.update();

        for (Viewable view : Controller.instance.players.values())
            view.update();
    }

    static public Graphics2D getGraphics2D(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2d;
    }

    public void updateActions() {
        menu.updateActions();
    }
}
