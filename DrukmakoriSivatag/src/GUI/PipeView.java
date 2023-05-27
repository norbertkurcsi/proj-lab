package GUI;

import proto.FieldNode;
import proto.Pipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PipeView extends JPanel implements Viewable {
    private Point end1Position;
    private Point end2Position;
    private Point centerPosition;
    private Pipe pipe;

    private PipeButton pipeButton = new PipeButton();

    private boolean wasCut = false;

    private static Image normal = new ImageIcon(Controller.assetsPath + "pipe.png").getImage();
    private static Image broken = new ImageIcon(Controller.assetsPath + "pipe_broken.png").getImage();
    private static Image slippery_broken = new ImageIcon(Controller.assetsPath + "pipe_slippery_broken.png").getImage();
    private static Image slippery_normal = new ImageIcon(Controller.assetsPath + "pipe_slippery.png").getImage();
    private static Image sticky_broken = new ImageIcon(Controller.assetsPath + "pipe_sticky_broken.png").getImage();
    private static Image sticky_normal = new ImageIcon(Controller.assetsPath + "pipe_sticky.png").getImage();

    public Image actual = normal;
    private Color pipeColor = Color.BLACK;

    public PipeView(Pipe pipe) {
        super();

        this.pipe = pipe;
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

        pipeButton.setBounds(centerPosition.x, centerPosition.y, Window.BUTTONSIZE, Window.BUTTONSIZE);

        this.setLayout(null);
        this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        this.setOpaque(false);
        this.add(pipeButton);
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
            } else if (sticky) {
                actual = sticky_broken;
            } else {
                actual = broken;
            }
        } else {
            if (slippery) {
                actual = slippery_normal;
            } else if (sticky) {
                actual = sticky_normal;
            } else {
                actual = normal;
            }
        }
        if (!pipe.hasWaterFlown())
            pipeColor = Color.BLACK;
        else
            pipeColor = Color.CYAN;
        updateEnds();
        validate();
        pipeButton.validate();
        repaint();
        pipeButton.repaint();
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
                centerPosition = new Point((end1Position.x + end2Position.x) / 2,
                        (end1Position.y + end2Position.y) / 2);
                wasCut = false;
            }
        }
        pipeButton.setBounds(centerPosition.x, centerPosition.y, Window.BUTTONSIZE, Window.BUTTONSIZE);
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
        g2d.drawLine(end1Position.x + Window.BUTTONSIZE / 2, end1Position.y + Window.BUTTONSIZE / 2,
                centerPosition.x + Window.BUTTONSIZE / 2, centerPosition.y + Window.BUTTONSIZE / 2);
        g2d.drawLine(centerPosition.x + Window.BUTTONSIZE / 2, centerPosition.y + Window.BUTTONSIZE / 2,
                end2Position.x + Window.BUTTONSIZE / 2, end2Position.y + Window.BUTTONSIZE / 2);
    }

    private class PipeButton extends JButton {
        public PipeButton() {
            this.addActionListener((ActionEvent e) -> {
                Controller.instance.selectField(pipe);
            });

            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    centerPosition = e.getLocationOnScreen();
                    Controller.instance.window.updateAllViews();
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                }
            });

            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.setRolloverEnabled(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int w = getWidth(), h = getHeight();
            boolean isSelected = Controller.instance.selectedFields.contains(pipe);
            if (isSelected) {
                g.setColor(Color.GREEN);
                g.fillRoundRect(0, 0, w, h, 25, 25);
            }

            Window.getGraphics2D(g).drawImage(actual, 2, 2, w - 4, h - 4, null);
            if (this.getModel().isRollover()) {
                g.setColor(new Color(0, 0, 0, 50));
                g.fillRoundRect(2, 2, w - 4, h - 4, 25, 25);
            }
        }
    }
}
