package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.DBAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.DBTransactionDAO;

/**
 * Created by menuka on 11/20/16.
 */

public class DBExpenseManager extends ExpenseManager {
    private Context context;
    public DBExpenseManager(Context context){
        this.context = context;
        setup();
    }
    @Override
    public void setup(){
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("140650E", context.MODE_PRIVATE, null);

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                "Account_no VARCHAR PRIMARY KEY," +
                "Bank VARCHAR," +
                "Holder VARCHAR," +
                "Initial_amt REAL" +
                " );");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS TransactionLog(" +
                "Transaction_id INTEGER PRIMARY KEY," +
                "Account_no VARCHAR," +
                "Type INT," +
                "Amt REAL," +
                "Log_date DATE," +
                "FOREIGN KEY (Account_no) REFERENCES Account(Account_no)" +
                ");");

        AccountDAO accountDAO = new DBAccountDAO(sqLiteDatabase);

        setAccountsDAO(accountDAO);

        setTransactionsDAO(new DBTransactionDAO(sqLiteDatabase));
    }
}
