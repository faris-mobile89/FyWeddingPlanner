package app.com.fyweddingplanner;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.com.fyweddingplanner.models.Offers;
import app.com.fyweddingplanner.models.WeddingPlanner;

public class AllWeddingPlanner extends Fragment implements View.OnClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    // Log tag
    private static final String TAG = OffersTab.class.getSimpleName();

    private List<WeddingPlanner> offerList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_offers,container,false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        //searchOffer = (Button) v.findViewById(R.id.offer_add_btn);
        //searchOffer.setOnClickListener(this);
        mRecyclerView = (RecyclerView)  v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        //getData();

        loadListViewWithData(json);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    public void getData() {
        //Add request Queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://192.168.1.185/sample/webservices1.asmx/readAllWeddingPlanner";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            loadListViewWithData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String errorDescription = "";
                if (volleyError instanceof NetworkError) {
                } else if (volleyError instanceof ServerError) {
                    errorDescription = "Server Error";
                } else if (volleyError instanceof AuthFailureError) {
                    errorDescription = "AuthFailureError";
                } else if (volleyError instanceof ParseError) {
                    errorDescription = "Parse Error";
                } else if (volleyError instanceof NoConnectionError) {
                    errorDescription = "No Conenction";
                } else if (volleyError instanceof TimeoutError) {
                    errorDescription = "Time Out";

                } else {
                    try {
                        errorDescription = "Error";
                        Toast.makeText(getActivity(), errorDescription, Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                //param.put("filter", et.getText().toString());
                return param;
            }
        };

        queue.add(req);


    }

    public void loadListViewWithData(String string){

        try {

            JSONObject obj = new JSONObject(string);
            JSONArray obje = obj.getJSONArray("WeddingPlanner");

            for (int i = 0; i < obje.length(); i++) {
                JSONObject x = obje.getJSONObject(i);
                String name = x.getString("wPName");
                String offers = x.getString("wPOffers");
                String description = x.getString("wPdiscription");
                String phone = x.getString("wPPhone");
                String location = x.getString("wPLocation");

                WeddingPlanner m = new WeddingPlanner();
                m.setName(name);
                m.setDescription(description);
                m.setOffers(offers);
                m.setLocation(location);
                m.setPhone(phone);

                offerList.add(m);
            }
            mAdapter = new WeddingCardAdapter(getActivity(), offerList);
            mRecyclerView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    String json = "{\n" +
            "  \"WeddingPlanner\": [\n" +
            "    {\n" +
            "      \"wPName\": \"Massaya Wedding Planner\",\n" +
            "      \"wPdiscription\": \"Event Planning \",\n" +
            "      \"wPOffers\": \"\",\n" +
            "      \"wPLocation\": \"amman\",\n" +
            "      \"wPServices\": \"organize flowers with center pieces, lighting, wedding zafa, photo ghrafing, video's, photo video clips, and the best D.J\",\n" +
            "      \"wPImg\": \"q\",\n" +
            "      \"wPPhone\": 796997744\n" +
            "    },\n" +
            "    {\n" +
            "      \"wPName\": \"Fantazia for Weddings Arrangements\",\n" +
            "      \"wPdiscription\": \"Event Planner\",\n" +
            "      \"wPOffers\": \"ط¹ط±ط¶ ط§ظ„ 2000 ط¯ظٹظ†ط§ط± \\r\\n25 ط³ظ†طھط±ط¨ظٹط³ \\r\\nظƒظˆط´ط© ,ط²ظٹظ†ط© ط³ظٹط§ط±ط© ,ظ…ط³ظƒط© ط¹ط±ظˆط³ \\r\\nظ†ط¸ط§ظ… طµظˆطھ ظˆط¥ط¶ط§ط،ط© ظƒط§ظ…ظ„ ظ„ظ„ظ…ظƒط§ظ† \\r\\nطھطµظˆظٹط± ظپظˆطھظˆط؛ط±ط§ظپظٹ ظ…ط¹ ط·ط¨ط§ط¹ط© 100 طµظˆط±ط© ظˆ طھظƒط¨ظٹط± طµظˆط±ط© ظ„ظ„ط¹ط±ط³ط§ظ† \\r\\nطھطµظˆظٹط± ظپظٹط¯ظٹظˆ ظƒط§ظ…ظٹط±طھظٹظ† 3 ظ…ظˆط§ظ‚ط¹ ط¨ظٹطھ ط¹ط±ظٹط³ ظˆ ط¨ظٹطھ ط¹ط±ظˆط³ ظˆ ط\u00ADظپظ„ط© \\r\\nط²ظپط© (ط£ط±ط¯ظ†ظٹط© ط£ظˆ ظپظ„ط³ط·ظٹظ†ظٹط© ط£ظˆ ظ…ظƒط³ ظپظ„ط³ط·ظٹظ†ظٹ ط§ط±ط¯ظ†ظٹ ) 3 ظ…ظˆط§ظ‚ط¹ \\r\\nط¯ط§ظ†ط³ ظپظ„ظˆط±\\r\\n\",\n" +
            "      \"wPLocation\": \"ط¹ظ…ط§ظ† ,ظپظ†ط¯ظ‚ ظˆظ…ظ†طھط¬ط¹ ط§ظپط±ط³طھ\",\n" +
            "      \"wPServices\": \"\",\n" +
            "      \"wPImg\": \"y\",\n" +
            "      \"wPPhone\": 795159411\n" +
            "    },\n" +
            "    {\n" +
            "      \"wPName\": \"Prestige Wedding Planner\",\n" +
            "      \"wPdiscription\": \"Event Planning,Amman Sweifeh, Tareq AlJundi ST ,07 9766 1663\",\n" +
            "      \"wPOffers\": \"discount 20% to all packeages\",\n" +
            "      \"wPLocation\": \"طµظˆظٹظپظٹظ‡ ,ط¹ظ…ط§ظ† ,ط§ظ„ط§ط±ط¯ظ†\",\n" +
            "      \"wPServices\": \"#events #weddings #birthdays #launching #graduation #shower party #henna #baby shower #fashion show #grand opening #themed events #conferences etc...\\r\\nSave\\r\\nSweifeh, Tareq AlJundi ST. Beside Abdeen Grand Store ., Amman, Jordan 11184\\r\\nAmman\\r\\n07 9766 1663\\r\\nClosed Now\\r\\nClosed until tomorrow 10:00AM - 3:30PM, 5:30PM - 8:00PM\\r\\nPrice Range: $$$$\\r\\nTypically replies within a few hours\\r\\nMessage Now\\r\\n\",\n" +
            "      \"wPImg\": \"uj\",\n" +
            "      \"wPPhone\": 797661663\n" +
            "    },\n" +
            "    {\n" +
            "      \"wPName\": \"GLOW Marketing & Events\",\n" +
            "      \"wPdiscription\": \"Wedding Planning آ· Marketing Consultant آ· Event\",\n" +
            "      \"wPOffers\": \"\",\n" +
            "      \"wPLocation\": \"Wadi saqra street\\r\\nAmman\",\n" +
            "      \"wPServices\": \"Wedding Planning آ· Marketing Consultant آ· Event\",\n" +
            "      \"wPImg\": \"uu\",\n" +
            "      \"wPPhone\": 799999669\n" +
            "    },\n" +
            "    {\n" +
            "      \"wPName\": \"Fadi Kurdieh Pws\",\n" +
            "      \"wPdiscription\": \"wedding planner\",\n" +
            "      \"wPOffers\": \"طµظˆطھ + ظ„ط§ط¹ط¨ DJ\\r\\n8 ظ…ظˆ?ظ†ط¬ ظ‡ط¯ \\r\\n20 ط¨ط§ط±ظƒط§ظ† \\r\\nظƒظˆط´ظ‡ \\r\\n25 ط³ظ†طھط± ط¨ظٹط³ط²\\r\\nط²ظپظ‡ \\r\\nطھطµظˆظٹط± 2 ظƒط§ظ…ظٹط±ط§ ?ظٹط¯ظٹظˆ \\r\\n150 طµظˆط±ظ‡ ظپظˆطھظˆ\\r\\n ط§ظ„ط³ط¹ط± 2500 ط¯ظٹظ†ط§\",\n" +
            "      \"wPLocation\": \"amman\",\n" +
            "      \"wPServices\": \"DJ\\r\\nطھطµظˆظٹط±\\r\\nظˆط±ط¯\\r\\nط²ظپظ‡\\r\\nظƒظˆط´ظ‡\\r\\nط¯ط§ظ†ط³ ظپظ„ظˆط±\\r\\nط§ط¶ط§ط،ظ‡\",\n" +
            "      \"wPImg\": null,\n" +
            "      \"wPPhone\": 790826365\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    @Override
    public void onClick(View view) {

    }
}


 class WeddingCardAdapter extends RecyclerView.Adapter<WeddingCardAdapter.ViewHolder>{

    List<WeddingPlanner> mItems;
    Context context;
    Activity activity;

    public WeddingCardAdapter(Context context, List<WeddingPlanner> items) {
        this.context  = context;
        this.mItems   = items;
        this.activity = (Activity)context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_recycler_view_item_planner, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        WeddingPlanner item = mItems.get(i);
        viewHolder.tvTitle.setText(item.getName());
        viewHolder.tvDetails.setText(item.getDescription());
        viewHolder.tvLocation.setText(item.getLocation());
        viewHolder.tvPhone.setText(item.getPhone());
        //imageLoader.displayImage(event.getPic(), viewHolder.imgThumbnail, options);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgThumbnail;
        public TextView tvTitle;
        public TextView tvDetails;
        public TextView tvPhone;
        public TextView tvLocation;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvTitle      = (TextView)itemView.findViewById(R.id.tv_title);
            //tvDetails    = (TextView)itemView.findViewById(R.id.tv_text);
            tvLocation    = (TextView)itemView.findViewById(R.id.tv_location);
            //tvPhone    = (TextView)itemView.findViewById(R.id.tv_phone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final int itemPosition = getAdapterPosition();
            WeddingPlanner item = mItems.get(itemPosition);

            switch (v.getId()){

            }
        }
    }
}


