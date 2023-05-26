package GUI;

import proto.Pump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PumpView extends JButton implements Viewable {

    private Pump pump;
    private Point position;
    private static Image normal = new ImageIcon(Controller.assetsPath + "pump.png").getImage();
    private static Image normal_rollover = new ImageIcon(Controller.assetsPath + "pump_rollover.png").getImage();
    private static Image broken = new ImageIcon(Controller.assetsPath + "pump_broken.png").getImage();
    private static Image broken_rollover = new ImageIcon(Controller.assetsPath + "pump_broken_rollover.png").getImage();

    private Image actual = normal;
    private Image actualRollover = normal_rollover;

    public PumpView(Point position, Pump pump) {
        super();
        this.pump = pump;
        this.position = position;

        addActionListener((ActionEvent e) -> {
            Controller.instance.selectField(this.pump);
        });
        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setBounds((int) position.getX(), (int) position.getY(), Window.BUTTONSIZE, Window.BUTTONSIZE);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    public void update() {
        if (pump.isBroken()) {
            actual = broken;
            actualRollover = broken_rollover;
        } else {
            actual = normal;
            actualRollover = normal_rollover;
        }
        validate();
        repaint();
    }

    public Point getPosition() {
        return position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.getModel().isRollover())
            Window.getGraphics2D(g).drawImage(actualRollover, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
        else Window.getGraphics2D(g).drawImage(actual, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
    }
}
