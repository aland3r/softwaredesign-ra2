package com.flashbrix.user;

import com.flashbrix.language.Language;

/**
 * Representa o aprendiz — usuário ativo que pratica idiomas no Flashbrix.
 * Possui idioma nativo (firstLanguage) e idioma alvo (targetLanguage).
 */
public class Learner {

    private final long id;
    private String username;
    private String displayName;
    private String email;
    private final Language firstLanguage;
    private Language targetLanguage;

    private static long nextId = 1;

    private Learner(String username, String displayName, String email,
                    Language firstLanguage, Language targetLanguage) {
        this.id = nextId++;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.firstLanguage = firstLanguage;
        this.targetLanguage = targetLanguage;
    }

    public static Learner createAccount(String username, String displayName, String email,
                                        Language firstLanguage, Language targetLanguage) {
        return new Learner(username, displayName, email, firstLanguage, targetLanguage);
    }

    public void updateProfile(String displayName, String email) {
        this.displayName = displayName;
        this.email = email;
    }

    public void setTargetLanguage(Language language) {
        this.targetLanguage = language;
    }

    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getDisplayName() { return displayName; }
    public String getEmail() { return email; }
    public Language getFirstLanguage() { return firstLanguage; }
    public Language getTargetLanguage() { return targetLanguage; }

    @Override
    public String toString() {
        return "Learner{username='" + username + "', " + firstLanguage + " → " + targetLanguage + "}";
    }
}
