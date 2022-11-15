import java.util.*;
import java.io.*;

public class A_Lucky_Sum_of_Digits {
  static final int mod = 1_000_000_007;

  public static void main(String[] args) {
    FastScanner fs = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    // int test=fs.nextInt();
    int test = 1;
    for (int tt = 1; tt <= test; tt++) {
      int n = fs.nextInt();
      findMin(n);

    }

  }

  static void findMin(int sum) {
    int a = 0, b = 0;
    while (sum > 0) {
      // Cases where all remaining digits
      // are 4 or 7 (Remaining sum of digits
      // should be multiple of 4 or 7)
      if (sum % 7 == 0) {
        b++;
        sum -= 7;
      } else if (sum % 4 == 0) {
        a++;
        sum -= 4;
      }

      // If both 4s and 7s are there
      // in digit sum, we subtract a 4.
      else {
        a++;
        sum -= 4;
      }
    }

    if (sum < 0) {
      System.out.print("-1");
      return;
    }

    for (int i = 0; i < a; i++)
      System.out.print("4");

    for (int i = 0; i < b; i++)
      System.out.print("7");

    System.out.println();
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
