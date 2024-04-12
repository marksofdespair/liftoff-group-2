package org.teamlaika.laikaspetpark.models.data;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.teamlaika.laikaspetpark.models.Provider;

import java.util.ArrayList;
import java.util.List;

public class ProviderSpecification {

    public static Specification<Provider> hasSkills(boolean isGroomer,
                                                    boolean isSitter,
                                                    boolean isTrainer,
                                                    boolean isWalker) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> providerPredicates = new ArrayList<>();

            if (isGroomer) {
                providerPredicates.add(
                        criteriaBuilder.equal(root.get("isGroomer"), true)
                );
            }

            if (isSitter) {
                providerPredicates.add(
                        criteriaBuilder.equal(root.get("isSitter"), true)
                );
            }

            if (isTrainer) {
                providerPredicates.add(
                        criteriaBuilder.equal(root.get("isTrainer"), true)
                );
            }

            if (isWalker) {
                providerPredicates.add(
                        criteriaBuilder.equal(root.get("isWalker"), true)
                );
            }

            return criteriaBuilder.and(providerPredicates.toArray(new Predicate[]{}));

        };
    }
//    public Predicate toPredicate(Root<Provider> root,
//                                 CriteriaQuery<?> query,
//                                 CriteriaBuilder builder) {
//
//        Predicate returnPred = builder.disjunction();
//
//        List<Predicate> providerPredicates = new ArrayList<>();
//
//        if (filter.isGroomer()) {
//            providerPredicates.add(
//                    builder.equal(root.get("isGroomer"), filter.isGroomer())
//            );
//        }
//
//        if (filter.isSitter()) {
//            providerPredicates.add(
//                    builder.equal(root.get("isSitter"), filter.isSitter())
//            );
//        }
//
//        if (filter.isTrainer()) {
//            providerPredicates.add(
//                    builder.equal(root.get("isTrainer"), filter.isTrainer())
//            );
//        }
//
//        if (filter.isWalker()) {
//            providerPredicates.add(
//                    builder.equal(root.get("isWalker"), filter.isWalker())
//            );
//        }
//
//        returnPred = builder.and(providerPredicates.toArray(new Predicate[]{}));
//
//        return returnPred;
//    }
}
