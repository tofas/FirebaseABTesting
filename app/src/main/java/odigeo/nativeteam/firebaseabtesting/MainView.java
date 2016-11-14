package odigeo.nativeteam.firebaseabtesting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class MainView extends Fragment implements MainViewInterface {

    private static MainView instance;

    public static MainView getInstance() {
        if(instance == null)
            instance = new MainView();

        return instance;
    }

    public MainView() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_view, container, false);
        return view;
    }
}
