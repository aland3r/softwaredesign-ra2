package com.flashbrix.practice.observer;

/**
 * PADRÃO OBSERVER — interface do observador.
 *
 * Todo objeto que queira reagir à submissão de uma prática deve implementar
 * esta interface e se inscrever em uma instância de Practice via subscribe().
 * No Flashbrix, Flashbrick é o observador principal: ao ser notificado,
 * recalcula o synapticRetentionIndex do vocabulário praticado.
 */
public interface RetentionObserver {

    /**
     * Chamado por Practice.notifyObservers() a cada submissão de resposta.
     *
     * @param isCorrect true se o usuário acertou, false caso contrário
     */
    void onPracticeSubmitted(boolean isCorrect);
}
