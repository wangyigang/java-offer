package month3.RandomAccessFileTest;

import org.junit.Test;

import java.io.*;

/*
    总结： RandomAccessFile的参数(path, mode)
        mode : r rw rws rwd
 */
/*
    注意点： mode只能是 r rw rws rwd这四种 rws (read write sync )
    rwd
 */
public class RandomAccessFileTest {
    @Test
    public void test1() {
        String path = "D:\\input\\hello.txt";
        int seekPointer = 2000;
        try {
            randomRed(path, seekPointer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        String path = "D:\\input\\hello.txt";
        try {
            randomWrite(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        String path="D:\\input\\hello.txt";

        try {
            insert(path, 33, "\n 优秀...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //功能三： 任意位置插入数据
    public static void insert(String filename, long points, String insertContent) throws IOException {
        File tmp=File.createTempFile("tmp", null);
        tmp.deleteOnExit(); //设置在JVM退出时删除
        RandomAccessFile raf = new RandomAccessFile(filename, "rw");
        //创建一个临时文件夹保存插入点后的数据
        FileOutputStream tmpOut= new FileOutputStream(tmp);
        FileInputStream tmpIn = new FileInputStream(tmp);
        raf.seek(points); //寻找到需要对位置
        //将插入点后的数据读入到临时文件夹
        byte[] buffer=new byte[1024];

        int hasread=0;
        while((hasread= raf.read(buffer))>0){
            tmpOut.write(buffer,0,hasread);
        }
        //插入需要指定添加的数据
        raf.seek(points);//返回原来的插入处
        //追加需要追加的内容
        raf.write(insertContent.getBytes());
        //最后追加临时文件中的内容
        while((hasread=tmpIn.read(buffer))>0){
            raf.write(buffer,0,hasread);
        }

    }

    //功能二：追加数据
    public static  void randomWrite(String path) throws IOException {
        //以读写的方式建立
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        //将记录指针移动到文件的最后
        raf.seek(raf.length());
        raf.write("追加...\r\n".getBytes());
    }


    //功能一：读取任意位置的数据
    public static void randomRed(String path, int pointe) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "r"); // r只读   rw-读写 rws rwd
        //获取RandomAccessFile对象文件指针的位置
        System.out.println("RandomAccessFile文件指针的初始位置:" + raf.getFilePointer());
        raf.seek(pointe);//移动文件指针位置
        byte[] buff = new byte[1024];
        int hasRead = 0;
        while ((hasRead = raf.read(buff)) > 0) {
            System.out.println(new String(buff, 0, hasRead));
        }
    }

}
