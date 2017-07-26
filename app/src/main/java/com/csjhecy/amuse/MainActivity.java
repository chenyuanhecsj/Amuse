package com.csjhecy.amuse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csjhecy.amuse.fragment.Text1Fragment;
import com.csjhecy.amuse.fragment.Text2Fragment;
import com.csjhecy.amuse.fragment.Text3Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.test1)
    RadioButton test1;
    @BindView(R.id.test2)
    RadioButton test2;
    @BindView(R.id.test3)
    RadioButton test3;
    @BindView(R.id.rb_group)
    RadioGroup mRbGroup;
    private MainPresenter mainPresenter;
    private Text1Fragment mText1Fragment;
    private Text2Fragment mText2Fragment;
    private Text3Fragment mText3Fragment;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this, MainModel.getInstance());
        choiseFragment(0);
        mRbGroup.check(R.id.test1);
    }

    private void choiseFragment(int position) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        hidenFragment();
        switch (position) {
            case 0:
                if (mText1Fragment == null) {
                    mText1Fragment = Text1Fragment.newInstance(null);
                    addFragment(mText1Fragment);
                }
                mFragmentTransaction.show(mText1Fragment);
                break;
            case 1:
                if (mText2Fragment == null) {
                    mText2Fragment = mText2Fragment.newInstance(null);
                    addFragment(mText2Fragment);
                }
                mFragmentTransaction.show(mText2Fragment);

                break;
            case 2:
                if (mText3Fragment == null) {
                    mText3Fragment = mText3Fragment.newInstance(null);
                    addFragment(mText3Fragment);
                }
                mFragmentTransaction.show(mText3Fragment);

                break;
        }
        mFragmentTransaction.commit();
    }

    private void addFragment(Fragment fragment) {
        mFragmentTransaction.add(R.id.fl_content, fragment);
    }

    @OnClick({R.id.test1, R.id.test2, R.id.test3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test1:
                choiseFragment(0);
                break;
            case R.id.test2:
                choiseFragment(1);
                break;
            case R.id.test3:
                choiseFragment(2);
                break;
        }
    }

    /**
     * 隐藏全部的Fragment
     */
    private void hidenFragment() {

        if (mText1Fragment != null) {
            mFragmentTransaction.hide(mText1Fragment);
        }

        if (mText2Fragment != null) {
            mFragmentTransaction.hide(mText2Fragment);
        }

        if (mText3Fragment != null) {
            mFragmentTransaction.hide(mText3Fragment);
        }

    }
}
