package co.mirach.kounsel.redesign.home.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.home.model.CategoryItemDetailsModel;

public class MostPopularViewModel extends AndroidViewModel {

    private static final String TAG = "CategoryViewModel";
    private static final String MOST_POPULAR_CATEGORY_PATH = "/app/home";
    private static final String POPULAR_CATEGORIES = "popular_categories";

    private PopularCategoryListLiveData mPopularCategories;

    public MostPopularViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<ArrayList<CategoryItemDetailsModel>> getCategories(Context context) {
        if (mPopularCategories == null) {
            mPopularCategories = new PopularCategoryListLiveData();
            mPopularCategories.loadCategories(context);
        }
        return mPopularCategories;
    }

    class PopularCategoryListLiveData extends MutableLiveData<ArrayList<CategoryItemDetailsModel>> {

        private void loadCategories(final Context context){
            DocumentReference docRef = FirebaseFirestore.getInstance().document(MOST_POPULAR_CATEGORY_PATH);
            docRef.get(Source.CACHE).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists() ) {
                            Map<String, Object> mPopularCategoriesMap = document.getData();
                            ArrayList<String> popularCategoriesArrayList =
                                    (ArrayList<String>) mPopularCategoriesMap.get(POPULAR_CATEGORIES);
                            ArrayList<CategoryItemDetailsModel> categoryItems = new ArrayList<>();
                            for(int i = 0;i<popularCategoriesArrayList.size();i++){
                                CategoryItemDetailsModel categoryItemDetailsModel =
                                        new CategoryItemDetailsModel(
                                                popularCategoriesArrayList.get(i),
                                                context.getResources().getDrawable(R.drawable.therapy_popular),
                                                R.color.greyblue,2);
                                categoryItems.add(categoryItemDetailsModel);
                            }
                            setValue(categoryItems);
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            setValue(null);
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        setValue(null);
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

    }
}
