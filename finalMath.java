import java.util.Scanner;

public class finalMath {
    public static int addition(int floor, int lives) {
        System.out.println("You will never defeat me. Unless you can add up your damage correctly.");

        int add1 = (int) ((Math.random() * ((10+floor)+1) + 1));
        int add2 = (int) ((Math.random() * ((10+floor)+1) + 1));
        int addition = add1 + add2;
        System.out.println("You will need to defeat me with a perfect value to get to " + addition+".");
        System.out.println("floor:"+ floor);
        lives = floor/5;
        System.out.println("Lives:" + lives);
        System.out.print(addition + "=" + add1 + "+");
        Scanner input = new Scanner(System.in);
        int userInput = input.nextInt();

        if (userInput == add2) {
            System.out.println("Correct! You get to move onto the next level.");
            return floor + 1;
        } else {
          if(floor/5 >= 1) {
            floor -= 5;
            return floor;
          } else {
              System.out.println("Wrong. You have no extra lives, so start over.");
              return floor = 1;
            }
          }
    }
    public static int subtraction(int floor, int lives) {
      System.out.println("You will never defeat me. Unless you can remove my defences correctly.");

      int sub1 = (int)((Math.random() * ((10+floor)+1)+ 1));
      int sub2 = (int)((Math.random() * ((10+floor)+1)+ 1));

      int subtraction=sub1-sub2;
      System.out.println("You will need to defeat me with a perfect value to get to " + subtraction);
      System.out.println("floor:" + floor);
      lives = floor/5;
      System.out.println("Lives:" + lives);
      System.out.print(subtraction + "=" + sub1 + "-");
      Scanner input = new Scanner(System.in);
      int userInput = input.nextInt();

      if (userInput == sub2) {
          System.out.print("Correct! You get to move onto the next level.");
          return floor + 1;
      } else {
        if(floor/5 >= 1) {
          floor -= 5;
          return floor;
        } else {
            System.out.println("Wrong. You have no extra lives, so start over.");
            return floor = 1;
          }

        }
    }
    public static int multiplication(int floor, int lives) {
      System.out.println("You will never defeat me. Unless you can multiply up your damage correctly.");

      int mult1 = (int)((Math.random() * ((10+floor)+1)+ 1));
      int mult2 = (int)((Math.random() * ((10+floor)+1)+ 1));

      int multiply=mult1*mult2;
      System.out.println("You will need to defeat me with a perfect value to get to "+multiply);
      System.out.println("floor:" + floor);
      lives = floor/5;
      System.out.println("Lives:" + lives);
      System.out.print(multiply + "=" + mult1 + "*");
      Scanner input=new Scanner(System.in);
      int userInput = input.nextInt();


      if (userInput==mult2) {
          System.out.print("Correct! You get to move onto the next level.");
          return floor + 1;
      } else {
        if(floor/5 >= 1) {
            floor -= 5;
            return floor;
        } else {
            System.out.println("Wrong. You have no extra lives, so start over.");
            return floor = 1;
          }
        }
    }

    public static void main(String[] args) {
        boolean quit=false;
        int floor = 1;
        int lives = 0;
        do {
          for (int i = 0; i < 50; ++i) System.out.println();
          System.out.println("This is a test of your math skills \nEnter a number corresponding to the options");
          System.out.println("1) Addition \n2) Subtraction \n3) Multiplication \n'q' or 4 to Quit");
          System.out.print("Select a Problem Type: ");
          Scanner input = new Scanner(System.in);
          String option = input.next();
          for (int i = 0; i < 50; ++i) System.out.println();
          if (option.equals("1")) {
              System.out.println("Selected Addition");
              floor = addition(floor, lives);
          } else if (option.equals("2")) {
              System.out.println("Selected Subtraction");
              floor = subtraction(floor, lives);
          } else if (option.equals("3")) {
              System.out.println("Selected Multiplication");
          } else if (option.equals("q")|option.equals("Q")|option.equals("4")) {
              System.out.println("You Quit\n");
              quit=true;
          } else {
              System.out.println("Please Try again.");
          }

        } while (!quit);
    }
}
