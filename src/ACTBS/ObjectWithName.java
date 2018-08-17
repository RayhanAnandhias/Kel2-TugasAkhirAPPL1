/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This abstract class serves as a way to compare one object of related type in this package to another,
 *              helping to ensure validation and comparison.
 */

package ACTBS;

import java.util.ArrayList;
import java.util.List;


public abstract class ObjectWithName {
    private String name;

    public String getName() { return this.name; }
    public boolean setName( String name ) {
        if ( this.isValidName(name) ) {
            this.name = name.toUpperCase();
            return true;
        }
        else {
            return false;
        }
    }

    public static ArrayList<String> getNames(List list) {
        ArrayList<String> n = new ArrayList<>();
        for (ObjectWithName o : (List<ObjectWithName>)list) {
            n.add(o.getName());
        }
        return n;
    }

    protected boolean isValidName( String name ) {
        return ( ! name.isEmpty() );
    }

    public boolean equals( Object o ) {
        return o != null && o instanceof ObjectWithName && ((ObjectWithName) o).getName().equalsIgnoreCase(this.getName());
    }

    public static ObjectWithName SearchList( String name, List l ) {
        for ( Object o : l ) {
            ObjectWithName on = ( ObjectWithName ) o;
            if ( on.getName().equalsIgnoreCase( name ) ) { return on; }
        }
        return null;
    }

    public String toExport() {
        return this.name;
    }
}
