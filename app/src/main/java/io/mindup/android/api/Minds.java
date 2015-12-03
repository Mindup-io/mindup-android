package io.mindup.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import io.mindup.android.domain.Mind;

/**
 * Created by math on 02/12/15.
 */
public class Minds extends API{

    private static Minds ourInstance = new Minds();

    public static Minds getInstance() {
        return ourInstance;
    }



    private String lastRequest;

    private Minds() {
    }


    /**
     *
     * @param limit     optional, integer. This is the limit by category, so if no
     *                  category set, it will return 'limit' by category.
     * @param category  optional, possible values: 'new', 'active', 'top', 'fav'.
     *                  Can contain multiple values, comma separated. If you use limit,
     *                  it will get limit element for each category and do an union on
     *                  it. It means that if you get two cat with a limit of 5, you'll
     *                  get minimum 5 minds, maximum 10.
     * @param page      optional, tell which page you want to retrieve. If you use it,
     *                  you must use per_page too.
     * @param perPage   optional, tell how many minds you want to retrieve per page.
     * @param since     optional, from when you want to get the minds.
     * @param tags      optional, filter the elements by tags
     *
     * @return Minds matching the query
     */
    public Mind[] getMinds(Integer limit, Object category, Integer page,
                              Integer perPage, Date since, Set<String> tags) throws IOException{

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(API_URL)
                .append("/")
                .append("mind")
                .append("/")
                .append(USER_ID)
                .append("?");


        if(isIntegerDefined(limit)){
            queryBuilder.append("limit=")
                    .append(limit)
                    .append("&");
        }

        if(isObjectDefined(category)){
            queryBuilder.append("cat=")
                    .append(category.toString())
                    .append("&");
        }

        if(isIntegerDefined(page) && isIntegerDefined(perPage)){
            queryBuilder.append("page=")
                    .append(page)
                    .append("per_page=")
                    .append(perPage)
                    .append("&");
        }

        if(isObjectDefined(since)){
            queryBuilder.append("since=")
                    .append(since.getTime())
                    .append("&");
        }

        if(isObjectDefined(tags)){

            queryBuilder.append("tags=");

            for (String tag : tags) {
                queryBuilder.append(tag)
                        .append(",");
            }
        }

        System.out.println("qdz");

        System.out.println("=="+fetchJson(queryBuilder.toString()));

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

        return gson.fromJson(
                fetchJson(queryBuilder.toString())
                , Mind[].class
        );
    }

    /**
     * The only purpose of this @override is to allow mocking
     * @param url
     * @return
     * @throws IOException
     */
    protected String fetchJson(String url) throws IOException {
        return super.fetchJson(url);
    }

    public String getLastRequest(){
        return this.lastRequest;
    }
}
