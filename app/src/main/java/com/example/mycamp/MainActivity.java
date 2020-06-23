package com.example.mycamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    private final Handler myHandler = new Handler();
    private boolean timeToggle = false;
    public static final String EXTRA_MESSAGE = "com.example.MyCamp.MESSAGE";
    private TextView txt;
    private Integer number = 0;
    private Date date = new Date();

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

        recommendationsList = (RecyclerView) findViewById(R.id.my_recycler_view);
        recListLayoutManager = new LinearLayoutManager(this);
        recommendationsList.setLayoutManager(recListLayoutManager);
        recListAdapter = new MyHikingTrailAdapter(dataSet);
        recommendationsList.setAdapter(recListAdapter);

        myTask.execute(findViewById(R.id.dateText));
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
