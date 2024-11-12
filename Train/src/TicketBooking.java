public class TicketBooking {
    private char source;
    private char destination;
    private int seats;
    private TicketSystem ticketSystem;

    TicketBooking(char source, char destination, int seats, TicketSystem ticketSystem) {
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.ticketSystem = ticketSystem;
    }

    private void bookTicket() {
        if (ticketSystem.checkAvailability(source, destination, seats)) {

            Ticket ticket = new Ticket(source, destination, seats, TicketStatus.Booked);
            int pnr = ticket.getPnrNumber();
            ticketSystem.addToBookedTickets(pnr, ticket);

            System.out.println("\nYour ticket has been booked!. Here's your the PNR number: " + ticket.getPnrNumber() + "\n");
            ticketSystem.increaseSeatAvailability(source, destination, seats);
            // book
        } else {
            if (ticketSystem.waitingListAdded + seats > 2) {
                System.out.println("\nOops.. Can't add more than 2 waiting lists. Tickets are full!\n");
            } else {
                WaitingList waitingList = new WaitingList(source, destination, seats, ticketSystem);
                waitingList.execute();
            }
        }
    }

    protected void execute() {
        this.bookTicket();
    }
}
