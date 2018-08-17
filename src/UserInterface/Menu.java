/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package UserInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public abstract class Menu {
    private ArrayList<MenuOption> options;
    private Scanner in = new Scanner(System.in);
    private boolean shouldExit;
    private int lastSelection;
    private String title;

    Menu(String t) {
        title = t;
        options = new ArrayList<>();
        shouldExit = false;
        init();
    }

    public abstract void init();

    public void main() {
        if (options.size() < 1) {
            return;
        }
        while (!shouldExit) {
            printMenu();
            doCommand();
        }
    }

    void addOption(MenuOption o) {
        options.add(o);
    }

    public void removeOption(String id) {
        options.remove(id);
    }
    void removeOption(MenuOption m) {
        options.remove(m);
    }

    private void exit() {
        shouldExit = true;
    }
    void exit(Object o) {
        this.exit();
    }

    String promptForLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }

    public char promptForChar(String prompt) {
        String s;
        do {
            s = promptForLine(prompt);
        }
        while (s.length() == 0);

        return s.charAt(0);
    }

    int promptForInt(String prompt) {
        while (true) {
            String s = promptForLine(prompt);
            try {
                return Integer.parseInt(s);
            } catch (Exception ignored) {}
        }
    }
    private int promptForInt(String prompt, int min, int max) {
        int i = min - 1;
        while (i < min || i > max) {
            i = promptForInt(prompt);
        }
        return i;
    }

    int[] promptForDate(String prompt) {
        while (true) {
            String s = promptForLine(prompt).trim();
            if (s.matches("^\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}$")) {
                String[] pieces = s.split("[-./]");
                int year = Integer.parseInt(pieces[0]);
                int month = Integer.parseInt(pieces[1]);
                int day = Integer.parseInt(pieces[2]);

                Calendar.Builder cb = new Calendar.Builder();

                try {
                    cb.setDate(year, month, day).setTimeOfDay(0, 0, 0).build();
                    return new int[] { year, month, day };
                } catch (IllegalArgumentException e) { /* go back through the loop, date is invalid */ }
            }
        }
    }

    public String stringPicker(String prompt, ArrayList<String> l) {
        if (l.size() == 0) {
            System.out.println("Nothing to select from!");
            return "";
        }
        for (int i = 1; i <= l.size(); i++) {
            System.out.format("%d: %s\n", i, l.get(i - 1));
        }
        int resp = -1;
        while (resp < 1 || resp > l.size()) {
            resp = promptForInt(prompt);
        }

        return l.get(resp - 1);
    }

    private void doCommand() {
        int n = promptForInt("Enter your selection: ", 1, options.size());
        lastSelection = n;
        MenuOption o = options.get(n - 1);
        o.doCommand(null);
    }

    public int getLastSelection() {
        return lastSelection;
    }

    private void printMenu() {
        System.out.format("\nTRAVEL BOOKING SYSTEM\n=====================\n%s\n", title);
        int count = 1;
        for (MenuOption m : options) {
            System.out.format("%d: %s\n", count++, m);
        }
    }
}
