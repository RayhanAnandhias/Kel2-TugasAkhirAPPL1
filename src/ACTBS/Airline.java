/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class is a concrete version of the abstract Carrier class.
 *              Implementing the isValidName(...) method, verifying if the Carrier name
 *              is of 6 characters or less.
 */

package ACTBS;


public class Airline extends Carrier {
    @Override
    protected boolean isValidName( String s ) {
        return !(s.length() >= 6 || s.isEmpty());
    }
}
