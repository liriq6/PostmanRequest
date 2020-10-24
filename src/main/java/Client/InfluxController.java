package Client;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

public class InfluxController {
    private static double responseTime;
    private static String nameTable;

    public InfluxController(double responseTime,String nameTable) {
        this.responseTime = responseTime;
        this.nameTable = nameTable;
    }

    public static void Influx() throws InterruptedException {

        String databaseURL = "http://localhost:8086";
        final InfluxDB influxDB = InfluxDBFactory.connect(databaseURL);

        influxDB.createDatabase("ResponseTime");
        influxDB.createRetentionPolicy("defaultPolicy", "baeldung", "30d", 1, true);

        final BatchPoints batchPoints = BatchPoints
                .database("ResponseTime")
                .retentionPolicy("autogen")
                .build();

        Point point = Point.measurement("time")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("name", "SYSTEM")
                .addField("time", responseTime)
                .addField(nameTable, responseTime)
                .build();

        batchPoints.point(point);
        influxDB.write(batchPoints);
    }
}