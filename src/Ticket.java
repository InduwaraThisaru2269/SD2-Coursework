import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

        private String row;
        private int seat;
        private double price;
        private Person newUser;

        public Ticket(String Row, int Seat, double Price, Person newUser){
            this.row = Row;
            this.seat = Seat;
            this.price = Price;
            this.newUser = newUser;
        }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getNewUser() {
        return newUser;
    }

    public void setNewUser(Person newUser) {
        this.newUser = newUser;
    }

    public void TicketInfo(){
        System.out.println();
        newUser.printInfo();
        System.out.println("Seat: "+seat);
        System.out.println("Row: "+row);
        System.out.println("Price: $ "+price);
        System.out.println();
    }

    public void save(){

        try {
            FileWriter newFileWriter = new FileWriter(row+seat+".txt");

            newFileWriter.write("--------Ticket Information-------- \n");
            newFileWriter.write("Row: "+ row +"\n");
            newFileWriter.write("Seat: "+ seat + "\n");
            newFileWriter.write("Price: "+ price + "\n");
            newFileWriter.write("\n--------Person Information-------- \n");
            newFileWriter.write(newUser.saveToTxtInfo());
            newFileWriter.close();
            System.out.println("Ticket information is saved to a file.");

        } catch (IOException e) {
            System.err.println("Error saving to a text file. ");
        }

    }
}
