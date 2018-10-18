package com.example.suzuki.datadetailview;

import java.util.Arrays;

public class Prefecture {
    private static String[] PREFUCTURE_LIST = {
            "北海道", "青森県","秋田県","岩手県","山形県",
            "宮城県","福島県","群馬県","栃木県","茨城県",
            "千葉県","埼玉県","東京都","神奈川県","山梨県",
            "長野県","静岡県","愛知県","岐阜県","新潟県",
            "富山県","石川県","福井県","滋賀県","三重県",
            "和歌山県","奈良県","京都府","大阪府","兵庫県",
            "鳥取県","岡山県","島根県","広島県","山口県",
            "香川県","徳島県","愛媛県","高知県","大分県",
            "福岡県","佐賀県","長崎県","熊本県","宮崎県",
            "鹿児島県", "沖縄県"
    };

    private String herePrefecture;

    Prefecture(){
        this.herePrefecture = "";
    }

    Prefecture(String prefec){
        this.herePrefecture = prefec;
    }

    public String getHerePrefecture() {
        return herePrefecture;
    }

    public static boolean isPrefucture(String placeName){
        return Arrays.asList(PREFUCTURE_LIST).contains(placeName);
    }
}
