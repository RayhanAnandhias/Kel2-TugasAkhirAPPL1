/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This abstract method contains all the data needed to generate a concrete trip (Flight).
 *              A nested class called TripBuilder takes all the needed info and constructs a trip.
 */

package ACTBS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public abstract class Trip extends ObjectWithName {
    private Port origin;
    private Port dest;
    private int departureYear;
    private int departureMonth;
    private int departureDay;
    private int departureHour;
    private int departureMinute;
    private List<Section> sections = new ArrayList<Section>();

    public String dateAsString() {
        return String.format("%04d-%02d-%02d", departureYear, departureMonth, departureDay);
    }

    public String dump() {
        StringBuilder s = new StringBuilder(String.format(
                "  Trip: %s %s -> %s departs %04d-%02d-%02d\n",
                getName(), origin.getName(), dest.getName(), departureYear, departureMonth, departureDay
        ));
        for ( Section sect : sections ) {
            s.append(sect.dump());
        }
        return s.toString();
    }

    public String summary() {
        return String.format(
                "%s: %s -> %s, departs %04d-%02d-%02d",
                getName(), origin.getName(), dest.getName(), departureYear, departureMonth, departureDay
                );
    }

    public Port getOrigin() {
        return origin;
    }
    public Section getSection(SeatClass c) {
        for (Section s : sections) {
            if (s.getSeatClass() == c) {
                return s;
            }
        }
        return null;
    }
    public Port getDest() {
        return dest;
    }
    public String getDepartureDateStr() {
        return String.format( "%04d-%02d-%02d", departureYear, departureMonth, departureDay );
    }
    private String getTripDate() {
        return String.format("%d, %d, %d, %d, %d", departureYear, departureMonth, departureDay, departureHour, departureMinute);
    }

    public enum TripType {flight, trainTrip, cruise}

    public void bookSeat( SeatClass c, int row, char col ) {
        Section s = getSection(c);
        if ( s == null ) {
            Log.print( String.format( "No %s section exists on flight %s", c, getName() ));
            return;
        }
        s.bookSeat( row, col );
    }

    public boolean hasAvailableSeats() {
        for ( Section s : sections ) {
            if (s.hasAvailableSeats()) {
                return true;
            }
        }
        return false;
    }

    public boolean addSection( Section newSection ) {
        if ( newSection == null ) {
            Log.print( "Passed null object for new Section." );
            return false;
        }
        SeatClass sc = newSection.getSeatClass();
        if ( getSection( sc ) != null ) {
            Log.print( String.format( "Cannot add new section to flight %s: duplicate seat class %s", getName(), sc ));
            return false;
        }
        sections.add( newSection );
        return true;
    }

    public void changeSectionCost(SeatClass sc, int cost) {
        for (Section s : sections) {
            if (s.getSeatClass() == sc) {
                s.setCost(cost);
                return;
            }
        }
    }

    public ArrayList<SeatClass> getSeatClasses() {
        ArrayList<SeatClass> l = new ArrayList<>();
        for (Section s : sections) {
            SeatClass c = s.getSeatClass();
            if (!l.contains(c)) {
                l.add(c);
            }
        }
        return l;
    }

    public String toExport() {
        ArrayList<String> sectionList = new ArrayList<>();
        for (Section s : sections) {
            sectionList.add(s.toExport());
        }
        String joinedSectionList = String.join(", ", sectionList);

        return String.format("%s|%s|%s|%s[%s]", getName(), getTripDate(), getOrigin().toExport(), getDest().toExport(), joinedSectionList);
    }


    public static class TripBuilder {
        private Trip instance;
        private boolean originSet, destSet, dateSet, idSet;
        private TripType type;

        public TripBuilder( Trip.TripType type ) {
            this.type = type;
            initInstance();
        }

        private void initInstance( ) {
            destSet = originSet = dateSet = idSet = false;
            switch ( type ) {
                case flight:
                    instance = new Flight();
                    break;
                case trainTrip:
                    instance = new TrainTrip();
                    break;
                default:
                    throw new IllegalArgumentException( "Invalid type" );
            }
        }

        public TripBuilder setOrigin( Port p ) {
            if ( p == null ) { return null; }
            if ( ! p.equals( instance.dest ) ) {
                instance.origin = p;
                originSet = true;
                return this;
            }
            else { return null; }
        }
        public TripBuilder setDest( Port p ) {
            if ( p == null ) { return null; }
            if ( ! p.equals( instance.origin ) ) {
                instance.dest = p;
                destSet = true;
                return this;
            }
            else { return null; }
        }
        public TripBuilder setID( String s ) {
            if ( instance.setName( s ) ) {
                idSet = true;
                return this;
            }
            else {
                return null;
            }
        }
        public TripBuilder setDate( int year, int month, int day, int hour, int min ) {
            Calendar departureDate = Calendar.getInstance();
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
            sdfrmt.setLenient(false);
            Calendar now;
            try {
            	Date javaDate = sdfrmt.parse(month+"/"+day+"/"+year);
                departureDate.set(Calendar.YEAR, year);
                departureDate.set(Calendar.MONTH, year);
                departureDate.set(Calendar.DAY_OF_MONTH, year);
                departureDate.set(Calendar.HOUR_OF_DAY, year);
                departureDate.set(Calendar.MINUTE, year);
            } catch ( IllegalArgumentException | ParseException e ) {
                return null;
            }

            now = Calendar.getInstance();

            if (departureDate.getTime().before(now.getTime())) { return null; }

            instance.departureYear = year;
            instance.departureMonth = month;
            instance.departureDay = day;
            instance.departureHour = hour;
            instance.departureMinute = min;
            dateSet = true;

            return this;
        }

        public Trip build() {
            if ( destSet && originSet && dateSet && idSet ) {
                Trip ret = instance;
                initInstance();

                return ret;
            }
            else {
                return null;
            }
        }
    }
}