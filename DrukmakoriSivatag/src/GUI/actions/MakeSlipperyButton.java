package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Pipe;
import proto.Player;
import proto.Saboteur;

public class MakeSlipperyButton extends ActionButton {
    public MakeSlipperyButton() {
        super();

        this.setContentAreaFilled(false);
        this.setRolloverEnabled(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setText("MAKE SLIPPERY");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.makeSlippery();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Saboteur))
            return false;

        return Controller.instance.selectedFields.size() == 0
                && player.getPosition() instanceof Pipe && !((Pipe)player.getPosition()).isSlippery();
    }
}
