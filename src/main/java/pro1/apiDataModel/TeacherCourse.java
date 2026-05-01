package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class TeacherCourse
{
    @SerializedName("zkratka")
    public String code;
    @SerializedName("nazev")
    public String title;
    public int ucitelId;
    @SerializedName("rok")
    public int rok;
    @SerializedName("katedra")
    public String katedra;
}
