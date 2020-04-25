package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    public FirstFragment() {}
    private PaintView paint_brush,paint_eraser;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {

        View rootView=inflater.inflate(R.layout.fragment_first,container,false);

        LinearLayout Rl= (LinearLayout) rootView.findViewById(R.id.paint);
        paint_brush=new PaintView(getActivity());

        Rl.addView(paint_brush);


        System.out.println("eyss");
        System.out.println(this.getId());
        // Inflate the layout for this fragment
        return rootView;

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

/*
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        */
    }
    public void eraser()
    {
        paint_brush.eraser();

    }
    public void brush(){
        paint_brush.brush();
    }

}
