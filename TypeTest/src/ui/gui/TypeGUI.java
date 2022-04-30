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
        JLabel labelType = new JLabel("Type Speed Test", SwingConstants.CENTER);
        panel[0].add(labelType, BorderLayout.NORTH);
        JButton buttonType = new JButton("Press me");
        buttonType.addActionListener((e) -> {
            setState(State.TYPING);
        });
        panel[0].add(buttonType, BorderLayout.CENTER);
        topLayerPanel.add(panel[0], State.PREPARING.toString());

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
