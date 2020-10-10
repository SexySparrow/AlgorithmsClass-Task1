import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

class Gantere {
	int greutate;
	int repetari;

	public Gantere(int greutate, int repetari) {
		this.greutate = greutate;
		this.repetari = repetari;
	}

	@Override
	public String toString() {
		return "Gantere{" +
				"greutate=" + greutate +
				", repetari=" + repetari +
				'}';
	}
}

public class Sala {

	public static void main(String[] args) throws IOException {
		File myObj = new File("sala.in");
        BufferedReader br = new BufferedReader(new FileReader(myObj));
        String line = br.readLine();
        String[] lines = line.split(" ");
		int n = Integer.parseInt(lines[0]);
		int m = Integer.parseInt(lines[1]);
		Gantere[] gantere = new Gantere[n];
		int x, y;

		for (int i = 0; i < n; i++){
		    line = br.readLine();
            lines = line.split(" ");
            x = Integer.parseInt(lines[0]);
            y = Integer.parseInt(lines[1]);
            gantere[i] = new Gantere(x, y);
        }

		Arrays.sort(gantere, new SortByReps());

		int index = 0;
		int minim = gantere[index].greutate;
		long repetari = 0;
		while (m > 0) {
			if (index > n - 1)
				break;

			repetari += gantere[index].repetari;
			if (minim > gantere[index].greutate) {
				int minim_nou;
				minim_nou = gantere[index].greutate;
				if (minim_nou * repetari > minim * (repetari - gantere[index].repetari)) {
					minim = minim_nou;
					m--;
				} else
					repetari -= gantere[index].repetari;

			} else
				m--;
			//System.out.println(gantere[index]);
			index++;
		}

		long muschi = minim * repetari;
		System.out.println(muschi);
		File output = new File("sala.out");
		FileWriter fr = new FileWriter(output);
		fr.write(Long.toString(muschi));
		fr.close();
	}

	public static class SortByReps implements Comparator<Gantere> {
	    public int dif;
		public int compare(Gantere a, Gantere b) {

			 dif = Long.compare(b.greutate * b.repetari, a.greutate * a.repetari);

			if (dif == 0)
				return b.greutate - a.greutate;
			return dif;
		}
	}
}
