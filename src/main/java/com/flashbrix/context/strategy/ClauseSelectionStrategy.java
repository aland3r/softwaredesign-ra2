package com.flashbrix.context.strategy;

import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.CefrLevel;
import com.flashbrix.vocabulary.Clause;
import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.extraction.ExtractionContext;
import com.flashbrix.vocabulary.extraction.SourceType;

/**
 * PADRÃO STRATEGY — estratégia concreta para Clause.
 *
 * Expande a seleção para a oração completa que contém a posição clicada.
 * A heurística delimita a oração pelas pontuações de pausa (vírgula, ponto-
 * e-vírgula) — em produção, seria substituída por análise de dependência NLP.
 */
public class ClauseSelectionStrategy implements SelectionStrategy {

    @Override
    public LexicalUnit expandSelection(int position, String text, Language language) {
        // Divide o texto em cláusulas delimitadas por vírgula ou ponto-e-vírgula
        String[] clauses = text.split("[,;]");
        int charPos = 0;
        for (String clause : clauses) {
            int end = charPos + clause.length();
            if (position >= charPos && position <= end) {
                String term = clause.trim();
                ExtractionContext ctx = new ExtractionContext(term, language, CefrLevel.C1, SourceType.BOOK);
                return new Clause(term, language, CefrLevel.C1, ctx);
            }
            charPos = end + 1;
        }
        // Fallback: sentença inteira como cláusula
        ExtractionContext ctx = new ExtractionContext(text, language, CefrLevel.C1, SourceType.BOOK);
        return new Clause(text, language, CefrLevel.C1, ctx);
    }
}
