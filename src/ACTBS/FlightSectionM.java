/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class serves as the concrete implementation of the Section abstract class.
 *              M type is a seat layout with 4 columns with an aisle between columns 2 and 3.
 *              Contains methods to check seat preference, a seat availability display in rows and columns,
*               and finally a toString to show the letter of this seating type.
 */

package ACTBS;


public class FlightSectionM extends Section {
    FlightSectionM(int rows, SeatClass sc, int cost ) {
        super( rows, 4, sc, cost );
    }

    public boolean seatMatchesPref( int row, char col, SeatPrefType pref ) {
        col = Character.toUpperCase(col);
        return (
                ( pref == SeatPrefType.window && ( col == 'A' || col == 'D' ) ) ||
                ( pref == SeatPrefType.aisle  && ( col == 'B' || col == 'C' ) )
        );
    }

    public String dumpRow( int rowNum ) {
        StringBuilder s = new StringBuilder();
        for (char col = 'A'; col - 'A' < getNumCols(); col++) {
            s.append(seatStatusAsChar(rowNum, col));
            if ( col == 'B' ) { s.append(' '); }
        }
        return s.toString();
    }

    public String toString() {
        return "M";
    }
}
