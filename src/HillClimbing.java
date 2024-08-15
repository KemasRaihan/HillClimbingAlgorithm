import java.util.ArrayList;
import java.util.Random;

public class HillClimbing {

    static double [] dataset;
    static ArrayList<Double> currentFitnessValues;
    static ArrayList<Double> newFitnessValues;
    static double finalFitness = 0;


    public static ArrayList<Integer> genSolution(int n){
        ArrayList<Integer> solution = new ArrayList<>();

        Random r = new Random();

        // assign random boolean values to array list state
        while(solution.size()<n) {
            solution.add(r.nextInt(2));
        }
        return solution;
    }

    public static double calcFitness(ArrayList<Integer> solution){
        double fitness;
        int size = solution.size();
        // initialise total weights in lorry A
        double totalA = 0;
        // initialise total weights in lorry B
        double totalB = 0;

        for(int i=0; i<size-1; i++) {
            if(solution.get(i)==1){
                totalA+=dataset[i];
            }else{
                totalB+=dataset[i];
            }
        }

        fitness = Math.abs(totalA-totalB);

        return fitness;

    }

    // copy the values of a solution to a new one
    void copySolution(ArrayList<Integer> current, ArrayList<Integer> newSolution)
    {
        for(int i =0; i < current.size(); i++)
        {
            newSolution.add(current.get(i));
        }
    }



    //flip value in the solution array
    void flip(ArrayList<Integer> solution, int index)
    {
        // if value is 0 then flip it to 1 and vice versa
        if(solution.get(index)==0)
        {
            solution.set(index, 1);
        }
        else
        {
            solution.set(index, 0);
        }
    }



    void printSolution(ArrayList<Integer> solution)
    {
        System.out.print("[");
        for(int i = 0; i < solution.size(); i++)
        {
            System.out.print(solution.get(i) + ", ");
        }
        System.out.print("]");
    }




    public ArrayList<Integer> smallChange(ArrayList<Integer> solution, double rate){
        Random r = new Random();

        // count the number of values being flip
        int count = 0;

        // initialise index of value in the array to be flipped
        int index = 0;

        // length of array
        int len = solution.size();

        // number of values to change
        int n = (int)(rate * len);

        while(count < n)
        {
            // 50% chance of the value being flip
            if(r.nextInt(2) == 1)
            {
                // flip values
                flip(solution, index);

                // update count if value has been flipped
                count++;
            }

            //update index
            index = (index + 1) % len;

        }

        return solution;
    }




    public void runHC(ArrayList<Integer> initial, int iterations, int n, double rate){

        currentFitnessValues = new ArrayList<>();
        newFitnessValues = new ArrayList<>();

        // initialise current solution
        ArrayList<Integer> current = new ArrayList<>();

        // copy initial solution to current
        copySolution(initial, current);

        double currentFitness = 0;

        for(int i = 0; i < iterations; i++)
        {
            // get new solution
            ArrayList<Integer> newSolution = new ArrayList<>();
            copySolution(current, newSolution);

            newSolution = smallChange(newSolution, rate);

            currentFitness = calcFitness(current);

            double newFitness = calcFitness(newSolution);

//            System.out.print("Current solution: ");
//            printSolution(current);
//            System.out.print("\tFitness value: " + currentFitness);
//            System.out.println();
//
//            System.out.print("New solution: ");
//            printSolution(newSolution);
//            System.out.print("\tFitness value: " + newFitness);
//            System.out.println();

            currentFitnessValues.add(currentFitness);

            // if new solution has a fitness value greater than the current one, then replace it
            if(newFitness < currentFitness)
                current = newSolution;

            newFitnessValues.add(newFitness);

        }
        Data.writeResult(Double.toString(rate) +".csv", currentFitnessValues, newFitnessValues);
        finalFitness = currentFitness;
        System.out.println("Rate: " + rate + ", Final fitness value: " + finalFitness);
    }

    public static void main(String[] args) {
        HillClimbing hc = new HillClimbing();

        int iterations = 1000;
        int n = 100;

        // generate dataset
        dataset = Data.genData(n);

        // initialise first solution
        ArrayList<Integer> initial = genSolution(n);

        System.out.println("Initial fitness: " + calcFitness(initial));

        // get different rates
        double[] rates = new double[]{0.05, 0.1, 0.2, 0.5};

        for(int i = 0; i < rates.length; i++)
            hc.runHC(initial, iterations, n, rates[i]);
    }


}
