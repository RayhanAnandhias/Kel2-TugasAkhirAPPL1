/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: A factory that constructs a defined concrete Section for a Section.
 */

package ACTBS;


public class SectionFactory {
    private static SectionFactory myInstance = null;

    private SectionFactory() { /* Intentionally empty */ }

    public static SectionFactory getInstance() {
        if (myInstance == null) {
            myInstance = new SectionFactory();
        }
        return myInstance;
    }

    public SectionType[] aproposSectionLayouts(TravelType t) {
        switch (t) {
            case air:
                SectionType[] xAir = {SectionType.flightS, SectionType.flightM, SectionType.flightW};
                return xAir;
            case train:
                SectionType[] xTrain = {SectionType.trainTripS, SectionType.trainTripM};
                return xTrain;
            default:
                SectionType[] y = {};
                return y;
        }
    }

    public SeatClass[] aproposSeatClasses(TravelType t) {
        switch (t) {
            case air:
                SeatClass[] xAir = {SeatClass.first, SeatClass.economy, SeatClass.business};
                return xAir;
            case train:
                SeatClass[] xTrain = {SeatClass.first, SeatClass.economy};
                return xTrain;
            default:
                SeatClass[] y = {};
                return y;
        }
    }

    public Section make( SectionType type, SeatClass seatClass, int rows, int cols, int seatCost ) {
        Section s;
        if ( rows < 1 || rows > 100 ) {
            Log.print(String.format("Invalid number of rows: %d", rows));
            return null;
        }
        if ( cols < 1 || cols > 10 ) {
            Log.print( String.format("Invalid number of columns: %d", cols ) );
            return null;
        }
        switch(type) {
            case flightG:
                s = new FlightSectionG(rows, cols, seatClass);
                break;
            case flightS:
                s = new FlightSectionS(rows, seatClass, seatCost);
                break;
            case flightM:
                s = new FlightSectionM(rows, seatClass, seatCost);
                break;
            case flightW:
                s = new FlightSectionW(rows, seatClass, seatCost);
                break;
            case trainTripM:
                s = new RailSectionM(rows, seatClass, seatCost);
                break;
            case trainTripS:
                s = new RailSectionS(rows, seatClass, seatCost);
                break;
            default:
                throw new IllegalArgumentException( "No type " + type + "." );
        }
        s.setSeatClass(seatClass);
        return s;
    }
}