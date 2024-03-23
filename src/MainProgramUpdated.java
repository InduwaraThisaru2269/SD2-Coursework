import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProgramUpdated {

    static int[][] SeatingPlan = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    static Ticket[] BoughtTickets = new Ticket[SeatingPlan.length * 14];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Print the menu
        while (true) {
            System.out.println("\nWelcome to the Plane Management System! \n");
            System.out.println("***********************************************");
            System.out.println("*                 MENU OPTIONS                *");
            System.out.println("***********************************************");
            System.out.println("      1) Buy a Seat");
            System.out.println("      2) Cancel a Seat");
            System.out.println("      3) Find First Seat Available");
            System.out.println("      4) Show Seating Plan");
            System.out.println("      5) Print Ticket Information and Sales");
            System.out.println("      6) Search Tickets");
            System.out.println("      0) Quit");
            System.out.println();

            try{
                System.out.print("Enter an Option: ");
                int Option = input.nextInt();

                if (Option == 0) {
                    System.out.println("GoodBye!");
                    return;
                } else if (Option == 1) {
                    buy_seat();
                } else if (Option == 2) {
                    cancel_seat();
                } else if (Option == 3) {
                    find_first_available();
                } else if (Option == 4) {
                    show_seating_plan();
                } else if (Option == 5) {
                    print_tickets_info();
                } else if (Option == 6) {
                    search_ticket();
                } else {
                    System.out.println("Enter a valid option!");
                }
            }catch (InputMismatchException e){
                System.out.println("Enter an Integer!");
                input.nextLine();
                continue;
            }
        }

    }

    public static int getRow(String RowLetter, int RowNumber) {
        switch(RowLetter){
            case "A":
                RowNumber = 0;
                break;
            case "B":
                RowNumber = 1;
                break;
            case "C":
                RowNumber = 2;
                break;
            case "D":
                RowNumber = 3;
                break;
        }
        return RowNumber;
    }

    /*
     * This "buy_seat" method will,
     * 1) First Draw the seating plan.
     * 2) Then let you buy one or many seats as you want.
     * 3) And then record it and draw the updated seating plan.
     */
    static void buy_seat() {

        Scanner input2 = new Scanner(System.in);

        String RowLetter;
        int SeatNum;

        int Row = 0;
        double Price;

        Person newPassenger;
        Ticket newTicket;

        String Name;
        String Surname;
        String Email;

        while (true) {
            System.out.print("Enter Your Name: ");
            Name = input2.nextLine();

            System.out.print("Enter your Surname: ");
            Surname = input2.nextLine();

            while(true){
                System.out.print("Enter your Email: ");
                Email = input2.nextLine();

                if(Email.contains("@") && Email.contains(".")){
                    break;
                }else{
                    System.out.println("Enter the Email Again!");
                }
            }

            newPassenger = new Person(Name, Surname, Email);

            while (true) {
                System.out.print("Enter the Row (A/B/C/D): ");
                RowLetter = input2.nextLine().toUpperCase();

                if (RowLetter.equals("A") || RowLetter.equals("B") || RowLetter.equals("C") || RowLetter.equals("D")) {
                    System.out.println("The Row You Entered is Correct.");
                    break;
                } else {
                    System.out.println("The Row You Entered is Incorrect. Enter Again.");
                }
            }

            Row = getRow(RowLetter, Row);

            if (RowLetter.equals("A")|| RowLetter.equals("D")) {
                while (true) {

                    try{
                        System.out.print("Enter the Seat Number: ");
                        SeatNum = input2.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Enter a valid character!");
                        input2.nextLine();
                        continue;
                    }

                    if (SeatNum > 0 && SeatNum <= 14) {

                        if (SeatingPlan[Row][SeatNum - 1] == 0) {
                            System.out.println("Seat is Available. Seat " + RowLetter + SeatNum + " has been sold.");
                            SeatingPlan[Row][SeatNum - 1] = 1;
                            break;
                        }
                        else {
                            System.out.println("This Seat is already sold. Enter Again");
                        }
                    }

                    else {
                        System.out.println("The Seat Number You Entered is Incorrect. Enter Again.");
                    }
                }
            }
            else {
                while (true) {
                    try{
                        System.out.print("Enter the Seat Number: ");
                        SeatNum = input2.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Enter a valid character!");
                        input2.nextLine();
                        continue;
                    }

                    if (SeatNum > 0 && SeatNum <= 12) {
                        if (SeatingPlan[Row][SeatNum - 1] == 0) {
                            System.out.println("Seat is Available. Seat " + RowLetter + SeatNum + " has been sold.");
                            SeatingPlan[Row][SeatNum - 1] = 1;
                            break;
                        } else {
                            System.out.println("This Seat is already sold. Enter Again");
                        }
                    } else {
                        System.out.println("The Seat Number You Entered is Incorrect. Enter Again.");
                    }
                }
            }

            if(SeatNum > 0 && SeatNum <= 5){
                Price = 200.00;
            }
            else if(SeatNum > 5 && SeatNum <= 9){
                Price = 150.00;
            }
            else{
                Price = 180.00;
            }
            System.out.println("Price: "+Price);

            newTicket = new Ticket(RowLetter,SeatNum,Price,newPassenger);

            for(int t = 0; t < BoughtTickets.length; t++){
                if(BoughtTickets[t] == null){
                    BoughtTickets[t] = newTicket;
                    break;
                }
            }

            newTicket.save();

            // Ask the user to buy another seat or not
            input2.nextLine();
            System.out.print("Do you want to buy an another seat (Enter 'q' for Quit or Press any key to continue):  ");
            String Answer = input2.nextLine();

            if (Answer.equals("q"))
                break;
        }
    }

    /*
     * This "cancel_seat()" method will,
     * 1) Cancel the seat you previously bought
     * 3) Validate the user input.
     * 2) Update the Map.
     */
    static void cancel_seat() {
        Scanner input3 = new Scanner(System.in);

        String RowLetter;
        int SeatNum;

        int Row = 0;
        boolean SeatBooked = false;

        while (true) {

            for(int r = 0; r < SeatingPlan.length; r++){
                for(int c = 0; c < SeatingPlan[r].length; c++){
                    if(SeatingPlan[r][c] == 1){
                        SeatBooked = true;
                        break;
                    }
                }
                if(SeatBooked)
                    break;
            }

            if(!SeatBooked){
                System.out.println("You haven't booked any seat.");
                break;
            }

            while (true) {

                System.out.println("Bought Tickets: \n");
                for(int t = 0; t < BoughtTickets.length; t++){
                    if(BoughtTickets[t] != null){
                        System.out.println("* "+BoughtTickets[t].getRow()+BoughtTickets[t].getSeat());
                    }
                }

                System.out.print("Enter the Row (A/B/C/D): ");
                RowLetter = input3.nextLine().toUpperCase();

                if (RowLetter.equals("A") || RowLetter.equals("B") || RowLetter.equals("C") || RowLetter.equals("D")) {
                    System.out.println("The Row You Entered is Correct.");
                    break;
                } else {
                    System.out.println("The Row Letter You Entered is Incorrect. Enter Again.");
                }
            }

            Row = getRow(RowLetter, Row);

            if (RowLetter.equals("A")|| RowLetter.equals("D")) {
                while (true) {
                    System.out.print("Enter the Seat Number: ");
                    SeatNum = input3.nextInt();

                    if (SeatNum > 0 && SeatNum <= 14) {

                        if (SeatingPlan[Row][SeatNum - 1] == 1) {
                            System.out.println("Ticket is canceled. Seat is now Available.");
                            SeatingPlan[Row][SeatNum - 1] = 0;

                            for (int t = 0; t < BoughtTickets.length; t++) {
                                Ticket ticket = BoughtTickets[t];
                                if (ticket != null && ticket.getRow().equals(RowLetter) && ticket.getSeat() == SeatNum) {
                                    BoughtTickets[t] = null;
                                    break;
                                }
                            }
                            break;
                        } else {
                            System.out.println("Seat is already available.");
                            break;
                        }
                    } else {
                        System.out.println("The Seat Number You Entered is Incorrect.");
                        break;
                    }
                }
            } else {
                while (true) {
                    System.out.print("Enter the Seat Number: ");
                    SeatNum = input3.nextInt();

                    if (SeatNum > 0 && SeatNum <= 14) {
                        if (SeatingPlan[Row][SeatNum - 1] == 1) {
                            System.out.println("Seat is now Available.");
                            SeatingPlan[Row][SeatNum - 1] = 0;

                            for (int t = 0; t < BoughtTickets.length; t++) {
                                if (BoughtTickets[t] != null && BoughtTickets[t].getRow().equals(RowLetter) && BoughtTickets[t].getSeat() == SeatNum) {
                                    BoughtTickets[t] = null;
                                    break;
                                }
                            }

                            break;
                        } else {
                            System.out.println("Seat is already available.");
                            break;
                        }
                    } else {
                        System.out.println("The Seat Number You Entered is Incorrect.");
                        break;
                    }
                }
            }



            // Ask the user to buy another seat
            input3.nextLine();
            System.out.println("Do you want to cancel an another seat (Enter 'q' for Quit or Press any key to continue):  ");
            String Answer = input3.nextLine();

            if (Answer.equals("q"))
                break;

        }
    }

    /*
     * This "find_first_available" method will
     * 1) Help the user to find the first available seat in the airplane.
     */
    static void find_first_available() {

        String RowLetter = "";
        boolean found = false;

        for (int r = 0; r < SeatingPlan.length; r++) {
            for (int c = 0; c < SeatingPlan[r].length; c++) {

                if (r == 0) {
                    RowLetter = "A";
                } else if (r == 1) {
                    RowLetter = "B";
                } else if (r == 2) {
                    RowLetter = "C";
                } else if (r == 3) {
                    RowLetter = "D";
                }

                if (SeatingPlan[r][c] == 0) {
                    System.out.println("The first available seat is: " + RowLetter + (c + 1));
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
    }

    /*
     * This "show_seating_plan" method will,
     * 1)Help the user to identify the seating plan.
     */
    static void show_seating_plan() {

        System.out.println("Updated Seating Plan: ");
        System.out.println();

        for (int r = 0; r < SeatingPlan.length; r++) {
            for (int c = 0; c < SeatingPlan[r].length; c++) {
                if (SeatingPlan[r][c] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    static void print_tickets_info(){

        double TotalAmount = 0.00;

        System.out.println("Sold Tickets: ");
        for(int t = 0; t < BoughtTickets.length; t++){
            if(BoughtTickets[t] != null){
                BoughtTickets[t].TicketInfo();
                TotalAmount += BoughtTickets[t].getPrice();
            }

        }

        System.out.println("Total Amount: $ "+ TotalAmount);
    }

    static void search_ticket() {
        Scanner input4 = new Scanner(System.in);

        String RowLetter;
        int SeatNum;

        int Row = 0;
        boolean SeatBooked = false;

        while (true) {
            while (true) {
                System.out.print("Enter the Row (A/B/C/D): ");
                RowLetter = input4.nextLine().toUpperCase();

                if (RowLetter.equals("A") || RowLetter.equals("B") || RowLetter.equals("C") || RowLetter.equals("D")) {
                    System.out.println("The Row You Entered is Correct.");
                    break;
                } else {
                    System.out.println("The Row Letter You Entered is Incorrect. Enter Again.");
                }
            }

            Row = getRow(RowLetter, Row);

            if (RowLetter.equals("A")|| RowLetter.equals("D")) {

                while (true) {

                    try{
                        System.out.print("Enter the Seat Number: ");
                        SeatNum = input4.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Enter a valid integer.");
                        input4.nextLine();
                        continue;
                    }

                    if (SeatNum > 0 && SeatNum <= 14) {

                        if (SeatingPlan[Row][SeatNum - 1] == 1) {
                            for (int t = 0; t < BoughtTickets.length; t++) {
                                if (BoughtTickets[t] != null && BoughtTickets[t].getRow().equals(RowLetter) && BoughtTickets[t].getSeat() == SeatNum){
                                    BoughtTickets[t].TicketInfo();
                                    break;
                                }
                            }
                            break;
                        } else {
                            System.out.println("Seat is available.");
                            break;
                        }
                    } else {
                        System.out.println("The Seat Number You Entered is Incorrect. Enter Again.");
                    }
                }
            } else {
                while (true) {
                    try{
                        System.out.print("Enter the Seat Number: ");
                        SeatNum = input4.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Enter a valid integer.");
                        input4.nextLine();
                        continue;
                    }

                    if (SeatNum > 0 && SeatNum <= 14) {

                        if (SeatingPlan[Row][SeatNum - 1] == 1) {
                            for (int t = 0; t < BoughtTickets.length; t++) {
                                if (BoughtTickets[t] != null && BoughtTickets[t].getRow().equals(RowLetter) && BoughtTickets[t].getSeat() == SeatNum){
                                    BoughtTickets[t].TicketInfo();
                                    break;
                                }
                            }
                            break;
                        } else {
                            System.out.println("Seat is available.");
                            break;
                        }
                    } else {
                        System.out.println("The Seat Number You Entered is Incorrect. Enter Again.");
                    }
                }
            }
            // Ask the user to buy another seat
            input4.nextLine();
            System.out.println("Do you want to check another ticket? (Enter 'q' for Quit or Press any key to continue) :  ");
            String Answer = input4.nextLine();

            if (Answer.equals("q"))
                break;

        }
    }


}
