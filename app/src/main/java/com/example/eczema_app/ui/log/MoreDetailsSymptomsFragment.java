package com.example.eczema_app.ui.log;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


//import com.example.eczema_app.Eczema;
//import com.example.eczema_app.HerokuService;
import com.example.eczema_app.R;
//import com.example.eczema_app.Service;
//


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoreDetailsSymptomsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoreDetailsSymptomsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreDetailsSymptomsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private MoreDetailsSymptomsViewModel moreDetailsSymptomsViewModel;
    public String currentCity;

    public MoreDetailsSymptomsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moreDetailsSymptomsViewModel =
                ViewModelProviders.of(this).get(MoreDetailsSymptomsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_more_details_symptoms, container, false);
//        final TextView textView = root.findViewById(R.id.MoreDetails);
        final Button button = root.findViewById(R.id.save);
        final EditText locationText = root.findViewById(R.id.location);
//        moreDetailsSymptomsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
////                textView.setText(s);
//            }
//        });

        /* Reference 1 - taken from http://jkutner.github.io/2016/08/18/android-backend-api-heroku-retrofit.html */

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://eczema-app.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        final HerokuService hservice = retrofit.create(HerokuService.class);
//
//        final com.example.eczema_app.Service service = retrofit.create(com.example.eczema_app.Service.class);
//
//        final Eczema eczema = new Eczema();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Eczema entry = new Eczema();
//                Call<Eczema> createCall = service.create(entry);
//                createCall.enqueue(new Callback<Eczema>() {
//                    @Override
//                    public void onResponse(Call<Eczema> call, Response<Eczema> response) {
//                        Eczema newEntry = Eczema();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Eczema> call, Throwable t) {
//                        t.printStackTrace();
//                        textView.setText(t.getMessage());
//                    }
//                });
//
//            }
//
//        });

        Spinner dropDown = root.findViewById(R.id.whatTreatment);
        String[] treatments = new String[] {"No Treatment Used", "Corticosteroids", "Emollient", "Systematic Therapy", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, treatments);
//        ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(treatments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropDown.setAdapter(adapter);








        /* End of Reference 1 */

        return root;
    }


    // function to retrieve treatment dropdown option selected by user
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        parent.getItemAtPosition(pos);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreDetailsSymptomsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreDetailsSymptomsFragment newInstance(String param1, String param2) {
        MoreDetailsSymptomsFragment fragment = new MoreDetailsSymptomsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
public void onButtonPressed(Uri uri) {
    if (mListener != null) {
        mListener.onFragmentInteraction(uri);
    }
}
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

/** See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

