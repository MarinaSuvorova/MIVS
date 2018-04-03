import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Course {
    private String courseCode;
    private String tittle;
    private String desciption;
    private LocalDate startDate;
    private int credit;
    private String lecturerId;

    public void setCourseInfo(String cousreCode, String[] courseInfo) {
        this.courseCode = cousreCode;
        this.lecturerId = courseInfo[0];
        try {
            this.credit = Integer.parseInt(courseInfo[1]);
        } catch (Exception e) {
            System.out.println("Wrong number format");
        }
        this.tittle = courseInfo[2];
        this.desciption = courseInfo[3];
        try {
            this.startDate = LocalDate.parse(courseInfo[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Wrong date format");
        }
    }

    public String getCourseInfo() {
        return lecturerId + ";" +
                credit + ";" +
                tittle + ";" +
                desciption + ";" +
                startDate + ";";
    }

    public String getCourseCode() {

        return courseCode;
    }

    public void setCourseCode(String courseCode) {

        this.courseCode = courseCode;
    }

    public String getTittle() {

        return tittle;
    }

    public void setTittle(String tittle) {

        this.tittle = tittle;
    }

    public String getDesciption() {

        return desciption;
    }

    public void setDesciption(String desciption) {

        this.desciption = desciption;
    }

    public LocalDate getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        try {
            this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Wrong date format");
        }
    }

    public int getCredit() {

        return credit;
    }

    public void setCredit(int credit) {

        this.credit = credit;
    }

    public String getLecturerId() {

        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }
}
