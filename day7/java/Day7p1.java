import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day7p1 {
  static String getPrimitiveType(String cardSet) {
    Map<String, Integer> cardCount = new HashMap<>();
    for (int charIdx = 0; charIdx < cardSet.length(); charIdx++) {
      String card = "" + cardSet.charAt(charIdx);
      if (cardCount.get(card) == null) cardCount.put(card, 0);
      cardCount.put(card, cardCount.get(card) + 1);
    }
    ArrayList<Integer> cardCountArray = new ArrayList<>(cardCount.values());
    Collections.sort(cardCountArray);

    String primitiveType = 
      cardCountArray.stream()
      .map(Object::toString)
      .collect(Collectors.joining(" "));

    return primitiveType;
  }

  static int findIndex(String[] arr, String target) {
    for (int i = 0; i < arr.length; i++) {
      char current = arr[i].charAt(0);
      char ctarget = target.charAt(0);
      if (current == ctarget) {
        return i;
      }
    }
    return -1; 
  }

  static ArrayList<Integer> parseCardsetStrenght(String cardset, String cardStrenght[]) {
    ArrayList<Integer> cardsetStrenght = new ArrayList<>();
    for (String card : cardset.split("")) {
      cardsetStrenght.add(findIndex(cardStrenght, card) + 1);
    }
    return cardsetStrenght;
  }

  static ArrayList<Map<String, String>> sortCardsByStrenght(
    ArrayList<Map<String, String>> cardsArr,
    String cardStrenght[]
  ) {
    int leng = cardsArr.size();
    for (int i = 0; i < leng - 1; i++) {
      for (int j = 0; j < leng - i - 1; j++){
        boolean currentGreater = false;
        ArrayList<Integer> currentCardSet = parseCardsetStrenght(cardsArr.get(j).get("cards"), cardStrenght);
        ArrayList<Integer> nextCardSet = parseCardsetStrenght(cardsArr.get(j + 1).get("cards"), cardStrenght);
        for (int cardIdx = 0; cardIdx < 5; cardIdx++) {
          if (currentCardSet.get(cardIdx) == nextCardSet.get(cardIdx)) continue;
          if (currentCardSet.get(cardIdx) > nextCardSet.get(cardIdx)) {
            currentGreater = true;
            break;
          }
        }

        if (currentGreater) {
          Map<String, String> temp = cardsArr.get(j);
          cardsArr.set(j, cardsArr.get(j + 1));
          cardsArr.set(j + 1, temp);
        }
      }
    }

    return cardsArr;
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
    
    // int rankCount = lines.size();
    ArrayList<Map<String, String>> cardSets = new ArrayList<>();
    Map<String, String> cardSet = new HashMap<>();
    /*
     * cardSet = {
     *  cards,
     *  bid,
     *  type,
     * }
     */

    // Getting all cards.
    for (String cardstr : lines) {
      cardSet.clear();
      cardSet.put("cards", cardstr.split(" ")[0]);
      cardSet.put("bid", cardstr.split(" ")[1]);
      cardSets.add(new HashMap<>(cardSet));
    }

    // Defining the cards type
    Map<String, String> cardTypesMap = new HashMap<>();
    cardTypesMap.put("5", "five");
    cardTypesMap.put("1 4", "four");
    cardTypesMap.put("2 3", "full");
    cardTypesMap.put("1 1 3", "three");
    cardTypesMap.put("1 2 2", "two");
    cardTypesMap.put("1 1 1 2", "one");
    cardTypesMap.put("1 1 1 1 1", "high");
    for (Map<String, String> cards : cardSets) {
      String primitiveType = getPrimitiveType(cards.get("cards"));
      String cardtype = cardTypesMap.get(primitiveType);
      cards.put("type", cardtype);
    }

    // Getting the rank in the type
    ArrayList<Map<String, String>> cardsByCardTypes = new ArrayList<>();
    // [[card, card], [card, card]]
    String cardStrenght[] = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    String cardTypes[] = {"high", "one", "two", "three", "full", "four", "five"};
    for (String cardType : cardTypes) {
      ArrayList<Map<String, String>> cardsFromtype = new ArrayList<>();
      for (Map<String, String> cards : cardSets) {
        if (cards.get("type") == cardType) {
          cardsFromtype.add(cards);
        }
      }

      cardsFromtype = sortCardsByStrenght(cardsFromtype, cardStrenght);
      for (Map<String, String> currentCardSet : cardsFromtype) {
        cardsByCardTypes.add(new HashMap<>(currentCardSet));
      }
    }

    long sum = 0;
    for (int rank = 0; rank < cardsByCardTypes.size(); rank++) {
      System.out.println("rank "+ (rank + 1) + ": " + cardsByCardTypes.get(rank));
      sum += (rank+1) * Integer.parseInt(cardsByCardTypes.get(rank).get("bid"));
    }

    System.out.println("Sum: " + sum);
  }
}