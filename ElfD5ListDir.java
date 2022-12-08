
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ElfD5ListDir {

    public static void main(String arg[]) {
        ElfD5ListDir elf = new ElfD5ListDir();
        List<String> A = elf.readFile("7.txt");
        System.out.println(A);

        int n = A.size();
        for (int j = 0; j < n; j++)
            System.out.println(A.get(j));
        System.out.println();
        int sum = 0;
        HashMap<String, Integer> hs = new HashMap<String, Integer>();
        // Stack<HashMap<String, Integer>> stk = new Stack<HashMap<String, Integer>>();
        Stack<String> stk = new Stack<String>();
        String lastDir = "";

        for (int i = 0; i < n; i++) {
            String[] val = A.get(i).split(" ");
            // System.out.println("Processing " + A.get(i));
            if (val.length == 3) {
                String cmd = val[1];
                String path = val[2];
                if (path.equals("..")) {
                    String s = stk.pop();
                    System.out.println("popped " + s);
                    lastDir = stk.peek();
                    int sval = hs.get(s);
                    String root = stk.peek();
                    hs.put(root, hs.get(root) + sval);
                }

                if (!path.equals(" ") && !path.equals("..")) {
                    stk.push(path);
                    lastDir = stk.peek();
                    hs.put(lastDir, 0);
                }
                System.out.println("1.1 " + cmd + " " + path + " " + stk + " hs" + hs + " last " + lastDir);
            } else if (val.length == 2) {
                String cmd = val[0];
                String path = val[1];
                if (path.equals("ls")) {
                    System.out.println("2.1 " + cmd + " " + path + " " + stk + " hs" + hs + " last " + lastDir);
                } else if (path.equals("dir")) {
                    System.out.println("2.2" + cmd + " " + path + " " + stk + " hs" + hs + " last " + lastDir);
                } else {
                    System.out.println("2.3 " + cmd + " " + path + " " + stk + " hs" + hs + " last " + lastDir);
                    if (!cmd.equals("dir")) {
                        hs.put(lastDir, Integer.parseInt(cmd) + hs.get(lastDir));
                    }
                }
            }

        }
        System.out.println("2.X " + stk + " hs" + hs + " last " + lastDir);

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