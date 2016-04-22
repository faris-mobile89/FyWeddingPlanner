package app.com.fyweddingplanner.auth;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.com.fyweddingplanner.R;

public class SignUp extends Activity {


    EditText etName,etPassword,etEmail,etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName=(EditText) findViewById(R.id.edtUserSignUp);
        etPassword=(EditText) findViewById(R.id.edtPassSignUp);
        etEmail=(EditText) findViewById(R.id.edtEmail);
        etPhone=(EditText) findViewById(R.id.edtPhone);
    }


    public void signUp(View v)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = getResources().getString(R.string.ws_url_signUp);

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject o=new JSONObject(s);
                    String data=o.getString("result");
                    if(data.equals("1"))
                    {
                        Toast.makeText(getApplicationContext(), "Sign Up Successfully", Toast.LENGTH_LONG).show();}
                    else
                    {Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();}
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
                Toast.makeText(getApplicationContext(), errorDescription,Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("UserName",etName.getText().toString());
                param.put("password", etPassword.getText().toString());
                param.put("phone", etPhone.getText().toString());
                param.put("Email", etEmail.getText().toString());
                param.put("Location", "lll");
                param.put("Description", "lll");
                return param;
            }

        };


        queue.add(req);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
