package aouyen.bacem.checkout;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Bacem on 08/04/2018.
 */
public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes ;
    public TextView dia ;
    public TextView content;
    public TextView markt;
    public TextView semestert;
    public TextView  typet;
    public ImageView emoj ;

    String subject ;
    int type ;
    int   mark ;
    String note ;
    int semester ;
    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mark_detail_layout);
        yes = (Button) findViewById(R.id.quit_dialog_id);
        yes.setOnClickListener(this);
        dia = (TextView) findViewById(R.id.txt_dia);
        dia.setText(subject);
        content = (TextView) findViewById(R.id.note_id_id);
        content.setText(note);
        content.setMovementMethod(new ScrollingMovementMethod());
        typet =(TextView) findViewById(R.id.type_detail_id);
        typet.setText(type==0?"Type: Test":"Type: Exam");
       semestert =(TextView) findViewById(R.id.semsester_setails_id);
        semestert.setText(semester==1?"First Semester":"Second Semester");
        markt =(TextView) findViewById(R.id.mark_detail_id);
        markt.setText(Integer.toString(mark / 100)+"/20");
     emoj= (ImageView) findViewById(R.id.mark_emoji_id);
          if (mark >=1800 )
          {
              emoj.setImageResource(R.drawable.happy);
              markt.setTextColor(Color.parseColor("#203b08"));
          }
          else if (mark<1800 && mark >1500 )
          {
              emoj.setImageResource(R.drawable.happy_little);
              markt.setTextColor(Color.parseColor("#645609"));
          }
          else if (mark<=1500 && mark >1300 )
          {
              emoj.setImageResource(R.drawable.hapy_very_little);
              markt.setTextColor(Color.parseColor("#643009"));
          }
          else if (mark<=1300&& mark >=1000 )
          {
              emoj.setImageResource(R.drawable.not_happy);
              markt.setTextColor(Color.parseColor("#641809"));
          }
          else if (mark<1000 && mark >=800 )
          {
              emoj.setImageResource(R.drawable.sad);
              markt.setTextColor(Color.parseColor("#ffae00"));
          }
          else
          {
              markt.setTextColor(Color.parseColor("#ff0000"));
              emoj.setImageResource(R.drawable.cry) ;
          }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.quit_dialog_id:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }



    public void setType(int type) {
     this.type = type;
    }


    public void setMark(int mark) {
        this.mark= mark;
    }



    public void setNote(String note) {
        this.note=note;
    }



    public void setSemester(int semester) {
        this.semester=semester;
    }



    public void setSubject(String subject) {
  this.subject=subject;
    }
}