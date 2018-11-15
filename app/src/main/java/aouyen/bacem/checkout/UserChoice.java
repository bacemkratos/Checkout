package aouyen.bacem.checkout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserChoice extends AppCompatActivity {
    private FirebaseAuth auth;
   private   FirebaseDatabase database ;
   private FirebaseUser user ;
   private TextView mygreeting  ;
   private String id ;
   private  users myuser  ;
   private myadapter adapt ;
  private   DatabaseReference myRef ;
    private ArrayList<String> mykids = new ArrayList<>();
    private ListView mylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        myRef =FirebaseDatabase.getInstance().getReference();

         id=user.getUid();



        setContentView(R.layout.choice_activity_layout);
     mylist = (ListView)findViewById(R.id.choose_list_id) ;
     mygreeting = (TextView)findViewById(R.id.greet_id) ;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String name =dataSnapshot.child("users").child(id).getValue(users.class).getName();
                String prenom = dataSnapshot.child("users").child(id).getValue(users.class).getPrenom();
                String gender =dataSnapshot.child("users").child(id).getValue(users.class).getGender();
                   myuser = new users(name , prenom, gender) ;
                mygreeting.setText(setGreeting(myuser));

              for(DataSnapshot a : dataSnapshot.child("kids").child(id).getChildren())
                {
                    mykids.add(a.getKey().toString()) ;

                }



                adapt = new myadapter() ;
                mylist.setAdapter(adapt);

                mylist.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
                    {
                        Intent myintent =  new Intent(getApplicationContext(),Loadingpage.class);
                        myintent.putExtra("name", mykids.get(position));
                      myintent.putExtra("id",id) ;
                      startActivity(myintent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @NonNull
 private String  setGreeting(users U)
  {  users  localuser = U ;
       String mr = (localuser.getGender().equals("m"))? getResources().getString(R.string.mr) : getResources().getString(R.string.mrs) ;
        return   getResources().getString(R.string.hello)+" "+mr+" "+localuser.getName()+"  "+localuser.getPrenom()+"\n"+getResources().getString(R.string.choose);

  }

    class myadapter extends BaseAdapter {


        @Override
        public int getCount() {

            return mykids.size();

        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.custom,null);
            TextView t = (TextView) view.findViewById(R.id.name_id);
            ImageView im = (ImageView)  view.findViewById(R.id.imageView);

            t.setText(mykids.get(i).toString());

            return view;
        }
    }
}
