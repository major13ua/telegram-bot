package ua.major13.telegram.bot.processor;

import com.google.common.collect.Lists;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.telegram.TelegramConstants;
import org.apache.camel.impl.DefaultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.major13.telegram.bot.core.ChatManager;
import ua.major13.telegram.bot.core.JokeService;
import java.util.List;

@Component
public class ScheduleBot {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleBot.class);

    @Autowired
    private ChatManager chatManager;

    @Autowired
    private JokeService jokeService;

    public List<Message> getSubscriptions(Exchange exchange){
        final int size = chatManager.list().size();
        LOG.info("Active subscriptions count: " + size);
        List<Message> chatList = Lists.newLinkedList();
        if (size > 0) {
            String joke = jokeService.getJoke();
            chatManager.list().values().stream().forEach(
                    chat -> {
                        Message msg = new DefaultMessage();
                        msg.setHeader(TelegramConstants.TELEGRAM_CHAT_ID, chat.getId());
                        msg.setBody(joke);
                        chatList.add(msg);
                    }
            );
        }
        return chatList;
    }

}
