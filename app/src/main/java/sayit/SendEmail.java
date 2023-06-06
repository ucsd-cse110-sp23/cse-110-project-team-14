package sayit;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SendEmail {
    AudioRecorder recorder;
    JButton askButton;
    IAudioConverter converter;
    IChatBot chat;
    MyAskPanel askPanel;
    Frame frame;
    Storage storage;
    MySideBar sideBar;
    ButtonCoordinator buttonCoordinator;
    private boolean askStop = false;
    final String URL = "http://localhost:8100/";

    public SendEmail(AudioRecorder r, JButton b, IAudioConverter con, IChatBot bot, MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co) {
        recorder = r;
        askButton = b;
        converter = con;
        chat = bot;
        askPanel = pan;
        frame = f;
        storage = s;
        sideBar = bar;
        buttonCoordinator = co;
    }
}
