package pairmatching.request;

import pairmatching.model.Course;
import pairmatching.model.Level;

public class PairTypeRequest {
    private final Course course;
    private final Level level;
    private final String missionName;

    private PairTypeRequest(Course course, Level level, String missionName) {
        this.course = course;
        this.level = level;
        this.missionName = missionName;
    }

    public static PairTypeRequest of(Course course, Level level, String missionName) {
        return new PairTypeRequest(course, level, missionName);
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMissionName() {
        return missionName;
    }
}
