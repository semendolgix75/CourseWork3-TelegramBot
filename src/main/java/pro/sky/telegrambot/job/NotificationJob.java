package pro.sky.telegrambot.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.service.MessageSender;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
//Класс который открывает через период и проверяет совпадения времени заданого с текущим
public class NotificationJob {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final NotificationTaskRepository notificationTaskRepository;
    private final MessageSender messageSender;

    public NotificationJob(NotificationTaskRepository notificationTaskRepository, MessageSender messageSender) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.messageSender = messageSender;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void sendNotification() {
        LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        logger.info("Job start for date time {}",currentDateTime);

        List<NotificationTask> notificationTasks = notificationTaskRepository.findAllByNotificationDateTime(currentDateTime);
        logger.info("{} task have been found",notificationTasks.size());

        for (NotificationTask notificationTask : notificationTasks) {
            messageSender.send(notificationTask.getChatId(),"Напоминание!"+notificationTask.getMessageText()
            );
            logger.info("Notification with id {} has been successfully sent", notificationTask.getChatId());
        }
        logger.info("Job finished");
    }
}
