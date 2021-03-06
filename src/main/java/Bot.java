import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot {
    public String getBotUsername() {
        return "more_weather_bot";
    }

    public String getBotToken() {
        return "1625857121:AAEWcKDYWiAhtRrj7-KLPcKoKqWA9DSnez0";
    }

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()&&update.getMessage().hasText()){
            Message message = update.getMessage();
            String text = message.getText();

            System.out.println(text);
            SendMessage answer = new SendMessage();

            answer.setChatId(message.getChatId().toString());
            if (text.equals("/help")){
                answer.setText("Введите название вашего города. А в ответ бот пришлёт вам погоду:)");
            }else if(text.equals("/start")){
                answer.setText("Введите название вашего города. А в ответ бот пришлёт вам погоду:)");
            }else {
                try {
                    answer.setText(Weather.getWeather(text));
                } catch (IOException e) {
                    e.printStackTrace();
                    answer.setText("Не найден город по вашему запросу!");
                }}
                try {
                    //sending answer to bot
                    execute(answer);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

        }
    }
}
