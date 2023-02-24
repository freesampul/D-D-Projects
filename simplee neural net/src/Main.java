import java.util.Arrays;
import java.util.Comparator;

class Main {
    public static void main(String[] args){
        // Let's train a NN that can add two numbers!
        // You can make up your own simple algorithm and have the computer guess - just add a few data points below and
        // then train, and then add test cases to see if it found the algorithm!
        double[][] inputs = {
                {1, 2},
                {2, 4},
                {5, 2},
                {10, 2},
                {5, 5},
                {100, -50}
        };
        double[][] solutions = {
                {3}, {6}, {7}, {12}, {10}, {50}
        };
        NeuralNetwork nn = trainByEvolution(new int[]{inputs[0].length, 10, 10, solutions[0].length}, -2, 2, 0.001, 0.02, inputs, solutions);
//        NeuralNetwork nn = trainByHillClimber(new int[]{inputs[0].length, 10, 10, solutions[0].length}, -2, 2, 0.001, 0.02, inputs, solutions);

        // Let's run a calculation that *wasn't* included in our training set to make sure the NN actually found our
        // solution, and wasn't just overfitting to our training dataset.
        System.out.println(nn.runCalculation(new double[]{9, 10})[0]); // --> should output 19
        System.out.println(nn.runCalculation(new double[]{4, 5})[0]); // --> should output 9
        System.out.println(nn.runCalculation(new double[]{10, -5})[0]); // --> should output 5
    }

    public static double calculateError(NeuralNetwork nn, double[][] inputs, double[][] solutions){
        double error = 0;
        for(int i = 0; i < inputs.length; i++) {
            double[] prediction = nn.runCalculation(inputs[i]);
            for(int j = 0; j < prediction.length; j++){
                error += Math.abs(solutions[i][j] - prediction[j]);
            }
        }
        return error;
    }


    public static NeuralNetwork trainByHillClimber(int[] neuronCounts, double initialRandMin, double initialRandMax, double mutationAmt, double successThreshold, double[][] inputs, double[][] solutions){
        NeuralNetwork network = new NeuralNetwork(neuronCounts, initialRandMin, initialRandMax);

        double error = calculateError(network, inputs, solutions);
        while(error > successThreshold) {
            NeuralNetwork copy = network.deepCopy();

            copy.mutate(mutationAmt * error);
            double newError = calculateError(copy, inputs, solutions);
            if(newError < error) {
                error = newError;
                network = copy;
            }
            System.out.println(error);
        }
        return network;
    }

    public static NeuralNetwork trainByEvolution(int[] neuronCounts, double initialRandMin, double initialRandMax, double mutationAmt, double successThreshold, double[][] inputs, double[][] solutions){

        // Evolution (without breeding): create a population of 100 models. The bottom 20% die; the middle 60% survive (and get mutated);

        NeuralNetwork net[] = new NeuralNetwork[100];
        for(int i = 0; i < net.length; i++)
        {
            net[i] = new NeuralNetwork(neuronCounts, initialRandMin, initialRandMax);
        }

        // Loop through each network, setting its network.error variable to the calculated error

        Arrays.sort(net, new Comparator<NeuralNetwork>() {
            public int compare(NeuralNetwork first, NeuralNetwork second) {
                // access network.error and sort by it
            return first.error < second.error ? 1 : -1;
    }});




        NeuralNetwork[] nets = new NeuralNetwork[100];
        for(int i = 0; i < nets.length; i++){
            nets[i] = new NeuralNetwork(neuronCounts, initialRandMin, initialRandMax);
        }
        while(true){
            // Calculate error of all the models and store it as an instance variable
            for(NeuralNetwork nn : nets){
                nn.error = calculateError(nn, inputs, solutions);
            }

            // The code below sorts the models by their error.
            Arrays.sort(nets, new Comparator<NeuralNetwork>() {
                @Override
                public int compare(NeuralNetwork o1, NeuralNetwork o2) {
                    return o1.error < o2.error ? 1 : -1;
                }
            });

            System.out.println("Best: " + nets[99].error + "\t\tMedian: " + nets[50].error + "\t\tWorst: " + nets[0].error);
            if(nets[99].error < successThreshold) return nets[99];

            // replace bottom 20% with clones of top 20%
            for(int i = 0; i < 20; i++){
                nets[i] = nets[80 + i].deepCopy();
            }

            //Fuck I actually want to write script but i neeed to LOOK productive versus atually just writing rando mshit? Project i can work on, math based drawing



            // Mutate bottom 80%
            for(int i = 0; i < 80; i++){
                // Mutate a certain amount depending on how far from solution we are
                nets[i].mutate(mutationAmt * nets[i].error);
            }
        }

    }
}
