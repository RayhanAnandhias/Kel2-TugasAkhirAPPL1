/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package ACTBS;

import ACTBS.Trip.TripType;

public class CruiseSystem extends BookingSystem {
    public Trip.TripType getTripType() {
        return Trip.TripType.cruise;
    }
    public String getPortType() { return "Sea Port"; }
    public String getCarrierType() { return "Cruise Line"; }
    public TravelType travelType() {
        return TravelType.sea;
    }
}
