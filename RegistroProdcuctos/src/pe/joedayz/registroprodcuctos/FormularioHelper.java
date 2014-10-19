package pe.joedayz.registroprodcuctos;

import pe.joedayz.registroprodcuctos.modelo.Producto;
import android.widget.EditText;
import android.widget.RatingBar;

public class FormularioHelper {

	
	private EditText editNombre;
	private EditText editDescripcion;
	private RatingBar ratingProducto;

	public FormularioHelper(Formulario formulario){
		editNombre = (EditText) 
				formulario.findViewById(R.id.editNombre);
		
		editDescripcion = (EditText) 
				formulario.findViewById(R.id.editDescripcion);
		
		ratingProducto = (RatingBar)
				formulario.findViewById(R.id.ratingProducto);
		
	}

	public Producto guardarProductoDeFormulario() {
		Producto producto = new Producto();
		producto.setNombre(editNombre.getText().toString());
		producto.setDescripcion(editDescripcion.getText().toString());
		producto.setRating((double) ratingProducto.getRating());
		
		return producto;
	}
}
