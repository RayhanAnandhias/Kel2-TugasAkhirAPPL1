/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description:
 */

package UserInterface;

import ACTBS.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class TravelMenu extends Menu {
    private TravelType travelType;
    private BookingSystem bsys;
    private SystemManager mgr;
    private boolean adminMode = false;
    private MenuOption adminon;
    private ArrayList<MenuOption> adminOptions;

    TravelMenu(TravelType t, SystemManager mgr) {
        super(String.format("%s System Menu", t.toString().toUpperCase()));
        this.mgr = mgr;
        travelType = t;
        bsys = mgr.getTravelSystem(t);
    }

    public void init() {
        adminon = new MenuOption("adminon", "Enter admin mode", this::adminModeOn);
        addOption(new MenuOption("import", "Read input file", this::readFile));
        addOption(new MenuOption("chprice", "Change price for individual section", this::changeSectionPrice));
        addOption(new MenuOption("searchavail", "Look for available bookings", this::searchSeats));
        addOption(new MenuOption("chstdprice", "Change standard section pricing", this::changePricing));
        addOption(new MenuOption("bookseat", "Book a specific accommodation", this::BookBySeat));
        addOption(new MenuOption("bookpref", "Book by preference", this::BookByPref));
        addOption(new MenuOption("details", "Display system details", this::Dump));
        addOption(new MenuOption("export", "Write my state to output file", this::writeFile));
        addOption(adminon);
        addOption(new MenuOption("exit", "Return to main menu", this::exit));
    }

    private void createPort(Object o) {
        String name = promptForLine(String.format("Enter the new %s name:  ", bsys.getPortType()));
        mgr.createPort(travelType, name);
    }

    private void createCarrier(Object o) {
        String name = promptForLine(String.format("Enter the new %s name:  ", bsys.getCarrierType()));
        mgr.createCarrier(travelType, name);
    }

    private void createTrip(Object o) {
        String carrier = chooseCarrier();
        String origin = choosePort(String.format("Select origin %s: ", bsys.getPortType()));
        String dest = choosePort(String.format("Select destination %s: ", bsys.getPortType()));
        int[] date = promptForDate("Enter the departure date (YYYY-MM-DD): ");
        String id = promptForLine("Enter the trip ID: ");
        mgr.createTrip(travelType, carrier, origin, dest, date[0], date[1], date[2], id);
    }

    private void createSection(Object o) {
        String carrier = chooseCarrier();
        String tripID = chooseCarrierTrip(carrier);

        ArrayList<SectionType> types = new ArrayList<>();
        Collections.addAll(types, bsys.aproposSectionTypes());
        ListPicker<SectionType> lp = new ListPicker<>("Choose a section layout: ", types);
        SectionType type = lp.pickFromList();

        ArrayList<SeatClass> classes = new ArrayList<>();
        Collections.addAll(classes, bsys.aproposSeatClasses());
        ListPicker<SeatClass> lp2 = new ListPicker<>("Choose a class: ", classes);
        SeatClass seatClass = lp2.pickFromList();

        int rows = promptForInt("Number of rows: ");
        int cost = promptForInt("Cost per booking: ");

        mgr.createSection(travelType, carrier, tripID, rows, type, seatClass, cost);
    }

    private void adminModeOn(Object o) {
        if (adminOptions == null) {
            adminOptions = new ArrayList<>();
            adminOptions.add(new MenuOption("newport", String.format("(admin) Create a new %s", bsys.getPortType()), this::createPort));
            adminOptions.add(new MenuOption("newcarrier", String.format("(admin) Create a new %s", bsys.getCarrierType()), this::createCarrier));
            adminOptions.add(new MenuOption("newtrip", String.format("(admin) Create a new %s", bsys.getTripType()), this::createTrip));
            adminOptions.add(new MenuOption("newsect", String.format("(admin) Create a new %s section", bsys.getTripType()), this::createSection));
            adminOptions.add(new MenuOption("adminoff", "Exit admin mode", this::adminModeOff));
        }
        if (adminMode) {
            return;
        }
        adminMode = true;
        removeOption(adminon);
        for (MenuOption m : adminOptions) {
            addOption(m);
        }
    }

    private void adminModeOff(Object o) {
        if (!adminMode) {
            return;
        }
        adminMode = false;
        for (MenuOption m : adminOptions) {
            removeOption(m);
        }
        addOption(adminon);
    }

    private String chooseCarrier() {
        ArrayList<String> carrierNames = bsys.listCarriers();
        return StringPicker.pickString(String.format("Choose %s: ", bsys.getCarrierType()), carrierNames);
    }

    private String chooseCarrierTrip(String carrier) {
        ArrayList<String> tripNames = bsys.listTrips(carrier);
        return StringPicker.pickString(String.format("Choose %s ID: ", bsys.getTripType()), tripNames);
    }

    private SeatClass chooseSection(String carrier, String tripID) {
        ArrayList<SeatClass> sections = bsys.listSections(carrier, tripID);
        ListPicker<SeatClass> lp = new ListPicker<SeatClass>("Choose section: ", sections);
        SeatClass sc = lp.pickFromList();
        return sc;
    }

    private String choosePort() {
        return choosePort(String.format("Choose %s: ", bsys.getPortType()));
    }

    private String choosePort(String prompt) {
        ArrayList<String> portNames = bsys.listPorts();
        return StringPicker.pickString(prompt, portNames);
    }

    private void readFile(Object o) {
        String input = fileInputPrompt();

        while (!input.toUpperCase().equals("EXIT")) {
            File fin = new File(input);
            if (fin.exists() && fin.canRead()) {
                mgr.importParser(input, travelType);
                System.out.println("\nIMPORT SUCCESSFUL\n\n");
                return;
            }
            else {
                System.out.println("\n*** Could not find file of that name in the directory, try again or type EXIT. ***\n");
                input = fileInputPrompt();
            }
        }
    }

    private String fileInputPrompt() {
        System.out.print("Enter File Name (enter EXIT to back out): ");
        return new Scanner(System.in).next();
    }

    private void changeSectionPrice(Object o) {
        String carrier = chooseCarrier();
        if (carrier == null) {
            return;
        }
        String trip = chooseCarrierTrip(carrier);
        if (trip == null) {
            return;
        }
        SeatClass sc = chooseSection(carrier, trip);
        if (sc == null) {
            return;
        }
        int cost = promptForInt("New price: ");
        // TODO: Range checking
        bsys.changeSectionCost(carrier, trip, sc, cost);
    }

    private void searchSeats(Object o) {
    }

    private void changePricing(Object o) {
    }

    private void BookBySeat(Object o) {
    }

    private void BookByPref(Object o) {
    }

    private void Dump(Object o) {
        mgr.displaySystemDetails(travelType);
    }

    private void writeFile(Object o) {
        String input = fileInputPrompt();
        while (!input.toUpperCase().equals("EXIT")) {
            if (input.matches("[a-zA-Z]*.txt")) {
                mgr.exportParser(input, travelType);
                System.out.println("\nEXPORT SUCCESSFUL\n\n");
                return;
            }
            else {
                System.out.println("\n*** The file name you entered must end with '.txt' as it's file extension, try again or type EXIT. ***\n");
                input = fileInputPrompt();
            }
        }
    }
}
