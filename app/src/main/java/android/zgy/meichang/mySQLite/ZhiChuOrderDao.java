package android.zgy.meichang.mySQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import android.zgy.meichang.R;

import java.util.ArrayList;
import java.util.List;


public class ZhiChuOrderDao {
    private static final String TAG = "ZhiChuOrderDao";

    // 列定义
    private final String[] ORDER_COLUMNS = new String[] {"Id", "content","danjia","date"};

    private Context context;
    private myOrderDBHelper ordersDBHelper;

    public ZhiChuOrderDao(Context context) {
        this.context = context;
        ordersDBHelper = new myOrderDBHelper(context);
    }

    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(myOrderDBHelper.TABLE_NAME_ZHI_CHU, new String[]{"COUNT(Id)"}, null, null, null, null, "id desc");

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 初始化数据
     */
    public void initTable(){
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("insert into " + myOrderDBHelper.TABLE_NAME_ZHI_CHU + " (Id, content, danjia, date) values (0,'','', '')");
            /*for(int j = 0; j<50; j++){
                db.execSQL("insert into " + myOrderDBHelper.TABLE_NAME_ZHI_CHU + " (Id, content, danjia, date) values ("+j+", "+ " '"+""+j+"'"+", 'danjia', 'date')");
            }*/


            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 执行自定义SQL语句
     */
    public void execSQL(String sql) {
        SQLiteDatabase db = null;

        try {
            if (sql.contains("select")){
                Toast.makeText(context, R.string.strUnableSql, Toast.LENGTH_SHORT).show();
            }else if (sql.contains("insert") || sql.contains("update") || sql.contains("delete")){
                db = ordersDBHelper.getWritableDatabase();
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                Toast.makeText(context, R.string.strSuccessSql, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 查询数据库中所有数据
     */
    public List<myOrder> getAllDate(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select * from Orders
            cursor = db.query(myOrderDBHelper.TABLE_NAME_ZHI_CHU, ORDER_COLUMNS, null, null, null, null, "id desc");

            if (cursor.getCount() > 0) {
                List<myOrder> orderList = new ArrayList<myOrder>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseOrder(cursor));
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    /**
     * 查询数据库中部分数据
     */
    public List<myOrder> getPartDate(int begin , int end){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        int i = 0;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select * from Orders
            cursor = db.query(myOrderDBHelper.TABLE_NAME_ZHI_CHU, ORDER_COLUMNS, null, null, null, null, "id desc");

            if (cursor.getCount() > 0) {
                List<myOrder> orderList = new ArrayList<myOrder>(cursor.getCount());
                Log.e(TAG, "getPartDate: " +"i="+i+" ,begin="+begin+" ,end="+end);
                while (i <= end & cursor.moveToNext()){
                    if (i >= begin  &i < end) {
                        Log.e(TAG, "getPartDate while: " +"i="+i+" ,begin="+begin+" ,end="+end);
                        orderList.add(parseOrder(cursor));
                    }
                    i++;
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    /**
     * 新增一条数据
     */
    public boolean insertDate(String content,String danjia,String date){
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
            ContentValues contentValues = new ContentValues();
            //contentValues.put("Id", id);
            contentValues.put("content", content);
            contentValues.put("danjia", danjia);
            contentValues.put("date", date);
            db.insertOrThrow(myOrderDBHelper.TABLE_NAME_ZHI_CHU, null, contentValues);

            db.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 删除一条数据  此处删除Id为7的数据
     */
    public boolean deleteOrder(int id) {
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // delete from Orders where Id = 7
            db.delete(myOrderDBHelper.TABLE_NAME_ZHI_CHU, "Id = ?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 修改一条数据  此处将Id为6的数据的OrderPrice修改了800
     */
    public boolean updateOrder(){
        SQLiteDatabase db = null;
        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set OrderPrice = 800 where Id = 6
            ContentValues cv = new ContentValues();
            cv.put("OrderPrice", 800);
            db.update(myOrderDBHelper.TABLE_NAME_ZHI_CHU,
                    cv,
                    "Id = ?",
                    new String[]{String.valueOf(6)});
            db.setTransactionSuccessful();
            return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

        return false;
    }

    /**
     * 数据查询  此处将用户名为"Bor"的信息提取出来
     */
    public List<myOrder> getBorOrder(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();

            // select * from Orders where CustomName = 'Bor'
            cursor = db.query(myOrderDBHelper.TABLE_NAME_ZHI_CHU,
                    ORDER_COLUMNS,
                    "CustomName = ?",
                    new String[] {"Bor"},
                    null, null, null);

            if (cursor.getCount() > 0) {
                List<myOrder> orderList = new ArrayList<myOrder>(cursor.getCount());
                while (cursor.moveToNext()) {
                    myOrder order = parseOrder(cursor);
                    orderList.add(order);
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 统计查询  此处查询Country为China的用户总数
     */
    public int getChinaCount(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select count(Id) from Orders where Country = 'China'
            cursor = db.query(myOrderDBHelper.TABLE_NAME_ZHI_CHU,
                    new String[]{"COUNT(Id)"},
                    "Country = ?",
                    new String[] {"China"},
                    null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return count;
    }

    /**
     * 比较查询  此处查询单笔数据中OrderPrice最高的
     */
    public myOrder getMaxOrderPrice(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select Id, CustomName, Max(OrderPrice) as OrderPrice, Country from Orders
            cursor = db.query(myOrderDBHelper.TABLE_NAME_ZHI_CHU, new String[]{"Id", "CustomName", "Max(OrderPrice) as OrderPrice", "Country"}, null, null, null, null, null);

            if (cursor.getCount() > 0){
                if (cursor.moveToFirst()) {
                    return parseOrder(cursor);
                }
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 将查找到的数据转换成Order类
     */
    private myOrder parseOrder(Cursor cursor){
        myOrder order = new myOrder();
        order.id = (cursor.getInt(cursor.getColumnIndex("Id")));
        order.content = (cursor.getString(cursor.getColumnIndex("content")));
        order.danjia = (cursor.getString(cursor.getColumnIndex("danjia")));
        order.date = (cursor.getString(cursor.getColumnIndex("date")));
        return order;
    }
}
