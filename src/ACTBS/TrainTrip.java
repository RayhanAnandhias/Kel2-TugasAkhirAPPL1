package ACTBS;


public class TrainTrip extends Trip {
    public boolean isValidName( String name ) {
        return !(name.isEmpty());
    }
}