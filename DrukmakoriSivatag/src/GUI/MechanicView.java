package GUI;

import proto.Field;
import proto.Mechanic;
import proto.Pipe;
import proto.Pump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MechanicView extends JButton implements Viewable {

    private FieldPosition position;
    private Mechanic mechanic;

    public MechanicView(FieldPosition position, Mechanic mechanic) {
        this.mechanic = mechanic;
        this.position = position;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyController();
            }
        });
        setPreferredSize(new Dimension(Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2));
        setMinimumSize(getPreferredSize());
        setBounds(position.getX(), position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        setBackground(Color.BLUE);
    }

    @Override
    public Object getModelObject() {
        return mechanic;
    }

    @Override
    public FieldPosition getPosition() {
        return null;
    }

    private void notifyController() {
        Controller.selectPlayerView(this);
    }

    @Override
    public void update() {
        // Ellenorizzuk, h mozgott-e
        Viewable newPos = (Controller.getViewOfObject(mechanic.getPosition()));
        if(newPos!= null && !this.equals(newPos)) {
            position = newPos.getPosition();
            setBounds(position.getX(), position.getY() - 10 , Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        }
        validate();
        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
