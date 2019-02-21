package month02.day18;

import org.junit.Test;

public class MergeSortTest {
    @Test
    public void test() {
        int[] arr = {9, -16, 21, 23, -30, -49, 21, 30, 30};
//        int[] arr = {9, -16, 21};
        int[] tmp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, tmp);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private void mergeSort(int[] arr, int left, int right, int[] tmp) {
        if(left<right){
            //必须left<right
            int mid = (left+right)/2;
            mergeSort(arr, left, mid, tmp);
            mergeSort(arr, mid+1, right, tmp);

            //合并
            merge(arr,left,mid,right,tmp);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int l = left;
        int r = mid+1;
        int t=0;// 下标
        while(l<=mid && r<=right){
            if(arr[l] <arr[r]){
                tmp[t++]=arr[l++];
            }else{
                tmp[t++] = arr[r++];
            }
        }
        while(l<=mid){
            tmp[t++]=arr[l++];
        }
        while(r<=right){
            tmp[t++] = arr[r++];
        }
        //将tmp中数据拷贝到arr中
        t=0;//重置
        int tmpLeft=left;
        while(tmpLeft<=right){
            arr[tmpLeft++] = tmp[t++];
        }

    }






//
//    /*
//        测试一：left<=right 是否可以 答：不可以，必须left<right
//        测试二：从tmp拷贝到arr中时，必须tmpleft<= t //必须有=号，否则会有数据丢失
//     */
//    private void mergeSort(int[] arr, int left, int right, int[] tmp) {
//        if (left < right) {
//            int mid = (left + right) / 2;
//            mergeSort(arr, left, mid, tmp);
//            mergeSort(arr, mid + 1, right, tmp);
//
//            //进行合并
//            merge(arr, left, mid, right, tmp);
//        }
//    }
//
//    //遍历
//    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
//        int l = left;
//        int r = mid+1;
//        int t = 0;
//        //首先合并
//        while (l <= mid && r <= right) {
//            if (arr[l] < arr[r]) {
//                tmp[t++] = arr[l++];
//            } else {
//                tmp[t++] = arr[r++];
//            }
//        }
//        //判断剩余部分
//        while (l <= mid) {
//            tmp[t++] = arr[l++];
//        }
//        while (r <= right) {
//            tmp[t++] = arr[r++];
//        }
//        //然后将数据从tmp中拷贝到arr中
//        t = 0; //重新置为0
//        int tmpLeft = left; //不能改变原有值
//        while (tmpLeft <= right) {
//            arr[tmpLeft++] = tmp[t++];
//        }
//    }


//    //归并排序--先递归到每个只有一个数据，然后进行合并
//    private void mergeSOrt(int[] arr, int left, int right, int[] tmp) {
//        if(left<right){
//            int mid = (left+right) /2;
//            mergeSOrt(arr, left, mid, tmp);
//            mergeSOrt(arr, mid+1, right, tmp);
//
//            merge(arr, left,mid, right, tmp);
//        }
//    }
//    //递归到最后以后，将进行合并，两个区域中的数据进行合并
//    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
//        //首先合并两个区域中的数据
//        int l = left;
//        int r = mid+1;
//        int t = 0;
//        while(l<=mid && r<=right){
//            if(arr[l]<arr[r]){
//                tmp[t++] = arr[l++];
//            }else {
//                tmp[t++] = arr[r++];
//            }
//        }
//        //判断是否有剩余部分
//        while(l<=mid){
//            tmp[t++] = arr[l++];
//        }
//        while(r<=right){
//            tmp[t++] = arr[r++];
//        }
//        //然后将数据从tmp中拷贝到arr中
//        t=0;
//        int tmpLeft = left;
//        //tmpLeft<=right
//        while (tmpLeft<=right){
//            //范围从0-right逐个进行拷贝
//            arr[tmpLeft++] = tmp[t++];
//        }
//    }

    /*
     def mergeSort(array: Array[Int], left:Int, right:Int): Unit ={
    var tmp = new Array[Int](array.length)
    mergeSort(array, left, right, tmp)
  }

  def merge(array: Array[Int], left: Int, mid: Int, right: Int, tmp: Array[Int]): Unit = {
    //三个索引指针，第一个指向left 起始位置，第二个指向mid+1 的起始位置，第三个指向tmp的起始位置
    var l = left
    var m = mid+1
    var t = 0

     //先进行比较两个部分的较小值，然后放入到tmp中，使变得有序
    while(l<= mid && m <= right){
      if (array(l)< array(m)){ //如果l的数据小
        tmp(t)=array(l)
        t+=1 //将较小的数据放入后，t指针向后移动一个单位
        l+=1 //l向后移动一个单位
      }else {
        tmp(t) =array(m)
        m+=1 //m指针向后移动一个单位
        t+=1  //t指针向后移动一个单位
      }
    }
    //然后判断这两个部分数据，是否还有剩余--有剩余将之放入到tmp中
    while(l<=mid){
      tmp(t) = array(l)
      l+=1
      t+=1
    }
    while(m<= right){
      tmp(t) = array(m)
      m+=1
      t+=1
    }
    //最后将数据从tmp拷贝到array中
    t=0
    var tmpLeft = left
    while(tmpLeft<=right){ //拷贝数据
      array(tmpLeft) = tmp(t)
      tmpLeft+=1
      t+=1
    }
  }

  def mergeSort(array: Array[Int], left:Int, right:Int, tmp:Array[Int]): Unit ={
    var mid = (left+right)/2
    if (left<right) {
      mergeSort(array, left, mid, tmp)
      mergeSort(array, mid + 1, right, tmp)
      //merger合并操作
      merge(array, left, mid, right, tmp)
    }
  }
     */
}
