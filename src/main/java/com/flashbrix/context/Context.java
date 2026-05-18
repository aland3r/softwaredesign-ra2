package com.flashbrix.context;

import com.flashbrix.language.Level;
import com.flashbrix.language.Language;

import java.time.LocalDateTime;

public abstract class Context {
    private int id;
    private String title;
    private Language language;
    private Level level;
    private LocalDateTime createdAt;
    private Source source;

    public Context(String title, Language language, Level level, Source source) {
        this.title = title;
        this.language = language;
        this.level = level;
        this.source = source;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Context{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language=" + language +
                ", level=" + level +
                ", createdAt=" + createdAt +
                ", source=" + source +
                '}';
    }
}

