package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeGUI extends JFrame implements ActionListener {
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;

    private State currentState = State.PREPARING;
    private JPanel topLayerPanel;
    private JPanel[] panel = new JPanel[3];

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

    private void setState(State nextState) {
        currentState = nextState;
        CardLayout cl = (CardLayout) (topLayerPanel.getLayout());
        cl.show(topLayerPanel, currentState.toString());
    }

    public void startPanel() {
        topLayerPanel = new JPanel(new CardLayout());
        add(topLayerPanel);

        panel[0] = new JPanel(new BorderLayout());
        JLabel labelType = new JLabel("Type \"speed test\" as many times as you want,\n then hit ENTER", SwingConstants.CENTER);
        labelType.setFont(new Font("Serif", Font.PLAIN, 35));
        panel[0].add(labelType, BorderLayout.NORTH);
        JButton buttonType = new JButton("Press me");
        buttonType.addActionListener((e) -> {
            setState(State.TYPING);
        });
        panel[0].add(buttonType, BorderLayout.SOUTH);
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        panel[0].add(textArea, BorderLayout.CENTER);
        topLayerPanel.add(panel[0], State.PREPARING.toString());


//        JLabel label2 = new JLabel("Type \"speed test\" as many times as you want,\n then hit ENTER"
//                , SwingConstants.CENTER);
//        label.setFont(new Font("Serif", Font.PLAIN, 20));
//        label.setLocation(30, 30);
//        typePanel.add(label2);

        panel[1] = new JPanel(new BorderLayout());
        JLabel labelOther = new JLabel("TYPE FAST", SwingConstants.CENTER);
        panel[1].add(labelOther, BorderLayout.CENTER);
        JButton buttonOther = new JButton("hahahaha I'm in pain");
        buttonOther.addActionListener((e) -> {
            setState(State.FINISHED);
        });
        panel[1].add(buttonOther, BorderLayout.CENTER);
        topLayerPanel.add(panel[1], State.TYPING.toString());

        panel[2] = new JPanel(new BorderLayout());
        JLabel labelThird = new JLabel("Results", SwingConstants.CENTER);
        panel[2].add(labelThird, BorderLayout.CENTER);
        JButton buttonGOGOGO = new JButton("Your time is TIME");
        buttonGOGOGO.addActionListener((e) -> {
            setState(State.PREPARING);
        });
        panel[2].add(buttonGOGOGO, BorderLayout.CENTER);
        topLayerPanel.add(panel[2], State.FINISHED.toString());

        CardLayout cl = (CardLayout)(topLayerPanel.getLayout());
        cl.show(topLayerPanel, currentState.toString());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
