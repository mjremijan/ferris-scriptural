package org.ferris.scriptural.window.tray;

import java.awt.TrayIcon.MessageType;
import java.util.StringJoiner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayMessageEvent {
    protected String caption, text;
    protected MessageType messageType;

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", String.format("[%s: ", this.getClass().getSimpleName()), "]");
        sj.add(String.format("caption=\"%s\"", getCaption()));
        sj.add(String.format("text=\"%s\"", getText()));
        sj.add(String.format("messageType=\"%s\"", getMessageType()));
        return sj.toString();
    }

    public TrayMessageEvent(String caption, String text, MessageType messageType) {
        this.caption = caption;
        this.text = text;
        this.messageType = messageType;
    }

    public String getCaption() {
        return caption;
    }

    public String getText() {
        return text;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
