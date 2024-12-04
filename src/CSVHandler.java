import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private static final String FILE_NAME = "carpark_data.csv";

    /**
     * Reads all records from the CSV file.
     * 
     * @return List of VehicleRecord objects
     * @throws IOException If file operations fail
     */
    public List<VehicleRecord> readRecords() throws IOException {
        List<VehicleRecord> records = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists())
            return records;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                LocalDateTime entryTime = AppUtil.parseDateTime(data[1]);
                LocalDateTime exitTime = data[2].equals("N/A") ? null : AppUtil.parseDateTime(data[2]);
                records.add(new VehicleRecord(data[0], entryTime, exitTime));
            }
        }
        return records;
    }

    /**
     * Writes all records to the CSV file.
     * 
     * @param records List of VehicleRecord objects to write
     * @throws IOException If file operations fail
     */
    public void writeRecords(List<VehicleRecord> records) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (VehicleRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Adds a new record to the CSV file.
     * 
     * @param record VehicleRecord to add
     * @throws IOException If file operations fail
     */
    public void addRecord(VehicleRecord record) throws IOException {
        List<VehicleRecord> records = readRecords();
        records.add(record);
        writeRecords(records);
    }

    /**
     * Updates an existing record with an exit time.
     * 
     * @param vrn      Vehicle Registration Number to update
     * @param exitTime Exit time to set
     * @throws IOException If file operations fail
     */
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
