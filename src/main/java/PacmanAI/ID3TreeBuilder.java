package main.java.PacmanAI;


import main.java.PacmanAI.interfaces.DecisionTree;
import main.java.PacmanAI.interfaces.DecisionTreeBuilder;

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
        informationNeeded = info(dataset);
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
        //TODO loop over attribute values
            // res += numOfValue/TotalRows * info(partion value)
        return 0;
    }

    /**
     * Calculates the entropy for the whole dataPartition.
     * Returns the information needed to classify a tuple in the partition.
     * @return double
     */
    private double info(Partition partition) {
        //TODO loop over class and sum -(p * log p)
        return 0;
    }

    private Partition makePartion(Partition partition, String attribute, String value) {
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
