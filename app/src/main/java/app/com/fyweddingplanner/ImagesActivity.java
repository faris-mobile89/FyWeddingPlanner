package app.com.fyweddingplanner;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ImagesActivity extends Activity {


    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        ActionBar ab = getActionBar();
        ab.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

        Tab tab = ab.newTab()
                .setText( "frag 1" )
                .setTabListener(
                        new MyTabListener( this,
                                AllWeddingPlanner.class.getName() ) );
        ab.addTab( tab );

        tab = ab.newTab()
                .setText( "frag 2" )
                .setTabListener(
                        new MyTabListener( this,
                                AllCardsShop.class.getName() ) );
        ab.addTab( tab );
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
            mFragment = Fragment.instantiate(mActivity,
                    mFragName);
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
