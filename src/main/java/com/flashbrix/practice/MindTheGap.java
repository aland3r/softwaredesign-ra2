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
 * Prática de lacunas: o usuário arrasta blocos de vocabulário para
 * preencher os espaços em branco de uma sentença.
 */
public class MindTheGap extends Practice {

    private final String gappedText;
    private final List<LexicalUnit> blocks;
    private final List<LexicalUnit> correctAnswers;
    private final List<LexicalUnit> userAnswers;

    public MindTheGap(String instruction, Language language, Level level,
                      String gappedText, List<LexicalUnit> blocks,
                      List<LexicalUnit> correctAnswers) {
        super(instruction, language, level);
        this.gappedText = gappedText;
        this.blocks = blocks;
        this.correctAnswers = correctAnswers;
        this.userAnswers = new ArrayList<>();
    }

    public void addAnswer(LexicalUnit unit) {
        userAnswers.add(unit);
    }

    public void clearAnswers() {
        userAnswers.clear();
    }

    public void submitAnswers() {
        boolean correct = userAnswers.size() == correctAnswers.size()
                && userAnswers.stream().map(LexicalUnit::getTerm)
                    .toList().equals(correctAnswers.stream().map(LexicalUnit::getTerm).toList());
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

    public String getGappedText() { return gappedText; }
    public List<LexicalUnit> getBlocks() { return blocks; }
    public List<LexicalUnit> getUserAnswers() { return userAnswers; }

    @Override
    public String toString() {
        return "MindTheGap{" + super.toString() + ", gappedText='" + gappedText + "'}";
    }
}
