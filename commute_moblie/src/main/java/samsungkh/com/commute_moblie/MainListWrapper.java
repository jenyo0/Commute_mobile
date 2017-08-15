package samsungkh.com.commute_moblie;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sangwon on 2017-08-10.
 * findViewById에 대한 성능이슈 한번은 find한다. find한 view를 성능을 고려해 메모리에 저장
 * 그 다음에 이용 시 find 없이 그대로 획득하여 사용
 * 한 항목의 find 대상이 되는 view가 여러 개라면 하나하나 따로 저장
 * 획득이 힘들어서 wrapper로 묶고 퉁으로 저장
 */

public class MainListWrapper {

    //항목에 보이는 모든 view를 선언할 필요는 없음. find 대상만
    ImageView initialView;
    TextView nameView;

    // 항목 layout을 초기화 하는 곳은 adapter
    // adapter에서 초기화된 view 계층의 root 전달
    // 가정 : adapter에서 view가 필요할 때 직접 find 하지 않고 wrapper 의 view를 얻어서 사용한다!
    // 이 wrapper를 adapter에서 메모리에 지속시킨다는 가정만 있으면 최초에 한번만 find
    public MainListWrapper(View root){
        initialView = (ImageView) root.findViewById(R.id.image_view) ;
        nameView = (TextView) root.findViewById(R.id.main_route_name);
    }
}
