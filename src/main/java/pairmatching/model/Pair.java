package pairmatching.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private Course course;
    private String MissionName;
    private Level level;
    private List<String> shuffleCrewNames;

    private Pair(Course course, String missionName, Level level, List<String> shuffleCrewNames) {
        this.course = course;
        this.MissionName = missionName;
        this.level = level;
        this.shuffleCrewNames = shuffleCrewNames;
    }

    public static Pair createPair(Course course, String missionName, Level level, List<String> shuffleCrewNames) {
        return new Pair(course, missionName, level, shuffleCrewNames);
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
