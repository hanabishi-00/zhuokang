package hdy.faultDiagnose.entity;

import java.util.ArrayList;
import java.util.List;

public class diagSuggExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public diagSuggExample() {
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

        public Criteria andSuggIdIsNull() {
            addCriterion("sugg_id is null");
            return (Criteria) this;
        }

        public Criteria andSuggIdIsNotNull() {
            addCriterion("sugg_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuggIdEqualTo(Integer value) {
            addCriterion("sugg_id =", value, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdNotEqualTo(Integer value) {
            addCriterion("sugg_id <>", value, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdGreaterThan(Integer value) {
            addCriterion("sugg_id >", value, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sugg_id >=", value, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdLessThan(Integer value) {
            addCriterion("sugg_id <", value, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdLessThanOrEqualTo(Integer value) {
            addCriterion("sugg_id <=", value, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdIn(List<Integer> values) {
            addCriterion("sugg_id in", values, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdNotIn(List<Integer> values) {
            addCriterion("sugg_id not in", values, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdBetween(Integer value1, Integer value2) {
            addCriterion("sugg_id between", value1, value2, "suggId");
            return (Criteria) this;
        }

        public Criteria andSuggIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sugg_id not between", value1, value2, "suggId");
            return (Criteria) this;
        }

        public Criteria andRepairComIsNull() {
            addCriterion("repair_com is null");
            return (Criteria) this;
        }

        public Criteria andRepairComIsNotNull() {
            addCriterion("repair_com is not null");
            return (Criteria) this;
        }

        public Criteria andRepairComEqualTo(String value) {
            addCriterion("repair_com =", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComNotEqualTo(String value) {
            addCriterion("repair_com <>", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComGreaterThan(String value) {
            addCriterion("repair_com >", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComGreaterThanOrEqualTo(String value) {
            addCriterion("repair_com >=", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComLessThan(String value) {
            addCriterion("repair_com <", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComLessThanOrEqualTo(String value) {
            addCriterion("repair_com <=", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComLike(String value) {
            addCriterion("repair_com like", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComNotLike(String value) {
            addCriterion("repair_com not like", value, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComIn(List<String> values) {
            addCriterion("repair_com in", values, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComNotIn(List<String> values) {
            addCriterion("repair_com not in", values, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComBetween(String value1, String value2) {
            addCriterion("repair_com between", value1, value2, "repairCom");
            return (Criteria) this;
        }

        public Criteria andRepairComNotBetween(String value1, String value2) {
            addCriterion("repair_com not between", value1, value2, "repairCom");
            return (Criteria) this;
        }

        public Criteria andToolComIsNull() {
            addCriterion("tool_com is null");
            return (Criteria) this;
        }

        public Criteria andToolComIsNotNull() {
            addCriterion("tool_com is not null");
            return (Criteria) this;
        }

        public Criteria andToolComEqualTo(String value) {
            addCriterion("tool_com =", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComNotEqualTo(String value) {
            addCriterion("tool_com <>", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComGreaterThan(String value) {
            addCriterion("tool_com >", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComGreaterThanOrEqualTo(String value) {
            addCriterion("tool_com >=", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComLessThan(String value) {
            addCriterion("tool_com <", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComLessThanOrEqualTo(String value) {
            addCriterion("tool_com <=", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComLike(String value) {
            addCriterion("tool_com like", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComNotLike(String value) {
            addCriterion("tool_com not like", value, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComIn(List<String> values) {
            addCriterion("tool_com in", values, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComNotIn(List<String> values) {
            addCriterion("tool_com not in", values, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComBetween(String value1, String value2) {
            addCriterion("tool_com between", value1, value2, "toolCom");
            return (Criteria) this;
        }

        public Criteria andToolComNotBetween(String value1, String value2) {
            addCriterion("tool_com not between", value1, value2, "toolCom");
            return (Criteria) this;
        }

        public Criteria andRunComIsNull() {
            addCriterion("run_com is null");
            return (Criteria) this;
        }

        public Criteria andRunComIsNotNull() {
            addCriterion("run_com is not null");
            return (Criteria) this;
        }

        public Criteria andRunComEqualTo(String value) {
            addCriterion("run_com =", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComNotEqualTo(String value) {
            addCriterion("run_com <>", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComGreaterThan(String value) {
            addCriterion("run_com >", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComGreaterThanOrEqualTo(String value) {
            addCriterion("run_com >=", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComLessThan(String value) {
            addCriterion("run_com <", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComLessThanOrEqualTo(String value) {
            addCriterion("run_com <=", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComLike(String value) {
            addCriterion("run_com like", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComNotLike(String value) {
            addCriterion("run_com not like", value, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComIn(List<String> values) {
            addCriterion("run_com in", values, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComNotIn(List<String> values) {
            addCriterion("run_com not in", values, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComBetween(String value1, String value2) {
            addCriterion("run_com between", value1, value2, "runCom");
            return (Criteria) this;
        }

        public Criteria andRunComNotBetween(String value1, String value2) {
            addCriterion("run_com not between", value1, value2, "runCom");
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