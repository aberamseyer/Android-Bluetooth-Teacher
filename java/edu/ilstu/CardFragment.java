package edu.ilstu;

        import android.bluetooth.BluetoothAdapter;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.content.pm.ResolveInfo;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;


public class CardFragment extends Fragment {

    ArrayList<WonderModel> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String Wonders[] = {"Question 1","Question 2","Question 3","Question 4","Question 5","Question 6","Question 7"};
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
        getActivity().setTitle("7 Wonders of the Modern World");
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
                        // do whatever
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
        private ArrayList<WonderModel> list;

        public MyAdapter(ArrayList<WonderModel> Data) {
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

            holder.questionTypeText.setText(list.get(position).getCardName());

        }

       @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView questionTypeText;
        public Button sendButton; //TODO remove sendButton from layout and code, has been replaced with fab2send in MainActivity.java


        public MyViewHolder(View v) {
            super(v);
            questionTypeText = (TextView) v.findViewById(R.id.questionTypeText);
            sendButton = (Button) v.findViewById(R.id.sendButton);


            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("blutooth", "bt button tapped");

                    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, Wonders[0]); //TODO find a way to get the card that was tapped and access that part of the array

                    final PackageManager pm = getActivity().getApplicationContext().getPackageManager();
                    List<ResolveInfo> appsList = pm.queryIntentActivities(intent, 0);

                    if(appsList.size() > 0){
                        String packageName = null;
                        String className = null;
                        boolean found = false;

                        for(int i = 0; i < appsList.size(); i++){
                            // find bluetooth in the list of activities
                            packageName = appsList.get(i).activityInfo.packageName;
                            if(packageName.equals("com.android.bluetooth")){
                                className = appsList.get(i).activityInfo.name;
                                found = true;
                                break;
                            }
                        }
                        if(! found){
                            Toast.makeText(getContext(), R.string.blu_notfound_inlist,
                                    Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }

                        intent.setClassName(packageName, className);
                        startActivity(intent);
                    }
                }
            });



        }
    }

    public void initializeList() {
        listitems.clear();

        for(int i =0;i<7;i++){

            WonderModel item = new WonderModel();
            item.setCardName(Wonders[i]);
            item.setIsfav(0);
            item.setIsturned(0);
            listitems.add(item);
        }

    }
}
