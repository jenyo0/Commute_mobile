package samsungkh.com.commute_moblie;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //하위호환성 문제로 support LIB 를 import해야 함.
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //메뉴구성을 위해 자동으로 콜되는 함수
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //메뉴 객체 획득하고 그 객체에 포함된 SearchView 획득
        MenuItem menuItem = menu.findItem(R.id.menu_main_search);
        searchView=(SearchView) MenuItemCompat.getActionView(menuItem);  //dprecated되었지만 하위호환성문제때문에 사용(최근에 됨)
        //hint문자열
        searchView.setQueryHint(getResources().getString(R.string.main_search_hint));
        //초기아이콘으로셋팅
        searchView.setIconifiedByDefault(true);

        //이벤트
        searchView.setOnQueryTextListener(queryListener);  //하단이벤트 참조

        return super.onCreateOptionsMenu(menu);
    }

    //Inline query
    SearchView.OnQueryTextListener queryListener = new SearchView.OnQueryTextListener() {

        //검색버튼 눌렀을 때 call
        @Override
        public boolean onQueryTextSubmit(String query) {
            //검색어 초기화
            searchView.setQuery("", false);
            searchView.setIconified(true);

            //로직처리부분
            Log.d("kkang", query);

            return false;
        }

        //검색어 입력 시 마다 call(추천단어에 사용)
        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}
