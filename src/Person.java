public class Person{

    private String name;
    private String surname;
    private String email;

    public Person(String Name, String Surname, String Email) {
        this.name = Name;
        this.surname = Surname;
        this.email = Email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);

    }

    public String saveToTxtInfo(){
        return "Name: " + name + "\nSurname: "+ surname + "\nEmail: " +email;
    }


}

