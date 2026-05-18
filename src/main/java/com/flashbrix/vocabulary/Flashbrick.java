package com.flashbrix.vocabulary;

import com.flashbrix.practice.observer.RetentionObserver;
import com.flashbrix.user.Learner;

import java.time.LocalDateTime;

/**
 * PADRÃO OBSERVER — observador concreto.
 *
 * Classe associativa entre Learner e LexicalUnit, pertencente ao domínio vocabulary.
 * Representa um "tijolo" de conhecimento: a relação entre o aprendiz e uma unidade
 * léxica específica, medida pelo synapticRetentionIndex (SRI).
 *
 * Ao implementar RetentionObserver e se inscrever em uma instância de Practice,
 * Flashbrick é notificado a cada submissão e recalcula o SRI com base no
 * desempenho — sem que Practice precise conhecer Flashbrick diretamente.
 *
 * SRI: float entre 0 (sem retenção) e 1 (retenção máxima, uso fluente).
 */
public class Flashbrick implements RetentionObserver {

    private final long id;
    private final Learner learner;
    private final LexicalUnit lexicalUnit;
    private float synapticRetentionIndex;
    private final LocalDateTime createdAt;
    private LocalDateTime lastPracticed;

    private static long nextId = 1;

    private static final float GAIN  = 0.10f;
    private static final float DECAY = 0.05f;

    public Flashbrick(Learner learner, LexicalUnit lexicalUnit) {
        this.id = nextId++;
        this.learner = learner;
        this.lexicalUnit = lexicalUnit;
        this.synapticRetentionIndex = 0.0f;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Recebe a notificação de Practice e atualiza o SRI.
     * Acerto → SRI sobe GAIN (máx 1.0).
     * Erro   → SRI cai DECAY  (mín 0.0).
     */
    @Override
    public void onPracticeSubmitted(boolean isCorrect) {
        if (isCorrect) {
            synapticRetentionIndex = Math.min(1.0f, synapticRetentionIndex + GAIN);
        } else {
            synapticRetentionIndex = Math.max(0.0f, synapticRetentionIndex - DECAY);
        }
        lastPracticed = LocalDateTime.now();
    }

    /** Remove o vocabulário da fila de repetição espaçada (SRI → 0). */
    public void removeFromCue() {
        synapticRetentionIndex = 0.0f;
    }

    public long getId() { return id; }
    public Learner getLearner() { return learner; }
    public LexicalUnit getLexicalUnit() { return lexicalUnit; }
    public float getSynapticRetentionIndex() { return synapticRetentionIndex; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastPracticed() { return lastPracticed; }

    @Override
    public String toString() {
        return "Flashbrick{learner=" + learner.getUsername()
                + ", term='" + lexicalUnit.getTerm()
                + "', SRI=" + String.format("%.2f", synapticRetentionIndex) + "}";
    }
}
