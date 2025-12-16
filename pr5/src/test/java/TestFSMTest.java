import duikt.java.com.TestFSM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Параметризовані тести для скінченого автомата TestFSM.
 */
class TestFSMTest {
    
    private TestFSM fsm;
    
    @BeforeEach
    void setUp() {
        fsm = new TestFSM();
    }
    
    // ========== БАЗОВІ ТЕСТИ ==========
    
    @ParameterizedTest(name = "Рядок \"{0}\" повинен повернути \"{1}\"")
    @DisplayName("Базові випадки з прикладу завдання")
    @CsvSource({
        "abcTESTabc, F",
        "abcTES, 3"
    })
    void testBasicExamples(String input, String expected) {
        assertEquals(expected, fsm.process(input));
    }
    
    // ========== ТЕСТИ НА ЗНАХОДЖЕННЯ TEST ==========
    
    @ParameterizedTest(name = "Рядок \"{0}\" містить TEST -> F")
    @DisplayName("Рядки що містять TEST повинні повернути F")
    @ValueSource(strings = {
        "TEST",
        "abcTEST",
        "TESTabc",
        "abcTESTabc",
        "TESTTEST",
        "xxxTESTyyy",
        "TEST123",
        "123TEST"
    })
    void testStringsContainingTEST(String input) {
        assertEquals("F", fsm.process(input));
    }
    
    // ========== ТЕСТИ НА ВІДСУТНІСТЬ TEST ==========
    
    @ParameterizedTest(name = "Рядок \"{0}\" не містить TEST")
    @DisplayName("Рядки що не містять TEST")
    @CsvSource({
        "'', 0",
        "abc, 0",
        "T, 1",
        "TE, 2",
        "TES, 3",
        "TSET, 1",
        "ETST, 1",
        "test, 0",
        "Test, 0",
        "tEST, 1"
    })
    void testStringsNotContainingTEST(String input, String expected) {
        assertEquals(expected, fsm.process(input));
    }
    
    // ========== ТЕСТИ НА ПРОМІЖНІ СТАНИ ==========
    
    @ParameterizedTest(name = "Стан 1: після T в рядку \"{0}\"")
    @DisplayName("Рядки що закінчуються в стані 1 (знайдено T)")
    @ValueSource(strings = {
        "T",
        "abcT",
        "xyzT",
        "123T"
    })
    void testState1(String input) {
        assertEquals("1", fsm.process(input));
    }
    
    @ParameterizedTest(name = "Стан 2: після TE в рядку \"{0}\"")
    @DisplayName("Рядки що закінчуються в стані 2 (знайдено TE)")
    @ValueSource(strings = {
        "TE",
        "abcTE",
        "xyzTE"
    })
    void testState2(String input) {
        assertEquals("2", fsm.process(input));
    }
    
    @ParameterizedTest(name = "Стан 3: після TES в рядку \"{0}\"")
    @DisplayName("Рядки що закінчуються в стані 3 (знайдено TES)")
    @ValueSource(strings = {
        "TES",
        "abcTES",
        "xyzTES"
    })
    void testState3(String input) {
        assertEquals("3", fsm.process(input));
    }
    
    // ========== ТЕСТИ НА ГРАНИЧНІ ВИПАДКИ (ЗАВДАННЯ ІЗ ЗІРОЧКОЮ) ==========
    
    @ParameterizedTest(name = "Граничний випадок: \"{0}\" повинен повернути F")
    @DisplayName("Граничні випадки - рядки з подвійними символами що містять TEST")
    @CsvSource({
        "TTEST, F",
        "TTTEST, F",
        "TETEST, F",
        "TESTEST, F"
    })
    void testEdgeCasesWithTEST(String input, String expected) {
        assertEquals(expected, fsm.process(input));
    }
    
    @Test
    @DisplayName("Граничний випадок TTEST - детальний аналіз")
    void testTTESTEdgeCase() {
        String result = fsm.process("TTEST");
        assertEquals("F", result, 
            "TTEST містить TEST (останні 4 символи). " +
            "Перший T переводить в стан 1, другий T залишається в стані 1, " +
            "потім EST завершує TEST.");
    }
    
    @Test
    @DisplayName("Граничний випадок TETEST - детальний аналіз")
    void testTETESTEdgeCase() {
        String result = fsm.process("TETEST");
        assertEquals("F", result,
            "TETEST містить TEST (останні 4 символи). " +
            "TE переводить в стан 2, потім T повертає в стан 1, " +
            "і EST завершує TEST.");
    }
    
    @Test
    @DisplayName("Граничний випадок TESTEST - детальний аналіз")
    void testTESTESTEdgeCase() {
        String result = fsm.process("TESTEST");
        assertEquals("F", result,
            "TESTEST містить TEST двічі: TEST на позиціях 0-3 і TEST на позиціях 3-6.");
    }
    
    // ========== ТЕСТИ НА СКИДАННЯ СТАНУ ==========
    
    @Test
    @DisplayName("Автомат правильно скидається між викликами")
    void testResetBetweenCalls() {
        assertEquals("F", fsm.process("TEST"));
        assertEquals("0", fsm.process("abc"));
        assertEquals("F", fsm.process("abcTESTxyz"));
    }
    
    // ========== ТЕСТИ НА NULL ТА ПОРОЖНІЙ РЯДОК ==========
    
    @Test
    @DisplayName("Null рядок повертає стан 0")
    void testNullInput() {
        assertEquals("0", fsm.process(null));
    }
    
    @Test
    @DisplayName("Порожній рядок повертає стан 0")
    void testEmptyInput() {
        assertEquals("0", fsm.process(""));
    }
    
    // ========== ТЕСТИ НА МНОЖИННІ ВХОДЖЕННЯ ==========
    
    @ParameterizedTest(name = "Множинні TEST в рядку \"{0}\"")
    @DisplayName("Рядки з множинними входженнями TEST")
    @ValueSource(strings = {
        "TESTTEST",
        "TESTTESTTEST",
        "abcTESTdefTESTghi"
    })
    void testMultipleTEST(String input) {
        assertEquals("F", fsm.process(input));
    }
    
    // ========== ТЕСТИ НА РЕГІСТР ==========
    
    @ParameterizedTest(name = "Нижній регістр \"{0}\" не розпізнається")
    @DisplayName("Автомат чутливий до регістру - нижній регістр не розпізнається")
    @CsvSource({
        "test, 0",
        "Test, 0",
        "tEst, 0",
        "tEST, 1",
        "TEst, 0"
    })
    void testCaseSensitivity(String input, String expected) {
        assertEquals(expected, fsm.process(input));
    }
}
