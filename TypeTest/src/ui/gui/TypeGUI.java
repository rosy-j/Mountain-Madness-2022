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
        JLabel label = new JLabel("Type \"speed test\" as many times as you want,\n then hit ENTER", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 35));
        typePanel.add(label, BorderLayout.NORTH);
//        JLabel label2 = new JLabel("Type \"speed test\" as many times as you want,\n then hit ENTER"
//                , SwingConstants.CENTER);
//        label.setFont(new Font("Serif", Font.PLAIN, 20));
//        label.setLocation(30, 30);
//        typePanel.add(label2);
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        typePanel.add(textArea, BorderLayout.CENTER);
        add(typePanel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
