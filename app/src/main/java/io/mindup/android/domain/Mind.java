package io.mindup.android.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Set;

/**
 * Created by Mathieu Nayrolles <mathieu.nayrolles@gmail.com> on 12/1/15.
 */
public class Mind {

    /**
     * Minds characteristics
     */

    @SerializedName("_id")
    private String              id;
    @SerializedName("name")
    private String              content;
    @SerializedName("tags")
    private Set<String>         hashtags;
    @SerializedName("creation_time")
    private Date                createdAt;
    @SerializedName("last_msg_time")
    private Date                lastUpdatedAt;
    @SerializedName("nb_favs")
    private int                 nbFavorites;
    @SerializedName("nb_messages")
    private int                 nbReactions;
    @SerializedName("type")
    private String              type;
    @SerializedName("city")
    private String              city;
    @SerializedName("loc")
    private int[]               location;

    /**
     * User's characteristic to this mind
     */
    @SerializedName("favorited")
    private boolean             favorited;
    @SerializedName("opened")
    private boolean             openned;
    @SerializedName("isOwner")
    private boolean             author;
    @SerializedName("nb_read_messages")
    private int                 nbUnreadReactions;

    /**
     * Transcient properties. Stay on the app
     */
    private transient Set<Reaction> reactions;
    private transient boolean       reported;

    /**
     * Not yet part of the API
     */
    private transient int           cost;
    private transient int           pointsAcquired;
    private transient int           nbUps;
    private transient int           kilometersSpan;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<String> hashtags) {
        this.hashtags = hashtags;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public int getNbFavorites() {
        return nbFavorites;
    }

    public void setNbFavorites(int nbFavorites) {
        this.nbFavorites = nbFavorites;
    }

    public int getNbReactions() {
        return nbReactions;
    }

    public void setNbReactions(int nbReactions) {
        this.nbReactions = nbReactions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isOpenned() {
        return openned;
    }

    public void setOpenned(boolean openned) {
        this.openned = openned;
    }

    public boolean isAuthor() {
        return author;
    }

    public void setAuthor(boolean author) {
        this.author = author;
    }

    public int getNbUnreadReactions() {
        return nbUnreadReactions;
    }

    public void setNbUnreadReactions(int nbUnreadReactions) {
        this.nbUnreadReactions = nbUnreadReactions;
    }

}
