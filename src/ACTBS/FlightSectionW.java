/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class serves as the concrete implementation of the Section abstract class.
 *              W is a seat layout with 10 columns with aisles between columns 3 and 4, and between columns 7 and 8.
 *              Contains methods to check seat preference, a seat availability display in rows and columns,
 *              and finally a toString to show the letter of this seating type.
 */

package ACTBS;


public class FlightSectionW extends Section {
    FlightSectionW(int rows, SeatClass sc, int cost ) {
        super( rows, 10, sc, cost );
    }

    public boolean seatMatchesPref( int row, char col, SeatPrefType pref ) {
        col = Character.toUpperCase(col);
        return (
                ( pref == SeatPrefType.window && ( col == 'A' || col == 'J' ) ) ||
                ( pref == SeatPrefType.aisle  && ( col == 'C' || col == 'D' || col == 'G' || col == 'H' ) )
        );
    }

    public String dumpRow( int rowNum ) {
        StringBuilder s = new StringBuilder();
        for (char col = 'A'; col - 'A' < getNumCols(); col++) {
            s.append(seatStatusAsChar(rowNum, col));
            if ( col == 'C' || col == 'G' ) { s.append(' '); }
        }
        return s.toString();
    }

    public String toString() {
        return "W";
    }
}
