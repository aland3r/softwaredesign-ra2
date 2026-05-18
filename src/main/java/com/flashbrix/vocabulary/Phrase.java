package com.flashbrix.vocabulary;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — produto concreto.
 * Representa um grupo frasal (NP, VP, PP…) extraído do contexto de imersão.
 * Cobre agrupamentos contíguos de duas ou mais palavras que funcionam como
 * uma unidade sintática.
 */
public class Phrase extends LexicalUnit {

    public Phrase(String term, Language language, CefrLevel cefr, ExtractionContext ctx) {
        super(term, language, cefr, ctx);
    }

    @Override
    protected String kindLabel() {
        return "Phrase";
    }
}
