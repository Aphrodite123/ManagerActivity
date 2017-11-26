package com.aphrodite.manageractivity.db;

import com.aphrodite.manageractivity.entity.Dog;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Aphrodite on 2017/11/26.
 */

public class RealmManager {
  private static RealmManager mRealmManager = null;

  private Realm mRealm;

  public RealmManager() {
    mRealm = Realm.getDefaultInstance();
  }

  public static RealmManager getInstance() {
    if (null == mRealmManager) {
      synchronized (RealmManager.class) {
        if (null == mRealmManager) {
          mRealmManager = new RealmManager();
        }
      }
    }
    return mRealmManager;
  }

  /**
   * 增加
   *
   * @param realmObject
   */
  public void insert(RealmObject realmObject) {
    mRealm.beginTransaction();
    mRealm.copyToRealm(realmObject);
    mRealm.commitTransaction();
  }

  /**
   * 删除
   *
   * @param id
   */
  public void delete(String id) {
    Dog dog = mRealm.where(Dog.class).equalTo("id", id).findFirst();
    mRealm.beginTransaction();
    dog.deleteFromRealm();
    mRealm.commitTransaction();
  }

  /**
   * 更新
   *
   * @param id
   * @param newName
   */
  public void update(String id, String newName) {
    Dog dog = mRealm.where(Dog.class).equalTo("id", id).findFirst();
    mRealm.beginTransaction();
    dog.setName(newName);
    mRealm.commitTransaction();
  }

  /**
   * 查询所有
   *
   * @return
   */
  public List<?> queryAllObject() {
    RealmResults<Dog> dogs = mRealm.where(Dog.class).findAll();
    //增序排列
    dogs = dogs.sort("id");
    //降序排列
//    dogs = dogs.sort("id", Sort.DESCENDING);
    return mRealm.copyFromRealm(dogs);
  }

  /**
   * 根据id(主键)查询
   *
   * @param id
   * @return
   */
  public RealmObject queryById(String id) {
    Dog dog = mRealm.where(Dog.class).equalTo("id", id).findFirst();
    return dog;
  }

  /**
   * 根据具体age查询
   *
   * @param age
   * @return
   */
  public List<?> queryByAge(int age) {
    RealmResults<Dog> dogs = mRealm.where(Dog.class).equalTo("age", age).findAll();
    return mRealm.copyFromRealm(dogs);
  }

  public void close() {
    if (mRealm.isEmpty() || mRealm.isClosed()) {
      return;
    }
    mRealm.close();
  }
}
