package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizHelper extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "oder.db";
    private static final int DATABASE_VERSION = 1;
    private static QuizHelper instance;
    private SQLiteDatabase db;


    public static synchronized QuizHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizHelper(context.getApplicationContext());
        }
        return instance;
    }

    final String SQL_CREATE_TOPIC_TABLE = "CREATE TABLE " +
            Contract.TopicTable.TABLE_NAME + "( " +
            Contract.TopicTable._ID + " INTEGER PRIMARY KEY, " +
            Contract.TopicTable.COLUMN_NAME + " TEXT, " +
            Contract.TopicTable.COLUMN_IMG + " INTEGER " +
            ")";

    final String SQL_CREATE_QUIZ_TABLE = "CREATE TABLE " +
            Contract.QuizTable.TABLE_NAME + "( " +
            Contract.QuizTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.QuizTable.COLUMN_NAME + " TEXT, " +
            Contract.QuizTable.COLUMN_DIFFICULTY + " TEXT, " +
            Contract.QuizTable.COLUMN_IMG + " INTEGER, " +
            Contract.QuizTable.COLUMN_TOPIC_ID + " INTEGER, " +
            "FOREIGN KEY(" + Contract.QuizTable.COLUMN_TOPIC_ID + ") REFERENCES " +
            Contract.TopicTable.TABLE_NAME + "(" + Contract.TopicTable._ID  + ")" + "ON DELETE CASCADE" +
            ")";

    final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
            Contract.QuestionsTable.TABLE_NAME + " ( " +
            Contract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            Contract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            Contract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            Contract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            Contract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            Contract.QuestionsTable.COLUMN_ANSWER + " TEXT, " +
            Contract.QuestionsTable.COLUMN_QUIZ_ID + " INTEGER, " +
            "FOREIGN KEY(" + Contract.QuestionsTable.COLUMN_QUIZ_ID + ") REFERENCES " +
            Contract.QuizTable.TABLE_NAME + "(" + Contract.QuizTable._ID + ")" + "ON DELETE CASCADE" +
            ")";

    final String SQL_CREATE_DICT_TABLE = "CREATE TABLE " +
            Contract.DictItemsTable.TABLE_NAME + "( " +
            Contract.DictItemsTable._ID + " INTEGER PRIMARY KEY, " +
            Contract.DictItemsTable.COLUMN_WORD + " TEXT, " +
            Contract.DictItemsTable.COLUMN_CONTENT + " TEXT " +
            ")";

    public QuizHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.TopicTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.QuizTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(SQL_CREATE_TOPIC_TABLE);
        db.execSQL(SQL_CREATE_QUIZ_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_DICT_TABLE);
        fillDictTable();
        fillTopicTable();
        fillQuizTable();
        fillQuestionsTable();
    }

    private void fillTopicTable() {
//        insertTopic(new Topic(9, "fjghjfdg", R.drawable.ic_baseline_topic_24));
    }

    public long addTopic(Topic topic) {
        SQLiteDatabase db = getWritableDatabase();
        return insertTopic(topic);
    }
    public void addTopics(ArrayList<Topic> ls) {
        SQLiteDatabase db = getWritableDatabase();
        for (Topic topic : ls) {
            insertTopic(topic);
        }
    }
    private long insertTopic(Topic topic) {
        ContentValues cv = new ContentValues();
        cv.put(Contract.TopicTable._ID, topic.getId());
        cv.put(Contract.TopicTable.COLUMN_NAME, topic.getName());
        cv.put(Contract.TopicTable.COLUMN_IMG, topic.getImg());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Contract.TopicTable.TABLE_NAME, null, cv);

    }

    private void fillQuizTable() {
    }

    public long  addQuiz(Quiz quiz){
        SQLiteDatabase db = getWritableDatabase();
        return insertQuiz(quiz);
    }

    public void addQuizs(ArrayList<Quiz> ls) {
        SQLiteDatabase db = getWritableDatabase();
        for (Quiz quiz : ls) {
            insertQuiz(quiz);
        }
    }
    private long insertQuiz(Quiz quiz) {
        ContentValues cv = new ContentValues();
        cv.put(Contract.QuizTable.COLUMN_NAME, quiz.getName());
        cv.put(Contract.QuizTable.COLUMN_IMG, quiz.getImg());
        cv.put(Contract.QuizTable.COLUMN_DIFFICULTY, quiz.getDifficulty());
        cv.put(Contract.QuizTable.COLUMN_TOPIC_ID, quiz.getTopic().getId());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Contract.QuizTable.TABLE_NAME, null, cv);
    }


    private void fillQuestionsTable() {
    }

    public long  addQuestion(Question question){
        SQLiteDatabase db = getWritableDatabase();
        return insertQuestion(question);
    }

    public void addQuestions(ArrayList<Question> ls) {
        SQLiteDatabase db = getWritableDatabase();
        for (Question question : ls) {
            insertQuestion(question);
        }
    }
    private long insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(Contract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Contract.QuestionsTable.COLUMN_ANSWER, question.getAnswer());
        cv.put(Contract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Contract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Contract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Contract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(Contract.QuestionsTable.COLUMN_QUIZ_ID, question.getQuiz().getId());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Contract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Topic> getAllTopic(){
        ArrayList<Topic> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.TopicTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(c.getInt(c.getColumnIndex(Contract.TopicTable._ID)));
                topic.setImg(c.getInt(c.getColumnIndex(Contract.TopicTable.COLUMN_IMG)));
                topic.setName(c.getString(c.getColumnIndex(Contract.TopicTable.COLUMN_NAME)));
                ls.add(topic);
            } while (c.moveToNext());
        }
        c.close();
        return ls;
    }

    public Topic getTopicById(int id){
        Topic topic = new Topic();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.TopicTable.TABLE_NAME + " WHERE " + Contract.TopicTable._ID + " = " + id , null);
        if (c.moveToFirst()) {

                topic = new Topic();
                topic.setId(c.getInt(c.getColumnIndex(Contract.TopicTable._ID)));
                topic.setImg(c.getInt(c.getColumnIndex(Contract.TopicTable.COLUMN_IMG)));
                topic.setName(c.getString(c.getColumnIndex(Contract.TopicTable.COLUMN_NAME)));

        }
        c.close();
        return topic;
    }

    public ArrayList<Quiz> getQuizsByTopic(Topic topic, String difficulty){
        ArrayList<Quiz> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = Contract.QuizTable.COLUMN_TOPIC_ID + " = ? " +
                " AND " + Contract.QuizTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(topic.getId()), difficulty};
        Cursor c = db.query(
                Contract.QuizTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Quiz quiz = new Quiz();
                quiz.setTopic(topic);
                quiz.setId(c.getInt(c.getColumnIndex(Contract.QuizTable._ID)));
                quiz.setDifficulty(difficulty);
                quiz.setImg(c.getInt(c.getColumnIndex(Contract.QuizTable.COLUMN_IMG)));
                quiz.setName(c.getString(c.getColumnIndex(Contract.QuizTable.COLUMN_NAME)));
                ls.add(quiz);
            } while (c.moveToNext());
        }
        c.close();
        return ls;
    }

    public ArrayList<Quiz> getAllQuizs(String difficulty){
        ArrayList<Quiz> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = Contract.QuizTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.query(
                Contract.QuizTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Quiz quiz = new Quiz();
                quiz.setTopic(getTopicById(c.getInt(c.getColumnIndex(Contract.QuizTable.COLUMN_TOPIC_ID))));
                quiz.setId(c.getInt(c.getColumnIndex(Contract.QuizTable._ID)));
                quiz.setDifficulty(difficulty);
                quiz.setImg(c.getInt(c.getColumnIndex(Contract.QuizTable.COLUMN_IMG)));
                quiz.setName(c.getString(c.getColumnIndex(Contract.QuizTable.COLUMN_NAME)));
                ls.add(quiz);
            } while (c.moveToNext());
        }
        c.close();
        return ls;
    }

    public ArrayList<Question> getQuestionsByQuiz(Quiz quiz){
        ArrayList<Question> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = Contract.QuestionsTable.COLUMN_QUIZ_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(quiz.getId())};
        Cursor c = db.query(
                Contract.QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuiz(quiz);
                question.setId(c.getInt(c.getColumnIndex(Contract.QuestionsTable._ID)));
                question.setAnswer(c.getString(c.getColumnIndex(Contract.QuestionsTable.COLUMN_ANSWER)));
                question.setOption1(c.getString(c.getColumnIndex(Contract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Contract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Contract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(Contract.QuestionsTable.COLUMN_OPTION4)));
                question.setQuestion(c.getString(c.getColumnIndex(Contract.QuestionsTable.COLUMN_QUESTION)));
                ls.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return ls;

    }

    public void updateTopicData(Topic topic, Context context){

        SQLiteDatabase db = getWritableDatabase();
        insertTopic(topic);
        ArrayList<Quiz> ls = new ArrayList<>();
        for( int i = 0 ; i < 3; i++){
            Quiz q = new Quiz(topic,Quiz.EASY.toUpperCase() + " Quiz " + (i+1), Quiz.EASY);
            q.setId((int) insertQuiz(q));
            ls.add(q);

        }
        for( int i = 0 ; i < 3; i++){
            Quiz q = new Quiz(topic,Quiz.MEDIUM.toUpperCase() + " Quiz " + (i+1), Quiz.MEDIUM);
            q.setId((int) insertQuiz(q));
            ls.add(q);
        }
        for( int i = 0 ; i < 3; i++){
            Quiz q = new Quiz(topic,Quiz.HARD.toUpperCase() + " Quiz " + (i+1), Quiz.HARD);
            q.setId((int) insertQuiz(q));
            ls.add(q);
        }

        for(Quiz q: ls)
            updateQuestionData(q,context);

    }



    private void updateQuestionData(Quiz q, Context context) {
        RequestQueue rq = Volley.newRequestQueue(context);
        JsonObjectRequest jar = new JsonObjectRequest(
                Request.Method.GET,
                "https://opentdb.com/api.php?amount="+Quiz.MAX_NUM_QUESTION+"&category="+q.getTopic().getId()+"&difficulty="+ q.getDifficulty() +"&type=multiple",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray arr = response.optJSONArray("results");
                        int l = arr.length();
                        for( int i = 0; i < l; i++){
                            Question qu = new Question(q, arr.optJSONObject(i));
                            int id = (int) insertQuestion(qu);
                            qu.setId(id);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        rq.add(jar);
    }

    private void fillDictTable() {
    }

    public long addWord(DictItem word) {
        SQLiteDatabase db = getWritableDatabase();
        return insertWord(word);
    }

    private long insertWord(DictItem word) {
        ContentValues cv = new ContentValues();
        cv.put(Contract.DictItemsTable.COLUMN_WORD, word.getWord());
        cv.put(Contract.DictItemsTable.COLUMN_CONTENT, word.getContent());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Contract.DictItemsTable.TABLE_NAME, null, cv);

    }

    public ArrayList<DictItem> getAllWord(){
        ArrayList<DictItem> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.DictItemsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                DictItem word = new DictItem();
                word.setId(c.getInt(c.getColumnIndex(Contract.DictItemsTable._ID)));
                word.setWord(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_WORD)));
                word.setContent(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_CONTENT)));
                ls.add(word);
            } while (c.moveToNext());
        }
        c.close();
        return ls;
    }

    public ArrayList<DictItem> getAllWordByKey(String key){
        ArrayList<DictItem> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.DictItemsTable.TABLE_NAME +
                " WHERE " + Contract.DictItemsTable.COLUMN_WORD + " LIKE '%" + key + "%'"  , null);
        if (c.moveToFirst()) {
            do {
                DictItem word = new DictItem();
                word.setId(c.getInt(c.getColumnIndex(Contract.DictItemsTable._ID)));
                word.setWord(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_WORD)));
                word.setContent(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_CONTENT)));
                ls.add(word);
            } while (c.moveToNext());
        }
        c.close();
        return ls;
    }


}
