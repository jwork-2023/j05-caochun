package screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

public interface Screen {

    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);
}
