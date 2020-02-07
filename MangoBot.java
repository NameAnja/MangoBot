import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MangoBot extends TelegramLongPollingBot {

    private String[] names = {"Anja", "Melinda", "Lukas", "Dominik"};
    Long anja = 31316046l;
    Long lukas = 839422729l;
    Long dominik = 78597075l;
    Long melinda = 874320469l;
    Map<Long, String> tag;
    //String tag = "heute";

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long id = update.getMessage().getChatId();
            tag = new HashMap<>();

            switch (update.getMessage().getText()) {

                case "/Zusammensetzung": {
                    generateCooks(id);
                    break;
                }
                case "/Horoskop": {
                    horoskop(id);
                    break;
                }
                case "/MensaSued":{
                    suedmensa(id);
                    break;
                }

                default:
                    SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                            .setChatId(update.getMessage().getChatId())
                            .setText("Ich antworte bisher nur auf das Wort '/Horoskop' und '/MensaSued'.");
                    try {
                        execute(message); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
            }
        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("heutem")||call_data.equals("morgenm")){
                suedmensamenue(call_data,chat_id);
            }

            if (call_data.equals("Jungfrau")||call_data.equals("Waage")||call_data.equals("Stier")||call_data.equals("Steinbock")
            ||call_data.equals("Wassermann")||call_data.equals("Fische")||call_data.equals("Skorpion")||call_data.equals("Schuetze")
            ||call_data.equals("Krebs")||call_data.equals("Zwillinge")||call_data.equals("Widder")||call_data.equals("Loewe")) {
                    sternzeichen(chat_id,tag.get(chat_id),call_data);
            }

            if (call_data.equals("heute")){
                tag.put(chat_id,"heute");
                System.out.println("hi");
                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(chat_id)
                        .setText("Welches Sternzeichen?");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Jungfrau ♍️ ").setCallbackData("Jungfrau"));
                rowInline.add(new InlineKeyboardButton().setText("Waage ♎️ ").setCallbackData("Waage"));
                rowInline.add(new InlineKeyboardButton().setText("Stier ♉️ ").setCallbackData("Stier"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                rowInline2.add(new InlineKeyboardButton().setText("Steinbock ♑️ ").setCallbackData("Steinbock"));
                rowInline2.add(new InlineKeyboardButton().setText("Wassermann ♒️ ").setCallbackData("Wassermann"));
                rowInline2.add(new InlineKeyboardButton().setText("Fische ♓️️ ").setCallbackData("Fische"));
                rowsInline.add(rowInline2);

                List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
                rowInline3.add(new InlineKeyboardButton().setText("Skorpion ♏️️ ").setCallbackData("Skorpion"));
                rowInline3.add(new InlineKeyboardButton().setText("Schütze ♐️ ").setCallbackData("Schuetze"));
                rowInline3.add(new InlineKeyboardButton().setText("Krebs ♋️ ").setCallbackData("Krebs"));
                rowsInline.add(rowInline3);

                List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
                rowInline4.add(new InlineKeyboardButton().setText("Löwe ♌️ ").setCallbackData("Loewe"));
                rowInline4.add(new InlineKeyboardButton().setText("Widder ️♈️ ").setCallbackData("Widder"));
                rowInline4.add(new InlineKeyboardButton().setText("Zwillinge ♊️️ ").setCallbackData("Zwillinge"));
                rowsInline.add(rowInline4);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (call_data.equals("morgen")){
                tag.put(chat_id,"morgen");
                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(chat_id)
                        .setText("Welches Sternzeichen?");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Jungfrau ♍️ ").setCallbackData("Jungfrau"));
                rowInline.add(new InlineKeyboardButton().setText("Waage ♎️ ").setCallbackData("Waage"));
                rowInline.add(new InlineKeyboardButton().setText("Stier ♉️ ").setCallbackData("Stier"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                rowInline2.add(new InlineKeyboardButton().setText("Steinbock ♑️ ").setCallbackData("Steinbock"));
                rowInline2.add(new InlineKeyboardButton().setText("Wassermann ♒️ ").setCallbackData("Wassermann"));
                rowInline2.add(new InlineKeyboardButton().setText("Fische ♓️️ ").setCallbackData("Fische"));
                rowsInline.add(rowInline2);

                List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
                rowInline3.add(new InlineKeyboardButton().setText("Skorpion ♏️️ ").setCallbackData("Skorpion"));
                rowInline3.add(new InlineKeyboardButton().setText("Schütze ♐️ ").setCallbackData("Schuetze"));
                rowInline3.add(new InlineKeyboardButton().setText("Krebs ♋️ ").setCallbackData("Krebs"));
                rowsInline.add(rowInline3);

                List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
                rowInline4.add(new InlineKeyboardButton().setText("Löwe ♌️ ").setCallbackData("Loewe"));
                rowInline4.add(new InlineKeyboardButton().setText("Widder ️♈️ ").setCallbackData("Widder"));
                rowInline4.add(new InlineKeyboardButton().setText("Zwillinge ♊️️ ").setCallbackData("Zwillinge"));
                rowsInline.add(rowInline4);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);


                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void suedmensamenue(String wann, Long id ){
        Calendar calendar = Calendar.getInstance();
        try {
            if(wann.equals("morgenm")){

                calendar.add(Calendar.DAY_OF_YEAR, 4);
            }else {
                calendar.add(Calendar.DAY_OF_YEAR, 3);
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            Document doc = Jsoup.connect("https://www.stw-rw.de/de/mensen-und-cafeterien/speiseplaene/"+year+"-"+month+"-"+day+".html").get();
        String textContents = doc.select("table").first().text();

        String[] data = textContents.split("Bed.: ");
        String menue = data[0];
        for (int i=1; i<data.length-1;i++){
            String bit = data[i];
            menue = menue + "\n" + bit.substring(20, bit.length());
        }

        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(id)
                .setText(menue);

            execute(message); // Call method to send the message
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void suedmensa(Long id){
        try {
            SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(id)
                    .setText("Von wann?");
            InlineKeyboardMarkup markupInline1 = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline1 = new ArrayList<>();
            List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
            rowInline1.add(new InlineKeyboardButton().setText("heute").setCallbackData("heutem"));
            rowInline1.add(new InlineKeyboardButton().setText("morgen").setCallbackData("morgenm"));
            rowsInline1.add(rowInline1);
            // Add it to the message
            markupInline1.setKeyboard(rowsInline1);
            message1.setReplyMarkup(markupInline1);
            execute(message1); // Call method to send the message


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void generateCooks(Long id) {
        System.out.println("shuffled by " + id);
        shuffleArray(names);
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(id)
                .setText(names[0] + " kocht Hauptspeise mit " + names[1] + "\n und die Nachspeise machen " + names[2]
                        + " und " + names[3]);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private static void shuffleArray(String[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private void horoskop(Long id) {

        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(id)
                .setText("Von wann?");
        InlineKeyboardMarkup markupInline1 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(new InlineKeyboardButton().setText("heute").setCallbackData("heute"));
        rowInline1.add(new InlineKeyboardButton().setText("morgen").setCallbackData("morgen"));
        rowsInline1.add(rowInline1);
        // Add it to the message
        markupInline1.setKeyboard(rowsInline1);
        message1.setReplyMarkup(markupInline1);
        try {
            execute(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage horoskopToMessage(String text, Long id) {
        String[] data = text.split("Vormittag ");
        String headline = data[0];
        String rest = data[1];
        data = rest.split("Nachmittag ");
        String vormittag = data[0];
        rest = data[1];
        data = rest.split("Abend ");
        String nachmittag = data[0];
        String abend = data[1];
        for(int n=2;n<data.length;n++){
            abend = abend + "Abend " +data[n];
        }


        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(id)
                .setText(headline + "\n \n Vormittag:\n" + vormittag + "\n \n Nachmittag: \n" + nachmittag + "\n \n Abend: \n" + abend);
        return message;
    }

    private void sternzeichen(Long id, String wann, String sternzeichen){
        try {
            Document doc = Jsoup.connect("https://astrowoche.wunderweib.de/tageshoroskop/"+wann+"/"+sternzeichen).get();
            String textContents = doc.select(".typo--editor").first().text();
            int offset = 0;
            switch (sternzeichen) {
                case "Jungfrau":{offset = 56;}
                case "Waage": {offset = 53;}
                case "Stier": {offset = 53;}
                case "Steinbock": {offset = 59;}
                case "Wassermann": {offset = 59;}
                case "Fische" : {offset=55;}
                case "Skorpion": {offset=57;}
                case "Schuetze": {offset=56;}
                case "Krebs": {offset=54;}
                case "Loewe": {offset=53;}
                case "Widder" : {offset=55;}
                case "Zwillinge" : {offset=56;}


            }
            String text = textContents.substring(0, textContents.length() - offset);

            SendMessage messageHoroskop = horoskopToMessage(sternzeichen + " " + wann +": \n \n" + text, id);

            try {
                execute(messageHoroskop); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MangoSchalenBot";
    }

    @Override
    public String getBotToken() {
        return "1069910652:AAEZZXc6MFKjkN4WhKB83ojm1FiOGQTN_is";
    }
}
