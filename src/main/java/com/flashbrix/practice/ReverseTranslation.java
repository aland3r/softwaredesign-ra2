package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

/**
 * PADRÃO COMPOSITE — folha.
 * PADRÃO OBSERVER — dispara notifyObservers() ao ser submetida.
 *
 * Prática de tradução inversa: o usuário recebe uma sentença na língua nativa
 * e produz a versão no idioma alvo.
 */
public class ReverseTranslation extends Practice {

    private String userReverseTranslation;
    private final String expectedTranslation;

    public ReverseTranslation(String instruction, Language language, Level level,
                              String expectedTranslation) {
        super(instruction, language, level);
        this.expectedTranslation = expectedTranslation;
    }

    public void submitReverseTranslation(String userAnswer) {
        this.userReverseTranslation = userAnswer;
        boolean correct = userAnswer.trim().equalsIgnoreCase(expectedTranslation.trim());
        evaluatePractice(correct);
    }

    @Override
    public void evaluatePractice(boolean isCorrect) {
        notifyObservers(isCorrect);
    }

    @Override
    public Practice nextPractice() {
        return null;
    }

    public String getUserReverseTranslation() { return userReverseTranslation; }
    public String getExpectedTranslation() { return expectedTranslation; }

    @Override
    public String toString() {
        return "ReverseTranslation{" + super.toString()
                + ", userAnswer='" + userReverseTranslation + "'}";
    }
}
