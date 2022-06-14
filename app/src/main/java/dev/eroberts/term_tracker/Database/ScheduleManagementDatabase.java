package dev.eroberts.term_tracker.Database;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import dev.eroberts.term_tracker.DAO.AssessmentsDAO;
import dev.eroberts.term_tracker.DAO.CoursesDAO;
import dev.eroberts.term_tracker.DAO.MentorsDAO;
import dev.eroberts.term_tracker.DAO.TermsDAO;import dev.eroberts.term_tracker.Entities.AssessmentEntity;import dev.eroberts.term_tracker.Entities.CourseEntity;import dev.eroberts.term_tracker.Entities.MentorEntity;import dev.eroberts.term_tracker.Entities.TermEntity;@Database(entities = {AssessmentEntity.class, CourseEntity.class, MentorEntity.class, TermEntity.class}, version = 4, exportSchema = false) public abstract class ScheduleManagementDatabase extends RoomDatabase {
    public abstract AssessmentsDAO assessmentDAO();
    public abstract CoursesDAO courseDAO();
    public abstract MentorsDAO mentorDAO();
    public abstract TermsDAO termDAO();

    private static volatile ScheduleManagementDatabase INSTANCE;

    static ScheduleManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ScheduleManagementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ScheduleManagementDatabase.class, "schedule_management_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AssessmentsDAO mAssessmentsDao;
        private final CoursesDAO mCoursesDao;
        private final MentorsDAO mMentorsDAO;
        private final TermsDAO mTermsDAO;
        PopulateDbAsync(ScheduleManagementDatabase db) {
            mAssessmentsDao = db.assessmentDAO();
            mCoursesDao = db.courseDAO();
            mMentorsDAO = db.mentorDAO();
            mTermsDAO = db.termDAO();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            mAssessmentsDao.deleteAllAssessments();
            mCoursesDao.deleteAllCourses();
            mMentorsDAO.deleteAllMentors();
            mTermsDAO.deleteAllTerms();
        TermEntity term1=new TermEntity(1, "Test Term 1", "6/1/2022", "12/1/2022");
        mTermsDAO.insert(term1);
        CourseEntity course=new CourseEntity(1, "Test Course 1", "6/1/2022",
                "12/1/2022", "In Progress", "Watch webinars", 1);
        mCoursesDao.insert(course);
        AssessmentEntity assessment=new AssessmentEntity(1, "Test Assessment 1", "6/1/2022",
                "Performance Assessment", 1);
        mAssessmentsDao.insert(assessment);
        MentorEntity mentor=new MentorEntity(1, "Mentor McMentorFace", "mentorface@wgu.edu",
                "505-515-5987", 1);
        mMentorsDAO.insert(mentor);
            return null;
        }
    }
}
