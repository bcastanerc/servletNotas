package com.liceu.notes.utils;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class markdown {
    public static String renderNote(String text){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(text);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String finalText = renderer.render(document);
        System.out.println("markdown to html note: " + finalText);
        return finalText;
    }

}
