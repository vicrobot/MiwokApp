package com.example.android.miwok;

public class Word {

    private String mVar1;
    private String mVar2;
    private int mVar3;

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
}
