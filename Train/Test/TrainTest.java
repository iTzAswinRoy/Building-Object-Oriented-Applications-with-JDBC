//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TrainTest {
//    private TicketSystem ticketSystem;
//
//
//
//    @BeforeEach
//    void setUp() {
//        this.ticketSystem = new TicketSystem();
//        ticketSystem.reset();
//    }
//
//
//
//        @Test
//        void testBookTicketSuccess() {
//            TicketBooking booking = new TicketBooking('A', 'C', 2, ticketSystem);
//            booking.execute();
//
//            // Verify the ticket was booked
//            assertEquals(1, ticketSystem.ticketBooked.size());
//            Ticket ticket = ticketSystem.ticketBooked.values().iterator().next();
//            assertEquals(TicketStatus.Booked, ticket.getTicketStatus());
//        }
//
//        @Test
//        void testBookTicketWaitingList() {
//            // Book all seats to fill the booking list
//            TicketBooking booking1 = new TicketBooking('A', 'E', 8, ticketSystem);
//            booking1.execute();
//
//            TicketBooking booking2 = new TicketBooking('A', 'E', 1, ticketSystem);
//            booking2.execute();
//
//            // Verify the ticket is added to the waiting list
//            assertEquals(1, ticketSystem.waitingList.size());
//            Ticket waitingTicket = ticketSystem.waitingList.values().iterator().next();
//            assertEquals(TicketStatus.Waiting_List, waitingTicket.getTicketStatus());
//        }
//
//        @Test
//        void testCancelTicketFullCancellation() {
//            TicketBooking booking = new TicketBooking('A', 'C', 2, ticketSystem);
//            booking.execute();
//
//            Ticket ticket = ticketSystem.ticketBooked.values().iterator().next();
//            int pnr = ticket.getPnrNumber();
//
//            TicketCancellation cancellation = new TicketCancellation(pnr, 2, ticketSystem);
//            cancellation.execute();
//
//            // Verify the ticket was cancelled
//            assertEquals(0, ticketSystem.ticketBooked.size());
//            assertEquals(1, ticketSystem.ticketCancelled.size());
//            Ticket cancelledTicket = ticketSystem.ticketCancelled.get(pnr);
//            assertEquals(TicketStatus.Booked, cancelledTicket.getTicketStatus());
//        }
//
//        @Test
//        void testCancelTicketPartialCancellation() {
//            TicketBooking booking = new TicketBooking('A', 'D', 4, ticketSystem);
//            booking.execute();
//
//            Ticket ticket = ticketSystem.ticketBooked.values().iterator().next();
//            int pnr = ticket.getPnrNumber();
//
//            TicketCancellation cancellation = new TicketCancellation(pnr, 2, ticketSystem);
//            cancellation.execute();
//
//            // Verify partial cancellation
//            assertEquals(1, ticketSystem.ticketBooked.size());
//            assertEquals(2, ticketSystem.ticketBooked.get(pnr).getSeats());
//        }
//
//        @Test
//        void testCheckAvailability() {
//            assertTrue(ticketSystem.checkAvailability('A', 'C', 2));
//            assertFalse(ticketSystem.checkAvailability('A', 'C', 9));
//        }
//
//        @Test
//        void testWaitingListLimit() {
//            // Book all seats to fill the booking list
//            TicketBooking booking1 = new TicketBooking('A', 'E', 8, ticketSystem);
//            booking1.execute();
//
//            // Add two tickets to waiting list, reaching the limit
//            TicketBooking booking2 = new TicketBooking('A', 'E', 1, ticketSystem);
//            TicketBooking booking3 = new TicketBooking('A', 'E', 1, ticketSystem);
//            booking2.execute();
//            booking3.execute();
//
//            // Verify the waiting list limit
//            assertEquals(2, ticketSystem.waitingListAdded);
//        }
//
//        @Test
//        void testIncreaseAndDecreaseSeatAvailability() {
//            ticketSystem.decreaseSeatAvailability('A', 'C', 2);
//            assertEquals(6, ticketSystem.seatsAvailability[0]);
//            assertEquals(6, ticketSystem.seatsAvailability[1]);
//
//            ticketSystem.increaseSeatAvailability('A', 'C', 2);
//            assertEquals(8, ticketSystem.seatsAvailability[0]);
//            assertEquals(8, ticketSystem.seatsAvailability[1]);
//        }
//    }
//
//
////    @Test
////    void bookingTicket() {
////        System.out.println("--------------------------------");
////        TicketBooking ticketBooking = new TicketBooking('A', 'C', 5, ticketSystem);
////        ticketBooking.execute();
////
////        System.out.println(ticketSystem.ticketBooked.get(1).getSeats());
////
//////        assertEquals(5, ticketSystem.ticketBooked.get(1).getSeats());
////    }
////
////    @Test
////    void bookingAllTickets() {
////        TicketBooking booking1 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////        TicketBooking booking2 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////        TicketBooking booking3 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////        TicketBooking booking4 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////        TicketBooking booking5 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////        TicketBooking booking6 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////        TicketBooking booking7 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////        TicketBooking booking8 = new TicketBooking('A', 'C', 1, ticketSystem);
////
////
////        booking1.execute();
////        booking2.execute();
////        booking3.execute();
////        booking4.execute();
////        booking5.execute();
////        booking6.execute();
////        booking7.execute();
////        booking8.execute();
////
////        assertEquals(0, ticketSystem.seatsAvailability[1]);
////
////    }
////
////    void checkingWaitingList() {
////
////    }
////
////    void checkingAllWaitingList() {
////
////    }
////
////
////    void checkingCancellation() {
////
////    }
////
////    void checkingAllCancellation() {
////
////    }
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////    @Test
////    public void testBookTicketSuccessfully() {
////        TicketBooking ticketBooking = new TicketBooking('A', 'C', 2, ticketSystem);
////        ticketBooking.execute();
////
////        Ticket bookedTicket = ticketSystem.ticketBooked.get(1);  // First ticket's PNR should be 1
////        assertNotNull(bookedTicket);
////        assertEquals(2, bookedTicket.getSeats());
////        assertEquals(TicketStatus.Booked, bookedTicket.getTicketStatus());
////    }
////
////    @Test
////    public void testAddToWaitingListWhenSeatsAreFull() {
////        // Filling up seats initially
////        TicketBooking ticketBooking = new TicketBooking('A', 'C', 8, ticketSystem);
////        ticketBooking.execute();
////
////        // Attempting another booking, which should go to waiting list
////        TicketBooking ticketBooking2 = new TicketBooking('A', 'C', 2, ticketSystem);
////        ticketBooking2.execute();
////
////        Ticket waitingListTicket = ticketSystem.waitingList.get(2);  // Second ticket's PNR should be 2
////        assertNotNull(waitingListTicket);
////        assertEquals(2, waitingListTicket.getSeats());
////        assertEquals(TicketStatus.Waiting_List, waitingListTicket.getTicketStatus());
////    }
////
////    @Test
////    public void testCancelTicketFullCancellation() {
////        // Book a ticket to be canceled
////        TicketBooking ticketBooking = new TicketBooking('A', 'C', 2, ticketSystem);
////        ticketBooking.execute();
////
////        TicketCancellation ticketCancellation = new TicketCancellation(1, 2, ticketSystem);
////        ticketCancellation.execute();
////
////        Ticket canceledTicket = ticketSystem.ticketCancelled.get(1);
////        assertNotNull(canceledTicket);
////        assertEquals(TicketStatus.Booked, canceledTicket.getTicketStatus());  // Status at booking time
////        assertFalse(ticketSystem.ticketBooked.containsKey(1));  // Should be removed from booked
////    }
////
////    @Test
////    public void testPartialCancellation() {
////        TicketBooking ticketBooking = new TicketBooking('A', 'C', 4, ticketSystem);
////        ticketBooking.execute();
////
////        TicketCancellation ticketCancellation = new TicketCancellation(1, 2, ticketSystem);
////        ticketCancellation.execute();
////
////        Ticket bookedTicket = ticketSystem.ticketBooked.get(1);
////        assertNotNull(bookedTicket);
////        assertEquals(2, bookedTicket.getSeats());  // Remaining seats after partial cancellation
////    }
////
////    @Test
////    public void testCheckAvailability() {
////        // Initially all seats are available
////        assertTrue(ticketSystem.checkAvailability('A', 'C', 8));
////
////        // Book 8 seats
////        TicketBooking ticketBooking = new TicketBooking('A', 'C', 8, ticketSystem);
////        ticketBooking.execute();
////
////        // Check availability for the same route and seats
////        assertFalse(ticketSystem.checkAvailability('A', 'C', 8));  // Should be full now
////    }
////
////    @Test
////    public void testPrintChart() {
////        PrintChart printChart = new PrintChart(ticketSystem);
////
////        // Booking some tickets to verify chart output
////        TicketBooking ticketBooking = new TicketBooking('A', 'C', 3, ticketSystem);
////        ticketBooking.execute();
////
////        TicketBooking ticketBooking2 = new TicketBooking('C', 'E', 2, ticketSystem);
////        ticketBooking2.execute();
////
////        printChart.execute();  // Manually verify console output if needed
////        // For automated testing, you might mock System.out to capture output or use other methods
////    }
////
////    @Test
////    public void testAddToWaitingList() {
////        WaitingList waitingList = new WaitingList('A', 'B', 1, ticketSystem);
////        waitingList.execute();
////
////        Ticket waitingListTicket = ticketSystem.waitingList.get(1);
////        assertNotNull(waitingListTicket);
////        assertEquals(TicketStatus.Waiting_List, waitingListTicket.getTicketStatus());
////    }
////}