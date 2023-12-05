import java.util.Scanner;
public class FinalSubtract {
  public static void main(String[] args) {

    //Subtraction Enemy
    System.out.println("You will never defeat me. Unless you can remove my defences correctly.");
    Scanner floors=new Scanner(System.in);
    int floor = floors.nextInt();

    int sub1 = (int)((Math.random() * (10+floor+1)+ 1));
    int sub2 = (int)((Math.random() * (10+floor+1)+ 1));

    int subtraction=sub1-sub2;
    System.out.println("You will need to defeat me with a perfect value to get "+subtraction);
    System.out.print(sub1+"-");
    Scanner input=new Scanner(System.in);
    int userInput = input.nextInt();


    if (userInput==sub2) {
      System.out.print("How did you know that was my number.");
    }
    else {
      System.out.print("Ha. Wrong. I win this one.");
    }
  }
}
