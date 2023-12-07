// Dungeon Crawl from Team 06
// Team member one: Clinton Ma
// Team member two: Andrew Basurto
// Team member three: Connor Wilson

// Run Map.java
import java.io.*;
import java.util.Scanner;

public class Map {
    // exit coord
    static int exitXcoord = 51; // x coord for exit (ALWAYS subtract 1)
    static int exitYcoord = 0; // y coord for exit (ALWAYS subtract 1)

    // enemy coords (multiple enemies)
    static int[] enemyXcoords = {10, 24, 3, 33, 47}; // x coord for enemy (ALWAYS subtract 1)
    static int[] enemyYcoords = {10, 20, 2, 3, 22}; // y coord for enemy (ALWAYS subtract 1)

    // number of enemy to defeat
    static int targetNumEnemyToDefeat = 5; // number of enemy to defeat

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        playMap(userInputScanner);
    }

    public static void playMap(Scanner userInputScanner) {
        // how many rows (for Y coord)
        int rows = 27;
        // how many columns (for X coord)
        int columns = 55;

        // 2d array of chars
        char[][] gameMap = generateMap(rows, columns);

        // starting player coords (ALWAYS make 1 LESS in x and y) if x = 10, choose x = 9, if y = 20, choose y = 19, etc
        int playerXcoord = 5; // x coord for player '#' are included
        int playerYcoord = 24; // y coord for player '#' are included

        // player
        gameMap[playerYcoord][playerXcoord] = '@';

        // print initial map
        printMap(gameMap);

        // game loop
        boolean exitReached = false;
        int numEnemyDefeated = 0; // init num of enemy defeated
        while (!exitReached) { // defeat at least 1 enemy
            // detect user input
            char move = getUserMove(userInputScanner);

            // update player coordinates
            int newPlayerXcoord = playerXcoord;
            int newPlayerYcoord = playerYcoord;

            switch (move) {
                case 'w':
                case 'W':
                    newPlayerYcoord--; // moves up
                    break;
                case 'a':
                case 'A':
                    newPlayerXcoord--; // moves left
                    break;
                case 's':
                case 'S':
                    newPlayerYcoord++; // moves down
                    break;
                case 'd':
                case 'D':
                    newPlayerXcoord++; // moves right
                    break;
            }

            // Check if the new position is within bounds and is not a wall
            boolean isValidMove = newPlayerYcoord >= 0 && newPlayerYcoord < rows && newPlayerXcoord >= 0
                    && newPlayerXcoord < columns && gameMap[newPlayerYcoord][newPlayerXcoord] != '#';

            // check if player landed on any enemy
            boolean landedOnEnemy = false;
            for (int i = 0; i < enemyXcoords.length; i++) {
                if (newPlayerYcoord == enemyYcoords[i] && newPlayerXcoord == enemyXcoords[i]) {
                    landedOnEnemy = true;
                    break;
                }
            }
            
            // Update player position only if it's a valid move
            if (isValidMove) {
                // Clear previous player position
                gameMap[playerYcoord][playerXcoord] = '-';

                // Set new player position
                gameMap[newPlayerYcoord][newPlayerXcoord] = '@';

                // Update player coordinates after the move is validated
                playerXcoord = newPlayerXcoord;
                playerYcoord = newPlayerYcoord;

                //if player lands on enemy
                if (landedOnEnemy) {
                    try {
                        executeEnemyBattle();
                        numEnemyDefeated++; // increase num of enemy defeated
                    }
                    catch (IOException e) {
                        e.printStackTrace(); // display error message if error
            }
        }
    }
            
            // Check if the player reached the exit
            exitReached = newPlayerYcoord == exitYcoord && newPlayerXcoord == exitXcoord && numEnemyDefeated >= targetNumEnemyToDefeat; // choose how many enemies to defeat

            // clear console
            System.out.println("\u001b[2J\u001b[H");
            System.out.flush();

            // print updated map
            printMap(gameMap);

            // if reach exit, run new Java file
            if (exitReached) {
                try {
                    // compile and run map2 within map
                    executeMap2();

                    // Exit the program after running Map2
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace(); // display error message if error
                }
            }
        }
    }
    

    public static char[][] generateMap(int x, int y) {
        // plug in the number of rows and columns in the map
        char[][] map = new char[x][y];

        // these are empty spaces
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = '-';
            }
        }

        // these are walls
        for (int i = 0; i < x; i++) {
            map[i][0] = '#'; // Left wall
            map[i][y - 1] = '#'; // Right wall
        }

        for (int j = 0; j < y; j++) {
            map[0][j] = '#'; // Top wall
            map[x - 1][j] = '#'; // Bottom wall
        }

        map[exitYcoord][exitXcoord] = 'X'; // X for exit

        // set enemy positions
        for (int i = 0; i < enemyXcoords.length; i++) {
            int enemyX = enemyXcoords[i];
            int enemyY = enemyYcoords[i];
            map[enemyY][enemyX] = '?'; // enemy
        }
        
        return map;
    }

    public static void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char getUserMove(Scanner sc) {
        System.out.println("Welcome to Map1");
        System.out.println("Defeat " + targetNumEnemyToDefeat + " Enemies to Advance." );
        System.out.print("Enter your move (W/A/S/D): ");
        char move = sc.next().charAt(0);
        sc.nextLine();
        return move;
    }

    public static void executeEnemyBattle() throws IOException {
        // command to compile MonsterBattle
        String compileCommand2 = "javac finalMath.java";

        // create a process builder to start a new program
        ProcessBuilder battleBuilder = new ProcessBuilder(compileCommand2.split("\\s+"));
        // sets the working directory for the new process to the current working directory
        battleBuilder.directory(new File(System.getProperty("user.dir"))); // Set the working directory

        // redirect the output and error streams
        battleBuilder.redirectErrorStream(true);
        // starts process EnemyBattle
        Process battleProcess = battleBuilder.start();

        // obtain the output stream
        try (InputStream battleInputStream = battleProcess.getInputStream();
             Scanner battleScanner = new Scanner(battleInputStream)) {

            while (battleScanner.hasNextLine()) {
                System.out.println(battleScanner.nextLine());
            }

            // wait for the battle process to complete
            int battleExitCode = battleProcess.waitFor();

            if (battleExitCode == 0) {
                // if compilation is successful, run finalMath
                executeEnemyBattleRun();
            } else {
                System.out.println("Error: finalMath compilation failed!");
            }
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeEnemyBattleRun() throws IOException {
        // command to run finalMath
        String runCommand = "java finalMath";
    
        // create a process builder to start the new program
        ProcessBuilder runBuilder = new ProcessBuilder(runCommand.split("\\s+"));
        runBuilder.directory(new File(System.getProperty("user.dir"))); // Set the working directory
    
        // inherit input, output, and error streams
        runBuilder.inheritIO();
    
        // starts process finalMath
        Process runProcess = runBuilder.start();
    
        try {
            // wait for the process to complete
            int runExitCode = runProcess.waitFor();
    
            if (runExitCode != 0) {
                // print an error message
                System.out.println("Error: finalMath did not execute successfully!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeMap2() throws IOException {
        // command to compile Map2
        String compileCommand = "javac Map2.java";

        // create a process builder to start a new program
        ProcessBuilder compileBuilder = new ProcessBuilder(compileCommand.split("\\s+"));
        // sets the working directory for the new process to the current working directory
        compileBuilder.directory(new File(System.getProperty("user.dir"))); // Set the working directory

        // redirect the output and error streams
        compileBuilder.redirectErrorStream(true);
        // starts process Map2
        Process compileProcess = compileBuilder.start();

        // obtain the output stream
        try (InputStream compileInputStream = compileProcess.getInputStream();
             Scanner compileScanner = new Scanner(compileInputStream)) {

            while (compileScanner.hasNextLine()) {
                System.out.println(compileScanner.nextLine());
            }

            // wait for the compilation process to complete
            int compileExitCode = compileProcess.waitFor();

            if (compileExitCode == 0) {
                // if compilation is successful, run Map2
                executeMap2Run();
            } else {
                System.out.println("Error: Map2 compilation failed!");
            }
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeMap2Run() throws IOException {
        // command to run Map2
        String runCommand = "java Map2";
    
        // create a process builder to start the new program
        ProcessBuilder runBuilder = new ProcessBuilder(runCommand.split("\\s+"));
        runBuilder.directory(new File(System.getProperty("user.dir"))); // Set the working directory
    
        // inherit input, output, and error streams
        runBuilder.inheritIO();
    
        // starts process Map2
        Process runProcess = runBuilder.start();
    
        try {
            // wait for the process to complete
            int runExitCode = runProcess.waitFor();
    
            if (runExitCode != 0) {
                // print an error message
                System.out.println("Error: Map2 did not execute successfully!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
