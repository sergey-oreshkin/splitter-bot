package home.serg.service;

public class MessageProcessorImpl implements MessageProcessor {
    public static final String NOTHING_TO_SAY = "На это мне нечего ответить";

    @Override
    public String getResponse(long chat, String msg) {
        String[] parts = msg.split(" ");
        String response = NOTHING_TO_SAY;
        switch (parts[0]) {
            case "/":
                response = "Ну привет";
                break;
            case "/me":
                String name = parts[1];
                response = "Я тебя запомнил, " + name;
                break;
            case "/all":
                response = "Это все кого я знаю";
                break;
            case "/spend":
                response = "Я это учту";
                break;
            case "/calc":
                response = "Посчитаю, но попозже";
                break;
        }
        return response;
    }
}
