package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StagDate {
    public String value;

    public boolean isValid() {
        return value != null && !value.trim().isEmpty();
    }

    public LocalDate toLocalDate() {
        if (!isValid()) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            return LocalDate.parse(value.split(" ")[0], formatter);
        } catch (Exception e) {
            return null;
        }
    }
}