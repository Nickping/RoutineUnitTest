package sm_arts.routineunittest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import sm_arts.routineunittest.dto.RoutineItem;

/**
 * Created by admin on 2017-11-15.
 */

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder> {

    private static final String TAG = "RoutineAdapter";
    private  List<RoutineItem> mRoutineItems;

    public RoutineAdapter(List<RoutineItem> routineItems) {
        this.mRoutineItems = routineItems;
    }
    public void setItems(List<RoutineItem> mRoutineItems)
    {
        this.mRoutineItems = mRoutineItems;
        notifyDataSetChanged();
    }


    public static class RoutineViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLinearLayout;
        CardView mCardView;
        TextView mTextView;
        TextView mTextViewData;
        Button mButton;

        public RoutineViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.routine_item_linear);
            mCardView = (CardView) itemView.findViewById(R.id.routine_item_cardview);
            mTextView = (TextView) itemView.findViewById(R.id.routine_item_tv);
            mTextViewData = (TextView)itemView.findViewById(R.id.routine_item_data_tv);
            mButton = (Button) itemView.findViewById(R.id.routine_item_btn);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: ");
                }
            });
        }
    }

    @Override
    public RoutineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.routineitem, parent, false);
        RoutineViewHolder holder = new RoutineViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RoutineViewHolder holder, int position) {
        TextView textView = holder.mTextView;
        RoutineItem item = mRoutineItems.get(position);
        TextView textViewData = holder.mTextViewData;
        LinkedTreeMap<String,Object> map = item.data;
        textView.setText(mRoutineItems.get(position).task_type);
        if(TextUtils.equals("message",item.task_type))
        {
           textViewData.setText((String)map.get("text"));
        }else if(TextUtils.equals("weather",item.task_type))
        {
            textViewData.setText((String)map.get("lat")+" "+(String)map.get("lon"));
        }

    }

    @Override
    public int getItemCount() {
        return mRoutineItems.size();
    }
}
