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

public class PipeView extends JPanel implements Viewable {

    private Point end1Position;
    private Point end2Position;
    private Point centerPosition;
    private Pipe pipe;

    private JButton button;

    private static Image normal = new ImageIcon(Controller.assetsPath + "pipe.png").getImage();
    private static Image normal_selected = new ImageIcon(Controller.assetsPath + "pipe_selected.png").getImage();
    private static Image normal_rollover = new ImageIcon(Controller.assetsPath + "pipe_rollover.png").getImage();
    private static Image broken = new ImageIcon(Controller.assetsPath + "pipe_broken.png").getImage();
    private static Image broken_rollover = new ImageIcon(Controller.assetsPath + "pipe_broken_rollover.png").getImage();
    private static Image slippery_broken = new ImageIcon(Controller.assetsPath + "pipe_slippery_broken.png").getImage();
    private static Image slippery_broken_rollover = new ImageIcon(Controller.assetsPath + "pipe_slippery_broken_rollover.png").getImage();
    private static Image slippery_normal = new ImageIcon(Controller.assetsPath + "pipe_slippery.png").getImage();
    private static Image slippery_normal_rollover = new ImageIcon(Controller.assetsPath + "pipe_slippery_rollover.png").getImage();
    private static Image sticky_broken = new ImageIcon(Controller.assetsPath + "pipe_sticky_broken.png").getImage();
    private static Image sticky_broken_rollover = new ImageIcon(Controller.assetsPath + "pipe_sticky_broken_rollover.png").getImage();
    private static Image sticky_normal = new ImageIcon(Controller.assetsPath + "pipe_sticky.png").getImage();
    private static Image sticky_normal_rollover = new ImageIcon(Controller.assetsPath + "pipe_sticky_rollover.png").getImage();

    public Image actual = normal;
    public Image actualRollover = normal_rollover;
    private Color pipeColor = Color.BLACK;

    public PipeView(Pipe pipe) {
        Field end1 = pipe.getEnds().get(0);
        Field end2 = pipe.getEnds().get(1);
        if (end1 != null) end1Position = Controller.instance.fields.get(end1).getPosition();
        if (end2 != null) end2Position = Controller.instance.fields.get(end2).getPosition();
        // Ha egyik vége szabad
        if (end1 == null && end2 != null) end1Position = new Point(end2Position.x, end2Position.y - 50);
        if (end1 != null && end2 == null) end2Position = new Point(end1Position.x, end1Position.y - 50);
        // Ha mindkét vége szabad
        if (end1 == null && end2 == null) {
            end1Position = new Point(20, 50);
            end2Position = new Point(20, 100);
        }
        centerPosition = new Point((end1Position.x + end2Position.x) / 2, (end1Position.y + end2Position.y) / 2);
        this.pipe = pipe;

        setLayout(null);
        setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        setBackground(Color.magenta);
        button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Platform
                if (this.getModel().isRollover()) {
                    Window.setImage(g).drawImage(actualRollover, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
                } else Window.setImage(g).drawImage(actual, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
            }
        };

        button.addActionListener((ActionEvent e) -> {
            Controller.instance.selectField(pipe);
        });

        add(button);
        button.setPreferredSize(new Dimension(Window.BUTTONSIZE, Window.BUTTONSIZE));
        button.setMinimumSize(button.getPreferredSize());
        button.setMaximumSize(button.getPreferredSize());
        button.setBounds(centerPosition.x, centerPosition.y, Window.BUTTONSIZE, Window.BUTTONSIZE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setRolloverEnabled(true);
    }

    @Override
    public void update() {
        boolean isBroken = pipe.isBroken();
        boolean slippery = pipe.isSlippery();
        boolean sticky = pipe.isSticky();
        if (isBroken) {
            if (slippery) {
                actual = slippery_broken;
                actualRollover = slippery_broken_rollover;
            } else if (sticky) {
                actual = sticky_broken;
                actualRollover = sticky_broken_rollover;
            } else {
                actual = broken;
                actualRollover = broken_rollover;
            }
        } else {
            if (slippery) {
                actual = slippery_normal;
                actualRollover = slippery_normal_rollover;
            } else if (sticky) {
                actual = sticky_normal;
                actualRollover = sticky_normal_rollover;
            } else {
                actual = normal;
                actualRollover = normal_rollover;
            }
        }
        if (pipe.isEmpty()) pipeColor = Color.BLACK;
        else pipeColor = Color.CYAN;
        validate();
        button.validate();
        repaint();
        button.repaint();
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Pipes
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.setColor(pipeColor);
        g2d.drawLine(end1Position.x + Window.BUTTONSIZE / 2, end1Position.y + Window.BUTTONSIZE / 2, end2Position.x + Window.BUTTONSIZE / 2, end2Position.y + Window.BUTTONSIZE / 2);
//        button.repaint();
    }
}
