import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_BREAK_DELIMITER = "\n";
    private static final String BLANK_SPACE = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {

            List<WordInfo> wordInfos = calculateWordCount(sentence);

            wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return generateWordFrequency(wordInfos);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordCount(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String uniqueWord: new HashSet<>(words)) {
            int count = (int)words.stream().filter(word -> word.equals(uniqueWord)).count();
            wordInfos.add(new WordInfo(uniqueWord, count));
        }
        return wordInfos;
    }

    private String generateWordFrequency(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK_DELIMITER);
        for (WordInfo wordInfo : wordInfos) {
            String wordFrequency = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
            joiner.add(wordFrequency);
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
