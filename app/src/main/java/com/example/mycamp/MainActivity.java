package com.example.mycamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceJsonTable;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.google.gson.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

public class MainActivity extends AppCompatActivity
{
    private final Handler myHandler = new Handler();
    private boolean timeToggle = false;
    public static final String EXTRA_MESSAGE = "com.example.MyCamp.MESSAGE";
    private TextView txt;
    private Integer number = 0;
    private Date date = new Date();
    Gson gsonBuilder = new Gson();
    TextView testText;

    URL databaseUrl;
    HttpURLConnection databaseConnection;

    //RecyclerView stuff
    private RecyclerView recommendationsList;
    private RecyclerView.Adapter recListAdapter;
    private RecyclerView.LayoutManager recListLayoutManager;
    private HikingTrail[] dataSet = {
            new HikingTrail("Wallace Falls", "add1", false, false, 0),
            new HikingTrail("Haybrook Lookout", "add2", false, false, 0),
            new HikingTrail("Tenerrife Falls", "add3", false, false, 0),
            new HikingTrail("Bridal Veil Falls", "add4", false, false, 0),
            new HikingTrail("Poo Poo Point", "add5", false, false, 0),
            new HikingTrail("Pipeline Trail", "add6", false, false, 0),
            new HikingTrail("Rattlesnake Ledge", "add7", false, false, 0),
            new HikingTrail("MailBox Peak Falls", "add8", false, false, 0)
    };

    MobileServiceClient msClient;
    MobileServiceTable<Trip> msTable;
    List<Trip> results;





    class UpDateUITask extends AsyncTask<View, String, String>
    {
        @Override
        protected void onPreExecute()
        {

        }

        @Override
        protected String doInBackground(View... params)
        {
            updateUI(params[0]);
            return null;
        }

        protected void onPostExecute(String result)
        {

        }
    }

    class NetworkTask extends AsyncTask<View, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            networkTaskArrayList.add(this);
        }

        @Override
        protected Void doInBackground(View... voids)
        {
            try
            {
                String tableName = "Trips";
                databaseUrl = new URL("https://mytripplannerappservice.azurewebsites.net/api/" + tableName + "/");
                databaseConnection = (HttpURLConnection) databaseUrl.openConnection();
                databaseConnection.setRequestMethod("GET");
                databaseConnection.setDoOutput(true);
                databaseConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");



                Trip testTrip = new Trip("Cool", "FROM ANDROID APP", "8-24-2020", "8-30-2020");

                String tripJSON = gsonBuilder.toJson(testTrip);
                Trip tempTrip = gsonBuilder.fromJson(tripJSON, Trip.class);

                //testText.setText("YES IT DID!");




                InputStream input = databaseConnection.getInputStream();
                InputStreamReader iReader = new InputStreamReader(input);
                BufferedReader bReader = new BufferedReader(iReader);
                String content  = bReader.readLine();



                OutputStream o = databaseConnection.getOutputStream();

                DataOutputStream out = new DataOutputStream(o);
                out.write(tripJSON.getBytes());
                out.flush();
                out.close();

                BufferedReader reponse = new BufferedReader(new InputStreamReader(databaseConnection.getInputStream()));

                String inputLine;
                StringBuffer responseString = new StringBuffer();

                while ((inputLine = reponse.readLine()) != null)
                {
                    responseString.append(inputLine);
                }

            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            networkTaskArrayList.remove(this);
            number++;
        }

    }

    ArrayList<NetworkTask> networkTaskArrayList = new ArrayList<NetworkTask>();

    //NetworkTask tempTask = new NetworkTask();

    private UpDateUITask myTask = new UpDateUITask();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //String teext = "TACOBOI";
        //((Button) findViewById(R.id.button)).setText(teext);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = ((TextView) findViewById(R.id.dateText));
        txt.setText("food");

        testText = ((TextView) findViewById(R.id.textView));

//        recommendationsList = (RecyclerView) findViewById(R.id.my_recycler_view);
//        recListLayoutManager = new LinearLayoutManager(this);
//        recommendationsList.setLayoutManager(recListLayoutManager);                                //UNCOMMENT
//        recListAdapter = new MyHikingTrailAdapter(dataSet);
//        recommendationsList.setAdapter(recListAdapter);

    }

    private void updateUI(View view)
    {

        for(int i = 0; true;)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e) { }
            if(timeToggle)
            {

                date = new Date();
                ((TextView) view).setText(date.toString());
            }

        }
    }

    public void sendMessage(View view)
    {
//        date = new Date();
//        ((Button) view).setText(date.toString());
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        TextView editText = (TextView) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);

            //scheduleTaskExecuter.scheduleAtFixedRate(myRunnable, 0, 15, TimeUnit.SECONDS);


        //myHandler.post(myRunnable);

        if(timeToggle == false)
        {
            timeToggle = true;
        }
        else
        {
            timeToggle = false;
        }
        NetworkTask myNetworkTask = new NetworkTask();
        myNetworkTask.execute(testText);
        //networkTaskArrayList.add(myTask);
        myTask.execute((TextView)findViewById(R.id.dateText));

        Integer i = networkTaskArrayList.size();

        ((Button)view).setText(i.toString());





//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                myHandler.post(updateRunnable);
//            }
//        }).start();
    }


}
