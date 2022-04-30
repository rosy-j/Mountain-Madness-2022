package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TypeGUI extends JFrame implements ActionListener, KeyListener {
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;
    private int correctWordCount = 0;
    private int wpm = 0;
    private long startTime = System.currentTimeMillis();
    private long elapsedTime = 0L;
    private JPanel typePanel;
    private JTextArea textArea;
    private JLabel label;
    private JLabel scoreLabel;

    public TypeGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);

        startPanel();

        pack();
        setVisible(true);
        while(elapsedTime < 60*1000) {
            elapsedTime = System.currentTimeMillis()- startTime;
        }
        textArea.setEditable(false);
        parseTextArea();
        displayScore();

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
        for(int i = 1; i < splitText.length; i++) {
            System.out.println("tring: " + splitText[i]);
            if (i % 2 == 0) {
                if (test.equalsIgnoreCase(splitText[i])) {
                    System.out.println("Test: " + splitText[i]);
                    correctWordCount++;
                }
            } else {
                if (speed.equalsIgnoreCase(splitText[i])) {
                    System.out.println("speed: " + splitText[i]);
                    correctWordCount++;
                }
            }
        }
    }

    public void startPanel() {
        typePanel = new JPanel(new BorderLayout());

        label = new JLabel("Type \"speed test\" as many times as you can in 60s. Press ENTER to start :)", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 30));


        textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(this);
        textArea.setEditable(false);

        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));

        typePanel.add(label, BorderLayout.NORTH);
        typePanel.add(textArea, BorderLayout.CENTER);
        typePanel.add(scoreLabel, BorderLayout.SOUTH);

        add(typePanel);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==(KeyEvent.VK_ENTER)) {
            textArea.setEditable(true);
            startTime = System.currentTimeMillis();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
