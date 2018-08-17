/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This abstract class contains all the needed info to construct a type of section of seating.
 */

package ACTBS;

import java.util.Iterator;


public abstract class Section implements Iterable<SeatStatus> {
    private int rows;
    private int columns;
    private int cost;
    private SeatStatus[][] statusOfSeats;
    private SeatClass seatClass;

    public Section(int r, int c, SeatClass sc, int cost ) {
        rows = r;
        columns = c;
        statusOfSeats = new SeatStatus[rows][columns];
        seatClass = sc;
        this.cost = cost;
        initializeSeating();
    }

    private class SectionIterator implements Iterator<SeatStatus> {
        Section sect;
        int r, c;
        int lastRow, lastCol;

        SectionIterator(Section s) {
            sect = s;
            r = 0;
            c = 0;
            lastRow = -1;
            lastCol = -1;
        }

        public boolean hasNext() {
            return (r < sect.rows || c < sect.columns);
        }

        public SeatStatus next() {
            SeatStatus st = sect.statusOfSeats[r][c];
            lastRow = r;
            lastCol = c;
            c += 1;
            if (c >= sect.columns) {
                r += 1;
                c = 0;
            }
            return st;
        }

        int getRow() {
            return lastRow + 1;
        }

        char getCol() {
            return (char) ('A' + lastCol );
        }
    }

    public SectionIterator iterator() {
        return new SectionIterator(this);
    }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public SeatClass getSeatClass() { return this.seatClass; }
    public void setSeatClass( SeatClass sc ) { this.seatClass = sc; }

    private SeatStatus getSeatStatus(int row, char col) {
        if ( checkValidSeat(row, col) ) {
            return statusOfSeats[rowNum(row)][colNum(col)];
        }
        else {
            return null;
        }
    }
    private void setSeatStatus(int row, char col, SeatStatus stat) {
        if (checkValidSeat(row, col)) {
            statusOfSeats[rowNum(row)][colNum(col)] = stat;
        }
    }

    public String getType() { return this.toString(); }

    int getNumRows() { return rows; }

    int getNumCols() { return columns; }

    private Integer rowNum(int row) {
        // We take in row numbers starting at 1, but represent them as zeroes.
        row -= 1;
        if (row >= 0 && row < rows) {
            return row;
        }
        else {
            return null;
        }
    }

    private Integer colNum(char c) {
        c = Character.toLowerCase(c);
        if (c < 'a' || c > 'z') {
            return null;
        }
        int col = c - 'a';
        return colNum(col);
    }

    private Integer colNum(int col) {
        if (col >= 0 && col < columns) {
            return col;
        }
        else {
            return null;
        }
    }

    private boolean checkValidSeat(int row, char col) {
        Integer r = rowNum(row);
        Integer c = colNum(col);
        if (r == null || c == null) {
            Log.print(String.format("Invalid seat reference: %d%c", row, col));
            return false;
        }
        return true;
    }

    private boolean isSeatAvailable(int row, char col) {
        return (checkValidSeat(row, col) && getSeatStatus(row, col) == SeatStatus.available);
    }

    public boolean seatMatchesPref( int row, char col, SeatPrefType pref ) {
        return false;
    }

    public boolean hasAvailableSeats() {
        for (SeatStatus st : this) {
            if (st == SeatStatus.available) {
                return true;
            }
        }
        return false;
    }

    public void bookSeat(int row, char col) {
        if (isSeatAvailable(row, col)) {
            setSeatStatus(row, col, SeatStatus.booked);
            //Log.print(String.format("Successfully booked seat %d%c in %s section!", row, col, seatClass ));
        }
        else {
            Log.print(String.format("Seat %d%c cannot be booked. Seat not available.", row, col));
        }
    }
    private void bookSeat() {
        if (hasAvailableSeats()) {
            SectionIterator i = iterator();
            while (i.hasNext()) {
                if (i.next() == SeatStatus.available) {
                    bookSeat(i.getRow(), i.getCol());
                    return;
                }
            }
            Log.print("Something went wrong when booking the seat.");
        }
        else {
            Log.print("No more seats available in this section.");
        }
    }
    public void bookSeat( int row, char col, SeatPrefType pref ) {
        SectionIterator i = iterator();
        while (i.hasNext()) {
            if (i.next() == SeatStatus.available && seatMatchesPref( i.getRow(), i.getCol(), pref )) {
                bookSeat(i.getRow(), i.getCol());
                //Log.print(String.format("Successfully booked seat %d%c matching preference %s in %s section!", row, col, pref, seatClass ));
                return;
            }
        }
        Log.print(String.format("No seat matches preference %s, booking anything available.", pref ));
        bookSeat();
    }

    private void initializeSeating() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++)
                statusOfSeats[r][c] = SeatStatus.available;
        }
    }

    public String dump() {
        StringBuilder s = new StringBuilder(String.format("    %s Section (Cost: $%d):\n", seatClass, cost));
        for (int y = 1; y <= rows; y++) {
            s.append(String.format("      Row %-3d %s\n", y, dumpRow(y)));
        }
        return s.toString();
    }

    public String dumpRow( int rowNum ) {
        StringBuilder s = new StringBuilder();
        for (char col = 'A'; col - 'A' < columns; col++) {
            s.append(seatStatusAsChar(rowNum, col));
        }
        return s.toString();
    }

    char seatStatusAsChar( int row, char col ) {
        switch ( getSeatStatus( row, col ) ) {
            case booked:    return 'X';
            case available: return '.';
            default:        return '?';
        }
    }

    public String toExport() {
        return getSeatClass() + ":" + getCost() + ":" + getType() + ":" + getNumRows();
    }
}
