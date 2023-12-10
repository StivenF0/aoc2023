import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class Day2p2 {
  // Get cubes in a game string
  static int getCubes(String cubeStr) {
    String trimmedStr = cubeStr.trim();
    String[] stringParts = trimmedStr.split(" ");
    int cubes = Integer.parseInt(stringParts[0]);
    return cubes;
  }

  public static void main(String[] args) {
    // Reading the input file
    ArrayList<String> lines = new ArrayList<>();
    try {
      File inputFile = new File("../input.txt");
      Scanner inputScanner = new Scanner(inputFile);
      while (inputScanner.hasNextLine()) {
        lines.add(inputScanner.nextLine().trim());
      }
      inputScanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // Getting all the rounds maximum values in each game.
    ArrayList<Map<String, Integer>> games = new ArrayList<>();
    for (String line : lines) {
      String[] gameRounds = line.split(":")[1].split(";");
      int redMax = 0;
      int greenMax = 0;
      int blueMax = 0;

      // Getting max values of cubes on each set of an round.
      for (String gameRound : gameRounds) {
        String[] cubes = gameRound.split(",");
        for (String cubeRound : cubes) {
          if (cubeRound.contains("red")) {
            redMax = getCubes(cubeRound) > redMax ? getCubes(cubeRound) : redMax;
          }
          if (cubeRound.contains("green")) {
            greenMax = getCubes(cubeRound) > greenMax ? getCubes(cubeRound) : greenMax;
          }
          if (cubeRound.contains("blue")) {
            blueMax = getCubes(cubeRound) > blueMax ? getCubes(cubeRound) : blueMax;
          }
        }
      }

      // Appending to the games list.
      Map<String, Integer> game = new HashMap<>();
      game.put("red", redMax);
      game.put("green", greenMax);
      game.put("blue", blueMax);
      
      games.add(game);
    }

    // Doing the operation of power for each game
    int powerSum = 0;
    for (Map<String, Integer> game : games) {
      powerSum += game.get("red") * game.get("green") * game.get("blue");
    }

    System.out.println(powerSum);
  }
}