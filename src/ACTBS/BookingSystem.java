/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class serves as the liaison between the SystemManager and the other travel systems.
 */

package ACTBS;

import java.util.ArrayList;
import java.util.List;


public abstract class BookingSystem {
    // SPECIFIERS
    public abstract String getTripType();
    public abstract String getCarrierType();
    public abstract String getPortType();
    public abstract TravelType travelType();

    // LISTS
    private List<Port> ports = new ArrayList<>();
    private List<Carrier> carriers = new ArrayList<>();

    // FACTORIES
    private PortFactory portFactory = PortFactory.getInstance();
    private CarrierFactory carrierFactory = CarrierFactory.getInstance();
    private SectionFactory sectionFactory = SectionFactory.getInstance();


    public ArrayList<String> listPorts() {
        return ObjectWithName.getNames(ports);
    }

    public ArrayList<String> listCarriers() {
        return ObjectWithName.getNames(carriers);
    }

    public ArrayList<String> listTrips(String carrier) {
        Carrier c = findCarrier(carrier);
        return c.listTrips();
    }

    public ArrayList<SeatClass> listSections(String carrier, String tripID) {
        Carrier c = findCarrier(carrier);
        Trip t = c.getTrip(tripID);
        ArrayList<SeatClass> classes = t.getSeatClasses();
        return classes;
    }

    public void changeSectionCost(String carrier, String tripID, SeatClass sc, int cost) {
        findCarrier(carrier).getTrip(tripID).changeSectionCost(sc, cost);
    }

    public void createCarrier(String name) {
        Carrier l = carrierFactory.make( travelType(), name);
        if (l == null) {
            Log.print(String.format("Error creating %s %s", getCarrierType(), name));
        }
        else {
            condAdd(l, carriers, getCarrierType());
        }
    }

    public SectionType[] aproposSectionTypes() {
        return sectionFactory.aproposSectionLayouts(travelType());
    }

    public SeatClass[] aproposSeatClasses() {
        return sectionFactory.aproposSeatClasses(travelType());
    }

    public void createPort(String name) {
        Port p = portFactory.make( travelType(), name);
        if (p == null) {
            Log.print(String.format("Error creating %s %s", getPortType(), name));
        }
        else {
            condAdd(p, ports, getPortType());
        }
    }

    public void createTrip(String carrierName, String origin, String dest, int year, int month, int day, String id) {
        createTrip(carrierName, origin, dest, year, month, day, 11, 59, id);
    }

    public void createTrip(String carrierName, String origin, String dest, int year, int month, int day, int hour, int min, String id) {
        Trip.TripBuilder builder = new Trip.TripBuilder(Trip.TripType.flight);

        Carrier carrier = findCarrier(carrierName);
        if (carrier == null) {
            Log.print(String.format("Invalid %s for flight id %s.", getCarrierType(), id));
            return;
        }
        builder = builder.setOrigin(findPort(origin));
        if (builder == null) {
            Log.print(String.format("Invalid origin %s %s for %s id %s.", getPortType(), origin, getTripType(), id));
            return;
        }
        builder = builder.setDest(findPort(dest));
        if (builder == null) {
            Log.print(String.format("Invalid destination %s %s for %s id %s.", getPortType(), dest, getTripType(), id));
            return;
        }
        builder = builder.setID(id);
        if (builder == null) {
            Log.print(String.format("Invalid id %s for %s.", id, getTripType()));
            return;
        }
        builder = builder.setDate(year, month, day, hour, min);
        if (builder == null) {
            Log.print(String.format("Invalid date %04d-%02d-%02d for %s %s.", year, month, day, getTripType(), id));
            return;
        }

        carrier.addTrip(builder.build());
    }

    public void createSection(String carrierID, String tripID, int rows, SectionType type, SeatClass sclass, int seatCost) {
        createSection(carrierID, tripID, rows, 0, type, sclass, seatCost);
    }

    public void createSection(String carrierID, String tripID, int rows, int cols, SeatClass sclass) {
        createSection(carrierID, tripID, rows, cols, SectionType.flightG, sclass, 0);
    }

    public void createSection(String carrierID, String tripID, int rows, int cols, SectionType type, SeatClass sclass, int seatCost) {
        Carrier c = findCarrier(carrierID);
        if (c == null) {
            Log.print(String.format("Invalid %s %s for creating section in %s ID %s", getCarrierType(), carrierID, getTripType(), tripID));
            return;
        }
        Section s = sectionFactory.make(type, sclass, rows, cols, seatCost);
        if (s == null) {
            Log.print("Cannot create section.");
            return;
        }

        c.addSectionToTrip(tripID, s);
    }

    public void bookSeat(String carrier, String tripID, SeatClass s, int row, char col) {
        Carrier c = findCarrier(carrier);
        if (c == null) {
            Log.print(String.format("Cannot find %s %s to book seat on %s", getCarrierType(), carrier, tripID));
            return;
        }
        c.bookSeat(tripID, s, row, col);
    }

    public ArrayList<Trip> findTrips(String origin, String dest) {
        ArrayList<Trip> list = new ArrayList<>();
        for (Carrier l : carriers) {
            list.addAll(l.findTripsByOriginDest(origin, dest, true));
        }
        return list;
    }

    public void printAvailableTrips(String origin, String dest) {
        ArrayList<Trip> list = findTrips(origin, dest);
        Log.print(String.format("------%s from %s to %s with availability:", getTripType(), origin, dest));
        for (Trip t : list) {
            Log.print(String.format("Flight ID: %s (%s -> %s), departs %s", t, t.getOrigin(), t.getDest(), t.getDepartureDateStr()));
        }
        Log.print("------End list");
    }

    public void printSystemDetails() {
        System.out.println("");
        for (Port p : ports) {
            Log.print(p.dump());
        }
        System.out.println("");
        for (Carrier c : carriers) {
            Log.print(c.dump());
        }
    }

    public String toExport() {
        ArrayList<String> portList = new ArrayList<>();
        ArrayList<String> carrierList = new ArrayList<>();
        for (Port p : ports) {
            portList.add(p.toExport());
        }
        for (Carrier c : carriers) {
            carrierList.add(c.toExport());
        }
        String joinedPortList = String.join(", ", portList);
        String joinedCarrierList = String.join(", ", carrierList);

        return String.format("[%s]{%s}", joinedPortList, joinedCarrierList);
    }

    private Port findPort(String name) {
        return (Port) (ObjectWithName.SearchList(name, ports));
    }

    private Carrier findCarrier(String name) {
        return (Carrier) (ObjectWithName.SearchList(name, carriers));
    }

    private void condAdd(ObjectWithName o, List l, String desc) {
        if (l.contains(o)) {
            Log.print(String.format("Duplicate %s '%s' specified.", desc, o));
        }
        else {
            l.add(o);
        }
    }
}
