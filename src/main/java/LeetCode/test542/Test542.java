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
        int[][] arr = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] ret = updateMatrix(arr);
        for (int[] ints : ret) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public int[][] updateMatrix(int[][] matrix) {
        /**
         BFS思想, 把所有1都置为最大值, 把所有为0的位置加入队列中, 每次从队列中poll
         一个节点, 更新其四周的节点, 如果被更新的节点距离变小了就将其入队列准备更新其邻接点
         广度优先算法：

         **/
        int m = matrix.length;
        if (m < 1) return matrix;
        int n = matrix[0].length;
        Deque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1)
                    matrix[i][j] = Integer.MAX_VALUE;
                else
                    q.offer(new int[]{i, j});
            }
        }
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        while (!q.isEmpty()) {
            int[] node = q.poll();
            for (int[] dir : dirs) {
                int x = node[0] + dir[0];
                int y = node[1] + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[node[0]][node[1]] + 1)
                    continue;
                matrix[x][y] = matrix[node[0]][node[1]] + 1;
                q.offer(new int[]{x, y});
            }
        }
        return matrix;
    }

}
