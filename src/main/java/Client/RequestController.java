package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;

public class RequestController {

    private static String url;
    private static byte[] buffer;
    private static OutputStream out;
    private static InputStreamReader isr;
    private static HttpURLConnection httpConn;
    public static double ResponseTime;

    public RequestController(String url) {
        this.url = url;
    }

    public void init() throws IOException {
        httpConn = (HttpURLConnection) new URL(url).openConnection();
//        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
//        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
//        httpConn.setRequestMethod("POST");
//        httpConn.setDoOutput(true);
//        httpConn.setDoInput(true);
    }

    public void action(String body, String nameTable) throws IOException, InterruptedException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long Date1 = timestamp.getTime();
        buffer = (body).getBytes();
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("Content-lenght", String.valueOf(buffer.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        try {
            out = httpConn.getOutputStream();
            out.write(buffer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";

        try {
            isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            String responce = "";
            while ((responce = in.readLine()) != null) {
                result += responce;
            }
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        long Date2 = timestamp1.getTime();
        ResponseTime = (Date2 - Date1) / 1000.0;
        System.out.println(ResponseTime);

        InfluxController influxController = new InfluxController(ResponseTime, nameTable);
        influxController.Influx();

        return;
    }

    public void end() {
        httpConn.disconnect();
    }
}
