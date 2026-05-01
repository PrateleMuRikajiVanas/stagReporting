package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.Schedule;
import pro1.reports.report3.reportDataModel.WeekdayReportItem;

public class DepartmentWeekdaysReporting {
    public static WeekdayReportItem[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        String json = dataSource.getRozvrhByKatedra(katedra, rok);
        Schedule schedule = new Gson().fromJson(json, Schedule.class);

        WeekdayReportItem[] result = new WeekdayReportItem[days.length];

        for (int i = 0; i < days.length; i++) {
            String targetDay = days[i];
            int count = 0;

            if (schedule != null && schedule.rozvrhovaAkce != null) {
                for (Action a : schedule.rozvrhovaAkce) {
                    if (targetDay.equals(a.denZkr)) {
                        count++;
                    }
                }
            }
            result[i] = new WeekdayReportItem(targetDay, count);
        }
        return result;
    }
}