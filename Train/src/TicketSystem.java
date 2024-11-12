import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TicketSystem {
    protected Map<Integer, Ticket> ticketBooked = new HashMap<>();
    protected Map<Integer, Ticket> waitingList = new HashMap<>();
    protected Map<Integer, Ticket> ticketCancelled = new HashMap<>();


    int[] seatsAvailability = new int[5];

    int waitingListAdded = 0;

    public TicketSystem() {
        Arrays.fill(seatsAvailability, 8);
    }

    public boolean checkAvailability(char source, char destination, int seats) {
        for (int i = source - 'A'; i < destination - 'A'; i++) {
            if(seatsAvailability[i] < seats){
                return false;
            }
        }
        return true;
    }

    public void addToBookedTickets(int pnr, Ticket ticket) {
        ticketBooked.put(pnr, ticket);
    }

    public void decreaseSeatAvailability(char source, char destination, int seats) {
        for (int i = source - 'A'; i < destination - 'A'; i++) {
            seatsAvailability[i] += seats;
        }
    }

    public void increaseSeatAvailability(char source, char destination, int seats) {
        for (int i = source - 'A'; i < destination - 'A'; i++) {
            seatsAvailability[i] -= seats;
        }
    }

    public Ticket getTicket(int pnr) {
        if(ticketBooked != null) {

            return ticketBooked.get(pnr);
        } else {
            return waitingList.get(pnr);
        }
    }

    public void reset() {
        ticketBooked.clear();
        Arrays.fill(seatsAvailability, 0); // Reset seat availability
    }
}
