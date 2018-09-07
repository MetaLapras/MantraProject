package com.pasistence.mantrafingerprint.Main;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Models.Rating;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

public class FeedbackActivity extends AppCompatActivity  implements RatingDialogListener{
    ImageView img;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        img = (ImageView)findViewById(R.id.feedback_img);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);





        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRatingDialog();
            }
        });
    }

    private void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(1)
                .setTitle("Rate this food")
                .setDescription("Please select some stars and give your feedback")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.colorPrimary)
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorAccent1)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(R.color.white)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
               /* .setWindowAnimation(R.style.MyDialogFadeAnimation)*/
                .create(FeedbackActivity.this)
                .show();
    }

    @Override
    public void onPositiveButtonClicked (int value, String comments) {
        //Fix User can Rate multiple Times
       /* final Rating rating = new Rating(
                String.valueOf(value),
                comments);*/

        Toast.makeText(this, "Submit the Rating with Comments", Toast.LENGTH_SHORT).show();

        /*ratingTbl.push()
                .setValue(rating)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(FeedbackActivity.this, "Thankyou For Submitting Feedback", Toast.LENGTH_SHORT).show();
                    }
                });*/


    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}
