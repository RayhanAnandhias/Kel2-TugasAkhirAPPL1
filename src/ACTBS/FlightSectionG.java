/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class serves as the concrete implementation of the Section abstract class.
 *              This particular type is the "generic" flight section type used by the test code.
 *              Only contains a toString method to display the letter it represents, 'G' for generic.
 */

package ACTBS;


class FlightSectionG extends Section {
    FlightSectionG( int rows, int cols, SeatClass sc ) {
        super( rows, cols, sc, 0 );
    }

    public String toString() {
        return "G";
    }
}
