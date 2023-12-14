package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.model.PairResult;
import pairmatching.request.PairTypeRequest;
import pairmatching.response.CreateCrewResponse;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final PairMatchingController pairMatchingController = new PairMatchingController(new PairMatchingService());

    public static void main(String[] args) {
        PairResult pairResult = pairMatchingController.createPairResult();
        CreateCrewResponse createCrewResponse = pairMatchingController.createCrews();

        while (true) {
            String select = inputView.selectInput();
            if (select.equals("1")) {
                createPairProcess(pairResult, createCrewResponse);
            } else if (select.equals("2")) {
                selectPairProcess(pairResult);
            } else if (select.equals("3")) {
                resetPairProcess(pairResult);
            } else if (select.equals("Q")) {
                break;
            }
        }
    }

    // 페어 매칭 프로세스
    private static void createPairProcess(PairResult pairResult, CreateCrewResponse createCrewResponse) {
        PairTypeRequest pairTypeRequest = inputView.selectPairTypeInput();
        List<Crew> crews = pairMatchingController.selectCourseCrews(createCrewResponse, pairTypeRequest);
        matchingProcess(pairResult, pairTypeRequest, crews, false);
    }

    private static void matchingProcess(PairResult pairResult, PairTypeRequest pairTypeRequest, List<Crew> crews, boolean isUpdate) {
        int error = 0;
        while (true) {
            if (error >= 3) {
                System.out.println("[ERROR] 3회 이상 매칭에 실패하였습니다.");
                return;
            }
            error = tryMatching(pairResult, pairTypeRequest, crews, isUpdate, error);
            if (error == 0) {
                return;
            }
        }
    }

    private static int tryMatching(PairResult pairResult, PairTypeRequest pairTypeRequest, List<Crew> crews, boolean isUpdate, int error) {
        try {
            Pair pair = pairMatchingController.createPair(pairResult, pairTypeRequest, crews, isUpdate);
            outputView.printPair(pair);
        } catch (IllegalArgumentException e) {
            error = handleIllegalArgumentException(error);
        } catch (IllegalStateException e) {
            error++;
        }
        return error;
    }

    private static int handleIllegalArgumentException(int error) {
        String input = inputView.selectReMatchingInput();
        if (input.equals("아니오")) {
            error = 3; // 바로 루프 종료를 위해 에러 카운트 증가
        }
        return error;
    }

    // 페어 조회 프로세스
    private static void selectPairProcess(PairResult pairResult) {
        PairTypeRequest pairTypeRequest = inputView.selectPairTypeInput();
        Pair pair = pairMatchingController.selectPair(pairResult, pairTypeRequest);
        outputView.printPair(pair);
    }

    // 페어 초기화 프로세스
    private static void resetPairProcess(PairResult pairResult) {
        pairMatchingController.resetPair(pairResult);
    }
}
