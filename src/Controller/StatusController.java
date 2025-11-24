package Controller;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import Model.Game;
import Model.StatusListener;

public class StatusController {
    private JTextPane statusPane;
    private Game model;
    private StatusListener statusListener;

    public StatusController(JTextPane pane, Game model){
        this.statusPane = pane;
        this.model = model;

        // listen for game updates
        statusListener = new ControllerStatusListener();
        this.model.addListener(statusListener);
    }

    private class ControllerStatusListener implements StatusListener {
        @Override
        public void statusMessage(String message) {
            try {
                int end = statusPane.getDocument().getLength();
                statusPane.getStyledDocument().insertString(end, message + "\n", null);
                // scroll to bottom
                statusPane.setCaretPosition(statusPane.getDocument().getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
}
