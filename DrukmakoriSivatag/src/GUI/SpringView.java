package GUI;

import proto.Spring;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpringView extends JButton implements Viewable {
    private Spring spring;
    private Point position;
    private static Image normal = new ImageIcon(Controller.assetsPath + "spring.png").getImage();
    private static Image normal_rollover = new ImageIcon(Controller.assetsPath + "spring_rollover.png").getImage();

    public SpringView(Point position, Spring spring) {
        super();
        this.spring = spring;
        this.position = position;

        addActionListener((ActionEvent e) -> {
            Controller.instance.selectField(spring);
        });
        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setBounds((int) position.getX(), (int) position.getY(), Window.BUTTONSIZE, Window.BUTTONSIZE);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    public Object getModelObject() {
        return spring;
    }

    @Override
    public void update() {
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
            Window.setImage(g).drawImage(normal_rollover, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
        else Window.setImage(g).drawImage(normal, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
    }
}