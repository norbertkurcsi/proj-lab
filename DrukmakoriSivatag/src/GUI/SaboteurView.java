package GUI;

import proto.Saboteur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaboteurView extends JButton implements Viewable {

    private FieldPosition position;
    private Saboteur saboteur;

    public SaboteurView(FieldPosition position, Saboteur saboteur) {
        this.saboteur = saboteur;
        this.position = position;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyController();
            }
        });

        setPreferredSize(new Dimension(Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2));
        setMinimumSize(getPreferredSize());
        setBounds(position.getX() + 20, position.getY() - 10, Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        setBackground(Color.YELLOW);
    }

    @Override
    public Object getModelObject() {
        return saboteur;
    }

    @Override
    public FieldPosition getPosition() {
        return position;
    }

    private void notifyController() {
        Controller.selectPlayerView(this);
    }

    @Override
    public void update() {
        // Ellenorizzuk, h mozgott-e
        Viewable newPos = (Controller.getViewOfObject(saboteur.getPosition()));
        if(newPos!= null && !this.equals(newPos)) {
            position = newPos.getPosition();
            setBounds(position.getX() + 20, position.getY() - 10 , Window.BUTTONSIZE / 2, Window.BUTTONSIZE / 2);
        }
        validate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
