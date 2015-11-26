package com.yun.jonas.holder;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yun.jonas.BuildConfig;
import com.yun.jonas.R;
import com.yun.jonas.application.BaseApplication;

/**
 * Created by Jonas on 2015/11/22.
 */
public class ViewHolderHelper implements View.OnClickListener {

    private Context mContext;
    private int position;
    private View mConvertView;
    SparseArray<View> mViews;
    private Object associatedObject;

    private ViewHolderHelper(Context context, ViewGroup parent, int layoutId, int position){
        this.mContext = context;
        this.position = position;
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolderHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position){
        if(convertView == null) {
            return new ViewHolderHelper(context, parent, layoutId, position);
        }
        ViewHolderHelper existingHelper = (ViewHolderHelper)convertView.getTag();
        existingHelper.position = position;
        return existingHelper;
    }

    public View getRootView(){
        return mConvertView;
    }

    public <T extends View> T findViewById(int viewId){
        View view = mViews.get(viewId);
        if(view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }

    public ViewHolderHelper setText(int viewId, String value){
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }

    public ViewHolderHelper setBackground(int viewId, int resId){
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public ViewHolderHelper setImageFromUrl(int viewId, String url){
        ImageView imageView = findViewById(viewId);
//        Glide.with(mContext).load(url).placeholder(R.drawable.ic_empty_page).error(R.drawable.ic_error_page).fitCenter().override(200, 200)
//                .into(imageView);
        Glide.with(mContext).load(url).centerCrop().override(BaseApplication.screenW/3, BaseApplication.screenW/3).into(imageView);
//        Glide.with(mContext).load(url).centerCrop().override(200, 200).into(imageView);
        return this;
    }

    public ViewHolderHelper setImageFromUrl(int viewId, String url, int width, int height){
        ImageView imageView = findViewById(viewId);
        Glide.with(mContext).load(url).placeholder(R.drawable.ic_empty_page).error(R.drawable.ic_error_page).fitCenter().override(width,
                height).into(imageView);
        //        Glide.with(mContext).load(url).centerCrop().override(200, 200).into(imageView);
        return this;
    }

    public ViewHolderHelper setClickListener(int viewId, View.OnClickListener listener){
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolderHelper setClickListener(int viewId){
        View view = findViewById(viewId);
        view.setOnClickListener(this);
        return this;
    }

    public ViewHolderHelper setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener){
        AdapterView view = findViewById(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }

    public ViewHolderHelper setAdapter(int viewId, Adapter adapter){
        AdapterView view = findViewById(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /** Retrieves the last converted object on this view. */
    public Object getAssociatedObject(){
        return associatedObject;
    }

    /** Should be called during convert */
    public void setAssociatedObject(Object associatedObject){
        this.associatedObject = associatedObject;
    }

    @Override
    public void onClick(View v){
        onItemClicked(v.getId());
    }

    public void onItemClicked(int viewId){
        if(BuildConfig.DEBUG) {
            Log.d("ViewHolderHelper", "..........");
        }
    }
}

