import java.util.Scanner;
public class FinalMultiply {
  public static void main(String[] args) {

    //Multiplication Enemy
    System.out.println("You will never defeat me. Unless you can multiply up your damage correctly.");
    Scanner floors=new Scanner(System.in);
    int floor = floors.nextInt();

    int mult1 = (int)((Math.random() * (10+floor+1)+ 1));
    int mult2 = (int)((Math.random() * (10+floor+1)+ 1));

    int multiply=mult1*mult2;
    System.out.println("You will need to defeat me with a perfect value to get "+multiply);
    System.out.print(mult1+"*");
    Scanner input=new Scanner(System.in);
    int userInput = input.nextInt();


    if (userInput==mult2) {
      System.out.print("How did you know that was my number.");
    }
    else {
      System.out.print("Ha. Wrong. I win this one.");
    }
  }
}
