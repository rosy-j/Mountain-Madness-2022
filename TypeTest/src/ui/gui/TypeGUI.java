package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeGUI extends JFrame implements ActionListener {
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;

    private JPanel typePanel;

    public TypeGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);

        startPanel();

        pack();
        setVisible(true);

    }

    public void startPanel() {
        typePanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Type Speed Test", SwingConstants.CENTER);
        typePanel.add(label, BorderLayout.NORTH);
        add(typePanel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
