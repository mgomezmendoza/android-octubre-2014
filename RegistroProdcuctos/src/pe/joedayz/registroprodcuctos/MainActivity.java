package pe.joedayz.registroprodcuctos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] productos = {"Leche", "Arroz", "Azucar",
								"Aceite"};
		
		
		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> arrayAdapter =
				new ArrayAdapter<String>(this, layout, productos);
		
		ListView lista = (ListView)
					findViewById(R.id.listaProductos);
		lista.setAdapter(arrayAdapter);
		
		
		lista.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, 
					View view,
					int position, long id) {
				
				Toast.makeText(MainActivity.this,
						"Clic en la posicion " + position,
						Toast.LENGTH_SHORT).show();
				
			}	
		
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?>
					adapter, View view,
					int position, long id) {
				
				Toast.makeText(MainActivity.this,
						"Clic largo en  " + 
						adapter.getItemAtPosition(position),
						Toast.LENGTH_LONG).show();
				return false;
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
