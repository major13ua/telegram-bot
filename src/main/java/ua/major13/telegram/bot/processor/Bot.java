package ua.major13.telegram.bot.processor;

import com.google.common.base.Strings;
import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ua.major13.telegram.bot.core.ChatManager;
import ua.major13.telegram.bot.core.JokeService;

@Component
public class Bot {
    private static final Logger LOG = LoggerFactory.getLogger(Bot.class);

    @Autowired
    private ChatManager chatManager;

    @Autowired
    private JokeService jokeService;

    @Value("${bot.daddy}")
    private String botDaddy;

    private enum Commands {
        HELP ("/help"),
        JOKE ("/joke"),
        DADDY ("/daddy"),
        SUBSCRIBE ("/subscribe"),
        UNSUBSCRIBE ("/cancel");

        private String command;

        Commands(String command){
            this.command = command;
        }

        public String command() {
            return this.command;
        }

    }

    private static String HELP_PAGE =
            Commands.DADDY.command() + " - Who's my Daddy?\n" +
                    Commands.JOKE.command() + " - tell a joke\n" +
                    Commands.SUBSCRIBE.command() + " - send 1 joke per minute\n" +
                    Commands.UNSUBSCRIBE.command() + " - cancel subscription\n" +
                    Commands.HELP.command() + " - help page";

    private static String PATTERN = "XXX";

    private static String DADDY_PAGE = "XXX is my Daddy!";

    private String handleCreateChat() {
        return  "Added to Q";
    }

    private String handleRemoveChat() {
        return  "Removed from Q";
    }

    private Commands parseMessage(String message) {
        for(Commands item: Commands.values()){
            if(item.command().equals(message)){
                return item;
            }
        }
        return Commands.HELP;
    }

    public String process(Exchange exchange) {

        IncomingMessage message = exchange.getIn().getBody(IncomingMessage.class);
        if (message == null) {
            return null; // skip non-text messages
        }

        LOG.info("Get message from={} message={}", message.getFrom().getUsername(), message.getText());

        if (Strings.isNullOrEmpty(message.getText())) {
            return HELP_PAGE;
        }

        Commands command = parseMessage(message.getText());

        switch (command) {
            case DADDY: return DADDY_PAGE.replace(PATTERN, botDaddy);
            case JOKE:  return jokeService.getJoke();
            case SUBSCRIBE : return handleCreateChat();
            case UNSUBSCRIBE : return handleRemoveChat();
            case HELP : return HELP_PAGE;
            default : return "Unknown command ["+command+"]. Write /help";
        }

    }

}
