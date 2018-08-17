/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This is a concrete variation of an abstract Trip class. Using the Trip class's builder
 *              a Flight is constructed. But, this concrete class contains one method to validate the name.
 */

package ACTBS;


public class Flight extends Trip {
    public boolean isValidName( String name ) {
        return !(name.isEmpty());
    }
}

