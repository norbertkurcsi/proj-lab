package GUI;

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

    public PipeView(Pipe pipe) {
        end1Position = Controller.instance.fields.get(pipe.getEnds()[0]).getPosition();
        end2Position = Controller.instance.fields.get(pipe.getEnds()[1]).getPosition();
        centerPosition = new Point((end1Position.getX() + end2Position.getX()) / 2, (end1Position.getY() + end2Position.getY()) / 2);
        this.pipe = pipe;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyController();
            }
        });

        setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setBounds((int)centerPosition.getX(), (int)centerPosition.getY() , Window.BUTTONSIZE, Window.BUTTONSIZE);
//        setBounds(end1Position.getX(), end1Position.getY() , end2Position.getX(), end2Position.getY());
        setBackground(Color.magenta);
    }

    private void notifyController() {
        Controller.selectMapElement(this);
    }

    @Override
    public void update() {
        if(pipe.isBroken()) {
            setBackground(Color.RED);
        }
        else setBackground(Color.magenta);
        validate();
        repaint();
    }

    @Override
    public Object getModelObject() {
        return pipe;
    }

    @Override
    public FieldPosition getPosition() {
        return centerPosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //TODO
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setClip(0, 0, Window.WIDTH, Window.HEIGHT);
//        g2d.setStroke(new BasicStroke(2.0f));
//        g2d.setColor(Color.BLACK);
//        g2d.drawLine(0, 0, end2Position.getX(), end2Position.getY());
//        g2d.drawLine(end1Position.getX(), end1Position.getY(), end2Position.getX(), end2Position.getY());
    }
}
