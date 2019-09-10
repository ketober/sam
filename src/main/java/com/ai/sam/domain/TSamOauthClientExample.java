package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamOauthClientExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamOauthClientExample() {
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

        public Criteria andClientIdIsNull() {
            addCriterion("CLIENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("CLIENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(String value) {
            addCriterion("CLIENT_ID =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(String value) {
            addCriterion("CLIENT_ID <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(String value) {
            addCriterion("CLIENT_ID >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(String value) {
            addCriterion("CLIENT_ID <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLike(String value) {
            addCriterion("CLIENT_ID like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotLike(String value) {
            addCriterion("CLIENT_ID not like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<String> values) {
            addCriterion("CLIENT_ID in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<String> values) {
            addCriterion("CLIENT_ID not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(String value1, String value2) {
            addCriterion("CLIENT_ID between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("CLIENT_ID not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientNameIsNull() {
            addCriterion("CLIENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClientNameIsNotNull() {
            addCriterion("CLIENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClientNameEqualTo(String value) {
            addCriterion("CLIENT_NAME =", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotEqualTo(String value) {
            addCriterion("CLIENT_NAME <>", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameGreaterThan(String value) {
            addCriterion("CLIENT_NAME >", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_NAME >=", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLessThan(String value) {
            addCriterion("CLIENT_NAME <", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_NAME <=", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLike(String value) {
            addCriterion("CLIENT_NAME like", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotLike(String value) {
            addCriterion("CLIENT_NAME not like", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameIn(List<String> values) {
            addCriterion("CLIENT_NAME in", values, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotIn(List<String> values) {
            addCriterion("CLIENT_NAME not in", values, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameBetween(String value1, String value2) {
            addCriterion("CLIENT_NAME between", value1, value2, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotBetween(String value1, String value2) {
            addCriterion("CLIENT_NAME not between", value1, value2, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientSecretIsNull() {
            addCriterion("CLIENT_SECRET is null");
            return (Criteria) this;
        }

        public Criteria andClientSecretIsNotNull() {
            addCriterion("CLIENT_SECRET is not null");
            return (Criteria) this;
        }

        public Criteria andClientSecretEqualTo(String value) {
            addCriterion("CLIENT_SECRET =", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotEqualTo(String value) {
            addCriterion("CLIENT_SECRET <>", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretGreaterThan(String value) {
            addCriterion("CLIENT_SECRET >", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_SECRET >=", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretLessThan(String value) {
            addCriterion("CLIENT_SECRET <", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_SECRET <=", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretLike(String value) {
            addCriterion("CLIENT_SECRET like", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotLike(String value) {
            addCriterion("CLIENT_SECRET not like", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretIn(List<String> values) {
            addCriterion("CLIENT_SECRET in", values, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotIn(List<String> values) {
            addCriterion("CLIENT_SECRET not in", values, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretBetween(String value1, String value2) {
            addCriterion("CLIENT_SECRET between", value1, value2, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotBetween(String value1, String value2) {
            addCriterion("CLIENT_SECRET not between", value1, value2, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientDescIsNull() {
            addCriterion("CLIENT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andClientDescIsNotNull() {
            addCriterion("CLIENT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andClientDescEqualTo(String value) {
            addCriterion("CLIENT_DESC =", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescNotEqualTo(String value) {
            addCriterion("CLIENT_DESC <>", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescGreaterThan(String value) {
            addCriterion("CLIENT_DESC >", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_DESC >=", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescLessThan(String value) {
            addCriterion("CLIENT_DESC <", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_DESC <=", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescLike(String value) {
            addCriterion("CLIENT_DESC like", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescNotLike(String value) {
            addCriterion("CLIENT_DESC not like", value, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescIn(List<String> values) {
            addCriterion("CLIENT_DESC in", values, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescNotIn(List<String> values) {
            addCriterion("CLIENT_DESC not in", values, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescBetween(String value1, String value2) {
            addCriterion("CLIENT_DESC between", value1, value2, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andClientDescNotBetween(String value1, String value2) {
            addCriterion("CLIENT_DESC not between", value1, value2, "clientDesc");
            return (Criteria) this;
        }

        public Criteria andRedirectUriIsNull() {
            addCriterion("REDIRECT_URI is null");
            return (Criteria) this;
        }

        public Criteria andRedirectUriIsNotNull() {
            addCriterion("REDIRECT_URI is not null");
            return (Criteria) this;
        }

        public Criteria andRedirectUriEqualTo(String value) {
            addCriterion("REDIRECT_URI =", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotEqualTo(String value) {
            addCriterion("REDIRECT_URI <>", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriGreaterThan(String value) {
            addCriterion("REDIRECT_URI >", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriGreaterThanOrEqualTo(String value) {
            addCriterion("REDIRECT_URI >=", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriLessThan(String value) {
            addCriterion("REDIRECT_URI <", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriLessThanOrEqualTo(String value) {
            addCriterion("REDIRECT_URI <=", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriLike(String value) {
            addCriterion("REDIRECT_URI like", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotLike(String value) {
            addCriterion("REDIRECT_URI not like", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriIn(List<String> values) {
            addCriterion("REDIRECT_URI in", values, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotIn(List<String> values) {
            addCriterion("REDIRECT_URI not in", values, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriBetween(String value1, String value2) {
            addCriterion("REDIRECT_URI between", value1, value2, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotBetween(String value1, String value2) {
            addCriterion("REDIRECT_URI not between", value1, value2, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityIsNull() {
            addCriterion("ACCESS_TOKEN_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityIsNotNull() {
            addCriterion("ACCESS_TOKEN_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityEqualTo(Integer value) {
            addCriterion("ACCESS_TOKEN_VALIDITY =", value, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityNotEqualTo(Integer value) {
            addCriterion("ACCESS_TOKEN_VALIDITY <>", value, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityGreaterThan(Integer value) {
            addCriterion("ACCESS_TOKEN_VALIDITY >", value, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACCESS_TOKEN_VALIDITY >=", value, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityLessThan(Integer value) {
            addCriterion("ACCESS_TOKEN_VALIDITY <", value, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityLessThanOrEqualTo(Integer value) {
            addCriterion("ACCESS_TOKEN_VALIDITY <=", value, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityIn(List<Integer> values) {
            addCriterion("ACCESS_TOKEN_VALIDITY in", values, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityNotIn(List<Integer> values) {
            addCriterion("ACCESS_TOKEN_VALIDITY not in", values, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityBetween(Integer value1, Integer value2) {
            addCriterion("ACCESS_TOKEN_VALIDITY between", value1, value2, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andAccessTokenValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("ACCESS_TOKEN_VALIDITY not between", value1, value2, "accessTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityIsNull() {
            addCriterion("REFRESH_TOKEN_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityIsNotNull() {
            addCriterion("REFRESH_TOKEN_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityEqualTo(Integer value) {
            addCriterion("REFRESH_TOKEN_VALIDITY =", value, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityNotEqualTo(Integer value) {
            addCriterion("REFRESH_TOKEN_VALIDITY <>", value, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityGreaterThan(Integer value) {
            addCriterion("REFRESH_TOKEN_VALIDITY >", value, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("REFRESH_TOKEN_VALIDITY >=", value, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityLessThan(Integer value) {
            addCriterion("REFRESH_TOKEN_VALIDITY <", value, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityLessThanOrEqualTo(Integer value) {
            addCriterion("REFRESH_TOKEN_VALIDITY <=", value, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityIn(List<Integer> values) {
            addCriterion("REFRESH_TOKEN_VALIDITY in", values, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityNotIn(List<Integer> values) {
            addCriterion("REFRESH_TOKEN_VALIDITY not in", values, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityBetween(Integer value1, Integer value2) {
            addCriterion("REFRESH_TOKEN_VALIDITY between", value1, value2, "refreshTokenValidity");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("REFRESH_TOKEN_VALIDITY not between", value1, value2, "refreshTokenValidity");
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

        public Criteria andGrantTypesIsNull() {
            addCriterion("GRANT_TYPES is null");
            return (Criteria) this;
        }

        public Criteria andGrantTypesIsNotNull() {
            addCriterion("GRANT_TYPES is not null");
            return (Criteria) this;
        }

        public Criteria andGrantTypesEqualTo(String value) {
            addCriterion("GRANT_TYPES =", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesNotEqualTo(String value) {
            addCriterion("GRANT_TYPES <>", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesGreaterThan(String value) {
            addCriterion("GRANT_TYPES >", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesGreaterThanOrEqualTo(String value) {
            addCriterion("GRANT_TYPES >=", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesLessThan(String value) {
            addCriterion("GRANT_TYPES <", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesLessThanOrEqualTo(String value) {
            addCriterion("GRANT_TYPES <=", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesLike(String value) {
            addCriterion("GRANT_TYPES like", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesNotLike(String value) {
            addCriterion("GRANT_TYPES not like", value, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesIn(List<String> values) {
            addCriterion("GRANT_TYPES in", values, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesNotIn(List<String> values) {
            addCriterion("GRANT_TYPES not in", values, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesBetween(String value1, String value2) {
            addCriterion("GRANT_TYPES between", value1, value2, "grantTypes");
            return (Criteria) this;
        }

        public Criteria andGrantTypesNotBetween(String value1, String value2) {
            addCriterion("GRANT_TYPES not between", value1, value2, "grantTypes");
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