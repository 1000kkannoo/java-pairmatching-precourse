package pairmatching.view;

import pairmatching.request.PairTypeRequest;
import pairmatching.util.InputParser;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {

    public static final String SELECT_INPUT_MESSAGE = "기능을 선택하세요.\n" + "1. 페어 매칭\n" + "2. 페어 조회\n" + "3. 페어 초기화\n" + "Q. 종료";
    public static final String SELECT_PAIR_TYPE_MESSAGE = "#############################################\n" +
            "과정: 백엔드 | 프론트엔드\n" +
            "미션:\n" +
            "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
            "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
            "  - 레벨3: \n" +
            "  - 레벨4: 성능개선 | 배포\n" +
            "  - 레벨5: \n" +
            "############################################\n" +
            "과정, 레벨, 미션을 선택하세요.\n" +
            "ex) 백엔드, 레벨1, 자동차경주";

    public int selectInput() {
        System.out.println(SELECT_INPUT_MESSAGE);
        return Integer.parseInt(readLine());
    }

    public PairTypeRequest selectPairTypeInput() {
        System.out.println(SELECT_PAIR_TYPE_MESSAGE);
        String input = readLine();
        return InputParser.pairTypeParser(input);
    }
}
