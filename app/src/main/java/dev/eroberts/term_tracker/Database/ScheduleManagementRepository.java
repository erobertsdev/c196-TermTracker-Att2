package dev.eroberts.term_tracker.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.c196_degree_tracker.DAO.*;
import com.example.c196_degree_tracker.Entities.*;

import java.util.List;

import dev.eroberts.term_tracker.DAO.AssessmentsDAO;
import dev.eroberts.term_tracker.DAO.CoursesDAO;
import dev.eroberts.term_tracker.DAO.MentorsDAO;
import dev.eroberts.term_tracker.DAO.TermsDAO;
import dev.eroberts.term_tracker.Entities.AssessmentEntity;
import dev.eroberts.term_tracker.Entities.CourseEntity;
import dev.eroberts.term_tracker.Entities.MentorEntity;
import dev.eroberts.term_tracker.Entities.TermEntity;

public class ScheduleManagementRepository {
    private final AssessmentsDAO mAssessmentsDAO;
    private final CoursesDAO mCoursesDAO;
    private final MentorsDAO mMentorsDAO;
    private final TermsDAO mTermsDAO;
    private final LiveData<List<AssessmentEntity>> mAllAssessments;
    private final LiveData<List<AssessmentEntity>> mAssociatedAssessments;
    private final LiveData<List<CourseEntity>> mAllCourses;
    private final LiveData<List<CourseEntity>> mAssociatedCourses;
    private final LiveData<List<MentorEntity>> mAllMentors;
    private final LiveData<List<MentorEntity>> mAssociatedMentors;
    private final LiveData<List<TermEntity>> mAllTerms;
    private int courseID;
    private int termID;

    public ScheduleManagementRepository(Application application){
        ScheduleManagementDatabase db=ScheduleManagementDatabase.getDatabase(application);
        mAssessmentsDAO =db.assessmentDAO();
        mAllAssessments= mAssessmentsDAO.getAllAssessments();
        mAssociatedAssessments= mAssessmentsDAO.getAllAssociatedAssessments(courseID);
        mCoursesDAO =db.courseDAO();
        mAllCourses= mCoursesDAO.getAllCourses();
        mAssociatedCourses= mCoursesDAO.getAllAssociatedCourses(termID);
        mMentorsDAO =db.mentorDAO();
        mAllMentors= mMentorsDAO.getAllMentors();
        mAssociatedMentors= mMentorsDAO.getAllAssociatedMentors(courseID);
        mTermsDAO =db.termDAO();
        mAllTerms= mTermsDAO.getAllTerms();

    }
    public LiveData<List<AssessmentEntity>> getAllAssessments(){
        return mAllAssessments;
    }
    public LiveData<List<AssessmentEntity>> getAssociatedAssessments(int courseID){
        return mAssociatedAssessments;}
    public LiveData<List<CourseEntity>> getAllCourses(){
        return mAllCourses;
    }
    public LiveData<List<CourseEntity>> getAssociatedCourses(int termID) { return mAssociatedCourses;}
    public LiveData<List<MentorEntity>> getAllMentors() { return mAllMentors;}
    public LiveData<List<MentorEntity>> getAssociatedMentors(int courseID) { return mAssociatedMentors;}
    public LiveData<List<TermEntity>> getAllTerms() { return mAllTerms;}

    public void insert (AssessmentEntity assessmentEntity) {
        new insertAsyncTask1(mAssessmentsDAO).execute(assessmentEntity);
    }

    private static class insertAsyncTask1 extends AsyncTask<AssessmentEntity, Void, Void> {

        private final AssessmentsDAO mAsyncTaskDao;

        insertAsyncTask1(AssessmentsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AssessmentEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insert (CourseEntity courseEntity) {
        new insertAsyncTask2(mCoursesDAO).execute(courseEntity);
    }

    private static class insertAsyncTask2 extends AsyncTask<CourseEntity, Void, Void> {

        private final CoursesDAO mAsyncTaskDao;

        insertAsyncTask2(CoursesDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insert (MentorEntity mentorEntity) {
        new insertAsyncTask3(mMentorsDAO).execute(mentorEntity);
    }

    private static class insertAsyncTask3 extends AsyncTask<MentorEntity, Void, Void> {

        private final MentorsDAO mAsyncTaskDao;

        insertAsyncTask3(MentorsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MentorEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insert (TermEntity termEntity) {
        new insertAsyncTask4(mTermsDAO).execute(termEntity);
    }

    private static class insertAsyncTask4 extends AsyncTask<TermEntity, Void, Void> {

        private final TermsDAO mAsyncTaskDao;

        insertAsyncTask4(TermsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TermEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete (AssessmentEntity assessmentEntity) {
        new deleteAsyncTask1(mAssessmentsDAO).execute(assessmentEntity);
    }

    private static class deleteAsyncTask1 extends AsyncTask<AssessmentEntity, Void, Void> {

        private final AssessmentsDAO mAsyncTaskDao;

        deleteAsyncTask1(AssessmentsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AssessmentEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void delete (CourseEntity courseEntity) {
        new deleteAsyncTask2(mCoursesDAO).execute(courseEntity);
    }

    private static class deleteAsyncTask2 extends AsyncTask<CourseEntity, Void, Void> {

        private final CoursesDAO mAsyncTaskDao;

        deleteAsyncTask2(CoursesDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void delete (MentorEntity mentorEntity) {
        new deleteAsyncTask3(mMentorsDAO).execute(mentorEntity);
    }

    private static class deleteAsyncTask3 extends AsyncTask<MentorEntity, Void, Void> {

        private final MentorsDAO mAsyncTaskDao;

        deleteAsyncTask3(MentorsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MentorEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void delete (TermEntity termEntity) {
        new deleteAsyncTask4(mTermsDAO).execute(termEntity);
    }

    private static class deleteAsyncTask4 extends AsyncTask<TermEntity, Void, Void> {

        private final TermsDAO mAsyncTaskDao;

        deleteAsyncTask4(TermsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TermEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}