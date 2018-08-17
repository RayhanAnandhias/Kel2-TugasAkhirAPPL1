/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class is basically a toString(...) method, a singleton instance that prints out the needed data.
 */

package ACTBS;


public class Log {
    private static Log ourInstance = new Log();

    private Log() {}

    public static Log getInstance() {
        return ourInstance;
    }

    public static void print( String s ) {
        System.out.println( s );
    }
}