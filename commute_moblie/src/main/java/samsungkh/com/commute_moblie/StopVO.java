package samsungkh.com.commute_moblie;

import java.io.Serializable;

/**
 * Created by Jungyong on 2017-08-27.
 */

public class StopVO implements Serializable{

    String rt_id;     // route_id
    String stop_id;   // 정거장 id
    String stop_desc; // 정거장 이름
    String gps;        // gps 정보

}
