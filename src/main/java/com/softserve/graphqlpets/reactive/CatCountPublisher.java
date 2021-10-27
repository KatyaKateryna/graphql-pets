package com.softserve.graphqlpets.reactive;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;
import org.springframework.stereotype.Component;

@Component
public class CatCountPublisher {

    private final Flowable<Integer> publisher;
    private final PublishSubject<Integer> publishSubject = PublishSubject.create();

    public CatCountPublisher() {
        ConnectableObservable<Integer> commentUpdateObservable = publishSubject.share().publish();
        commentUpdateObservable.connect();

        publisher = commentUpdateObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(Integer catCount) {
        publishSubject.onNext(catCount);
    }

    public Flowable<Integer> getPublisher() {
        return publisher;
    }
}
