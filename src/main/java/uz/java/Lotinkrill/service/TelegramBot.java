package uz.java.Lotinkrill.service;

import com.ibm.icu.text.Transliterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.java.Lotinkrill.config.Botconfig;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private Botconfig botconfig;

    private Transliterator transliterator;
    @Override
    public String getBotUsername() {
        return botconfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return botconfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {



        if(update.getMessage().hasText()) {
            String id = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();
            if(text.equals("/start")){
                sent_message(id,"Assalomu-alaykum krill-lotin botida xush kelibsiz. Krill yozuvidagi yoki lotin yozuvidagi text kiriting:");
            }else{
                language_method(id,text);
            }

        }
    }


    private void language_method(String id,String text){
        if (text.codePointAt(0) >= 65 && text.codePointAt(0) <= 122 && text.codePointAt(0) != 119) {
            transliterator = Transliterator.getInstance("Latin-Cyrillic");
            sent_message(id,transliterator.transliterate(text));
        } else if(text.codePointAt(0) >= 1039 && text.codePointAt(0) <= 1103){
            transliterator = Transliterator.getInstance("Cyrillic-Latin");
            sent_message(id,transliterator.transliterate(text));
        }else {
            sent_message(id,text);
        }
    }

    private void sent_message(String id,String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
