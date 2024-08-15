import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;


public class Data {
    public static double [] genData(int n)
    {
        double [] res = new double [n];

        for(int i=0; i<n; i++) {
            Random r = new Random();
            double num = (double)(r.nextInt(10000)+ 10)/100;
            //DecimalFormat df = new DecimalFormat("#0.00");
            res[i] = num;
        }

        return res;
    }

    public static void printArray (double [] data) {

        for(int i=0; i<data.length; i++) {
                System.out.print(data[i]+"\t");
            System.out.println();
        }
    }

    public static double [] readFile (String fileName){

        double [] res = null;

        try {

            FileReader fr = new FileReader (fileName);
            BufferedReader br = new BufferedReader (fr);

            int col=0, row=0;
            String line = null;

            //need to know how many rows and cols in the csv file
            while ((line = br.readLine()) != null)
            {
                row++;
                String [] column = line.split(",");
                col = column.length;

            }

            int i=0, j=0;
            res = new double [row]; //set the size of the array after we have known the row and col
            //System.out.println(col+" "+row);

            br = new BufferedReader (new FileReader (fileName)); //this has to be initiated again!

            while ((line = br.readLine()) != null)
            {
                String [] column = line.split(",");

                for (j=0; j<column.length; j++)
                {
                    res [i] = Double.parseDouble(column [j]) ;
                }

                i++;
            }

        }
        catch(Exception e) {
            System.err.print("Error");
        }


        return res;
    }

    public static void writeData(double [][] d) {

        String fn = "/Volumes/UBUNTU 20_0/Backup - HP/TSP_Data/data10.csv";

        try {

            FileWriter fw = new FileWriter(fn);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i=0; i<d.length; i++) {
                for(int j=0; j<d[i].length; j++) {

                    bw.write(Double.toString(d[i][j]));

                    if(j!=d[i].length-1) bw.write(","); //however in java, if there is no value after a comma, it will not be counted as length. The if condition might not necessary
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(Exception e) {
            System.err.print("Error");
        }
    }

    public static void writeResult(String file, ArrayList<Double> cValues, ArrayList<Double> nValues) {

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter (fw);

            // write values
            for (int i=0; i<cValues.size(); i++) {
                bw.write(Double.toString(i+1)); // write number of iterations
                bw.write(",");
                bw.write(Double.toString(cValues.get(i))); // write current fitness value
                bw.write(",");
                bw.write(Double.toString(nValues.get(i))); // write new fitness value
                bw.newLine();
            }
            bw.close();
            fw.close();

        }
        catch(Exception e) {
            System.err.print("Error");
        }


    }

    public static void main(String[] args) {

        double [] data = genData(500);
        //printArray(data);
        //writeData(data);

        printArray(data);
    }

}