package LeetCode.test542;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test542 {
    /*
    0 0 0
    0 1 0
    1 1 1
     */
    @Test
    public void test() {
        //int[][] arr = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] arr = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] ret = updateMatrix(arr);
        for (int[] ints : ret) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /*
        bfs的思路是逐层更新，首先我们将所有的1都改为无穷大。从一个0开始，
        逐层向外更新，每次只考虑周围的四个节点，如果目标节点的值大于源节
        点的值，就证明我们给目标节点找到了一条更短的路，我们更新目标节点，
        否则目标节点保持不变。bfs需要使用队列进行存储，先将所有的0入列，
        作为起点，之后每次更新过的节点都要入列，用来实现祝层向外的效果。
     */
    public int[][] updateMatrix(int[][] matrix) {
        int length = matrix.length;
        int m = matrix[0].length;
        //初始化队列
        Deque<int[]> deque = new ArrayDeque<>();
        //将矩阵中所有0坐标如队列，将所有的1设置为int的最大值
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] ==1){ //1 置为int最大值
                    matrix[i][j]=Integer.MAX_VALUE;
                }else{
                    deque.push(new int[]{i,j});
                }
            }
        }
        //进行四个方向的坐标的尝试--放在外面合适些
        int[][] dirs= {{-1,0},{0,-1},{1,0},{0,1}};
        //循环直到队列为空
        while (!deque.isEmpty()){
            //将队列收元素取出来
            int[] pop = deque.pop();
            for (int[] dir : dirs) {
                //计算新的坐标
                int x = dir[0]+ pop[0]; //计算x
                int y = dir[1]+ pop[1]; //计算y
                //判断是否合法
                //如果x,y超过边界，或者x y的数值比当前小，则不用更新
                if(x<0|| y<0 || x>=length || y>= m|| matrix[x][y]<= matrix[pop[0]][pop[1]]){
                    //continue
                    continue;
                }
                //否则更新当前位置的数据，可以理解为深度
                matrix[x][y] =matrix[pop[0]][pop[1]]+1;
                //将跟新的点存入队列
                deque.offer(new int[]{x,y});
            }
        }
        return matrix;
    }

//    //核心逻辑不太理解...
//    public int[][] updateMatrix(int[][] matrix) {
//        //BFS：深度优先思想
//        int length = matrix.length;
//        if (length < 1) {
//            return matrix;//特殊情况，直接返回
//        }
//        int n = matrix[0].length;
//        Deque<int[]> deque = new ArrayDeque<>();
//
//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < n; j++) { //两层循环进行遍历
//                if (matrix[i][j] == 1) { //如果数组中是1，存入integer的最大值
//                    matrix[i][j] = Integer.MAX_VALUE;
//                } else {
//                    deque.push(new int[]{i, j}); //将i和j 都为0的全部放进队列中
//                }
//            }
//        }
//        //四个方向
//        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
//        while (!deque.isEmpty()) {
//            int[] node = deque.poll();
//            for (int[] dir : dirs) {
//                int x = node[0] + dir[0]; //x:
//                int y = node[1] + dir[1]; //y
//                if(x<0 || x>=length || y<0 || y>= n || matrix[x][y] <= matrix[node[0]][node[1]]+1){
//                    continue;
//                }
//                matrix[x][y] =matrix[node[0]][node[1]]+1;
//                deque.offer(new int[]{x,y});
//            }
//        }
//        return matrix;
//    }

//    public int[][] updateMatrix(int[][] matrix) {
//        /**
//         BFS思想, 把所有1都置为最大值, 把所有为0的位置加入队列中, 每次从队列中poll
//         一个节点, 更新其四周的节点, 如果被更新的节点距离变小了就将其入队列准备更新其邻接点
//         广度优先算法：
//         **/
//        int m = matrix.length;
//        if (m < 1) return matrix;
//        int n = matrix[0].length;
//        Deque<int[]> q = new ArrayDeque<>();
//
//        for (int i = 0; i < m; ++i) {
//            for (int j = 0; j < n; ++j) {
//                if (matrix[i][j] == 1)
//                    matrix[i][j] = Integer.MAX_VALUE;
//                else
//                    q.offer(new int[]{i, j});
//            }
//        }
//        //代表四个方向--上下左右
//        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
//
//        while (!q.isEmpty()) {
//            int[] node = q.poll();
//            for (int[] dir : dirs) {
//                int x = node[0] + dir[0];
//                int y = node[1] + dir[1];
//                if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[node[0]][node[1]] + 1)
//                    continue;
//                matrix[x][y] = matrix[node[0]][node[1]] + 1;
//                q.offer(new int[]{x, y});
//            }
//        }
//        return matrix;
//    }

}
