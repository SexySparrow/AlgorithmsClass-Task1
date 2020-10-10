import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Pair{
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}



public class Gard {

    public static class SortByX implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            int dif = a.x - b.x;
            if(dif != 0)
                return a.x - b.x;
            return b.y - a.y;
        }
    }


    public static void main(String[] args) throws IOException {
        File myObj = new File("gard.in");
        Scanner sc = new Scanner(myObj);
        int n = sc.nextInt();
        Pair[] garduri = new Pair[n];
        int x,y;

        for (int i = 0; i < n; i++)
        {
            x = sc.nextInt();
            y = sc.nextInt();
            garduri[i] = new Pair(x,y);
        }
        Arrays.sort(garduri,new SortByX());
        int nr = 0;
        int ref = 0;
        for (int i = 1; i < n; i++)
        {
            if(garduri[ref].y >= garduri[i].y){
                nr++;
            }
            else
                ref = i;
        }

        //System.out.println(nr);
        File output = new File("gard.out");
        FileWriter fr = new FileWriter(output);
        fr.write(Integer.toString(nr));
        fr.close();
        sc.close();
    }
}
