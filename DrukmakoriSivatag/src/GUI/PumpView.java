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
    private Image normal;
    private Image broken;

    public PumpView(Point position, Pump pump) {
        super();
        this.pump = pump;
        this.position = position;

        addActionListener((ActionEvent e) -> {Controller.instance.selectField(this.pump);});
        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setBounds((int)position.getX(), (int)position.getY() , Window.BUTTONSIZE, Window.BUTTONSIZE);
        setBackground(Color.GREEN);

        normal = new ImageIcon(Controller.assetsPath + toString() + ".png").getImage();
        broken = new ImageIcon(Controller.assetsPath + toString() + "_broken.png").getImage();
    }

    @Override
    public Object getModelObject() {
        return pump;
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
    public String toString() {
        return "pump";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(pump.isBroken()) {
            Window.setImage(g).drawImage(broken, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
        }
        else Window.setImage(g).drawImage(normal, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
    }
}
