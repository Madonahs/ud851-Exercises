package com.example.android.waitlist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.GuestListAdapter;
import com.example.android.waitlist.R;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "John");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Tim");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Jessica");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 99);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Larry");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Kim");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 45);
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (WaitlistContract.WaitlistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}

/*
package com.example.android.waitlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.data.WaitlistContract;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    // Holds on to the cursor to display the waitlist
    private Cursor mCursor;
    private Context mContext;


/*public GuestListAdapter(Context context, Cursor cursor) {
    this.mContext = context;
    this.mCursor = cursor;
}*/

   /* @Override
    public GuestListAdapter.GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.guest_list_item, parent, false);
        return new GuestListAdapter.GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestListAdapter.GuestViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        // COMPLETED (6) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));

        // Display the guest name
        holder.nameTextView.setText(name);
        // Display the party count
        holder.partySizeTextView.setText(String.valueOf(partySize));
        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
   /* public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }*/
/*
/**
 * Inner class to hold the views needed to display a single item in the recycler-view
 */
/*class GuestViewHolder extends RecyclerView.ViewHolder {

    // Will display the guest name
    TextView nameTextView;
    // Will display the party size number
    TextView partySizeTextView;

    /**
     * Constructor for our ViewHolder. Within this constructor, we get a reference to our
     * TextViews
     *
     * @param itemView The View that you inflated in
     *                 {@link GuestListAdapter#onCreateViewHolder(ViewGroup, int)}
     */
    /*public GuestViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
        partySizeTextView = (TextView) itemView.findViewById(R.id.party_size_text_view);
    }

}
}
 */
   /*public class TestUtil {

       public static void insertFakeData(SQLiteDatabase db){
           if(db == null){
               return;
           }
           //create a list of fake guests
           List<ContentValues> list = new ArrayList<ContentValues>();

           ContentValues cv = new ContentValues();
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "John");
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 12);
           list.add(cv);

           cv = new ContentValues();
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Tim");
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 2);
           list.add(cv);

           cv = new ContentValues();
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Jessica");
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 99);
           list.add(cv);

           cv = new ContentValues();
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Larry");
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 1);
           list.add(cv);

           cv = new ContentValues();
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Kim");
           cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 45);
           list.add(cv);

           //insert all guests in one transaction
           try
           {
               db.beginTransaction();
               //clear the table first
               db.delete (WaitlistContract.WaitlistEntry.TABLE_NAME,null,null);
               //go through the list and add one by one
               for(ContentValues c:list){
                   db.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, c);
               }
               db.setTransactionSuccessful();
           }
           catch (SQLException e) {
               //too bad :(
           }
           finally
           {
               db.endTransaction();
           }

       }
   }

/*
package com.example.android.waitlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.data.WaitlistContract;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    // Holds on to the cursor to display the waitlist
    private Cursor mCursor;
    private Context mContext;


/*public GuestListAdapter(Context context, Cursor cursor) {
    this.mContext = context;
    this.mCursor = cursor;
}*/

   /* @Override
    public GuestListAdapter.GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.guest_list_item, parent, false);
        return new GuestListAdapter.GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestListAdapter.GuestViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        // COMPLETED (6) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));

        // Display the guest name
        holder.nameTextView.setText(name);
        // Display the party count
        holder.partySizeTextView.setText(String.valueOf(partySize));
        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
   /* public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }*/
/*
/**
 * Inner class to hold the views needed to display a single item in the recycler-view
 */
/*class GuestViewHolder extends RecyclerView.ViewHolder {

    // Will display the guest name
    TextView nameTextView;
    // Will display the party size number
    TextView partySizeTextView;

    /**
     * Constructor for our ViewHolder. Within this constructor, we get a reference to our
     * TextViews
     *
     * @param itemView The View that you inflated in
     *                 {@link GuestListAdapter#onCreateViewHolder(ViewGroup, int)}
     */
    /*public GuestViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
        partySizeTextView = (TextView) itemView.findViewById(R.id.party_size_text_view);
    }

}
}
public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "John");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Tim");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Jessica");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 99);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Larry");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Kim");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 45);
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (WaitlistContract.WaitlistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}

/*
package com.example.android.waitlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.data.WaitlistContract;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    // Holds on to the cursor to display the waitlist
    private Cursor mCursor;
    private Context mContext;


/*public GuestListAdapter(Context context, Cursor cursor) {
    this.mContext = context;
    this.mCursor = cursor;
}*/

   /* @Override
    public GuestListAdapter.GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.guest_list_item, parent, false);
        return new GuestListAdapter.GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestListAdapter.GuestViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        // COMPLETED (6) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));

        // Display the guest name
        holder.nameTextView.setText(name);
        // Display the party count
        holder.partySizeTextView.setText(String.valueOf(partySize));
        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
   /* public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }*/
/*
/**
 * Inner class to hold the views needed to display a single item in the recycler-view
 */
/*class GuestViewHolder extends RecyclerView.ViewHolder {

    // Will display the guest name
    TextView nameTextView;
    // Will display the party size number
    TextView partySizeTextView;

    /**
     * Constructor for our ViewHolder. Within this constructor, we get a reference to our
     * TextViews
     *
     * @param itemView The View that you inflated in
     *                 {@link GuestListAdapter#onCreateViewHolder(ViewGroup, int)}
     */
    /*public GuestViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
        partySizeTextView = (TextView) itemView.findViewById(R.id.party_size_text_view);
    }

}
}
*/