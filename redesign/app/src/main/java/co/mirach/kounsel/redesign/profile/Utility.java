package co.mirach.kounsel.redesign.profile;

import android.content.Context;

import java.util.ArrayList;

import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.home.model.CounselorModel;

public class Utility {

    public static ArrayList<CounselorModel> getCounselorCategories(final Context context){
        ArrayList<CounselorModel> counselorModels = new ArrayList<>();

        CounselorModel counselorModel = new CounselorModel(
                "Nannie Moreno",
                "Clinical Social Worker","15","3.7",
                context.getResources().getDrawable(R.drawable.photo));
        counselorModels.add(counselorModel);


        counselorModel = new CounselorModel(
                "Roy Patrick",
                "Educational Psychologist","15","3.9",
                context.getResources().getDrawable(R.drawable.photo));
        counselorModels.add(counselorModel);


        counselorModel = new CounselorModel(
                "Nannie Moreno",
                "Clinical Social Worker","15","3.9",
                context.getResources().getDrawable(R.drawable.photo));
        counselorModels.add(counselorModel);
        counselorModels.add(counselorModel);
        counselorModels.add(counselorModel);
        counselorModels.add(counselorModel);
        return counselorModels;
    }
}
