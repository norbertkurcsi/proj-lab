package GUI;

import proto.Pump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PumpView extends JButton implements Viewable {

    Pump pump;
    FieldPosition position;

    public PumpView(FieldPosition position, Pump pump) {
        super();
        this.pump = pump;
        this.position = position;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyController();
            }
        });
        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setBounds(position.getX(), position.getY() , Window.BUTTONSIZE, Window.BUTTONSIZE);
        setBackground(Color.GREEN);
    }

    private void notifyController() {
        Controller.selectMapElement(this);
    }

    @Override
    public Object getModelObject() {
        return pump;
    }

    @Override
    public void update() {
        if(pump.isBroken()) {
            setBackground(Color.RED);
        }
        else setBackground(Color.GREEN);
        validate();
        repaint();
    }

    public FieldPosition getPosition() {
        return position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
