package com.snakereactor.SnakeReactor.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="gamelog")
public class Gamelog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false, updatable = true)
    private long id;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="user_rewards", nullable = false)
    private int user_rewards;

    @Column(name="gametime", nullable = false)
    private int gametime;

    @Column(name="score", nullable = false)
    private int score;

    @Column(name="turns_made", nullable = false)
    private int turns_made;

    @Column(name="demise", nullable = false)
    private String demise;

    @Column(name="high_score_bool")
    private Boolean high_score_bool;

    @Column(name="free_reward_bool")
    private Boolean free_reward_bool;

    @Column(name="purchased_reward_bool")
    private Boolean purchased_reward_bool;

    @Column(name="time_in_purchase_modal")
    private double time_in_purchase_modal;

    //NO ARGS CONSTRUCTOR
    public Gamelog(){
        super();
        this.date = LocalDateTime.now();
    }

    public Gamelog(String username, String gender, int age, int user_rewards, int gametime, int score, int turns_made, String demise, Boolean high_score_bool, Boolean free_reward_bool, Boolean purchased_reward_bool, double time_in_purchase_modal){
        super();
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.user_rewards = user_rewards;
        this.gametime = gametime;
        this.score = score;
        this.turns_made = turns_made;
        this.demise = demise;
        this.high_score_bool = high_score_bool;
        this.free_reward_bool = free_reward_bool;
        this.purchased_reward_bool = purchased_reward_bool;
        this.time_in_purchase_modal = time_in_purchase_modal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGametime() {
        return gametime;
    }

    public void setGametime(int gametime) {
        this.gametime = gametime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurns_made() {
        return turns_made;
    }

    public void setTurns_made(int turns_made) {
        this.turns_made = turns_made;
    }

    public String getDemise() {
        return demise;
    }

    public void setDemise(String demise) {
        this.demise = demise;
    }

    public Boolean getHigh_score_bool() {
        return high_score_bool;
    }

    public void setHigh_score_bool(Boolean high_score_bool) {
        this.high_score_bool = high_score_bool;
    }

    public Boolean getFree_reward_bool() {
        return free_reward_bool;
    }

    public void setFree_reward_bool(Boolean free_reward_bool) {
        this.free_reward_bool = free_reward_bool;
    }

    public Boolean getPurchased_reward_bool() {
        return purchased_reward_bool;
    }

    public void setPurchased_reward_bool(Boolean purchased_reward_bool) {
        this.purchased_reward_bool = purchased_reward_bool;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUser_rewards() {
        return user_rewards;
    }

    public void setUser_rewards(int user_rewards) {
        this.user_rewards = user_rewards;
    }

    public double getTime_in_purchase_modal() {
        return time_in_purchase_modal;
    }

    public void setTime_in_purchase_modal(double time_in_purchase_modal){
        this.time_in_purchase_modal = time_in_purchase_modal;
    }
}
