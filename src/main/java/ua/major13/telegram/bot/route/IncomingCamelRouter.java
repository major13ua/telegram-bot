package ua.major13.telegram.bot.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.major13.telegram.bot.processor.Bot;

@Component
public class IncomingCamelRouter extends RouteBuilder {

    @Autowired
    private Bot bot;

    @Override
    public void configure() throws Exception {

        from("telegram:bots")
        .bean(bot)
        .to("telegram:bots");

    }
}
