package com.example.projetnfc.Read;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetnfc.R;
import com.example.projetnfc.model.TagContent;

import java.util.ArrayList;
import java.util.Collections;

public class NFCReaderActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    public static String TAG = "TAG";
    private NfcReaderViewModel nfcReaderViewModel;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.read_tag_layout);

        nfcReaderViewModel = new ViewModelProvider(this).get(NfcReaderViewModel.class);

        //Get default NfcAdapter and PendingIntent instances
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // check NFC feature:
        if (nfcAdapter == null) {
            Toast.makeText(this, "This device doesn't support NFC", Toast.LENGTH_SHORT).show();
            finish();
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

//Add items to the list dynamically
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("bonjour");

//Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        //Enable NFC foreground detection
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled()) {
                Toast.makeText(this, "Please enable NFC", Toast.LENGTH_SHORT).show();
                showWirelessSettings();
            } else {
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
            }
        } else {
            Toast.makeText(this, "NFC isn't available", Toast.LENGTH_SHORT).show();
        }

        nfcReaderViewModel.getReadFailed().observe(this, readFailed -> {
            Toast.makeText(this, readFailed.getMessage(), Toast.LENGTH_SHORT).show();
        });

        nfcReaderViewModel.getTagRead().observe(this, readSuccess -> {
            for (TagContent s : readSuccess) {

                switch (s.getType()){
                    case TEXT:{
                        String etudiant=s.getContent();
                        int index = items.indexOf(etudiant.trim());
                        if (index != -1) {
                            // Element exists in the ArrayList at index "index"
                            items.set(index, s.getContent()+"\u2705");
                        } else {
                            // Element does not exist in the ArrayList
                            Toast.makeText(this, s.getContent() +"doesn't exist", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    }
                    default:{
                        Toast.makeText(this, "Type de tag non reconnu", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
    }

    private void showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Disable NFC foreground detection
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        // check the event was triggered by the tag discovery
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            // get the tag object from the received intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            nfcReaderViewModel.processNfcTag(rawMsgs);
        }
    }
}
