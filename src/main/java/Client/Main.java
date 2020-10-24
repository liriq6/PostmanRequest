package Client;

import java.io.IOException;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        String URL = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";
        final RequestController request = new RequestController(URL);


        Thread thread = new Thread() {
            public void run() {
                RandomController random = new RandomController();
                String bodyOne = "" +
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                        "  <soap:Body>\n" +
                        "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                        "      <ubiNum>" + random.randomInt(1000) + "</ubiNum>\n" +
                        "    </NumberToWords>\n" +
                        "  </soap:Body>\n" +
                        "</soap:Envelope>";
                String nameTableOne = "NumberToWords";
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                long Date1 = timestamp.getTime();
                long Date2 = timestamp.getTime();
                while ((Date2 - Date1) / 1000.0 < 30000*12) {
                    for (int i = 0; i < 7; i++) {
                        try {
                            Thread.sleep(2000);//8500 and 20000
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            request.init();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            request.action(bodyOne, nameTableOne);
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
        Thread thread1 = new Thread() {
            public void run() {
                RandomController random = new RandomController();
                String bodyTwo = "" +
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                        "  <soap:Body>\n" +
                        "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                        "      <dNum>" + random.randomInt(1000) + "</dNum>\n" +
                        "    </NumberToDollars>\n" +
                        "  </soap:Body>\n" +
                        "</soap:Envelope>";
                String nameTableTwo = "NumberToDollars";
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                long Date1 = timestamp.getTime();
                long Date2 = timestamp.getTime();
                while ((Date2 - Date1) / 1000.0 < 30000*12) {
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(7000);//8500 and 20000
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            request.init();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            request.action(bodyTwo, nameTableTwo);
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
//        request.init();
        thread.start();
//        Thread.sleep(300000);
//        thread1.start();
//        request.end();

//        request.init();
//        ThreadController threadOne = new ThreadController(bodyOne, nameTableOne, 8500,7);
//        threadOne.thread.start();
//        ThreadController threadTwo = new ThreadController(bodyTwo, nameTableTwo, 20000,3);
//        threadTwo.thread.start();
//        request.end();
    }
}

