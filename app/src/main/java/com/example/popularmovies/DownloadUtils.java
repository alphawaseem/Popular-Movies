package com.example.popularmovies;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by waseem on 11/9/16.
 * Helper class to make http connection to the input url and returns
 * the response in string
 */
public class DownloadUtils {

    private static final String LOG_TAG = DownloadUtils.class.getSimpleName();

    /**
     * private constructor so that no objects of this class can be made
     */
    private DownloadUtils() {

    }

    /**
     * This function will make a network call from the input url and returns the
     * network response as string
     *
     * @param url the query url
     * @return the json response after making call to query url if response code is OK(200)
     */
    public static String makeHttpRequest(URL url) {
        HttpURLConnection httpURLConnection = null;
        String jsonResponse = null;
        InputStream stream = null;

        try {
            //try connecting to given url
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) { // if response is OK 200
                stream = httpURLConnection.getInputStream(); // gets the response in stream
                jsonResponse = readFromInputStream(stream); //convert stream to string

            }
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }
        if (httpURLConnection != null)
            httpURLConnection.disconnect();
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getLocalizedMessage());
            }
        }
        return jsonResponse;
    }

    /**
     * This function reads the stream object using buffered reader and returns the string
     *
     * @param stream input stream object
     * @return string after reading from stream object
     */
    public static String readFromInputStream(InputStream stream) {

        StringBuilder response = new StringBuilder();
        if (stream != null) {
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            try {
                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    response.append(line);
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getLocalizedMessage());
            }

        }
        return response.toString();
    }
}
