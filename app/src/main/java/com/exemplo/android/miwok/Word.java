package com.exemplo.android.miwok;

public class Word {

    private String mWordDefaultTranslation;
    private String mwordMiwokTranslation;

    public Word(String defaultTranslation, String miwokTranslation){
        mWordDefaultTranslation = defaultTranslation;
        mwordMiwokTranslation = miwokTranslation;
    }


    public String getmWordDefaultTranslation() {
        return mWordDefaultTranslation;
    }

    public String getMwordMiwokTranslation() {
        return mwordMiwokTranslation;
    }
}
