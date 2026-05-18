package com.flashbrix.vocabulary;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.extraction.ExtractionContext;

/**
 * PADRÃO FACTORY METHOD — produto abstrato.
 *
 * Superclasse abstrata de todas as unidades léxicas do Flashbrix.
 * O cliente programa para LexicalUnit, nunca para os tipos concretos
 * (Word, Phrase, Clause, Collocation). As fábricas concretas em
 * vocabulary/factory/ decidem qual subtipo instanciar com base no
 * ExtractionContext recebido.
 */
public abstract class LexicalUnit {

    private final String term;
    private final Language language;
    private final CefrLevel cefr;
    private final ExtractionContext extractionContext;

    protected LexicalUnit(String term, Language language, CefrLevel cefr,
                          ExtractionContext extractionContext) {
        this.term = term;
        this.language = language;
        this.cefr = cefr;
        this.extractionContext = extractionContext;
    }

    public String getTerm() { return term; }
    public Language getLanguage() { return language; }
    public CefrLevel getCefr() { return cefr; }
    public ExtractionContext getExtractionContext() { return extractionContext; }

    /** Identifica o tipo concreto da unidade — implementado por cada subtipo. */
    protected abstract String kindLabel();

    @Override
    public String toString() {
        return kindLabel() + "{term='" + term + "', language=" + language
                + ", cefr=" + cefr + "}";
    }
}
