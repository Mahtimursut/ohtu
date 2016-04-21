package referencelibrary.io;

import java.util.Scanner;

public class ConsoleIO implements IO {
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public String readLine(String prompt) {
        System.out.print(prompt + " ");
        String nextLine;
        try {
            nextLine = scanner.nextLine();
        } catch (Exception ex) {
            nextLine = "";
        }
        return nextLine;
    }
    
}