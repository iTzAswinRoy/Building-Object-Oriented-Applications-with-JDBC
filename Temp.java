import java.util.*;

public class Temp {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();

        while (true){

            System.out.println("1. Book \n2. Cancel \n3. View ticket");

            System.out.println("Enter an option");
            Scanner in = new Scanner(System.in);

            int option = in.nextInt();

            switch (option) {
                case 1 ->{
                    System.out.println("enter the source:");
                    char source = in.next().toUpperCase().charAt(0);


                    System.out.println("enter the destination:");
                    char destination = in.next().toUpperCase().charAt(0);


                    System.out.println("enter no. of seats:");
                    int seats = in.nextInt();

                    TicketBooking ticketBooking = new TicketBooking(source, destination, seats, ticketSystem);
                    ticketBooking.execute();
                }

                case 2 -> {
                    System.out.println("Enter the pnr number:");
                    int pnr = in.nextInt();

                    System.out.println("enter the no. of seats:");
                    int seats = in.nextInt();


                    TicketCancellation ticketCancellation = new TicketCancellation(pnr, seats, ticketSystem);
                    ticketCancellation.execute();
                }

                case 3 -> {
                    PrintChart printChart = new PrintChart(ticketSystem);
                    printChart.execute();
                }

                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}

 class PrintChart {
    private TicketSystem ticketSystem;

    PrintChart(TicketSystem ticketSystem){
        this.ticketSystem = ticketSystem;
    }
    protected void printChart() {
        System.out.println("\n-------------------------------------------------------------------------------------");

        System.out.println("Ticket booked:");
        if(!ticketSystem.ticketBooked.isEmpty()) {

            for (int i = 1; i <= ticketSystem.ticketBooked.size(); i++) {
                System.out.println(ticketSystem.ticketBooked.get(i));
            }
        } else {
            System.out.println("Null");
        }

        System.out.println("\nTickets in waiting list:");
        if(!ticketSystem.waitingList.isEmpty()) {

            for (Ticket values : ticketSystem.waitingList.values()) {
                System.out.println(values);
            }
        } else {
            System.out.println("Null");
        }

        System.out.println("\nTickets Cancelled:");
        if(!ticketSystem.ticketCancelled.isEmpty()) {

            for (Ticket value : ticketSystem.ticketCancelled.values()) {
                System.out.println(value);
            }

        } else {
            System.out.println("Null");
        }

        System.out.println("\nSeats available:");
        System.out.println("\t A  B  C  D  E");
        System.out.println("\t"+ Arrays.toString(ticketSystem.seatsAvailability));

        System.out.println("\nTicket chart:");
        System.out.println("\tSeat Booked ->");
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8");

        for (char i = 'A'; i <= 'E'; i++) {
            System.out.print(i+"\t");

            int bookedSeats = 8 - ticketSystem.seatsAvailability[i - 'A']; // Calculate booked seats

            for (int j = 0; j < bookedSeats; j++) {
                System.out.print("*\t");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------\n");
    }

    public void execute() {
        printChart();
    }
}
 class Ticket {

    static int pnrGenerator = 1;
    protected final int pnrNumber = pnrGenerator++;
    protected final char source;
    protected final char  destination;
    protected int seats;
    protected TicketStatus ticketStatus;


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
 class TicketBooking {
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

            System.out.println("\nYour ticket has been booked!. Here's your the PNR number: " + ticket.getPnrNumber()+"\n");
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

 class TicketCancellation {
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

                if(waitingListTicket != null) {

                    if (waitingListTicket.getSeats() == removeSeats || waitingListTicket.getSeats() < removeSeats) {

                        waitingListTicket.setTicketStatus(TicketStatus.Booked);
                        ticketSystem.ticketBooked.put(waitingListTicket.getPnrNumber(), waitingListTicket);

//                        ticketSystem.waitingList.remove(waitingListTicket.getPnrNumber());
                        collectKeys.add(waitingListTicket.getPnrNumber());
                        ticketSystem.waitingListAdded -= removeSeats;
                        ticketSystem.increaseSeatAvailability(ticket.getSource(), ticket.getDestination(), removeSeats);
                    }
                }
            }

            for (Integer pnr : collectKeys){
                ticketSystem.waitingList.remove(pnr);
            }

        } else {
            ticket.setSeats(removeSeats);
        }
    }

    public void execute(){
        this.fullCancellation();
    }
}
 enum TicketStatus {
    Booked, Cancelled, Waiting_List
}


 class TicketSystem {
    Map<Integer, Ticket> ticketBooked = new HashMap<>();
    Map<Integer, Ticket> waitingList = new HashMap<>();
    Map<Integer, Ticket> ticketCancelled = new HashMap<>();


    int[] seatsAvailability = new int[5];

    int waitingListAdded = 0;

    public TicketSystem() {
        Arrays.fill(seatsAvailability, 8);
    }

    public boolean checkAvailability(char source, char destination, int seats){
        for (int i = source - 'A'; i < destination - 'A'; i++) {
            if(seatsAvailability[i] < seats){
                return false;
            }
        }
        return true;
    }

    public void addToBookedTickets(int pnr, Ticket ticket){
        ticketBooked.put(pnr, ticket);
    }

    public void decreaseSeatAvailability(char source, char destination, int seats){
        for (int i = source - 'A'; i < destination - 'A'; i++) {
            seatsAvailability[i] += seats;
        }
    }

    public void increaseSeatAvailability(char source, char destination, int seats){
        for (int i = source - 'A'; i < destination - 'A'; i++) {
            seatsAvailability[i] -= seats;
        }
    }

    public Ticket getTicket(int pnr){
        if(ticketBooked!=null){
            return ticketBooked.get(pnr);
        } else {
            return waitingList.get(pnr);
        }
    }

}
 class WaitingList {
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
