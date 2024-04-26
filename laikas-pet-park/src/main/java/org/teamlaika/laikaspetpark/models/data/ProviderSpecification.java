package org.teamlaika.laikaspetpark.models.data;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.User;

import java.util.ArrayList;
import java.util.List;

public class ProviderSpecification {

    public static Specification<Provider> providerFilter (String isGroomer,
                                                          String isSitter,
                                                          String isTrainer,
                                                          String isWalker,
                                                          Integer zipcode) {
        return (root, query, criteriaBuilder) -> {

            Join<Provider, User> userJoin = root.join("user");

            List<Predicate> providerPredicates = new ArrayList<>();

            if (isGroomer.equals("true")) {
                providerPredicates.add(
                    criteriaBuilder.equal(root.get("isGroomer"), true)
                );
            }

            if (isSitter.equals("true")) {
                providerPredicates.add(
                        criteriaBuilder.equal(root.get("isSitter"), true)
                );
            }

            if (isTrainer.equals("true")) {
                providerPredicates.add(
                        criteriaBuilder.equal(root.get("isTrainer"), true)
                );
            }

            if (isWalker.equals("true")) {
                providerPredicates.add(
                        criteriaBuilder.equal(root.get("isWalker"), true)
                );
            }

            if (!zipcode.toString().isEmpty()) {
                providerPredicates.add(
                        criteriaBuilder.equal(userJoin.get("zipcode"), zipcode)
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
