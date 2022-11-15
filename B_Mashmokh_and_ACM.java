import java.util.*;
import java.io.*;

public class B_Mashmokh_and_ACM {
    static final int mod = 1_000_000_007;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        // int test=fs.nextInt();
        int test = 1;
        for (int tt = 1; tt <= test; tt++) {
            int n = fs.nextInt();
            int k = fs.nextInt();
            int d[][] = new int[2010][2010];
            for (int i = 1; i <= 2000; i++)
                d[1][i] = 1;

            for (int l = 1; l <= (k - 1); l++)
                for (int x = 1; x <= n; x++)
                    for (int i = 1; i * x <= n; i++) {
                        d[l + 1][i * x] += d[l][x];
                        d[l + 1][i * x] -= d[l + 1][i * x] / mod * mod;
                    }

            int res = 0;
            for (int x = 1; x <= n; x++) {
                res += d[k][x];
                res -= res / mod * mod;
            }
            System.out.println(res);

        }

    }

    static void printArray(int a[]) {
        for (int x : a) {
            System.out.print(x + " ");
        }
    }

    static int[] listToArray(ArrayList<Integer> a1) {
        int[] arr = a1.stream().mapToInt(i -> i).toArray();

        return arr;
    }

    static int gcd(int a, int b) {

        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    static void dfs(int node, ArrayList<ArrayList<Integer>> adj,
            int vis[]) {
        vis[node] = 1;
        for (Integer it : adj.get(node)) {
            if (vis[it] == 0) {
                dfs(it, adj, vis);
            }
        }
    }

    static long add(long a, long b) {
        return (a + b) % mod;
    }

    static long sub(long a, long b) {
        return ((a - b) % mod + mod) % mod;
    }

    static long mul(long a, long b) {
        return (a * b) % mod;
    }

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class pair {
        int f;
        int s;

        pair(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }
}
