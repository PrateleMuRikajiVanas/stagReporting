package pro1.reports.report1.reportDataModel;

import com.google.gson.annotations.SerializedName;
import pro1.apiDataModel.TeacherCourse;

import java.util.List;

public class CourseBookList {
    @SerializedName("literatura")
    public List<CourseBook> items;
}
