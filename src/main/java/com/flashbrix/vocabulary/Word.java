package com.flashbrix.vocabulary;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — produto concreto.
 * Representa uma palavra isolada (token único) extraída do contexto de imersão.
 */
public class Word extends LexicalUnit {

    public Word(String term, Language language, CefrLevel cefr, ExtractionContext ctx) {
        super(term, language, cefr, ctx);
    }

    @Override
    protected String kindLabel() {
        return "Word";
    }
}
