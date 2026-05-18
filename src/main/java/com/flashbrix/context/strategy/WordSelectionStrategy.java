package com.flashbrix.context.strategy;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.CefrLevel;
import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.Word;
import com.flashbrix.vocabulary.extraction.ExtractionContext;
import com.flashbrix.vocabulary.extraction.SourceType;

/**
 * PADRÃO STRATEGY — estratégia concreta para Word.
 *
 * Identifica o token individual (palavra) na posição clicada.
 * Usado quando o usuário clica em uma única palavra para adicioná-la ao léxico.
 */
public class WordSelectionStrategy implements SelectionStrategy {

    @Override
    public LexicalUnit expandSelection(int position, String text, Language language) {
        // Percorre os tokens e identifica qual cobre a posição clicada
        String[] tokens = text.split("\\s+");
        int charPos = 0;
        for (String token : tokens) {
            String clean = token.replaceAll("[^\\w]", "");
            if (position >= charPos && position < charPos + token.length()) {
                ExtractionContext ctx = new ExtractionContext(clean, language, CefrLevel.B1, SourceType.BOOK);
                return new Word(clean, language, CefrLevel.B1, ctx);
            }
            charPos += token.length() + 1;
        }
        // Fallback: primeiro token
        String fallback = tokens.length > 0 ? tokens[0].replaceAll("[^\\w]", "") : text;
        ExtractionContext ctx = new ExtractionContext(fallback, language, CefrLevel.B1, SourceType.BOOK);
        return new Word(fallback, language, CefrLevel.B1, ctx);
    }
}
