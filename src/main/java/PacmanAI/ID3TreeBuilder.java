package PacmanAI;

import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import PacmanAI.interfaces.DecisionTree;
import PacmanAI.interfaces.DecisionTreeBuilder;
import dataRecording.DataTuple;
import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ID3TreeBuilder implements DecisionTreeBuilder {

    private Partition dataset;
    private HashMap<String, LinkedList<String>> attributes;
    private LinkedList<String> classes;
    private double informationNeeded;


    public double getInformationNeeded() {
        return informationNeeded;
    }

    public ID3TreeBuilder() {
        Parser parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
        LinkedList<DataTuple> tuples = parser.parseTrainingData("src/main/resources/trainingData.txt");
        assert(tuples.size() > 0);
        dataset = new Partition(tuples);
        attributes = parser.parse("src/main/resources/survivormanattributes3.txt");
        assert(attributes.size() > 0);
        classes = new LinkedList<>();
        classes.add("LEFT");
        classes.add("UP");
        classes.add("RIGHT");
        classes.add("DOWN");
        classes.add("NEUTRAL");
        informationNeeded = info(dataset); // MOVE as second parameter
    }

    /**
     * Constructor
     * @param dataset The training partition of the data set
     * @param attributes the attributelist and values for each attribute
     */
    public ID3TreeBuilder(Partition dataset, HashMap<String, LinkedList<String>> attributes)
    {
        this.dataset = dataset;
        this.attributes = attributes;
        classes = new LinkedList<>();
        classes.add("LEFT");
        classes.add("UP");
        classes.add("RIGHT");
        classes.add("DOWN");
        classes.add("NEUTRAL");
        informationNeeded = info(dataset); // MOVE as second parameter
    }

    /**
     * Returns a decisionTree built with the ID3 algorithm
     */
    @Override
    public DecisionTree buildTree() {
        return new ID3Tree(build(dataset, attributes));
    }

    /**
     * Recursivly builds the decision tree with the ID3 algorithm
     * @param partition
     * @param attributes
     * @return TreeNode complete decision tree
     */
    private TreeNode build(Partition partition, HashMap<String, LinkedList<String>> attributes) {
        if(partitionIsPure(partition)) {
            return new TreeNode(ValueOf(partition));
        }
        if (attributes.isEmpty()) {
            return new TreeNode(majorityVote(partition));
        }
        String split = selectSplit(partition, attributes);
        LinkedList<String> values = attributes.get(split);
        HashMap<String, LinkedList<String>> copy = new HashMap<>(attributes);
        copy.remove(split);
        TreeNode node = new TreeNode(split);
        values.forEach(value-> {
            Partition subset = makePartition(partition, split, value);
            if(subset.getTuples().isEmpty()) {
                TreeNode child = new TreeNode(majorityVote(partition));
                node.addEdge(value, child);
            } else {
                TreeNode child = build(subset, copy);
                node.addEdge(value, child);
            }
        });

        return node;
    }

    private String majorityVote(Partition partition) {
        HashMap<Enum, Integer> tuples = new HashMap<>();

        for(DataTuple tuple : partition.getTuples()) {
            if(!tuples.containsKey(tuple.DirectionChosen)) {
                tuples.put(tuple.DirectionChosen, 1);
            } else {
                tuples.put(tuple.DirectionChosen, tuples.get(tuple.DirectionChosen) + 1);
            }
        }

        int maxValue = 0;
        Enum bestChoice = null;
        for (Map.Entry<Enum, Integer> entry : tuples.entrySet()) {
            Enum key = entry.getKey();
            int value = entry.getValue();

            if(value > maxValue) {
                bestChoice = key;
                maxValue = value;
            }
        }

        return "" + bestChoice;
    }

    private String ValueOf(Partition partition) {
        return "" + partition.getTuples().get(0).DirectionChosen;
    }

    private boolean partitionIsPure(Partition partition) {

        Enum move = partition.getTuples().get(0).DirectionChosen;
        int equalities = 0;

        for(DataTuple tuple : partition.getTuples()) {
            if(move == tuple.DirectionChosen) {
                equalities++;
            } else {
                return false;
            }
        }

        return equalities == partition.getTuples().size();
    }

    private String selectSplit(Partition partition, HashMap<String, LinkedList<String>> attributes) {
        String toSplitOn = "";
        double bestGain = Integer.MIN_VALUE;
        for(String key : attributes.keySet()) {
            double gain = gain(partition, key);
            if(gain > bestGain) {
                toSplitOn = key;
                bestGain = gain;
            }
        }
        return toSplitOn;
    }

    /**
     * Calculates the information gained by splitting on the given attribute.
     * @param attribute
     * @return
     */
    public double gain(Partition partition, String attribute) {
        return informationNeeded - infoAttribute(partition, attribute);
    }

    /**
     * Calculates the entropy for the attribute dataPartition.
     * Returns the information needed to classify a tuple in with the given attribute.
     * @param partition Subset of the DataSet on the given attribute
     * @param attribute Name of Attribute
     * @return double
     */
    public double infoAttribute(Partition partition, String attribute) {
        LinkedList<DataTuple> tuples = partition.getTuples();
        LinkedList<String> values = attributes.get(attribute);
        String[] attributeValues = new String[values.size()];

        for(int i = 0; i < values.size(); i++) {
            attributeValues[i] = values.get(i);
        }

        double[] attributeFractions = new double[attributeValues.length];

        int amount = 0;
        for(DataTuple tuple : tuples) {
            String value = tuple.getAttribute(attribute);
            for(int i = 0; i < attributeValues.length; i++) {
                if(attributeValues[i].equals(value)) {
                    attributeFractions[i]++;
                    amount++;
                }
            }
        }

        double result = 0;
        for(int i = 0; i < attributeFractions.length; i++) {
            attributeFractions[i] = attributeFractions[i] / amount;
            Partition p = makePartition(partition, attribute, values.get(i));
            double infoP = info(p);
            result += attributeFractions[i] * infoP;
        }

        return result;
    }

    /**
     * Calculates the entropy for the whole dataPartition.
     * Returns the information needed to classify a tuple in the partition.
     * @return double
     */
    private double info(Partition partition) {

        LinkedList<DataTuple> tuples = partition.getTuples();
        if(tuples.size() == 0) {
            return 0;
        }
        String[] attributeValues = new String[classes.size()];

        for(int i = 0; i < classes.size(); i++) {
            attributeValues[i] = classes.get(i);
        }

        double[] attributeFractions = new double[attributeValues.length];

        for(DataTuple tuple : tuples) {
            String value = tuple.getAttribute("DirectionChosen");
            for(int i = 0; i < attributeValues.length; i++) {
                if(attributeValues[i].equals(value)) {
                    attributeFractions[i]++;
                }
            }
        }

        for(int i = 0; i < attributeFractions.length; i++) {
            attributeFractions[i] = attributeFractions[i] / tuples.size();
        }

        double result = 0;
        for(int i = 0; i < attributeFractions.length; i++) {
            double fraction = attributeFractions[i];
            if(fraction != 0) {
                result += (fraction * (Math.log(fraction)/Math.log(2)+1e-10)) * -1;
            }
        }

        return result;
    }

    /**
     * Creates a new partition from the specified partition based on which attribute and value you would like to create
     * the new partition from.
     * @param partition The partition you would like to create the new partition from.
     * @param attribute The attribute you would like the new partition to have.
     * @param value The value of the attribute that you would like the new partition to be split on.
     * @return Returns the new partition with the values specified in the method's parameters.
     */
    private Partition makePartition(Partition partition, String attribute, String value) {
        return partition.createNewPartitionOn(attribute, value);
    }

}
