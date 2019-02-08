package com.exemplo.android.miwok;


public class Word {

    private String mWordDefaultTranslation;
    private String mwordMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudio;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String miwokTranslation, int audio) {
        mWordDefaultTranslation = defaultTranslation;
        mwordMiwokTranslation = miwokTranslation;
        mAudio = audio;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audio) {
        mWordDefaultTranslation = defaultTranslation;
        mwordMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudio = audio;
    }

    public String getDefaultTranslation() {
        return mWordDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mwordMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return mAudio;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mWordDefaultTranslation='" + mWordDefaultTranslation + '\'' +
                ", mwordMiwokTranslation='" + mwordMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudio=" + mAudio +
                '}';
    }
}
