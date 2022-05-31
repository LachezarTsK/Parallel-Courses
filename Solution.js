
/**
 * @param {number} numberOfCourses
 * @param {number[][]} relations
 * @return {number}
 */
var minimumSemesters = function (numberOfCourses, relations) {
    this.NOT_POSSIBLE_TO_TAKE_ALL_COURSES = -1;
    this.graph = Array.from(new Array(numberOfCourses + 1), () => new Array());
    this.indegrees = new Array(numberOfCourses + 1).fill(0);

    createGraph(relations);
    initializeArrayIndegrees(relations);
    return findMinimumNumberOfSemestersToTakeAllCourses(numberOfCourses);
};

/**
 * @param {number} numberOfCourses
 * @return {number}
 */
function findMinimumNumberOfSemestersToTakeAllCourses(numberOfCourses) {
    const queue = new Queue();
    initializeQueueWithCoursesOfZeroIndegrees(queue);
    let minimumNumberOfSemesters = 0;

    while (!queue.isEmpty()) {

        for (let i = queue.size() - 1; i >= 0; --i) {
            let current = queue.dequeue();
            --numberOfCourses;

            const courses = this.graph[current];
            for (let course of courses) {
                if (--this.indegrees[course] === 0) {
                    queue.enqueue(course);
                }
            }
        }
        ++minimumNumberOfSemesters;
    }
    return numberOfCourses === 0 ? minimumNumberOfSemesters : this.NOT_POSSIBLE_TO_TAKE_ALL_COURSES;
}

/**
 * @param {Queue of integers} queue
 * @return {void}
 */
function initializeQueueWithCoursesOfZeroIndegrees(queue) {
    for (let i = 1; i < this.indegrees.length; ++i) {
        if (indegrees[i] === 0) {
            queue.enqueue(i);
        }
    }
}

/**
 * @param {number[][]} relations
 * @return {void}
 */
function  createGraph(relations) {
    for (let relation of relations) {
        this.graph[relation[0]].push(relation[1]);
    }
}

/**
 * @param {number[][]} relations
 * @return {void}
 */
function initializeArrayIndegrees(relations) {
    for (let relation of relations) {
        this.indegrees[relation[1]]++;
    }
}
