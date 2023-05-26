package GUI;

import proto.Mechanic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MechanicView extends JButton implements Viewable {
    private Mechanic mechanic;

    private Image image = new ImageIcon(Controller.assetsPath + "mechanic.png").getImage();
    private Image image_rollover = new ImageIcon(Controller.assetsPath + "mechanic_rollover.png").getImage();

    public MechanicView(Mechanic mechanic) {
        this.mechanic = mechanic;

        addActionListener((ActionEvent e) -> {
            Controller.instance.selectPlayer(this.mechanic);
        });

        setPreferredSize(new Dimension(Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2));
        setMinimumSize(getPreferredSize());

        Point position = getPosition();
        setBounds((int) position.getX(), (int) position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setRolloverEnabled(true);
    }

    @Override
    public Point getPosition() {
        Viewable view = Controller.instance.fields.get(mechanic.getPosition());
        return view.getPosition();
    }

    @Override
    public void update() {
        Point position = getPosition();
        setBounds((int) position.getX(), (int) position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        validate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.getModel().isRollover())
            Window.getGraphics2D(g).drawImage(image_rollover, 0, 0, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2, null);
        else
            Window.getGraphics2D(g).drawImage(image, 0, 0, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2, null);
    }
}
