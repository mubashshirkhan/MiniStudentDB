import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private static final long serialVersionUid = 1L;

    private int sno;
    private String name;
    private String course;
    private double fee;

    public Student(int sno, String name, String course, double fee) {
        this.sno = sno;
        this.name = name;
        this.course = course;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Student student))
            return false;

        return sno == student.sno &&
                Objects.equals(course, student.course);
    }
    @Override
    public int hashCode() {
        return Objects.hash(sno, course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", fee=" + fee +
                '}';
    }
}