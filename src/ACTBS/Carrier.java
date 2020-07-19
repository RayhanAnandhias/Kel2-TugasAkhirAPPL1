/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This abstract class represents the name of the airline or likewise company transporting the passengers.
 *              Holding the methods that deal with Trips and books seating.
 */

package ACTBS;

import java.util.ArrayList;
import java.util.List;


public abstract class Carrier extends ObjectWithName {
    // LIST
    private List<Trip> trips = new ArrayList<>();

    public void addSectionToTrip ( String tripID, Section s ) {
        Trip t = (Trip) SearchList( tripID, trips );
        if (t == null) {
            Log.print("Cannot add section to non-existent trip " + tripID);
            return;
        }
        t.addSection(s);
    }

    public List<Trip> findTripsByOriginDest(String origin, String dest, boolean excludeFull ) {
        ArrayList<Trip> l = new ArrayList<>();
        for ( Trip t : trips ) {
            String originName = t.getOrigin().getName();
            String destName = t.getDest().getName();
            if ( originName.equals(origin) && destName.equals(dest) ) {
                if ( !( excludeFull ) || t.hasAvailableSeats() ) { l.add(t); }
            }
        }
        return l;
    }

    public ArrayList<String> listTrips() {
        return ObjectWithName.getNames(trips);
    }

    public Trip getTrip(String tripID) {
        return (Trip)SearchList( tripID, trips );
    }

    public void bookSeat(String tripID, SeatClass s, int row, char col) {
        Trip t = getTrip( tripID );
        if ( t == null ) {
            Log.print( String.format( "Cannot find trip %s on carrier %s to book a seat.", tripID, getName() ) );
        }
        else {
            t.bookSeat( s, row, col );
        }
    }

    public void addTrip( Trip t ) {
        if ( SearchList( t.getName(), trips ) == null ) {
            trips.add(t);
        }
        else {
            Log.print( "Cannot add duplicate trip ID" + t.getName() );
        }
    }

    public String dump() {
        StringBuilder s = new StringBuilder(String.format("Carrier: %s\n", getName()));
        for ( Trip t : trips ) {
            s.append(t.dump());
        }
        return s.toString();
    }

    public String toExport() {
        ArrayList<String> carrierList = new ArrayList<>();
        for (Trip t : trips) {
            carrierList.add(t.toExport());
        }
        String joinedCarrierList = String.join(", ", carrierList);

        return String.format("%s[%s]", getName(), joinedCarrierList);
    }
}
