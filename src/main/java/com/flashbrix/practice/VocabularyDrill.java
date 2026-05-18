package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

/**
 * PADRÃO COMPOSITE — folha.
 * PADRÃO OBSERVER — dispara notifyObservers() ao ser submetida.
 *
 * Prática de flashcard: exibe a pista (tradução, definição ou áudio) e
 * o usuário tenta recuperar a palavra do idioma alvo.
 */
public class VocabularyDrill extends Practice {

    private int attempts;
    private final String cueType;
    private boolean revealed;

    public VocabularyDrill(String instruction, Language language, Level level, String cueType) {
        super(instruction, language, level);
        this.cueType = cueType;
        this.attempts = 0;
        this.revealed = false;
    }

    public void reveal() {
        this.revealed = true;
        this.attempts++;
    }

    public void submit(boolean isCorrect) {
        evaluatePractice(isCorrect);
    }

    @Override
    public void evaluatePractice(boolean isCorrect) {
        notifyObservers(isCorrect);
    }

    @Override
    public Practice nextPractice() {
        return null;
    }

    public int getAttempts() { return attempts; }
    public String getCueType() { return cueType; }
    public boolean isRevealed() { return revealed; }

    @Override
    public String toString() {
        return "VocabularyDrill{" + super.toString() + ", cueType='" + cueType + "'}";
    }
}
