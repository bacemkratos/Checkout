package aouyen.bacem.checkout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Loadingpage extends AppCompatActivity {
    private static int  laodcount;
    private DatabaseReference myRef =FirebaseDatabase.getInstance().getReference();
   private StorageReference storageRef ;
    private TextView loadingDialog ;
  public static  ClassRoom myclassroom;
   public static   Kid mykid ;
    public static ArrayList<News> news= new ArrayList<>();
    private TextView loadingDialog2 ;
    public static String name ;
    public static String userId ;
   public  File localFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myRef =FirebaseDatabase.getInstance().getReference();
        laodcount = 0;
           storageRef= FirebaseStorage.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingpage);
        name = getIntent().getStringExtra("name");
        userId = getIntent().getStringExtra("id");
        loadingDialog = (TextView)findViewById(R.id.id_load);
        loadingDialog2 = (TextView)findViewById(R.id.id_load2);
        loadingDialog.setText("Loading Data Of "+name+"...");
        loadingDialog2.setText("Loading Profil Photo...");

        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference  islandRef = storageRef.child(userId+"/"+name.toLowerCase()+"/face.jpg");
        islandRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        laodcount++;

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                loadingDialog2.setText("Loading info...");
                              mykid=     dataSnapshot.child("kids").child(userId).child(name).getValue(Kid.class);
                                 laodcount++;
                                 news.clear();
                                loadingDialog2.setText("Loading News...");
                                 for ( DataSnapshot newo: dataSnapshot.child("news").getChildren())
                                 {
                                     if ( checkNewsConcern(mykid ,newo.getValue(News.class)) )
                      news.add(              newo.getValue(News.class)) ;
                                 }
                                laodcount++;
                                loadingDialog2.setText("Loading ClassRoom data...");
                                myclassroom= dataSnapshot.child("classroom").child(mykid.getClassRoom()).getValue(ClassRoom.class);
                                laodcount++;
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }) ;

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(),"Download failed",Toast.LENGTH_LONG).show();
            }
        });

        new Thread()
        {
            public void run() {
                while (true)
                {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (laodcount == 4) {
                        Intent myintent =  new Intent(getApplicationContext(),ProfilActivity.class);
                        myintent.putExtra("name", name);
                        myintent.putExtra("id",userId) ;
                        myintent.putExtra("photo",localFile.getAbsolutePath()) ;
                        Log.d("123456",mykid.getMarks().get(0).getSubject()) ;
                        startActivity(myintent);
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }.start();
    }
    public boolean checkNewsConcern (Kid ref1 , News ref2)
    {     boolean result = false;
        for (String tag : ref2.getTags() ) {

            if ( tag.equalsIgnoreCase(ref1.getClassRoom())  || tag.equalsIgnoreCase("school"))
                result=true;
        }
        return result ;
    }
}
