package com.atharva.crud.repository.specification.query;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Operator {
    GT {        
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(
                cb.greaterThan(
                    (Expression<Comparable>) key, cb.literal((Comparable) value)
                ), predicate
            );
        }
    } 
    ,

    GTE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.greaterThanOrEqualTo((Expression<Comparable>) key, cb.literal((Comparable) value)), predicate);
        }
    },

    LT {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.lessThan((Expression<Comparable>) key, cb.literal((Comparable) value)), predicate);
        }
    },

    LTE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.lessThanOrEqualTo((Expression<Comparable>) key, cb.literal((Comparable) value)), predicate);
        }
    },


    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.equal(key, value), predicate);
        }
    },

    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },

    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Expression<String> key = this.getPath(root, request);
            return cb.and(cb.like(cb.upper(key), "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
        }
    },

    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> inClause = cb.in(this.getPath(root, request));
            for (Object value : values) {
                inClause.value(request.getFieldType().parse(value.toString()));
            }
            return cb.and(inClause, predicate);
        }
    },

    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = this.getPath(root, request);
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = this.getPath(root, request);
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);

    //with this piece of code I can use nested properties on my entities.
    public <T,V> Path<V> getPath(Root<T> root, FilterRequest request) {
        String [] keys = request.getKey().split("\\.");
        Path<V> path = root.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            path = path.get(keys[i]);
        }
        return path;
    }

    

}
