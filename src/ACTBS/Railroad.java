package ACTBS;


public class Railroad extends Carrier {
    @Override
    protected boolean isValidName( String s ) {
        return !(s.length() >= 20 || s.isEmpty());
    }
}