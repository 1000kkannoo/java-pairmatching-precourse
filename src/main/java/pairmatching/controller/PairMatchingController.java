package pairmatching.controller;

import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.model.PairResult;
import pairmatching.request.PairTypeRequest;
import pairmatching.response.CreateCrewResponse;
import pairmatching.service.PairMatchingService;

import java.io.IOException;
import java.util.List;

public class PairMatchingController {

    private final PairMatchingService pairMatchingService;

    public PairMatchingController(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    public PairResult createPairResult() {
        return new PairResult();
    }

    public CreateCrewResponse createCrews() {
        return pairMatchingService.createCrews();
    }

    public List<Crew> selectCourseCrews(CreateCrewResponse response, PairTypeRequest request) {
        return pairMatchingService.selectCourseCrews(response, request);
    }

    public Pair createPair(PairResult pairResult, PairTypeRequest pairTypeRequest, List<Crew> crews, Boolean isUpdate) {
        return pairMatchingService.createPair(pairResult, pairTypeRequest, crews, isUpdate);
    }

    public Pair selectPair(PairResult pairResult, PairTypeRequest pairTypeRequest) {
        return pairMatchingService.selectPair(pairResult, pairTypeRequest);
    }

    public void resetPair(PairResult pairResult) {
        pairMatchingService.resetPair(pairResult);
    }
}
