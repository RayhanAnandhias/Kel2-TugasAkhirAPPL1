/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package ACTBS;


public class CruiseSystem extends BookingSystem {
    public String getTripType() {
        return "Cruise";
    }
    public String getPortType() { return "Sea Port"; }
    public String getCarrierType() { return "Cruise Line"; }
    public TravelType travelType() {
        return TravelType.sea;
    }
}
