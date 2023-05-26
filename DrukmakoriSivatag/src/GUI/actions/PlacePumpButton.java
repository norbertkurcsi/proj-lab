package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;

public class PlacePumpButton extends ActionButton {
    public PlacePumpButton() {
        super();

        this.setText("PLACE PIPE");
        this.setEnabled(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.placePipe();
        });
    }

    public boolean canPerform() {
        return false;
        // Player player = Controller.instance.selectedPlayer;
        // if (player == null && player instanceof Mechanic)
        // return false;

        // return Controller.instance.selectedFields.size() == 0
        // && player.getPosition() instanceof Pipe;
    }
}
