
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    private static final int NOT_POSSIBLE_TO_TAKE_ALL_COURSES = -1;
    List[] graph;
    int[] indegrees;

    public int minimumSemesters(int numberOfCourses, int[][] relations) {
        createGraph(numberOfCourses, relations);
        initializeArrayIndegrees(numberOfCourses, relations);
        return findMinimumNumberOfSemestersToTakeAllCourses(numberOfCourses);
    }

    private int findMinimumNumberOfSemestersToTakeAllCourses(int numberOfCourses) {
        Queue<Integer> queue = new LinkedList<>();
        initializeQueueWithCoursesOfZeroIndegrees(queue);
        int minimumNumberOfSemesters = 0;

        while (!queue.isEmpty()) {

            for (int i = queue.size() - 1; i >= 0; --i) {
                int current = queue.poll();
                --numberOfCourses;

                List<Integer> courses = graph[current];
                for (int course : courses) {
                    if (--indegrees[course] == 0) {
                        queue.add(course);
                    }
                }
            }
            ++minimumNumberOfSemesters;
        }
        return numberOfCourses == 0 ? minimumNumberOfSemesters : NOT_POSSIBLE_TO_TAKE_ALL_COURSES;
    }

    private void initializeQueueWithCoursesOfZeroIndegrees(Queue<Integer> queue) {
        for (int i = 1; i < indegrees.length; ++i) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
    }

    private void createGraph(int numberOfCourses, int[][] relations) {
        graph = new ArrayList[numberOfCourses + 1];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int[] relation : relations) {
            graph[relation[0]].add(relation[1]);
        }
    }

    private void initializeArrayIndegrees(int numberOfCourses, int[][] relations) {
        indegrees = new int[numberOfCourses + 1];
        for (int[] relation : relations) {
            indegrees[relation[1]]++;
        }
    }
}
