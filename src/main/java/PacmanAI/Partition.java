package PacmanAI;

import dataRecording.DataTuple;

import java.util.LinkedList;

/**
 * @author Kalk88 & Kemaldev
 *
 * Container class for a filtered set of Data tuples.
 *
 *
*/
public class Partition {
    private LinkedList<DataTuple> tuples;

    public Partition(LinkedList<DataTuple> subpartion) {
        this.tuples = subpartion;
    }
  
    public LinkedList<DataTuple> getTuples() {
        return tuples;
    }

    /**
     * Creates a new Partition on the given attribute and value.
     * @param attribute to look at
     * @param value to split on
     * @return a new Partition
     */
    public Partition createNewPartitionOn(String attribute, String value) {
        LinkedList<DataTuple> list = new LinkedList<>();
        tuples.forEach( tuple -> {
                if(value.equals(tuple.getAttribute(attribute)))
                        list.add(tuple);
                });
        return new Partition(list);
    }
}
