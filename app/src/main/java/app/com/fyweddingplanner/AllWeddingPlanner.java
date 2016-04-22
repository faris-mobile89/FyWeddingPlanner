package app.com.fyweddingplanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

import app.com.fyweddingplanner.models.WeddingPlanner;

public class AllWeddingPlanner extends AppCompatActivity {

    ListView lv;
    EditText et;
    ArrayAdapter<WeddingPlanner> aaa;
    List<WeddingPlanner>xx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_wedding_planner);
        lv=(ListView) findViewById(R.id.lv);
        et=(EditText) findViewById(R.id.et);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {


                WeddingPlanner w = new WeddingPlanner();
                w = (WeddingPlanner) lv.getItemAtPosition(position);
                //  Toast.makeText(getApplicationContext(), u.name, Toast.LENGTH_LONG).show();
            }
        });

    }




    public void go(View v) {
        final List<WeddingPlanner> l = new ArrayList<WeddingPlanner>();

        //Add request Queue

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.185/sample/webservices1.asmx/readAllWeddingPlanner";


        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {

                    xx = new ArrayList<WeddingPlanner>();
                    aaa = new ArrayAdapter<WeddingPlanner>(AllWeddingPlanner.this, android.R.layout.simple_list_item_1, xx);

                    JSONObject obj = new JSONObject(s);
                    JSONArray obje = obj.getJSONArray("WeddingPlanner");

                    for (int i = 0; i < obje .length(); i++) {
                        JSONObject x = obje.getJSONObject(i);


                        String name = x.getString("name");
                        String offers = x.getString("offers");
                        String description = x.getString("description");

                        WeddingPlanner m=new WeddingPlanner();
                        m.name=x.getString("name");
                        m.description= x.getString("description");
                        m.offers =x.getString("offers");

                        xx.add(m);
                    }
                    aaa = new ArrayAdapter<WeddingPlanner>(AllWeddingPlanner.this, android.R.layout.simple_list_item_1, xx);
                    lv.setAdapter(aaa);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                else
                    errorDescription="Error";
                Toast.makeText(getApplicationContext(), errorDescription, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("filter", et.getText().toString());


                return param;
            }
        };

        queue.add(req);


    }
}