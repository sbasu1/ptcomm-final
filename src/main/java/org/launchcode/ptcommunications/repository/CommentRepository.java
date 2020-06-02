package org.launchcode.ptcommunications.repository;

import org.launchcode.ptcommunications.models.Comment;
import org.launchcode.ptcommunications.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<Comment, Integer> {
}
