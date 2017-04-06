package com.example.hp_pc.aticustestone;

/**
 * Created by Hp-PC on 12/12/2016.
 */
import android.content.Intent;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;

import static android.support.v4.content.ContextCompat.startActivity;


public class BuilderManager {
    private static int[] imgResrses = new int[]{
            R.drawable.ic_action_list,
            R.drawable.ic_textsms,
            R.drawable.ic_phone,
            R.drawable.logottenspar
    };
    private static int imgResrsesIndex = 0;

    static int getImgResourses() {
        if (imgResrsesIndex >= imgResrses.length) imgResrsesIndex = 0;
        return imgResrses[imgResrsesIndex++];
    }

    private static int[] stringResourses = new int[]{
            R.string.dashboard,
            R.string.chatbtn,
            R.string.active,
            R.string.aboutbtn
    };
    private static int stringResourceIndex = 0;

    static int getStringRes(){
        if(stringResourceIndex>= stringResourses.length) stringResourceIndex=0;
        return stringResourses[stringResourceIndex++];
    }

    static TextInsideCircleButton.Builder getTextInsideCircleButtonBuilder() {
        return new TextInsideCircleButton.Builder()
                .normalImageRes(getImgResourses())
                .normalTextRes(getStringRes())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {

                    }
                });
    }
}




