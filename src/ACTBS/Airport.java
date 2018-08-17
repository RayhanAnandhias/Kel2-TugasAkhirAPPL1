/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This is a concrete class of the Port abstract class.
 *              Featuring the concrete method of dump() and a
 *              validation method that enforces the airport name to be three characters.
 */

package ACTBS;


public class Airport extends Port {
    @Override
    protected boolean isValidName(String name) {
        if ( name.length() != 3 ) { return false; }
        char[] lettersOfName = name.toCharArray();
        for (char letter : lettersOfName) {
            if (!Character.isLetter(letter))
                return false;
        }
        return true;
    }

    public String dump() {
        return String.format( "Airport: %s", getName() );
    }
}
