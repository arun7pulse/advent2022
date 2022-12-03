import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElfRuckSuck {
    // public static calcRucksuck(ArrayList<String> str){

    // }

    public static void main(String[] args) {
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

        System.out.println(map);
        try {
            BufferedReader br = new BufferedReader(new FileReader("3.txt"));
            String line;
            int sum = 0;
            while ((line = br.readLine()) != null) {
                int n = line.length();
                // System.out.println(line + " len " + n);
                String str1 = line.substring(0, n / 2);
                String str2 = line.substring(n / 2, n);
                System.out.println(str1 + " < Left Right > " + str2);
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
                                System.out.println("Sum " + sum);
                            }
                            hs.put(str1.charAt(i), 1);
                            // System.out.println("HS " + hs);
                        }
                    }
                }

            }
            System.out.println("Total Sum " + sum);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
