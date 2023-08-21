import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String SPACE_DELIMETER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputText) {
        if (inputText.split(SPACE_DELIMETER).length == 1) {
            return inputText + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputText.split(SPACE_DELIMETER);

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String word : words) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, 1);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequencyInfo>> map = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : map.entrySet()) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = list;

                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
                for (WordFrequencyInfo word : wordFrequencyInfoList) {
                    String s = word.getWord() + SPACE_CHAR + word.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequencyInfo.getWord())) {
                List<WordFrequencyInfo> frequencyInfoList = new ArrayList<>();
                frequencyInfoList.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), frequencyInfoList);
            } else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }
        return map;
    }

}
