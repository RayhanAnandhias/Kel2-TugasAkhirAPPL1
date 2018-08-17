/**
 * Contributors: Tony Kolstee and Matthew Jett
 * Class: Design Patterns CSCD349-01 with Tom Capaul Spring 2018
 * Description: This class does all the work for reading a file and messing with it contents.
 *              Basically a template method.
 */

package ACTBS;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Parser {
    private Scanner sc;
    private BookingSystem system;
    private String document;

    Parser(File source) throws IOException {
        sc = new Scanner(source);
    }

    public void read(BookingSystem type) {
        system = type;

        // FORMAT: trim out random white space in document and add spacing around delimiters for easier parsing
        trimDocument();
        spaceOutDelimiters();

        // RESET: pointer in file back to beginning, but with new adjusted version
        reset();

        // TEMPLATE: order to parsing the data and inserting it into the respective locations
        importPorts();
        importCarriers();

        sc.close();
    }

    private void trimDocument() {
        StringBuilder sb = new StringBuilder();
        sc.useDelimiter(" ");
        while (sc.hasNext()) {
            sb.append(sc.next().trim());
        }
        document = String.valueOf(sb);
    }

    private void spaceOutDelimiters() {
        document = document.replaceAll("[\\[]", " [ ").replaceAll("[]]", " ] ").replaceAll("[{]", " { ").replaceAll("[}]", " } ").replaceAll("[|]", " | ");
    }

    private void reset() {
        sc.close();
        sc = new Scanner(document);
    }

    private void importPorts() {
        String name;
        sc.useDelimiter("[\\[, ]");
        while (!sc.hasNext("]")) {
            name = sc.next();
            if (!name.equals("")) {
                system.createPort(name.trim());
            }
        }
        // Skip trailing characters to start next section
        sc.findWithinHorizon("[{]", 5);
    }

    private void importCarriers() {
        String name;
        sc.reset();
        do {
            name = sc.findInLine("[a-zA-Z]{4}");
            if (name == null) break;
            system.createCarrier(name);
            importTrips(name);
        } while (true);
    }

    private void importTrips(String name) {
        Scanner subScan = null;
        String trip;
        String date;
        String section;
        String carrierName;
        String origin;
        String dest;
        int year;
        int month;
        int day;
        int hour;
        int min;
        String tripID;

        sc.findWithinHorizon("[\\[] ", 5);
        sc.useDelimiter(" *]");
        while (!sc.hasNext("")) {
            trip = sc.next().replaceAll(" ", "") + "]";
            subScan = new Scanner(trip);
            carrierName = name;
            subScan.useDelimiter("\\|");
            tripID = subScan.next().replaceAll(",", "");
            date = subScan.next();
            origin = subScan.next();
            subScan.useDelimiter("\\[");
            dest = subScan.next().replace("|", "");
            subScan.useDelimiter("]");
            section = subScan.next().replace("[", "");
            subScan = new Scanner(date);
            subScan.useDelimiter(",");
            year = subScan.nextInt();
            month = subScan.nextInt();
            day = subScan.nextInt();
            hour = subScan.nextInt();
            min = subScan.nextInt();

            system.createTrip(carrierName, origin, dest, year, month, day, hour, min, tripID);
            importSections(section, carrierName, tripID);
        }

        // Close scanner if it was ever used
        if (subScan != null) { subScan.close(); }
    }

    private void importSections(String subDoc, String carrierID, String tripID) {
        Scanner subScan = new Scanner(subDoc);
        Scanner subsubScan = null;
        String section;
        int rows;
        int cols = 0;
        SectionType type = null;
        SeatClass sclass = null;
        int seatCost;

        subScan.useDelimiter(",");
        while (subScan.hasNext()) {
            section = subScan.next();
            subsubScan = new Scanner(section);
            subsubScan.useDelimiter(":");
            switch (subsubScan.next()) {
                case "F":
                    sclass = SeatClass.first;
                    break;
                case "B":
                    sclass = SeatClass.business;
                    break;
                case "E":
                    sclass = SeatClass.economy;
                    break;
            }
            seatCost = subsubScan.nextInt();
            switch (subsubScan.next()) {
                case "S":
                    type = SectionType.flightS;
                    cols = 3;
                    break;
                case "M":
                    type = SectionType.flightM;
                    cols = 4;
                    break;
                case "W":
                    type = SectionType.flightW;
                    cols = 10;
                    break;
            }
            rows = subsubScan.nextInt();

            system.createSection(carrierID, tripID, rows, cols, type, sclass, seatCost);
        }

        // Close scanners
        subScan.close();
        if (subsubScan != null) { subsubScan.close(); }
    }
}
