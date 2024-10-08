package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
//Вводим сущность (модель), связанной с таблицей в базе данных
@Entity

public class NotificationTask {
//    нам потребуется иметь первичный ключ в таблице, идентификатор чата,
//    в который нужно отправить уведомление, текст уведомления и дату+время,
//    когда требуется отправить уведомление.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;    //первичный ключ в таблице
    @Column(name="chat_id")
    private Long chatId;    //идентификатор чата
    @Column(name="message_Text")
    private String messageText;         //текст уведомления
    @Column(name="notification_Date_Time")
    private LocalDateTime notificationDateTime;     //дату+время напоминания

    public NotificationTask(Long id, Long chatId, String messageText, LocalDateTime notificationDateTime) {
        this.id = id;
        this.chatId = chatId;
        this.messageText = messageText;
        this.notificationDateTime = notificationDateTime;
    }

    public NotificationTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }

    public void setNotificationDateTime(LocalDateTime notificationDateTime) {
        this.notificationDateTime = notificationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationTask)) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && Objects.equals(messageText, that.messageText) && Objects.equals(notificationDateTime, that.notificationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, messageText, notificationDateTime);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", messageText='" + messageText + '\'' +
                ", notificationDateTime=" + notificationDateTime +
                '}';
    }
}
