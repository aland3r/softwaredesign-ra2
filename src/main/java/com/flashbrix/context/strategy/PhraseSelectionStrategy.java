package com.flashbrix.context.strategy;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.CefrLevel;
import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.Phrase;
import com.flashbrix.vocabulary.extraction.ExtractionContext;
import com.flashbrix.vocabulary.extraction.SourceType;

/**
 * PADRÃO STRATEGY — estratégia concreta para Phrase.
 *
 * Expande a seleção para o grupo frasal ao redor da posição clicada.
 * A heurística extrai uma janela de até 3 tokens centrada na posição —
 * em produção, seria substituída por análise sintática via spaCy/NLP.
 */
public class PhraseSelectionStrategy implements SelectionStrategy {

    private static final int WINDOW = 3;

    @Override
    public LexicalUnit expandSelection(int position, String text, Language language) {
        String[] tokens = text.split("\\s+");
        int charPos = 0;
        int targetIndex = 0;

        // Localiza o índice do token na posição clicada
        for (int i = 0; i < tokens.length; i++) {
            if (position >= charPos && position < charPos + tokens[i].length()) {
                targetIndex = i;
                break;
            }
            charPos += tokens[i].length() + 1;
        }

        // Monta o grupo frasal com até WINDOW tokens ao redor do alvo
        int start = Math.max(0, targetIndex - 1);
        int end = Math.min(tokens.length, start + WINDOW);
        StringBuilder phrase = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (i > start) phrase.append(" ");
            phrase.append(tokens[i].replaceAll("[^\\w\\s]", ""));
        }

        String term = phrase.toString().trim();
        ExtractionContext ctx = new ExtractionContext(term, language, CefrLevel.B2, SourceType.BOOK);
        return new Phrase(term, language, CefrLevel.B2, ctx);
    }
}
