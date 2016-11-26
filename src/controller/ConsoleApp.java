package controller;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class ConsoleApp extends OutputStream {
	private TextArea textArea;

    public ConsoleApp(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.appendText(String.valueOf((char)b));
        // scrolls the text area to the end of data
       // textArea.setCaretPosition(textArea.getText().length());
        // keeps the textArea up to date
        
    }
    
}
