package th.ac.ku.ndsrl.kunemata.dictionary.app;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;


public class MainActivity extends Activity {
   private WebResultFragment webResultFragment;
   EditText searchEditText;
   private SearchView searchView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      webResultFragment = (WebResultFragment) getFragmentManager().findFragmentById(R.id.fgmWebResult);
   }

   @Override
   protected void onNewIntent(Intent intent) {
      setIntent(intent);

      switch (intent.getAction()) {
         case Intent.ACTION_SEARCH:
            webResultFragment.showResult(intent.getStringExtra(SearchManager.QUERY));
            break;

         case Intent.ACTION_VIEW:
            String suggestWord = intent.getDataString();
            searchView.setQuery(suggestWord, false);
            searchView.clearFocus();
            webResultFragment.showResult(suggestWord);
            break;
      }

   }

   @SuppressWarnings("ResourceType")
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);

      SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
      searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
      searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
      searchView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);


      // Set searchView style programmatically
      // Set EditText color
      searchEditText = (EditText) searchView.findViewById(getResources().getIdentifier("android:id/search_src_text", null, null));
      searchEditText.setTextColor(Color.WHITE);
      searchEditText.setHintTextColor(Color.parseColor("#ffffff"));

      View searchPlate = searchView.findViewById(getResources().getIdentifier("android:id/search_plate", null, null));
      searchPlate.setBackgroundResource(R.drawable.textfield_searchview);

      ImageView searchHintIcon = (ImageView) searchView.findViewById(getResources().getIdentifier("android:id/search_mag_icon", null, null));
      searchHintIcon.setImageResource(R.drawable.ic_action_search);

      ImageView closeButtonImage = (ImageView) searchView.findViewById(getResources().getIdentifier("android:id/search_close_btn", null, null));
      closeButtonImage.setImageResource(R.drawable.ic_action_cancel);

      MenuItem searchItem = menu.findItem(R.id.action_search);
      searchItem.expandActionView();

      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      switch (item.getItemId()) {
         case R.id.action_search:
            return true;

         default:
            return super.onOptionsItemSelected(item);
      }
   }

}
