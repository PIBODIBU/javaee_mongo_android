package com.android.javaeemongodb.ui.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.ui.activity.DocumentListActivity;
import com.android.javaeemongodb.ui.activity.InfoActivity;
import com.android.javaeemongodb.ui.activity.ModelInfoActivity;
import com.android.javaeemongodb.ui.activity.SettingsActivity;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public abstract class BaseNavDrawerActivity extends BaseAnimActivity {
    private final String TAG = getClass().getSimpleName();

    private DrawerBuilder drawerBuilder = null;
    private Drawer drawer = null;
    private InputMethodManager inputMethodManager = null;

    private boolean wasInputActive = false;
    private View savedFocus = null;

    public BaseNavDrawerActivity() {
    }

    /**
     * Base public methods
     */

    public Drawer getDrawer() {
        createDrawerBuilder(createToolbar(false));
        createDrawer();
        getCurrentSelection();

        return drawer;
    }

    public Drawer getDrawer(
            boolean withToolbar,
            boolean withToolbarBackArrow
    ) {
        createDrawerBuilder(withToolbar ? createToolbar(withToolbarBackArrow) : null);
        createDrawer();
        getCurrentSelection();

        return drawer;
    }

    /**
     * Support methods
     */

    protected void removeToolbarTitle() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("");
    }

    protected void setToolbarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    protected void getCurrentSelection() {
        String basename = BaseNavDrawerActivity.this.getClass().getSimpleName();

        if (drawer == null) {
            return;
        }

        if (basename.equals(DocumentListActivity.class.getSimpleName())) {
            drawer.setSelection(DrawerItems.DocListActivity.ordinal());
        } else if (basename.equals(InfoActivity.class.getSimpleName())) {
            drawer.setSelection(DrawerItems.InfoActivity.ordinal());
        } else if (basename.equals(SettingsActivity.class.getSimpleName())) {
            drawer.setSelection(DrawerItems.SettingsActivity.ordinal());
        } else {
            drawer.setSelection(-1);
        }
    }

    /**
     * Base private methods
     */

    private void createDrawer() {
        if (drawerBuilder != null) {
            drawer = drawerBuilder.build(); // Building Drawer
            drawer.getRecyclerView().setVerticalScrollBarEnabled(false); // remove ScrollBar from RecyclerView
        } else {
            Log.e(TAG, "createDrawer() -> DrawerBuilder is null");
        }
    }

    private Toolbar createToolbar(boolean withBackArrowEnabled) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(withBackArrowEnabled);

        return toolbar;
    }

    private void createDrawerBuilder(Toolbar toolbar) {
        final PrimaryDrawerItem docList = new PrimaryDrawerItem()
                .withName(getResources().getString(R.string.drawer_doc_list))
                .withIcon(GoogleMaterial.Icon.gmd_reorder)
                .withIdentifier(DrawerItems.DocListActivity.ordinal());

        final PrimaryDrawerItem info = new PrimaryDrawerItem()
                .withName(getResources().getString(R.string.drawer_info))
                .withIcon(GoogleMaterial.Icon.gmd_info_outline)
                .withIdentifier(DrawerItems.InfoActivity.ordinal());

        final PrimaryDrawerItem settings = new PrimaryDrawerItem()
                .withName(getResources().getString(R.string.drawer_settings))
                .withIcon(GoogleMaterial.Icon.gmd_settings)
                .withIdentifier(DrawerItems.SettingsActivity.ordinal());

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withProfileImagesVisible(false)
                .withHeaderBackground(R.color.colorPrimary)
                .build();

        /**
         * Implementing DrawerBuilder
         */
        drawerBuilder = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withHeaderDivider(false)
                .withAccountHeader(accountHeader)
                .withSliderBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                .addDrawerItems(
                        docList,
                        info,
                        new DividerDrawerItem(),
                        settings
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        if (inputMethodManager.isAcceptingText()) {
                            if (getCurrentFocus() != null) {
                                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                                wasInputActive = true;
                                savedFocus = getCurrentFocus();
                            }
                        } else {
                            wasInputActive = false;
                        }
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        if (wasInputActive) {
                            inputMethodManager.showSoftInput(getCurrentFocus(), 0);
                            if (savedFocus != null)
                                savedFocus.requestFocus();
                        }
                    }

                    @Override
                    public void onDrawerSlide(View view, float v) {

                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    // Обработка клика
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        try {
                            String currentClass = BaseNavDrawerActivity.this.getClass().getSimpleName();
                            DrawerItems drawerItems = DrawerItems.values()[(int) drawerItem.getIdentifier()];

                            switch (drawerItems) {
                                case DocListActivity: {
                                    if (currentClass.equals(DocumentListActivity.class.getSimpleName())) {
                                        break;
                                    } else {
                                        finish();

                                        startActivity(new Intent(BaseNavDrawerActivity.this, DocumentListActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        break;
                                    }
                                }
                                case InfoActivity: {
                                    if (currentClass.equals(ModelInfoActivity.class.getSimpleName())) {
                                        break;
                                    } else {
                                        finish();

                                        startActivity(new Intent(BaseNavDrawerActivity.this, InfoActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        break;
                                    }
                                }
                                case SettingsActivity: {
                                    if (currentClass.equals(SettingsActivity.class.getSimpleName())) {
                                        break;
                                    } else {
                                        finish();

                                        startActivity(new Intent(BaseNavDrawerActivity.this, SettingsActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        break;
                                    }
                                }
                                default: {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return false;
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            switch (keyCode) {
                case KeyEvent.KEYCODE_MENU: {
                    if (drawer.isDrawerOpen())
                        drawer.closeDrawer();
                    else
                        drawer.openDrawer();
                    break;
                }
                case KeyEvent.KEYCODE_BACK: {
                    if (drawer.isDrawerOpen()) { // Check if Drawer is opened
                        drawer.closeDrawer();
                    } else {
                        super.onBackPressed();
                    }

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

    @Override
    protected void onResume() {
        getCurrentSelection();
        super.onResume();
    }

    private enum DrawerItems {
        DocListActivity,
        InfoActivity,
        ModelEditActivity,
        SettingsActivity
    }
}