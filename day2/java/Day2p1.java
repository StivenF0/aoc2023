import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class Day2p1 {
  static int getCubes(String cubeStr) {
    String trimmedStr = cubeStr.trim();
    String[] stringParts = trimmedStr.split(" ");
    int cubes = Integer.parseInt(stringParts[0]);
    return cubes;
  }

  public static void main(String[] args) {
    final Map<String, Integer> MAX = new HashMap<>();
    MAX.put("red", 12);
    MAX.put("green", 13);
    MAX.put("blue", 14);

    ArrayList<String> lines = new ArrayList<>();
    try {
      File inputFile = new File("../placeholder.txt");
      Scanner inputScanner = new Scanner(inputFile);
      while (inputScanner.hasNextLine()) {
        lines.add(inputScanner.nextLine().trim());
      }
      inputScanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    ArrayList<Map<String, Integer>> games = new ArrayList<>();
    for (String line : lines) {
      int id = Integer.parseInt(line.split(":")[0].split(" ")[1]);
      String[] gameRounds = line.split(":")[1].split(";");
      int redMax = 0;
      int greenMax = 0;
      int blueMax = 0;
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

      Map<String, Integer> game = new HashMap<>();
      game.put("id", id);
      game.put("red", redMax);
      game.put("green", greenMax);
      game.put("blue", blueMax);
      
      games.add(game);
    }

    int gamesPossible = 0;
    for (Map<String, Integer> game : games) {
      if (
        game.get("red") > MAX.get("red") ||
        game.get("green") > MAX.get("green") ||
        game.get("blue") > MAX.get("blue")
      ) {
        continue;
      }
      gamesPossible += game.get("id");
    }

    System.out.println(gamesPossible);
  }
}