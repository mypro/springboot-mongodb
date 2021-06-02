package org.xinhua.gk.test.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xinhua.gk.common.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Junit {

    @Before
    public void before() throws Exception {
        System.out.println("*****************************************");
    }

    @After
    public void after() throws Exception {
        System.out.println("*****************************************");
    }

    /**
     * Method: checkUserNavs(String seqId, String systemId, String authorityServer, String getNavs)
     */
    @Test
    public void test() throws Exception {
       String s =  "rtmp://172.22.26.174:1935/live/289d69a5b74f48f9ab75788818e172b3?token=7030b851789f4978ac9d045029153182";
        System.out.println(s.substring(s.lastIndexOf("/")+1,s.lastIndexOf("?")));
    }

    @Test
    public void tes1t() throws Exception {

        System.out.println(new Date(1607500681150L));
    }


    @Test
    public void test1() throws Exception {
        int x = -2147483648;

//        System.out.println(String.valueOf(Math.abs(x)));
        System.out.println(reverse1(x));
        System.out.println(reverse(x));
    }
    public int reverse1(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }



    public int reverse(int x) {
        if (x > (Math.pow(2, 31) - 1) || x < (0 - Math.pow(2, 31)) || x == 0) return 0;

        if (x > 0) {
            String s = rev(String.valueOf(x));
            if (null == s) {
                return 0;
            }
            return Integer.valueOf(s);
        } else {
            String s = rev(String.valueOf(Math.abs(x)));
            if (null == s) {
                return 0;
            }
            return 0 - Integer.valueOf(s);
        }

    }

    public String rev(String s) {
        if (null != s && s.length() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--) {
                sb.append(s.charAt(i));
            }
            long l = Long.valueOf(sb.toString());
            if (l > (Math.pow(2, 31) - 1) || l < (0 - Math.pow(2, 31))) {
                return null;
            }
            return sb.toString();
        }
        return null;

    }

    @Test
    public void testFindByLiveId() throws Exception {
        JSONObject re = JSON.parseObject("{\"bakStreamAddress\":[{\"备用地址1\":[{\"protocol\":1,\"type\":5,\"streamId\":\"liveId2021041200003\",\"channelName\":\"新华社流\",\"pushAddress\":\"rtmp://push-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003?auth_key=1620786669-b3b872340c8f43449684082207df0b27-1618194669660-bf7b83142f8517a0a0f5235a85ca4453\",\"playAddress\":{\"rtmp\":\"rtmp://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003?auth_key=1620786669-469769b3d32d422a80d533790d3c0889-1618194669660-509ea69f5b88e9c878e89d6d55e92c6e\",\"flv\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003.flv?auth_key=1620786669-3a1b0f59bc6345bcb1acefbaf2b64209-1618194669660-e7d0c55d5805c71a394bc10e65641b63\",\"hls\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003.m3u8?auth_key=1620786669-50332364000749ac96f385af15feb638-1618194669660-f3e8fd5c7bc773263f0c876a1f1369ac\"},\"outputList\":[{\"channelDescribe\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003.flv?auth_key=1620786669-3a1b0f59bc6345bcb1acefbaf2b64209-1618194669660-e7d0c55d5805c71a394bc10e65641b63\",\"rtmpDownstreamAddress\":\"rtmp://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003?auth_key=1620786669-469769b3d32d422a80d533790d3c0889-1618194669660-509ea69f5b88e9c878e89d6d55e92c6e\",\"channelStatus\":\"original\",\"resolution\":\"1080\",\"hlsDownstreamAddress\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003.m3u8?auth_key=1620786669-50332364000749ac96f385af15feb638-1618194669660-f3e8fd5c7bc773263f0c876a1f1369ac\"},{\"channelDescribe\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003_lhd.flv?auth_key=1620786669-fe53b7ddf46243558240f052a39e2ac9-1618194669660-cb0a5764219fcc0c245c813827323a62\",\"rtmpDownstreamAddress\":\"rtmp://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003_lhd?auth_key=1620786669-450b206a3c13413280c8485524736fee-1618194669660-d748d037f0784d6415d752fd3054da42\",\"channelStatus\":\"lhd\",\"resolution\":\"648\",\"hlsDownstreamAddress\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200003_lhd.m3u8?auth_key=1620786669-591588ea5873435a91b4c3441fbb44c4-1618194669660-6e51d81cf45d3d8add7930f0985563f1\"}],\"status\":1},{\"protocol\":1,\"type\":7,\"streamId\":\"liveId2021041200004\",\"channelName\":\"阿里流\",\"pushAddress\":\"rtmp://push-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004?auth_key=1620786703-199977892fc347aea503796c8f96417b-1618194703440-503363bfbcfce378400f547fe5444439\",\"playAddress\":{\"rtmp\":\"rtmp://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004?auth_key=1620786703-06bf059d5cba4225b47857ebe63efef4-1618194703440-8b1a49ba795b227cc924b09ebb13f1eb\",\"flv\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004.flv?auth_key=1620786703-d0b8e4dc0a394c33a34ab135c160cfb3-1618194703440-c17aff14ff2f0312349602a4bb604407\",\"hls\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004.m3u8?auth_key=1620786703-c0453953be544ae4acc73663441dd827-1618194703440-486d49971de485cfc0e85376bdd0ba0d\"},\"outputList\":[{\"channelDescribe\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004.flv?auth_key=1620786703-d0b8e4dc0a394c33a34ab135c160cfb3-1618194703440-c17aff14ff2f0312349602a4bb604407\",\"rtmpDownstreamAddress\":\"rtmp://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004?auth_key=1620786703-06bf059d5cba4225b47857ebe63efef4-1618194703440-8b1a49ba795b227cc924b09ebb13f1eb\",\"channelStatus\":\"original\",\"resolution\":\"1080\",\"hlsDownstreamAddress\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004.m3u8?auth_key=1620786703-c0453953be544ae4acc73663441dd827-1618194703440-486d49971de485cfc0e85376bdd0ba0d\"},{\"channelDescribe\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004_lhd.flv?auth_key=1620786703-9efd1a5983f24bbc85905a38bc303555-1618194703440-c0ad272c5e56b55269487f586c36377c\",\"rtmpDownstreamAddress\":\"rtmp://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004_lhd?auth_key=1620786703-7a7cd1a61c4144c9bf629b75786b29d1-1618194703440-840fad5f33d0a5f9ca09ef774f6bd5a8\",\"channelStatus\":\"lhd\",\"resolution\":\"648\",\"hlsDownstreamAddress\":\"http://play-a.live.xinhua-news.cn/xhs_live_transcode/liveId2021041200004_lhd.m3u8?auth_key=1620786703-b414a27854a1460b9bf5442efa858bba-1618194703440-8232ed4caee03c72048a26d0f195e05a\"}],\"status\":1}]]}");
        System.out.println(re.getJSONArray(Constants.BAK_STREAM_ADDRESS));
    }

    @Test
    public void test2() throws Exception {
//        List<String > a = null;
        List<String > a = new ArrayList<>();
        for (String s  : a) {
            System.out.println(s);
        }
    }


    @Test
    public void testHashmap() throws Exception {
        HashMap map = new HashMap();
        map.put("test", "value");
        System.out.println(map);
        System.out.println(map.hashCode());
    }
}