package referencelibrary.io;

import java.util.ArrayList;

public class StubIO implements IO {

    private String[] lines;
    private int i;
    private ArrayList<String> prints;

    public StubIO(String... values) {
        this.lines = values;
        prints = new ArrayList<String>();
    }

    @Override
    public void print(String toPrint) {
        prints.add(toPrint);
    }

    public ArrayList<String> getPrints() {
        return prints;
    }

    @Override
    public String readLine(String prompt) {
        print(prompt);
        if (i < lines.length) {
            return lines[i++];
        }
        return "";
    }
}
