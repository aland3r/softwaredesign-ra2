package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;
import com.flashbrix.vocabulary.LexicalUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO COMPOSITE — folha.
 * PADRÃO OBSERVER — dispara notifyObservers() ao ser submetida.
 *
 * Prática de construção de sentença: o usuário recebe blocos de vocabulário
 * embaralhados e os ordena para formar a sentença correta no idioma alvo.
 */
public class BrickLayer extends Practice {

    private final List<LexicalUnit> blocks;
    private final List<String> userSentence;

    public BrickLayer(String instruction, Language language, Level level, List<LexicalUnit> blocks) {
        super(instruction, language, level);
        this.blocks = blocks;
        this.userSentence = new ArrayList<>();
    }

    public void addToSentence(String term) {
        userSentence.add(term);
    }

    public void clearSlots() {
        userSentence.clear();
    }

    public void submitSentence(boolean isCorrect) {
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

    public List<LexicalUnit> getBlocks() { return blocks; }
    public List<String> getUserSentence() { return userSentence; }

    @Override
    public String toString() {
        return "BrickLayer{" + super.toString() + ", userSentence=" + userSentence + "}";
    }
}
