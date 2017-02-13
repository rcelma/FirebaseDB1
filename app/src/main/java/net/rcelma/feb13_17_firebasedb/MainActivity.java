package net.rcelma.feb13_17_firebasedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
	private EditText et1;
	private EditText et2;
	private Button b1;
	private Button b2;
	private TextView tv;
	private EditText pCol;
	private EditText pGen;
	private EditText pAge;
	private EditText p4Ke;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		b1 = (Button) findViewById(R.id.b1);
		b2 = (Button) findViewById(R.id.b2);
		tv = (TextView) findViewById(R.id.tv);
		pCol = (EditText) findViewById(R.id.pColor);
		pGen = (EditText) findViewById(R.id.pGender);
		pAge = (EditText) findViewById(R.id.pAge);
		p4Ke = (EditText) findViewById(R.id.p4Kevin);
	}

	public void b1Click(View view) {

		DatabaseReference reference = FirebaseDatabase.getInstance().getReference("name");
		reference.setValue(et1.getText().toString());
	}

	public void b2Click(View view) {

		DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Contact");
		reference.child("name").setValue(et1.getText().toString());
		reference.child("surname").setValue(et2.getText().toString());

		ValueEventListener listener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				String s1 = dataSnapshot.child("name").getValue(String.class);
				String s2 = dataSnapshot.child("surname").getValue(String.class);

				tv.setText("Name: ".concat(s1).concat(" Surname: ").concat(s2));
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		};
		reference.addValueEventListener(listener);
	}

	public void b3Click(View view) {

		DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Pollo");
		final Pollo pollo = new Pollo();
		pollo.setColor(pCol.getText().toString());
		pollo.setGender(pGen.getText().toString());
		pollo.setAge(new Integer(pAge.getText().toString()));
		pollo.setForKevin(p4Ke.getText().toString());
		String uid = reference.push().getKey();
		pollo.setUid(uid);
		reference.push().setValue(pollo);
		ValueEventListener listener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				List<Pollo> pollos = new ArrayList<>();
				GenericTypeIndicator<HashMap<String, Pollo>> indicator = new GenericTypeIndicator<HashMap<String, Pollo>>() {
				};
				HashMap<String, Pollo> pollosM = dataSnapshot.getValue(indicator);
				for(Map.Entry<String, Pollo> entry: pollosM.entrySet()){
					pollos.add(entry.getValue());
				}
				Toast.makeText(MainActivity.this, pollos.toString(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		};
		reference.addValueEventListener(listener);/* */
	}
}