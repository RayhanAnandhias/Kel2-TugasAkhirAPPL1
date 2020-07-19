/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This abstract class represents a hub or port.
 *              Features one abstract method that calls the name of the port and a method to retrieve the
*               name of the airport for the export method.
 */

package ACTBS;


public abstract class Port extends ObjectWithName {
    public abstract String dump();

    public String toExport() {
        return getName();
    }
    
    @Override
    public String toString () {
		return getName();
    }
}
