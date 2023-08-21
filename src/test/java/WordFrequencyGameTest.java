import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the(){
        //Given
        String inputString = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(inputString, expectResult);
    }

    @Test
    public void should_process_two_words(){
        //Given
        String inputString = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputString, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces(){
        //Given
        String inputString = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputString, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter(){
        //Given
        String inputString = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputString, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted(){
        //Given
        String inputString = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(inputString, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending(){
        //Given
        String inputString = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_Input_words_process_to_expected_word(inputString, expectResult);
    }

    private void validate_Input_words_process_to_expected_word(String inputString, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.calculateWordFrequency(inputString);
        //Then
        assertThat(result).isEqualTo(expectResult);
    }
}
