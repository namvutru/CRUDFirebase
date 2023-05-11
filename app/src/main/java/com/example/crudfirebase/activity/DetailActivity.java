package com.example.crudfirebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.crudfirebase.R;
import com.example.crudfirebase.adapter.MyFragmentAdapter;
import com.example.crudfirebase.model.ItemCart;
import com.example.crudfirebase.model.Product;
import com.example.crudfirebase.session.SessionManagement;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {
    ImageView imganhchitiet;
    TextView textten,textmota,textGiachitiet;
    Button btcancel,btaddtocart;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);
        anhxa();
        Intent intent = getIntent();
        Product  pro= (Product) intent.getSerializableExtra("myProduct");
            textGiachitiet.setText("Giá: "+String.valueOf(pro.getGia())+"$");
            textten.setText(pro.getTen());
            textmota.setText(pro.getDes());
            Glide.with(this).load(pro.getAnh()).into(imganhchitiet);
            btcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            btaddtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                    String username = sessionManagement.getUser();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Cart");

                    DatabaseReference user1Ref = ref.child(username+pro.getId());


                    user1Ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            ItemCart  itemCart= snapshot.getValue(ItemCart.class);
                            if(itemCart!=null){
                                int soluong = itemCart.getSoluong();
                                ref.child(username+pro.getId()).setValue(new ItemCart(username+pro.getId(),username,pro.getId(),soluong+1,pro.getGia()));
                            }else{
                                ref.child(username+pro.getId()).setValue(new ItemCart(username+pro.getId(),username,pro.getId(),1,pro.getGia()));
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            System.out.println("Lỗi: " + error.getMessage());
                        }
                    });
                    goCartActivity();
                }
            });
    }
    private void goCartActivity(){
        Intent intent =  new Intent(DetailActivity.this, CartActivity.class);
        startActivity(intent);
    }
    private void anhxa(){
        textGiachitiet=findViewById(R.id.textGiachitiet);
        imganhchitiet= findViewById(R.id.imgAnhchitiet);
        textten= findViewById(R.id.textTenchitiet);
        textmota= findViewById(R.id.textMotachitiet);
        btcancel= findViewById(R.id.buttonCancelchitiet);
        btaddtocart= findViewById(R.id.buttonAddtocart);
    }
}
