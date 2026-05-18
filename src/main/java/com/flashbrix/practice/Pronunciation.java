package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

/**
 * PADRÃO COMPOSITE — folha.
 * PADRÃO OBSERVER — dispara notifyObservers() ao ser submetida.
 *
 * Prática de pronúncia: o usuário ouve a pronúncia de referência e
 * grava a própria versão para comparação fonética.
 */
public class Pronunciation extends Practice {

    private String recordedAudio;
    private int attempts;

    public Pronunciation(String instruction, Language language, Level level) {
        super(instruction, language, level);
        this.attempts = 0;
    }

    public void recordAudio(String audioPath) {
        this.recordedAudio = audioPath;
        this.attempts++;
    }

    public void submitPronunciation(boolean isCorrect) {
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

    public String getRecordedAudio() { return recordedAudio; }
    public int getAttempts() { return attempts; }

    @Override
    public String toString() {
        return "Pronunciation{" + super.toString() + ", attempts=" + attempts + "}";
    }
}
