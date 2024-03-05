import java.util.Scanner;

public class MainProgram {

    static int[][] SeatingPlan = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Print the menu
        while (true) {
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
            System.out.print("Enter an Option: ");
            int Option = input.nextInt();

            switch (Option) {
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;

            }

            if (Option == 0)
                break;
        }

    }

    /*
    After entering the RowLetter, 
    the "getRow" method is used to determine which row in the array this letter is assigned to.
     */
    private static int getRow(String RowLetter, int RowNumber) {
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

        // Buy one or many seats and update the seating plan.
        String RowLetter;
        int SeatNum;

        int Row = 0;

        while (true) {

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
                    System.out.print("Enter the Seat Number: ");
                    SeatNum = input2.nextInt();

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
            } else {
                while (true) {
                    System.out.print("Enter the Seat Number: ");
                    SeatNum = input2.nextInt();

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

            // Ask the user to buy another seat or not
            input2.nextLine();
            System.out.print("Do you want to buy an another seat (Enter 'q' for Quit):  ");
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

        // Buy one or many seats and update the seating plan.
        String RowLetter;
        int SeatNum;

        int Row = 0;

        while (true) {

            while (true) {
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
                            System.out.println("Seat is now Available.");
                            SeatingPlan[Row][SeatNum - 1] = 0;
                            break;
                        } else {
                            System.out.println("Seat is already available. Enter again.");
                        }
                    } else {
                        System.out.println("The Seat Number You Entered is Incorrect. Enter Again.");
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
                            break;
                        } else {
                            System.out.println("Seat is already available. Enter again.");
                        }
                    } else {
                        System.out.println("The Seat Number You Entered is Incorrect. Enter Again.");
                    }
                }
            }

            // Ask the user to buy another seat
            input3.nextLine();
            System.out.println("Do you want to cancel an another seat (Enter 'q' for Quit):  ");
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

}