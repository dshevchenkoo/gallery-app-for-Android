package com.dshevchenkoo.gallery;

/**
 * Created by dshevchenkoo on 30.04.18.
 */

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Image extends ArrayList<Image> implements Parcelable {

    private  String title;
    private String url;
    private static ArrayList<Image> images = new ArrayList<>();

    public Image(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Image(){}

    protected Image(Parcel in) {
        url = in.readString();
        title = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса Яндекс Диска
            try {
                URL url = new URL("https://cloud-api.yandex.net/v1/disk/public/resources?public_key=https://yadi.sk/d/terSeecm3VQqWv&%20preview_size=S");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            JSONObject dataJsonObj = null;

            try {
                dataJsonObj = new JSONObject(strJson);
                JSONObject trans = dataJsonObj.getJSONObject("_embedded");
                JSONArray item = trans.getJSONArray("items");
                images.clear();
                // перебираем ссылки на изображения и имена
                for (int i = 0; i < item.length(); i++) {
                    JSONObject jsonObject = item.getJSONObject(i);
                    Image image = new Image();
                    image.setTitle(jsonObject.getString("name"));
                    image.setUrl(jsonObject.getString("file"));
                    images.add(image);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Image> getImages() {
        return images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(title);
    }
}