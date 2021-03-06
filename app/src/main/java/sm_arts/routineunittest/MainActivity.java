package sm_arts.routineunittest;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sm_arts.routineunittest.dto.RoutineItem;
import sm_arts.routineunittest.helper.RoutineItemlistener;

public class MainActivity extends AppCompatActivity implements RoutineView {
    @BindView(R.id.main_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    RoutinePresenter mRoutinePresenter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    RoutineAdapter mRoutineAdapter;
    List<RoutineItem> mRoutineItemList;
    @BindView(R.id.addTask)
    Button mButtonAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRoutineItemList = new ArrayList<>();
        mRoutineAdapter = new RoutineAdapter(mRoutineItemList, new RoutineItemlistener() {
            @Override
            public void onItemClicked(View v, int position) {
                RoutineItem routineItem = mRoutineAdapter.getItemWithPosition(position);
                mRoutinePresenter.deleteRoutineItem(routineItem);
            }
        });
        mRoutinePresenter = new RoutinePresenter(this, mRoutineAdapter);
        initlayout();

    }

    private void initlayout() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRoutinePresenter.getRoutineData();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRoutineAdapter);
        mRecyclerView.setHasFixedSize(true);
        mButtonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRoutinePresenter.addTask();
            }
        });

    }

    @Override
    public void onDataDownloadFinished() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
