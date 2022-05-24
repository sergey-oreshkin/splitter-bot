package home.serg.service;

public interface MessageProcessor {
    String getResponse(long chat, String msg);
}
