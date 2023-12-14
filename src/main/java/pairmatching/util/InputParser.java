package pairmatching.util;

import pairmatching.model.Course;
import pairmatching.model.Level;
import pairmatching.request.PairTypeRequest;

import java.util.Arrays;
import java.util.List;

import static pairmatching.model.Level.*;

public class InputParser {

    public static List<String> LEVEL1_MISSION_NAME = Arrays.asList("자동차경주", "로또", "숫자야구게임");
    public static List<String> LEVEL2_MISSION_NAME = Arrays.asList("장바구니", "결제", "지하철노선도");
    public static List<String> LEVEL4_MISSION_NAME = Arrays.asList("성능개선", "배포");

    public static PairTypeRequest pairTypeParser(String input) {
        String[] split = input.split(", ");
        Course course = Course.findByCourse(split[0]);
        Level level = findByLevel(split[1]);
        String missionName = split[2];

        if (level.equals(LEVEL1) && !LEVEL1_MISSION_NAME.contains(missionName)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
        } else if (level.equals(LEVEL2) && !LEVEL2_MISSION_NAME.contains(missionName)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
        } else if (level.equals(LEVEL4) && !LEVEL4_MISSION_NAME.contains(missionName)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
        }
        return PairTypeRequest.of(course, level, missionName);
    }
}
