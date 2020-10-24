package Client;

import java.io.IOException;
import java.sql.Timestamp;

public class ThreadController {
    private static String body;
    private static String nameTable;
    private static int latency;
    private static int quantity;


    public ThreadController(String body, String nameTable, int latency,int quantity) {
        this.body = body;
        this.nameTable = nameTable;
        this.latency = latency;
        this.quantity = quantity;
    }

    String URL = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";
    final RequestController request = new RequestController(URL);


    Thread thread = new Thread() {
        public void run() {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long Date1 = timestamp.getTime();
            long Date2 = timestamp.getTime();
            while ((Date2 - Date1) / 1000.0 < 300) {
                for (int i = 0; i < quantity; i++) {
                    try {
                        Thread.sleep(latency);//8500 and 20000
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        request.init();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        request.action(body, nameTable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    request.end();
                }
                Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
                Date2 = timestamp1.getTime();
            }
        }
    };
}

