package ui;

import ui.gui.TypeGUI;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        new TypeGUI();
        String input = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Typing Speed Test");
        System.out.println("Type this: \n");
        for(int i = 0; i < 3; i++) {
            System.out.println("speed test");
        }
        System.out.println("\n");
        System.out.println(scanner.nextLine());



    }
}
