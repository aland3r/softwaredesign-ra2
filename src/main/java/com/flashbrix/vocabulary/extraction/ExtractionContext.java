package com.flashbrix.vocabulary.extraction;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.CefrLevel;

/**
 * Encapsula o contexto em que uma unidade léxica foi extraída.
 * É o parâmetro central do Factory Method: a fábrica lê o rawText,
 * o idioma, o nível QECR e o tipo de fonte para criar o produto correto.
 */
public class ExtractionContext {

    private final String rawText;
    private final Language language;
    private final CefrLevel cefr;
    private final SourceType sourceType;

    public ExtractionContext(String rawText, Language language, CefrLevel cefr, SourceType sourceType) {
        this.rawText = rawText;
        this.language = language;
        this.cefr = cefr;
        this.sourceType = sourceType;
    }

    public String getRawText() {
        return rawText;
    }

    public Language getLanguage() {
        return language;
    }

    public CefrLevel getCefr() {
        return cefr;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    @Override
    public String toString() {
        return "ExtractionContext{rawText='" + rawText + "', language=" + language
                + ", cefr=" + cefr + ", source=" + sourceType + "}";
    }
}
