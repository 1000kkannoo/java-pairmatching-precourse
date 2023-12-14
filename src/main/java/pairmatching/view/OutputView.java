package pairmatching.view;

import pairmatching.model.Pair;

import java.util.List;

public class OutputView {

    public void printPair(Pair pair) {
        List<String> shuffleCrewNames = pair.getShuffleCrewNames();
        int size = shuffleCrewNames.size();

        // 마지막 3명을 제외하고 2명씩 출력
        for (int i = 0; i < size - (size % 2 == 0 ? 0 : 3); i += 2) {
            System.out.println(shuffleCrewNames.get(i) + " : " + shuffleCrewNames.get(i + 1));
        }

        // 크기가 홀수인 경우, 마지막 3명을 출력
        if (size % 2 != 0) {
            System.out.println(shuffleCrewNames.get(size - 3) + " : " + shuffleCrewNames.get(size - 2) + " : " + shuffleCrewNames.get(size - 1));
        }
    }
}
