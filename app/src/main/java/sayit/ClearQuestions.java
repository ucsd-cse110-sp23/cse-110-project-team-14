package sayit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.*;

public class ClearQuestions {
    Frame frame;
    Storage storage;
    MySideBar sideBar;
    ButtonCoordinator buttonCoordinator;
    final String URL = "http://localhost:8100/account";


    public ClearQuestions(Frame f, Storage s, MySideBar bar, ButtonCoordinator c) {
        frame = f;
        storage = s;
        sideBar = bar;
        buttonCoordinator = c;
    }

    public void clearAll() {
        Thread t = new Thread(
            () -> {
                if (buttonCoordinator.getCurQ()) {
                    
                } else {
                    String response = "HTTP REQUEST SENT";
                    try {
                        URL url = new URL(URL + "?=true");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("DELETE");
                        conn.setDoOutput(false);
                        
                        BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream())
                        );


                        response = in.readLine();
                        in.close();
                        
                        System.out.println(response);

                    }catch (MalformedURLException exception){
                        exception.printStackTrace();
                    }catch (IOException exception){
                        exception.printStackTrace();
                    }
                    
                    sideBar.deleteAll();
                    storage.clearAll();
                    buttonCoordinator.setCurButton(null);;
                    frame.updateAnswerBox(" ");
                    frame.updateQuestionBox(" ");
                    sideBar.revalidate();
                    sideBar.repaint();
                    frame.revalidate();
                }
            });
        t.start();
    }
}
