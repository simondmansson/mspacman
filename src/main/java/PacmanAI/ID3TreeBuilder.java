package PacmanAI;

import PacmanAI.interfaces.DecisionTree;
import PacmanAI.interfaces.DecisionTreeBuilder;
import dataRecording.DataTuple;
import java.util.HashMap;
import java.util.LinkedList;

public class ID3TreeBuilder implements DecisionTreeBuilder {

    private Partition dataset;
    private HashMap<String, LinkedList<String>> attributes;
    private LinkedList<String> classes;
    private double informationNeeded;


    public double getInformationNeeded() {
        return informationNeeded;
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
        TreeNode node = new TreeNode();
        return node;
    }

    private String selectSplit(Partition partition, HashMap<String, LinkedList<String>> attributes) {
        String toSplitOn = "";
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


    /*
    private void printTree(TreeNode current) {
        current.getChildren().forEach {
            TreeNode child ->        System.out.printf(" d %S", 2*increment, current.label)
        }
    }
*/
}
