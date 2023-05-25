package GUI;

import proto.Pump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PumpView extends JButton implements Viewable {

    private Pump pump;
    private Point position;
    private static Image normal = new ImageIcon(Controller.assetsPath + "pump.png").getImage();
    private static Image broken = new ImageIcon(Controller.assetsPath + "pump_broken.png").getImage();

    private Image actual = normal;

    public PumpView(Point position, Pump pump) {
        super();
        this.pump = pump;
        this.position = position;

        addActionListener((ActionEvent e) -> {Controller.instance.selectField(this.pump);});
        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setBounds((int)position.getX(), (int)position.getY() , Window.BUTTONSIZE, Window.BUTTONSIZE);
    }

    @Override
    public Object getModelObject() {
        return pump;
    }

    @Override
    public void update() {
        if(pump.isBroken()) {
            actual = broken;
        }
        else actual = normal;
        validate();
        repaint();
    }

    public Point getPosition() {
        return position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Window.setImage(g).drawImage(actual, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
    }
}
