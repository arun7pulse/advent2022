
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ElfD5CargoCrane {

    public static String findPart2(List<String> dataList, HashMap<Integer, Stack<Character>> hs2) {
        System.out.println(hs2);
        for (String data : dataList) {
            String[] val = data.split("\\s");
            int loop = Integer.parseInt(val[0]);
            int fromstack = Integer.parseInt(val[1]);
            int toStack = Integer.parseInt(val[2]);

            if (loop == 1) {
                for (int i = 0; i < loop; i++) {
                    char item = hs2.get(fromstack).pop();
                    // System.out.println("popped " + item);
                    hs2.get(toStack).push(item);
                    // System.out.println("Pushed stack " + hs.get(toStack));
                    // System.out.println("Each move " + hs);
                }
            } else if (loop > 1) {
                Stack<Character> tempStack = new Stack<>();
                // System.out.println("toStack " + hs.get(toStack));
                for (int i = 0; i < loop; i++) {
                    char item = hs2.get(fromstack).pop();
                    tempStack.push(item); // D, N, Z
                }
                // System.out.println("tempstack " + tempStack);
                int fixedSize = tempStack.size();
                for (int i = 0; i < fixedSize; i++) {
                    // System.out.println(" temp stack size" + tempStack.size());
                    char item = tempStack.pop();
                    hs2.get(toStack).push(item); // P, Z, N, D
                }
                // System.out.println("after merge " + hs.get(toStack));
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= hs2.size(); j++) {
            sb.append(hs2.get(j).peek());
        }
        return sb.toString();
    }

    public static String findPart1(List<String> dataList, HashMap<Integer, Stack<Character>> hs1) {
        System.out.println(hs1);
        for (String data : dataList) {
            // System.out.println(data + "\n");

            String[] val = data.split("\\s");
            int loop = Integer.parseInt(val[0]);
            int fromstack = Integer.parseInt(val[1]);
            int toStack = Integer.parseInt(val[2]);

            for (int i = 0; i < loop; i++) {
                char item = hs1.get(fromstack).pop();
                // System.out.println("popped " + item);
                hs1.get(toStack).push(item);
                // System.out.println("Pushed stack " + hs.get(toStack));
                // System.out.println("Each move " + hs);
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= hs1.size(); j++) {
            sb.append(hs1.get(j).peek());
        }
        return sb.toString();

    }

    public static void main(String arg[]) {
        ElfD5CargoCrane elf = new ElfD5CargoCrane();
        List<String> dataList = elf.readFile("5.txt");
        HashMap<Integer, Stack<Character>> hs = new HashMap<Integer, Stack<Character>>();
        ArrayList<Character> A1 = new ArrayList<Character>(Arrays.asList('B', 'Z', 'T'));
        ArrayList<Character> A2 = new ArrayList<Character>(Arrays.asList('V', 'H', 'T', 'D', 'N'));
        ArrayList<Character> A3 = new ArrayList<Character>(Arrays.asList('B', 'F', 'M', 'D'));
        ArrayList<Character> A4 = new ArrayList<Character>(Arrays.asList('T', 'J', 'G', 'W', 'V', 'Q', 'L'));
        ArrayList<Character> A5 = new ArrayList<Character>(Arrays.asList('W', 'D', 'G', 'P', 'V', 'F', 'Q', 'M'));
        ArrayList<Character> A6 = new ArrayList<Character>(Arrays.asList('V', 'Z', 'Q', 'G', 'H', 'F', 'S'));
        ArrayList<Character> A7 = new ArrayList<Character>(Arrays.asList('Z', 'S', 'N', 'R', 'L', 'T', 'C', 'W'));
        ArrayList<Character> A8 = new ArrayList<Character>(Arrays.asList('Z', 'H', 'W', 'D', 'J', 'N', 'R', 'M'));
        ArrayList<Character> A9 = new ArrayList<Character>(Arrays.asList('M', 'Q', 'L', 'F', 'D', 'S'));

        Stack<Character> one = new Stack<Character>();
        one.addAll(A1);
        Stack<Character> two = new Stack<Character>();
        two.addAll(A2);
        Stack<Character> three = new Stack<Character>();
        three.addAll(A3);
        Stack<Character> four = new Stack<Character>();
        four.addAll(A4);
        Stack<Character> five = new Stack<Character>();
        five.addAll(A5);
        Stack<Character> six = new Stack<Character>();
        six.addAll(A6);
        Stack<Character> seven = new Stack<Character>();
        seven.addAll(A7);
        Stack<Character> eight = new Stack<Character>();
        eight.addAll(A8);
        Stack<Character> nine = new Stack<Character>();
        nine.addAll(A9);

        hs.put(1, one);
        hs.put(2, two);
        hs.put(3, three);
        hs.put(4, four);
        hs.put(5, five);
        hs.put(6, six);
        hs.put(7, seven);
        hs.put(8, eight);
        hs.put(9, nine);
        System.out.println(hs);
        HashMap<Integer, Stack<Character>> hs2 = hs;
        System.out.println("Day5 Part1 " + findPart1(dataList, hs));
        System.out.println(hs2);
        System.out.println("Day5 Part2 " + findPart2(dataList, hs2));

    }

    private File getResourceFile(final String fileName) {
        URL url = this.getClass().getClassLoader().getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }

        File file = new File(url.getFile());

        return file;
    }

    public List<String> readFile(String fileName) {
        List<String> dataList = new ArrayList<>();
        try {
            File myObj = getResourceFile(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                dataList.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dataList;
    }
}