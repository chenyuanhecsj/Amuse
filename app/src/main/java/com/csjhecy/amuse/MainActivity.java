package com.csjhecy.amuse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.test1)
    TextView test1;
    @BindView(R.id.test2)
    TextView test2;
    @BindView(R.id.test3)
    TextView test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainPresenter mainPresenter = new MainPresenter(this, MainModel.getInstance());


        // choiseFragment();

    }


    private void choiseFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragment).commit();
    }

    @OnClick({R.id.test1, R.id.test2, R.id.test3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test1:
                break;
            case R.id.test2:
                break;
            case R.id.test3:
                break;
        }
    }
}
