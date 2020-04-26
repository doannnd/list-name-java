package com.nguyendinhdoan.list_name_java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyendinhdoan.list_name_java.model.User;
import com.nguyendinhdoan.list_name_java.databinding.ItemNameBinding;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {

    private ItemNameBinding binding;

    private List<User> userList;

    public NameAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void insertUser(User user) {
        userList.add(user);
        notifyItemInserted(userList.size() - 1);
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNameBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new NameViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        User user = userList.get(position);
        if (user != null) {
            binding.tvPosition.setText(String.valueOf(position + 1));
            binding.tvName.setText(user.getName());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class NameViewHolder extends RecyclerView.ViewHolder {

        NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
