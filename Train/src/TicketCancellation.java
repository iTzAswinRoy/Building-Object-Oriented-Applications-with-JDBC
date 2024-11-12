import java.util.ArrayList;

public class TicketCancellation {
    int pnr;
    int seats;
    TicketSystem ticketSystem;

    TicketCancellation(int pnr, int seats, TicketSystem ticketSystem) {
        this.pnr = pnr;
        this.seats = seats;
        this.ticketSystem = ticketSystem;
    }

    private void fullCancellation() {
        Ticket ticket = ticketSystem.getTicket(pnr);

        if(ticket != null) {
            // this will remove the entire tickets if we enter the total seats in a ticket
            if(ticket.getSeats() == seats) {

                System.out.println("\nYour ticket has been cancelled\n");
                ticketSystem.ticketCancelled.put(pnr, ticketSystem.ticketBooked.get(pnr));
                ticketSystem.ticketCancelled.get(pnr).setTicketStatus(TicketStatus.Cancelled);
                ticketSystem.ticketBooked.remove(pnr);

            } else {
                partialCancellation(seats, ticket);
                System.out.println("\nYour seats has been cancelled partially\n");
            }

            ticketSystem.decreaseSeatAvailability(ticket.getSource(), ticket.getDestination(), seats);
        } else {
            System.out.println("\nTicket not found!\n");
        }
    }

    public void partialCancellation(int removeSeats, Ticket ticket) {
        if(!ticketSystem.waitingList.isEmpty()) {

            ArrayList<Integer> collectKeys = new ArrayList<>();

            for (Ticket waitingListTicket: ticketSystem.waitingList.values()) {

                if (waitingListTicket != null) {

                    if (waitingListTicket.getSeats() == removeSeats || waitingListTicket.getSeats() < removeSeats) {

                        waitingListTicket.setTicketStatus(TicketStatus.Booked);

                        ticketSystem.addToBookedTickets(waitingListTicket.getPnrNumber(), waitingListTicket);

                        collectKeys.add(waitingListTicket.getPnrNumber());
                        ticketSystem.waitingListAdded -= removeSeats;
                    }
                }
                break;
            }

            for (Integer pnr : collectKeys){
                ticketSystem.waitingList.remove(pnr);
            }


            ticket.setSeats(removeSeats);
            ticketSystem.increaseSeatAvailability(ticket.getSource(), ticket.getDestination(), removeSeats);
        }

    }

    public void execute(){
        this.fullCancellation();
    }
}
