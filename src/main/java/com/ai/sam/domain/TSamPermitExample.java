package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamPermitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamPermitExample() {
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

        public Criteria andPermitIdIsNull() {
            addCriterion("PERMIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPermitIdIsNotNull() {
            addCriterion("PERMIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPermitIdEqualTo(String value) {
            addCriterion("PERMIT_ID =", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdNotEqualTo(String value) {
            addCriterion("PERMIT_ID <>", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdGreaterThan(String value) {
            addCriterion("PERMIT_ID >", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdGreaterThanOrEqualTo(String value) {
            addCriterion("PERMIT_ID >=", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdLessThan(String value) {
            addCriterion("PERMIT_ID <", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdLessThanOrEqualTo(String value) {
            addCriterion("PERMIT_ID <=", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdLike(String value) {
            addCriterion("PERMIT_ID like", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdNotLike(String value) {
            addCriterion("PERMIT_ID not like", value, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdIn(List<String> values) {
            addCriterion("PERMIT_ID in", values, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdNotIn(List<String> values) {
            addCriterion("PERMIT_ID not in", values, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdBetween(String value1, String value2) {
            addCriterion("PERMIT_ID between", value1, value2, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitIdNotBetween(String value1, String value2) {
            addCriterion("PERMIT_ID not between", value1, value2, "permitId");
            return (Criteria) this;
        }

        public Criteria andPermitTypeIsNull() {
            addCriterion("PERMIT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPermitTypeIsNotNull() {
            addCriterion("PERMIT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPermitTypeEqualTo(String value) {
            addCriterion("PERMIT_TYPE =", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeNotEqualTo(String value) {
            addCriterion("PERMIT_TYPE <>", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeGreaterThan(String value) {
            addCriterion("PERMIT_TYPE >", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PERMIT_TYPE >=", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeLessThan(String value) {
            addCriterion("PERMIT_TYPE <", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeLessThanOrEqualTo(String value) {
            addCriterion("PERMIT_TYPE <=", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeLike(String value) {
            addCriterion("PERMIT_TYPE like", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeNotLike(String value) {
            addCriterion("PERMIT_TYPE not like", value, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeIn(List<String> values) {
            addCriterion("PERMIT_TYPE in", values, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeNotIn(List<String> values) {
            addCriterion("PERMIT_TYPE not in", values, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeBetween(String value1, String value2) {
            addCriterion("PERMIT_TYPE between", value1, value2, "permitType");
            return (Criteria) this;
        }

        public Criteria andPermitTypeNotBetween(String value1, String value2) {
            addCriterion("PERMIT_TYPE not between", value1, value2, "permitType");
            return (Criteria) this;
        }

        public Criteria andEntityIdIsNull() {
            addCriterion("ENTITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andEntityIdIsNotNull() {
            addCriterion("ENTITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEntityIdEqualTo(String value) {
            addCriterion("ENTITY_ID =", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotEqualTo(String value) {
            addCriterion("ENTITY_ID <>", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThan(String value) {
            addCriterion("ENTITY_ID >", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThanOrEqualTo(String value) {
            addCriterion("ENTITY_ID >=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThan(String value) {
            addCriterion("ENTITY_ID <", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThanOrEqualTo(String value) {
            addCriterion("ENTITY_ID <=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLike(String value) {
            addCriterion("ENTITY_ID like", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotLike(String value) {
            addCriterion("ENTITY_ID not like", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdIn(List<String> values) {
            addCriterion("ENTITY_ID in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotIn(List<String> values) {
            addCriterion("ENTITY_ID not in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdBetween(String value1, String value2) {
            addCriterion("ENTITY_ID between", value1, value2, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotBetween(String value1, String value2) {
            addCriterion("ENTITY_ID not between", value1, value2, "entityId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdIsNull() {
            addCriterion("AUTH_OBJ_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdIsNotNull() {
            addCriterion("AUTH_OBJ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdEqualTo(String value) {
            addCriterion("AUTH_OBJ_ID =", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdNotEqualTo(String value) {
            addCriterion("AUTH_OBJ_ID <>", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdGreaterThan(String value) {
            addCriterion("AUTH_OBJ_ID >", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_OBJ_ID >=", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdLessThan(String value) {
            addCriterion("AUTH_OBJ_ID <", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdLessThanOrEqualTo(String value) {
            addCriterion("AUTH_OBJ_ID <=", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdLike(String value) {
            addCriterion("AUTH_OBJ_ID like", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdNotLike(String value) {
            addCriterion("AUTH_OBJ_ID not like", value, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdIn(List<String> values) {
            addCriterion("AUTH_OBJ_ID in", values, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdNotIn(List<String> values) {
            addCriterion("AUTH_OBJ_ID not in", values, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdBetween(String value1, String value2) {
            addCriterion("AUTH_OBJ_ID between", value1, value2, "authObjId");
            return (Criteria) this;
        }

        public Criteria andAuthObjIdNotBetween(String value1, String value2) {
            addCriterion("AUTH_OBJ_ID not between", value1, value2, "authObjId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("MODULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("MODULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(String value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(String value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(String value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(String value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(String value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLike(String value) {
            addCriterion("MODULE_ID like", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotLike(String value) {
            addCriterion("MODULE_ID not like", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<String> values) {
            addCriterion("MODULE_ID in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<String> values) {
            addCriterion("MODULE_ID not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(String value1, String value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(String value1, String value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andApplicationidIsNull() {
            addCriterion("APPLICATIONID is null");
            return (Criteria) this;
        }

        public Criteria andApplicationidIsNotNull() {
            addCriterion("APPLICATIONID is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationidEqualTo(String value) {
            addCriterion("APPLICATIONID =", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidNotEqualTo(String value) {
            addCriterion("APPLICATIONID <>", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidGreaterThan(String value) {
            addCriterion("APPLICATIONID >", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATIONID >=", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidLessThan(String value) {
            addCriterion("APPLICATIONID <", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidLessThanOrEqualTo(String value) {
            addCriterion("APPLICATIONID <=", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidLike(String value) {
            addCriterion("APPLICATIONID like", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidNotLike(String value) {
            addCriterion("APPLICATIONID not like", value, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidIn(List<String> values) {
            addCriterion("APPLICATIONID in", values, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidNotIn(List<String> values) {
            addCriterion("APPLICATIONID not in", values, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidBetween(String value1, String value2) {
            addCriterion("APPLICATIONID between", value1, value2, "applicationid");
            return (Criteria) this;
        }

        public Criteria andApplicationidNotBetween(String value1, String value2) {
            addCriterion("APPLICATIONID not between", value1, value2, "applicationid");
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