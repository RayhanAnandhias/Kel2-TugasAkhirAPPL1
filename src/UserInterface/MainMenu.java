/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package UserInterface;

import ACTBS.SystemManager;
import ACTBS.TravelType;


public class MainMenu extends Menu {
    private SystemManager mgr;

    MainMenu() {
        super("Main Menu -----------");
        mgr = new SystemManager();
    }
    MainMenu(SystemManager mgr) {
        super("Main Menu -----------");
        this.mgr = mgr;
    }

    public void init() {
        addOption(new MenuOption("air", "Work with Airline System", this::airlineMenu));
        addOption(new MenuOption("sea", "Work with Cruise Ship System ", this::cruiseMenu));
        addOption(new MenuOption("train", "Work with Rail System", this::railMenu));
        addOption(new MenuOption("exit", "Quit", this::exit));
    }

    private void airlineMenu(Object o) {
        TravelMenu tm = new TravelMenu(TravelType.air, mgr);
        tm.main();
    }

    private void cruiseMenu(Object o) {
        TravelMenu tm = new TravelMenu(TravelType.sea, mgr);
        tm.main();
    }

    private void railMenu(Object o) {
        TravelMenu tm = new TravelMenu(TravelType.train, mgr);
        tm.main();
    }
}
