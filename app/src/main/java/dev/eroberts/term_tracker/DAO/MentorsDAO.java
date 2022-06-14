package dev.eroberts.term_tracker.DAO;import androidx.lifecycle.LiveData;import androidx.room.Dao;import androidx.room.Delete;import androidx.room.Insert;import androidx.room.OnConflictStrategy;import androidx.room.Query;import dev.eroberts.term_tracker.Entities.MentorEntity;import java.util.List;
@Dao
public interface MentorsDAO { @Insert(onConflict = OnConflictStrategy.REPLACE) void insert(MentorEntity mentor);@Delete void delete(MentorEntity mentor);@Query("DELETE FROM mentor_table") void deleteAllMentors();@Query("SELECT * FROM mentor_table ORDER BY mentorID ASC") LiveData<List<MentorEntity>> getAllMentors();@Query ("SELECT * FROM mentor_table WHERE courseID= :courseID ORDER BY mentorName ASC") LiveData<List<MentorEntity>> getAllAssociatedMentors(int courseID);}

