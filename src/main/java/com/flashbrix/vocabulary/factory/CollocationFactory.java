package com.flashbrix.vocabulary.factory;

import com.flashbrix.vocabulary.Collocation;
import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — criador concreto para Collocation.
 * Instancia uma Collocation a partir do ExtractionContext.
 * Cobre colocações, verbos frasais e combinações lexicais recorrentes.
 */
public class CollocationFactory extends LexicalUnitFactory {

    @Override
    public LexicalUnit create(ExtractionContext ctx) {
        return new Collocation(ctx.getRawText(), ctx.getLanguage(), ctx.getCefr(), ctx);
    }
}
