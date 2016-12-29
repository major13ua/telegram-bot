package ua.major13.telegram.bot.processor;

import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.TelegramConstants;
import org.apache.camel.component.telegram.model.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.major13.telegram.bot.core.ChatManager;

@Component
public class ScheduleBot {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleBot.class);

    @Autowired
    private ChatManager chatManager;
    
    public String process(Exchange exchange) {

        Chat chat = chatManager.list().values().stream().findFirst().orElse(null);

        if (chat != null) {
            exchange.getIn().setHeader(TelegramConstants.TELEGRAM_CHAT_ID, chat.getId());
            return "My time: " + System.currentTimeMillis();
        }

        return null;
    }

}
