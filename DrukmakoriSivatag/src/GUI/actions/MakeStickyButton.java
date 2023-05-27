package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Pipe;
import proto.Player;

public class MakeStickyButton extends ActionButton {
    public MakeStickyButton() {
        super();

        this.setText("MAKE STICKY");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.makeSticky();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (player == null)
            return false;

        return Controller.instance.selectedFields.size() == 0
                && player.getPosition() instanceof Pipe && !((Pipe)player.getPosition()).isSticky();
    }
}
