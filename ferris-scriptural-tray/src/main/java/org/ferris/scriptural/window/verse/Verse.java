package org.ferris.scriptural.window.verse;

import java.util.StringJoiner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Verse {
    protected String title;
    protected String location;
    protected String text;

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", String.format("[%s: ", this.getClass().getSimpleName()), "]");
        sj.add(String.format("title=\"%s\"", getTitle()));
        sj.add(String.format("location=\"%s\"", getLocation()));
        sj.add(String.format("text=\"%s\"", getText()));
        return sj.toString();
    }

    public Verse(String title, String location, String text) {
        this.title = title;
        this.location = location;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getText() {
        return text;
    }

}
