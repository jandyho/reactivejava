package com.pandora.rxandroid.activities;


import android.os.Bundle;
import android.widget.TextView;

import com.pandora.rxandroid.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class HelloActivityV2 extends RxAppCompatActivity {
    public static final String TAG = HelloActivityV2.class.getSimpleName();

    @BindView(R.id.textView) TextView textView;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        DisposableObserver<String> observable = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                textView.setText(s);
            }
            @Override
            public void onError(@NonNull Throwable e) { }
            @Override
            public void onComplete() { }
        };
        Observable.<String>create(s -> {
            s.onNext("Hello, world!");
            s.onComplete();
        }).subscribe(observable);

//        Observable<String> observable = new DisposableObserver<String>() {
//            @Override
//            public void onNext(@NonNull String s) {
//                textView.setText(s);
//            }
//            @Override
//            public void onError(@NonNull Throwable e) { }
//            @Override
//            public void onComplete() { }
//        };

//        Observable.<String>create(s -> {
//            s.onNext("Hello, world!");
//            s.onComplete();
//        }).subscribe(o -> textView.setText(o));
//

        // Lambda 적용.
//        Observable.just("Hello, world!")
//                .subscribe(textView::setText);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
