package th.ac.ku.ndsrl.kunemata.dictionary.app.provider;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import com.github.Reizinixc.wordsuggester.LongdoWordSuggester;
import com.github.Reizinixc.wordsuggester.WordSuggester;

import java.util.Arrays;
import java.util.HashMap;

public class LongdoWordSuggestionProvider extends ContentProvider {
   public static final String AUTORITIES = "th.ac.ku.ndsrl.kunemata.dictionary.app.provider.LongdoWordSuggestionProvider";
   private static final int SEARCH_LIMIT = 20;
   private WordSuggester suggester;

   @Override
   public boolean onCreate() {
      suggester = new LongdoWordSuggester(SEARCH_LIMIT);

      return true;
   }

   @Override
   public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
      HashMap<String, Object> map = new HashMap<>();
      map.put("uri", uri);
      map.put("selection", selection);
      map.put("selectionArgs", Arrays.toString(selectionArgs));
      map.put("sortOrder", sortOrder);

//      Log.d("query", map.toString());

      MatrixCursor resultCursor = new MatrixCursor(new String[] {BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_INTENT_DATA});

      if (selectionArgs.length > 0 && selectionArgs[0] != null && selectionArgs[0].length() > 1) {
         // We should get query from arg[0]
         String word = selectionArgs[0];

         // Connect to Longdo word suggest API for fetch the suggestion list
         int id = 1;
         for (String suggestionWord : suggester.getSuggestWords(word)) {
            resultCursor.addRow(new Object[] {id++, suggestionWord, suggestionWord});
         }
      }

      return resultCursor;
   }

   @Override
   public String getType(Uri uri) {
      return null;
   }

   @Override
   public Uri insert(Uri uri, ContentValues values) {
      throw new UnsupportedOperationException("Cannot insert suggest content provider");
   }

   @Override
   public int delete(Uri uri, String selection, String[] selectionArgs) {
      throw new UnsupportedOperationException("Cannot delete suggest content provider");
   }

   @Override
   public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
      throw new UnsupportedOperationException("Cannot insert suggest content provider");
   }

}
