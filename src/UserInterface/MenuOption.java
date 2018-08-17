/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package UserInterface;

import java.util.function.Consumer;


public class MenuOption implements Comparable {
    private String description;
    private String id;
    private Consumer<Object> fnRef;

    MenuOption(String id, String desc, Consumer<Object> f) {
        this.id = id;
        description = desc;
        fnRef = f;
    }
    MenuOption(String desc, Consumer<Object> f) {
        this.id = "x";
        this.description = desc;
        this.fnRef = f;
    }

    public void doCommand(Object o) {
        fnRef.accept(o);
    }

    public String toString() {
        return String.format("%s", description);
    }

    public String getid() {
        return this.id;
    }

    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        if (o instanceof String) {
            return id.compareTo((String) o);
        }
        if (o instanceof MenuOption) {
            return id.compareTo(((MenuOption) o).id);
        }

        return -1;
    }

    public boolean equals(Object o) {
        return (this.compareTo(o) == 0);
    }
}
