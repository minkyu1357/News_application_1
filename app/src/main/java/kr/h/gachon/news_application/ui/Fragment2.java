package kr.h.gachon.news_application.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.h.gachon.news_application.R;
import kr.h.gachon.news_application.network.model.News;
import kr.h.gachon.news_application.viewmodel.NewsViewModel;


public class Fragment2 extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter2 adapter;
    private RecyclerView.LayoutManager layoutManager;
    private NewsViewModel vm;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_2, container, false);


        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter=new MyAdapter2();
        recyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(NewsViewModel.class);
        vm.getHeadlines().observe(getViewLifecycleOwner(), this::onNewsReceived);
        vm.loadHeadlines();

        return view;
    }
    private void onNewsReceived(List<News> newsList) {
        adapter.submitList(newsList);
    }

}