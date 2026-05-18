package com.flashbrix.vocabulary;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — produto concreto.
 * Representa uma oração (cláusula principal ou subordinada) extraída do contexto.
 * Útil para capturar estruturas sintáticas complexas com sujeito e predicado.
 */
public class Clause extends LexicalUnit {

    public Clause(String term, Language language, CefrLevel cefr, ExtractionContext ctx) {
        super(term, language, cefr, ctx);
    }

    @Override
    protected String kindLabel() {
        return "Clause";
    }
}
