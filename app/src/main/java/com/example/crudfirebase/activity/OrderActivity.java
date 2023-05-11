package com.example.crudfirebase.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.crudfirebase.R;
import com.example.crudfirebase.adapter.ItemCartRecycleAdapter;
import com.example.crudfirebase.adapter.ItemOrderRecycleAdapter;
import com.example.crudfirebase.adapter.MyFragmentAdapter;
import com.example.crudfirebase.model.DonHang;
import com.example.crudfirebase.model.ItemCart;
import com.example.crudfirebase.session.SessionManagement;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MyFragmentAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);

        tabLayout.addTab(tabLayout.newTab().setText("Đang giao hàng"));
        tabLayout.addTab(tabLayout.newTab().setText("Thành công"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter1 = new MyFragmentAdapter(fragmentManager , getLifecycle());
        viewPager2.setAdapter(adapter1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        recyclerView = findViewById(R.id.recyclerViewOrder);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        listdonhang = new ArrayList<>();
 //       readData();

    }
//    private void readData() {
//        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
//        String username = sessionManagement.getUser();
//
//        DatabaseReference proRef = FirebaseDatabase.getInstance().getReference("Order");
//
//        proRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                listdonhang.clear();
//                Iterable<DataSnapshot> userSnapshots = snapshot.getChildren();
//                for (DataSnapshot userSnapshot : userSnapshots) {
//                    DonHang donhang = userSnapshot.getValue(DonHang.class);
//                    if(username.equals(donhang.getIdUser())){
//                        listdonhang.add(donhang);
//                    }
//                }
//                adapter = new ItemOrderRecycleAdapter(OrderActivity.this,listdonhang);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
}