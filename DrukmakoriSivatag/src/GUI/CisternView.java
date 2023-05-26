package GUI;

import proto.Cistern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CisternView extends JButton implements Viewable {
    private Cistern cistern;
    private Point position;
    private static Image normal = new ImageIcon(Controller.assetsPath + "cistern.png").getImage();
    private static Image normal_rollover = new ImageIcon(Controller.assetsPath + "cistern_rollover.png").getImage();
    private static Image pipeAvailable = new ImageIcon(Controller.assetsPath + "cistern_pipe.png").getImage();
    private static Image pipeAvailable_rollover = new ImageIcon(Controller.assetsPath + "cistern_pipe_rollover.png").getImage();

    private Image actual = pipeAvailable;
    private Image actualRollover = pipeAvailable_rollover;

    public CisternView(Point position, Cistern cistern) {
        super();
        this.cistern = cistern;
        this.position = position;

        addActionListener((ActionEvent e) -> {
            Controller.instance.selectField(cistern);
        });
        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setBounds((int) position.getX(), (int) position.getY(), Window.BUTTONSIZE, Window.BUTTONSIZE);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    public void update() {
        if (cistern.isPipeAvailable()) {
            actual = pipeAvailable;
            actualRollover = pipeAvailable_rollover;
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
        else
            Window.getGraphics2D(g).drawImage(actual, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
    }
}