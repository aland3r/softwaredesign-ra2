package com.flashbrix.vocabulary.factory;

import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — criador abstrato.
 *
 * Define o contrato de criação de unidades léxicas. Subclasses concretas
 * (WordFactory, PhraseFactory, ClauseFactory, CollocationFactory) sobrescrevem
 * create() para instanciar o produto correto.
 *
 * O cliente programa para LexicalUnitFactory e recebe um LexicalUnit —
 * não sabe qual subclasse concreta foi criada.
 */
public abstract class LexicalUnitFactory {

    /**
     * Método de fábrica: define qual LexicalUnit será criada.
     * Subclasses concretas implementam este método.
     *
     * @param ctx contexto de extração com rawText, idioma, nível QECR e fonte
     * @return instância do produto léxico correspondente
     */
    public abstract LexicalUnit create(ExtractionContext ctx);
}
