package GUI;

import proto.Mechanic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MechanicView extends JButton implements Viewable {
    private Mechanic mechanic;

    private static Image image = new ImageIcon(Controller.assetsPath + "mechanic2.png").getImage();

    public MechanicView(Mechanic mechanic) {
        super();

        this.mechanic = mechanic;

        this.addActionListener((ActionEvent e) -> {
            Controller.instance.selectPlayer(this.mechanic);
        });

        int size = (int) (Window.BUTTONSIZE * 0.6);
        Point position = getPosition();
        this.setBounds((int) position.getX(), (int) position.getY() - 10, size, size);

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setRolloverEnabled(true);
    }

    @Override
    public Point getPosition() {
        Viewable view = Controller.instance.fields.get(mechanic.getPosition());
        return view.getPosition();
    }

    @Override
    public void update() {
        Point position = getPosition();
        setBounds((int) position.getX(), (int) position.getY() - 10, getWidth(), getHeight());
        validate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth(), h = getHeight();

        boolean isSelected = Controller.instance.selectedPlayer == mechanic;
        if (isSelected) {
            g.setColor(Color.GREEN);
            g.fillOval(0, 0, w, h);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image, 2, 2, w - 4, h - 4, null);

        if (this.getModel().isRollover()) {
            g.setColor(new Color(0, 0, 0, 50));
            g.fillOval(2, 2, w - 4, h - 4);
        }
    }
}
