package algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayank Singh on 26-07-2015.
 */

public class AdjacentVertices {
    List<Integer> adjacent;

    public AdjacentVertices() {
        adjacent = new ArrayList<>(); {
        }
    }
    public void add(int i) {
        if(!adjacent.contains((Integer) i))
            adjacent.add((Integer) i);
    }

    public void remove(int i) {
        if(adjacent.contains((Integer) i))
            adjacent.remove((Integer) i);
    }

    public String toString() {
        return adjacent.toString();
    }
}