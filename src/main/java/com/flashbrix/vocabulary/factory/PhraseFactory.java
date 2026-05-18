package com.flashbrix.vocabulary.factory;

import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.Phrase;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — criador concreto para Phrase.
 * Instancia um Phrase a partir do ExtractionContext.
 */
public class PhraseFactory extends LexicalUnitFactory {

    @Override
    public LexicalUnit create(ExtractionContext ctx) {
        return new Phrase(ctx.getRawText(), ctx.getLanguage(), ctx.getCefr(), ctx);
    }
}
