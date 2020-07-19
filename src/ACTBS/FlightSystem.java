/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package ACTBS;

import ACTBS.Trip.TripType;

public class FlightSystem extends BookingSystem {
    public Trip.TripType getTripType() {
        return Trip.TripType.flight;
    }
    public String getPortType() {
        return "Airport";
    }
    public String getCarrierType() {
        return "Airline";
    }
    public TravelType travelType() {
        return TravelType.air;
    }
}
