package GUI;

import proto.Mechanic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MechanicView extends JButton implements Viewable {
    private Mechanic mechanic;
    private Point position;

    private Image image = new ImageIcon(Controller.assetsPath + "mechanic.png").getImage();
    private Image image_rollover = new ImageIcon(Controller.assetsPath + "mechanic_rollover.png").getImage();

    public MechanicView(Point position, Mechanic mechanic) {
        this.mechanic = mechanic;
        this.position = position;

        addActionListener((ActionEvent e) -> {
            Controller.instance.selectPlayer(this.mechanic);
        });

        setPreferredSize(new Dimension(Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2));
        setMinimumSize(getPreferredSize());

        setBounds((int) position.getX(), (int) position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setRolloverEnabled(true);
    }

    @Override
    public Object getModelObject() {
        return mechanic;
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
            Window.setImage(g).drawImage(image_rollover, 0, 0, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2, null);
        else
            Window.setImage(g).drawImage(image, 0, 0, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2, null);
    }
}
