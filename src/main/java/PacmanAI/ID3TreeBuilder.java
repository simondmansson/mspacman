package PacmanAI;

import PacmanAI.interfaces.DecisionTree;
import PacmanAI.interfaces.DecisionTreeBuilder;
import dataRecording.DataTuple;
import java.util.HashMap;
import java.util.LinkedList;

public class ID3TreeBuilder implements DecisionTreeBuilder {

    private Partition dataset;
    private HashMap<String, LinkedList<String>> attributes;
    private double informationNeeded;

    /**
     * Constructor
     * @param dataset The training partition of the data set
     * @param attributes the attributelist and values for each attribute
     */
    public ID3TreeBuilder(Partition dataset, HashMap<String, LinkedList<String>> attributes)
    {
        this.dataset = dataset;
        this.attributes = attributes;
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
    private double gain(Partition partition, String attribute) {
        return informationNeeded - infoAttribute(partition, attribute);
    }

    /**
     * Calculates the entropy for the attribute dataPartition.
     * Returns the information needed to classify a tuple in with the given attribute.
     * @param partition Subset of the DataSet on the given attribute
     * @param attribute Name of Attribute
     * @return double
     */
    private double infoAttribute(Partition partition, String attribute) {
        double[] attributeFractions = getAttributeFractions(partition, attribute);
        LinkedList<String> values = attributes.get(attribute);

        double result = 0;
        for(int i = 0; i < values.size(); i++) {
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
        double[] attributeFractions = getAttributeFractions(partition, "DirectionChosen");

        double result = 0;
        for(int i = 0; i < attributeFractions.length; i++) {
            double fraction = attributeFractions[i];
            result += (fraction * (int)(Math.log(fraction)/Math.log(2)+1e-10)) * -1;
        }

        return result;
    }

    private double[] getAttributeFractions(Partition partition, String attribute) {
        LinkedList<DataTuple> tuples = partition.getTuples();
        LinkedList<String> values = attributes.get(attribute);
        String[] attributeValues = new String[values.size()];

        for(int i = 0; i < values.size(); i++) {
            attributeValues[i] = values.get(i);
        }

        double[] attributeFractions = new double[attributeValues.length];

        for(DataTuple tuple : tuples) {
            String value = tuple.getAttribute(attribute);
            for(int i = 0; i < attributeValues.length; i++) {
                if(attributeValues[i].equals(value)) {
                    attributeFractions[i]++;
                }
            }
        }

        for(int i = 0; i < attributeFractions.length; i++) {
            attributeFractions[i] = attributeFractions[i] / tuples.size();
        }

        return attributeFractions;
    }

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
