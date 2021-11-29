import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // create scanner object


        int size;
        do{
            System.out.println("Please enter the size of the game minimum is 3");
            size= in.nextInt();
            if(size<3)
                System.out.println("Wrong size");
        }while(size<3);


        back key = new back(size); // constructor
        key.fillRandomNumberUnique();
        boolean winner;
        do{
             winner=key.userNumber(in);
        }while (winner);
        key.displayNumber(1);



    }


}
