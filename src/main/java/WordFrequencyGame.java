import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_BREAK_DELIMITER = "\n";
    private static final String BLANK_SPACE = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {


        if (inputStr.split(SPACE_REGEX).length==1) {
            return inputStr + " 1";
        } else {

            try {

                List<Input> inputList = calculateWordCount(inputStr);

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                return generateWordFrequency(inputList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<Input> calculateWordCount(String inputStr) {
        String[] arr = inputStr.split(SPACE_REGEX);

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }

        Map<String, List<Input>> map =getListMap(inputList);

        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        inputList = list;
        return inputList;
    }

    private String generateWordFrequency(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK_DELIMITER);
        for (Input w : inputList) {
            String s = w.getValue() + BLANK_SPACE +w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList){
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }
            else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
