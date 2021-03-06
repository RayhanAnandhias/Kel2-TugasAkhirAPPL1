import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ACTBS.SeatClass;
import ACTBS.SystemManager;
import ACTBS.Trip.TripBuilder;

public class UnitTest {

	SystemManager res;
	TripBuilder tripBuilder;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private final String NO_ERROR = "";

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Before
	public void init() {
		res = new SystemManager();
	}

	@Test
	public void TestAddTrainStation()  {
		res.createTrainStation("BDG");
		res.createTrainStation("JKT");
		assertEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddTrainStationDuplicate()  {
		res.createTrainStation("BDG");
		res.createTrainStation("BDG");
		assertNotEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddTrainStationError()  {
		res.createTrainStation("BDGG");
		assertNotEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddRailRoad()  {
		res.createTrain("serayu");
		assertEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddRailRoadError()  {
		res.createTrain("abcdefghijklmnopqrstuvwxyz");
		assertNotEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddRailRoadDuplicate()  {
		res.createTrain("serayu");
		res.createTrain("serayu");
		assertNotEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddCreateTrainTrip() {
		res.createTrainStation("BDG");
		res.createTrainStation("JKT");
		res.createTrain("SERAYU");
		res.createTrainTrip("SERAYU", "BDG", "JKT", 2000, 7, 19, "ABC123");
		assertEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddAirport() {
	    res.createAirport("DEN");
	    assertEquals(NO_ERROR, outContent.toString());
	}

	@Test
	public void TestAddAirportError() {
	    res.createAirport("DEN2");
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestAddAirportDuplicate() {
	    res.createAirport("DEN");
	    res.createAirport("DEN");
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestCreateAirline() {
		res.createAirline("DELTA");
		res.createAirline("AMER");
		res.createAirline("FRONT");
	    assertEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestCreateAirlineError() {
		res.createAirline("DELTAF");
		res.createAirline("AMERARARA");
		res.createAirline("FRONT");
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestCreateFlight() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
	    assertEquals(NO_ERROR, outContent.toString());
	}
	
	@Test //Error
	public void TestCreateInvalidFlightDate() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 23, 40, "123");
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestCreateSection() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
		res.createFlightSection("DELTA","123", 2, 2, SeatClass.economy);
	    assertEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestCreateSectionDuplicate() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
		res.createFlightSection("DELTA","123", 2, 2, SeatClass.economy);
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestCreateSectionError() {
		res.createAirline("DELTA1");
		res.createFlightSection("DELTA","123", 2, 2, SeatClass.economy);
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestCreateSectionNoAirlines() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
		res.createFlightSection("ALPHA","123", 2, 2, SeatClass.economy);
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestBookSeat() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
		res.createFlightSection("DELTA","123", 2, 2, SeatClass.economy);
		res.bookFlightSeat("DELTA", "123", SeatClass.first, 1, 'A');
	    assertEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestBookNoSeat() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
		res.createFlightSection("DELTA","123", 2, 2, SeatClass.economy);
		res.bookFlightSeat("DELTA", "123", SeatClass.first, 4, 'A');
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestBookSeatBooked() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
		res.createFlightSection("DELTA","123", 2, 2, SeatClass.economy);
		res.bookFlightSeat("DELTA", "123", SeatClass.first, 1, 'A');
		res.bookFlightSeat("DELTA", "123", SeatClass.first, 1, 'A');
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
	
	@Test
	public void TestFindFlights() {
		res.createAirport("DEN");
		res.createAirport("LON");
		res.createAirline("DELTA");
		res.createFlight("DELTA", "DEN", "LON", 2018, 10, 10, "123");
		res.createFlightSection("DELTA","123", 2, 3, SeatClass.first);
		res.createFlightSection("DELTA","123", 2, 2, SeatClass.economy);
		res.bookFlightSeat("DELTA", "123", SeatClass.first, 1, 'A');
		res.bookFlightSeat("DELTA", "123", SeatClass.first, 1, 'A');
		res.findAvailableFlights("DEN", "LON");
	    assertNotEquals(NO_ERROR, outContent.toString());
	}
}
