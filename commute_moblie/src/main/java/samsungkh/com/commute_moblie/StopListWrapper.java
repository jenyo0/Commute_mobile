package samsungkh.com.commute_moblie;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jungyong on 2017-08-27.
 */

public class StopListWrapper {

    //항목에 보이는 모든 view를 선언할 필요는 없음. find 대상만
    TextView descView;
    ImageView mapIcon;

    // 항목 layout을 초기화 하는 곳은 adapter
    // adapter에서 초기화된 view 계층의 root 전달
    // 가정 : adapter에서 view가 필요할 때 직접 find 하지 않고 wrapper 의 view를 얻어서 사용한다!
    // 이 wrapper를 adapter에서 메모리에 지속시킨다는 가정만 있으면 최초에 한번만 find
    public StopListWrapper(View root){
        descView = (TextView) root.findViewById(R.id.stop_desc);
        mapIcon = (ImageView) root.findViewById(R.id.stop_map_icon) ;
    }
}
