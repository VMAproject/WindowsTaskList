package com.tasklist.model;

/**
 * Compares two TaskDto and puts a comparison sign.
 * Sign:
 * = - if equal
 * ~ - has changes in PID ot Memory
 * - - removed (left, not right)
 * + - added (not left, right)
 */
public class TaskDtoDiff {

    TaskDto left;
    DiffSign diffSign;
    TaskDto right;

    public TaskDtoDiff(TaskDto left, DiffSign diffSign, TaskDto right) {
        this.left = left;
        this.diffSign = diffSign;
        this.right = right;
    }

    public TaskDto getLeft() {
        return left;
    }

    public DiffSign getDiffSign() {
        return diffSign;
    }

    public TaskDto getRight() {
        return right;
    }
}
