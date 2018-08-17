/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This is a concrete class of the Port abstract class.
 *              Featuring the concrete method of dump() and a
 *              validation method that enforces the airport name to be three characters.
 */

package ACTBS;


public class Seaport extends Port {
    @Override
    protected boolean isValidName(String name) {
        return name.length() <= 20;
    }

    public String dump() {
        return String.format("Seaport %s", getName());
    }
}
