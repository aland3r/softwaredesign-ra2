package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

/**
 * PADRÃO COMPOSITE — folha.
 * PADRÃO OBSERVER — dispara notifyObservers() ao ser submetida.
 *
 * Prática de tradução direta: o usuário recebe uma sentença no idioma alvo
 * e traduz para sua língua nativa.
 */
public class Translation extends Practice {

    private String userTranslation;
    private final String expectedTranslation;

    public Translation(String instruction, Language language, Level level, String expectedTranslation) {
        super(instruction, language, level);
        this.expectedTranslation = expectedTranslation;
    }

    public void submitTranslation(String userAnswer) {
        this.userTranslation = userAnswer;
        boolean correct = userAnswer.trim().equalsIgnoreCase(expectedTranslation.trim());
        evaluatePractice(correct);
    }

    @Override
    public void evaluatePractice(boolean isCorrect) {
        // Folha: notifica observers diretamente, sem delegar a filhos
        notifyObservers(isCorrect);
    }

    @Override
    public Practice nextPractice() {
        return null; // folha não possui práticas filhas
    }

    public String getUserTranslation() { return userTranslation; }
    public String getExpectedTranslation() { return expectedTranslation; }

    @Override
    public String toString() {
        return "Translation{" + super.toString() + ", userTranslation='" + userTranslation + "'}";
    }
}
