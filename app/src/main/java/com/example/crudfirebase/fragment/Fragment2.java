package com.example.crudfirebase.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudfirebase.R;
import com.example.crudfirebase.adapter.ItemOrderRecycleAdapter;
import com.example.crudfirebase.model.DonHang;
import com.example.crudfirebase.session.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment2 extends Fragment {
    ItemOrderRecycleAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<DonHang> listdonhang;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_order_detail, container, false);
        // Inflate the layout for this fragment

        recyclerView = view.findViewById(R.id.recyclerViewOrder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listdonhang = new ArrayList<>();
        readData();

        return view;

    }
    private void readData() {
        SessionManagement sessionManagement = new SessionManagement(getActivity());
        String username = sessionManagement.getUser();

        DatabaseReference proRef = FirebaseDatabase.getInstance().getReference("Order");

        proRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listdonhang.clear();
                Iterable<DataSnapshot> userSnapshots = snapshot.getChildren();
                for (DataSnapshot userSnapshot : userSnapshots) {
                    DonHang donhang = userSnapshot.getValue(DonHang.class);
                    if(username.equals(donhang.getIdUser())&&donhang.getTrangthai().equals("1")){
                        listdonhang.add(donhang);
                    }
                }
                adapter = new ItemOrderRecycleAdapter(getActivity(),listdonhang);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}