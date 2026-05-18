package com.flashbrix.context.strategy;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.LexicalUnit;

/**
 * PADRÃO STRATEGY — interface da estratégia.
 *
 * Define o contrato para os algoritmos de expansão de seleção léxica.
 * Sentence mantém uma referência para a implementação ativa e chama
 * expandSelection() sem saber qual algoritmo está sendo executado.
 *
 * Estratégias disponíveis:
 *   WordSelectionStrategy       — expande para o token único na posição
 *   PhraseSelectionStrategy     — expande para o grupo frasal ao redor
 *   ClauseSelectionStrategy     — expande para a oração completa
 *   CollocationSelectionStrategy — expande para a colocação identificada
 */
public interface SelectionStrategy {

    /**
     * Expande a seleção a partir da posição clicada e retorna a unidade léxica.
     *
     * @param position posição do caractere clicado na sentença
     * @param text     texto completo da sentença
     * @param language idioma da sentença
     * @return unidade léxica identificada na posição
     */
    LexicalUnit expandSelection(int position, String text, Language language);
}
