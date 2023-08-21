import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputText) {
        if (inputText.split(SPACE_DELIMITER).length == 1) {
            return inputText + " 1";
        } else {
            try {
                String[] words = inputText.split(SPACE_DELIMITER);

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String word : words) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, 1);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = frequencyInfos(wordFrequencyInfoList)
                        .stream()
                        .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                        .collect(Collectors.toList());

                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordFrequencyInfo> frequencyInfos(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .collect(Collectors.groupingBy(WordFrequencyInfo::getWord))
                .entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .sorted(Comparator.comparingInt(WordFrequencyInfo::getWordCount).reversed())
                .collect(Collectors.toList());
    }

    private String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(wordFrequencyInfo -> wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                .collect(Collectors.joining(NEWLINE_DELIMITER));
    }

    private Map<String, List<WordFrequencyInfo>> createWordFrequencyMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
            map.computeIfAbsent(wordFrequencyInfo.getWord(), k -> new ArrayList<>()).add(wordFrequencyInfo);
        }
        return map;
    }
}
