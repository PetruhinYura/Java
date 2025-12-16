package duikt.java.com;

/**
 * Скінчений автомат (Finite State Machine) для розпізнавання слова "TEST" у рядку.
 * 
 * Стани:
 * - S (0): Початковий стан
 * - 1: Знайдено 'T'
 * - 2: Знайдено 'TE'
 * - 3: Знайдено 'TES'
 * - F (-1): Кінцевий стан - знайдено "TEST"
 */
public class TestFSM {
    
    private static final int STATE_S = 0;
    private static final int STATE_1 = 1;
    private static final int STATE_2 = 2;
    private static final int STATE_3 = 3;
    private static final int STATE_F = -1;
    
    private int currentState;
    
    public TestFSM() {
        this.currentState = STATE_S;
    }
    
    /**
     * Обробляє вхідний рядок і повертає кінцевий стан автомата.
     * 
     * @param input вхідний рядок
     * @return "F" якщо знайдено "TEST", або номер стану (0, 1, 2, 3)
     */
    public String process(String input) {
        currentState = STATE_S;
        
        if (input == null || input.isEmpty()) {
            return String.valueOf(currentState);
        }
        
        for (char c : input.toCharArray()) {
            processChar(c);
            if (currentState == STATE_F) {
                return "F";
            }
        }
        
        return currentState == STATE_F ? "F" : String.valueOf(currentState);
    }
    
    /**
     * Обробляє один символ і змінює стан автомата.
     * 
     * @param c символ для обробки
     */
    private void processChar(char c) {
        switch (currentState) {
            case STATE_S:
                if (c == 'T') {
                    currentState = STATE_1;
                }
                break;
                
            case STATE_1:
                if (c == 'E') {
                    currentState = STATE_2;
                } else if (c == 'T') {
                    currentState = STATE_1;
                } else {
                    currentState = STATE_S;
                }
                break;
                
            case STATE_2:
                if (c == 'S') {
                    currentState = STATE_3;
                } else if (c == 'T') {
                    currentState = STATE_1;
                } else {
                    currentState = STATE_S;
                }
                break;
                
            case STATE_3:
                if (c == 'T') {
                    currentState = STATE_F;
                } else {
                    currentState = STATE_S;
                }
                break;
                
            case STATE_F:
                break;
        }
    }
    
    /**
     * Повертає поточний стан автомата.
     * 
     * @return поточний стан
     */
    public int getCurrentState() {
        return currentState;
    }
    
    /**
     * Скидає автомат до початкового стану.
     */
    public void reset() {
        currentState = STATE_S;
    }
}
