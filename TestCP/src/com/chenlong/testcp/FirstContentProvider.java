package com.chenlong.testcp;

import java.util.HashMap;
import java.util.Map;

import com.chenlong.testcp.FirstProviderMetaData.UserTableMetaData;
import com.chenlong.testcp.db.DataServiceHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class FirstContentProvider extends ContentProvider {

	public static final UriMatcher URI_MATCHER;
	
	public static final int INCOMING_USER_COLLECTION=1;
	
	public static final int INCOMING_USER_SINGLE=2;
	
	private DataServiceHelper dh;
	
	static{
		URI_MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(FirstProviderMetaData.AUTHORITY, "users", INCOMING_USER_COLLECTION);
		URI_MATCHER.addURI(FirstProviderMetaData.AUTHORITY, "users/#", INCOMING_USER_SINGLE);
	}
	
	public static Map<String, String> userProjectionMap=new HashMap<String, String>();
	static {
		userProjectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		userProjectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}
	
	@Override
	public String getType(Uri uri){
		System.out.println("getType");
		switch (URI_MATCHER.match(uri)) {
		case INCOMING_USER_COLLECTION:
			return UserTableMetaData.CONTENT_TYPE;
		case INCOMING_USER_SINGLE:
			return UserTableMetaData.CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknown Uri: "+uri);
		}
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		System.out.println("delete");
		return 0;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		System.out.println("insert");
		SQLiteDatabase db=dh.getWritableDatabase();
		long rowId=db.insert(UserTableMetaData.TABLE_NAME, null, arg1);
		if(rowId>0){
			Uri insertedUserUri=ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertedUserUri, null);
			return insertedUserUri;
		}
		throw new SQLException("Failed to insert row into "+arg0);
	}

	//回调函数，在创建provider对象时调用
	@Override
	public boolean onCreate() {
		dh=new DataServiceHelper(getContext(), FirstProviderMetaData.DATABASE_NAME,FirstProviderMetaData.DATABASE_VERSION);
		System.out.println("onCreate");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
		switch (URI_MATCHER.match(uri)) {
		case INCOMING_USER_COLLECTION:
			qb.setTables(FirstProviderMetaData.USERS_TABLE_NAME);
			qb.setProjectionMap(userProjectionMap);
			break;
		case INCOMING_USER_SINGLE:
			qb.setTables(UserTableMetaData.TABLE_NAME);
			qb.setProjectionMap(userProjectionMap);
			qb.appendWhere(UserTableMetaData._ID+"="+uri.getPathSegments().get(1));
			break;
		}
		String orderBy;
		if(TextUtils.isEmpty(sortOrder)){
			orderBy=UserTableMetaData.DEFAULT_SORT_ORDER;
		}else {
			orderBy=sortOrder;
		}
		SQLiteDatabase db=dh.getWritableDatabase();
		Cursor c=qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(),uri);
		System.out.println("query");
		return c;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		System.out.println("update");
		return 0;
	}

	public DataServiceHelper getDh() {
		return dh;
	}

	public void setDh(DataServiceHelper dh) {
		this.dh = dh;
	}

}















