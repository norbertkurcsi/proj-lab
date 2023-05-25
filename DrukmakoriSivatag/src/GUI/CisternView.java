package GUI;

import proto.Cistern;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CisternView extends JButton implements Viewable {
    private Cistern cistern;
    private Point position;
    private static Image normal = new ImageIcon(Controller.assetsPath + "cistern.png").getImage();;

    public CisternView(Point position, Cistern cistern) {
        super();
        this.cistern = cistern;
        this.position = position;

        addActionListener((ActionEvent e) -> {Controller.instance.selectField(cistern);});
        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setBounds((int)position.getX(), (int)position.getY() , Window.BUTTONSIZE, Window.BUTTONSIZE);
    }

    @Override
    public Object getModelObject() {
        return cistern;
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Window.setImage(g).drawImage(normal, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
    }
}