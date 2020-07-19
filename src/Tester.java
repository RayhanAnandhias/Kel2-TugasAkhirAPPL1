import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ACTBS.SeatClass;
import ACTBS.SystemManager;
import ACTBS.Trip.TripBuilder;

public class Tester {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SystemManager res = new SystemManager();

		res.createAirport("DEN");
		res.createAirport("DFW");
		res.createAirport("LON");
		res.createAirport("DEN");// invalid res.createAirport("DENW");//invalid

		res.createAirline("DELTA");
		res.createAirline("AMER");
		res.createAirline("FRONT");
		res.createAirline("FRONTIER"); // invalid
		res.createAirline("FRONT"); // invalid

		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlight("DELTA", "DEN", "DEN", 2018, 8, 8, "567abc");// same airprt
		res.createFlight("DEL", "DEN", "LON", 2018, 9, 8, "567"); // invalid airline
		res.createFlight("DELTA", "LON33", "DEN33", 2019, 5, 7, "123");// invalid airports
		res.createFlight("AMER", "DEN", "LON", 2010, 40, 100, "123abc");// invalid date

		res.createFlightSection("DELTA", "123", 2, 2, SeatClass.economy);
		res.createFlightSection("DELTA", "123", 2, 3, SeatClass.first);
		res.createFlightSection("DELTA", "123", 2, 3, SeatClass.first);// Invalid /*
		res.createFlightSection("SWSERTT", "123", 5, 5, SeatClass.economy);// Invalid airline

		res.bookFlightSeat("DELTA", "123", SeatClass.first, 1, 'A');
		res.bookFlightSeat("DELTA", "123", SeatClass.economy, 1, 'A');
		res.bookFlightSeat("DELTA", "123", SeatClass.economy, 1, 'B');
		res.bookFlightSeat("DELTA888", "123", SeatClass.business, 1, 'A'); // Invalid airline
		res.bookFlightSeat("DELTA", "123haha7", SeatClass.business, 1, 'A'); // Invalid flightId
		res.bookFlightSeat("DELTA", "123", SeatClass.economy, 1, 'A'); // already booked
		
		res.findAvailableFlights("DEN", "LON");

		res.displaySystemDetails();

		res.importParser();
		res.exportParser();
		
	}
}
