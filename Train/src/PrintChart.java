import java.util.Arrays;

public class PrintChart {
    private TicketSystem ticketSystem;

    PrintChart(TicketSystem ticketSystem){
        this.ticketSystem = ticketSystem;
    }
    protected void printChart() {
        System.out.println("\n-------------------------------------------------------------------------------------");

        System.out.println("Ticket booked:");
        if(!ticketSystem.ticketBooked.isEmpty()) {

            for (Ticket ticketBooked : ticketSystem.ticketBooked.values()){

                System.out.println(ticketBooked);
//            for (int i = 1; i <= ticketSystem.ticketBooked.size(); i++) {
//                System.out.println(ticketSystem.ticketBooked.get(i));
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
