package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Exam;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class DepartmentExamsStatsReporting {
    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
        String json = dataSource.getTerminyZkousek2(katedra);
        ExamsList list = new Gson().fromJson(json, ExamsList.class);

        int count = 0;
        Set<String> rooms = new TreeSet<>(); // TreeSet automaticky řadí abecedně a zahazuje duplicity

        if (list != null && list.termin != null) {
            for (Exam e : list.termin) {
                if (e.obsazeni > 0) {
                    count++;
                }
                if (e.mistnost != null && !e.mistnost.trim().isEmpty()) {
                    rooms.add(e.mistnost.trim());
                }
            }
        }

        DepartmentExamsStats stats = new DepartmentExamsStats();
        stats.realizedExamsCount = count;
        stats.reservedRooms = new ArrayList<>(rooms);

        return stats;
    }
}