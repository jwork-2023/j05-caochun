package screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

public abstract class RestartScreen implements Screen {

    @Override
    public abstract void displayOutput(AsciiPanel terminal);

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                return new SnakeGameScreen();
            default:
                return this;
        }
    }

}
