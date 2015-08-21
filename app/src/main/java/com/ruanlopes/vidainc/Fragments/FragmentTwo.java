package com.ruanlopes.vidainc.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.ruanlopes.vidainc.Constant;
import com.ruanlopes.vidainc.Helper.SQLiteHelper;
import com.ruanlopes.vidainc.Model.Room;
import com.ruanlopes.vidainc.R;

import java.util.ArrayList;

public class FragmentTwo extends Fragment {

    View rootView;
    private RelativeLayout mRelativeLayout;
    private DragView mDragView;
    private DisplayMetrics metrics;

    ArrayList data;

    ImageButton editButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_two, container, false);

        mRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.frameCanvasTwo);

        //create Canvas and link it
        mDragView = new DragView(rootView.getContext(), metrics);
        mRelativeLayout.addView(mDragView);

        editButton = (ImageButton) rootView.findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constant.edited = false;
            }
        });


        return rootView;
    }

    /**
     * Inner Class in charge to draw on the mCanvas Layout
     */

    public class DragView extends View {

        public DisplayMetrics metrics;
        public Context contextDrag;
        public ArrayList<Room> mRoomList;

        SQLiteHelper db;

        int width;
        int height;

        private Room mTouchedRoom;

        public DragView(Context context, DisplayMetrics displayMetrics){

            super(context);


            contextDrag = context;
            metrics = displayMetrics;


            // TODO: This part it should connect with the database and retrieve all the data inside a ArrayList,
            // TODO: so the ondraw can redraw everything in a new view
            db = new SQLiteHelper(contextDrag);
            mRoomList =  db.getAllRooms();


            width = this.getWidth();
            height = this.getHeight();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (int i = 0; i < mRoomList.size(); i++) {

                Room b = mRoomList.get(i);
                canvas.drawBitmap(b.getImage(), b.getCurrent().x, b.getCurrent().y, null);
            }

            invalidate();

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            int action = event.getAction();
            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (action) {

                case MotionEvent.ACTION_DOWN:

                    mTouchedRoom = didTouchCircle(x,y);

                    if (mTouchedRoom != null) {

                        mTouchedRoom.setSelected(true);

                        //Intent intent = new Intent(this, InsideRoom.class);
                        //startActivity(intent);
                    }

                    break;
            }

            invalidate();
            return true;

        }

        public Room didTouchCircle(int x, int y){

            //First Width
            for (Room room : mRoomList) {

                if (room.getLeft() + room.getImageWidth() > x
                        && room.getLeft() < x
                        && room.getTop() + room.getImageHeight() > y
                        && room.getTop() < y) {

                    return room;

                }

            }

            return null;

        }



    }


}
