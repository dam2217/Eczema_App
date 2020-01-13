package com.example.eczema_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eczema_app.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

//
//        final CardView card1 = findViewById(R.id.cardView1);
//        final CardView card2 = findViewById(R.id.cardView2);
//        final CardView card3 = findViewById(R.id.cardView3);
//
////        int entries = logList.size();
////        if (entries < 3){
////            if (entries == 1){
////                card3.setVisibility(View.GONE);
////                card2.setVisibility(View.GONE);
////            }else if (entries == 2){
////                card3.setVisibility(View.GONE);
////            }
////        }
//
//
//        final TextView date1 = findViewById((R.id.date_1));
////       TODO: SET DATE TO THE ONE OF THE LOG FROM DATABASE
////        System.out.println(logList.get(e));
////        date1.setText(logList.get(entries - 1).date);
//
//        final TextView noLocations1 = findViewById(R.id.no_locations_1);
//
//        //   TODO: UNCOMMENT THE BELOW AND INCLUDE THE ARRAY LENGTH TO SHOW THE NUMBER OF INFLAMED AREAS FROM DATABASE
//        //   String numberOfLocations1 = String.valueOf("Length of array holding affected areas");
//        //   noLocations1.setText("Locations: " + numberOfLocations1);
//
//        final TextView treatment1 = findViewById(R.id.treatment_1);
//
////        TODO: UNCOMMENT THE BELOW AND SET THE TREATMENT TYPE FROM DATABASE, LIST THE TREATED AREAS FROM THE DATABASE -
////         ****** REPRESENTS LINE THAT NEEDS CHANGING
////      ******  if ("Treatment used is true"){
////            treatment1.setText("Treatment Used");
////            treatment1.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
////       *******      String treatmentType = "treatment type"
////                    inflammationLocations.setTitle("Treatment: " + treatmentType);
////       *******      inflammationLocations.setMessage("List of treated areas for most recent entry");
////                    inflammationLocations.show();
////                }
////            });
////        } else {
////            treatment1.setText("No Treatment Used");
////        }
//
//        final TextView notes1None = findViewById(R.id.notes_1_none);
//        final Button notes1 = findViewById(R.id.notes_1);
////        TODO: UNCOMMENT BELOW,
////         CHANGE INPUT FOR IF STATEMENT TO RELEVANT FIELD FROM DATABASE,
////         GET NOTES FROM DATABASE
////     *******   if ("notes field is empty"){
////            notes1.setVisibility(View.GONE);
////            notes1None.setVisibility(View.VISIBLE);
////        } else {
////            notes1.setVisibility(View.VISIBLE);
////            notes1None.setVisibility(View.GONE);
////            notes1.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
////                    inflammationLocations.setTitle("Notes");
////           ******** inflammationLocations.setMessage("notes for most recent entry");
////                    inflammationLocations.show();
////                }
////            });
////        }
//
//
//
//
//        //creating activity of AlertDialog
////        noLocations1.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            final AlertDialog inflammationLocations = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme).create();
//            inflammationLocations.setTitle("Inflammations");
//// TODO: LIST OF LOCATIONS AND SEVERITY FROM DATABASE
//            inflammationLocations.setMessage("Insert list of locations and severity for most recent entry ");
//            inflammationLocations.show();
//        }
//    });
//
//
//
//    final TextView date2 = findViewById((R.id.date_2));
////        TODO: BELOW
//        date2.setText("get date");
//
//    final TextView noLocations2 = findViewById(R.id.no_locations_2);
//    String numberOfLocations2 = String.valueOf("Length of array holding affected areas");
//        noLocations2.setText("Locations: " + numberOfLocations2);
//
//    final TextView treatment2 = findViewById(R.id.treatment_2);
//
////        TODO: UNCOMMENT THE BELOW AND SET THE TREATMENT TYPE FROM DATABASE, LIST THE TREATED AREAS FROM THE DATABASE
////       ***** if ("Treatment used is true"){
////            treatment2.setText("Treatment Used");
////            treatment2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
////         ******  String treatmentType = "treatment type"
//////                    inflammationLocations.setTitle("Treatment: " + treatmentType);
//////       *******      inflammationLocations.setMessage("List of treated areas for most recent entry");
//////                    inflammationLocations.show();
////            }
////        });
////        } else {
////            treatment2.setText("No Treatment Used");
////        }
//
//    final TextView notes2None = findViewById(R.id.notes_2_none);
//    final Button notes2 = findViewById(R.id.notes_2);
////        TODO: UNCOMMENT BELOW,
////         CHANGE INPUT FOR IF STATEMENT TO RELEVANT FIELD FROM DATABASE,
////         GET NOTES FROM DATABASE
//
////  ******  if ("notes field is empty"){
////            notes2.setVisibility(View.GONE);
////            notes2None.setVisibility(View.VISIBLE);
////        } else {
////            notes2.setVisibility(View.VISIBLE);
////            notes2None.setVisibility(View.GONE);
////            notes2.setOnClickListener(new View.OnClickListener() {
//    //            @Override
//    //            public void onClick(View v) {
//    //                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
//    //                inflammationLocations.setTitle("Notes");
//    //       ******   inflammationLocations.setMessage("notes for  second entry");
//    //                inflammationLocations.show();
//    //            }
//    //        });
////        }
//
//
//////        noLocations2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                final AlertDialog inflammationLocations = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme).create();
////                inflammationLocations.setTitle("Inflammations");
//////      TODO: GET LIST OF LOCATIONS AND SEVERITY FOR SECOND-MOST RECENT ENTRY IN DATABASE
////                inflammationLocations.setMessage("Insert list of locations and severity for second entry ");
////                inflammationLocations.show();
////            }
////        });
//
//
//    final TextView date3 = findViewById((R.id.date_3));
////        TODO: BELOW
//        date3.setText("get date");
//
//    final TextView noLocations3 = findViewById(R.id.no_locations_3);
//    //        TODO: BELOW
//    String numberOfLocations3 = String.valueOf("Length of array holding affected areas");
//        noLocations3.setText("Locations: " + numberOfLocations3);
//
//    final TextView treatment3 = findViewById(R.id.treatment_3);
////        TODO: BELOW
////       ******* if ("Treatment used is true"){
////            treatment3.setText("Treatment Used");
////            treatment3.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
////               ******  String treatmentType = "treatment type"
////////                    inflammationLocations.setTitle("Treatment: " + treatmentType);
////////       *******      inflammationLocations.setMessage("List of treated areas for most recent entry");
////////                    inflammationLocations.show();
////            }
////        });
////        } else {
////            treatment3.setText("No Treatment Used");
////        }
//
//    final Button notes3 = findViewById(R.id.notes_3);
//    final TextView notes3None = findViewById(R.id.notes_3_none);
////        TODO: BELOW
////  ******      if ("notes field is empty"){
////            notes3.setVisibility(View.GONE);
////            notes3None.setVisibility(View.VISIBLE);
////        } else {
////            notes3.setVisibility(View.VISIBLE);
////            notes3None.setVisibility(View.GONE);
//    //        notes3.setOnClickListener(new View.OnClickListener() {
//    //            @Override
//    //            public void onClick(View v) {
//    //                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
//    //                inflammationLocations.setTitle("Notes");
//    //     ******     inflammationLocations.setMessage("notes for third entry");
//    //                inflammationLocations.show();
//    //            }
//    //        });
////        }
//
//
//
//
//
//////        noLocations3.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                final AlertDialog inflammationLocations = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme).create();
////                inflammationLocations.setTitle("Inflammations");
//////                TODO: BELOW
////                inflammationLocations.setMessage("Insert list of locations and severity for third entry");
////                inflammationLocations.show();
////            }
////        });

        return root;
    }


}

