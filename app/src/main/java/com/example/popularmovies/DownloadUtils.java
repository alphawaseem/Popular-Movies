package com.example.popularmovies;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by hajira on 11/9/16.
 */
public class DownloadUtils {

    private static final String LOG_TAG = DownloadUtils.class.getSimpleName();

    private DownloadUtils() {

    }

    public static URL getMoviesDbURL(String orderBy) {
        MovieUriBuilder.setOrderType(orderBy);
        URL url = null;
        try {
            url = new URL(MovieUriBuilder.getUri().toString());
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }
        return url;
    }

    public static String makeHttpRequest(URL url) {


        HttpURLConnection httpURLConnection = null;
        String jsonResponse = null;
        InputStream stream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                stream = httpURLConnection.getInputStream();
                jsonResponse = readFromInputStream(stream);

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

    public static String readFromInputStream(InputStream stream) {

        StringBuilder response = new StringBuilder();
        if (stream != null) {
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            try {
                while (true) {
                    line  = bufferedReader.readLine();
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
