import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Elf6DMarker {
    public static List<String> readFileByLines(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }

    public static void main(String[] args) throws IOException {
        // Day Part 1
        List<String> lines = readFileByLines("6.txt");
        // System.out.println(lines);
        System.out.println("Part1 Ans " + findPart1(lines));
        // Day Part2
        // System.out.println("Part2 Ans " + findPart2(lines));

    }

    private static int findPart1(List<String> lines) {
        // String result = " ";
        System.out.println("Lines" + lines);
        String A = lines.get(0);
        int n = A.length();
        int marker = 0;

        for (int i = 3; i < n; i++) {
            HashSet<Character> s = new HashSet<Character>();
            System.out.println(A.charAt(i - 3) + " " + A.charAt(i - 2) + " " + A.charAt(i - 1) + " " + A.charAt(i));
            s.add(A.charAt(i - 3));
            s.add(A.charAt(i - 2));
            s.add(A.charAt(i - 1));
            s.add(A.charAt(i));
            if (s.size() == 4) {
                marker = i + 1;
                break;
            }
            // if (A.charAt(i - 3) != A.charAt(i - 2)
            // || A.charAt(i - 3) != A.charAt(i - 1)
            // || A.charAt(i - 3) != A.charAt(i)
            // || A.charAt(i - 2) != A.charAt(i - 1)
            // || A.charAt(i - 2) != A.charAt(i)
            // || A.charAt(i - 1) != A.charAt(i)) {
            // System.out.println("Yes Yes");
            // marker = i;
            // break;
            // }

        }
        return marker;
    }
}
