import { useMemo, useState } from "react";
import "./App.css";

const SESSION_KEY = "flashbrix_user";

const SAMPLE_TEXTS = [
  {
    id: "t1",
    label: "BOOK / fiction",
    source: "The Left Hand of Darkness — Ursula K. Le Guin",
    lang: "EN",
    body:
      "It is good to have an end to journey toward; but it is the journey that matters, in the end."
  },
  {
    id: "t2",
    label: "YOUTUBE / transcript",
    source: "Mini doc — \"How spaced repetition actually works\"",
    lang: "EN",
    body:
      "Your brain forgets on a predictable curve. Review right before you would forget, and the interval grows."
  },
  {
    id: "t3",
    label: "INSTAGRAM / on-screen text",
    source: "@learn_vocab · reel caption",
    lang: "PT",
    body:
      "Marcar uma palavra no meio da leitura vira flashcard — o contexto fica grudado na memória."
  },
  {
    id: "t4",
    label: "BOOK / essay",
    source: "Sidewalk — Mitchell Duneier",
    lang: "EN",
    body:
      "Public characters are those who receive a certain amount of deference and attention from others."
  },
  {
    id: "t5",
    label: "YOUTUBE / interview clip",
    source: "Language learner podcast #042",
    lang: "ES",
    body:
      "No se trata de acumular palabras sueltas; se trata de atar cada unidad a una situación vivida."
  }
];

const LANGUAGES = ["EN", "PT", "ES"];
const CEFR_LEVELS = ["A1", "A2", "B1", "B2", "C1", "C2"];
const SOURCE_TYPES = ["BOOK", "YOUTUBE_VIDEO", "INSTAGRAM_REEL"];
const UNIT_KINDS = [
  { value: "WORD", label: "Word" },
  { value: "PHRASE", label: "Phrase" },
  { value: "COLLOCATION", label: "Collocation" },
  { value: "CLAUSE", label: "Clause" }
];

function buildPreview({ rawText, language, cefr, sourceType, unitKind }) {
  const term = rawText.trim();
  return {
    kind: unitKind,
    term,
    language,
    cefr,
    extractionContext: {
      rawText: term,
      language,
      cefr,
      sourceType
    },
    note: "UI preview — placeholder até REST no backend Java."
  };
}

function readStoredUser() {
  try {
    return sessionStorage.getItem(SESSION_KEY);
  } catch {
    return null;
  }
}

function LoginScreen({ onLogin }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function handleSubmit(e) {
    e.preventDefault();
    const u = username.trim();
    if (!u) return;
    onLogin(u);
  }

  return (
    <div className="login-wrap">
      <div className="window login-window">
        <div className="title-bar">
          <span className="title-bar__text">FLASHBRIX.EXE — access</span>
        </div>
        <div className="window__body">
          <p className="login-tagline">Build fluency.</p>
          <form className="login-form" onSubmit={handleSubmit}>
            <label className="wire-label">
              USERNAME_
              <input
                className="wire-input"
                value={username}
                onChange={(ev) => setUsername(ev.target.value)}
                autoComplete="username"
                maxLength={64}
              />
            </label>
            <label className="wire-label">
              PASSWORD_
              <input
                className="wire-input"
                type="password"
                value={password}
                onChange={(ev) => setPassword(ev.target.value)}
                autoComplete="current-password"
              />
            </label>
            <p className="wire-hint">
              (demo: qualquer username; senha ignorada)
            </p>
            <div className="login-actions">
              <button type="submit" className="wire-button wire-button--default">
                OK
              </button>
              <button
                type="button"
                className="wire-button"
                onClick={() => {
                  setUsername("");
                  setPassword("");
                }}
              >
                CLEAR
              </button>
            </div>
          </form>
        </div>
      </div>
      <pre className="ascii-deco">{`
  +--------+
  | [>]    |  v0.1 wireframe shell
  +--------+
      `}</pre>
    </div>
  );
}

