package ua.major13.telegram.bot.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ua.major13.telegram.bot.processor.ScheduleBot;

@Component
public class ScheduleCamelRouter extends RouteBuilder {

    @Autowired
    private ScheduleBot scheduledBot;

    @Value("${joke.schedule}")
    private String jokeSchedule;

    @Override
    public void configure() throws Exception {
        from(jokeSchedule)
        .split().method("scheduleBot", "getSubscriptions")
        .to("telegram:bots");
    }
}
