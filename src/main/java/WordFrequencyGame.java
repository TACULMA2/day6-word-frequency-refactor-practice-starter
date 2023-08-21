import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String calculateWordFrequency(String inputText) {
        String[] words = inputText.split(SPACE_DELIMITER);
        if (words.length == 1) {
            return inputText + SPACE_CHAR + "1";
        }
        List<WordFrequencyInfo> wordFrequencyInfoList = calculateWordFrequencyInfoList(words);
        if (wordFrequencyInfoList == null) {
            throw new RuntimeException(CALCULATE_ERROR);
        }
        Map<String, List<WordFrequencyInfo>> wordFrequencyMap = createWordFrequencyMap(wordFrequencyInfoList);
        List<WordFrequencyInfo> sortedWordFrequencyList = frequencyInfo(wordFrequencyMap);
        return generatePrintLines(sortedWordFrequencyList);
    }

    private List<WordFrequencyInfo> calculateWordFrequencyInfoList(String[] words) {
        return Arrays.stream(words)
                .map(word -> new WordFrequencyInfo(word, 1))
                .collect(Collectors.toList());
    }

    private List<WordFrequencyInfo> frequencyInfo(Map<String, List<WordFrequencyInfo>> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .sorted(Comparator.comparing(WordFrequencyInfo::getWordCount).reversed())
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
