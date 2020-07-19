package ACTBS;

public class Station extends Port {

    @Override
    protected boolean isValidName(String name) {
        if ( name.length() != 3 ) { return false; }
        char[] lettersOfName = name.toCharArray();
        for (char letter : lettersOfName) {
            if (!Character.isLetter(letter))
                return false;
        }
        return true;
    }

    @Override
    public String dump() {
        return String.format( "Train Station: %s", getName() );
    }
    
}