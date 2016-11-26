package controller;

import java.io.IOException;
import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ConsoleContainer extends OutputStream{

	private TextArea    output;

    public ConsoleContainer(TextArea ta)
    {
        this.output = ta;
    }

    @Override
    public void write(int i) throws IOException
    {
    	 appendText(String.valueOf((char)i));
    	 
      //  output.appendText(String.valueOf((char) i));
    }

    public void appendText(String valueOf) {
        Platform.runLater(() -> output.appendText(valueOf));
    }

}
