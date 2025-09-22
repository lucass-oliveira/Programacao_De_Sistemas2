package ps2.titular_app;

import java.util.Scanner;

public class ES {
    private static final Scanner scan = new Scanner(System.in);

    public static String input(String msg) {
        System.out.print(msg);
        return scan.nextLine();
    }

    public static void print(String msg) {
        System.out.println(msg);
    }
}
