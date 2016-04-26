package app.com.fyweddingplanner;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class OffersTab extends Fragment implements View.OnClickListener{


    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    // Log tag
    private static final String TAG = OffersTab.class.getSimpleName();

    private List<Offers> offerList = new ArrayList<>();
    private Button searchOffer;
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
        getOffers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.offer_add_btn:
//                startActivity(new Intent(getActivity(), AddOffer.class).putExtra("editMode", false));
//                break;
        }
    }

    public void getOffers(){
        //Add request Queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://192.168.1.185/sample/webservices1.asmx/readAllOffersFromDB";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loadListViewWithData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String errorDescription = "";
                if( volleyError instanceof NetworkError) {
                } else if( volleyError instanceof ServerError) {
                    errorDescription="Server Error";
                } else if( volleyError instanceof AuthFailureError) {
                    errorDescription="AuthFailureError";
                } else if( volleyError instanceof ParseError) {
                    errorDescription="Parse Error";
                } else if( volleyError instanceof NoConnectionError) {
                    errorDescription="No Conenction";
                } else if( volleyError instanceof TimeoutError) {
                    errorDescription="Time Out";
                }
                else {
                    try {
                        errorDescription = "Error";
                        Toast.makeText(getActivity(), errorDescription, Toast.LENGTH_SHORT).show();
                    }catch (Exception e){

                    }
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                //param.put("filter", et.getText().toString());
                return param;
            }
        };
        queue.add(req);
        //loadListViewWithData(x);
    }


    public void loadListViewWithData(String string){
        try {
            JSONObject obj = new JSONObject(string);
            JSONArray obje = obj.getJSONArray("offers");
            for (int i = 0; i < obje .length(); i++) {
                JSONObject x = obje.getJSONObject(i);
                String offerName = x.getString("sName");
                //String filed = x.getString("filed");
                String sdescription = x.getString("sdescription");
                Offers m = new Offers();
                m.setName(offerName);
                m.setDisciption(sdescription);
                offerList.add(m);
            }
            mAdapter = new CardAdapter(getActivity(), offerList,OffersTab.this);
            mRecyclerView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    String x = "{\n" +
            "  \"offers\": [\n" +
            "    {\n" +
            "      \"sName\": \"Prestige Wedding Planner Event Planning,Amman Sweife,Tareq AlJundi ST ,07 9766 1663\",\n" +
            "      \"sdescription\": \"weddingplanner\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}