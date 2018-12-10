package com.day1208;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class WholeRecordReader extends RecordReader<Text, BytesWritable> {
    private boolean flag = true;
    private FSDataInputStream fis = null;
    private FileSplit fileSplit = null;
    private Text k = new Text();
    private BytesWritable v = new BytesWritable();
    /**
     * 初始化fsdatainputstream
     *
     * @param split
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        fileSplit = (FileSplit) split;
        Path path = fileSplit.getPath();
        Configuration conf = context.getConfiguration();

        FileSystem fs = path.getFileSystem(conf);
        fis = fs.open(path);

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(flag){
            String path = fileSplit.getPath().toString();
            k.set(path);

            //读取留内容
            byte[] bytes = new byte[(int) fileSplit.getLength()];
            fis.read(bytes);
            v.set(bytes,0,bytes.length);
            flag = false;
            return true;
        }
        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return k;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return v;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return flag ? 0 : 1;
    }

    @Override
    public void close() throws IOException {
        IOUtils.closeStream(fis);
    }
}