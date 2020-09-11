package com.glg.roomtest;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Description:
 *
 * @package: com.glg.roomtest
 * @className: AppDataBase
 * @author: gao
 * @date: 2020/8/31 16:53
 */
@Database(entities = {User.class}, version = 5)//升级数据库的时候一定要记得修改此处的版本号
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    //数据库变动添加Migration
    //增加和删除字段的时候要修改数据表对应的实体类的属性
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user "
                    + " ADD COLUMN age INTEGER NOT NULL DEFAULT 0");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user "
                    + " ADD COLUMN grade INTEGER NOT NULL DEFAULT 1");
        }
    };

    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user "
                    + " RENAME TO users");
        }
    };

    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `user_new` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `first_name` TEXT, `last_name` TEXT, `age` INTEGER NOT NULL)");
            database.execSQL("INSERT INTO user_new (uid, first_name, last_name, age) SELECT uid, first_name, last_name, age FROM users");
            database.execSQL("DROP TABLE users");
            database.execSQL("ALTER TABLE user_new RENAME TO user");
        }
    };
}