function HomeScreen({ username, onLogout }) {
  const [view, setView] = useState("sources");
  const [rawText, setRawText] = useState("make a decision");
  const [language, setLanguage] = useState("EN");
  const [cefr, setCefr] = useState("B1");
  const [sourceType, setSourceType] = useState("BOOK");
  const [unitKind, setUnitKind] = useState("COLLOCATION");
  const [result, setResult] = useState(() =>
    buildPreview({
      rawText: "make a decision",
      language: "EN",
      cefr: "B1",
      sourceType: "BOOK",
      unitKind: "COLLOCATION"
    })
  );

  const summary = useMemo(() => {
    const k = UNIT_KINDS.find((u) => u.value === unitKind)?.label ?? unitKind;
    return `${k}: "${rawText.trim() || "…"}" | ${language} | ${cefr} | ${sourceType}`;
  }, [cefr, language, rawText, sourceType, unitKind]);

  function handleExtractSubmit(event) {
    event.preventDefault();
    setResult(
      buildPreview({ rawText, language, cefr, sourceType, unitKind })
    );
  }

  return (
    <div className="app-shell">
      <header className="top-bar">
        <div className="top-bar__brand">
          <span className="top-bar__name">FLASHBRIX</span>
          <span className="top-bar__sep">|</span>
          <span>user: {username}</span>
        </div>
        <div className="top-bar__actions">
          <button
            type="button"
            className={
              "wire-button wire-button--small" +
              (view === "sources" ? " wire-button--active" : "")
            }
            onClick={() => setView("sources")}
          >
            TEXT_SOURCES
          </button>
          <button
            type="button"
            className={
              "wire-button wire-button--small" +
              (view === "extract" ? " wire-button--active" : "")
            }
            onClick={() => setView("extract")}
          >
            EXTRACT_UNIT
          </button>
          <button type="button" className="wire-button wire-button--small" onClick={onLogout}>
            LOGOUT
          </button>
        </div>
      </header>

      <main className="main-area">
        {view === "sources" && (
          <div className="window main-window">
            <div className="title-bar">
              <span className="title-bar__text">context — sample texts (read-only)</span>
            </div>
            <div className="window__body window__body--padded">
              <p className="section-intro">
                Instâncias de texto para marcar trechos depois. Fonte / idioma / trecho.
              </p>
              <ul className="text-instance-list">
                {SAMPLE_TEXTS.map((item) => (
                  <li key={item.id} className="text-instance">
                    <div className="text-instance__meta">
                      <span className="text-instance__label">{item.label}</span>
                      <span className="text-instance__lang">[{item.lang}]</span>
                    </div>
                    <div className="text-instance__source">{item.source}</div>
                    <blockquote className="text-instance__body">{item.body}</blockquote>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        )}

        {view === "extract" && (
          <div className="extract-layout">
            <div className="window">
              <div className="title-bar">
                <span className="title-bar__text">extract — lexical unit (preview)</span>
              </div>
              <div className="window__body window__body--padded">
                <form className="extract-form" onSubmit={handleExtractSubmit}>
                  <label className="wire-label">
                    MARKED_TEXT_
                    <textarea
                      className="wire-input wire-input--tall"
                      value={rawText}
                      onChange={(e) => setRawText(e.target.value)}
                      rows={4}
                    />
                  </label>
                  <div className="extract-grid">
                    <label className="wire-label">
                      KIND_
                      <select
                        className="wire-input"
                        value={unitKind}
                        onChange={(e) => setUnitKind(e.target.value)}
                      >
                        {UNIT_KINDS.map((opt) => (
                          <option key={opt.value} value={opt.value}>
                            {opt.label}
                          </option>
                        ))}
                      </select>
                    </label>
                    <label className="wire-label">
                      LANG_
                      <select
                        className="wire-input"
                        value={language}
                        onChange={(e) => setLanguage(e.target.value)}
                      >
                        {LANGUAGES.map((code) => (
                          <option key={code} value={code}>
                            {code}
                          </option>
                        ))}
                      </select>
                    </label>
                    <label className="wire-label">
                      CEFR_
                      <select
                        className="wire-input"
                        value={cefr}
                        onChange={(e) => setCefr(e.target.value)}
                      >
                        {CEFR_LEVELS.map((level) => (
                          <option key={level} value={level}>
                            {level}
                          </option>
                        ))}
                      </select>
                    </label>
                    <label className="wire-label">
                      SOURCE_
                      <select
                        className="wire-input"
                        value={sourceType}
                        onChange={(e) => setSourceType(e.target.value)}
                      >
                        {SOURCE_TYPES.map((src) => (
                          <option key={src} value={src}>
                            {src.replaceAll("_", " ")}
                          </option>
                        ))}
                      </select>
                    </label>
                  </div>
                  <button type="submit" className="wire-button wire-button--default">
                    BUILD_PREVIEW
                  </button>
                </form>
              </div>
            </div>
            <div className="window">
              <div className="title-bar">
                <span className="title-bar__text">output — json</span>
              </div>
              <div className="window__body window__body--padded">
                <p className="output-summary">{summary}</p>
                <pre className="wire-pre">{JSON.stringify(result, null, 2)}</pre>
              </div>
            </div>
          </div>
        )}
      </main>
    </div>
  );
}

export default function App() {
  const [user, setUser] = useState(() => readStoredUser());

  function handleLogin(username) {
    try {
      sessionStorage.setItem(SESSION_KEY, username);
    } catch {
      /* ignore */
    }
    setUser(username);
  }

  function handleLogout() {
    try {
      sessionStorage.removeItem(SESSION_KEY);
    } catch {
      /* ignore */
    }
    setUser(null);
  }

  if (!user) {
    return <LoginScreen onLogin={handleLogin} />;
  }

  return <HomeScreen username={user} onLogout={handleLogout} />;
}
