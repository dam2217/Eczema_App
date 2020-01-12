package com.example.eczema_app.ui.home;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class LoggedDataEntry implements Parcelable {
    public String date;
    public String time;
    public String hf;
    public String hb;
    public String tf;
    public String tb;
    public String raf;
    public String rab;
    public String laf;
    public String lab;
    public String rlf;
    public String rlb;
    public String llf;
    public String llb;
    public String treatmentYorN;
    public String treatmentUsed;
    public String temperature;
    public String humidity;
    public String pollutionLevel;
    public String pollenLevel;
    public String location;
    public String hfTreated;
    public String hbTreated;
    public String tfTreated;
    public String tbTreated;
    public String rafTreated;
    public String rabTreated;
    public String lafTreated;
    public String labTreated;
    public String rlfTreated;
    public String rlbTreated;
    public String llfTreated;
    public String llbTreated;
    public String notes;

    public LoggedDataEntry(){
        this.date = "";
        this.time = "";
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
        this.temperature = "";
        this.humidity = "";
        this.pollutionLevel = "";
        this.pollenLevel = "";
        this.location = "";
        this.hfTreated = "";
        this.hbTreated = "";
        this.tfTreated = "";
        this.tbTreated = "";
        this.rafTreated = "";
        this.rabTreated = "";
        this.lafTreated = "";
        this.labTreated = "";
        this.rlfTreated = "";
        this.rlbTreated = "";
        this.llfTreated = "";
        this.llbTreated = "";
        this.notes = "";
    }

    public LoggedDataEntry(String dateNow, String timeNow, String headf, String headb, String torsof,
                          String torsob, String rarmf,
                          String rarmb, String larmf, String larmb,
                          String rlegf, String rlegb,
                          String llegf, String llegb, String treatmentyesorno,
                          String treatmentu, String temp, String hum, String pollution,
                          String pollen, String loc, String hft, String hbt, String tft, String tbt,
                          String raft, String rabt, String laft, String labt, String rlft,
                          String rlbt, String llft, String llbt, String n){

        this.date = dateNow;
        this.time = timeNow;
        this.hf = headf;
        this.hb = headb;
        this.tf = torsof;
        this.tb = torsob;
        this.raf = rarmf;
        this.rab = rarmb;
        this.laf = larmf;
        this.lab = larmb;
        this.rlf = rlegf;
        this.rlb = rlegb;
        this.llf = llegf;
        this.llb = llegb;
        this.treatmentYorN = treatmentyesorno;
        this.treatmentUsed = treatmentu;
        this.temperature = temp;
        this.humidity = hum;
        this.pollutionLevel = pollution;
        this.pollenLevel = pollen;
        this.location = loc;
        this.hfTreated = hft;
        this.hbTreated = hbt;
        this.tfTreated = tft;
        this.tbTreated = tbt;
        this.rafTreated = raft;
        this.rabTreated = rabt;
        this.lafTreated = laft;
        this.labTreated = labt;
        this.rlfTreated = rlft;
        this.rlbTreated = rlbt;
        this.llfTreated = llft;
        this.llbTreated = llbt;
        this.notes = n;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setHf(String hf) {
        this.hf = hf;
        Log.i("head front", hf);
    }

    public void setHb(String hb){
        this.hb = hb;
        Log.i("head back", hb);
    }

    public void setTf(String tf) {
        this.tf = tf;
        Log.i("torso front", tf);
    }

    public void setTb(String tb) {
        this.tb = tb;
        Log.i("torso back", tb);
    }

    public void setRaf(String raf) {
        this.raf = raf;
        Log.i("right arm front", raf);
    }

    public void setRab(String rab) {
        this.rab = rab;
        Log.i("right arm back", rab);
    }

    public void setLaf(String laf) {
        this.laf = laf;
        Log.i("left arm front", laf);
    }

    public void setLab(String lab) {
        this.lab = lab;
        Log.i("left arm back", lab);
    }

    public void setRlf(String rlf) {
        this.rlf = rlf;
        Log.i("right leg front", rlf);
    }

    public void setRlb(String rlb) {
        this.rlb = rlb;
        Log.i("right leg back", rlb);
    }

    public void setLlf(String llf) {
        this.llf = llf;
        Log.i("left leg front", llf);
    }

    public void setLlb(String llb) {
        this.llb = llb;
        Log.i("left leg back", llb);
    }

    public void setTreatmentYorN(String treatmentYorN) {
        this.treatmentYorN = treatmentYorN;
        Log.i("treatmentYorN", treatmentYorN);
    }

    public void setTreatmentUsed(String treatmentUsed) {
        this.treatmentUsed = treatmentUsed;
        Log.i("treatmentUsed", treatmentUsed);
    }

    public void setTemperature(String temperatureVal){
        this.temperature = temperatureVal;
        Log.i("temperature", temperature);
    }

    public void setHumidity(String humidityVal){
        this.humidity = humidityVal;
        Log.i("humidity", humidity);
    }

    public void setPollutionLevel(String pollutionAQI){
        this.pollutionLevel = pollutionAQI;
        Log.i("pollutionLevel", pollutionLevel);
    }

    public void setPollenLevel(String pollenIndex){
        this.pollenLevel = pollenIndex;
        Log.i("pollenLevel", pollenLevel);
    }

    public String getHf() {
        return hf;
    }

    public String getHb() {
        return hb;
    }

    public String getTf() {
        return tf;
    }

    public String getTb() {
        return tb;
    }

    public String getRaf() {
        return raf;
    }

    public String getRab() {
        return rab;
    }

    public String getLaf() {
        return laf;
    }

    public String getLab() {
        return lab;
    }

    public String getRlf() {
        return rlf;
    }

    public String getRlb() {
        return rlb;
    }

    public String getLlf() {
        return llf;
    }

    public String getLlb() {
        return llb;
    }

    public void setLocation(String city){
        this.location = city;

        Log.i("location", location);
    }

    public String getHbTreated() {
        return hbTreated;
    }

    public String getHfTreated() {
        return hfTreated;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getLocation() {
        return location;
    }

    public String getLabTreated() {
        return labTreated;
    }

    public String getLafTreated() {
        return lafTreated;
    }

    public String getLlbTreated() {
        return llbTreated;
    }

    public String getLlfTreated() {
        return llfTreated;
    }

    public String getPollenLevel() {
        return pollenLevel;
    }

    public String getPollutionLevel() {
        return pollutionLevel;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getRabTreated() {
        return rabTreated;
    }

    public String getRafTreated() {
        return rafTreated;
    }

    public String getRlbTreated() {
        return rlbTreated;
    }

    public String getTreatmentUsed() {
        return treatmentUsed;
    }

    public String getRlfTreated() {
        return rlfTreated;
    }

    public String getTbTreated() {
        return tbTreated;
    }

    public String getTfTreated() {
        return tfTreated;
    }

    public String getTreatmentYorN() {
        return treatmentYorN;
    }

    public void setHbTreated(boolean treatmentUsed){
        this.hbTreated = String.valueOf(treatmentUsed);
        Log.i("hbTreated", hbTreated);
    }

    public void setHfTreated(boolean treatmentUsed){
        this.hfTreated = String.valueOf(treatmentUsed);
        Log.i("hfTreated", hfTreated);
    }

    public void setTbTreated(boolean treatmentUsed){
        this.tbTreated = String.valueOf(treatmentUsed);
        Log.i("tbTreated", tbTreated);

    }

    public void setTfTreated(boolean treatmentUsed){
        this.tfTreated = String.valueOf(treatmentUsed);
        Log.i("tfTreated", tfTreated);
    }

    public void setLabTreated(boolean treatmentUsed){
        this.labTreated = String.valueOf(treatmentUsed);
        Log.i("labTreated", labTreated);
    }

    public void setLafTreated(boolean treatmentUsed){
        this.lafTreated = String.valueOf(treatmentUsed);
        Log.i("lafTreated", lafTreated);
    }

    public void setLlbTreated(boolean treatmentUsed){
        this.llbTreated = String.valueOf(treatmentUsed);
        Log.i("llbTreated", llbTreated);
    }

    public void setLlfTreated(boolean treatmentUsed){
        this.llfTreated = String.valueOf(treatmentUsed);
        Log.i("llfTreated", llfTreated);
    }

    public void setRlbTreated(boolean treatmentUsed){
        this.rlbTreated = String.valueOf(treatmentUsed);
        Log.i("rlbTreated", rlbTreated);
    }

    public void setRlfTreated(boolean treatmentUsed){
        this.rlfTreated = String.valueOf(treatmentUsed);
        Log.i("rlfTreated", rlfTreated);
    }

    public void setRabTreated(boolean treatmentUsed){
        this.rabTreated = String.valueOf(treatmentUsed);
        Log.i("rabTreated", rabTreated);
    }

    public void setRafTreated(boolean treatmentUsed){
        this.rafTreated = String.valueOf(treatmentUsed);
        Log.i("rafTreated", rafTreated);
    }

    public void setNotes(String extraInfo){
        this.notes = extraInfo;
        Log.i("notes", notes);
    }

    public String getNotes() {
        return this.notes;
    }

    //parcel part
    public LoggedDataEntry(Parcel in){
        String[] data= new String[34];

        in.readStringArray(data);
        this.date= data[0];
        this.date= data[1];
        this.hf= data[2];
        this.hb= data[3];
        this.tf= data[4];
        this.tb= data[5];
        this.raf= data[6];
        this.rab= data[7];
        this.laf= data[8];
        this.lab= data[9];
        this.rlf= data[10];
        this.rlb= data[11];
        this.llf= data[12];
        this.llb= data[13];
        this.treatmentYorN= data[14];
        this.treatmentUsed= data[15];
        this.pollenLevel = data[16];
        this.pollutionLevel = data[17];
        this.humidity = data[18];
        this.temperature = data[19];
        this.location = data[20];
        this.hfTreated = data[21];
        this.hbTreated = data[22];
        this.tfTreated = data[23];
        this.tbTreated = data[24];
        this.rafTreated = data[25];
        this.rabTreated = data[26];
        this.lafTreated = data[27];
        this.labTreated = data[28];
        this.rlfTreated = data[29];
        this.rlbTreated = data[30];
        this.llfTreated = data[31];
        this.llbTreated = data[32];
        this.notes = data[33];


    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

        dest.writeStringArray(new String[]{this.date, this.time, String.valueOf(this.hf), String.valueOf(this.hb),
                String.valueOf(this.tf), String.valueOf(this.tb), String.valueOf(this.raf),
                String.valueOf(this.rab), String.valueOf(this.laf), String.valueOf(this.lab),
                String.valueOf(this.rlf), String.valueOf(this.rlb), String.valueOf(this.llf),
                String.valueOf(this.llb), this.treatmentYorN, this.treatmentUsed, this.pollenLevel,
                this.pollutionLevel, this.humidity, this.temperature, this.location, this.hfTreated,
                this.hbTreated, this.tfTreated, this.tbTreated, this.rafTreated, this.rabTreated,
                this.lafTreated, this.labTreated, this.rlfTreated, this.rlbTreated, this.llfTreated,
                this.llbTreated, this.notes});
    }

    public static final Parcelable.Creator<LoggedDataEntry> CREATOR= new Parcelable.Creator<LoggedDataEntry>() {
        @Override
        public LoggedDataEntry createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new LoggedDataEntry(source);  //using parcelable constructor
        }

        @Override
        public LoggedDataEntry[] newArray(int size) {
            // TODO Auto-generated method stub
            return new LoggedDataEntry[size];
        }
    };
}



