import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // create scanner object


        System.out.println("Please enter the size of the game minimum is 3");
        int size = in.nextInt();
        in.nextLine();
        back key = new back(size); // constructor
        key.fillRandomNumberUnique();
        boolean winner;
        do{
             winner=key.userNumber(in);
        }while (winner);
        key.displayNumber(1);



    }


}
