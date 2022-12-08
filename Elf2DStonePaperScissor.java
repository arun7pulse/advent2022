import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Elf2DStonePaperScissor {
    private Map<String, Integer> handSymbolPoint;
    private Map<String, Integer> resultPoints;

    private File getResourceFile(final String fileName) {
        URL url = this.getClass().getClassLoader().getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }

        File file = new File(url.getFile());

        return file;
    }

    public static void main(String arg[]) {
        Elf2DStonePaperScissor elf = new Elf2DStonePaperScissor();
        elf.setGamePoints();
        // elf.calcGameScoreByElfPatternGiven("day2-inputfile1.txt");
        elf.calcGameScoreByRndNeedsToEnd("2.txt");
    }

    public void setGamePoints() {
        // Player 2 points
        this.handSymbolPoint = new HashMap<>();
        this.handSymbolPoint.put("X", 1); // Rock
        this.handSymbolPoint.put("Y", 2); // Paper
        this.handSymbolPoint.put("Z", 3); // Scissor

        // Player 2 result Points with Player 1 competition
        this.resultPoints = new HashMap<>();
        // // Rock vs All
        this.resultPoints.put("A X", 3);
        this.resultPoints.put("A Y", 6);
        this.resultPoints.put("A Z", 0);

        // Paper vs All
        this.resultPoints.put("B X", 0);
        this.resultPoints.put("B Y", 3);
        this.resultPoints.put("B Z", 6);

        // Scissor vs All
        this.resultPoints.put("C X", 6);
        this.resultPoints.put("C Y", 0);
        this.resultPoints.put("C Z", 3);
    }

    /*
     * "Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
     * 
     * The total score is still calculated in the same way, but now you need to
     * figure out what shape to choose so the round ends as indicated. The example
     * above now goes like this:
     * 
     * In the first round, your opponent will choose Rock (A), and you need the
     * round to end in a draw (Y), so you also choose Rock. This gives you a score
     * of 1 + 3 = 4. In the second round, your opponent will choose Paper (B), and
     * you choose Rock so you lose (X) with a score of 1 + 0 = 1. In the third
     * round, you will defeat your opponent's Scissors with Rock for a score of 1 +
     * 6 = 7. Now that you're correctly decrypting the ultra top secret strategy
     * guide, you would get a total score of 12.
     * 
     * Following the Elf's instructions for the second column, what would your total
     * score be if everything goes exactly according to your strategy guide?
     */

    public void calcGameScoreByRndNeedsToEnd(String fileName) {
        try {
            File myObj = getResourceFile(fileName);
            Scanner myReader = new Scanner(myObj);
            int totalPointScored = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (resultPoints.get(data) != null) {
                    // win 6, loss 0 and draw 3 points
                    int gameResult = 0;
                    // this.resultPoints.get(data);
                    // For second player based on the column 2 value i.e.,
                    // X means you need to lose
                    // Y means you need to draw
                    // Z means you need to win
                    String player2OptedVal = data.split(" ")[1];
                    final String player1OptedVal = data.split(" ")[0];

                    List<Entry<String, Integer>> list = this.resultPoints.entrySet().stream()
                            .filter(e -> e.getKey().startsWith(player1OptedVal + " ")).map(e -> e)
                            .collect(Collectors.toList());
                    // System.out.println(list);
                    String secondPlayerKeyFound = null;
                    for (int i = 0; i < list.size(); i++) {
                        if (player2OptedVal.equals("X")) {
                            if (list.get(i).getValue() == 0) {
                                gameResult = 0;
                                String keyName = list.get(i).getKey();
                                secondPlayerKeyFound = keyName.split(" ")[1];
                                break;
                            }
                        } else if (player2OptedVal.equals("Y")) {
                            if (list.get(i).getValue() == 3) {
                                gameResult = 3;
                                String keyName = list.get(i).getKey();
                                secondPlayerKeyFound = keyName.split(" ")[1];
                                break;
                            }
                        } else if (player2OptedVal.equals("Z")) {
                            if (list.get(i).getValue() == 6) {
                                gameResult = 6;
                                String keyName = list.get(i).getKey();
                                secondPlayerKeyFound = keyName.split(" ")[1];
                                break;
                            }
                        }
                    }

                    // int gameResult = this.resultPoints.get(data);
                    int symbolPoint = this.handSymbolPoint.get(secondPlayerKeyFound);

                    System.out.println("symbolPoint " + symbolPoint + "\t+ game result " + gameResult);
                    totalPointScored += gameResult + symbolPoint;
                }
            }
            myReader.close();
            System.out.println("totalPointScored : " + totalPointScored);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void calcGameScoreByElfPatternGiven(String fileName) {
        try {
            File myObj = getResourceFile(fileName);
            Scanner myReader = new Scanner(myObj);
            int totalPointScored = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (resultPoints.get(data) != null) {
                    // win 6, loss 0 and draw 3 points
                    int gameResult = this.resultPoints.get(data);
                    int symbolPoint = this.handSymbolPoint.get(data.split(" ")[1]);
                    totalPointScored += gameResult + symbolPoint;
                }
            }
            myReader.close();
            System.out.println("totalPointScored : " + totalPointScored);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}