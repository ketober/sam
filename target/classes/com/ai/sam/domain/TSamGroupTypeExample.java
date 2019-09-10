package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamGroupTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamGroupTypeExample() {
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

        public Criteria andGroupTypeIdIsNull() {
            addCriterion("GROUP_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdIsNotNull() {
            addCriterion("GROUP_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID =", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID <>", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdGreaterThan(String value) {
            addCriterion("GROUP_TYPE_ID >", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID >=", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLessThan(String value) {
            addCriterion("GROUP_TYPE_ID <", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLessThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID <=", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLike(String value) {
            addCriterion("GROUP_TYPE_ID like", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotLike(String value) {
            addCriterion("GROUP_TYPE_ID not like", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdIn(List<String> values) {
            addCriterion("GROUP_TYPE_ID in", values, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotIn(List<String> values) {
            addCriterion("GROUP_TYPE_ID not in", values, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_ID between", value1, value2, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_ID not between", value1, value2, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameIsNull() {
            addCriterion("GROUP_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameIsNotNull() {
            addCriterion("GROUP_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME =", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME <>", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameGreaterThan(String value) {
            addCriterion("GROUP_TYPE_NAME >", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME >=", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameLessThan(String value) {
            addCriterion("GROUP_TYPE_NAME <", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME <=", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameLike(String value) {
            addCriterion("GROUP_TYPE_NAME like", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotLike(String value) {
            addCriterion("GROUP_TYPE_NAME not like", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameIn(List<String> values) {
            addCriterion("GROUP_TYPE_NAME in", values, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotIn(List<String> values) {
            addCriterion("GROUP_TYPE_NAME not in", values, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_NAME between", value1, value2, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_NAME not between", value1, value2, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescIsNull() {
            addCriterion("GROUP_TYPE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescIsNotNull() {
            addCriterion("GROUP_TYPE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescEqualTo(String value) {
            addCriterion("GROUP_TYPE_DESC =", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescNotEqualTo(String value) {
            addCriterion("GROUP_TYPE_DESC <>", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescGreaterThan(String value) {
            addCriterion("GROUP_TYPE_DESC >", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_DESC >=", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescLessThan(String value) {
            addCriterion("GROUP_TYPE_DESC <", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescLessThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_DESC <=", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescLike(String value) {
            addCriterion("GROUP_TYPE_DESC like", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescNotLike(String value) {
            addCriterion("GROUP_TYPE_DESC not like", value, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescIn(List<String> values) {
            addCriterion("GROUP_TYPE_DESC in", values, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescNotIn(List<String> values) {
            addCriterion("GROUP_TYPE_DESC not in", values, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_DESC between", value1, value2, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andGroupTypeDescNotBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_DESC not between", value1, value2, "groupTypeDesc");
            return (Criteria) this;
        }

        public Criteria andSysNoIsNull() {
            addCriterion("SYS_NO is null");
            return (Criteria) this;
        }

        public Criteria andSysNoIsNotNull() {
            addCriterion("SYS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSysNoEqualTo(String value) {
            addCriterion("SYS_NO =", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoNotEqualTo(String value) {
            addCriterion("SYS_NO <>", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoGreaterThan(String value) {
            addCriterion("SYS_NO >", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_NO >=", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoLessThan(String value) {
            addCriterion("SYS_NO <", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoLessThanOrEqualTo(String value) {
            addCriterion("SYS_NO <=", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoLike(String value) {
            addCriterion("SYS_NO like", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoNotLike(String value) {
            addCriterion("SYS_NO not like", value, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoIn(List<String> values) {
            addCriterion("SYS_NO in", values, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoNotIn(List<String> values) {
            addCriterion("SYS_NO not in", values, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoBetween(String value1, String value2) {
            addCriterion("SYS_NO between", value1, value2, "sysNo");
            return (Criteria) this;
        }

        public Criteria andSysNoNotBetween(String value1, String value2) {
            addCriterion("SYS_NO not between", value1, value2, "sysNo");
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