package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.model.*;
import pairmatching.request.PairTypeRequest;
import pairmatching.response.CreateCrewResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairMatchingService {

    public static final String BACKEND_CREW = "/Users/chw/Desktop/project/java-pairmatching-precourse/src/main/resources/backend-crew.md";
    public static final String FRONTEND_CREW = "/Users/chw/Desktop/project/java-pairmatching-precourse/src/main/resources/frontend-crew.md";

    public CreateCrewResponse createCrews() throws IOException {
        List<Crew> backend = new ArrayList<>();
        List<Crew> frontend = new ArrayList<>();

        String[] backendCrewNames = new String(getFileString(BACKEND_CREW)).split("\n");
        String[] frontendCrewNames = new String(getFileString(FRONTEND_CREW)).split("\n");

        saveCrews(backend, backendCrewNames, Course.BACKEND);
        saveCrews(frontend, frontendCrewNames, Course.FRONTEND);

        return CreateCrewResponse.of(backend, frontend);
    }

    public List<Crew> selectCourseCrews(CreateCrewResponse createCrewResponse, PairTypeRequest request) {
        if (request.getCourse().equals(Course.BACKEND)) {
            return createCrewResponse.getBackend();
        }
        return createCrewResponse.getFrontend();
    }

    public Pair createPair(PairResult pairResult, PairTypeRequest request, List<Crew> crews) {
        List<Pair> pairResults = pairResult.getPairs();
        validateMatched(request, pairResults, crews);

        List<String> pairCrewNames = getPairCrewNames(crews);
        List<String> shuffleCrewNames = Randoms.shuffle(pairCrewNames);
        validatePairDuplicate(request, pairResults, shuffleCrewNames);

        Pair pair = Pair.createPair(request.getMissionName(), request.getLevel(), shuffleCrewNames);
        pairResults.add(pair);

        return pair;
    }

    public Pair selectPair(PairResult pairResult, PairTypeRequest request) {
        for (Pair pair : pairResult.getPairs()) {
            if (pair.getLevel().equals(request.getLevel()) && pair.getMissionName().equals(request.getMissionName())) {
                return pair;
            }
        }
        throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
    }

    public void resetPair(PairResult pairResult) {
        pairResult.resetPair();
    }

    private static void validatePairDuplicate(PairTypeRequest request, List<Pair> pairResults, List<String> shuffleCrewNames) {
        for (Pair pair : pairResults) {
            if (!pair.getLevel().equals(request.getLevel())) {
                continue;
            }
            List<String> alreadyShuffleCrewNames = pair.getShuffleCrewNames();
            // 공통된 로직: 짝수든 홀수든 항상 실행
            for (int i = 0; i < alreadyShuffleCrewNames.size() - 1; i++) {
                validatePairMatchEven(shuffleCrewNames, alreadyShuffleCrewNames, i);
            }
            // 홀수 크기의 명단에 대한 추가적인 검증
            if (alreadyShuffleCrewNames.size() % 2 != 0) {
                validatePairMatchOddNumber(shuffleCrewNames, alreadyShuffleCrewNames, alreadyShuffleCrewNames.size() - 2);
            }
        }
    }

    private static void validatePairMatchEven(List<String> shuffleCrewNames, List<String> alreadyShuffleCrewNames, int i) {
        String alreadyMatchCrewName1 = alreadyShuffleCrewNames.get(i);
        String alreadyMatchCrewName2 = alreadyShuffleCrewNames.get(i + 1);
        List<String> alreadyPairNames = Arrays.asList(alreadyMatchCrewName1, alreadyMatchCrewName2);

        String matchCrewName1 = shuffleCrewNames.get(i);
        String matchCrewName2 = shuffleCrewNames.get(i + 1);

        if (alreadyPairNames.contains(matchCrewName1) && alreadyPairNames.contains(matchCrewName2)) {
            throw new IllegalStateException("[ERROR] 중복된 페어가 발생하였습니다.");
        }
    }

    private static void validatePairMatchOddNumber(List<String> shuffleCrewNames, List<String> alreadyShuffleCrewNames, int i) {
        if (i == alreadyShuffleCrewNames.size() - 3) {
            String alreadyMatchCrewName1 = alreadyShuffleCrewNames.get(i);
            String alreadyMatchCrewName2 = alreadyShuffleCrewNames.get(i + 1);
            String alreadyMatchCrewName3 = alreadyShuffleCrewNames.get(i + 2);
            List<String> alreadyPairNames = Arrays.asList(alreadyMatchCrewName1, alreadyMatchCrewName2, alreadyMatchCrewName3);

            String matchCrewName1 = shuffleCrewNames.get(i);
            String matchCrewName2 = shuffleCrewNames.get(i + 1);
            String matchCrewName3 = shuffleCrewNames.get(i + 2);

            if (alreadyPairNames.contains(matchCrewName1) && alreadyPairNames.contains(matchCrewName2) && alreadyPairNames.contains(matchCrewName3)) {
                throw new IllegalStateException("[ERROR] 중복된 페어가 발생하였습니다.");
            }
        }
    }

    private static void validateMatched(PairTypeRequest request, List<Pair> pairResults, List<Crew> crews) {
        for (Pair pair : pairResults) {
            if (pair.getLevel().equals(request.getLevel()) && pair.getMissionName().equals(request.getMissionName())) {
                throw new IllegalArgumentException("[ERROR] 이미 매칭 되어있는 타입입니다.");
            }
        }
    }

    private static List<String> getPairCrewNames(List<Crew> crews) {
        List<String> pairCrewNames = new ArrayList<>();
        for (Crew crew : crews) {
            pairCrewNames.add(crew.getName());
        }
        return pairCrewNames;
    }

    private static void saveCrews(List<Crew> backend, String[] backendCrewNames, Course course) {
        for (String name : backendCrewNames) {
            Crew crew = Crew.createCrew(course, name);
            backend.add(crew);
        }
    }

    private static byte[] getFileString(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
