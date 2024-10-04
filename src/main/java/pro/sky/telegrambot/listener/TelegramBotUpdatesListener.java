package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    //method to send welcome message
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
                    logger.info("Processing update: {}", update);
                    long chatId = update.message().chat().id();
                    String message = update.message().text();
                    if (message.equals(null)) {
                        logger.info("Null message was sent", chatId);
                        return;
                    }
                    if (message.equals("/start")) {
                        logger.info("Start message", chatId);
                        startMessageReceived(chatId, update.message().chat().firstName());
                    }

                });

                // Process your updates here
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void startMessageReceived(Long chatId, String userName) {
        String responseMessage = "Welcome, " + userName + ". To save task notification";
    }

}
