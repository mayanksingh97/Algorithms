package algo;

/**
 * Created by Mayank Singh on 26-07-2015.
 */
public class Edge {
    private int v1;
    private int v2;

    public Edge (int a, int b) {
        v1 = a;
        v2 = b;
    }

    public boolean equals(Edge other) {
        return (v1 == other.v1) &&  (v2 == other.v2);
    }

    public boolean isConnetedTo(int a) {
        return (v1 == a) || (v2 == a);
    }

    public boolean isSelfLoop() {
        return v1 == v2;
    }

    public String toString () {
        return v1 + " - " + v2;
    }

    public int getVertexOne() { return v1; }
    public int getVertexTwo() { return v2; }

    public void changeVetex(int vertex, int toChange) {
        if(v1 == vertex)
            v1 = toChange;
        if(v2 == vertex)
            v2 = toChange;

        if(v1 > v2) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
    }
}
