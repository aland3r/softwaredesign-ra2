package com.flashbrix.vocabulary.factory;

import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.Word;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — criador concreto para Word.
 * Instancia um Word a partir do ExtractionContext.
 */
public class WordFactory extends LexicalUnitFactory {

    @Override
    public LexicalUnit create(ExtractionContext ctx) {
        return new Word(ctx.getRawText(), ctx.getLanguage(), ctx.getCefr(), ctx);
    }
}
