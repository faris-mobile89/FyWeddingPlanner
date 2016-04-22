package app.com.fyweddingplanner.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import app.com.fyweddingplanner.MapActivity;
import app.com.fyweddingplanner.MenuActivity;
import app.com.fyweddingplanner.R;

public class LoginActivity extends Activity {
    EditText etName,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName=(EditText) findViewById(R.id.edtUserName);
        etPassword=(EditText) findViewById(R.id.edtPassLogin);
    }


    public void goToSignUp(View v)
    {
        Intent i=new Intent(this,SignUp.class);
        startActivity(i);
    }

    public void Login (View v )

    {

        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
        //Intent i = new Intent(LoginActivity.this, MapActivity.class);
        startActivity(i);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.ws_url_login);
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                try {
                    JSONObject o=new JSONObject(s);
                    String data=o.getString("result");
                    if(data.equals("1")) {
                        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(i);
                    }
                        else
                    {Toast.makeText(getApplicationContext(),"invalid user",Toast.LENGTH_LONG).show();}
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
                Toast.makeText(getApplicationContext(), "Error Login\n"+errorDescription,Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("UserName",etName.getText().toString());
                param.put("password", etPassword.getText().toString());
                return param;
            }
        };


        queue.add(req);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
