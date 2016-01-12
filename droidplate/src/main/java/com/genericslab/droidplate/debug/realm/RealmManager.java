package com.genericslab.droidplate.debug.realm;

import android.content.Context;

import com.genericslab.droidplate.debug.realm.model.Dog;
import com.genericslab.droidplate.log.Tracer;

import io.realm.Realm;

/**
 * Created by shahab on 1/12/16.
 */
public class RealmManager {

    public void test(Context context) {
        Realm realm = Realm.getInstance(context);
        Dog dog = realm.where(Dog.class).findFirst();
        if (dog == null) {
            dog = new Dog();
            dog.setName("Rex");
            dog.setAge(5);
            realm.beginTransaction();
            realm.copyToRealm(dog);
            realm.commitTransaction();
            Tracer.d("RealmManager: Dog: " + dog + " saved.");
        } else {
            Tracer.d("RealmManager: Dog: " + dog + " retrieved.");
        }

        realm.close();
    }
}
