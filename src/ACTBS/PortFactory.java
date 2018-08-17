/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: A factory that constructs a defined concrete Section for a Port.
 */

package ACTBS;


public class PortFactory {
    private static PortFactory myInstance = null;

    private PortFactory() { /* Intentionally empty */ }

    public static PortFactory getInstance() {
        if (myInstance == null) {
            myInstance = new PortFactory();
        }
        return myInstance;
    }

    public Port make(TravelType type, String name ){
        Port p = null;
        switch(type) {
            case air:
                p = new Airport();
                break;
            case sea:
                p = new Seaport();
                break;
        }
        if (p != null && !p.setName(name)) {
            p = null;
        }
        return p;
    }
}