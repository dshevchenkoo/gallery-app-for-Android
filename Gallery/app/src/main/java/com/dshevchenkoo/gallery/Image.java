package com.dshevchenkoo.gallery;

/**
 * Created by dshevchenkoo on 30.04.18.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private String url;
    private String title;

    public Image(String url, String title) {
        this.url = url;
        this.title = title;
    }

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

    public static  Image[] getImages() {
        return new Image[]{
                new Image("https://pp.userapi.com/c846019/v846019735/2c5bd/aXvvLbY7pxs.jpg", "Cat"),
                new Image("https://pp.userapi.com/c847123/v847123093/2d5cd/IvnNff0Y4Bc.jpg", "GDG Omsk"),
                new Image("https://pp.userapi.com/c840237/v840237603/4d0fd/nrmMZc5-dEI.jpg", "OLD"),
                new Image("https://sun9-1.userapi.com/c840438/v840438611/1736a/Xet4wGVeGok.jpg", "myDog"),
                new Image("https://sun9-1.userapi.com/c840530/v840530497/109b7/ETc56hiafkw.jpg", "me"),
                new Image("https://pp.userapi.com/c639716/v639716389/69294/mRkbYO3l16g.jpg", "Hello, World!"),
        };
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
