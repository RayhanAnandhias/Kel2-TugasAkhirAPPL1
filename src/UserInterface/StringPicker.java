/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package UserInterface;

import ACTBS.Log;
import java.util.ArrayList;


public class StringPicker extends Menu {
    private StringPicker(String title, ArrayList<String> items) {
        super(title);
        for (String s : items) {
            addOption(new MenuOption(s, this::exit));
        }
    }

    public static String pickString(String t, ArrayList<String> a) {
        StringPicker sp = new StringPicker(t, a);
        sp.main();
        int last = sp.getLastSelection();
        if (last <= 0) {
            Log.print("No items to choose from!");
            return null;
        }

        return a.get(last - 1);
    }

    public void init() {
    }
}
