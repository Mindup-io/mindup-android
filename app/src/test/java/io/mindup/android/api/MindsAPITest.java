package io.mindup.android.api;


import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import io.mindup.android.domain.Mind;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tests for the MindAPI
 *
 * Created by math on 02/12/15.
 */
public class MindsAPITest {

    @BeforeClass
    public static void onlyOnce() {
        API.USER_ID = "42";
    }

    @Test
    public void getMinds() throws Exception {

        MindsAPI mindsAPI = Mockito.spy(new MindsAPI());

        Mockito.when(mindsAPI.fetchJson("https://api0.mindup.io/v2/mind/42?limit=1&"))
                .thenReturn("\n" +
                        "[{\"_id\":\"5660a250d6c79f0655d9d6cd\",\"name\":\"Bienvenue \uD83D\uDE03 MindUp te permet de partager anonymement tes pensées (minds) aux gens qui t'entourent. Écris ton mind, choisis le nombre de destinataires et envoie ; si ton mind a du succès, tu gagneras des points pour envoyer encore plus de minds ! \uD83D\uDE1C\",\"creation_time\":\"2015-12-03T20:13:04.715Z\",\"nb_messages\":1,\"last_update\":\"2015-12-03T20:18:22.843Z\",\"type\":\"team\",\"tags\":[],\"last_msg_time\":\"2015-12-03T20:18:22.843Z\"},{\"_id\":\"5660a0bdd6c79f0655d9d6a9\",\"name\":\"Ptn je suis dans la grosse perde! !\uD83D\uDE22\uD83D\uDE20\",\"creation_time\":\"2015-12-03T20:06:21.861Z\",\"nb_messages\":22,\"nb_favs\":1,\"last_update\":\"2015-12-03T20:48:04.314Z\",\"type\":\"user\",\"tags\":[],\"last_msg_time\":\"2015-12-03T20:48:04.314Z\",\"favorited\":false,\"isOwner\":false,\"loc\":[0,0],\"city\":\"Earth\",\"nb_read_messages\":0,\"opened\":false},{\"_id\":\"565ce155d6c79f0655d9a3af\",\"name\":\"Vive les gros Boobs Ralph et les grenouille \uD83D\uDE02\uD83D\uDE02\uD83D\uDD1E\",\"creation_time\":\"2015-11-30T23:52:53.542Z\",\"nb_messages\":512,\"nb_favs\":1,\"last_update\":\"2015-12-03T20:38:08.470Z\",\"type\":\"user\",\"tags\":[],\"last_msg_time\":\"2015-12-03T20:38:08.470Z\",\"favorited\":false,\"isOwner\":false,\"loc\":[0,0],\"city\":\"Earth\",\"nb_read_messages\":0,\"opened\":false}]");


        Mind[] minds = mindsAPI.getMinds(1, null, null, null, null, null);

        /**
         * TODO: It's kind of weird that a limit 1 returns 3 mind. Obviously, the first team
         * mind in not really a mind, but we should have only the welcome team mind and another
         * one.
         *
         * That's what the API returns when we fetch https://api0.mindup.io/v2/mind/42?limit=1&
         * So, the fix is at the API server level, not here.
         */
        assertEquals(3, minds.length);
        assertEquals(minds[0].getType(), "team");
        assertEquals(minds[1].getType(), "user");
    }

    @Test
    public void getMind() throws Exception {

        MindsAPI mindsAPI = Mockito.spy(new MindsAPI());

        Mockito.when(mindsAPI.fetchJson("https://api0.mindup.io/v2/mind/get_one/42/5660a0bdd6c79f0655d9d6a9"))
                .thenReturn("\n" +
                        "{\"_id\":\"5660a0bdd6c79f0655d9d6a9\",\"name\":\"Ptn je suis dans la grosse perde! !\uD83D\uDE22\uD83D\uDE20\",\"creation_time\":\"2015-12-03T20:06:21.861Z\",\"nb_messages\":29,\"nb_favs\":1,\"last_update\":\"2015-12-03T21:38:40.497Z\",\"type\":\"user\",\"tags\":[],\"last_msg_time\":\"2015-12-03T21:38:40.497Z\",\"favorited\":false,\"isOwner\":false,\"loc\":[0,0],\"city\":\"Earth\",\"nb_read_messages\":0,\"opened\":false}");

        Mind mind = mindsAPI.getMind("5660a0bdd6c79f0655d9d6a9");

        assertEquals(mind.getId(), "5660a0bdd6c79f0655d9d6a9");

    }
}
