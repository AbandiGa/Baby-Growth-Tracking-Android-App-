import java.util.ArrayList;
import java.util.List;

import ghadeer.android.baby.model.Baby;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MenuActivity extends ListActivity{
    //Our DataSource to work with database.
    private BabyDataSource dataSource;
    //Used to store all baby to be used in ListView adapter.
    private List<Baby> baby = new ArrayList<Baby>();
     //To be used as an adapter with ListView.
    //private BabyAdapter adapter;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.main);

	final Button button = (Button) findViewById(R.id.);
	button.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
		// Perform action on click
	    }
	});
    }
}
