package pairmatching.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private String MissionName;
    private Level level;
    private List<String> shuffleCrewNames;

    private Pair(String missionName, Level level, List<String> shuffleCrewNames) {
        this.MissionName = missionName;
        this.level = level;
        this.shuffleCrewNames = shuffleCrewNames;
    }

    public static Pair createPair(String missionName, Level level, List<String> shuffleCrewNames) {
        return new Pair(missionName, level, shuffleCrewNames);
    }

    public String getMissionName() {
        return MissionName;
    }

    public Level getLevel() {
        return level;
    }

    public List<String> getShuffleCrewNames() {
        return shuffleCrewNames;
    }
}
