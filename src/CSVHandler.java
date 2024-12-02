import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
  private static final String FILE_NAME = "carpark_data.csv";
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

  public List<VehicleRecord> readRecords() throws IOException {
    List<VehicleRecord> records = new ArrayList<>();
    File file = new File(FILE_NAME);
    if (!file.exists())
      return records;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        LocalDateTime entryTime = LocalDateTime.parse(data[1], formatter);
        LocalDateTime exitTime = data[2].equals("N/A") ? null : LocalDateTime.parse(data[2], formatter);
        records.add(new VehicleRecord(data[0], entryTime, exitTime));
      }
    }
    return records;
  }

  public void writeRecords(List<VehicleRecord> records) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
      for (VehicleRecord record : records) {
        writer.write(record.toString());
        writer.newLine();
      }
    }
  }

  public void addRecord(VehicleRecord record) throws IOException {
    List<VehicleRecord> records = readRecords();
    records.add(record);
    writeRecords(records);
  }

  public void updateRecord(String vrn, LocalDateTime exitTime) throws IOException {
    List<VehicleRecord> records = readRecords();
    for (VehicleRecord record : records) {
      if (record.getCarVRN().equals(vrn) && record.isCurrentlyParked()) {
        record.setExitTime(exitTime);
        break;
      }
    }
    writeRecords(records);
  }
}
