package GUI;

import proto.Field;
import proto.FieldNode;
import proto.Pipe;
import proto.Pump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PipeView extends JPanel implements Viewable {

    private Point end1Position;
    private Point end2Position;
    private Point centerPosition;
    private Pipe pipe;

    private JButton button;

    private boolean wasCut = false;

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
        List<FieldNode> ends = pipe.getEnds();
        if (ends.size() == 0) {
            end1Position = new Point(50, 50);
            end2Position = new Point(100, 100);
        } else if (ends.size() == 1) {
            end1Position = Controller.instance.fields.get(ends.get(0)).getPosition();
            end2Position = new Point(end1Position.x + 100, end1Position.y + 100);
        } else {
            end1Position = Controller.instance.fields.get(ends.get(0)).getPosition();
            end2Position = Controller.instance.fields.get(ends.get(1)).getPosition();
        }
        centerPosition = new Point((end1Position.x + end2Position.x) / 2, (end1Position.y + end2Position.y) / 2);
        this.pipe = pipe;

        setLayout(null);
        setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        setOpaque(false);
        button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Platform
                if (this.getModel().isRollover()) {
                    Window.getGraphics2D(g).drawImage(actualRollover, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
                } else Window.getGraphics2D(g).drawImage(actual, 0, 0, Window.BUTTONSIZE, Window.BUTTONSIZE, null);
            }
        };

        button.addActionListener((ActionEvent e) -> {
            Controller.instance.selectField(pipe);
        });

        button.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                centerPosition = e.getLocationOnScreen();
                Controller.instance.window.updateAllViews();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
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

    public void setWasCut(boolean value) {
        wasCut = value;
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
        if (!pipe.hasWaterFlown()) pipeColor = Color.BLACK;
        else pipeColor = Color.CYAN;
        updateEnds();
        validate();
        button.validate();
        repaint();
        button.repaint();
    }

    private void updateEnds() {
        List<FieldNode> ends = pipe.getEnds();
        if (ends.size() == 0) {
            end1Position = centerPosition;
            end2Position = centerPosition;
        } else if (ends.size() == 1) {
            end1Position = Controller.instance.fields.get(ends.get(0)).getPosition();
            end2Position = centerPosition;

        } else {
            end1Position = Controller.instance.fields.get(ends.get(0)).getPosition();
            end2Position = Controller.instance.fields.get(ends.get(1)).getPosition();
            if (wasCut) {
                centerPosition = new Point((end1Position.x + end2Position.x) / 2, (end1Position.y + end2Position.y) / 2);
                wasCut = false;
            }
        }
        button.setBounds(centerPosition.x, centerPosition.y, Window.BUTTONSIZE, Window.BUTTONSIZE);
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
//        g2d.drawLine(end1Position.x + Window.BUTTONSIZE / 2, end1Position.y + Window.BUTTONSIZE / 2, end2Position.x + Window.BUTTONSIZE / 2, end2Position.y + Window.BUTTONSIZE / 2);
        g2d.drawLine(end1Position.x + Window.BUTTONSIZE / 2, end1Position.y + Window.BUTTONSIZE / 2, centerPosition.x + Window.BUTTONSIZE / 2, centerPosition.y + Window.BUTTONSIZE / 2);
        g2d.drawLine(centerPosition.x + Window.BUTTONSIZE / 2, centerPosition.y + Window.BUTTONSIZE / 2, end2Position.x + Window.BUTTONSIZE / 2, end2Position.y + Window.BUTTONSIZE / 2);
    }
}
