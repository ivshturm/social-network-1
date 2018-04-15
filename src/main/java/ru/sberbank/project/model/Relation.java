package ru.sberbank.project.model;

import javax.persistence.*;


@Entity
@Table(name = "relations")
public class Relation extends AbstractBaseEntity {

    @Column(name = "follower_id")
    private int followerId;

    @Column(name = "following_id")
    private int followingId;

    public Relation() {
    }

    public Relation(int followerId, int followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "followerId=" + followerId +
                ", followingId=" + followingId +
                '}';
    }
}
