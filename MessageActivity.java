package com.example.dchat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dchat.Adapter.MessageAdapter;
import com.example.dchat.Model.Chat;
import com.example.dchat.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    TextView username;
    FirebaseUser fuser;
    RecyclerView recyclerView;
    DatabaseReference reference;
    EditText msg_edit;
    Button send;
    MessageAdapter messageAdapter;
    List<Chat> mchat;
    Intent intent;
    String userid;

    //RecyclerView recyclerView;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_message );
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        username = findViewById( R.id.us1 );
        send = findViewById( R.id.sendbtn );
        msg_edit = findViewById( R.id.send_text );
        recyclerView = findViewById( R.id.rc2 );
        recyclerView.setHasFixedSize( true );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() );
        linearLayoutManager.setStackFromEnd( true );
        recyclerView.setLayoutManager( linearLayoutManager );


        intent = getIntent();
        userid = intent.getStringExtra( "userid" );
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference( "MyUsers" ).child( userid );
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue( Users.class );
                username.setText( user.getUsername() );
                readMessage( fuser.getUid(), userid );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
        send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msg_edit.getText().toString();
                if (!msg.equals( "" )) {
                    String msg2 = encrypt( msg );
                    sendMessage( fuser.getUid(), userid, msg2 );


                } else {
                    Toast.makeText( MessageActivity.this, "Please send non empty", Toast.LENGTH_SHORT ).show();
                }
                msg_edit.setText( "" );
            }
        } );
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
    public String decrpyt(String enmsg)
    {int p=17;
        int q=19;
        int n=p*q;
        ArrayList<Double>c=new ArrayList<Double>();
        int z=(p-1)*(q-1);
        ArrayList<BigInteger>msgback=new ArrayList<BigInteger>();




        int e=2;
        while(e<z)
        {
            if(gcd(e,z)==1)
            {
                break;

            }
            e++;

        }

        int d=0;
        System.out.println("value of e "+e);

        for (int i=0;i<=10;i++)
        {int f= 1+(i*z);


            if(f%e==0)
            {
                d=f/e;
                break;
            }




        }
        char[]encry_array=new char[enmsg.length()];

        for(int i=0;i<enmsg.length();i++)
        {encry_array[i]=enmsg.charAt( i );





        }
        BigInteger N=BigInteger.valueOf(n);

        ArrayList<BigInteger>C= new ArrayList<BigInteger>();
        ArrayList<Double>ck=new ArrayList<Double>();
        //ArrayList<Dr>original_msg=new ArrayList<>();
        for (int i=0;i<encry_array.length;i++)
        {char c1=encry_array[i];
            int number=c1;
            double douvalue=c1;
            ck.add( douvalue );





        }
        for(int i=0;i<ck.size();i++)
        {C.add( BigDecimal.valueOf(ck.get(i)).toBigInteger());



        }

        for(int i=0;i<C.size();i++)
        {
            msgback.add((C.get(i).pow(d)).mod(N));


        }
        System.out.println(msgback);

        ArrayList<Integer>decrypted_msg=new ArrayList<Integer>();

        for(int i=0;i<C.size();i++)
        {
            decrypted_msg.add(msgback.get(i).intValue());


        }

        char []finaldec=new char[decrypted_msg.size()];

        for(int i=0;i<C.size();i++)
        {
            int k=decrypted_msg.get(i);
            char msg_char=(char)k;
            finaldec[i]=msg_char;

        }
        String returnmsg="";
        for(int i=0;i<finaldec.length;i++)
        {
            returnmsg+=finaldec[i];



        }

        return returnmsg;
    }
    public String encrypt(String msg) {

        int p = 17;
        int q = 19;
        int n = p * q;
        ArrayList<Double> c = new ArrayList<Double>();
        int z = (p - 1) * (q - 1);
        ArrayList<BigInteger> msgback = new ArrayList<BigInteger>();

        char[] msgarray = new char[msg.length()];
        for (int i = 0; i < msgarray.length; i++) {
            msgarray[i] = msg.charAt( i );


        }

        ArrayList<Integer> original_msg = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            char c1 = msgarray[i];
            int number = c1;
            original_msg.add( number );


        }
        System.out.println( original_msg );
        System.out.println( "Value of z " + z );


        int e = 2;
        while (e < z) {
            if (gcd( e, z ) == 1) {
                break;

            }
            e++;

        }

        int d = 0;
        System.out.println( "value of e " + e );

        for (int i = 0; i <= 10; i++) {
            int f = 1 + (i * z);


            if (f % e == 0) {
                d = f / e;
                break;
            }


        }

        System.out.println( "Value of d " + d );
        //encryption
        for (int i = 0; i < original_msg.size(); i++) {
            c.add( (Math.pow( original_msg.get( i ), e )) % n );

        }
        System.out.println( c );
        char[] encry_array = new char[original_msg.size()];

        for (int i = 0; i < original_msg.size(); i++) {
            double k = c.get( i );
            int value = (int) k;
            char msg_encrchar = (char) value;
            encry_array[i] = msg_encrchar;

        }
        //System.out.println("Encrypted msg is "+String.valueOf(encry_array));

        String s = "";
        for (int i = 0; i < encry_array.length; i++) {
            s = s + encry_array[i];
        }
        return s;
    }
    private void sendMessage(String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put( "sender", sender );
        hashmap.put( "receiver", receiver );
        hashmap.put( "message", message );
        reference.child( "Chats" ).push().setValue( hashmap );

//
        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference( "ChatList" ).child( fuser.getUid() ).child( userid );


        chatRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    chatRef.child( "id" ).setValue( userid );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );


    }

    static int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd( z % e, e );
    }



    private void readMessage(final String myid,
                             final String userid) {


        mchat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference( "Chats" );
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mchat.clear();
                String f;

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Chat chat = snapshot1.getValue( Chat.class );
                    if (chat.getReceiver() != null && chat.getSender() != null) {
                        if ((chat.getReceiver().equals( myid ) && chat.getSender().equals( userid )) || (chat.getReceiver().equals( userid ) && chat.getSender().equals( myid ))) {
                            String fk=chat.getMessage();
                            String msgdecrypted=decrpyt( fk );
                            Chat c2=new Chat( chat.getSender(), chat.getReceiver(),msgdecrypted);

                            mchat.add( c2 );

                        }
                    }
                    messageAdapter = new MessageAdapter( MessageActivity.this, mchat );
                    recyclerView.setAdapter( messageAdapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );


    }

}