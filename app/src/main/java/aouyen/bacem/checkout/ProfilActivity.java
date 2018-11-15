package aouyen.bacem.checkout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class ProfilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ConstraintLayout home ;
    ConstraintLayout education ;
    ConstraintLayout markks ;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private RecyclerView mRecyclerView3;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.Adapter mAdapter3;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager2;
    private RecyclerView.LayoutManager mLayoutManager3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        education = (ConstraintLayout) findViewById(R.id.education_id) ;
        home = (ConstraintLayout) findViewById(R.id.home_id) ;
        markks= (ConstraintLayout) findViewById(R.id.markks) ;
        mRecyclerView = (RecyclerView) findViewById(R.id.news_list_id);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.ed_list_id);
        mRecyclerView3 = (RecyclerView) findViewById(R.id.mark_list_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView3.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager3 = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView2.setLayoutManager(mLayoutManager2);
        mRecyclerView3.setLayoutManager(mLayoutManager3);
        mAdapter = new MyAdapter(Loadingpage.news);
        mAdapter2 = new MyAdapter2(Loadingpage.myclassroom.getSubjects());
        mAdapter3 = new MyAdapter3(Loadingpage.mykid.getMarks());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView2.setAdapter(mAdapter2);
        mRecyclerView3.setAdapter(mAdapter3);
        mRecyclerView2.addOnItemTouchListener(new RecyclerTouchListener(this,
                mRecyclerView2, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                ( (TextView)  findViewById(R.id.s_tit)).setText(Loadingpage.myclassroom.getSubjects().get(position).getName());
                ( (TextView)  findViewById(R.id.s_tit2)).setText("Current Module: "+Loadingpage.myclassroom.getSubjects().get(position).getCurrentModule());
                ( (TextView)  findViewById(R.id.s_tit3)).setText("Teacher: "+Loadingpage.myclassroom.getSubjects().get(position).getTeacher());
                ( (TextView)  findViewById(R.id.desc_id)).setText(Loadingpage.myclassroom.getSubjects().get(position).getDescription());
                ( (ProgressBar ) findViewById(R.id.progressBar3)).setProgress(Loadingpage.myclassroom.getSubjects().get(position).getCompletation());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Loadingpage.mykid.getFullName());
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.tewt_1_id);
        nav_user.setText(Loadingpage.mykid.getFullName());
        ImageView im = (ImageView)hView.findViewById(R.id.profilçod);
            im.setImageBitmap( BitmapFactory.decodeFile(getIntent().getStringExtra("photo")));
        ( (TextView)findViewById(R.id.name_id) ).setText("Name: "+Loadingpage.mykid.getFullName().toString());
        ( (TextView)findViewById(R.id.age_id) ).setText("Age: "+Loadingpage.mykid.getAge());
        ( (TextView)findViewById(R.id.classroom_id) ).setText("Classroom: "+Loadingpage.mykid.getClassRoom().toString());
        education.setVisibility(View.GONE);
        markks.setVisibility(View.GONE);
        home.setVisibility(View.VISIBLE);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
         home.setVisibility(View.VISIBLE);
         education.setVisibility(View.GONE);
          markks.setVisibility(View.GONE);
        } else if (id == R.id.nav_education) {
            home.setVisibility(View.GONE);
            education.setVisibility(View.VISIBLE);
            markks.setVisibility(View.GONE);
        } else if (id == R.id.nav_slideshow) {
            home.setVisibility(View.GONE);
            education.setVisibility(View.GONE);
            markks.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(getApplicationContext(),UserChoice.class));
        } else if (id == R.id.nav_send) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<News> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public TextView mTextView2;
            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView)view.findViewById(R.id.news_title_id);
                mTextView2 = (TextView)view.findViewById(R.id.news_body_id);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<News> myDataset) {
           mDataset = myDataset;

        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

          LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
          View row = inflater.inflate(R.layout.news_item_layout,parent , false) ;
          ViewHolder v = new ViewHolder(row) ;
             return  v ;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset.get(mDataset.size()-position-1).getTitle());
            holder.mTextView2.setText(mDataset.get(mDataset.size()-position-1).getDescription());
            switch(mDataset.get(mDataset.size()-position-1).getType())
            {
                case 0 :  holder.mTextView.setTextColor(Color.parseColor("#452e2a")); holder.mTextView.setBackgroundResource(R.drawable.news_back_danger);  ;break;
                case 1 :  holder.mTextView.setTextColor(Color.parseColor("#7d7600"));holder.mTextView.setBackgroundResource(R.drawable.news_back_warning); break;
                case 2:   holder.mTextView.setTextColor(Color.parseColor("#fafdf3"));holder.mTextView.setBackgroundResource(R.drawable.news_back_normal); break;
                default: ;
            }

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size() ;
        }
    }
    public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
        private ArrayList<Subject> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
          public ProgressBar mybar ;
            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView)view.findViewById(R.id.module_d);
                 mybar = (ProgressBar) view.findViewById(R.id.bar_mdule);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter2(ArrayList<Subject> myDataset) {
            mDataset = myDataset;

        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
            View row = inflater.inflate(R.layout.subjects_item_layout,parent , false) ;
            ViewHolder v = new ViewHolder(row) ;
            return  v ;
        }

        // Replace the contents of a view (invoked by the layout manager)

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset.get(position).getName());
             holder.mybar.setProgress(mDataset.get(position).getCompletation());


        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size() ;
        }
    }
    public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder> {
        private ArrayList<Mark> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView1;
            public TextView mTextView2;
            public TextView mTextView3;
            public TextView mTextView4;
            public Button mybutton ;

            public ViewHolder(View view) {
                super(view);
                mTextView1= (TextView)view.findViewById(R.id.module_dd);
                mTextView2= (TextView)view.findViewById(R.id.note_id);
                mTextView3= (TextView)view.findViewById(R.id.smester_id);
                mTextView4= (TextView)view.findViewById(R.id.type_exam_id);
               mybutton =  (Button)view.findViewById(R.id.view_more_id);



            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter3(ArrayList<Mark> myDataset) {
            mDataset = myDataset;

        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
            View row = inflater.inflate(R.layout.marks_item_layout,parent , false) ;
            ViewHolder v = new ViewHolder(row) ;
            return  v ;
        }

        // Replace the contents of a view (invoked by the layout manager)

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView1.setText(mDataset.get(position).getSubject());

            holder.mTextView2.setText(Integer.toString(mDataset.get(position).getMark() / 100)+"/20");
            holder.mTextView3.setText(mDataset.get(position).getSemester()==1?"First Semester":"Second Semester");
            holder.mTextView4.setText(mDataset.get(position).getType()==0?"Test":"Exam");
            final int  pos = position;
            holder.mybutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomDialogClass cdd=new CustomDialogClass(ProfilActivity.this);
                    cdd.setSubject(mDataset.get(pos).getSubject());
                    cdd.setNote(mDataset.get(pos).getNote());
                    cdd.setMark(mDataset.get(pos).getMark());
                    cdd.setSemester(mDataset.get(pos).getSemester());
                    cdd.setType(mDataset.get(pos).getType());
                    cdd.show();
                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size() ;
        }
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {

                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
