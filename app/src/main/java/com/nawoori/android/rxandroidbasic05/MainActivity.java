package com.nawoori.android.rxandroidbasic05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //
    PublishSubject<String> publicSubject = PublishSubject.create();

    public void doPublish(View view) {
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    publicSubject.onNext("A" + i); //발행
                    Log.i("publish", "A" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {/* */}
                }
            }
        }.start();
    }

    //구독
    public void getPublish(View view) {
        publicSubject.subscribe(
                item -> Log.i("Subscribe", "item" + item)
        );
    }

    BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
    public void doBehavior(View view) {
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    behaviorSubject.onNext("B" + i); //발행
                    Log.i("Behavior", "B" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {/* */}
                }
            }
        }.start();
    }

    //구독
    public void getBehavior(View view) {
        behaviorSubject.subscribe(
                item -> Log.i("Behavior", "item" + item)
        );
    }

    ReplaySubject<String> replaySubject = ReplaySubject.create();
    public void doReplay(View view) {
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    replaySubject.onNext("C" + i); //발행
                    Log.i("Replay", "C" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {/* */}
                }
            }
        }.start();
    }

    //구독
    public void getReplay(View view) {
        replaySubject.subscribe(
                item -> Log.i("Replay", "item" + item)
        );
    }


    AsyncSubject<String> asyncSubject = AsyncSubject.create();
    public void doAscync(View view) {
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    asyncSubject.onNext("D" + i); //발행
                    Log.i("Ascync", "D" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {/* */}
                }
                asyncSubject.onComplete();
            }
        }.start();
    }


    public void getAsync(View view) {
        asyncSubject.subscribe(
          item -> Log.i("Async", "item="+item)
        );
    }
}

