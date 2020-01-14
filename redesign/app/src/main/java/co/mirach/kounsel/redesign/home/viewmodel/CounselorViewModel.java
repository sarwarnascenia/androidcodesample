package co.mirach.kounsel.redesign.home.viewmodel;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CounselorViewModel extends AndroidViewModel {

    private static final String TAG = "CategoryViewModel";
    private static final String COUNSELOR_PATH = "app/categories/counselors/counselors_count";

    private MutableLiveData<Map<String,Object>> mCounselorMap;
    private FirebaseFirestore mFirebaseFirestore;

    public CounselorViewModel(@NonNull Application application) {
        super(application);
        mFirebaseFirestore = FirebaseFirestore.getInstance();
    }

    public LiveData<Map<String,Object>> getCategories() {
        if (mCounselorMap == null) {
            mCounselorMap = new MutableLiveData<Map<String,Object>>();
            loadMaps();
        }
        return mCounselorMap;
    }

    private void loadMaps(){


        DocumentReference docRef = mFirebaseFirestore.document(COUNSELOR_PATH);
        docRef.get(Source.CACHE).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        mCounselorMap.setValue(document.getData());
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
