package month01.generic;

public class TestGenericClass {
    public static void main(String[] args) {
        //英语老师
        Student<String> s1 = new Student<String>("zhangsan", "A");
        //数学老师
        Student<Integer> s2 = new Student<>("lisi", 90);
    }
}

/*
需求要求：
声明一个学生类型，
要求：学生的成绩的类型可以由使用者决定
语文老师：优秀，良好，及格，不及格
数学老师：以数字形式
英语老师：A B C D E
 */
//S是类型形参
class Student<S>{
    private String name;
    private S score;

    public Student(String name, S score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public S getScore() {
        return score;
    }

    public void setScore(S score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}