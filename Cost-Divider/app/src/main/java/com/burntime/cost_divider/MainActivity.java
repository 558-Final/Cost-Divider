package com.burntime.cost_divider;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import java.text.NumberFormat;
import java.util.Locale;



public class MainActivity extends FragmentActivity implements ActionBar.TabListener,
        AbstractNewTransactionDialogFragment.NewTransactionDialogListener{

    public final static String TAG = MainActivity.class.getSimpleName();

    public static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    private Household household;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar.setSelectedNavigationItem(position);
                    }
                });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this));
        }

        household = Household.get(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_new_purchase:
                NewPurchaseFragment newPurchaseDialogFragment = new NewPurchaseFragment();
                newPurchaseDialogFragment.show(getFragmentManager(), "purchase");
                break;
            case R.id.action_new_payment:
                NewPaymentFragment newPaymentDialogFragment = new NewPaymentFragment();
                newPaymentDialogFragment.show(getFragmentManager(), "payment");
                break;
            case R.id.action_new_party:
                NewPartyFragment newPartyDialogFragment = new NewPartyFragment();
                newPartyDialogFragment.show(getFragmentManager(), "payment");
                break;
            case R.id.action_clear_data:
                AlertDialog d = new AlertDialog.Builder(this)
                        .setCancelable(true)
                        .setNegativeButton(android.R.string.cancel, null)
                        .setTitle(getString(R.string.clear_data_dialog_title))
                        .setMessage("Do you really want to clear all purchases, payments, and parties?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                household.clearData();
                                refreshLists();
                            }
                        })
                        .create();
                d.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab,
                              FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void newTransaction() {
        refreshLists();
    }

    public void refreshLists(){
        for (int f = 0; f < mSectionsPagerAdapter.getCount(); f++){
            mSectionsPagerAdapter.refreshFragment(f);
        }
    }
}
