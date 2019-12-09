package com.example.eczema_app.ui.log;

import android.util.Log;

class LogEntry {
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
    public CharSequence treatment;

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
        this.treatment = "";
    }

    public LogEntry(CharSequence hf, CharSequence hb, CharSequence tf, CharSequence tb, CharSequence raf, CharSequence rab, CharSequence laf, CharSequence lab, CharSequence rlf, CharSequence rlb, CharSequence llf, CharSequence llb) {
        this.hf = hf;
        this.hb = hb;
        this.tf = tf;
        this.tb = tb;
        this.raf = raf;
        this.rab = rab;
        this.laf = laf;
        this.lab = lab;
        this.rlf = rlf;
        this.rlb = rlb;
        this.llf = llf;
        this.llb = llb;
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

    public void setTreatment(CharSequence treatment) {
        this.treatment = treatment;
    }
}
