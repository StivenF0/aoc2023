import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class Day4p1 {
  static int getPoints(int num) {
    if (num == 0) return 0;
    return (int) Math.pow(2, num - 1);
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
    
    ArrayList<Integer> totalNum = new ArrayList<>();
    for (String cardsetstr : lines) {
      int correct = 0;
      String essential = cardsetstr.split(":")[1];
      String winners = essential.split("\\|")[0];
      String values = essential.split("\\|")[1];
      String[] winnersArr = winners.strip().split(" ");
      String[] valuesArr = values.strip().split(" ");
      for (String win : winnersArr) {
        if (win.isEmpty()) continue;
        for (String val : valuesArr) {
          if (val.isEmpty()) continue;
          System.out.println(val);
          correct += Integer.parseInt(win) == Integer.parseInt(val) ? 1 : 0;
        }
      }
      
      totalNum.add(getPoints(correct));
    }

    int sum = 0;
    for (int num : totalNum) {
      sum += num;
    }

    System.out.println(sum);
  }
}