package app.com.fyweddingplanner;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;


public class MenuActivity extends Activity  implements
        Frag1.OnFragmentInteractionListener,
        Frag2.OnFragmentInteractionListener,
        Frag3.OnFragmentInteractionListener {


    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab = ab.newTab()
                .setText( "Offers" )
                .setTabListener(
                        new MyTabListener(this,
                                OffersTab.class.getName()));
        ab.addTab(tab);

        tab = ab.newTab()
                .setText("Wedding Planner")
                .setTabListener(
                        new MyTabListener(this,
                                AllWeddingPlanner.class.getName()));
        ab.addTab( tab );

        tab = ab.newTab()
                .setText( "Saloons" )
                .setTabListener(
                        new MyTabListener( this,
                                Frag3.class.getName() ) );
        ab.addTab( tab );


        tab = ab.newTab()
                .setText( "Card Shops" )
                .setTabListener(
                        new MyTabListener( this,
                                Frag4.class.getName() ) );
        ab.addTab( tab );

                tab = ab.newTab()
                .setText( "Car rent" )
                .setTabListener(
                        new MyTabListener( this,
                                Frag5.class.getName() ) );
        ab.addTab( tab );

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class MyTabListener
            implements ActionBar.TabListener
    {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mFragName;

        public MyTabListener( Activity activity,
                              String fragName )
        {
            mActivity = activity;
            mFragName = fragName;
        }


        @Override
        public void onTabSelected( Tab tab,
                                   FragmentTransaction ft )
        {
            mFragment = Fragment.instantiate( mActivity,
                    mFragName );
            ft.add( android.R.id.content, mFragment );
        }


        @Override
        public void onTabUnselected( Tab tab,
                                     FragmentTransaction ft )
        {
            ft.remove( mFragment );
            mFragment = null;
        }



        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub

        }


    }





}
