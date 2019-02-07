package month01;

public class TestEnumType {
    public static void main(String[] args) {
        //jdk1.5之前
        //Season season = Season.SPRINT;
        Season winter = Season.WINTER;
        System.out.println(winter);
    }
}
//class Season{
//    //创建具体的枚举对象
//    public static final Season SPRINT= new Season();
//    public static final Season SUMMER= new Season();
//    public static final Season AUTUMN= new Season();
//    public static final Season WINTER= new Season();
//
//    //私有化构造器
//    private Season(){}
//}

enum Season{
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}