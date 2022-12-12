package datastructures.graph;

/**
 * This class models an undirected Edge in the Graph implementation.
 * An Edge contains two vertices and a weight. If no weight is
 * specified, the default is a weight of 1. This is so traversing
 * edges is assumed to be of greater distance or cost than staying
 * at the given vertex.
 *
 * This class also deviates from the expectations of the Comparable interface
 * in that a return value of 0 does not indicate that this.equals(other). The
 * equals() method only compares the vertices, while the compareTo() method
 * compares the edge weights. This provides more efficient implementation for
 * checking uniqueness of edges, as well as the fact that two edges of equal weight
 * should be considered equitably in a pathfinding or spanning tree algorithm.
 *
 * @author Michael Levet
 * @date June 09, 2015
 */
public class Edge implements Comparable<Edge> {

    private Vertex one, two;
    private int weight;

    /**
     *
     * @param one The first vertex in the Edge
     * @param two The second vertex in the Edge
     */
    public Edge(Vertex one, Vertex two){
        this(one, two, 1);
    }

    /**
     *
     * @param one The first vertex in the Edge
     * @param two The second vertex of the Edge
     * @param weight The weight of this Edge
     */
    public Edge(Vertex one, Vertex two, int weight){
        this.one = (one.getLabel().compareTo(two.getLabel()) <= 0) ? one : two;
        this.two = (this.one == one) ? two : one;
        this.weight = weight;
    }


    /**
     *
     * @param current
     * @return The neighbor of current along this Edge
     */
    public Vertex getNeighbor(Vertex current){
        if(!(current.equals(one) || current.equals(two))){
            return null;
        }

        return (current.equals(one)) ? two : one;
    }

    /**
     *
     * @return Vertex this.one
     */
    public Vertex getOne(){
        return this.one;
    }

    /**
     *
     * @return Vertex this.two
     */
    public Vertex getTwo(){
        return this.two;
    }


    /**
     *
     * @return int The weight of this Edge
     */
    public int getWeight(){
        return this.weight;
    }


    /**
     *
     * @param weight The new weight of this Edge
     */
    public void setWeight(int weight){
        this.weight = weight;
    }


    /**
     * Note that the compareTo() method deviates from
     * the specifications in the Comparable interface. A
     * return value of 0 does not indicate that this.equals(other).
     * The equals() method checks the Vertex endpoints, while the
     * compareTo() is used to compare Edge weights
     *
     * @param other The Edge to compare against this
     * @return int this.weight - other.weight
     */
    public int compareTo(Edge other){
        return this.weight - other.weight;
    }

    /**
     *
     * @return String A String representation of this Edge
     */
    public String toString(){
        return "({" + one + ", " + two + "}, " + weight + ")";
    }

    /**
     *
     * @return int The hash code for this Edge
     */
    public int hashCode(){
        return (one.getLabel() + two.getLabel()).hashCode();
    }

    /**
     *
     * @param other The Object to compare against this
     * @return ture iff other is an Edge with the same Vertices as this
     */
    public boolean equals(Object other){
        if(!(other instanceof Edge)){
            return false;
        }

        Edge e = (Edge)other;

        return e.one.equals(this.one) && e.two.equals(this.two);
    }
}
