package com.bignerdranch.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by sanfer on 8/28/17.
 */

public class PhotoViewFragment extends DialogFragment {

    private static String ARG_FILEURI = "file_uri";

    private File mPhotoFile;
    private ImageView mPhotoView;

    public static PhotoViewFragment newInstance(File photo_file) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FILEURI, photo_file);

        PhotoViewFragment f = new PhotoViewFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photoview, null);
        mPhotoView = (ImageView) v.findViewById(R.id.photo_view);
        mPhotoFile = (File) getArguments().getSerializable(ARG_FILEURI);
        updatePhoto();
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.photo_preview)
                .setPositiveButton(R.string.back, null)
                .setView(v)
                .create();
    }

    private void updatePhoto() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap img = PictureUtils.getOriginalBitmap(mPhotoFile.getPath());
            mPhotoView.setImageBitmap(img);
        }
    }

}
