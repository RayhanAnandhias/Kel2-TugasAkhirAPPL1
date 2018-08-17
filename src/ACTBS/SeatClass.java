/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: An enum that contains the list of types of seat classes one could book, like flight class or economy.
 */

package ACTBS;


public enum SeatClass {
    first("F"),
    business("B"),
    economy("E");

    private final String letter;

    SeatClass(String letter) {
        this.letter = letter;
    }

    public String toString() {
        return this.letter;
    }
}