import java.util.Scanner;
// nu,bers
public class back {

    private int array[][];
    private int size;
    private int maxIndexSize ;
    private  int [][] sortedArray;


    back(int size) {
        this.array = new int[size][size];  // setter for array size
        this.sortedArray = new int[size][size];  // setter for array size
        this.size = size;
        this.maxIndexSize=size-1;

        dynamicSortedArray();// to create a dynamic sorted array
    }

    void dynamicSortedArray(){
        int count = 1;
        for(int i = 0;i < sortedArray.length;i++){
            for(int j = 0;j<sortedArray.length;j++){
                if(count==size*size)
                    break;
                sortedArray[i][j]=count;
                count++;
            }
        }
    }


    int [] linerSearch(int number){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == number)
                    return new int[]{i, j}; // if not sorting of any element return false
            }
        }
        return null; // if sorting return true  and end the game

    } // end method linerSearch

    boolean userNumber(Scanner in){
        displayNumber(-1);
        System.out.print("\nEnter a number:  " );
        int userNumber = in.nextInt();

         try {
             int []iANDj = linerSearch(userNumber);
             int [] iANDjEmpty=indexOfEmptySpace();
             if(checkDimensionsOfNumberSelected(iANDj[0],iANDj[1]))
                 swap(iANDj[0],iANDj[1],iANDjEmpty[0],iANDjEmpty[1]);
             else
                 System.out.println("The number has no space to move in");

        } catch (Exception e) {
             System.out.println("\nInvalid Number\n");
        }

        if(ifEmptySpaceIsLastIndex()) {
            // if the array was sorted return true and end the game
             if(linerSearchForChecking()) ///////////////////////////////////////
                 return false;
             return true;
        }
        return true;
    }

    // if empty space in last index do a linerSearch to improve the performance
    boolean ifEmptySpaceIsLastIndex(){
        return array[maxIndexSize][maxIndexSize] == 0;
    }

    boolean linerSearchForChecking() { // for checking if the array is sorting if true end the game
            for(int i = 0 ;i<array.length;i++){
                for(int j = 0 ;j<array.length;j++){
                    if(array[i][j]!=sortedArray[i][j])
                        return false;
                }
            }
        return true; // if sorting return true  and end the game
    }


    boolean linerSearchForCheckingAuTo() { // for checking if the array is Auto sorting while generating
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[i][j] == 0)
                    continue;
                if (array[i][j] < array[i][j - 1])
                    return false; // if not sorting of any element return false
            }

        }
        return true; // if sorting return true

    }// end method  linerSearchForChecking

    void sort() {
        int n = size;
        int m = size;
        int i, j, temp;

        for (i = 0; i < n * m - 1; ++i) {
            for (j = 0; j < n * m - 1 - i; ++j) {
                if (array[j / m][j % m] > array[(j + 1) / m][(j + 1) % m]) {

                    temp = array[(j + 1) / m][(j + 1) % m];
                    array[(j + 1) / m][(j + 1) % m] = array[j / m][j % m];
                    array[j / m][j % m] = temp;

                }
            }
        }

    } // sort for testing the win function || no need for main game;

    void displayNumber(int n) { // display the number in array

        if(n==1)
            System.out.println("You are smart Nice played");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > 9)
                    System.out.print(array[i][j] + " ");
                 else if(array[i][j]==0)
                    System.out.print( "  ");
                 else
                System.out.print(array[i][j] + "  ");
                if (size - 1 == j) {
                    System.out.println("");
                }

            }
        }
    }// end method displayNumber


    void fillRandomNumberUnique() {
        int count ; // general initialize || this variable to count how many element insert into the array to stopped at last index (we need last index = 0 bt default)
        boolean autoSorted = true; //general initialize ||  this variable to check if the list is sorting automatically
        int num; // general initialize for number that we need to insert
        boolean checkUnique;
        int numberOfElements = size * size - 1; //
        do {
            count=0;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    num = (int) (Math.random() * numberOfElements) + 1; // random number between 1 and 3
                    checkUnique = checkUnique(num);
                    if (checkUnique) { // if the random number above is already in list add the number
                        array[i][j] = num;
                        count++;
                    } else { // if is it not do Math.random again
                        j--;
                    }
                    if (count == numberOfElements) { // this if will exit the loop before insert last index ro make it zero ||| count is number of elements that insert into array
                        autoSorted = linerSearchForCheckingAuTo();// if the list was sorted return true to use it again;
                        if (autoSorted)// if the list was sorted join the if and reset all elements to zero , for reuse it;
                            setArray(size); // if the list was sorted
                        break;// this break to exit from second loop
                    }
                }//for i
            }// for j
        } while (autoSorted);// if the list was sorted , do the proses Again

    }// end fillRandomNumberUnique

    boolean checkUnique(int num) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++)
                if (array[i][j] == num)
                    return false; // if the number is already in the list return false otherwise
        }
        return true;
    } // end method checkUnique

    public void setArray(int size) {
        this.array = new int[size][size];  // setter for array size
    } // end method setArray

    void swap(int iFirst, int jFirst, int iSecond, int jSecond) { // just swapping
        int temp;
        temp = array[iFirst][jFirst];
        array[iFirst][jFirst] = array[iSecond][jSecond];
        array[iSecond][jSecond] = temp;


    } // end swap method


    boolean checkDimensionsOfNumberSelected(int i,int j) {

        if ((i == 0 && j == maxIndexSize)) { // up right corner of matrix||

            return checkUpRight(i, j); // if there are an empty space return true otherwise return false

        } else if ((i == 0 && j == 0)) { // up left corner of matrix||

            return checkUpLeft(i, j);  // if there are an empty space return true otherwise return false

        } else if ((i == maxIndexSize && j == 0)) {// down left corner of matrix||

            return checkDownLeft(i, j);// // if there are an empty space return true otherwise return false

        } else if ((i == maxIndexSize && j == maxIndexSize)) { // down right corner of matrix||

            return checkDownRight(i, j);// if there are an empty space return true otherwise return false

        } else if ((i != 0) && (j != maxIndexSize)&& (i != maxIndexSize)&&(j != 0)){ // all numbers in the medal  of the matrix
            return checkAllNumberInTheMedalOfMatrix(i, j);

        } else {// otherwise, the numbers between the corners
            return checkAllNumberBetweenCornersOfMatrix(i, j,maxIndexSize);
        }
    }
