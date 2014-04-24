package th.ac.ku.ndsrl.kunemata.dictionary.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class WebResultFragment extends Fragment {
   private static final String WEBRESULT_API_URL = "http://dict.longdo.com/mobile.php?search=%s&nocache=1";

   private PullToRefreshLayout pullToRefreshLayout;
   private WebView wevResult;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_webresult, container, false);
      wevResult = (WebView) view.findViewById(R.id.wevResult);

      return view;
   }

   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
      final Activity activity = getActivity();
      pullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.ptr_layout);
      ActionBarPullToRefresh.from(activity)
            .allChildrenArePullable()
            .listener(new OnRefreshListener() {

               @Override
               public void onRefreshStarted(View view) {
                  showResult(activity.getIntent().getDataString());
                  pullToRefreshLayout.setRefreshComplete();
               }

            })
            .setup(pullToRefreshLayout);
   }

   public void showResult(String word) {
      if (word != null && !word.equals("")) {
         wevResult.loadUrl(String.format(WEBRESULT_API_URL, word));
      }
   }

}
