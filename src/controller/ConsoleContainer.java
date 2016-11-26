package controller;

import java.io.IOException;
import java.io.OutputStream;

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
        output.appendText(String.valueOf((char) i));
    }


}
