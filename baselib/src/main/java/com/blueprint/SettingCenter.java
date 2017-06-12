package com.blueprint;

import com.blueprint.helper.SpHelper;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Single;

import static com.blueprint.helper.FileHelper.clearFile;
import static com.blueprint.helper.FileHelper.getDirSize;

public class SettingCenter {

    public static Set<String> sMsaveDataUrls = new HashSet<>();

    public static void needSaveDataUrl(String url){
        sMsaveDataUrls.add(url);
    }

    public static boolean isNeedSaveData(String url){
        return sMsaveDataUrls.contains(url);
    }

    public static boolean getOnlyWifiLoadImage(){
        return (boolean)SpHelper.get(LibApp.getContext(), "getOnlyWifiLoadImage", false);
    }

    public static void setOnlyWifiLoadImage(boolean isEnable){
        SpHelper.get(LibApp.getContext(), "getOnlyWifiLoadImage", isEnable);
    }

    /**
     * 计算缓存大小
     */
    public static Single<Long> dirSizeObserver(){
        return getDirSize(LibApp.getContext().getCacheDir());
    }

    /**
     * 清除缓存
     */
    public static Single<Boolean> clearAppCache(){
        return clearFile(LibApp.getContext().getCacheDir());

    }

}
