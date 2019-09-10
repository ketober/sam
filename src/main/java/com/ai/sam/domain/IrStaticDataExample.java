package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class IrStaticDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IrStaticDataExample() {
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

        public Criteria andCodeTypeIsNull() {
            addCriterion("CODE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNotNull() {
            addCriterion("CODE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeEqualTo(String value) {
            addCriterion("CODE_TYPE =", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotEqualTo(String value) {
            addCriterion("CODE_TYPE <>", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThan(String value) {
            addCriterion("CODE_TYPE >", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_TYPE >=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThan(String value) {
            addCriterion("CODE_TYPE <", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThanOrEqualTo(String value) {
            addCriterion("CODE_TYPE <=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLike(String value) {
            addCriterion("CODE_TYPE like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotLike(String value) {
            addCriterion("CODE_TYPE not like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIn(List<String> values) {
            addCriterion("CODE_TYPE in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotIn(List<String> values) {
            addCriterion("CODE_TYPE not in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeBetween(String value1, String value2) {
            addCriterion("CODE_TYPE between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotBetween(String value1, String value2) {
            addCriterion("CODE_TYPE not between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNull() {
            addCriterion("CODE_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNotNull() {
            addCriterion("CODE_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeValueEqualTo(String value) {
            addCriterion("CODE_VALUE =", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotEqualTo(String value) {
            addCriterion("CODE_VALUE <>", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThan(String value) {
            addCriterion("CODE_VALUE >", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_VALUE >=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThan(String value) {
            addCriterion("CODE_VALUE <", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThanOrEqualTo(String value) {
            addCriterion("CODE_VALUE <=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLike(String value) {
            addCriterion("CODE_VALUE like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotLike(String value) {
            addCriterion("CODE_VALUE not like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueIn(List<String> values) {
            addCriterion("CODE_VALUE in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotIn(List<String> values) {
            addCriterion("CODE_VALUE not in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueBetween(String value1, String value2) {
            addCriterion("CODE_VALUE between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotBetween(String value1, String value2) {
            addCriterion("CODE_VALUE not between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNull() {
            addCriterion("CODE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNotNull() {
            addCriterion("CODE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCodeNameEqualTo(String value) {
            addCriterion("CODE_NAME =", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotEqualTo(String value) {
            addCriterion("CODE_NAME <>", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThan(String value) {
            addCriterion("CODE_NAME >", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_NAME >=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThan(String value) {
            addCriterion("CODE_NAME <", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThanOrEqualTo(String value) {
            addCriterion("CODE_NAME <=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLike(String value) {
            addCriterion("CODE_NAME like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotLike(String value) {
            addCriterion("CODE_NAME not like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameIn(List<String> values) {
            addCriterion("CODE_NAME in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotIn(List<String> values) {
            addCriterion("CODE_NAME not in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameBetween(String value1, String value2) {
            addCriterion("CODE_NAME between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotBetween(String value1, String value2) {
            addCriterion("CODE_NAME not between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeDescIsNull() {
            addCriterion("CODE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCodeDescIsNotNull() {
            addCriterion("CODE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCodeDescEqualTo(String value) {
            addCriterion("CODE_DESC =", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotEqualTo(String value) {
            addCriterion("CODE_DESC <>", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescGreaterThan(String value) {
            addCriterion("CODE_DESC >", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_DESC >=", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescLessThan(String value) {
            addCriterion("CODE_DESC <", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescLessThanOrEqualTo(String value) {
            addCriterion("CODE_DESC <=", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescLike(String value) {
            addCriterion("CODE_DESC like", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotLike(String value) {
            addCriterion("CODE_DESC not like", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescIn(List<String> values) {
            addCriterion("CODE_DESC in", values, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotIn(List<String> values) {
            addCriterion("CODE_DESC not in", values, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescBetween(String value1, String value2) {
            addCriterion("CODE_DESC between", value1, value2, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotBetween(String value1, String value2) {
            addCriterion("CODE_DESC not between", value1, value2, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNull() {
            addCriterion("SORT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNotNull() {
            addCriterion("SORT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSortIdEqualTo(Short value) {
            addCriterion("SORT_ID =", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotEqualTo(Short value) {
            addCriterion("SORT_ID <>", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThan(Short value) {
            addCriterion("SORT_ID >", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThanOrEqualTo(Short value) {
            addCriterion("SORT_ID >=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThan(Short value) {
            addCriterion("SORT_ID <", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThanOrEqualTo(Short value) {
            addCriterion("SORT_ID <=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdIn(List<Short> values) {
            addCriterion("SORT_ID in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotIn(List<Short> values) {
            addCriterion("SORT_ID not in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdBetween(Short value1, Short value2) {
            addCriterion("SORT_ID between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotBetween(Short value1, Short value2) {
            addCriterion("SORT_ID not between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
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