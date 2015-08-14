package com.ruanlopes.vidainc.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;

import com.ruanlopes.vidainc.Model.Room;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

	// database version
	private static final int database_VERSION = 1;

	// database name
	private static final String database_NAME = "RoomDB";
	private static final String table_ROOMS = "rooms";
	private static final String room_ID = "id";
	private static final String room_NAME = "title";
	private static final String room_LOCATIONX = "locationX";
	private static final String room_LOCATIONY = "locationY";
	private static final String room_DRAWABLE = "drawable";

	private static final String[] COLUMNS = {room_ID, room_NAME, room_LOCATIONX, room_LOCATIONY, room_DRAWABLE};

	public SQLiteHelper(Context context) {
		super(context, database_NAME, null, database_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{

		db.execSQL(" CREATE TABLE " + table_ROOMS + " (" +
						room_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				//room_NAME + " TEXT " +
				room_LOCATIONX + " INTEGER NOT NULL, " +
				room_LOCATIONY + " INTEGER NOT NULL, " +
				room_DRAWABLE + " INTEGER NOT NULL);"

		);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop rooms table if already exists
		db.execSQL("DROP TABLE IF EXISTS rooms");
		this.onCreate(db);
	}

	public void createCircle(Room room) {
		// get reference of the CircleDB database
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be inserted

		ContentValues values = new ContentValues();
		//values.put(room_NAME, room.getTitle());
		values.put(room_LOCATIONX, room.getLeft());
		values.put(room_LOCATIONY, room.getTop());
		values.put(room_DRAWABLE, room.getDrawable());



		// insert ROOM
		db.insert(table_ROOMS, null, values);

		// close database transaction
		db.close();
	}

	public Room readCircle(int id) {

		// get reference of the CircleDB database
		SQLiteDatabase db = this.getReadableDatabase();

		// get Room query
		Cursor cursor = db.query(table_ROOMS, // a. table
				COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

		// if results !=null, parse the first one
		if (cursor != null)
			cursor.moveToFirst();

		Room room = new Room();

		int x = Integer.parseInt(cursor.getString(2));
		int y = Integer.parseInt(cursor.getString(3));

		room.setCurrent(new Point(x,y));
		room.setDrawable(Integer.parseInt(cursor.getString(4)));

		return room;
	}

	public ArrayList<Room> getAllRooms() {

		ArrayList<Room> rooms = new ArrayList();

		// select room query
		String query = "SELECT  * FROM " + table_ROOMS;

		// get reference of the CircleDB database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// parse all results
		Room room = null;
		if (cursor.moveToFirst()) {
			do {
				room = new Room();
				//room.setId(Integer.parseInt(cursor.getString(0)));

				int a = cursor.getInt(2);
				int b = cursor.getInt(3);

				room.setCurrent(new Point(a,b));
				room.setDrawable(cursor.getInt(4));

				// Add room to rooms
				rooms.add(room);
			} while (cursor.moveToNext());
		}
		return rooms;
	}

}
