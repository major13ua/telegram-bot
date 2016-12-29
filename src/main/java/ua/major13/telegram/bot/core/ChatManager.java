package ua.major13.telegram.bot.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.component.telegram.model.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

@Component
public class ChatManager {

    private static final Logger LOG = LoggerFactory.getLogger(ChatManager.class);

    private Map<String, Chat> chats = new ConcurrentHashMap<>();
    
    public void put(Chat chat) {
        LOG.info("Creating chat : " + chat.getId());
        chats.put(chat.getId(), chat);
    }

    public boolean contains (Chat chat) {
        return chats.containsKey(chat.getId());
    }

    public void removeChat (Chat chat) {
        LOG.info("Removing chat : " + chat.getId());
        chats.remove(chat.getId());
    }

    public Map<String, Chat> list() {
        return ImmutableMap.<String, Chat>builder().putAll(chats).build();
    }
}
