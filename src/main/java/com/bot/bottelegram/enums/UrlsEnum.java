package com.bot.bottelegram.enums;

public enum UrlsEnum {
    TEKNAUTAS("https://www.elconfidencial.com/tecnologia/");

    private String url;

    UrlsEnum(String url) { this.url = url; }

    public String getUrl() { return url; }
}
