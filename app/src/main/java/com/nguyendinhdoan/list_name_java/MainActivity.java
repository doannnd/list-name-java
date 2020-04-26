package com.nguyendinhdoan.list_name_java;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nguyendinhdoan.list_name_java.adapter.NameAdapter;
import com.nguyendinhdoan.list_name_java.databinding.ActivityMainBinding;
import com.nguyendinhdoan.list_name_java.model.User;
import com.nguyendinhdoan.list_name_java.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<User> userList = new ArrayList<>();
    private NameAdapter adapter;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        setUpToolbar();
        setUpRecyclerView();
        setUpFab();
    }

    private void initData() {
        sharedPref = new SharedPref(this);
        userList.clear();
        userList.addAll(sharedPref.getAllUser());
    }

    private void setUpToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    private void setUpRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new NameAdapter(userList);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setUpFab() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInsertUserDialog();
            }
        });
    }

    private void showInsertUserDialog() {
        String dialogTitle = getString(R.string.name_of_user);
        String buttonTitle = getString(R.string.button_create);

        final EditText edtEnterUser = new EditText(this);
        edtEnterUser.setInputType(InputType.TYPE_CLASS_TEXT);

        Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setView(edtEnterUser);

        builder.setPositiveButton(buttonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userName = edtEnterUser.getText().toString();
                User user = new User(userName);

                adapter.insertUser(user);
                sharedPref.saveUser(user);

                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
