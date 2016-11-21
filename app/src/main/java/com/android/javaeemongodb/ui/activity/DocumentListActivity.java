package com.android.javaeemongodb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.helper.IntentKeys;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;
import com.android.javaeemongodb.ui.presenter.DocListPresenter;
import com.android.javaeemongodb.ui.presenter.implementation.DocumentListPresenterImpl;
import com.android.javaeemongodb.ui.view.DocumentListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DocumentListActivity extends BaseNavDrawerActivity
        implements DocumentListView, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private final String TAG = getClass().getSimpleName();
    private final int REQUEST_MODEL_ADD = 1;
    private final int REQUEST_MODEL_EDIT = 2;
    private final int REQUEST_MODEL_INFO = 3;
    private final int VIEW_MODE_LIST = 1;
    private final int VIEW_MODE_GRID = 2;

    @BindView(R.id.root_view)
    public CoordinatorLayout rootView;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.swipe_layout)
    public SwipeRefreshLayout refreshLayout;

    private SearchView searchView;
    private DocListPresenter presenter;
    private int viewMode = VIEW_MODE_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_list);

        ButterKnife.bind(this);
        getDrawer();

        setupView();

        presenter = new DocumentListPresenterImpl(this);
        presenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_doc_list_mode_normal, menu);
        setSearchView((SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search)));
        getSearchView().setOnQueryTextListener(this);
        getSearchView().setOnCloseListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_view_mode:
                setViewMode(getViewMode() == VIEW_MODE_LIST ? VIEW_MODE_GRID : VIEW_MODE_LIST);
                item.setIcon(ContextCompat.getDrawable(this,
                        getViewMode() == VIEW_MODE_LIST ? R.drawable.ic_view_module_white_24dp : R.drawable.ic_view_list_white_24dp));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MODEL_ADD) {
            if (resultCode == RESULT_OK) {
                onModelAdded();
            }
        }

        if (requestCode == REQUEST_MODEL_INFO) {
            if (resultCode == ModelInfoActivity.RESULT_MODEL_EDITED) {
                presenter.refreshDataSet(true);
            }
        }

        if (requestCode == REQUEST_MODEL_EDIT) {
            if (resultCode == RESULT_OK) {
                onModelEdited();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                if (!getSearchView().isIconified()) {
                    getSearchView().setIconified(true);
                } else if (presenter.getAdapter().isSelectedModeActivated()) {
                    presenter.setSelectionModeActivated(false);
                    setSelectionModeActivated(false);
                } else {
                    super.onBackPressed();
                }

                break;
            }
            default:
                super.onKeyDown(keyCode, event);
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public int getViewMode() {
        return viewMode;
    }

    @Override
    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;

        switch (viewMode) {
            case VIEW_MODE_LIST:
                presenter.setLayoutManager(presenter.getLinearLayoutManager());
                break;
            case VIEW_MODE_GRID:
                presenter.setLayoutManager(presenter.getGridLayoutManager());
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onClose() {
        presenter.getAdapter().setDataSet(presenter.getDataSet());
        presenter.getAdapter().notifyDataSetChanged();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<MedicineModel> filteredModels = new ArrayList<>();
        newText = newText.toLowerCase();

        for (MedicineModel model : presenter.getDataSet()) {
            if (model.getName().toLowerCase().contains(newText)) {
                filteredModels.add(model);
            }
        }

        presenter.getAdapter().setDataSet(filteredModels);
        presenter.getAdapter().notifyDataSetChanged();

        return true;
    }

    @Override
    public void onModelEdited() {
        presenter.refreshDataSet(true);
        Snackbar.make(rootView, R.string.snack_bar_model_edited, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onModelAdded() {
        presenter.refreshDataSet(true);
        Snackbar.make(rootView, R.string.snack_bar_model_added, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setupView() {
        refreshLayout.setColorSchemeResources(
                R.color.colorPrimary);
    }

    @OnClick(R.id.fab_add)
    public void startAddModelActivity() {
        startActivityForResult(new Intent(DocumentListActivity.this, ModelAddActivity.class), REQUEST_MODEL_ADD);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Nullable
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public boolean isRefreshing() {
        return refreshLayout.isRefreshing();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        refreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        refreshLayout.setOnRefreshListener(onRefreshListener);
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void startModelEditActivity(MedicineModel model) {
        startActivityForResult(new Intent(DocumentListActivity.this, ModelEditActivity.class)
                .putExtra(IntentKeys.OBJECT_MEDICINE_MODEL, model), REQUEST_MODEL_EDIT);
    }

    @Override
    public void startModelInfoActivity(MedicineModel model) {
        startActivityForResult(new Intent(DocumentListActivity.this, ModelInfoActivity.class)
                .putExtra(IntentKeys.OBJECT_MEDICINE_MODEL, model), REQUEST_MODEL_INFO);
    }

    private SearchView getSearchView() {
        return searchView;
    }

    private void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void setSelectionModeActivated(boolean activated) {
        if (activated) {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.menu_doc_list_mode_delete);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_delete: {
                            presenter.deleteManyModels();
                            break;
                        }
                        default:
                            break;
                    }
                    return false;
                }
            });
        } else {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.menu_doc_list_mode_normal);
            toolbar.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    setViewMode(getViewMode() == VIEW_MODE_LIST ? VIEW_MODE_GRID : VIEW_MODE_LIST);
                    menuItem.setIcon(ContextCompat.getDrawable(DocumentListActivity.this,
                            getViewMode() == VIEW_MODE_LIST ? R.drawable.ic_view_module_white_24dp : R.drawable.ic_view_list_white_24dp));
                    return true;
                }
            });
            toolbar.getMenu().getItem(0).setIcon(ContextCompat.getDrawable(this,
                    getViewMode() == VIEW_MODE_LIST ? R.drawable.ic_view_module_white_24dp : R.drawable.ic_view_list_white_24dp));
            setSearchView((SearchView) MenuItemCompat.getActionView(toolbar.getMenu().findItem(R.id.action_search)));
            getSearchView().setOnQueryTextListener(this);
        }
    }
}
