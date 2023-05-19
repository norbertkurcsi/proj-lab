package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private JPanel inventoryPanel;
    private JPanel actionPanel;

    private JSplitPane splitPanel;

    public MenuPanel() {
        super();
        setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT / 5));
        setPreferredSize(getMinimumSize());
        inventoryPanel = new JPanel();
        actionPanel = new JPanel();
        actionPanel.setBackground(Color.GREEN);
//        actionPanel.setBounds(0,0, getWidth()/2, getHeight());
        inventoryPanel.setBackground(Color.CYAN);
//        inventoryPanel.setBounds(getWidth() / 2, 0, getWidth(), getHeight());
        splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, actionPanel, inventoryPanel);
        splitPanel.setBounds(0, 0, getWidth(), getHeight());
        setLayout(new BorderLayout());
        add(splitPanel, BorderLayout.CENTER);
        initializeInventory();
    }

    private void initializeInventory() {
        JButton moveButton = new JButton("MOVE");
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.movePlayer();
            }
        });
        inventoryPanel.add(moveButton);

        JButton fixPipe = new JButton("FIXPIPE");
        fixPipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.fixPipe();
            }
        });
        inventoryPanel.add(fixPipe);

        JButton breakPipe = new JButton("BREAKPIPE");
        breakPipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.breakPipe();
            }
        });
        inventoryPanel.add(breakPipe);
    }
}
