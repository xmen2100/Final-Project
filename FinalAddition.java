import java.util.Scanner;
public class FinalAddition {
  public static void main(String[] args) {

    //Addition Enemy
    System.out.println("You will never defeat me. Unless you can add up your damage correctly.");
    Scanner floors=new Scanner(System.in);
    int floor = floors.nextInt();

    int add1 = (int)((Math.random() * ((10+floor)+1)+ 1));
    int add2 = (int)((Math.random() * ((10+floor)+1)+ 1));

    int addition=add1+add2;
    System.out.println("You will need to defeat me with a perfect value to get "+addition);
    System.out.print(add1+"+");
    Scanner input=new Scanner(System.in);
    int userInput = input.nextInt();


    if (userInput==add2) {
      System.out.print("How did you know that was my number.");
    }
    else {
      System.out.print("Ha. Wrong. I win this one.");
    }
  }
}
