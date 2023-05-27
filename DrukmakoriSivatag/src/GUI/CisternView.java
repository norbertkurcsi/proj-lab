package GUI;

import proto.Cistern;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class CisternView extends JButton implements Viewable {
    private Cistern cistern;
    private Point position;

    private static Image normal = new ImageIcon(Controller.assetsPath + "cistern.png").getImage();
    private static Image pipeAvailable = new ImageIcon(Controller.assetsPath + "cistern_pipe.png").getImage();

    private Image image = normal;

    public CisternView(Point position, Cistern cistern) {
        super();

        this.cistern = cistern;
        this.position = position;

        this.addActionListener((ActionEvent e) -> {
            Controller.instance.selectField(cistern);
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
        image = cistern.isPipeAvailable() ? pipeAvailable : normal;
        validate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth(), h = getHeight();
        boolean isSelected = Controller.instance.selectedFields.contains(cistern);
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