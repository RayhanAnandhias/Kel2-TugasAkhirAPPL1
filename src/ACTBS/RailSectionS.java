package ACTBS;

public class RailSectionS extends Section {
    RailSectionS(int rows, SeatClass sc, int cost) {
        super(rows, 4, sc, cost);
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
        return "S";
    }
}