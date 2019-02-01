package com.exemplo.android.miwok;


public class Word {

    private String mWordDefaultTranslation;
    private String mwordMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String miwokTranslation) {
        mWordDefaultTranslation = defaultTranslation;
        mwordMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId) {
        mWordDefaultTranslation = defaultTranslation;
        mwordMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    public String getDefaultTranslation() {
        return mWordDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mwordMiwokTranslation;
    }

    public int getImageResourceID() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
