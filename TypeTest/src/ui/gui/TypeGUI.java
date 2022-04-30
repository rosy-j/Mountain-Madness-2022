package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TypeGUI extends JFrame implements ActionListener {
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;

    private State currentState = State.PREPARING;
    private JPanel topLayerPanel;
    private JPanel[] panel = new JPanel[3];

    JTextField countDown;
    JTextArea textArea;

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
        startState(currentState);
        CardLayout cl = (CardLayout) (topLayerPanel.getLayout());
        cl.show(topLayerPanel, currentState.toString());
    }

    private void startState(State currentState) {
        switch(currentState) {
            case PREPARING:
                countDown.setText("3");
                break;
            case TYPING:
                textArea.setText("");
                break;
            case FINISHED:
                break;
        }
    }

    public void startPanel() {
        topLayerPanel = new JPanel(new CardLayout());
        add(topLayerPanel);

        panel[0] = new JPanel(new BorderLayout());
        JLabel labelPrep = new JLabel("TYPE FAST", SwingConstants.CENTER);
        panel[0].add(labelPrep, BorderLayout.NORTH);
        countDown = new JTextField();
        countDown.setText("3");
        countDown.setEditable(false);
        countDown.setHorizontalAlignment(JTextField.CENTER);
        countDown.setFont(new Font("Serif", Font.PLAIN, 48));
        panel[0].add(countDown, BorderLayout.CENTER);
        JButton buttonType = new JButton("Press me");
        buttonType.addActionListener((e) -> {
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.schedule(() -> {
                countDown.setText("2");
            }, 1, TimeUnit.SECONDS);
            executorService.schedule(() -> {
                countDown.setText("1");
            }, 2, TimeUnit.SECONDS);
            executorService.schedule(() -> {
                setState(State.TYPING);
            }, 3, TimeUnit.SECONDS);

        });
        panel[0].add(buttonType, BorderLayout.WEST);
        topLayerPanel.add(panel[0], State.PREPARING.toString());


//        JLabel label2 = new JLabel("Type \"speed test\" as many times as you want,\n then hit ENTER"
//                , SwingConstants.CENTER);
//        label.setFont(new Font("Serif", Font.PLAIN, 20));
//        label.setLocation(30, 30);
//        typePanel.add(label2);

        panel[1] = new JPanel(new BorderLayout());
        JLabel labelType = new JLabel("Type \"speed test\" as many times as you want,\n then hit ENTER", SwingConstants.CENTER);
        labelType.setFont(new Font("Serif", Font.PLAIN, 35));
        panel[1].add(labelType, BorderLayout.NORTH);
        JButton buttonOther = new JButton("hahahaha I'm in pain");
        buttonOther.addActionListener((e) -> {
            setState(State.FINISHED);
        });
        panel[1].add(buttonOther, BorderLayout.SOUTH);
        textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        panel[1].add(textArea, BorderLayout.CENTER);
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
