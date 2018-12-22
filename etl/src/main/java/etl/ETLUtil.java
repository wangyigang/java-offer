package etl;

public class ETLUtil {

    public static String etlString(String string) {
        String[] fiedls = string.split("\t");
        //①如果长度小于9， 返回Null
        if(fiedls.length<9){
            return null;
        }
        //替换" "
        fiedls[3] = fiedls[3].replace(" ", "");
        StringBuffer buffer = new StringBuffer();

        //将最后一部分的原有的\t转换为&进行拼接
        for(int i=0; i<fiedls.length; ++i){
            //分为两部分处理，一部分长度大于9,一部分没有最后一个字段
            if(i<9){
                if(i==fiedls.length-1){
                    buffer.append(fiedls[i]);
                }else{
                    buffer.append(fiedls[i]).append("\t");
                }
            }else{
                if(i==fiedls.length-1){
                    buffer.append(fiedls[i]);
                }else{
                    buffer.append(fiedls[i]).append("&");
                }
            }
        }
        return buffer.toString();
    }
}
