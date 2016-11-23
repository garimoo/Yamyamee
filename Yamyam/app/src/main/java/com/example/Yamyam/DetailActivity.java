package com.example.Yamyam;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;


public class DetailActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFA500));
    }
    public void kakao_onclick(View v){
        try{
            final KakaoLink kakaoLink=KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoBuilder=kakaoLink.createKakaoTalkLinkMessageBuilder();

            //메세지 추가
            kakaoBuilder.addText("카카오톡 공유");

            //앱 실행 버튼
            kakaoBuilder.addAppButton("냠냠이 실행");

            //메세지 발송
            kakaoLink.sendMessage(kakaoBuilder, this);
        }catch (KakaoParameterException e){
            e.printStackTrace();
        }
    }

    public void call_onclick(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1111-2222"));
        startActivity(intent);
    }
}
