package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO COMPOSITE — nó composto.
 *
 * BidirectionalTranslation agrega um conjunto ordenado de práticas
 * (Translation, ReverseTranslation, ReverseSpeech…) e as orquestra
 * como se fossem uma única prática do ponto de vista do cliente.
 *
 * Comportamento:
 * - evaluatePractice() delega recursivamente para cada filho e, após
 *   percorrer todos, notifica os próprios observers do nó composto.
 * - nextPractice() retorna a próxima prática pendente na fila interna.
 * - Novos tipos de prática podem ser adicionados como filhos sem
 *   alterar nenhuma outra parte do sistema.
 */
public class BidirectionalTranslation extends Practice {

    // Lista interna de práticas que compõem este nó composto
    private final List<Practice> practices = new ArrayList<>();
    private int currentIndex = 0;

    public BidirectionalTranslation(String instruction, Language language, Level level) {
        super(instruction, language, level);
    }

    /** Adiciona uma prática ao final da fila de execução. */
    public void enqueueTask(Practice practice) {
        practices.add(practice);
    }

    /** Remove uma prática da fila de execução. */
    public void dequeueTask(Practice practice) {
        practices.remove(practice);
    }

    /**
     * Avalia todas as práticas filhas em sequência e notifica os
     * observers do nó composto com o resultado agregado.
     *
     * @param isCorrect resultado geral passado para todos os filhos
     */
    @Override
    public void evaluatePractice(boolean isCorrect) {
        // Composite: propaga a avaliação recursivamente para cada filho
        for (Practice child : practices) {
            child.evaluatePractice(isCorrect);
        }
        // Após percorrer todos os filhos, notifica os observers deste nó
        notifyObservers(isCorrect);
    }

    /**
     * Retorna a próxima prática da fila de execução, em ordem FIFO.
     * Retorna null quando todas as práticas foram consumidas.
     */
    @Override
    public Practice nextPractice() {
        if (currentIndex < practices.size()) {
            return practices.get(currentIndex++);
        }
        return null;
    }

    public List<Practice> getPractices() {
        return practices;
    }

    @Override
    public String toString() {
        return "BidirectionalTranslation{" + super.toString()
                + ", children=" + practices.size() + "}";
    }
}
