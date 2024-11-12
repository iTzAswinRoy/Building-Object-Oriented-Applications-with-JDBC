public class Ticket {

    static int pnrGenerator = 1;
    private final int pnrNumber = pnrGenerator++;
    private final char source;
    private final char  destination;
    private int seats;
    private TicketStatus ticketStatus;


    Ticket(char source, char destination, int seats, TicketStatus ticketStatus){
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.ticketStatus = ticketStatus;
    }

    public int getPnrNumber() {
        return pnrNumber;
    }

    public char getSource() {
        return source;
    }

    public char getDestination() {
        return destination;
    }

    public int getSeats() {
        return seats;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setSeats(int removeSeats){
        seats -= removeSeats;
    }

    public void setTicketStatus(TicketStatus status){
        this.ticketStatus = status;
    }

    @Override
    public String toString() {
        return "Tickets - PNR no: "+getPnrNumber()+
                ", Source: "+ getSource()+
                ", Destination: "+ getDestination()+
                ", Seats: "+getSeats()+
                ", Status: "+getTicketStatus();
    }
}
