package com.example.eczema_app.ui.log;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class LogEntry implements Parcelable {
    public CharSequence hf;
    public CharSequence hb;
    public CharSequence tf;
    public CharSequence tb;
    public CharSequence raf;
    public CharSequence rab;
    public CharSequence laf;
    public CharSequence lab;
    public CharSequence rlf;
    public CharSequence rlb;
    public CharSequence llf;
    public CharSequence llb;
    public String treatmentYorN;
    public String treatmentUsed;

    public LogEntry(){
        this.hf = "";
        this.hb = "";
        this.tf = "";
        this.tb = "";
        this.raf = "";
        this.rab = "";
        this.laf = "";
        this.lab = "";
        this.rlf = "";
        this.rlb = "";
        this.llf = "";
        this.llb = "";
        this.treatmentYorN = "";
        this.treatmentUsed = "";
    }

    public void setHf(CharSequence hf) {
        this.hf = hf;
        Log.i("head front", hf.toString());
    }

    public void setHb(CharSequence hb){
        this.hb = hb;
        Log.i("head back", hb.toString());
    }

    public void setTf(CharSequence tf) {
        this.tf = tf;
        Log.i("torso front", tf.toString());
    }

    public void setTb(CharSequence tb) {
        this.tb = tb;
        Log.i("torso back", tb.toString());
    }

    public void setRaf(CharSequence raf) {
        this.raf = raf;
        Log.i("right arm front", raf.toString());
    }

    public void setRab(CharSequence rab) {
        this.rab = rab;
        Log.i("right arm back", rab.toString());
    }

    public void setLaf(CharSequence laf) {
        this.laf = laf;
        Log.i("left arm front", laf.toString());
    }

    public void setLab(CharSequence lab) {
        this.lab = lab;
        Log.i("left arm back", lab.toString());
    }

    public void setRlf(CharSequence rlf) {
        this.rlf = rlf;
        Log.i("right leg front", rlf.toString());
    }

    public void setRlb(CharSequence rlb) {
        this.rlb = rlb;
        Log.i("right leg back", rlb.toString());
    }

    public void setLlf(CharSequence llf) {
        this.llf = llf;
        Log.i("left leg front", llf.toString());
    }

    public void setLlb(CharSequence llb) {
        this.llb = llb;
        Log.i("left leg back", llb.toString());
    }

    public void setTreatmentYorN(String treatmentYorN) {
        this.treatmentYorN = treatmentYorN;
        Log.i("treatmentYorN", treatmentYorN);
    }

    public void setTreatmentUsed(String treatmentUsed) {
        this.treatmentUsed = treatmentUsed;
        Log.i("treatmentUsed", treatmentUsed);
    }

    //parcel part
    public LogEntry(Parcel in){
        String[] data= new String[14];

        in.readStringArray(data);
        this.hf= data[0];
        this.hb= data[1];
        this.tf= data[2];
        this.tb= data[3];
        this.raf= data[4];
        this.rab= data[5];
        this.laf= data[6];
        this.lab= data[7];
        this.rlf= data[8];
        this.rlb= data[9];
        this.llf= data[10];
        this.llb= data[11];
        this.treatmentYorN= data[12];
        this.treatmentUsed= data[13];

    }
    @Override
    public int describeContents() {
    // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    // TODO Auto-generated method stub

        dest.writeStringArray(new String[]{String.valueOf(this.hf), String.valueOf(this.hb),
                String.valueOf(this.tf), String.valueOf(this.tb), String.valueOf(this.raf),
                String.valueOf(this.rab), String.valueOf(this.laf), String.valueOf(this.lab),
                String.valueOf(this.rlf), String.valueOf(this.rlb), String.valueOf(this.llf),
                String.valueOf(this.llb), this.treatmentYorN, this.treatmentUsed});
    }

    public static final Parcelable.Creator<LogEntry> CREATOR= new Parcelable.Creator<LogEntry>() {
        @Override
        public LogEntry createFromParcel(Parcel source) {
        // TODO Auto-generated method stub
            return new LogEntry(source);  //using parcelable constructor
        }

        @Override
        public LogEntry[] newArray(int size) {
        // TODO Auto-generated method stub
            return new LogEntry[size];
        }
    };

}



