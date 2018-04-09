package com.example.android.miwok;

public class Word {

    private String mVar1;
    private String mVar2;
    private int mVar3 = NO_IMAGE;
    private static final int NO_IMAGE = -1;

    public Word(String var1,String var2,int var3){
        mVar1 = var1;
        mVar2 = var2;
        mVar3 = var3;
    }
    public Word(String var1,String var2){
        mVar1 = var1;
        mVar2 = var2;
    }
    //getters methods
    public String getFirstText(){
        return mVar1;
    }
    public String getSecondText(){
        return mVar2;
    }
    public int getImageResouse(){
        return mVar3;
    }

    public boolean hasImage(){
        boolean x = true;
        if (mVar3 ==NO_IMAGE) {
            x=false;
        }
        return x;
    }
}
