package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDurationItem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ThesisDurationReporting {
    public static ThesisDurationItem[] GetReport(DataSource dataSource, String katedra, String[] years) {
        ThesisDurationItem[] result = new ThesisDurationItem[years.length];
        Gson gson = new Gson();

        for (int i = 0; i < years.length; i++) {
            String year = years[i];
            String json = dataSource.getKvalifikacniPrace(year, katedra);
            ThesisList list = gson.fromJson(json, ThesisList.class);

            long totalDays = 0;
            int validCount = 0;

            if (list != null && list.kvalifikacniPrace != null) {
                for (Thesis t : list.kvalifikacniPrace) {
                    if (t.datumZadani != null && t.datumOdevzdani != null
                            && t.datumZadani.isValid() && t.datumOdevzdani.isValid()) {

                        LocalDate start = t.datumZadani.toLocalDate();
                        LocalDate end = t.datumOdevzdani.toLocalDate();

                        if (start != null && end != null) {
                            totalDays += ChronoUnit.DAYS.between(start, end);
                            validCount++;
                        }
                    }
                }
            }

            int avg = validCount > 0 ? Math.round((float) totalDays / validCount) : 0;
            result[i] = new ThesisDurationItem(year, avg);
        }
        return result;
    }
}