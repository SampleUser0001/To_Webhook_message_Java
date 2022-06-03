package sample.webhook;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import sample.webhook.model.MessageModel;
import sample.webhook.enums.PropertiesEnum;

import java.net.MalformedURLException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.net.ProtocolException;


/**
 * 起動引数の値をWebhookに送信する
 */
public class App {
    private static Logger logger = LogManager.getLogger();
    public void execute( String message )
    throws JsonProcessingException,
           ProtocolException,
           IOException {
        logger.info("message : {}", message);
        HttpURLConnection conn = null;
        try {
            URL url = new URL(PropertiesEnum.URL.getPropertiesValue());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            try(PrintWriter writer = new PrintWriter(conn.getOutputStream())) {
                MessageModel model = new MessageModel();
                model.setText(message);
                writer.append(new ObjectMapper().writeValueAsString(model))
                      .flush();
                try(BufferedReader response = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    logger.info(response.lines().collect(Collectors.joining()));
                }
            }
        } finally {
            if(conn != null) {
                conn.disconnect();
            }
        }
    }
    
    public static void main( String[] args ) 
    throws JsonProcessingException,
           ProtocolException,
           IOException {

        if(args.length < 1) {
            System.err.println("送信するメッセージを指定してください。");
            logger.error("args.length : {}", args.length);
            return;
        }

        PropertiesEnum.load("app.properties");
        new App().execute(args[0]);
    }
}
