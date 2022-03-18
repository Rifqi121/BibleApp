package com.example.bible.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bible.Book;
import com.example.bible.MyBookListAdapter;
import com.example.bible.R;
import com.example.bible.databinding.FragmentBookBinding;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {

    private BookViewModel bookViewModel;
    private FragmentBookBinding binding;
    private List<Book> bookList;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bookList = new ArrayList<>();
        listView = root.findViewById(R.id.listView);

        for (int i = 1; i <= 100; i++) {
            bookList.add(new Book("Title " + i));
        }

        MyBookListAdapter adapter = new MyBookListAdapter(getActivity().getApplicationContext(), R.layout.listview, bookList);
        listView.setAdapter(adapter);

        bookViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}