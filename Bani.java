import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Bancnote{
    public long banc10, banc50, banc100, banc200, banc500;

    public Bancnote(long banc10, long banc50, long banc100, long banc200, long banc500) {
        this.banc10 = banc10;
        this.banc50 = banc50;
        this.banc100 = banc100;
        this.banc200 = banc200;
        this.banc500 = banc500;
    }
    public void updateBancnote(long banc10, long banc50, long banc100, long banc200, long banc500) {
        this.banc10 = banc10;
        this.banc50 = banc50;
        this.banc100 = banc100;
        this.banc200 = banc200;
        this.banc500 = banc500;
    }
}

public class Bani {
    public static void main(String[] args) throws IOException {
        File myObj = new File("bani.in");
        Scanner sc = new Scanner(myObj);
        int type = sc.nextInt();
        long n = sc.nextLong();
        long rez = 5;

        if (type == 1)
        {
            if(n > 1){
                rez *= powy(2,n-1, 1000000007);
                rez %= 1000000007;
            }
        }
        else
        {
            Bancnote date = new Bancnote(1,1,1,1,1);
            if(n > 1){
                for (int i = 1; i < n; i++)
                {
                    long banc10, banc50, banc100, banc200, banc500;
                    banc10 = suma(date.banc50,date.banc100,date.banc500,1000000007);
                    banc50 = suma(date.banc10, date.banc200, 0 , 1000000007);
                    banc100 = suma(date.banc10, date.banc200, date.banc100, 1000000007);
                    banc200 = suma(date.banc50, date.banc500, 0, 1000000007);
                    banc500 = date.banc200;
                    date.updateBancnote(banc10,banc50,banc100,banc200,banc500);
                }
                rez = computeNewRez(date);
            }
        }
        File output = new File("bani.out");
        FileWriter fr = new FileWriter(output);
        fr.write(Long.toString(rez));
        fr.close();
        sc.close();
        //System.out.println(rez);
    }
    public static long suma(long a, long b, long c,long MOD)
    {
        return ((a % MOD) + (b % MOD) + (c % MOD)) % MOD;
    }

    public static long computeNewRez(Bancnote date)
    {
        long rez = date.banc500;
        rez = suma(rez,date.banc10,date.banc50,1000000007);
        rez = suma(rez,date.banc100,date.banc200,1000000007);
        return rez;
    }


    public static long powy(long base,long exp,long mod)
    {
        if(exp == 1)
            return base;
        else
        if(exp % 2 == 1)
        {
            return (powy(base,exp/2,mod)% mod * powy(base,exp/2,mod)% mod * base% mod) % mod;
        }
        else
            return (powy(base,exp/2,mod)% mod * powy(base,exp/2,mod)% mod) % mod;


    }
}
