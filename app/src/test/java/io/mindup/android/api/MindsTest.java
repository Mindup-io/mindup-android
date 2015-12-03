package io.mindup.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import io.mindup.android.domain.Mind;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by math on 02/12/15.
 */
public class MindsTest {

    @BeforeClass
    public static void onlyOnce() {
        API.USER_ID = "42";
    }

    @Test
    public void addition_isCorrect() throws Exception {

        Minds minds = Mockito.mock(Minds.class);
        when(minds.fetchJson("https://api0.mindup.io/v2/mind/42?limit=1&"))
                .thenReturn("\n" +
                        "[{\"_id\":\"565f3eaad6c79f0655d9c504\",\"name\":\"Bienvenue \uD83D\uDE03 MindUp te permet de partager anonymement tes pensées (minds) aux gens qui t'entourent. Écris ton mind, choisis le nombre de destinataires et envoie ; si ton mind a du succès, tu gagneras des points pour envoyer encore plus de minds ! \uD83D\uDE1C\",\"creation_time\":\"2015-12-02T18:55:38.684Z\",\"nb_messages\":0,\"last_update\":\"2015-12-02T18:55:38.684Z\",\"type\":\"team\",\"tags\":[]},{\"_id\":\"565f3bfdd6c79f0655d9c418\",\"name\":\"Pq toute ma vie repose sur elle ?\",\"creation_time\":\"2015-12-02T18:44:13.851Z\",\"nb_messages\":11,\"nb_favs\":0,\"last_update\":\"2015-12-02T19:03:36.898Z\",\"type\":\"user\",\"tags\":[],\"last_msg_time\":\"2015-12-02T19:03:36.898Z\",\"favorited\":false,\"isOwner\":false,\"loc\":[0,0],\"city\":\"Earth\",\"nb_read_messages\":0,\"opened\":false},{\"_id\":\"565ce155d6c79f0655d9a3af\",\"name\":\"Vive les gros Boobs Ralph et les grenouille \uD83D\uDE02\uD83D\uDE02\uD83D\uDD1E\",\"creation_time\":\"2015-11-30T23:52:53.542Z\",\"nb_messages\":498,\"nb_favs\":1,\"last_update\":\"2015-12-02T09:45:46.728Z\",\"type\":\"user\",\"tags\":[],\"last_msg_time\":\"2015-12-02T09:45:46.728Z\",\"favorited\":false,\"isOwner\":false,\"loc\":[0,0],\"city\":\"Earth\",\"nb_read_messages\":0,\"opened\":false}]");



        Mind[] mindsResult = minds.getMinds(1, null, null, null, null, null);

        System.out.println(mindsResult);



    }
}
