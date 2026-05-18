package com.flashbrix.practice;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;
import com.flashbrix.practice.observer.RetentionObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO OBSERVER — subject abstrato.
 * PADRÃO COMPOSITE — componente abstrato.
 *
 * Practice cumpre dois papéis simultâneos:
 *
 * (1) Subject do Observer: mantém a lista de RetentionObserver inscritos e
 *     chama notifyObservers() a cada submissão, desacoplando Practice de
 *     Flashbrick e de qualquer outro subsistema que precise reagir ao resultado.
 *
 * (2) Componente do Composite: declara evaluatePractice() e nextPractice(),
 *     que são implementados de forma diferente por folhas (Translation,
 *     Pronunciation…) e pelo nó composto (BidirectionalTranslation).
 *     O cliente percorre a árvore sem distinguir folhas de compostos.
 */
public abstract class Practice {

    private final int id;
    private final String instruction;
    private final Language language;
    private final Level level;

    // Observer: lista de inscritos que serão notificados a cada submissão
    private final List<RetentionObserver> observers = new ArrayList<>();

    private static int nextId = 1;

    protected Practice(String instruction, Language language, Level level) {
        this.id = nextId++;
        this.instruction = instruction;
        this.language = language;
        this.level = level;
    }

    // ─── Observer: gerenciamento de inscrições ───────────────────────────────

    /** Inscreve um observador para receber notificações desta prática. */
    public void subscribe(RetentionObserver observer) {
        observers.add(observer);
    }

    /** Remove um observador da lista de notificações. */
    public void unsubscribe(RetentionObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifica todos os inscritos com o resultado da submissão.
     * Chamado por evaluatePractice() nas subclasses.
     */
    protected void notifyObservers(boolean isCorrect) {
        for (RetentionObserver observer : observers) {
            observer.onPracticeSubmitted(isCorrect);
        }
    }

    // ─── Composite: interface comum entre folhas e composto ──────────────────

    /**
     * Avalia a prática com base no resultado do usuário.
     * Folhas: chamam notifyObservers() diretamente.
     * Composto (BidirectionalTranslation): delega para cada prática filha
     * e depois chama notifyObservers() no próprio nó.
     */
    public abstract void evaluatePractice(boolean isCorrect);

    /**
     * Retorna a próxima prática na sequência.
     * Folhas: retornam null.
     * Composto: retorna o próximo filho da lista interna.
     */
    public abstract Practice nextPractice();

    // ─── Getters ─────────────────────────────────────────────────────────────

    public int getId() { return id; }
    public String getInstruction() { return instruction; }
    public Language getLanguage() { return language; }
    public Level getLevel() { return level; }

    @Override
    public String toString() {
        return "Practice{id=" + id + ", instruction='" + instruction + "', level=" + level + "}";
    }
}
