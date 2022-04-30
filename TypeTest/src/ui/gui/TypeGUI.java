package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TypeGUI extends JFrame implements ActionListener, KeyListener {
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;
    private int correctWordCount = 0;
    private int wpm = 0;
    private JPanel typePanel;
    private JTextArea textArea;
    private JLabel label;
    private JLabel scoreLabel;

    private State currentState = State.PREPARING;
    private JPanel topLayerPanel;
    private JPanel[] panel = new JPanel[3];

    JTextField countDown;

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

    void timer() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            textArea.setEditable(false);
            parseTextArea();
            setState(State.FINISHED);
        }, 60, TimeUnit.SECONDS);
    }

    public void displayScore() {
        scoreLabel.setText("Score: " + correctWordCount + " WPM");
    }

    public void parseTextArea() {
        String text = textArea.getText();
        String speed = "speed";
        String test = "test";
        if(text.length()==0) {
            correctWordCount = 0;
            return;
        }
        String[] splitText = text.split("\\s+");
        for(int i = 0; i < splitText.length; i++) {
            if (i % 2 == 0) {
                if (speed.equalsIgnoreCase(splitText[i])) {
                    correctWordCount++;
                }
            } else {
                if (test.equalsIgnoreCase(splitText[i])) {
                    correctWordCount++;
                }
            }
        }
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
                textArea.setEditable(true);
                break;
            case FINISHED:
                displayScore();
                break;
        }
    }

    public void startPanel() {
        topLayerPanel = new JPanel(new CardLayout());
        add(topLayerPanel);

        panel[0] = new JPanel(new BorderLayout());
        JLabel labelPrep = new JLabel("TYPE FAST", SwingConstants.CENTER);
        labelPrep.setFont(new Font("Serif", Font.PLAIN, 48));
        panel[0].add(labelPrep, BorderLayout.NORTH);
        countDown = new JTextField();
        countDown.setText("3");
        countDown.setEditable(false);
        countDown.setHorizontalAlignment(JTextField.CENTER);
        countDown.setFont(new Font("Serif", Font.PLAIN, 48));
        panel[0].add(countDown, BorderLayout.CENTER);
        JButton buttonType = new JButton("Press me");
        buttonType.setFont(new Font("Serif", Font.PLAIN, 35));
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
                timer();
            }, 3, TimeUnit.SECONDS);

        });
        panel[0].add(buttonType, BorderLayout.SOUTH);
        topLayerPanel.add(panel[0], State.PREPARING.toString());

        panel[1] = new JPanel(new BorderLayout());
        label = new JLabel("Type \"speed test\" as many times as you can in 60s. Good Luck :)", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 30));

        panel[1].add(label, BorderLayout.NORTH);
        JButton buttonOther = new JButton("hahahaha I'm in pain");
        buttonOther.addActionListener((e) -> {
            setState(State.FINISHED);
        });
        buttonOther.setBackground(Color.PINK);
        buttonOther.setForeground(Color.WHITE);
        panel[1].add(buttonOther, BorderLayout.EAST);
        textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(this);
//        textArea.setEditable(false);
        panel[1].add(textArea, BorderLayout.CENTER);
        topLayerPanel.add(panel[1], State.TYPING.toString());

        panel[2] = new JPanel(new BorderLayout());
        JLabel labelThird = new JLabel("Results", SwingConstants.CENTER);
        panel[2].add(labelThird, BorderLayout.NORTH);
        JButton buttonGOGOGO = new JButton("Your time is TIME");
        buttonGOGOGO.addActionListener((e) -> {
            setState(State.PREPARING);
        });
        panel[2].add(buttonGOGOGO, BorderLayout.CENTER);
        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));
        panel[2].add(scoreLabel, BorderLayout.SOUTH);
        topLayerPanel.add(panel[2], State.FINISHED.toString());

        CardLayout cl = (CardLayout)(topLayerPanel.getLayout());
        cl.show(topLayerPanel, currentState.toString());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
