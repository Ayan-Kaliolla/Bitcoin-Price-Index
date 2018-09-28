package kz.kaliolla.bitcoinpriceindex;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import kz.kaliolla.bitcoinpriceindex.module.converter.CurrencyConverterFragment;
import kz.kaliolla.bitcoinpriceindex.module.home.HomeFragment;
import kz.kaliolla.bitcoinpriceindex.module.transaction.history.TransactionHistoryFragment;

public class MainActivity extends DaggerAppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{
    private static final String PARAM_CURRENT_FRAGMENT_TAG = "current_fragment_tag";
    private static String currentFragmentTag;
    private static FragmentManager fragmentManager;

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        initBottomNavBar();
        if (savedInstanceState == null) {
            bottomNavigation.setCurrentItem(0);
        }
    }

    private void initBottomNavBar() {
        List<AHBottomNavigationItem> menuItems = new ArrayList<>();
        menuItems.add(new AHBottomNavigationItem(R.string.home, R.drawable.ic_bottom_nav_home, R.color.colorGray));
        menuItems.add(new AHBottomNavigationItem(R.string.history, R.drawable.ic_bottom_nav_history, R.color.colorGray));
        menuItems.add(new AHBottomNavigationItem(R.string.conversion, R.drawable.ic_bottom_nav_converter, R.color.colorGray));
        bottomNavigation.addItems(menuItems);
        bottomNavigation.setDefaultBackgroundColor(Color.TRANSPARENT);
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorWhite));
        bottomNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.colorGray));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setOnTabSelectedListener(this);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        boolean replace = true;
        Fragment fragment;
        switch (position) {
            case 0:
                if (!HomeFragment.TAG.equals(currentFragmentTag)) {
                    fragment = fragmentManager.findFragmentByTag(HomeFragment.TAG);
                    if (fragment == null){
                        replace = false;
                        fragment = HomeFragment.getInstance();
                    }
                    refreshCurrentFragment(fragment, HomeFragment.TAG, replace);
                }
                return true;
            case 1:
                if (!TransactionHistoryFragment.TAG.equals(currentFragmentTag)) {
                    fragment = fragmentManager.findFragmentByTag(TransactionHistoryFragment.TAG);
                    if (fragment == null){
                        replace = false;
                        fragment = TransactionHistoryFragment.getInstance();
                    }
                    refreshCurrentFragment(fragment, TransactionHistoryFragment.TAG, replace);
                }
                return true;
            case 2:
                if (!CurrencyConverterFragment.TAG.equals(currentFragmentTag)) {
                    fragment = fragmentManager.findFragmentByTag(CurrencyConverterFragment.TAG);
                    if (fragment == null){
                        replace = false;
                        fragment = CurrencyConverterFragment.getInstance();
                    }
                    refreshCurrentFragment(fragment, CurrencyConverterFragment.TAG, replace);
                }
                return true;

        }
        return false;
    }

    private void refreshCurrentFragment(Fragment fragment, String tag, boolean replace) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragmentTag != null) {
            Fragment fragmentOld = fragmentManager.findFragmentByTag(currentFragmentTag);
            if (fragmentOld != null) {
                fragmentTransaction.hide(fragmentOld);
            }
        }
        currentFragmentTag = tag;
        if (replace) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.container, fragment, currentFragmentTag);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PARAM_CURRENT_FRAGMENT_TAG, currentFragmentTag);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentFragmentTag = savedInstanceState.getString(PARAM_CURRENT_FRAGMENT_TAG);
    }

}
