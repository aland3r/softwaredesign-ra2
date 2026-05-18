package com.flashbrix.vocabulary;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — produto concreto.
 * Representa uma colocação — combinação de palavras que coocorrem
 * sistematicamente (ex.: "take a decision", "heavy rain", "fazer uma pergunta").
 * Inclui verbos frasais (EN) e verbos separáveis (DE) como caso especial.
 */
public class Collocation extends LexicalUnit {

    public Collocation(String term, Language language, CefrLevel cefr, ExtractionContext ctx) {
        super(term, language, cefr, ctx);
    }

    @Override
    protected String kindLabel() {
        return "Collocation";
    }
}
