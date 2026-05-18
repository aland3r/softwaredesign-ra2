package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

/**
 * PADRÃO COMPOSITE — folha.
 * PADRÃO OBSERVER — dispara notifyObservers() ao ser submetida.
 *
 * Prática de compreensão (Fathom): questão de múltipla escolha sobre o
 * conteúdo do contexto de imersão. Avalia a compreensão contextual,
 * não apenas a forma da palavra.
 */
public class Fathom extends Practice {

    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String optionD;
    private final char correctOption;
    private char userAnswer;

    public Fathom(String instruction, Language language, Level level,
                  String optionA, String optionB, String optionC, String optionD,
                  char correctOption) {
        super(instruction, language, level);
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    public void submitAnswer(char option) {
        this.userAnswer = option;
        evaluatePractice(Character.toLowerCase(option) == Character.toLowerCase(correctOption));
    }

    @Override
    public void evaluatePractice(boolean isCorrect) {
        notifyObservers(isCorrect);
    }

    @Override
    public Practice nextPractice() {
        return null;
    }

    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public char getCorrectOption() { return correctOption; }
    public char getUserAnswer() { return userAnswer; }

    @Override
    public String toString() {
        return "Fathom{" + super.toString() + ", userAnswer='" + userAnswer
                + "', correct=" + (userAnswer == correctOption) + "}";
    }
}
