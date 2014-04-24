package th.ac.ku.ndsrl.kunemata.dictionary.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebResultFragment extends Fragment {
   private static final String WEBRESULT_API_URL = "http://dict.longdo.com/mobile.php?search=%s&nocache=1";

   private WebView wevResult;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_webresult, container, false);
      wevResult = (WebView) view.findViewById(R.id.wevResult);

      return view;
   }

   public void showResult(String word) {
      if (word != null && !word.equals("")) {
         wevResult.loadUrl(String.format(WEBRESULT_API_URL, word));
      }
   }

}
