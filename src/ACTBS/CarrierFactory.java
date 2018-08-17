/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: A factory that constructs a defined concrete Section for a Carrier.
 */

package ACTBS;


public class CarrierFactory {
    private static CarrierFactory myInstance;

    public static CarrierFactory getInstance() {
        if (myInstance == null) {
            myInstance = new CarrierFactory();
        }
        return myInstance;
    }

    private CarrierFactory() { }

    public Carrier make(TravelType type, String name ){
        Carrier l = null;
        switch(type) {
            case air:
                l = new Airline();
                if (!l.setName(name)) { l = null; }
                break;
            case sea:
                l = new CruiseLine();
                if ( ! l.setName(name)) { l = null; }
                break;
            default:
                Log.print( "CarrierFactory asked to make nonexistent type " + type );
                break;
        }
        return l;
    }
}