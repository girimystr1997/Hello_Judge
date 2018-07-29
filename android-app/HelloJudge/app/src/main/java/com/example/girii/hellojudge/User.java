package com.example.girii.hellojudge;

public class User {

    public String name;
    public String dept_and_branch;
    public String college_name;
    public String judge_one;
    public String judge_two;
    public String year;
    public String time;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name,String college_name, String dept_and_branch, String year, String judge_one, String judge_two) {
        this.name = name;
        this.year=year;
        this.dept_and_branch = dept_and_branch;
        this.college_name = college_name;
        this.judge_one = judge_one;
        this.judge_two=judge_two;
    }
}