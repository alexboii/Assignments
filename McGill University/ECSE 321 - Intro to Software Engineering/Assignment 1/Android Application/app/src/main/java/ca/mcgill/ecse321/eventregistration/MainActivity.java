package ca.mcgill.ecse321.eventregistration;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.eventregistration.controller.EventRegistrationController;
import ca.mcgill.ecse321.eventregistration.controller.InvalidInputException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Participant;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceEventRegistration;

public class MainActivity extends AppCompatActivity {

    TextView errorView;
    String error = null;
    private HashMap<Integer, Participant> participants;
    private Integer selectedParticipant = -1;
    private Integer selectedEvent = -1;
    private HashMap<Integer, Event> events;
    Spinner spinner, spinner2;
    private static boolean checkLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        PersistenceEventRegistration.loadEventRegistrationModel();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        errorView = (TextView) findViewById(R.id.errorMessage);
        setSupportActionBar(toolbar);

        if (checkLaunch) {
            PersistenceEventRegistration.setFileName(getFilesDir().getAbsolutePath() + File.separator + "eventregistration.xml");
            System.out.println(getFilesDir().getAbsolutePath() + "eventregistration.xml");
            PersistenceEventRegistration.loadEventRegistrationModel();
            checkLaunch = false;
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        spinner = (Spinner) findViewById(R.id.participantspinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long id) {
                // On selecting a spinner item
                selectedParticipant = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinner2 = (Spinner) findViewById(R.id.eventspinner);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long id) {
                // On selecting a spinner item
                selectedEvent = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        refreshData();

    }

    private void refreshData() {
        RegistrationManager rm = RegistrationManager.getInstance();
        TextView tv = (TextView) findViewById(R.id.newparticipant_name);
        TextView tv2 = (TextView) findViewById(R.id.newevent_name);
        TextView startView = (TextView) findViewById(R.id.newevent_start);
        TextView dateView = (TextView) findViewById(R.id.newevent_date);
        TextView endView = (TextView) findViewById(R.id.newevent_end);
        errorView.setText(error);
//        tv.setText("");

        if (error == null || error.length() == 0) {
            spinner = (Spinner) findViewById(R.id.participantspinner);
            ArrayAdapter<CharSequence> participantAdapter = new
                    ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
            participantAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            this.participants = new HashMap<Integer, Participant>();
            int i = 0;
            for (Iterator<Participant> participants = rm.getParticipants().iterator();
                 participants.hasNext(); i++) {
                Participant p = participants.next();
                participantAdapter.add(p.getName());
                this.participants.put(i, p);
            }
            selectedParticipant = -1;
            spinner.setAdapter(participantAdapter);

            spinner2 = (Spinner) findViewById(R.id.eventspinner);
            ArrayAdapter<CharSequence> eventAdapter = new
                    ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
            eventAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            this.events = new HashMap<Integer, Event>();
            i = 0;
            for (Iterator<Event> events = rm.getEvents().iterator();
                 events.hasNext(); i++) {
                Event e = events.next();
                eventAdapter.add(e.getName());
                this.events.put(i, e);
            }
            selectedEvent = -1;
            spinner2.setAdapter(eventAdapter);

//            tv.setText(null);
//            tv2.setText(null);
////            endView.setText((CharSequence) new Date());
////            startView.setText((CharSequence) new Date());
//            dateView.setText(null);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addParticipant(View v) {
        TextView tv = (TextView) findViewById(R.id.newparticipant_name);
//        TextView errorView = (TextView) findViewById(R.id.errorMessage);
        EventRegistrationController pc = new EventRegistrationController();

        error = null;

        try {
            pc.createParticipant(tv.getText().toString());

        } catch (InvalidInputException e) {
            error = e.getMessage();
//            errorView.setText(error);
        }

        refreshData();
    }

    public void addEvent(View v) {
        error = null;
        TextView tv = (TextView) findViewById(R.id.newevent_name);
        TextView startView = (TextView) findViewById(R.id.newevent_start);
        TextView dateView = (TextView) findViewById(R.id.newevent_date);
        TextView endView = (TextView) findViewById(R.id.newevent_end);
//        TextView errorView = (TextView) findViewById(R.id.errorMessage);

        EventRegistrationController pc = new EventRegistrationController();

        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm");


        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(fmt.parse(startView.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(2000, 1, 1);
        Time startTime = new Time(calendar.getTime().getTime());
        try {
            calendar.setTime(fmt.parse(endView.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(2000, 1, 1);
        Time endTime = new Time(calendar.getTime().getTime());

        try {
            pc.createEvent(tv.getText().toString(), java.sql.Date.valueOf(dateView.getText().toString()), startTime, endTime);

        } catch (InvalidInputException e) {
            error = e.getMessage();
//            errorView.setText(error);
        }

        refreshData();
    }

    public void registerParticipant(View v) {
        error = "";
        Spinner spinner = (Spinner) findViewById(R.id.participantspinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.eventspinner);
        if (spinner == null && spinner.getSelectedItem() == null)
            error = error + "Participant needs to be selected for registration ";
        if (spinner2 == null && spinner2.getSelectedItem() == null)
            error = error + "Event needs to be selected for registration";
        error = error.trim();

        if (error.length() == 0) {
            EventRegistrationController erc = new EventRegistrationController();

            try {
                System.out.println("here");
                erc.register(participants.get(selectedParticipant), events.get(selectedEvent));
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }

        }

        refreshData();
    }

    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText());
        args.putInt("id", v.getId());
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getTimeFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;
        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }
        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);
        return rtn;
    }

    private Bundle getDateFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;
        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }
        rtn.putInt("day", day);
        rtn.putInt("month", month - 1);
        rtn.putInt("year", year);
        return rtn;
    }

    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }
}
