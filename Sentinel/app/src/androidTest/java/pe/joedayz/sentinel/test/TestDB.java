package pe.joedayz.sentinel.test;


import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;


import java.util.Map;
import java.util.Set;

import pe.joedayz.sentinel.data.WeatherContract;
import pe.joedayz.sentinel.data.WeatherDbHelper;

import static pe.joedayz.sentinel.data.WeatherContract.WeatherEntry.*;

public class TestDB extends AndroidTestCase {

    public static final String LOG_TAG = TestDB.class.getSimpleName();

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb() {

        // If there's an error in those massive SQL table creation Strings,
        // errors will be thrown here when you try to get a writable database.
        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues testValues = createNorthPoleLocationValues();

        long locationRowId;
        locationRowId = db.insert(WeatherContract.LocationEntry.TABLE_NAME, null, testValues);

        // Verify we got a row back.
        assertTrue(locationRowId != -1);
        Log.d(LOG_TAG, "New row id: " + locationRowId);

        // Data's inserted.  IN THEORY.  Now pull some out to stare at it and verify it made
        // the round trip.

        // A cursor is your primary interface to the query results.
        Cursor cursor = db.query(
                WeatherContract.LocationEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
        );

        validateCursor(cursor, testValues);

        // Fantastic.  Now that we have a location, add some weather!
        ContentValues weatherValues = createWeatherValues(locationRowId);

        long weatherRowId = db.insert(TABLE_NAME, null, weatherValues);
        assertTrue(weatherRowId != -1);

        // A cursor is your primary interface to the query results.
        Cursor weatherCursor = db.query(
                TABLE_NAME,  // Table to Query
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null  // sort order
        );

        validateCursor(weatherCursor, weatherValues);

        dbHelper.close();
    }

    static ContentValues createWeatherValues(long locationRowId) {
        ContentValues weatherValues = new ContentValues();
        weatherValues.put(COLUMN_LOC_KEY, locationRowId);
        weatherValues.put(COLUMN_DATETEXT, "20141205");
        weatherValues.put(COLUMN_DEGREES, 1.1);
        weatherValues.put(COLUMN_HUMIDITY, 1.2);
        weatherValues.put(COLUMN_PRESSURE, 1.3);
        weatherValues.put(COLUMN_MAX_TEMP, 75);
        weatherValues.put(COLUMN_MIN_TEMP, 65);
        weatherValues.put(COLUMN_SHORT_DESC, "Asteroids");
        weatherValues.put(COLUMN_WIND_SPEED, 5.5);
        weatherValues.put(COLUMN_WEATHER_ID, 321);

        return weatherValues;
    }

    static ContentValues createNorthPoleLocationValues() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, "99705");
        testValues.put(WeatherContract.LocationEntry.COLUMN_CITY_NAME, "North Pole");
        testValues.put(WeatherContract.LocationEntry.COLUMN_COORD_LAT, 64.7488);
        testValues.put(WeatherContract.LocationEntry.COLUMN_COORD_LONG, -147.353);

        return testValues;
    }

    static void validateCursor(Cursor valueCursor, ContentValues expectedValues) {

        assertTrue(valueCursor.moveToFirst());

        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse(idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals(expectedValue, valueCursor.getString(idx));
        }
        valueCursor.close();
    }
}