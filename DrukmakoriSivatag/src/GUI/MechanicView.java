package GUI;

import proto.Mechanic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MechanicView extends JButton implements Viewable {
    private Mechanic mechanic;

    public MechanicView(Mechanic mechanic) {
        this.mechanic = mechanic;

        addActionListener((ActionEvent e) -> {
            Controller.instance.selectPlayer(this.mechanic);
        });

        setPreferredSize(new Dimension(Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2));
        setMinimumSize(getPreferredSize());

        Point position = getPosition();
        setBounds((int) position.getX(), (int) position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);

        setBackground(Color.BLUE);
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
    }
}
