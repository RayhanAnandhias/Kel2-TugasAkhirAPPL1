/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package ACTBS;

import ACTBS.Trip.TripType;

public class RailSystem extends BookingSystem {
    public Trip.TripType getTripType() {
        return Trip.TripType.trainTrip;
    }
    public String getPortType() { return "Train Station"; }
    public String getCarrierType() { return "Railroad"; }
    public TravelType travelType() {
        return TravelType.train;
    }
}
