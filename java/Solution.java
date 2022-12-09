import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
        B.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
        B.add(new ArrayList<Integer>(Arrays.asList(5, 6, 7, 0)));
        B.add(new ArrayList<Integer>(Arrays.asList(9, 2, 0, 4)));
        // B.add(new ArrayList<Integer>(Arrays.asList(1, 10)));
        System.out.println(solve(B));
    }

    public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        int m = A.get(0).size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j = 0; j < m; j++) {
                if (A.get(i).get(j) == 0) {
                    arr.add(0);
                } else {
                    arr.add(1);
                }
            }
            result.add(arr);
        }
        // for(int i=0;i<n;i++){
        // for(int j=0;j<m;j++){
        // if(A.get(i).get(j) == 0){

        // }
        // } return result;
        // }
        return result;
    }
}
