package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamTenantauthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamTenantauthExample() {
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

        public Criteria andDblCenIdIsNull() {
            addCriterion("DBL_CEN_ID is null");
            return (Criteria) this;
        }

        public Criteria andDblCenIdIsNotNull() {
            addCriterion("DBL_CEN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDblCenIdEqualTo(String value) {
            addCriterion("DBL_CEN_ID =", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdNotEqualTo(String value) {
            addCriterion("DBL_CEN_ID <>", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdGreaterThan(String value) {
            addCriterion("DBL_CEN_ID >", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdGreaterThanOrEqualTo(String value) {
            addCriterion("DBL_CEN_ID >=", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdLessThan(String value) {
            addCriterion("DBL_CEN_ID <", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdLessThanOrEqualTo(String value) {
            addCriterion("DBL_CEN_ID <=", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdLike(String value) {
            addCriterion("DBL_CEN_ID like", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdNotLike(String value) {
            addCriterion("DBL_CEN_ID not like", value, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdIn(List<String> values) {
            addCriterion("DBL_CEN_ID in", values, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdNotIn(List<String> values) {
            addCriterion("DBL_CEN_ID not in", values, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdBetween(String value1, String value2) {
            addCriterion("DBL_CEN_ID between", value1, value2, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andDblCenIdNotBetween(String value1, String value2) {
            addCriterion("DBL_CEN_ID not between", value1, value2, "dblCenId");
            return (Criteria) this;
        }

        public Criteria andStaffidIsNull() {
            addCriterion("STAFFID is null");
            return (Criteria) this;
        }

        public Criteria andStaffidIsNotNull() {
            addCriterion("STAFFID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffidEqualTo(String value) {
            addCriterion("STAFFID =", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotEqualTo(String value) {
            addCriterion("STAFFID <>", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidGreaterThan(String value) {
            addCriterion("STAFFID >", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidGreaterThanOrEqualTo(String value) {
            addCriterion("STAFFID >=", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidLessThan(String value) {
            addCriterion("STAFFID <", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidLessThanOrEqualTo(String value) {
            addCriterion("STAFFID <=", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidLike(String value) {
            addCriterion("STAFFID like", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotLike(String value) {
            addCriterion("STAFFID not like", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidIn(List<String> values) {
            addCriterion("STAFFID in", values, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotIn(List<String> values) {
            addCriterion("STAFFID not in", values, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidBetween(String value1, String value2) {
            addCriterion("STAFFID between", value1, value2, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotBetween(String value1, String value2) {
            addCriterion("STAFFID not between", value1, value2, "staffid");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
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