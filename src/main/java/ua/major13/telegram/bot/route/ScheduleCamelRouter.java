package ua.major13.telegram.bot.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.major13.telegram.bot.processor.ScheduleBot;

@Component
public class ScheduleCamelRouter extends RouteBuilder {

    @Autowired
    private ScheduleBot scheduledBot;

    @Override
    public void configure() throws Exception {

        from("timer://test?fixedRate=true&period=60000")
        .bean(scheduledBot)
        .to("telegram:bots");

    }
}
