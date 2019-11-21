/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019. Shendy Aditya Syamsudin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.almuwahhid.mylitelibs.utils;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DialogBuilder {
    Dialog dialog;
    boolean isCancellable = true;
    WindowManager.LayoutParams wlp;
    Context context;
    Window window;
    boolean isShow = false;

    OnInitComponent onInitComponent;

    public Dialog getDialog() {
        return dialog;
    }

    public Context getContext(){
        return context;
    }

    public Activity getActivity(){
        return (Activity) context;
    }

    public DialogBuilder(Context context, int layout) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);
        window = dialog.getWindow();
        wlp = window.getAttributes();
//        wlp.gravity = Gravity.BOTTOM;
//        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

    }

    public void initComponent(OnInitComponent onInitComponent){
        this.onInitComponent = onInitComponent;
        this.onInitComponent.initComponent(dialog);
    }

    public void setAnimation(int style){
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogBottomAnimation;
        dialog.getWindow().getAttributes().windowAnimations = style;
    }

    public void setGravity(int gravity) {
//        Gravity.BOTTOM
        wlp.gravity = gravity;
        window.setAttributes(wlp);
    }

    public void setTransparentBackground(boolean isTransparent) {
        if(isTransparent){
           wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
           dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
    }

    public void setFullScreen(RelativeLayout layout){
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        layout.setLayoutParams(new FrameLayout.LayoutParams(width, height));
    }

    public void setFullWidth(RelativeLayout layout){
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        layout.setLayoutParams(new FrameLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setLayout(int layout){
        this.dialog.setContentView(layout);
    }

    public void setCancellable(boolean t) {
        dialog.setCanceledOnTouchOutside(t);
        dialog.setCanceledOnTouchOutside(t);
    }


    public void dismiss() {
        dialog.dismiss();
        isShow = false;
    }

    public void show(){
        dialog.show();
        isShow = true;
    }

    public boolean isDialogShow(){
        return isShow;
    }



//    public interface

    public interface OnInitComponent {
        public void initComponent(Dialog dialog);
    }

}
