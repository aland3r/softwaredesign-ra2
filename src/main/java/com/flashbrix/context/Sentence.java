package com.flashbrix.context;

import com.flashbrix.context.strategy.SelectionStrategy;
import com.flashbrix.language.Language;
import com.flashbrix.vocabulary.LexicalUnit;

/**
 * PADRÃO STRATEGY — contexto (classe que usa a estratégia).
 *
 * Representa uma sentença dentro de um contexto de imersão (livro ou vídeo).
 * Quando o usuário clica em uma posição do texto, Sentence delega o trabalho
 * de expansão léxica para a SelectionStrategy ativa, sem saber qual algoritmo
 * está sendo executado. A estratégia é trocada dinamicamente pelo sistema de
 * NLP/IA conforme o tipo de agrupamento identificado.
 */
public class Sentence {

    private final int id;
    private final String text;
    private final Language language;
    private final int order;
    private final int paragraph;
    private final int chapterNumber;
    private final String chapterTitle;

    // Referência à estratégia ativa — pode ser trocada a qualquer momento
    private SelectionStrategy selectionStrategy;

    private static int nextId = 1;

    public Sentence(String text, Language language, int order, int paragraph,
                    int chapterNumber, String chapterTitle) {
        this.id = nextId++;
        this.text = text;
        this.language = language;
        this.order = order;
        this.paragraph = paragraph;
        this.chapterNumber = chapterNumber;
        this.chapterTitle = chapterTitle;
    }

    public Sentence(String text, Language language) {
        this(text, language, 1, 1, 1, "");
    }

    /**
     * Define a estratégia de seleção ativa.
     * Chamado pelo sistema quando o tipo de agrupamento é identificado pela IA.
     */
    public void setSelectionStrategy(SelectionStrategy strategy) {
        this.selectionStrategy = strategy;
    }

    /**
     * Expande a seleção a partir da posição clicada.
     * Delega completamente para a SelectionStrategy — Sentence não conhece o algoritmo.
     *
     * @param position posição do caractere clicado
     * @return unidade léxica identificada
     */
    public LexicalUnit expandSelection(int position) {
        if (selectionStrategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de seleção definida.");
        }
        return selectionStrategy.expandSelection(position, text, language);
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public Language getLanguage() { return language; }
    public int getOrder() { return order; }
    public int getParagraph() { return paragraph; }
    public int getChapterNumber() { return chapterNumber; }
    public String getChapterTitle() { return chapterTitle; }

    @Override
    public String toString() {
        return "Sentence{id=" + id + ", text='" + text + "', language=" + language + "}";
    }
}
