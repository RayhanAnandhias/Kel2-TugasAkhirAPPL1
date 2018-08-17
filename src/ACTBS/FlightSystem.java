/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package ACTBS;


public class FlightSystem extends BookingSystem {
    public String getTripType() {
        return "Flight";
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
