package com.example.mycamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.example.mycamp.MyCardView;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.util.Date;

//import com.google.common.util.concurrent.FutureCallback;
//import com.google.common.util.concurrent.Futures;
//import com.google.common.util.concurrent.ListenableFuture;
//import com.google.common.util.concurrent.SettableFuture;


import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOperations;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;


//import okhttp3.OkHttpClient;

//import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.*;

public class MainActivity extends AppCompatActivity
{
    private final Handler myHandler = new Handler();
    private boolean timeToggle = true;
    public static final String EXTRA_MESSAGE = "com.example.MyCamp.MESSAGE";
    private TextView txt;
    private Integer number = 0;
    private Date date = new Date();
    private Context c = this;

    /**
     * Client reference
     */
    private MobileServiceClient mClient;
    private MobileServiceTable<ToDoItem> mTable;

    //RecyclerView stuff
    private RecyclerView recommendationsList;
    private RecyclerView.Adapter recListAdapter;
    private RecyclerView.LayoutManager recListLayoutManager;
    String packageName = c.getPackageName();
    private HikingTrail[] dataSet = {
            new HikingTrail("Wallace Falls",            R.drawable.x, "add1", false, false, 0, 0),
            new HikingTrail("Haybrook Lookout",         R.drawable.x, "add2", false, false, 0, 1),
            new HikingTrail("Tenerrife Falls",          R.drawable.x, "add3", false, false, 0, 2),
            new HikingTrail("Bridal Veil Falls",        R.drawable.x, "add4", false, false, 0, 3),
            new HikingTrail("Poo Poo Point",            R.drawable.x, "add5", false, false, 0, 4),
            new HikingTrail("Duvall Pipeline Trail",    R.drawable.x, "add6", false, false, 0, 5),
            new HikingTrail("Rattlesnake Ledge",        R.drawable.x, "add7", false, false, 0, 6),
            new HikingTrail("MailBox Peak",             R.drawable.x, "add8", false, false, 0, 7),
            new HikingTrail("Lake 22",                  R.drawable.x, "add9", false, false, 0, 8)
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = ((TextView) findViewById(R.id.dateText));
        txt = ((TextView) findViewById(R.id.dateText));
        txt.setText("food");

        //--------------------------------------------------------------------------------------------//

        String url = "https://mycamp1.azurewebsites.net";
        try
        {
            mClient = new MobileServiceClient(url, this);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        mTable = mClient.getTable(ToDoItem.class);

        //--------------------------------------------------------------------------------------------//

        dataSet[0].SetImageId(c.getResources().getIdentifier("wallacefalls", "drawable", c.getPackageName()));
        dataSet[1].SetImageId(c.getResources().getIdentifier("haybrooklookout", "drawable", c.getPackageName()));
        dataSet[2].SetImageId(c.getResources().getIdentifier("tenerrifefalls", "drawable", c.getPackageName()));
        dataSet[3].SetImageId(c.getResources().getIdentifier("bridalveilfalls", "drawable", c.getPackageName()));
        dataSet[4].SetImageId(c.getResources().getIdentifier("poopoopoint", "drawable", c.getPackageName()));
        dataSet[5].SetImageId(c.getResources().getIdentifier("duvallpipelinetrail", "drawable", c.getPackageName()));
        dataSet[6].SetImageId(c.getResources().getIdentifier("rattlesnakeledge", "drawable", c.getPackageName()));
        dataSet[7].SetImageId(c.getResources().getIdentifier("mailboxpeak", "drawable", c.getPackageName()));
        dataSet[8].SetImageId(c.getResources().getIdentifier("lake22", "drawable", c.getPackageName()));


        //--------------------------------------------------------------------------------------------//

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
        if(timeToggle == false)
        {
            timeToggle = true;
        }
        else
        {
            timeToggle = false;
        }

//        ConstraintLayout myCardView = findViewById(R.id.main);
//        EditText myEditText = new EditText(c);
//        myCardView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//        myCardView.addView(myEditText);
    }

    public void CardClick(View view)
    {
        //((TextView)((Activity)view.getContext()).findViewById(R.id.text1)).setText(number);
        //((MyHikingTrailAdapter)recListAdapter).m_dataSet
        ViewGroup viewParent = (ViewGroup)view.getParent();
        TextView text = ((TextView)viewParent.getChildAt(2));
        CardView cV = (CardView)view.getParent().getParent();
        RecyclerView rV = (RecyclerView)cV.getParent();
        int pos = rV.getChildAdapterPosition(cV);
        text.setText(((Integer)dataSet[pos].number++).toString());
        //number++;
    }
}
