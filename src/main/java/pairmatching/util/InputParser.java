package pairmatching.util;

import pairmatching.model.Course;
import pairmatching.model.Level;
import pairmatching.request.PairTypeRequest;

public class InputParser {

    public static PairTypeRequest pairTypeParser(String input) {
        String[] split = input.split(", ");
        Course course = Course.findByCourse(split[0]);
        Level level = Level.findByLevel(split[1]);
        String missionName = split[2];
        return PairTypeRequest.of(course, level, missionName);
    }
}
