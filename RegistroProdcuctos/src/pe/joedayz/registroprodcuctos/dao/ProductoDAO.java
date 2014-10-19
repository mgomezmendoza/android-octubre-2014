package pe.joedayz.registroprodcuctos.dao;

import pe.joedayz.registroprodcuctos.modelo.Producto;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductoDAO extends SQLiteOpenHelper{

	private static final String DATABASE = "RegistroProducto";
	private static final int VERSION = 1;

	public ProductoDAO(Context context) {
		super(context, DATABASE, null, VERSION);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, 
			int newVersion) {
		String ddl = "DROP TABLE IF EXISTS Producto";
		db.execSQL(ddl);		
		this.onCreate(db);		
	}
	
	public void guardar(Producto producto) {
		ContentValues values = new ContentValues();
		values.put("nombre", producto.getNombre());
		values.put("descripcion", producto.getDescripcion());
		values.put("foto", producto.getFoto());
		values.put("rating", producto.getRating());
		
		getWritableDatabase().insert("Producto", null, values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE Producto (id PRIMARY KEY, "+
				"nombre TEXT UNIQUE NOT NULL, " +
				"descripcion TEXT," +
				"foto TEXT, " +
				"rating REAL);";
		db.execSQL(ddl);
	}



}
