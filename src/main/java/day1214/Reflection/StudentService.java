package day1214.Reflection;

import java.util.ArrayList;

public class StudentService {
    private ArrayList<Student> all = null;


    public StudentService() {
        this.all = new ArrayList<>();
    }
    public void add(Student stu){
        System.out.println("add方法被调用"+stu);
        all.add(stu);
    }

    public Student get(int index){
        return all.get(index);
    }
    public ArrayList<Student> getAll() {
        System.out.println("getAll()方法被调用...");
        return all;
    }
}
