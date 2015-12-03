package io.mindup.android.api;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

/**
 * Created by math on 02/12/15.
 */
public class API {
    protected static String USER_ID;
    protected static String API_URL = "https://api0.mindup.io/v2";

    protected boolean isIntegerDefined(Integer integer){
        if(integer != null && integer != -1){

            return true;
        }
        return false;
    }

    protected boolean isObjectDefined(Object object){
        if(object != null){

            return true;
        }
        return false;
    }


    protected String fetchJson(String url) throws IOException {
        return IOUtils.toString(new URL(url).openStream());
    }

}
