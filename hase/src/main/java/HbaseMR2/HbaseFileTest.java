package HbaseMR2;

import org.apache.hadoop.util.ToolRunner;

public class HbaseFileTest {
    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new HBaseFileTool(), args);
        System.out.println(run);
    }
}
