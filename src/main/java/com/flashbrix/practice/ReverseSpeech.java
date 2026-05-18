package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

/**
 * PADRÃO COMPOSITE — folha.
 * PADRÃO OBSERVER — dispara notifyObservers() ao ser submetida.
 *
 * Prática de fala inversa: o usuário ouve uma sentença na língua nativa
 * e a produz oralmente no idioma alvo.
 */
public class ReverseSpeech extends Practice {

    private String recordedAudio;
    private int attempts;

    public ReverseSpeech(String instruction, Language language, Level level) {
        super(instruction, language, level);
        this.attempts = 0;
    }

    public void recordAudio(String audioPath) {
        this.recordedAudio = audioPath;
        this.attempts++;
    }

    public void submitAudio(boolean isCorrect) {
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
        return "ReverseSpeech{" + super.toString() + ", attempts=" + attempts + "}";
    }
}
