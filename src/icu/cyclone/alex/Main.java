package icu.cyclone.alex;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type currency: ");
        System.out.println(Money.toString(sc.nextDouble()));
    }
}
