package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;

public class DisconnectPipeButton extends ActionButton {
    public DisconnectPipeButton() {
        super();

        this.setText("DISCONNECT PIPE");
        this.setEnabled(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.connectPipe();
        });
    }

    public boolean canPerform() {
        // TODO
        return false;

        // Player player = Controller.instance.selectedPlayer;
        // if (player == null && player instanceof Mechanic)
        // return false;

        // List<Field> selectedFields = Controller.instance.selectedFields;
        // if (selectedFields.size() != 2)
        // return false;

        // Field toDisconnect = selectedFields.get(0); // Pipe to disconnect
        // Field from = selectedFields.get(1); // The FieldNode to disconnect the pipe
        // from
        // if (!player.getPosition().hasNeighbour(toDisconnect) ||
        // !from.hasNeighbour(toDisconnect))
        // return false;

        // return toDisconnect instanceof Pipe
        // && from instanceof FieldNode;
    }
}
