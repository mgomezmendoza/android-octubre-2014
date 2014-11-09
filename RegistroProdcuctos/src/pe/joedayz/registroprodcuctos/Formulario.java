package pe.joedayz.registroprodcuctos;

import pe.joedayz.registroprodcuctos.dao.ProductoDAO;
import pe.joedayz.registroprodcuctos.modelo.Producto;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*modificado*/
/*modificado*/
/*modificado*/
public class Formulario extends ActionBarActivity {

	private FormularioHelper formularioHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		formularioHelper = new FormularioHelper(this);

		Button boton = (Button)
					findViewById(R.id.btnGuardarProducto);
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Producto producto = 
						formularioHelper.guardarProductoDeFormulario();
				
				ProductoDAO dao = new ProductoDAO(Formulario.this);
				dao.guardar(producto);
				dao.close();
				
				finish();
			}
		});
		
		
		
		


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
