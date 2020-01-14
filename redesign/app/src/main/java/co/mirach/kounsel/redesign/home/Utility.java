package co.mirach.kounsel.redesign.home;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

import java.util.ArrayList;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.home.model.CategoryItemDetailsModel;
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

//    public static final Uri getUriToResource(@NonNull Context context,
//                                             @AnyRes int resId)
//            throws Resources.NotFoundException {
//        /** Return a Resources instance for your application's package. */
//        Resources res = context.getResources();
//        /**
//         * Creates a Uri which parses the given encoded URI string.
//         * @param uriString an RFC 2396-compliant, encoded URI
//         * @throws NullPointerException if uriString is null
//         * @return Uri for this given uri string
//         */
//        Uri resUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + res.getResourcePackageName(resId)
//                + '/' + res.getResourceTypeName(resId)
//                + '/' + res.getResourceEntryName(resId));
//        /** return uri */
//        return resUri;
//    }
}
