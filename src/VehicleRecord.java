import java.time.LocalDateTime;

public class VehicleRecord {
    private String carVRN;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public VehicleRecord(String carVRN, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.carVRN = carVRN;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public String getCarVRN() {
        return carVRN;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public boolean isCurrentlyParked() {
        return exitTime == null;
    }

    public String calculateDuration() {
        if (exitTime == null)
            return "Still parked";
        long seconds = java.time.Duration.between(entryTime, exitTime).getSeconds();
        long days = seconds / (24 * 3600);
        seconds %= 24 * 3600;
        long hours = seconds / 3600;
        seconds %= 3600;
        long minutes = seconds / 60;
        seconds %= 60;

        return (days > 0 ? days + " days " : "") + String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public double calculateCost(double hourlyRate) {
        if (exitTime == null)
            return 0;
        long hours = java.time.Duration.between(entryTime, exitTime).toHours();
        return Math.ceil(hours + 1) * hourlyRate;
    }

    @Override
    public String toString() {
        return carVRN + "," + AppUtil.formatDateTime(entryTime) + "," + AppUtil.formatDateTime(exitTime);
    }
}
