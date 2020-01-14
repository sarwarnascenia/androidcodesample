package co.mirach.kounsel.redesign.home.viewmodel;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CategoryViewModel  extends AndroidViewModel {

    private static final String TAG = "CategoryViewModel";
    private static final String CATEGORY_PATH = "app/categories/mapping/category_map";

    private CategoryMapLiveData mCategoryMap;
    private FirebaseFirestore mFirebaseFirestore;

    private Map<String, Object> mCategoriesMap;
    private Map<String, Object> mCounselorMap;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        mFirebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void setCounselorMap(Map<String, Object> mCounselorMap_local){
        mCounselorMap = mCounselorMap_local;
    }

    public Map<String, Object> getCategoriesMap(){
        return mCategoriesMap;
    }
    public LiveData<ArrayList<String>> getCategories() {
        if (mCategoryMap == null) {
            mCategoryMap = new CategoryMapLiveData();
            mCategoryMap.loadCategories();
        }
        return mCategoryMap;
    }


    class CategoryMapLiveData extends LiveData<ArrayList<String>> {

        private void loadCategories(){
            DocumentReference docRef = mFirebaseFirestore.document(CATEGORY_PATH);
            docRef.get(Source.CACHE).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            mCategoriesMap = document.getData();
                            ArrayList<String> mainList = new ArrayList<>();
                            Map<String,Integer> subList = new HashMap();
                            //create all the sublist
                            for (Map.Entry<String,Object> entry : mCategoriesMap.entrySet()) {
                                ArrayList<String> tempArrayList = new ArrayList<>();
                                if(entry.getValue() instanceof List) {
                                    tempArrayList.addAll((Collection<? extends String>)entry.getValue());
                                    for(int i =0; i<tempArrayList.size();i++){
                                        if(!tempArrayList.get(i).equalsIgnoreCase(entry.getKey()))
                                            subList.put(tempArrayList.get(i),1);
                                    }
                                }
                                else {
                                }
                                mainList.add(entry.getKey());

                            }
                            // removed the list if it is not in the main list
                            for(int i = 0;i<mainList.size();i++){
                                if(subList.get(mainList.get(i)) !=null) {
                                    mainList.remove(i);
                                    i--;
                                }
                            }

                            //making another level of filter compare from the counselor list
                            for(int i = 0;i<mainList.size();i++){
                                boolean isFound = false;
                                ArrayList<String> tempArrayList = (ArrayList<String>) mCategoriesMap.get(mainList.get(i));
                                for(int j = 0; j<tempArrayList.size();j++){
                                    Log.d(TAG,"");
                                    if(mCounselorMap.get(tempArrayList.get(j)) != null ||mCounselorMap.get(mainList.get(i)) != null) {
                                        isFound = true;
                                        break;
                                    }
                                }
                                if(!isFound) {
                                    mainList.remove(i);
                                    i--;
                                }
                            }
                            mCategoryMap.setValue(mainList);
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

    }
}
