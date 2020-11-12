package com.snakereactor.SnakeReactor.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="score")
public class Score{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false, updatable = true)
    private long id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="score")
    private int score;

    @Column(name="reward")
    private int reward;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="reward_one")
    private int reward_one;

    @Column(name="reward_two")
    private int reward_two;

    @Column(name="reward_three")
    private int reward_three;

    @Column(name="reward_four")
    private int reward_four;

    @Column(name="reward_five")
    private int reward_five;

    @Column(name="reward_six")
    private int reward_six;

    public Score(){
        super();
        this.date = LocalDateTime.now();
        this.score = 0;
        this.reward = 0;
        this.reward_one = 0;
        this.reward_two = 0;
        this.reward_three = 0;
        this.reward_four = 0;
        this.reward_five = 0;
        this.reward_six = 0;
    }

    public Score(String username, int age, String gender, int reward, int score, int reward_one, int reward_two, int reward_three, int reward_four, int reward_five, int reward_six){
        super();
        this.date = LocalDateTime.now();
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.reward = reward;
        this.score = score;
        this.reward_one = reward_one;
        this.reward_two = reward_two;
        this.reward_three = reward_three;
        this.reward_four = reward_four;
        this.reward_five = reward_five;
        this.reward_six = reward_six;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public int getReward() { return reward; }

    public void setReward(int reward) { this.reward = reward; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getReward_one() {
        return reward_one;
    }

    public void setReward_one(int reward_one) {
        this.reward_one = reward_one;
    }

    public int getReward_two() {
        return reward_two;
    }

    public void setReward_two(int reward_two) {
        this.reward_two = reward_two;
    }

    public int getReward_three() {
        return reward_three;
    }

    public void setReward_three(int reward_three) {
        this.reward_three = reward_three;
    }

    public int getReward_four() {
        return reward_four;
    }

    public void setReward_four(int reward_four) {
        this.reward_four = reward_four;
    }

    public int getReward_five() {
        return reward_five;
    }

    public void setReward_five(int reward_five) {
        this.reward_five = reward_five;
    }

    public int getReward_six() {
        return reward_six;
    }

    public void setReward_six(int reward_six) {
        this.reward_six = reward_six;
    }
}
