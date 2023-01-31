package mate.academy.javabot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MateAcademyBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "MateAcademyMarathonBotKyslyi_bot";
    }

    @Override
    public String getBotToken() {
        return "5963552222:AAFPn6VSQqnIQ-NsNXlO0ZnycU9lAeZp2zI";
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        System.out.println("message received " + message.getText());
        System.out.println("chat id " + message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Hello user! I received your massege: " + message.getText());
        sendMessage.setChatId(message.getChatId());

        if (message.getText().equals("/start")) {
            sendMessage.enableMarkdown(true);
            sendMessage.setReplyMarkup(getMenuKeyboard());
        }

        if (message.getText().equals("breakfast")) {
            String menu = "Breakfast menu!\n";
            menu = menu + "1. Omelet\n";
            menu = menu + "2. Tea\n";
            menu = menu + "3. Cookies\n";
            sendMessage.setText(menu);
        }

        if (message.getText().equals("dinner")) {
            String menu = "Dinner menu!\n";
            menu = menu + "1. Soup\n";
            menu = menu + "2. Juice\n";
            menu = menu + "3. Steak\n";
            sendMessage.setText(menu);
        }

        if (message.getText().equals("lunch")) {
            String text = "menu for lunch is not create yet \uD83E\uDD13";
            sendMessage.setText(text);
        }

        if (message.getText().equals("supper")) {
            String text = "menu for supper is not create yet";
            sendMessage.setText(text);
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private ReplyKeyboardMarkup getMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("breakfast");
        keyboardRow.add("dinner");

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("lunch \uD83E\uDD13");
        keyboardSecondRow.add("supper");

        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }

}
