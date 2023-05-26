package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;

public class ConnectPipeButton extends ActionButton {
    public ConnectPipeButton() {
        super();

        this.setText("CONNECT PIPE");
        this.setEnabled(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.connectPipe();
        });
    }

    public boolean canPerform() {
        // TODO
        return false;
    }
}
