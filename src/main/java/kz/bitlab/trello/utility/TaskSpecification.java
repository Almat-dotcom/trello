package kz.bitlab.trello.utility;

import jakarta.persistence.criteria.Predicate;
import kz.bitlab.trello.entity.Task;
import kz.bitlab.trello.enums.TaskStatus;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TaskSpecification {
    public Specification<Task> getTaskSpecification(String title, TaskStatus status, Long userId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (title != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if(userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId));
            }

            Predicate commonPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

            return commonPredicate;
        };
    }
}
