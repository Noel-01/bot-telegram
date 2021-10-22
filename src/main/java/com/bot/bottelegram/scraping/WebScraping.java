package com.bot.bottelegram.scraping;

import com.bot.bottelegram.enums.UrlsEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebScraping {

    public List<String> webScraping() {

        try {
            // Descargar el html de esta pagina
            Document page = Jsoup.connect(UrlsEnum.TEKNAUTAS.getUrl()).get();


            // coger solo las urls que vienen en el html con href
            Elements pageElements = page.select("a[href]");


            // filtrar por las urls que empieza de las siguiente manera
            return pageElements.stream()
                    .filter(p -> p.attr("href").contains(UrlsEnum.TEKNAUTAS.getUrl()))
                    .filter(p -> !p.attr("href").equalsIgnoreCase(UrlsEnum.TEKNAUTAS.getUrl()))
                    .map(e -> e.attr("href"))
                    .limit(3)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