////////////////////////////////////////////////////////////////////////////////
    boolean checkUpRight(int i, int j) {
        return array[0][maxIndexSize-1] == 0 || array[1][maxIndexSize] == 0;
    }

    boolean checkUpLeft(int i, int j) {
        return array[1][0] == 0 || array[0][1] == 0;
    }

    boolean checkDownRight(int i, int j) {
        return array[maxIndexSize-1][maxIndexSize] == 0 || array[maxIndexSize][maxIndexSize-1] == 0;
    }

    boolean checkDownLeft(int i, int j) {
        return array[maxIndexSize][1] == 0 || array[maxIndexSize-1][0] == 0;
    }
//////////////////////////////////////////////////////////////////////
    boolean checkAllNumberInTheMedalOfMatrix(int i, int j) {
        if ((array[i][j + 1] == 0) || (array[i][j - 1] == 0) || (array[i + 1][j] == 0) || (array[i - 1][j] == 0))
            return true;
        return false;
    } //end method checkAllNumberInTheMedalOfMatrix

    boolean checkAllNumberBetweenCornersOfMatrix(int i, int j,int maxIndexSize) {
        if (j == maxIndexSize)
            return rightSide(i, j);
        else if (i == maxIndexSize)
            return downSide(i, j);
        else if (j == 0)
            return leftSide(i, j);
        else if (i == 0)
            return upSide(i, j);
        return false;
    }// end method checkAllNumberBetweenCornersOfMatrix

    boolean rightSide(int i, int j) {
        if (array[i - 1][j] == 0 || array[i + 1][j] == 0 || array[i][j - 1] == 0)
            return true;
        return false;

    } // end method rightSide

    boolean leftSide(int i, int j) {
        if (array[i - 1][j] == 0 || array[i + 1][j] == 0 || array[i][j + 1] == 0)
            return true;
        return false;
    }

    boolean upSide(int i, int j) {
        if (array[i][j + 1] == 0 || array[i][j - 1] == 0 || array[i + 1][j] == 0)
            return true;
        return false;
    }

    boolean downSide(int i, int j) {
        if ((array[i][j + 1] == 0) || (array[i][j - 1] == 0) || (array[i - 1][j] == 0))
            return true;
        return false;
    }


    int[] indexOfEmptySpace() {
        int i, j;
        for (i = 0; i < array.length; i++)
            for (j = 0; j < array.length; j++) {
                if (array[i][j] == 0) // if array[i][j] == empty space return the index
                    return new int[]{i, j};
            }
        return null;
    }

} // end class pass


