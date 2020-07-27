import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_BREAK_DELIMITER = "\n";
    private static final String BLANK_SPACE = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {
        try {

            List<WordInfo> wordInfoList = calculateWordCount(inputStr);

            wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return generateWordFrequency(wordInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordCount(String inputStr) {
        List<String> words = Arrays.asList(inputStr.split(SPACE_REGEX));
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String uniqueWord: new HashSet<>(words)) {
            int count = (int)words.stream().filter(word -> word.equals(uniqueWord)).count();
            wordInfos.add(new WordInfo(uniqueWord, count));
        }
        return wordInfos;
    }

    private String generateWordFrequency(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK_DELIMITER);
        for (WordInfo w : wordInfoList) {
            String s = w.getValue() + BLANK_SPACE + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
