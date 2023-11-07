package screen;

import asciiPanel.AsciiPanel;

public interface GlyphDelegate {

    void printGlyph(AsciiPanel terminal, int offsetX, int offsetY);
}