/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package UserInterface;

import ACTBS.Log;
import java.util.ArrayList;


public class ListPicker<E> extends Menu {
    private ArrayList<E> items;

    ListPicker(String title, ArrayList<E> items) {
        super(title);
        this.items = items;
        for (E o : items) {
            String s = o.toString();
            addOption(new MenuOption(s, this::exit));
        }
    }

    public E pickFromList() {
        main();
        int last = getLastSelection();
        if (last < 0) {
            Log.print("No items to choose from!");
            return null;
        }

        return items.get(last - 1);
    }

    public void init() {
    }
}
