# Checklist de Entrega — Flashbrix (RA2)
**Prazo:** 19/05/2026

---

## Entrega no Canvas

- [ ] Documento com nome da equipe + explicação breve do projeto
- [ ] Diagrama de classes (`docs/FlashbrixUmlClassDiagram-v1.pdf`)
- [ ] Relatório com justificativa de cada padrão GoF (`docs/padroes-gof.md`)
- [ ] Código-fonte (link do repositório ou ZIP)
- [ ] Reflexão sobre as maiores dificuldades

---

## Código — padrões implementados

- [x] **Factory Method** — `vocabulary/factory/` (LexicalUnitFactory + 4 fábricas)
- [x] **Strategy** — `context/strategy/` + `Sentence.expandSelection()`
- [x] **Observer** — `practice/observer/RetentionObserver` + `Practice` + `Flashbrick`
- [x] **Composite** — `practice/BidirectionalTranslation` (enqueueTask/dequeueTask)
- [x] **Main.java** demonstra os 4 padrões em sequência (`./gradlew run`)
- [x] Comentários em português explicando cada padrão

---

## Relatório (`docs/padroes-gof.md`)

- [x] Factory Method — problema, solução, classes envolvidas
- [x] Strategy — problema, solução, classes envolvidas
- [x] Observer — problema, solução, classes envolvidas
- [x] Composite — problema, solução, classes envolvidas
- [x] Facade — justificativa para **não aplicar**
- [x] Singleton — justificativa para **não aplicar**

---

## Arquivos para deletar antes de zipar/enviar

> O `.gitignore` já ignora `docs/`, `.claude/`, `CLAUDE.md`, `frontend/node_modules/`, `frontend/dist/`.
> Os arquivos abaixo podem ser **deletados manualmente** do repositório (são legados ou não fazem parte da entrega):

### Pacotes inteiros (pasta + conteúdo)
- `src/main/java/com/flashbrix/domain/` — design antigo, migrado para `vocabulary/`
- `src/main/java/com/flashbrix/context/transcript/` — substituído por `context/Sentence.java`

### Arquivos avulsos legados
- `src/main/java/com/flashbrix/context/UserContext.java`
- `src/main/java/com/flashbrix/context/UserVideoContext.java`
- `src/main/java/com/flashbrix/user/User.java`
- `src/main/java/com/flashbrix/user/UserVocabulary.java`
- `src/main/java/com/flashbrix/practice/PracticeResult.java`
- `src/main/java/com/flashbrix/practice/FathomQuestion.java`
- `src/main/java/com/flashbrix/practice/Gap.java`

### Frontend (fica no repo, mas não vai no Canvas)
- `frontend/` — pode deixar no repositório; o Canvas recebe só o backend Java

---

## Verificação final

```
./gradlew clean run
```
Saída esperada: 4 seções (Factory Method, Strategy, Observer, Composite) sem erros.
