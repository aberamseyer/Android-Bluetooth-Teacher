package edu.ilstu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class CardFragment extends Fragment {

    public static ArrayList<SAQuestion> itemsToSend = new ArrayList<SAQuestion>();
    RecyclerView MyRecyclerView;
    public static ArrayList<SAQuestion> questions = new ArrayList<SAQuestion>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
        getActivity().setTitle("Bluetooth Popup Quiz");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (itemsToSend.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(itemsToSend));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);


        MyRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), MyRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.i("card touched", "at position: " + position);
                        CheckBox cb = (CheckBox) view.findViewById(R.id.cardCheckBox);
                        if (cb.isChecked()) {
                            cb.setChecked(false);
                            itemsToSend.set(position, null);
                            printItemsToLog();
                        } else {
                            cb.setChecked(true);
                            itemsToSend.set(position, questions.get(position));
//                            printItemsToLog();
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        onItemClick(view, position);
                    }
                })
        );


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<SAQuestion> list;

        public MyAdapter(ArrayList<SAQuestion> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.questionTypeText.setText(list.get(position).toString());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView questionTypeText;

        public MyViewHolder(View v) {
            super(v);
            questionTypeText = (TextView) v.findViewById(R.id.questionTypeText);
        }
    }

    public void initializeList() {
        itemsToSend.clear();
        // Predefined multiple-choice questions + any others added
        questions.add(new MCQuestion("What grade do you plan to get in this class?", "A", "B", "C", "D", 1));
        questions.add(new MCQuestion("What is your favorite color", "Red", "Green", "Blue", "none of these", 2));
        questions.add(new MCQuestion("how old are you", "18", "19", "20", "21", 3));
        questions.add(new MCQuestion("What is your major?", "Computer Science", "IS", "Something else", "not sure yet", 4));
        questions.add(new MCQuestion("What kind of housing do you live in?", "Dorm", "Apartment", "House", "I'm Homeless", 2));
        for (int i = 0; i < questions.size(); i++) {
            itemsToSend.add(i, questions.get(i));
        }
    }

    private void printItemsToLog() {
        for (int i = 0; i < itemsToSend.size(); i++) {
            if (itemsToSend.get(i) != null)
                Log.i("aramsey", itemsToSend.get(i).toString());
        }
    }

    public ArrayList<SAQuestion> getItemsToSend() {
        return itemsToSend;
    }
}
