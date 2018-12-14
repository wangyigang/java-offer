package day1214.Reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Properties;

public class TestReflection {
    /*
    获取Class对象
     */
    @Test
    public void test1() throws ClassNotFoundException {
        Class<? extends String> aClass = "hello".getClass();
        Class<String> stringClass = String.class;
        Class<?> aClass1 = Class.forName("java.lang.String");
        Class<?> aClass2 = ClassLoader.getSystemClassLoader().loadClass("java.lang.String");
        System.out.println(aClass);
        System.out.println(stringClass);
        System.out.println(aClass1);
        System.out.println(aClass2);
    }
    @Test
    public void test2(){
        //基本数据类型
        Class<Integer> integerClass = int.class;
        System.out.println(integerClass);
        //void的数据类型
        //void
        Class<Void> voidClass = Void.class;
        System.out.println(voidClass);
        //Class
        Class<Class> classClass = Class.class;
        System.out.println(classClass);
        //数组--数组只要元素类型相同，所有的Class都一样
        Class<int[]> aClass = int[].class;
        System.out.println(aClass);
        int[] a = new int[100];
        Class<? extends int[]> aClass2 = a.getClass();
        System.out.println(aClass2 == aClass); //true
        //string[]
        Class<String[][]> aClass1 = String[][].class;

        //annotation注解
        Class<Override> overrideClass = Override.class;
        System.out.println(overrideClass);

    }
    @Test
    public void testIsWhichType(){
        Class<TestReflection> testReflectionClass = TestReflection.class;
        System.out.println(testReflectionClass.isSynthetic()); //是否是合成类，复杂类
        System.out.println(testReflectionClass.isPrimitive());
    }
    @Test
    public void testNewInstance(){
        Class<Student> studentClass = Student.class;
        try {
            Student student = studentClass.newInstance();
            System.out.println(student);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3(){
        try {
            Class<?> student = Class.forName("day1214.Reflection.Student");
            //Constructor<?>[] constructors = student.getConstructors();//获取所有公共的构造器
            //Constructor<?>[] declaredConstructors = student.getDeclaredConstructors();//获取所有声明的构造器
            //获取指定的构造器--参数为形参对应的类型列表的Class
            Constructor<?> constructor = student.getDeclaredConstructor(Integer.class, String.class);
            //如果构造器私有化，需要手动设置可访问
            constructor.setAccessible(true);
            //创建对象
            Object wang = constructor.newInstance(1, "wang");
            System.out.println(wang);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //动态的为某个对象设置属性
    @Test
    public void test4(){
        try {
            //获取类型
            Class<?> name = Class.forName("day1214.Reflection.Student");
            //创建实例对象
            Object student = name.newInstance();
            //获取属性对象
            Field id = name.getDeclaredField("id"); //获取id属性
            Field namefield = name.getDeclaredField("name");
            id.setAccessible(true);
            namefield.setAccessible(true);
            //设置属性值
            id.set(student, 2); //给哪个对象设置属性
            namefield.set(student, "dilireba");//给哪个对象设置属性
            System.out.println(student);

            Object o = id.get(student); //获取student对象的ID属性的值
            Object namefieldValue = namefield.get(student);
            System.out.println("id:"+o);
            System.out.println("name:"+namefieldValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    //动态的调用方法
    @Test
    public  void test5(){
        //获取Class对象
        try {
            Class<?> studentService = Class.forName("day1214.Reflection.StudentService");
            //创建实例对象
            Object o = studentService.newInstance();
            //获取要调用的方法
            //获取方法--getMethod()等一系列方法
            Method add = studentService.getMethod("add", Student.class);
            //通过方法对象，执行方法
            add.invoke(o, Student.class.newInstance());

            //获取getAll()方法的对象
            Method getAll = studentService.getMethod("getAll");
            //如果方法不可见，也可以使用setAccessible使其可见
            //getAll.setAccessible(true);
            Object allRet = getAll.invoke(o); //被调用方法，没有参数，可以不传

            System.out.println(allRet);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //动态获取某个类型的信息
    @Test
    public void test6(){
        Properties pro = new Properties();
        try {
            pro.load(new FileInputStream("type.properties"));
            String className = pro.getProperty("className");
            //获取类型对象
            Class<?> c = Class.forName(className);
            //获取类型信息
            //获取包信息
            Package cPackage = c.getPackage();
            System.out.println(cPackage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    获取修饰符：
    getModifierd():获取一个类或其他成员的访问修饰符

     */
    @Test
    public  void test() throws Exception {
        Class<Student> studentClass = Student.class;
        Student student = studentClass.newInstance();
        int modifiers = studentClass.getModifiers();
        System.out.println(Modifier.toString( modifiers));
        System.out.println("final"+Modifier.isPrivate(modifiers));
    }
    //类名
    @Test
    public void test7() throws ClassNotFoundException {
        Class<?> name = Class.forName("day1214.Reflection.Student");
        String typeName = name.getTypeName();
        System.out.println(typeName);
        String simpleName = name.getSimpleName();
        System.out.println(simpleName);
        String name1 = name.getName();
        System.out.println(name1);
        Class<?> superclass = name.getSuperclass();
        System.out.println(superclass);

        Type genericSuperclass = name.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

}
