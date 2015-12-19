package com.uit.smarthomecontrol.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import com.uit.smarthomecontrol.GroupDeviceDetailActivity;
import com.uit.smarthomecontrol.R;
import com.uit.smarthomecontrol.models.GroupItem;

import java.util.ArrayList;

public class ListGroupDeviceAdapter extends RecyclerSwipeAdapter<ListGroupDeviceAdapter.SimpleViewHolder> {
    Context context;
    ArrayList<GroupItem> listSensor;
    private LayoutInflater inflater;
    private boolean isDeleted = false;

    public ListGroupDeviceAdapter(Context context, ArrayList<GroupItem> prgmNameList) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        listSensor = prgmNameList;
        this.context = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_group_device_item, parent, false);
        SimpleViewHolder holder = new SimpleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final GroupItem current = listSensor.get(position);
        viewHolder.groupItem = current;

        viewHolder.txtGroupName.setText(current.getGroupName());

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag From Left
        //viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        // Drag From Right
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper));

        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                if (layout.getDragEdge().equals(SwipeLayout.DragEdge.Right)) {
                    YoYo.with(Techniques.Tada).duration(500).playOn(viewHolder.btnDelete);
                    YoYo.with(Techniques.Tada).duration(500).playOn(viewHolder.btnEdit);
                } else if (layout.getDragEdge().equals(SwipeLayout.DragEdge.Left)) {
                    Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });
        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Active this script", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(context, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                listSensor.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listSensor.size());
                mItemManger.closeAllItems();
            }
        });
        viewHolder.btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GroupDeviceDetailActivity.class);
                intent.putParcelableArrayListExtra("LIST_DEVICE", viewHolder.groupItem.getSensorItems());
                intent.putExtra("GROUP_NAME", viewHolder.groupItem.getGroupName());
                intent.putExtra("OPTION", "PREVIEW");
                intent.putExtra("KEYWORD", "kewyword");
                context.startActivity(intent);
            }
        });
        // mItemManger is member in RecyclerSwipeAdapter Class
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return listSensor.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView txtGroupName;
        SwipeLayout swipeLayout;
        ImageButton btnDelete;
        ImageButton btnEdit;
        GroupItem groupItem;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            btnDelete = (ImageButton) itemView.findViewById(R.id.delete);
            btnEdit = (ImageButton) itemView.findViewById(R.id.edit);
            txtGroupName = (TextView) itemView.findViewById(R.id.textView1);
        }
    }
}