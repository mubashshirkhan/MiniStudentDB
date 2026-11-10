import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {

    private static final String filename = "Student.ser";
    private List<Student> studentList;

    public StudentDB() {
        studentList = loadFromFile();
    }

    public void add(Student s) {
        studentList.add(s);
        saveToFile();
    }

    public int count() {
        return studentList.size();
    }

    public boolean isEmpty() {
        return studentList.isEmpty();
    }

    public List<Student> getAll() {
        return studentList;
    }

    public boolean exists(int sno, String course) {
        Student temp = new Student(sno, "", course, 0);
        return studentList.contains(temp);
    }

    public Student retrieve(int sno, String course) {
        Student temp = new Student(sno, "", course, 0);
        int index = studentList.indexOf(temp);
        if (index != -1) {
            return studentList.get(index);
        }
        return null;
    }

    public boolean update(int sno, String name, String course, double fee) {

        Student s = retrieve(sno, course);
        if (s != null) {
            s.setFee(fee);
            s.setName(name);
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean remove(int sno, String course) {

        Student temp = new Student(sno, "", course, 0);
        boolean removed = studentList.remove(temp);
        if (removed)
            saveToFile();
        return removed;
    }

    public boolean replace(int index, Student s) {
        if (index >= 0 && index < studentList.size()) {
            studentList.set(index, s);
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean insert(int index, Student s) {
        if (index >= 0 && index <= studentList.size()) {
            studentList.add(index, s);
            saveToFile();
            return true;
        }

        return false;
    }

    //Save
    private void saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));

            oos.writeObject(studentList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Load
    private List<Student> loadFromFile() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(filename))) {

            return (List<Student>) ois.readObject();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}