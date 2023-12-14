package pairmatching.model;

import java.util.ArrayList;
import java.util.List;

public class PairResult {
    private List<Pair> pairs = new ArrayList<>();

    public void addPairResult(Pair pair) {
        pairs.add(pair);
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
