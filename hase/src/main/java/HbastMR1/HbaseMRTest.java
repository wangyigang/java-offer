package HbastMR1;

import org.apache.hadoop.util.ToolRunner;

public class HbaseMRTest {
    public static void main(String[] args) throws Exception {

        int run = ToolRunner.run(new MRTool(), args);// 返回状态返回值
        System.out.println(run);

    }

}
