package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import android.provider.BaseColumns;

public final class Contract {
    public Contract() {
    }

    public static class TopicTable implements BaseColumns {
        public static final String TABLE_NAME = "topics";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMG = "img";
    }

    public static class QuizTable implements BaseColumns {
        public static final String TABLE_NAME = "quizs";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_TOPIC_ID = "topicid";

    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_QUIZ_ID = "quizid";
    }

    public static class DictItemsTable implements BaseColumns {
        public static final String TABLE_NAME = "dict";
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_CONTENT = "content";
    }
}
