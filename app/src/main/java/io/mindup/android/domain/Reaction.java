package io.mindup.android.domain;

import java.util.Date;

/**
 * Created by Mathieu Nayrolles <mathieu.nayrolles@gmail.com> on 12/1/15.
 */
public class Reaction {

    private String  id;
    private String  letterId;
    private String  reaction;
    private Date    datePosted;
    private int     nbLikes;
    private boolean liked;
    private boolean reported;
    private boolean seen;
    private boolean owner;
    private boolean motherOwner;
    private String  motherId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reaction)) return false;

        Reaction reaction = (Reaction) o;

        return getId().equals(reaction.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
