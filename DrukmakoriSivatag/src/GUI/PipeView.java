package GUI;

import proto.Field;
import proto.FieldNode;
import proto.Pipe;
import proto.Pump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PipeView extends JButton implements Viewable{

    private Point end1Position;
    private Point end2Position;
    private Point centerPosition;
    private Pipe pipe;

    private static Image normal = new ImageIcon(Controller.assetsPath + "pipe.png").getImage();
    private static Image broken = new ImageIcon(Controller.assetsPath + "pipe_broken.png").getImage();
    private static Image slippery_broken = new ImageIcon(Controller.assetsPath + "pipe_slippery_broken.png").getImage();
    private static Image slippery_normal = new ImageIcon(Controller.assetsPath + "pipe_slippery.png").getImage();
    private static Image sticky_broken = new ImageIcon(Controller.assetsPath + "pipe_sticky_broken.png").getImage();
    private static Image sticky_normal = new ImageIcon(Controller.assetsPath + "pipe_sticky.png").getImage();

    private Image actual = normal;
    private Color pipeColor = Color.BLACK;

    public PipeView(Pipe pipe) {
        Field end1 = pipe.getEnds().get(0);
        Field end2 = pipe.getEnds().get(1);
        if(end1 != null) end1Position = Controller.instance.fields.get(end1).getPosition();
        if(end2 != null) end2Position = Controller.instance.fields.get(end2).getPosition();
        if(end1 == null && end2 != null) end1Position = new Point(end2Position.x, end2Position.y - 50);
        if(end1 != null && end2 == null) end2Position = new Point(end1Position.x, end1Position.y - 50);
        if(end1 == null && end2 == null) {
            end1Position = new Point(20, 50);
            end2Position = new Point(20, 100);
        }
        centerPosition = new Point(((int)end1Position.getX() + (int)end2Position.getX()) / 2, ((int)end1Position.getY() + (int)end2Position.getY()) / 2);
        this.pipe = pipe;

        addActionListener((ActionEvent e) -> {Controller.instance.selectField(pipe);});

        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBounds((int)centerPosition.getX(), (int)centerPosition.getY() , Window.BUTTONSIZE, Window.BUTTONSIZE);
//        setBounds(end1Position.getX(), end1Position.getY() , end2Position.getX(), end2Position.getY());
    }

    @Override
    public void update() {
        boolean isBroken = pipe.isBroken();
        boolean slippery = pipe.isSlippery();
        boolean sticky = pipe.isSticky();
        if(isBroken) {
            if(slippery) actual = slippery_broken;
            else if(sticky) actual = sticky_broken;
            else actual = broken;
        }
        else {
            if(slippery) actual = slippery_normal;
            else if(sticky) actual = sticky_normal;
            else actual = normal;
        }
        if(pipe.isEmpty()) pipeColor = Color.BLACK;
        else pipeColor = Color.CYAN;
        validate();
        repaint();
    }

    @Override
    public Object getModelObject() {
        return pipe;
    }

    @Override
    public Point getPosition() {
        return centerPosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Pipes
        Graphics2D g2d = (Graphics2D) g;
        g2d.setClip(centerPosition.x-end2Position.x, centerPosition.y-end2Position.y, Window.WIDTH, Window.HEIGHT);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(pipeColor);
        g2d.drawLine(0, 0, centerPosition.x-end1Position.x, centerPosition.y-end1Position.y);
        g2d.drawLine(0, 0, centerPosition.x-end2Position.x, centerPosition.y-end2Position.y);

        // Platform
        Window.setImage(g).drawImage(actual, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
    }
}
