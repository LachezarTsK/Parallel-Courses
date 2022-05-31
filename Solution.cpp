
#include <queue>
#include <vector>
using namespace std;

class Solution {
    
    inline static int NOT_POSSIBLE_TO_TAKE_ALL_COURSES = -1;
    vector<vector<int>> graph;
    vector<int> indegrees;

public:
    int minimumSemesters(int numberOfCourses, vector<vector<int>>& relations) {
        createGraph(numberOfCourses, relations);
        initializeArrayIndegrees(numberOfCourses, relations);
        return findMinimumNumberOfSemestersToTakeAllCourses(numberOfCourses);
    }

private:
    int findMinimumNumberOfSemestersToTakeAllCourses(int numberOfCourses) {
        queue<int> queue;
        initializeQueueWithCoursesOfZeroIndegrees(queue);
        int minimumNumberOfSemesters = 0;

        while (!queue.empty()) {

            for (int i = queue.size() - 1; i >= 0; --i) {
                int current = queue.front();
                queue.pop();
                --numberOfCourses;

                for (const auto& course : graph[current]) {
                    if (--indegrees[course] == 0) {
                        queue.push(course);
                    }
                }
            }
            ++minimumNumberOfSemesters;
        }
        return numberOfCourses == 0 ? minimumNumberOfSemesters : NOT_POSSIBLE_TO_TAKE_ALL_COURSES;
    }

    void initializeQueueWithCoursesOfZeroIndegrees(queue<int>& queue) {
        for (int i = 1; i < indegrees.size(); ++i) {
            if (indegrees[i] == 0) {
                queue.push(i);
            }
        }
    }

    void createGraph(int numberOfCourses, const vector<vector<int>>& relations) {
        graph.resize(numberOfCourses + 1);
        for (const auto& relation : relations) {
            graph[relation[0]].push_back(relation[1]);
        }
    }

    void initializeArrayIndegrees(int numberOfCourses, const vector<vector<int>>& relations) {
        indegrees.resize(numberOfCourses + 1);
        for (const auto& relation : relations) {
            indegrees[relation[1]]++;
        }
    }
};
