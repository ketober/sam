package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamRoleMutexExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamRoleMutexExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMutexIdIsNull() {
            addCriterion("MUTEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andMutexIdIsNotNull() {
            addCriterion("MUTEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMutexIdEqualTo(String value) {
            addCriterion("MUTEX_ID =", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdNotEqualTo(String value) {
            addCriterion("MUTEX_ID <>", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdGreaterThan(String value) {
            addCriterion("MUTEX_ID >", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdGreaterThanOrEqualTo(String value) {
            addCriterion("MUTEX_ID >=", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdLessThan(String value) {
            addCriterion("MUTEX_ID <", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdLessThanOrEqualTo(String value) {
            addCriterion("MUTEX_ID <=", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdLike(String value) {
            addCriterion("MUTEX_ID like", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdNotLike(String value) {
            addCriterion("MUTEX_ID not like", value, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdIn(List<String> values) {
            addCriterion("MUTEX_ID in", values, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdNotIn(List<String> values) {
            addCriterion("MUTEX_ID not in", values, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdBetween(String value1, String value2) {
            addCriterion("MUTEX_ID between", value1, value2, "mutexId");
            return (Criteria) this;
        }

        public Criteria andMutexIdNotBetween(String value1, String value2) {
            addCriterion("MUTEX_ID not between", value1, value2, "mutexId");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNull() {
            addCriterion("ROLEID is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("ROLEID is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(String value) {
            addCriterion("ROLEID =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(String value) {
            addCriterion("ROLEID <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(String value) {
            addCriterion("ROLEID >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(String value) {
            addCriterion("ROLEID >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(String value) {
            addCriterion("ROLEID <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(String value) {
            addCriterion("ROLEID <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLike(String value) {
            addCriterion("ROLEID like", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotLike(String value) {
            addCriterion("ROLEID not like", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<String> values) {
            addCriterion("ROLEID in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<String> values) {
            addCriterion("ROLEID not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(String value1, String value2) {
            addCriterion("ROLEID between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(String value1, String value2) {
            addCriterion("ROLEID not between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdIsNull() {
            addCriterion("MUTEX_ROLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdIsNotNull() {
            addCriterion("MUTEX_ROLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdEqualTo(String value) {
            addCriterion("MUTEX_ROLE_ID =", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdNotEqualTo(String value) {
            addCriterion("MUTEX_ROLE_ID <>", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdGreaterThan(String value) {
            addCriterion("MUTEX_ROLE_ID >", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("MUTEX_ROLE_ID >=", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdLessThan(String value) {
            addCriterion("MUTEX_ROLE_ID <", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdLessThanOrEqualTo(String value) {
            addCriterion("MUTEX_ROLE_ID <=", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdLike(String value) {
            addCriterion("MUTEX_ROLE_ID like", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdNotLike(String value) {
            addCriterion("MUTEX_ROLE_ID not like", value, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdIn(List<String> values) {
            addCriterion("MUTEX_ROLE_ID in", values, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdNotIn(List<String> values) {
            addCriterion("MUTEX_ROLE_ID not in", values, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdBetween(String value1, String value2) {
            addCriterion("MUTEX_ROLE_ID between", value1, value2, "mutexRoleId");
            return (Criteria) this;
        }

        public Criteria andMutexRoleIdNotBetween(String value1, String value2) {
            addCriterion("MUTEX_ROLE_ID not between", value1, value2, "mutexRoleId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}