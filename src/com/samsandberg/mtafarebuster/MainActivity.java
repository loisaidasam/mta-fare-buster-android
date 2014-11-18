package com.samsandberg.mtafarebuster;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.samsandberg.mtafarebuster.MtaUtil.MtaUtilResult;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			final ListView listView = (ListView) rootView.findViewById(R.id.results_list);
			
			final EditText amountOnCard = (EditText) rootView.findViewById(R.id.amount_on_card);
			
			Button buttonGo = (Button) rootView.findViewById(R.id.button_go);
			buttonGo.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (amountOnCard.getText().toString().equals("")) {
						amountOnCard.setText(getActivity().getString(R.string.amount_on_card_default_value));
					}
					float amountOnCardValue = Float.valueOf(amountOnCard.getText().toString());
					ArrayList<MtaUtilResult> results = MtaUtil.amountToAdd(amountOnCardValue);
					ArrayList<String> strings = new ArrayList<String>();
					for (MtaUtilResult result : results) {
						strings.add(result.toString());
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings);
					listView.setAdapter(adapter);
				}
			});

			return rootView;
		}
	}

}
