package com.flashbrix.context;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;

public class YoutubeVideo extends Context {
    private String url;
    private int durationSeconds;
    private String contentCreator;

    public YoutubeVideo(String title, Language language, Level level, String url, int durationSeconds, String contentCreator) {
        super(title, language, level, Source.YOUTUBE);
        this.url = url;
        this.durationSeconds = durationSeconds;
        this.contentCreator = contentCreator;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getContentCreator() {
        return contentCreator;
    }

    public void setContentCreator(String contentCreator) {
        this.contentCreator = contentCreator;
    }

    @Override
    public String toString() {
        return "YoutubeVideo{" +
                super.toString() +
                ", url='" + url + '\'' +
                ", durationSeconds=" + durationSeconds +
                ", contentCreator='" + contentCreator + '\'' +
                '}';
    }
}

