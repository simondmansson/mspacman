package PacmanAI;

import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import PacmanAI.interfaces.DecisionTree;
import com.sun.deploy.panel.TreeBuilder;
import dataRecording.DataTuple;

import java.util.HashMap;
import java.util.LinkedList;

public class Trainer {
    private DecisionTree tree;
    private ID3TreeBuilder builder;
    private LinkedList<DataTuple> tuples;
    private LinkedList<DataTuple> trainingdata;
    LinkedList<DataTuple> testdata;
    private HashMap<String, LinkedList<String>> attributelist;
    private Parser parser;
    public Trainer() {
        parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
    }

    public double computeAccuracy(String data, String attributes) {
        readData(data, attributes);
        splitData();
        buildTree();
        double hits = 0;
        for (DataTuple tuple : testdata)
            if(tuple.DirectionChosen == tree.makeDecision(tuple.getSaveString()))
                hits++;

        return hits/trainingdata.size();
    }

    private void readData(String data, String attributes) {
        tuples = parser.parseTrainingData(data);
        attributelist = parser.parse(attributes);
    }
    private void splitData() {
       trainingdata = new LinkedList<>(tuples.subList(0, tuples.size()/2));
       testdata = new LinkedList<>(tuples.subList(tuples.size()/2+1, tuples.size()));
    }

    private void buildTree() {
        Partition trainingPartition = new Partition(trainingdata);
        builder = new ID3TreeBuilder(trainingPartition, attributelist);
        tree = builder.buildTree();
    }
}
