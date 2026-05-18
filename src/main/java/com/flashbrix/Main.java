package com.flashbrix;

import com.flashbrix.context.Sentence;
import com.flashbrix.context.strategy.*;
import com.flashbrix.vocabulary.Flashbrick;
import com.flashbrix.language.Language;
import com.flashbrix.language.Level;
import com.flashbrix.practice.*;
import com.flashbrix.user.Learner;
import com.flashbrix.vocabulary.CefrLevel;
import com.flashbrix.vocabulary.LexicalUnit;
import com.flashbrix.vocabulary.extraction.ExtractionContext;
import com.flashbrix.vocabulary.extraction.SourceType;
import com.flashbrix.vocabulary.factory.*;

/**
 * Demonstração dos quatro padrões GoF aplicados no Flashbrix:
 *   1. Factory Method — criação polimórfica de LexicalUnit
 *   2. Strategy       — expansão léxica intercambiável em Sentence
 *   3. Observer       — Flashbrick atualiza SRI a cada submissão de Practice
 *   4. Composite      — BidirectionalTranslation orquestra múltiplas práticas
 */
public class Main {

    public static void main(String[] args) {

        separador("1. FACTORY METHOD");

        // O cliente define o ExtractionContext (o que foi clicado, idioma, nível, fonte)
        // A fábrica concreta decide qual subclasse de LexicalUnit instanciar.
        // O cliente recebe LexicalUnit — nunca sabe se é Word, Phrase, Clause ou Collocation.

        ExtractionContext ctxWord = new ExtractionContext(
                "serendipity", Language.EN, CefrLevel.C1, SourceType.BOOK);
        LexicalUnitFactory factory = new WordFactory();
        LexicalUnit word = factory.create(ctxWord);
        System.out.println("Fábrica criou: " + word);

        ExtractionContext ctxPhrase = new ExtractionContext(
                "at the end of the day", Language.EN, CefrLevel.B2, SourceType.YOUTUBE);
        factory = new PhraseFactory();
        LexicalUnit phrase = factory.create(ctxPhrase);
        System.out.println("Fábrica criou: " + phrase);

        ExtractionContext ctxColloc = new ExtractionContext(
                "make a decision", Language.EN, CefrLevel.B1, SourceType.BOOK);
        factory = new CollocationFactory();
        LexicalUnit collocation = factory.create(ctxColloc);
        System.out.println("Fábrica criou: " + collocation);

        ExtractionContext ctxClause = new ExtractionContext(
                "although she had studied for weeks", Language.EN, CefrLevel.C1, SourceType.BOOK);
        factory = new ClauseFactory();
        LexicalUnit clause = factory.create(ctxClause);
        System.out.println("Fábrica criou: " + clause);

        // ─────────────────────────────────────────────────────────────────────

        separador("2. STRATEGY");

        // Sentence delega expandSelection() para a estratégia ativa.
        // A estratégia pode ser trocada sem alterar Sentence.

        Sentence sentence = new Sentence(
                "Although she had studied for weeks, the exam was incredibly difficult.",
                Language.EN);

        // Estratégia de palavra: extrai o token único na posição clicada
        sentence.setSelectionStrategy(new WordSelectionStrategy());
        LexicalUnit selectedWord = sentence.expandSelection(8); // "she"
        System.out.println("Strategy Word    → " + selectedWord);

        // Estratégia de frase: expande para o grupo frasal ao redor
        sentence.setSelectionStrategy(new PhraseSelectionStrategy());
        LexicalUnit selectedPhrase = sentence.expandSelection(37); // "the exam"
        System.out.println("Strategy Phrase  → " + selectedPhrase);

        // Estratégia de colocação: dois tokens que coocorrem sistematicamente
        sentence.setSelectionStrategy(new CollocationSelectionStrategy());
        LexicalUnit selectedColloc = sentence.expandSelection(51); // "incredibly difficult"
        System.out.println("Strategy Colloc  → " + selectedColloc);

        // Estratégia de cláusula: delimita a oração completa
        sentence.setSelectionStrategy(new ClauseSelectionStrategy());
        LexicalUnit selectedClause = sentence.expandSelection(10); // cláusula subordinada
        System.out.println("Strategy Clause  → " + selectedClause);

        // ─────────────────────────────────────────────────────────────────────

        separador("3. OBSERVER");

        // Learner cria uma conta e adquire a palavra "serendipity" como Flashbrick.
        // Flashbrick se inscreve como observer de Translation.
        // A cada submissão, onPracticeSubmitted() recalcula o SRI automaticamente.

        Learner learner = Learner.createAccount(
                "alan", "Alan de Ravila", "alan@email.com", Language.PT, Language.EN);

        Flashbrick flashbrick = new Flashbrick(learner, word);
        System.out.println("Flashbrick inicial: " + flashbrick);

        Translation translation = new Translation(
                "Translate: 'serendipity'", Language.EN, Level.C1, "serendipidade");
        translation.subscribe(flashbrick); // inscreve Flashbrick como observer

        System.out.println("→ Acertou!");
        translation.evaluatePractice(true);
        System.out.println("  " + flashbrick);

        System.out.println("→ Acertou!");
        translation.evaluatePractice(true);
        System.out.println("  " + flashbrick);

        System.out.println("→ Errou.");
        translation.evaluatePractice(false);
        System.out.println("  " + flashbrick);

        System.out.println("→ Acertou!");
        translation.evaluatePractice(true);
        System.out.println("  " + flashbrick);

        // ─────────────────────────────────────────────────────────────────────

        separador("4. COMPOSITE");

        // BidirectionalTranslation agrega Translation, ReverseTranslation e ReverseSpeech
        // como se fossem uma única prática. O cliente chama evaluatePractice() no nó
        // composto e ele propaga para todos os filhos recursivamente.
        // O mesmo Flashbrick observa o nó composto — recebe N+1 notificações.

        Translation t2 = new Translation(
                "Traduzir: 'serendipity' → PT", Language.EN, Level.C1, "serendipidade");
        ReverseTranslation rt = new ReverseTranslation(
                "Traduzir: 'serendipidade' → EN", Language.PT, Level.C1, "serendipity");
        ReverseSpeech rs = new ReverseSpeech(
                "Falar em EN: 'acaso feliz'", Language.EN, Level.C1);

        // Cria o nó composto e enfileira as práticas filhas
        BidirectionalTranslation bidirectional = new BidirectionalTranslation(
                "Tradução bidirecional completa", Language.EN, Level.C1);
        bidirectional.enqueueTask(t2);
        bidirectional.enqueueTask(rt);
        bidirectional.enqueueTask(rs);

        // Flashbrick observa o NÓ COMPOSTO — as folhas t2/rt/rs não têm observer próprio.
        // Ao chamar evaluatePractice() no composto, ele propaga para as folhas (que nada notificam)
        // e ao final chama notifyObservers() em si mesmo → Flashbrick recebe 1 notificação.
        bidirectional.subscribe(flashbrick);

        System.out.println("SRI antes do Composite: "
                + String.format("%.2f", flashbrick.getSynapticRetentionIndex()));
        System.out.println("→ evaluatePractice(true) no nó composto — propaga para 3 filhos:");
        bidirectional.evaluatePractice(true);
        System.out.println("  " + flashbrick);

        // Demonstra nextPractice() — percorre a fila interna do composto
        separador("COMPOSITE nextPractice()");
        BidirectionalTranslation bd2 = new BidirectionalTranslation(
                "Sequência de prática", Language.EN, Level.B2);
        bd2.enqueueTask(new Translation("EN→PT: difficult", Language.EN, Level.B2, "difícil"));
        bd2.enqueueTask(new ReverseTranslation("PT→EN: difícil", Language.PT, Level.B2, "difficult"));

        Practice next;
        while ((next = bd2.nextPractice()) != null) {
            System.out.println("  Próxima prática: " + next.getInstruction());
        }
        System.out.println("  (Fila esgotada — nextPractice() retornou null)");
    }

    private static void separador(String titulo) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  " + titulo);
        System.out.println("═".repeat(60));
    }
}
