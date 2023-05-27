package GUI;

import proto.Spring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpringView extends JButton implements Viewable {
    private Spring spring;
    private Point position;

    private static Image image = new ImageIcon(Controller.assetsPath + "spring.png").getImage();
    // private static Image normal_rollover = new ImageIcon(Controller.assetsPath +
    // "spring_rollover.png").getImage();

    public SpringView(Point position, Spring spring) {
        super();

        this.spring = spring;
        this.position = position;

        this.addActionListener((ActionEvent e) -> {
            Controller.instance.selectField(spring);
        });

        this.setBounds((int) position.getX(), (int) position.getY(), Window.BUTTONSIZE, Window.BUTTONSIZE);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setRolloverEnabled(true);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void update() {
        validate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth(), h = getHeight();
        boolean isSelected = Controller.instance.selectedFields.contains(spring);
        if (isSelected) {
            g.setColor(Color.GREEN);
            g.fillRoundRect(0, 0, w, h, 25, 25);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image, 2, 2, w - 4, h - 4, null);

        if (this.getModel().isRollover()) {
            g.setColor(new Color(0, 0, 0, 50));
            g.fillRoundRect(2, 2, w - 4, h - 4, 25, 25);
        }
    }
}