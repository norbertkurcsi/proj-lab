package GUI;

import shared.Observer;

import javax.swing.*;
import java.awt.*;

public class PumpView extends JButton implements Observer {



    public PumpView(FieldPosition position) {

    }

    public void enable(){
        this.setEnabled(true);
    }

    public void disable() {
        this.setEnabled(false);
    }

    @Override
    public void update() {
        invalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
