import java.io.IOException;
import java.util.Scanner;

// I know that I double checked the name input. But first I checked the input. I also checked for validation in Person Constructor because some Person class
// instances can be created anywhere else in the program during a more complex project.


public class Person {
    private String name;
    private int age;


    //Constructors

    //Here I used Constructors Overload.
    // In first constructor case, it does not have any parameters(they can be set later using Setters)

    public Person() {
        this.name="";
        this.age=0;
    }

    //In second constructor case, there exists 2 parameters.
    /* I choose to throw exceptions here (not in the main or some controller ) because in case of a more complex project, some
    person class instance could be created anywhere in the code and so it could be inputed wrong name(empty name) or age(less than 0)
    and if the exceptions would be thrown in main or somewhere else the parameters won't be checked
    */
    public Person (String name, int age) throws IOException{
        if (!name.matches("[a-zA-Z]+")){
            throw new IOException("name can't be empty. Please re-input the name");
        }
        else {
            this.name=name;
        }
        if (age>0) { // here I didnt check if age is a Integer because from the parameters list I already know that variable AGE is already an int.
            this.age=age;
        }
        else {
            throw new IOException("Person's age can't be lower than 0");
        }

    }
    //Getters and setters | In this case the setters and NAME getter aren't used. But I implemented here in case of needed later.
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //to String
    // in order to "transform" our Person instance to a String;
    @Override
    public String toString() {
        return "name:" + this.name  +" age:" + age;
    }

    //requested Method
    public boolean isMajor(){
        /*
        Method that return TRUE if person is major (person is at least 18 years old) or FALSE otherwise.
        Method does not have any input because it references to the current instance of Person class.
         */
        return this.getAge() >= 18;
    }

    //main method

    /*example of input: Mihai 21 // after "Mihai" is inputed press ENTER button
             ->  output: name:Mihai age:21 is a major person

                 input: Alex -2
             ->  output: java.io.IOException: Person's age can't be lower than 0

                 input: {ENTER Button on keyboard} 2
             ->  output: java.io.IOException: name can't be empty. Please re-input the name

                 input: exit
             ->  output: Process finished(program stops)
    */

    public static void main(String[] args) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Input Person's name:");
                String name= scanner.nextLine();
                if (!name.matches("[a-zA-Z]+")) { // here I checked using regex that the name does not contain any digits or special characters
                    throw new IOException("Please input a valid name without special characters or digits");
                }
                else if (name.equals("exit")){
                    break;
                }
                System.out.println("Input Person's age:");
                int age;
                if (scanner.hasNextInt()){
                    age=scanner.nextInt();
                }
                else{
                    throw new IOException("Please input a number");
                }
                Person p = new Person(name, age);
                if (p.isMajor()) {
                    System.out.println(p + " is a major person");
                } else {
                    System.out.println(p + " is not a major person");
                }

            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }


}
