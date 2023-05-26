package GUI;

import proto.Saboteur;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaboteurView extends JButton implements Viewable {

    private Point position;
    private Saboteur saboteur;

    private Image image = new ImageIcon(Controller.assetsPath + "saboteur.png").getImage();
    private Image image_rollover = new ImageIcon(Controller.assetsPath + "saboteur_rollover.png").getImage();

    public SaboteurView(Point position, Saboteur saboteur) {
        this.saboteur = saboteur;
        this.position = position;

        addActionListener((ActionEvent e) -> {
            Controller.instance.selectPlayer(saboteur);
        });

        setPreferredSize(new Dimension(Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2));
        setMinimumSize(getPreferredSize());
        setBounds((int) position.getX() + 20, (int) position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);

        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    public Object getModelObject() {
        return saboteur;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void update() {
        // Ellenorizzuk, h mozgott-e
        Viewable newPos = Controller.instance.fields.get(saboteur.getPosition());
        if (newPos != null && !this.equals(newPos)) {
            position = newPos.getPosition();
            setBounds((int) position.getX() + 20, (int) position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        }
        validate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.getModel().isRollover())
            Window.setImage(g).drawImage(image_rollover, 0, 0, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2, null);
        else Window.setImage(g).drawImage(image, 0, 0, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2, null);
    }
}
