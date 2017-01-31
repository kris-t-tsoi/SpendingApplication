package kristytsoi.spendingapplication;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainMenu extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //When start applicaiton get the draw fragment and set it up
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        //Start adding detail row fragments into the list in the main menu
        MenuListItemDetails[] menuDetails =  setMenuRowDetails();

        // The ListAdapter acts as a bridge between the data and each ListItem
        // You fill the ListView with a ListAdapter. You pass it a context represented by
        // this. A Context provides access to resources you need.
        ListAdapter theAdapter = new MenuListAdapter(this, menuDetails);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) findViewById(R.id.main_detail_overview);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter); //TODO null pointer exception




    }

    MenuListItemDetails[] setMenuRowDetails(){
        CalculateCostValues calculate = new CalculateCostValues();

        MenuListItemDetails monthSpending = new MenuListItemDetails(getResources().getString(R.string.month_spending), calculate.calculateMonthlySpending());
        MenuListItemDetails yearSpending = new MenuListItemDetails(getResources().getString(R.string.month_spending), calculate.calculateYearlySpending());

        MenuListItemDetails monthEarning = new MenuListItemDetails(getResources().getString(R.string.month_spending), calculate.calculateMonthlyEarning());
        MenuListItemDetails yearEarning= new MenuListItemDetails(getResources().getString(R.string.month_spending), calculate.calculateYearlyEarning());

        MenuListItemDetails monthProfLoss = new MenuListItemDetails(getResources().getString(R.string.month_spending), calculate.calculateMonthlyProfitLoss());
        MenuListItemDetails yearProfLoss = new MenuListItemDetails(getResources().getString(R.string.month_spending), calculate.calculateYearlyProfitLoss());


        return new MenuListItemDetails[]{monthSpending,monthEarning,monthProfLoss,yearSpending,yearEarning,yearProfLoss};

    }



    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_menu, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    //Option selection on top right
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //TODO settings
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Menu - Add Button Event Handler
    public void addExpense (View view) {

    }

    public void addRevenue (View view) {

    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainMenu) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
