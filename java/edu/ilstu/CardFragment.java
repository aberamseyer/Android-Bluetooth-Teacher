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

    public static ArrayList<SAQuestion> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    public static String MCQuestions[] = {"MCSAQuestion 1","MCSAQuestion 2","MCSAQuestion 3","MCSAQuestion 4","MCSAQuestion 5","MCSAQuestion 6","MCSAQuestion 7"};

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
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);


        MyRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), MyRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.i("card touched", "at position: " + position);
                        CheckBox cb = (CheckBox)view.findViewById(R.id.cardCheckBox);
                        if(cb.isChecked())
                            cb.setChecked(false);
                        else
                            cb.setChecked(true);
                    }

                    @Override public void onLongItemClick(View view, int position) {
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
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.questionTypeText.setText(list.get(position).getQuestion());

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
        listitems.clear();

        for(int i =0;i<7;i++){

            MCQuestion item = new MCQuestion();
            item.setA("a");
            item.setB("b");
            item.setC("c");
            item.setD("d");
            item.setQuestion("What is a letter?");
            listitems.add(item);
        }

    }
}
