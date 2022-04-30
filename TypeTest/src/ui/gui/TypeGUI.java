package ui.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TypeGUI extends JFrame implements ActionListener, KeyListener {
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;
    private int correctWordCount = 0;
    private JButton buttonType;
    private JTextArea textArea;
    private JLabel label;
    private JLabel scoreLabel;
    private final Color backgroundColour = new Color(58, 62, 69);
    private final Color buttonColourNotHovering = new Color(45, 47, 51);
    private final Color buttonColourHovering = new Color(167, 176, 214);
    private final Color textColour = Color.white;
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

        initializePanels();

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
                buttonType.setEnabled(true);
                countDown.setText("3");
                break;
            case TYPING:
                textArea.setText("");
                textArea.setEditable(true);
                correctWordCount = 0;
                break;
            case FINISHED:
                displayScore();
                break;
        }
    }

    public void initializePanels() {
        topLayerPanel = new JPanel(new CardLayout());
        add(topLayerPanel);
        initializePanel0();
        initializePanel1();
        initializePanel2();
        CardLayout cl = (CardLayout)(topLayerPanel.getLayout());
        cl.show(topLayerPanel, currentState.toString());
    }

    private void initializePanel2() {
        panel[2] = new JPanel(new BorderLayout());
        panel[2].setBackground(backgroundColour);
        panel[2].setBorder(new EmptyBorder(50, 50, 50, 50));
        JLabel labelThird = new JLabel("Results", SwingConstants.CENTER);
        labelThird.setFont(new Font("Serif", Font.PLAIN, 35));
        labelThird.setForeground(textColour);
        labelThird.setBorder(null);
        panel[2].add(labelThird, BorderLayout.NORTH);
        JButton buttonGOGOGO = new JButton("Restart");
        buttonGOGOGO.addActionListener((e) -> {
            setState(State.PREPARING);
        });
        buttonGOGOGO.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                buttonGOGOGO.setBackground(buttonColourHovering);
            }

            public void mouseExited(MouseEvent evt) {
                buttonGOGOGO.setBackground(buttonColourNotHovering);
            }
        });
        buttonGOGOGO.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonGOGOGO.setForeground(textColour);
        buttonGOGOGO.setBackground(buttonColourNotHovering);
        buttonGOGOGO.setFont(new Font("Serif", Font.PLAIN, 35));
        panel[2].add(buttonGOGOGO, BorderLayout.SOUTH);
        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));
        scoreLabel.setForeground(textColour);
        scoreLabel.setBorder(null);
        panel[2].add(scoreLabel, BorderLayout.CENTER);
        topLayerPanel.add(panel[2], State.FINISHED.toString());
    }

    private void initializePanel1() {
        panel[1] = new JPanel(new BorderLayout());
        label = new JLabel("Type \"speed test\" as many times as you can in 60s. Good Luck :)", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        panel[1].setBackground(backgroundColour);
        label.setForeground(textColour);
        panel[1].setBorder(new EmptyBorder(50, 50, 50, 50));

        panel[1].add(label, BorderLayout.NORTH);
        textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(this);

        textArea.setBackground(backgroundColour);
        textArea.setForeground(textColour);
        textArea.setBorder(new EmptyBorder(40, 40, 40, 40));
        textArea.setCaretColor(new Color(152, 145, 179));

        panel[1].add(textArea, BorderLayout.CENTER);
        topLayerPanel.add(panel[1], State.TYPING.toString());
    }

    private void initializePanel0() {
        panel[0] = new JPanel(new BorderLayout());
        JLabel labelPrep = new JLabel("TYPING SPEED TEST", SwingConstants.CENTER);
        labelPrep.setFont(new Font("Serif", Font.PLAIN, 48));

        panel[0].setBackground(backgroundColour);
        labelPrep.setForeground(textColour);

        panel[0].add(labelPrep, BorderLayout.NORTH);
        countDown = new JTextField();
        countDown.setText("3");
        countDown.setEditable(false);
        countDown.setHorizontalAlignment(JTextField.CENTER);
        countDown.setFont(new Font("Serif", Font.PLAIN, 48));
        countDown.setBackground(backgroundColour);
        countDown.setForeground(textColour);
        countDown.setBorder(null);
        panel[0].add(countDown, BorderLayout.CENTER);
        panel[0].setBorder(new EmptyBorder(50, 50, 50, 50));

        buttonType = new JButton("Press me");
        buttonType.setFont(new Font("Serif", Font.PLAIN, 35));
        buttonType.setBackground(buttonColourNotHovering);
        buttonType.setForeground(textColour);
        buttonType.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonType.addActionListener((e) -> {
            buttonType.setEnabled(false);
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
        buttonType.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                buttonType.setBackground(buttonColourHovering);
            }

            public void mouseExited(MouseEvent evt) {
                buttonType.setBackground(buttonColourNotHovering);
            }
        });
        panel[0].add(buttonType, BorderLayout.SOUTH);
        topLayerPanel.add(panel[0], State.PREPARING.toString());
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
