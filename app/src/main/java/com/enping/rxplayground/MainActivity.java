package com.enping.rxplayground;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private LinearLayout list;
    private Button btnSend;
    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (LinearLayout) findViewById(R.id.lv);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnClose = (Button) findViewById(R.id.btn_close);



        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                btnSend.setOnClickListener(new View.OnClickListener() {
                    public int i = 0;

                    @Override
                    public void onClick(View v) {
                        subscriber.onNext(i++);
                    }
                });

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onCompleted();
                    }
                });

                subscriber.add(new Subscription() {
                    @Override
                    public void unsubscribe() {
                        btnClose.setEnabled(false);
                        btnSend.setEnabled(false);
                    }

                    @Override
                    public boolean isUnsubscribed() {
                        return !btnClose.isEnabled() && !btnSend.isEnabled();
                    }
                });
            }
        }).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(final Integer integer) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        final View v = getLayoutInflater().inflate(R.layout.item_list,list,false);
                        ((TextView)v.findViewById(R.id.item_tag)).setText("Switch: "+integer);
                        v.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                subscriber.onNext(integer+" - Yes");
                                subscriber.onCompleted();
                            }
                        });
                        v.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                subscriber.onNext(integer+" - No");
                                subscriber.onCompleted();
                            }
                        });
                        subscriber.add(new Subscription() {
                            @Override
                            public void unsubscribe() {
                                list.removeView(v);
                            }

                            @Override
                            public boolean isUnsubscribed() {
                                return false;
                            }
                        });
                        list.addView(v);
                    }
                });
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this,"Complete",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
