import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Interval {
	int x;
	int y;

	public Interval(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Bomboane {
	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		File myObj = new File("bomboane.in");
		Scanner sc = new Scanner(myObj);
		n = sc.nextInt();
		m = sc.nextInt();
		Interval[] intervals = new Interval[n];
		int x, y;
		long min = 0, max = 0;

		for (int i = 0; i < n; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			min += x;
			max += y;
			intervals[i] = new Interval(x, y);
		}

		if (min > m) {
			printSolution(0);
			return;
		}
		if (min == m || max == m) {
			printSolution(1);
			return;
		}
		if (max < m) {
			printSolution(0);
			return;
		}

		long[][] dp = new long[n][m + 1];
		for (int i = intervals[0].x; i <= intervals[0].y; i++)
			dp[0][i] = 1;

		printSolution(solveDP(dp, intervals));
	}

	public static long solveDP(long[][] dp, Interval[] intervals) {
		int stop, start;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				stop = j - intervals[i].x;
				start = j - intervals[i].y;
				dp[i][j] = suma(start, stop, dp, i - 1);
			}
		}
		return dp[n - 1][m];
	}

	public static long suma(int start, int stop, long[][] dp, int line) {
		if (start < 0)
			start = 0;
		if (stop < 0)
			return 0;
		long suma = 0;

		for (int i = start; i <= stop; i++) {
			suma += dp[line][i] % 1000000007;
			suma %= 1000000007;
		}
		return suma;
	}

	public static void printSolution(long solution) throws IOException {
		File output = new File("bomboane.out");
		FileWriter fr = new FileWriter(output);
		fr.write(Long.toString(solution));
		fr.close();
	}
}
