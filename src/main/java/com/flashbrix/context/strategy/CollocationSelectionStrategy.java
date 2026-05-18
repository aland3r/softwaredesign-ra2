package com.flashbrix.context.strategy;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.CefrLevel;
import com.flashbrix.vocabulary.Collocation;
import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.extraction.ExtractionContext;
import com.flashbrix.vocabulary.extraction.SourceType;

/**
 * PADRÃO STRATEGY — estratégia concreta para Collocation.
 *
 * Expande a seleção para a colocação ao redor da posição clicada.
 * A heurística extrai uma janela de 2 tokens (palavra âncora + palavra co-ocorrente)
 * — em produção, seria alimentada por corpus e análise de co-ocorrência NLP.
 */
public class CollocationSelectionStrategy implements SelectionStrategy {

    @Override
    public LexicalUnit expandSelection(int position, String text, Language language) {
        String[] tokens = text.split("\\s+");
        int charPos = 0;
        int targetIndex = 0;

        // Localiza o token clicado
        for (int i = 0; i < tokens.length; i++) {
            if (position >= charPos && position < charPos + tokens[i].length()) {
                targetIndex = i;
                break;
            }
            charPos += tokens[i].length() + 1;
        }

        // Monta a colocação: token anterior + token clicado (ou clicado + próximo)
        int start = Math.max(0, targetIndex - 1);
        int end = Math.min(tokens.length, targetIndex + 2);
        StringBuilder collocation = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (i > start) collocation.append(" ");
            collocation.append(tokens[i].replaceAll("[^\\w\\s]", ""));
        }

        String term = collocation.toString().trim();
        ExtractionContext ctx = new ExtractionContext(term, language, CefrLevel.B2, SourceType.BOOK);
        return new Collocation(term, language, CefrLevel.B2, ctx);
    }
}
