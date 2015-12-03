package io.mindup.android.domain;

import java.util.Date;
import java.util.Set;

/**
 * Created by math on 02/12/15.
 */
public class Chatroom {

    /**
     * Chatroom characteristics
     */
    private String              chatroomId;
    private int                 nbPrivateMessages;
    private Date                createdAt;
    private Date                lastUpdatedAt;

    /**
     * User's characteritics to this mind
     */
    private int                 nbReadPrivateMessages;
    private boolean             hasUpdates;
    private boolean             owner;

    private Set<PrivateMessage> privateMessages;



}
