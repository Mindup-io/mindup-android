package io.mindup.android.api;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import io.mindup.android.domain.Mind;

/**
 * Consumes the API for Minds related operations
 *
 * Created by math on 02/12/15.
 */
public class MindsAPI extends API{


    /**
     * Build a query and fetch minds according to parameters
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

        return this.gson.fromJson(
                this.getJson(queryBuilder.toString())
                , Mind[].class
        );
    }

    /**
     * Get a mind by its id
     *
     * @param mindId
     * @return Mind
     */
    public Mind getMind(String mindId) throws IOException{

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(API_URL)
                .append("/mind/get_one/")
                .append(USER_ID)
                .append("/")
                .append(mindId);

        return this.gson.fromJson(
                this.getJson(queryBuilder.toString())
                , Mind.class
        );

    }

    /**
     * Favorite / Unfavorite a mind and return an updated mind
     * Note that the nbFavorites value of the updated mind can be
     * increase / decrease by more than one. The new value reflects
     * also the changes since the mind was last synchronized.
     *
     * @param mind
     * @return updated mind
     * @throws IOException
     */
    public Mind favUnFavMind(Mind mind) throws IOException{

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(API_URL)
                .append("/favorite/")
                .append(USER_ID)
                .append("/")
                .append(mind.getId())
                .append("/")
                /**
                 * If the mind is already favorited, we unfavorite it
                 * Otherwise, we favorite it.
                 */
                .append(mind.isFavorited() ? "unfav" : "fav");

        /**
         * The API response cannot be mapped to a POJO,
         * It's only composed of two fields :
         *  nb_favs (Int, equivalent to Mind.nbFavorited)
         *  favorited (boolean, equivalent to Mind.isFavorited
         */
        JsonObject jsonResponse = gson.fromJson(
                /**
                 * TODO: This API endpoint is a post,
                 * yet, it expects query parameters.
                 * So, we send a empty string here as data to
                 * fit the postJson signature.
                 *
                 * There's something to change server-side before
                 * we go live with this.
                 */
                this.postJson(queryBuilder.toString(), ""),
                JsonObject.class);

        //update the mind with the API response
        mind.setFavorited(jsonResponse.get("favorited").getAsBoolean());
        mind.setNbFavorites(jsonResponse.get("nb_favs").getAsInt());

        return mind;
    }
}
