import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.*;

public class ElfRuckSuck {

    public static List<String> readFileByLines(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }

    public static Map<Character, Integer> envVariables() {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int count = 1;

        for (char ch = 'a'; ch <= 'z'; ++ch) {
            map.put(ch, count);
            count++;
        }
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            map.put(ch, count);
            count++;
        }
        // System.out.println(map);
        return map;
    }

    public static int totalRuckSuck2(List<String> lines) {
        Map<Character, Integer> map = envVariables();
        int sum = 0;
        int count1 = 1;
        int count2 = 1;
        int count3 = 1;

        String line1 = " ";
        String line2 = " ";
        // System.out.println(lines);
        for (String line : lines) {
            if (count1 == 1) {
                line1 = line;
                count1++;
                count2++;
                count3++;
                // System.out.println(line1);

            } else if (count2 == 2) {
                line2 = line;
                count2++;
                count3++;
                // System.out.println(line2);
            } else if (count3 == 3) {
                // System.out.println(line1 + " " + line2 + " " + line);
                count1 = 1;
                count2 = 1;
                count3 = 1;

                HashMap<Character, Integer> hs = new HashMap<Character, Integer>();
                for (int i = 0; i < line1.length(); i++) {
                    if (!hs.containsKey(line1.charAt(i)))
                        hs.put(line1.charAt(i), 0);
                    for (int j = 0; j < line2.length(); j++) {
                        for (int k = 0; k < line.length(); k++) {
                            if (line1.charAt(i) == line2.charAt(j) && line2.charAt(j) == line.charAt(k)) {
                                if (hs.get(line1.charAt(i)) == 0) {
                                    sum += map.get(line1.charAt(i));
                                    // System.out.println("Sum " + sum);
                                }
                                hs.put(line1.charAt(i), 1);
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    public static int totalRuckSuck1(List<String> lines) {
        Map<Character, Integer> map = envVariables();
        int sum = 0;
        for (String line : lines) {
            int n = line.length();
            String str1 = line.substring(0, n / 2);
            String str2 = line.substring(n / 2, n);
            // System.out.println(line + " len " + n);
            // System.out.println(str1 + " < Left Right > " + str2);
            HashMap<Character, Integer> hs = new HashMap<Character, Integer>();
            for (int i = 0; i < str1.length(); i++) {
                if (!hs.containsKey(str1.charAt(i)))
                    hs.put(str1.charAt(i), 0);
                for (int j = 0; j < str2.length(); j++) {
                    // System.out.println("Common " + str1.charAt(i) + str2.charAt(j));
                    if (str1.charAt(i) == str2.charAt(j)) {
                        // System.out.println("hs val " + hs.get(str1.charAt(i)));
                        if (hs.get(str1.charAt(i)) == 0) {
                            sum += map.get(str1.charAt(i));
                            // System.out.println("Sum " + sum);
                        }
                        hs.put(str1.charAt(i), 1);
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        // Day3 Part 1
        List<String> lines = readFileByLines("3.txt");
        // System.out.println(lines);
        System.out.println("Total Sum1 " + totalRuckSuck1(lines));
        // Day3 Part2
        System.out.println("Total Sum2 " + totalRuckSuck2(lines));

    }
}
