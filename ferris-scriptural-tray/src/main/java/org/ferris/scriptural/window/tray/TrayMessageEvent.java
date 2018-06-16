package org.ferris.scriptural.window.tray;

import java.util.StringJoiner;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class TrayMessageEvent {
    protected String caption, text;

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", String.format("[%s: ", this.getClass().getSimpleName()), "]");
        sj.add(String.format("caption=\"%s\"", getCaption()));
        sj.add(String.format("text=\"%s\"", getText()));
        return sj.toString();
    }

    public TrayMessageEvent(String caption, String text) {
        this.caption = caption;
        this.text = text;
    }

    public String getCaption() {
        return caption;
    }

    public String getText() {
        return text;
    }
}
