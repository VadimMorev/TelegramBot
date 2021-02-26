import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    public String getBotUsername() {
        return "more_weather_bot";
    }

    public String getBotToken() {
        return "1625857121:AAEWcKDYWiAhtRrj7-KLPcKoKqWA9DSnez0";
    }

    public void onUpdateReceived(Update update) {

    }
}
