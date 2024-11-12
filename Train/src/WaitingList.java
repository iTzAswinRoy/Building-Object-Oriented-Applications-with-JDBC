public class WaitingList {
    char source;
    char destination;
    int seats;
    TicketSystem ticketSystem;

    WaitingList(char source, char destination, int seats, TicketSystem ticketSystem){
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.ticketSystem = ticketSystem;
    }

    private void addToWaitingList(char source, char destination, int seats) {
        Ticket ticket = new Ticket(source, destination, seats, TicketStatus.Waiting_List);
        ticketSystem.waitingList.put(ticket.getPnrNumber(), ticket);

        System.out.println("\nYour ticket has been added to the waiting list.\n");
        ticketSystem.waitingListAdded += seats;

    }

    public void execute(){
        this.addToWaitingList(source, destination, seats);
    }
}
