import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day4p2 {
  static String getCardId(String cardString) {
    String id = "";
    for (int i = 0; i < cardString.split(":")[0].strip().length(); i++) {
      if (!Character.isDigit(cardString.charAt(i))) continue;
      id += cardString.charAt(i);
    }
    return id;
  }

  public static void main(String[] args) {

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


    // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    ArrayList<Map<String, Integer>> games = new ArrayList<>();

    /*
     * game = {
     *  id: 0,
     *  points: 0,
     *  effect: 1,
     * }
     */
    Map<String, Integer> game = new HashMap<>();
    String id;
    String[] winnerNums;
    String[] hasNums;
    int points;
    for (String line : lines) {
      game.clear();
      
      id = getCardId(line);
      winnerNums = line.split(":")[1].strip().split("\\|")[0].strip().split(" ");
      hasNums = line.split(":")[1].strip().split("\\|")[1].strip().split(" ");
      points = 0;

      for (String winnerNum : winnerNums) {
        if (winnerNum.isEmpty()) continue;
        for (String hasNum : hasNums) {
          if (hasNum.isEmpty()) continue;
          if (Integer.parseInt(hasNum) == Integer.parseInt(winnerNum)) points++;
        }
      }

      game.put("id", Integer.parseInt(id));
      game.put("points", points);
      game.put("effect", 1);

      games.add(new HashMap<>(game));
    }

    
    for (int idx = 0; idx < games.size(); idx++) {
      Map<String, Integer> currGame = games.get(idx);
      
      if (currGame.get("points") == 0) continue;
      for (int iter = idx + 1; iter < idx + 1 + currGame.get("points"); iter++) {
        Map<String, Integer> workingGame = games.get(iter);
        workingGame.put("effect", workingGame.get("effect") + currGame.get("effect"));
      }
    }

    int sum = 0;
    for (int i = 0; i < games.size(); i++) {
      sum += games.get(i).get("effect");
    }

    System.out.println("Sum: " + sum);
  }
}