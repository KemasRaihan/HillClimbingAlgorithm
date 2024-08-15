

public class CompareFitness implements java.util.Comparator {

    HillClimbing ga = new HillClimbing();

    public int compare(Object a, Object b)
    {
//        if (((HillClimbing.totalWeights).fitness < ((GeneticAlgo.Individual)b).fitness) return(-1);
//        if (((GeneticAlgo.Individual)a).fitness > ((GeneticAlgo.Individual)b).fitness) return(1);
        return(0);
    }

}