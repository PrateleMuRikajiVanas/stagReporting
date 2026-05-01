//package pro1.reports.report1;
//
//import pro1.DataSource;
//import pro1.reports.report1.reportDataModel.CourseBook;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TeacherBooksReporting {
//
//    public static List<CourseBook> GetReport(DataSource dataSource, String rok, int ucitIdno, String katedra){
//        var coursesJson = dataSource.getPredmetyByUcitel(rok, ucitIdno, katedra  );
//
//        // TODO 1.1: Převeď coursesJson na objekt typu apiDataModel.TeacherCoursesList.
//        // TODO 1.2: Doplň nutné atributy do třídy apiDataModel.TeacherCourse
//
//        var reportItems = new ArrayList<CourseBook>();
//
//        // TODO 1.3: Pro každý předmět získej z dataSource ještě seznam knih. Pro každou z nich přidej prvek do reportItems.
//
//        return reportItems;
//    }
//}
package pro1.reports.report1;
import pro1.apiDataModel.BooksList;
import pro1.apiDataModel.TeacherCourse;
import pro1.apiDataModel.TeacherCoursesList;
import com.google.gson.Gson;
import pro1.DataSource;
import pro1.reports.report1.reportDataModel.CourseBook;
import pro1.reports.report1.reportDataModel.CourseBookList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeacherBooksReporting {
//    public static BookItem[] GetReport(DataSource dataSource, String rok, int ucitelId, String katedra) {
//        Gson gson = new Gson();
//        List<BookItem> allBooks = new ArrayList<>();
//        String subjectsJson = dataSource.getPredmetyByUcitel(rok, ucitelId, katedra);
//        SubjectList subjectList = gson.fromJson(subjectsJson, SubjectList.class);
//
//        if (subjectList != null && subjectList.predmet != null) {
//            for (Subject s : subjectList.predmet) {
//                String litJson = dataSource.getLiteraturaPredmetu(s.zkratka, katedra);
//                LiteratureList litList = gson.fromJson(litJson, LiteratureList.class);
//
//                if (litList != null && litList.literatura != null) {
//                    for (Literature l : litList.literatura) {
//                        allBooks.add(new BookItem(l.autor, l.titul, l.rokVydani));
//                    }
//                }
//            }
//        }
//
//        return allBooks.toArray(new BookItem[0]);
public static List<CourseBook> GetReport(DataSource dataSource, String rok, int ucitIdno, String katedra){
    var coursesJson = dataSource.getPredmetyByUcitel(rok, ucitIdno, katedra  );
    Gson gson = new Gson();
    // TODO 1.1: Převeď coursesJson na objekt typu apiDataModel.TeacherCoursesList.
    TeacherCoursesList coursesList = gson.fromJson(coursesJson,TeacherCoursesList.class);
    // TODO 1.2: Doplň nutné atributy do třídy apiDataModel.TeacherCourse

    var reportItems = new ArrayList<CourseBook>();

    // TODO 1.3: Pro každý předmět získej z dataSource ještě seznam knih. Pro každou z nich přidej prvek do reportItems.
    for (TeacherCourse course : coursesList.items) {
        var booksJson = dataSource.getLiteraturaPredmetu(course.code, katedra);

        BooksList booksList = gson.fromJson(booksJson, BooksList.class);

        if (booksList != null && booksList.items != null) {
            for (var book : booksList.items) {
                reportItems.add(new CourseBook(book.title, book.author, course.code));
            }
        }

    }
    return reportItems;
    }
}
