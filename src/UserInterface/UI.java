/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: Main entry point into this console based Java program.
 */

package UserInterface;


import ACTBS.SystemManager;

public class UI {
    public static void main(String[] args) {
        SystemManager res = new SystemManager();
        MainMenu m = new MainMenu(res);
        m.main();
    }
}
