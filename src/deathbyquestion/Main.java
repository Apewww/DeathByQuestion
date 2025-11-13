package deathbyquestion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;

        System.out.println("Selamat datang di Quiz Java!");
        System.out.println("1. Siapa penemu bahasa Java?");
        System.out.println("a) James Gosling");
        System.out.println("b) Elon Musk");
        System.out.println("c) Bill Gates");
        System.out.print("Jawaban: ");
        String ans1 = input.nextLine();

        if (ans1.equalsIgnoreCase("a")) {
            score += 10;
        }

        System.out.println("\nSkor akhir kamu: " + score);
        input.close();
    }
}
