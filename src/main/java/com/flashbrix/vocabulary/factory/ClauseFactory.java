package com.flashbrix.vocabulary.factory;

import com.flashbrix.vocabulary.Clause;
import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — criador concreto para Clause.
 * Instancia uma Clause a partir do ExtractionContext.
 */
public class ClauseFactory extends LexicalUnitFactory {

    @Override
    public LexicalUnit create(ExtractionContext ctx) {
        return new Clause(ctx.getRawText(), ctx.getLanguage(), ctx.getCefr(), ctx);
    }
}
