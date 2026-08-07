import java.util.*;

class Solution {

    // Contribution of each digit (1-9)
    static final int[][] contrib = {
            {0,0,0,0}, //0 unused
            {0,0,0,0}, //1
            {1,0,0,0}, //2
            {0,1,0,0}, //3
            {2,0,0,0}, //4
            {0,0,1,0}, //5
            {1,1,0,0}, //6
            {0,0,0,1}, //7
            {3,0,0,0}, //8
            {0,2,0,0}  //9
    };

    String num;
    int n;
    HashMap<String, Boolean> memo;

    public String smallestNumber(String num, long t) {

        int[] need = factorize(t);
        if (need == null) return "-1";

        this.num = num;

        for (int len = num.length(); len <= num.length() + 30; len++) {

            n = len;
            memo = new HashMap<>();

            StringBuilder ans = new StringBuilder();

            boolean ok;

            if (len == num.length()) {
                ok = dfs(0, need[0], need[1], need[2], need[3], true, ans);
            } else {
                ok = dfsLonger(0, need[0], need[1], need[2], need[3], ans);
            }

            if (ok) return ans.toString();
        }

        return "-1";
    }

    // Factorize t into 2,3,5,7
    private int[] factorize(long t) {

        int[] cnt = new int[4];

        while (t % 2 == 0) {
            cnt[0]++;
            t /= 2;
        }

        while (t % 3 == 0) {
            cnt[1]++;
            t /= 3;
        }

        while (t % 5 == 0) {
            cnt[2]++;
            t /= 5;
        }

        while (t % 7 == 0) {
            cnt[3]++;
            t /= 7;
        }

        if (t != 1) return null;

        return cnt;
    }

    // Same length (must be >= num)
    private boolean dfs(int pos, int a, int b, int c, int d,
                        boolean tight, StringBuilder ans) {

        if (a > (n - pos) * 3) return false;
        if (b > (n - pos) * 2) return false;
        if (c > (n - pos)) return false;
        if (d > (n - pos)) return false;

        if (pos == n)
            return a == 0 && b == 0 && c == 0 && d == 0;

        String key = pos + "#" + a + "#" + b + "#" + c + "#" + d + "#" + tight;
        if (memo.containsKey(key))
            return false;

        int start = tight ? num.charAt(pos) - '0' : 1;

        if (start == 0) start = 1;

        for (int dig = start; dig <= 9; dig++) {

            int na = Math.max(0, a - contrib[dig][0]);
            int nb = Math.max(0, b - contrib[dig][1]);
            int nc = Math.max(0, c - contrib[dig][2]);
            int nd = Math.max(0, d - contrib[dig][3]);

            ans.append((char) ('0' + dig));

            if (dfs(pos + 1,
                    na, nb, nc, nd,
                    tight && dig == (num.charAt(pos) - '0'),
                    ans))
                return true;

            ans.deleteCharAt(ans.length() - 1);
        }

        memo.put(key, false);
        return false;
    }

    // Length > original
    private boolean dfsLonger(int pos, int a, int b, int c, int d,
                              StringBuilder ans) {

        if (a > (n - pos) * 3) return false;
        if (b > (n - pos) * 2) return false;
        if (c > (n - pos)) return false;
        if (d > (n - pos)) return false;

        if (pos == n)
            return a == 0 && b == 0 && c == 0 && d == 0;

        String key = "L#" + pos + "#" + a + "#" + b + "#" + c + "#" + d;
        if (memo.containsKey(key))
            return false;

        for (int dig = 1; dig <= 9; dig++) {

            int na = Math.max(0, a - contrib[dig][0]);
            int nb = Math.max(0, b - contrib[dig][1]);
            int nc = Math.max(0, c - contrib[dig][2]);
            int nd = Math.max(0, d - contrib[dig][3]);

            ans.append((char) ('0' + dig));

            if (dfsLonger(pos + 1, na, nb, nc, nd, ans))
                return true;

            ans.deleteCharAt(ans.length() - 1);
        }

        memo.put(key, false);
        return false;
    }
}