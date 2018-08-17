/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package ACTBS;


public class RailSystem extends BookingSystem {
    public String getTripType() {
        return "Train Trip";
    }
    public String getPortType() { return "Train Station"; }
    public String getCarrierType() { return "Railroad"; }
    public TravelType travelType() {
        return TravelType.train;
    }
}
