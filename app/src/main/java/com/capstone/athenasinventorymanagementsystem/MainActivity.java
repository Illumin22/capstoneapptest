package com.capstone.athenasinventorymanagementsystem;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.athenasinventorymanagementsystem.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private ArrayList<Item> items;
    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new InventoryFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.inventory) {
                replaceFragment(new InventoryFragment());
            } else if (item.getItemId() == R.id.records) {
                replaceFragment(new RecordsFragment());
            } else if (item.getItemId() == R.id.alerts) {
                replaceFragment(new AlertsFragment());
            }

            return true;
        });

        items = new ArrayList<>();
        itemList = findViewById(R.id.item_list);

        // Add some items to the list
        items.add(new Item("Item 1", 10));
        items.add(new Item("Item 2", 20));
        items.add(new Item("Item 3", 30));

        // Set the adapter for the list view
        ItemAdapter adapter = new ItemAdapter(this, items);
        itemList.setAdapter(adapter);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void addItem(View view) {
        EditText itemNameEditText = findViewById(R.id.item_name_edit_text);
        String itemName = itemNameEditText.getText().toString();

        EditText quantityEditText = findViewById(R.id.quantity_edit_text);
        int quantity = Integer.parseInt(quantityEditText.getText().toString());

        // Add the item to the list
        items.add(new Item(itemName, quantity));

        // Update the adapter
        ItemAdapter adapter = new ItemAdapter(this, items);
        itemList.setAdapter(adapter);
    }
}

class Item {

    String name;
    int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.item_name_text_view);
        TextView quantityTextView = convertView.findViewById(R.id.quantity_text_view);

        nameTextView.setText(item.name);
        quantityTextView.setText(String.valueOf(item.quantity));

        return convertView;
    }
}
