package pe.joedayz.registroprodcuctos;

import pe.joedayz.registroprodcuctos.dao.ProductoDAO;
import pe.joedayz.registroprodcuctos.modelo.Producto;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Formulario extends ActionBarActivity {

	private FormularioHelper formularioHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		formularioHelper = new FormularioHelper(this);
		Producto producto = 
				formularioHelper.guardarProductoDeFormulario();
		
		ProductoDAO dao = new ProductoDAO(this);
		dao.guardar(producto);
		
	
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.formulario, menu);
//		return true;
//	}
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
