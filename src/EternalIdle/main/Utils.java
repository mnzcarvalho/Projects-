package EternalIdle.main;

import java.util.Scanner;

public class Utils {
    public static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Digite um número válido: ");
        }
        return sc.nextInt();
    }

    public static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.print("Digite um número válido: ");
        }
        return sc.nextDouble();
    }
}