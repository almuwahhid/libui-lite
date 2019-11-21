/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019. Al Muwahhid
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

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;


public class AlertDialogBuilder {
    Context context;
    OnAlertDialog onAlertDialog;
    String title = "";
    String ok_message = "";
    String cancel_message = "";
    AlertDialog.Builder alertDialog;

    public AlertDialogBuilder(Context context, String title, String ok_message, String cancel_message, OnAlertDialog onAlertDialog) {
        this.context = context;
        this.cancel_message = cancel_message;
        this.ok_message = ok_message;
        this.title = title;
        this.onAlertDialog = onAlertDialog;
        initAlert(true);
    }

    public AlertDialogBuilder(Context context, String title, String ok_message, OnAlertDialog onAlertDialog) {
        this.context = context;
        this.ok_message = ok_message;
        this.title = title;
        this.onAlertDialog = onAlertDialog;
        initAlert(false);
    }

    private void initAlert(boolean isCancellabel){
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(title);
        alertDialog.setPositiveButton(ok_message, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onAlertDialog.onPositiveButton(dialog);
            }
        });
        if(isCancellabel){
            alertDialog.setNegativeButton(cancel_message, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onAlertDialog.onNegativeButton(dialog);
                }
            });
        }
        alertDialog.show();
    }

    public void show(){
        alertDialog.show();
    }

    public interface OnAlertDialog{
        void onPositiveButton(DialogInterface dialog);
        void onNegativeButton(DialogInterface dialog);
    }

}
