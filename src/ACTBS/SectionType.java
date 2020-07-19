/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: An enum that contains the list of defined types of sections on a Carrier.
 */

package ACTBS;


public enum SectionType {
    trainTripM("M"),
    trainTripS("S"),
    flightG("G"),
    flightS("S"),
    flightM("M"),
    flightW("W");

    SectionType(String letter) {}
}
