/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class serves as the hub to instantiate and manipulate everything one would need
 *              to generate a complete itinerary.
 */

package ACTBS;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


public class SystemManager {
    private FlightSystem flightSystem = new FlightSystem();
    private CruiseSystem cruiseSystem = new CruiseSystem();
    private RailSystem railSystem = new RailSystem();

    public void createTrain(String name) {
        createCarrier(TravelType.train, name);
    }

    public void createTrainStation(String name) {
        createPort(TravelType.train, name);
    }

    public void createTrainTripSection(String carrierID, String tripID, int rows, int cols,
        SectionType type, SeatClass sclass, int seatCost) {
            railSystem.createSection(carrierID, tripID, rows, cols, type, sclass, seatCost);
    }

    public void createTrainTrip(String TrainName, String origin, String dest, 
        int year, int month, int day, String id) {
        createTrip(TravelType.train, TrainName, origin, dest, year, month, day, id);
    }

    public void createAirline( String name ) {
        flightSystem.createCarrier(name);
    }

    public void createCarrier(TravelType t, String name) {
        getTravelSystem(t).createCarrier(name);
    }

    public void createAirport(String name) {
        createPort(TravelType.air, name);
    }

    public void createPort(TravelType t, String name) {
        getTravelSystem(t).createPort(name);
    }

    public void createFlight( String airlineName, String origin, String dest, int year, int month, int day, String id ) {
        createTrip(TravelType.air, airlineName, origin, dest, year, month, day, id);
    }

    public void createTrip(TravelType t, String carrierName, String origin, String dest, int year, int month, int day, String id) {
        getTravelSystem(t).createTrip(carrierName, origin, dest, year, month, day, id);
    }

    public void createSection( String carrierID, String tripID, int rows, int cols, SeatClass sclass ) {
        flightSystem.createSection(carrierID, tripID, rows, cols, SectionType.flightG, sclass, 0);
    }

    public void createSection( String carrierID, String tripID, int rows, SectionType type, SeatClass sclass, int seatCost ) {
        flightSystem.createSection(carrierID, tripID, rows, 1, type, sclass, seatCost);
    }

    public void createSection(TravelType t, String carrierID, String tripID, int rows, int cols, SeatClass sclass) {
        getTravelSystem(t).createSection(carrierID, tripID, rows, cols, SectionType.flightG, sclass, 0);
    }

    public void createSection(TravelType t, String carrierID, String tripID, int rows, SectionType type, SeatClass sclass, int seatCost) {
        getTravelSystem(t).createSection(carrierID, tripID, rows, 1, type, sclass, seatCost);
    }

    public void bookSeat(String airline, String flightID, SeatClass s, int row, char col ) {
        flightSystem.bookSeat(airline, flightID, s, row, col);
    }

    public void findAvailableFlights( String origin, String dest ) {
        flightSystem.printAvailableTrips(origin, dest);
    }

    public void displaySystemDetails() {
        flightSystem.printSystemDetails();
    }

    public void displaySystemDetails(TravelType t) {
        getTravelSystem(t).printSystemDetails();
    }

    public ArrayList<String> getPortNames(TravelType t) {
        return getTravelSystem(t).listPorts();
    }

    public ArrayList<String> getCarrierNames(TravelType t) {
        return getTravelSystem(t).listCarriers();
    }

    public BookingSystem getTravelSystem(TravelType t) {
        switch (t) {
            case air:
                return flightSystem;
            case sea:
                return cruiseSystem;
            case train:
                return railSystem;
            default:
                throw new IllegalArgumentException("Invalid travel type.");
        }
    }

    public ArrayList<Trip> findTrips(TravelType t, String origin, String dest) {
        return getTravelSystem(t).findTrips(origin, dest);
    }


    /* PARSER METHODS */

    public void importParser() { importParser("AMSin.txt", TravelType.air); }
    public void importParser(TravelType type ) { importParser("AMSin.txt", type); }
    public void importParser(String FileName, TravelType type) {
        File fin = new File(FileName);
        Parser par = null;
        try {
            par = new Parser(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (par != null && fin.canRead()) {
            par.read(getTravelSystem(type));
        }
    }

    public void exportParser() { exportParser("AMSout.txt", TravelType.air); }
    public void exportParser(TravelType t) { exportParser( "AMSout.txt", t ); }
    public void exportParser(String fileName, TravelType type) {
        File fout = new File(fileName);
        PrintStream ps = null;
        try {
            if (fout.exists() || fout.createNewFile()) {
                ps = new PrintStream(fout);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ps != null && fout.canWrite()) {
            ps.print( getTravelSystem(type).toExport() );
        }
    }
}
