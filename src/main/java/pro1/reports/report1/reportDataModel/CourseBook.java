package pro1.reports.report1.reportDataModel;

import com.google.gson.annotations.SerializedName;

public class CourseBook
{
    @SerializedName("nazev")
    public String title;
    @SerializedName("autor")
    public String author;
    public String courseCode;

    public CourseBook(String title, String author, String courseCode)
    {
        this.title = title;
        this.author = author;
        this.courseCode = courseCode;
    }
}
