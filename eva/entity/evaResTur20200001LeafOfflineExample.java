package hdy.eva.entity;

import java.util.ArrayList;
import java.util.List;

public class evaResTur20200001LeafOfflineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public evaResTur20200001LeafOfflineExample() {
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

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Integer value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Integer value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Integer value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Integer value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Integer value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Integer> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Integer> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Integer value1, Integer value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(Byte value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(Byte value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(Byte value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(Byte value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(Byte value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(Byte value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<Byte> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<Byte> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(Byte value1, Byte value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(Byte value1, Byte value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andV0IsNull() {
            addCriterion("v0 is null");
            return (Criteria) this;
        }

        public Criteria andV0IsNotNull() {
            addCriterion("v0 is not null");
            return (Criteria) this;
        }

        public Criteria andV0EqualTo(Float value) {
            addCriterion("v0 =", value, "v0");
            return (Criteria) this;
        }

        public Criteria andV0NotEqualTo(Float value) {
            addCriterion("v0 <>", value, "v0");
            return (Criteria) this;
        }

        public Criteria andV0GreaterThan(Float value) {
            addCriterion("v0 >", value, "v0");
            return (Criteria) this;
        }

        public Criteria andV0GreaterThanOrEqualTo(Float value) {
            addCriterion("v0 >=", value, "v0");
            return (Criteria) this;
        }

        public Criteria andV0LessThan(Float value) {
            addCriterion("v0 <", value, "v0");
            return (Criteria) this;
        }

        public Criteria andV0LessThanOrEqualTo(Float value) {
            addCriterion("v0 <=", value, "v0");
            return (Criteria) this;
        }

        public Criteria andV0In(List<Float> values) {
            addCriterion("v0 in", values, "v0");
            return (Criteria) this;
        }

        public Criteria andV0NotIn(List<Float> values) {
            addCriterion("v0 not in", values, "v0");
            return (Criteria) this;
        }

        public Criteria andV0Between(Float value1, Float value2) {
            addCriterion("v0 between", value1, value2, "v0");
            return (Criteria) this;
        }

        public Criteria andV0NotBetween(Float value1, Float value2) {
            addCriterion("v0 not between", value1, value2, "v0");
            return (Criteria) this;
        }

        public Criteria andV1IsNull() {
            addCriterion("v1 is null");
            return (Criteria) this;
        }

        public Criteria andV1IsNotNull() {
            addCriterion("v1 is not null");
            return (Criteria) this;
        }

        public Criteria andV1EqualTo(Float value) {
            addCriterion("v1 =", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotEqualTo(Float value) {
            addCriterion("v1 <>", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1GreaterThan(Float value) {
            addCriterion("v1 >", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1GreaterThanOrEqualTo(Float value) {
            addCriterion("v1 >=", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1LessThan(Float value) {
            addCriterion("v1 <", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1LessThanOrEqualTo(Float value) {
            addCriterion("v1 <=", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1In(List<Float> values) {
            addCriterion("v1 in", values, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotIn(List<Float> values) {
            addCriterion("v1 not in", values, "v1");
            return (Criteria) this;
        }

        public Criteria andV1Between(Float value1, Float value2) {
            addCriterion("v1 between", value1, value2, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotBetween(Float value1, Float value2) {
            addCriterion("v1 not between", value1, value2, "v1");
            return (Criteria) this;
        }

        public Criteria andV2IsNull() {
            addCriterion("v2 is null");
            return (Criteria) this;
        }

        public Criteria andV2IsNotNull() {
            addCriterion("v2 is not null");
            return (Criteria) this;
        }

        public Criteria andV2EqualTo(Float value) {
            addCriterion("v2 =", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotEqualTo(Float value) {
            addCriterion("v2 <>", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2GreaterThan(Float value) {
            addCriterion("v2 >", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2GreaterThanOrEqualTo(Float value) {
            addCriterion("v2 >=", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2LessThan(Float value) {
            addCriterion("v2 <", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2LessThanOrEqualTo(Float value) {
            addCriterion("v2 <=", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2In(List<Float> values) {
            addCriterion("v2 in", values, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotIn(List<Float> values) {
            addCriterion("v2 not in", values, "v2");
            return (Criteria) this;
        }

        public Criteria andV2Between(Float value1, Float value2) {
            addCriterion("v2 between", value1, value2, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotBetween(Float value1, Float value2) {
            addCriterion("v2 not between", value1, value2, "v2");
            return (Criteria) this;
        }

        public Criteria andV3IsNull() {
            addCriterion("v3 is null");
            return (Criteria) this;
        }

        public Criteria andV3IsNotNull() {
            addCriterion("v3 is not null");
            return (Criteria) this;
        }

        public Criteria andV3EqualTo(Float value) {
            addCriterion("v3 =", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotEqualTo(Float value) {
            addCriterion("v3 <>", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3GreaterThan(Float value) {
            addCriterion("v3 >", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3GreaterThanOrEqualTo(Float value) {
            addCriterion("v3 >=", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3LessThan(Float value) {
            addCriterion("v3 <", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3LessThanOrEqualTo(Float value) {
            addCriterion("v3 <=", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3In(List<Float> values) {
            addCriterion("v3 in", values, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotIn(List<Float> values) {
            addCriterion("v3 not in", values, "v3");
            return (Criteria) this;
        }

        public Criteria andV3Between(Float value1, Float value2) {
            addCriterion("v3 between", value1, value2, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotBetween(Float value1, Float value2) {
            addCriterion("v3 not between", value1, value2, "v3");
            return (Criteria) this;
        }

        public Criteria andV4IsNull() {
            addCriterion("v4 is null");
            return (Criteria) this;
        }

        public Criteria andV4IsNotNull() {
            addCriterion("v4 is not null");
            return (Criteria) this;
        }

        public Criteria andV4EqualTo(Float value) {
            addCriterion("v4 =", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotEqualTo(Float value) {
            addCriterion("v4 <>", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4GreaterThan(Float value) {
            addCriterion("v4 >", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4GreaterThanOrEqualTo(Float value) {
            addCriterion("v4 >=", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4LessThan(Float value) {
            addCriterion("v4 <", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4LessThanOrEqualTo(Float value) {
            addCriterion("v4 <=", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4In(List<Float> values) {
            addCriterion("v4 in", values, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotIn(List<Float> values) {
            addCriterion("v4 not in", values, "v4");
            return (Criteria) this;
        }

        public Criteria andV4Between(Float value1, Float value2) {
            addCriterion("v4 between", value1, value2, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotBetween(Float value1, Float value2) {
            addCriterion("v4 not between", value1, value2, "v4");
            return (Criteria) this;
        }

        public Criteria andV5IsNull() {
            addCriterion("v5 is null");
            return (Criteria) this;
        }

        public Criteria andV5IsNotNull() {
            addCriterion("v5 is not null");
            return (Criteria) this;
        }

        public Criteria andV5EqualTo(Float value) {
            addCriterion("v5 =", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotEqualTo(Float value) {
            addCriterion("v5 <>", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5GreaterThan(Float value) {
            addCriterion("v5 >", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5GreaterThanOrEqualTo(Float value) {
            addCriterion("v5 >=", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5LessThan(Float value) {
            addCriterion("v5 <", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5LessThanOrEqualTo(Float value) {
            addCriterion("v5 <=", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5In(List<Float> values) {
            addCriterion("v5 in", values, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotIn(List<Float> values) {
            addCriterion("v5 not in", values, "v5");
            return (Criteria) this;
        }

        public Criteria andV5Between(Float value1, Float value2) {
            addCriterion("v5 between", value1, value2, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotBetween(Float value1, Float value2) {
            addCriterion("v5 not between", value1, value2, "v5");
            return (Criteria) this;
        }

        public Criteria andV6IsNull() {
            addCriterion("v6 is null");
            return (Criteria) this;
        }

        public Criteria andV6IsNotNull() {
            addCriterion("v6 is not null");
            return (Criteria) this;
        }

        public Criteria andV6EqualTo(Float value) {
            addCriterion("v6 =", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotEqualTo(Float value) {
            addCriterion("v6 <>", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6GreaterThan(Float value) {
            addCriterion("v6 >", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6GreaterThanOrEqualTo(Float value) {
            addCriterion("v6 >=", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6LessThan(Float value) {
            addCriterion("v6 <", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6LessThanOrEqualTo(Float value) {
            addCriterion("v6 <=", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6In(List<Float> values) {
            addCriterion("v6 in", values, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotIn(List<Float> values) {
            addCriterion("v6 not in", values, "v6");
            return (Criteria) this;
        }

        public Criteria andV6Between(Float value1, Float value2) {
            addCriterion("v6 between", value1, value2, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotBetween(Float value1, Float value2) {
            addCriterion("v6 not between", value1, value2, "v6");
            return (Criteria) this;
        }

        public Criteria andV7IsNull() {
            addCriterion("v7 is null");
            return (Criteria) this;
        }

        public Criteria andV7IsNotNull() {
            addCriterion("v7 is not null");
            return (Criteria) this;
        }

        public Criteria andV7EqualTo(Float value) {
            addCriterion("v7 =", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotEqualTo(Float value) {
            addCriterion("v7 <>", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7GreaterThan(Float value) {
            addCriterion("v7 >", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7GreaterThanOrEqualTo(Float value) {
            addCriterion("v7 >=", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7LessThan(Float value) {
            addCriterion("v7 <", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7LessThanOrEqualTo(Float value) {
            addCriterion("v7 <=", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7In(List<Float> values) {
            addCriterion("v7 in", values, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotIn(List<Float> values) {
            addCriterion("v7 not in", values, "v7");
            return (Criteria) this;
        }

        public Criteria andV7Between(Float value1, Float value2) {
            addCriterion("v7 between", value1, value2, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotBetween(Float value1, Float value2) {
            addCriterion("v7 not between", value1, value2, "v7");
            return (Criteria) this;
        }

        public Criteria andV8IsNull() {
            addCriterion("v8 is null");
            return (Criteria) this;
        }

        public Criteria andV8IsNotNull() {
            addCriterion("v8 is not null");
            return (Criteria) this;
        }

        public Criteria andV8EqualTo(Float value) {
            addCriterion("v8 =", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotEqualTo(Float value) {
            addCriterion("v8 <>", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8GreaterThan(Float value) {
            addCriterion("v8 >", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8GreaterThanOrEqualTo(Float value) {
            addCriterion("v8 >=", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8LessThan(Float value) {
            addCriterion("v8 <", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8LessThanOrEqualTo(Float value) {
            addCriterion("v8 <=", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8In(List<Float> values) {
            addCriterion("v8 in", values, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotIn(List<Float> values) {
            addCriterion("v8 not in", values, "v8");
            return (Criteria) this;
        }

        public Criteria andV8Between(Float value1, Float value2) {
            addCriterion("v8 between", value1, value2, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotBetween(Float value1, Float value2) {
            addCriterion("v8 not between", value1, value2, "v8");
            return (Criteria) this;
        }

        public Criteria andV9IsNull() {
            addCriterion("v9 is null");
            return (Criteria) this;
        }

        public Criteria andV9IsNotNull() {
            addCriterion("v9 is not null");
            return (Criteria) this;
        }

        public Criteria andV9EqualTo(Float value) {
            addCriterion("v9 =", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotEqualTo(Float value) {
            addCriterion("v9 <>", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9GreaterThan(Float value) {
            addCriterion("v9 >", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9GreaterThanOrEqualTo(Float value) {
            addCriterion("v9 >=", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9LessThan(Float value) {
            addCriterion("v9 <", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9LessThanOrEqualTo(Float value) {
            addCriterion("v9 <=", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9In(List<Float> values) {
            addCriterion("v9 in", values, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotIn(List<Float> values) {
            addCriterion("v9 not in", values, "v9");
            return (Criteria) this;
        }

        public Criteria andV9Between(Float value1, Float value2) {
            addCriterion("v9 between", value1, value2, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotBetween(Float value1, Float value2) {
            addCriterion("v9 not between", value1, value2, "v9");
            return (Criteria) this;
        }

        public Criteria andV10IsNull() {
            addCriterion("v10 is null");
            return (Criteria) this;
        }

        public Criteria andV10IsNotNull() {
            addCriterion("v10 is not null");
            return (Criteria) this;
        }

        public Criteria andV10EqualTo(Float value) {
            addCriterion("v10 =", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotEqualTo(Float value) {
            addCriterion("v10 <>", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10GreaterThan(Float value) {
            addCriterion("v10 >", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10GreaterThanOrEqualTo(Float value) {
            addCriterion("v10 >=", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10LessThan(Float value) {
            addCriterion("v10 <", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10LessThanOrEqualTo(Float value) {
            addCriterion("v10 <=", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10In(List<Float> values) {
            addCriterion("v10 in", values, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotIn(List<Float> values) {
            addCriterion("v10 not in", values, "v10");
            return (Criteria) this;
        }

        public Criteria andV10Between(Float value1, Float value2) {
            addCriterion("v10 between", value1, value2, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotBetween(Float value1, Float value2) {
            addCriterion("v10 not between", value1, value2, "v10");
            return (Criteria) this;
        }

        public Criteria andV11IsNull() {
            addCriterion("v11 is null");
            return (Criteria) this;
        }

        public Criteria andV11IsNotNull() {
            addCriterion("v11 is not null");
            return (Criteria) this;
        }

        public Criteria andV11EqualTo(Float value) {
            addCriterion("v11 =", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotEqualTo(Float value) {
            addCriterion("v11 <>", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11GreaterThan(Float value) {
            addCriterion("v11 >", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11GreaterThanOrEqualTo(Float value) {
            addCriterion("v11 >=", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11LessThan(Float value) {
            addCriterion("v11 <", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11LessThanOrEqualTo(Float value) {
            addCriterion("v11 <=", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11In(List<Float> values) {
            addCriterion("v11 in", values, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotIn(List<Float> values) {
            addCriterion("v11 not in", values, "v11");
            return (Criteria) this;
        }

        public Criteria andV11Between(Float value1, Float value2) {
            addCriterion("v11 between", value1, value2, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotBetween(Float value1, Float value2) {
            addCriterion("v11 not between", value1, value2, "v11");
            return (Criteria) this;
        }

        public Criteria andV12IsNull() {
            addCriterion("v12 is null");
            return (Criteria) this;
        }

        public Criteria andV12IsNotNull() {
            addCriterion("v12 is not null");
            return (Criteria) this;
        }

        public Criteria andV12EqualTo(Float value) {
            addCriterion("v12 =", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12NotEqualTo(Float value) {
            addCriterion("v12 <>", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12GreaterThan(Float value) {
            addCriterion("v12 >", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12GreaterThanOrEqualTo(Float value) {
            addCriterion("v12 >=", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12LessThan(Float value) {
            addCriterion("v12 <", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12LessThanOrEqualTo(Float value) {
            addCriterion("v12 <=", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12In(List<Float> values) {
            addCriterion("v12 in", values, "v12");
            return (Criteria) this;
        }

        public Criteria andV12NotIn(List<Float> values) {
            addCriterion("v12 not in", values, "v12");
            return (Criteria) this;
        }

        public Criteria andV12Between(Float value1, Float value2) {
            addCriterion("v12 between", value1, value2, "v12");
            return (Criteria) this;
        }

        public Criteria andV12NotBetween(Float value1, Float value2) {
            addCriterion("v12 not between", value1, value2, "v12");
            return (Criteria) this;
        }

        public Criteria andV13IsNull() {
            addCriterion("v13 is null");
            return (Criteria) this;
        }

        public Criteria andV13IsNotNull() {
            addCriterion("v13 is not null");
            return (Criteria) this;
        }

        public Criteria andV13EqualTo(Float value) {
            addCriterion("v13 =", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13NotEqualTo(Float value) {
            addCriterion("v13 <>", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13GreaterThan(Float value) {
            addCriterion("v13 >", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13GreaterThanOrEqualTo(Float value) {
            addCriterion("v13 >=", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13LessThan(Float value) {
            addCriterion("v13 <", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13LessThanOrEqualTo(Float value) {
            addCriterion("v13 <=", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13In(List<Float> values) {
            addCriterion("v13 in", values, "v13");
            return (Criteria) this;
        }

        public Criteria andV13NotIn(List<Float> values) {
            addCriterion("v13 not in", values, "v13");
            return (Criteria) this;
        }

        public Criteria andV13Between(Float value1, Float value2) {
            addCriterion("v13 between", value1, value2, "v13");
            return (Criteria) this;
        }

        public Criteria andV13NotBetween(Float value1, Float value2) {
            addCriterion("v13 not between", value1, value2, "v13");
            return (Criteria) this;
        }

        public Criteria andV14IsNull() {
            addCriterion("v14 is null");
            return (Criteria) this;
        }

        public Criteria andV14IsNotNull() {
            addCriterion("v14 is not null");
            return (Criteria) this;
        }

        public Criteria andV14EqualTo(Float value) {
            addCriterion("v14 =", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14NotEqualTo(Float value) {
            addCriterion("v14 <>", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14GreaterThan(Float value) {
            addCriterion("v14 >", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14GreaterThanOrEqualTo(Float value) {
            addCriterion("v14 >=", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14LessThan(Float value) {
            addCriterion("v14 <", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14LessThanOrEqualTo(Float value) {
            addCriterion("v14 <=", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14In(List<Float> values) {
            addCriterion("v14 in", values, "v14");
            return (Criteria) this;
        }

        public Criteria andV14NotIn(List<Float> values) {
            addCriterion("v14 not in", values, "v14");
            return (Criteria) this;
        }

        public Criteria andV14Between(Float value1, Float value2) {
            addCriterion("v14 between", value1, value2, "v14");
            return (Criteria) this;
        }

        public Criteria andV14NotBetween(Float value1, Float value2) {
            addCriterion("v14 not between", value1, value2, "v14");
            return (Criteria) this;
        }

        public Criteria andV15IsNull() {
            addCriterion("v15 is null");
            return (Criteria) this;
        }

        public Criteria andV15IsNotNull() {
            addCriterion("v15 is not null");
            return (Criteria) this;
        }

        public Criteria andV15EqualTo(Float value) {
            addCriterion("v15 =", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15NotEqualTo(Float value) {
            addCriterion("v15 <>", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15GreaterThan(Float value) {
            addCriterion("v15 >", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15GreaterThanOrEqualTo(Float value) {
            addCriterion("v15 >=", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15LessThan(Float value) {
            addCriterion("v15 <", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15LessThanOrEqualTo(Float value) {
            addCriterion("v15 <=", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15In(List<Float> values) {
            addCriterion("v15 in", values, "v15");
            return (Criteria) this;
        }

        public Criteria andV15NotIn(List<Float> values) {
            addCriterion("v15 not in", values, "v15");
            return (Criteria) this;
        }

        public Criteria andV15Between(Float value1, Float value2) {
            addCriterion("v15 between", value1, value2, "v15");
            return (Criteria) this;
        }

        public Criteria andV15NotBetween(Float value1, Float value2) {
            addCriterion("v15 not between", value1, value2, "v15");
            return (Criteria) this;
        }

        public Criteria andV16IsNull() {
            addCriterion("v16 is null");
            return (Criteria) this;
        }

        public Criteria andV16IsNotNull() {
            addCriterion("v16 is not null");
            return (Criteria) this;
        }

        public Criteria andV16EqualTo(Float value) {
            addCriterion("v16 =", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16NotEqualTo(Float value) {
            addCriterion("v16 <>", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16GreaterThan(Float value) {
            addCriterion("v16 >", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16GreaterThanOrEqualTo(Float value) {
            addCriterion("v16 >=", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16LessThan(Float value) {
            addCriterion("v16 <", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16LessThanOrEqualTo(Float value) {
            addCriterion("v16 <=", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16In(List<Float> values) {
            addCriterion("v16 in", values, "v16");
            return (Criteria) this;
        }

        public Criteria andV16NotIn(List<Float> values) {
            addCriterion("v16 not in", values, "v16");
            return (Criteria) this;
        }

        public Criteria andV16Between(Float value1, Float value2) {
            addCriterion("v16 between", value1, value2, "v16");
            return (Criteria) this;
        }

        public Criteria andV16NotBetween(Float value1, Float value2) {
            addCriterion("v16 not between", value1, value2, "v16");
            return (Criteria) this;
        }

        public Criteria andV17IsNull() {
            addCriterion("v17 is null");
            return (Criteria) this;
        }

        public Criteria andV17IsNotNull() {
            addCriterion("v17 is not null");
            return (Criteria) this;
        }

        public Criteria andV17EqualTo(Float value) {
            addCriterion("v17 =", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17NotEqualTo(Float value) {
            addCriterion("v17 <>", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17GreaterThan(Float value) {
            addCriterion("v17 >", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17GreaterThanOrEqualTo(Float value) {
            addCriterion("v17 >=", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17LessThan(Float value) {
            addCriterion("v17 <", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17LessThanOrEqualTo(Float value) {
            addCriterion("v17 <=", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17In(List<Float> values) {
            addCriterion("v17 in", values, "v17");
            return (Criteria) this;
        }

        public Criteria andV17NotIn(List<Float> values) {
            addCriterion("v17 not in", values, "v17");
            return (Criteria) this;
        }

        public Criteria andV17Between(Float value1, Float value2) {
            addCriterion("v17 between", value1, value2, "v17");
            return (Criteria) this;
        }

        public Criteria andV17NotBetween(Float value1, Float value2) {
            addCriterion("v17 not between", value1, value2, "v17");
            return (Criteria) this;
        }

        public Criteria andV18IsNull() {
            addCriterion("v18 is null");
            return (Criteria) this;
        }

        public Criteria andV18IsNotNull() {
            addCriterion("v18 is not null");
            return (Criteria) this;
        }

        public Criteria andV18EqualTo(Float value) {
            addCriterion("v18 =", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18NotEqualTo(Float value) {
            addCriterion("v18 <>", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18GreaterThan(Float value) {
            addCriterion("v18 >", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18GreaterThanOrEqualTo(Float value) {
            addCriterion("v18 >=", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18LessThan(Float value) {
            addCriterion("v18 <", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18LessThanOrEqualTo(Float value) {
            addCriterion("v18 <=", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18In(List<Float> values) {
            addCriterion("v18 in", values, "v18");
            return (Criteria) this;
        }

        public Criteria andV18NotIn(List<Float> values) {
            addCriterion("v18 not in", values, "v18");
            return (Criteria) this;
        }

        public Criteria andV18Between(Float value1, Float value2) {
            addCriterion("v18 between", value1, value2, "v18");
            return (Criteria) this;
        }

        public Criteria andV18NotBetween(Float value1, Float value2) {
            addCriterion("v18 not between", value1, value2, "v18");
            return (Criteria) this;
        }

        public Criteria andV19IsNull() {
            addCriterion("v19 is null");
            return (Criteria) this;
        }

        public Criteria andV19IsNotNull() {
            addCriterion("v19 is not null");
            return (Criteria) this;
        }

        public Criteria andV19EqualTo(Float value) {
            addCriterion("v19 =", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19NotEqualTo(Float value) {
            addCriterion("v19 <>", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19GreaterThan(Float value) {
            addCriterion("v19 >", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19GreaterThanOrEqualTo(Float value) {
            addCriterion("v19 >=", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19LessThan(Float value) {
            addCriterion("v19 <", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19LessThanOrEqualTo(Float value) {
            addCriterion("v19 <=", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19In(List<Float> values) {
            addCriterion("v19 in", values, "v19");
            return (Criteria) this;
        }

        public Criteria andV19NotIn(List<Float> values) {
            addCriterion("v19 not in", values, "v19");
            return (Criteria) this;
        }

        public Criteria andV19Between(Float value1, Float value2) {
            addCriterion("v19 between", value1, value2, "v19");
            return (Criteria) this;
        }

        public Criteria andV19NotBetween(Float value1, Float value2) {
            addCriterion("v19 not between", value1, value2, "v19");
            return (Criteria) this;
        }

        public Criteria andV20IsNull() {
            addCriterion("v20 is null");
            return (Criteria) this;
        }

        public Criteria andV20IsNotNull() {
            addCriterion("v20 is not null");
            return (Criteria) this;
        }

        public Criteria andV20EqualTo(Float value) {
            addCriterion("v20 =", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20NotEqualTo(Float value) {
            addCriterion("v20 <>", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20GreaterThan(Float value) {
            addCriterion("v20 >", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20GreaterThanOrEqualTo(Float value) {
            addCriterion("v20 >=", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20LessThan(Float value) {
            addCriterion("v20 <", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20LessThanOrEqualTo(Float value) {
            addCriterion("v20 <=", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20In(List<Float> values) {
            addCriterion("v20 in", values, "v20");
            return (Criteria) this;
        }

        public Criteria andV20NotIn(List<Float> values) {
            addCriterion("v20 not in", values, "v20");
            return (Criteria) this;
        }

        public Criteria andV20Between(Float value1, Float value2) {
            addCriterion("v20 between", value1, value2, "v20");
            return (Criteria) this;
        }

        public Criteria andV20NotBetween(Float value1, Float value2) {
            addCriterion("v20 not between", value1, value2, "v20");
            return (Criteria) this;
        }

        public Criteria andV21IsNull() {
            addCriterion("v21 is null");
            return (Criteria) this;
        }

        public Criteria andV21IsNotNull() {
            addCriterion("v21 is not null");
            return (Criteria) this;
        }

        public Criteria andV21EqualTo(Float value) {
            addCriterion("v21 =", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21NotEqualTo(Float value) {
            addCriterion("v21 <>", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21GreaterThan(Float value) {
            addCriterion("v21 >", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21GreaterThanOrEqualTo(Float value) {
            addCriterion("v21 >=", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21LessThan(Float value) {
            addCriterion("v21 <", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21LessThanOrEqualTo(Float value) {
            addCriterion("v21 <=", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21In(List<Float> values) {
            addCriterion("v21 in", values, "v21");
            return (Criteria) this;
        }

        public Criteria andV21NotIn(List<Float> values) {
            addCriterion("v21 not in", values, "v21");
            return (Criteria) this;
        }

        public Criteria andV21Between(Float value1, Float value2) {
            addCriterion("v21 between", value1, value2, "v21");
            return (Criteria) this;
        }

        public Criteria andV21NotBetween(Float value1, Float value2) {
            addCriterion("v21 not between", value1, value2, "v21");
            return (Criteria) this;
        }

        public Criteria andV22IsNull() {
            addCriterion("v22 is null");
            return (Criteria) this;
        }

        public Criteria andV22IsNotNull() {
            addCriterion("v22 is not null");
            return (Criteria) this;
        }

        public Criteria andV22EqualTo(Float value) {
            addCriterion("v22 =", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22NotEqualTo(Float value) {
            addCriterion("v22 <>", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22GreaterThan(Float value) {
            addCriterion("v22 >", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22GreaterThanOrEqualTo(Float value) {
            addCriterion("v22 >=", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22LessThan(Float value) {
            addCriterion("v22 <", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22LessThanOrEqualTo(Float value) {
            addCriterion("v22 <=", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22In(List<Float> values) {
            addCriterion("v22 in", values, "v22");
            return (Criteria) this;
        }

        public Criteria andV22NotIn(List<Float> values) {
            addCriterion("v22 not in", values, "v22");
            return (Criteria) this;
        }

        public Criteria andV22Between(Float value1, Float value2) {
            addCriterion("v22 between", value1, value2, "v22");
            return (Criteria) this;
        }

        public Criteria andV22NotBetween(Float value1, Float value2) {
            addCriterion("v22 not between", value1, value2, "v22");
            return (Criteria) this;
        }

        public Criteria andV23IsNull() {
            addCriterion("v23 is null");
            return (Criteria) this;
        }

        public Criteria andV23IsNotNull() {
            addCriterion("v23 is not null");
            return (Criteria) this;
        }

        public Criteria andV23EqualTo(Float value) {
            addCriterion("v23 =", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23NotEqualTo(Float value) {
            addCriterion("v23 <>", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23GreaterThan(Float value) {
            addCriterion("v23 >", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23GreaterThanOrEqualTo(Float value) {
            addCriterion("v23 >=", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23LessThan(Float value) {
            addCriterion("v23 <", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23LessThanOrEqualTo(Float value) {
            addCriterion("v23 <=", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23In(List<Float> values) {
            addCriterion("v23 in", values, "v23");
            return (Criteria) this;
        }

        public Criteria andV23NotIn(List<Float> values) {
            addCriterion("v23 not in", values, "v23");
            return (Criteria) this;
        }

        public Criteria andV23Between(Float value1, Float value2) {
            addCriterion("v23 between", value1, value2, "v23");
            return (Criteria) this;
        }

        public Criteria andV23NotBetween(Float value1, Float value2) {
            addCriterion("v23 not between", value1, value2, "v23");
            return (Criteria) this;
        }

        public Criteria andV24IsNull() {
            addCriterion("v24 is null");
            return (Criteria) this;
        }

        public Criteria andV24IsNotNull() {
            addCriterion("v24 is not null");
            return (Criteria) this;
        }

        public Criteria andV24EqualTo(Float value) {
            addCriterion("v24 =", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24NotEqualTo(Float value) {
            addCriterion("v24 <>", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24GreaterThan(Float value) {
            addCriterion("v24 >", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24GreaterThanOrEqualTo(Float value) {
            addCriterion("v24 >=", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24LessThan(Float value) {
            addCriterion("v24 <", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24LessThanOrEqualTo(Float value) {
            addCriterion("v24 <=", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24In(List<Float> values) {
            addCriterion("v24 in", values, "v24");
            return (Criteria) this;
        }

        public Criteria andV24NotIn(List<Float> values) {
            addCriterion("v24 not in", values, "v24");
            return (Criteria) this;
        }

        public Criteria andV24Between(Float value1, Float value2) {
            addCriterion("v24 between", value1, value2, "v24");
            return (Criteria) this;
        }

        public Criteria andV24NotBetween(Float value1, Float value2) {
            addCriterion("v24 not between", value1, value2, "v24");
            return (Criteria) this;
        }

        public Criteria andV25IsNull() {
            addCriterion("v25 is null");
            return (Criteria) this;
        }

        public Criteria andV25IsNotNull() {
            addCriterion("v25 is not null");
            return (Criteria) this;
        }

        public Criteria andV25EqualTo(Float value) {
            addCriterion("v25 =", value, "v25");
            return (Criteria) this;
        }

        public Criteria andV25NotEqualTo(Float value) {
            addCriterion("v25 <>", value, "v25");
            return (Criteria) this;
        }

        public Criteria andV25GreaterThan(Float value) {
            addCriterion("v25 >", value, "v25");
            return (Criteria) this;
        }

        public Criteria andV25GreaterThanOrEqualTo(Float value) {
            addCriterion("v25 >=", value, "v25");
            return (Criteria) this;
        }

        public Criteria andV25LessThan(Float value) {
            addCriterion("v25 <", value, "v25");
            return (Criteria) this;
        }

        public Criteria andV25LessThanOrEqualTo(Float value) {
            addCriterion("v25 <=", value, "v25");
            return (Criteria) this;
        }

        public Criteria andV25In(List<Float> values) {
            addCriterion("v25 in", values, "v25");
            return (Criteria) this;
        }

        public Criteria andV25NotIn(List<Float> values) {
            addCriterion("v25 not in", values, "v25");
            return (Criteria) this;
        }

        public Criteria andV25Between(Float value1, Float value2) {
            addCriterion("v25 between", value1, value2, "v25");
            return (Criteria) this;
        }

        public Criteria andV25NotBetween(Float value1, Float value2) {
            addCriterion("v25 not between", value1, value2, "v25");
            return (Criteria) this;
        }

        public Criteria andV26IsNull() {
            addCriterion("v26 is null");
            return (Criteria) this;
        }

        public Criteria andV26IsNotNull() {
            addCriterion("v26 is not null");
            return (Criteria) this;
        }

        public Criteria andV26EqualTo(Float value) {
            addCriterion("v26 =", value, "v26");
            return (Criteria) this;
        }

        public Criteria andV26NotEqualTo(Float value) {
            addCriterion("v26 <>", value, "v26");
            return (Criteria) this;
        }

        public Criteria andV26GreaterThan(Float value) {
            addCriterion("v26 >", value, "v26");
            return (Criteria) this;
        }

        public Criteria andV26GreaterThanOrEqualTo(Float value) {
            addCriterion("v26 >=", value, "v26");
            return (Criteria) this;
        }

        public Criteria andV26LessThan(Float value) {
            addCriterion("v26 <", value, "v26");
            return (Criteria) this;
        }

        public Criteria andV26LessThanOrEqualTo(Float value) {
            addCriterion("v26 <=", value, "v26");
            return (Criteria) this;
        }

        public Criteria andV26In(List<Float> values) {
            addCriterion("v26 in", values, "v26");
            return (Criteria) this;
        }

        public Criteria andV26NotIn(List<Float> values) {
            addCriterion("v26 not in", values, "v26");
            return (Criteria) this;
        }

        public Criteria andV26Between(Float value1, Float value2) {
            addCriterion("v26 between", value1, value2, "v26");
            return (Criteria) this;
        }

        public Criteria andV26NotBetween(Float value1, Float value2) {
            addCriterion("v26 not between", value1, value2, "v26");
            return (Criteria) this;
        }

        public Criteria andV27IsNull() {
            addCriterion("v27 is null");
            return (Criteria) this;
        }

        public Criteria andV27IsNotNull() {
            addCriterion("v27 is not null");
            return (Criteria) this;
        }

        public Criteria andV27EqualTo(Float value) {
            addCriterion("v27 =", value, "v27");
            return (Criteria) this;
        }

        public Criteria andV27NotEqualTo(Float value) {
            addCriterion("v27 <>", value, "v27");
            return (Criteria) this;
        }

        public Criteria andV27GreaterThan(Float value) {
            addCriterion("v27 >", value, "v27");
            return (Criteria) this;
        }

        public Criteria andV27GreaterThanOrEqualTo(Float value) {
            addCriterion("v27 >=", value, "v27");
            return (Criteria) this;
        }

        public Criteria andV27LessThan(Float value) {
            addCriterion("v27 <", value, "v27");
            return (Criteria) this;
        }

        public Criteria andV27LessThanOrEqualTo(Float value) {
            addCriterion("v27 <=", value, "v27");
            return (Criteria) this;
        }

        public Criteria andV27In(List<Float> values) {
            addCriterion("v27 in", values, "v27");
            return (Criteria) this;
        }

        public Criteria andV27NotIn(List<Float> values) {
            addCriterion("v27 not in", values, "v27");
            return (Criteria) this;
        }

        public Criteria andV27Between(Float value1, Float value2) {
            addCriterion("v27 between", value1, value2, "v27");
            return (Criteria) this;
        }

        public Criteria andV27NotBetween(Float value1, Float value2) {
            addCriterion("v27 not between", value1, value2, "v27");
            return (Criteria) this;
        }

        public Criteria andV28IsNull() {
            addCriterion("v28 is null");
            return (Criteria) this;
        }

        public Criteria andV28IsNotNull() {
            addCriterion("v28 is not null");
            return (Criteria) this;
        }

        public Criteria andV28EqualTo(Float value) {
            addCriterion("v28 =", value, "v28");
            return (Criteria) this;
        }

        public Criteria andV28NotEqualTo(Float value) {
            addCriterion("v28 <>", value, "v28");
            return (Criteria) this;
        }

        public Criteria andV28GreaterThan(Float value) {
            addCriterion("v28 >", value, "v28");
            return (Criteria) this;
        }

        public Criteria andV28GreaterThanOrEqualTo(Float value) {
            addCriterion("v28 >=", value, "v28");
            return (Criteria) this;
        }

        public Criteria andV28LessThan(Float value) {
            addCriterion("v28 <", value, "v28");
            return (Criteria) this;
        }

        public Criteria andV28LessThanOrEqualTo(Float value) {
            addCriterion("v28 <=", value, "v28");
            return (Criteria) this;
        }

        public Criteria andV28In(List<Float> values) {
            addCriterion("v28 in", values, "v28");
            return (Criteria) this;
        }

        public Criteria andV28NotIn(List<Float> values) {
            addCriterion("v28 not in", values, "v28");
            return (Criteria) this;
        }

        public Criteria andV28Between(Float value1, Float value2) {
            addCriterion("v28 between", value1, value2, "v28");
            return (Criteria) this;
        }

        public Criteria andV28NotBetween(Float value1, Float value2) {
            addCriterion("v28 not between", value1, value2, "v28");
            return (Criteria) this;
        }

        public Criteria andV29IsNull() {
            addCriterion("v29 is null");
            return (Criteria) this;
        }

        public Criteria andV29IsNotNull() {
            addCriterion("v29 is not null");
            return (Criteria) this;
        }

        public Criteria andV29EqualTo(Float value) {
            addCriterion("v29 =", value, "v29");
            return (Criteria) this;
        }

        public Criteria andV29NotEqualTo(Float value) {
            addCriterion("v29 <>", value, "v29");
            return (Criteria) this;
        }

        public Criteria andV29GreaterThan(Float value) {
            addCriterion("v29 >", value, "v29");
            return (Criteria) this;
        }

        public Criteria andV29GreaterThanOrEqualTo(Float value) {
            addCriterion("v29 >=", value, "v29");
            return (Criteria) this;
        }

        public Criteria andV29LessThan(Float value) {
            addCriterion("v29 <", value, "v29");
            return (Criteria) this;
        }

        public Criteria andV29LessThanOrEqualTo(Float value) {
            addCriterion("v29 <=", value, "v29");
            return (Criteria) this;
        }

        public Criteria andV29In(List<Float> values) {
            addCriterion("v29 in", values, "v29");
            return (Criteria) this;
        }

        public Criteria andV29NotIn(List<Float> values) {
            addCriterion("v29 not in", values, "v29");
            return (Criteria) this;
        }

        public Criteria andV29Between(Float value1, Float value2) {
            addCriterion("v29 between", value1, value2, "v29");
            return (Criteria) this;
        }

        public Criteria andV29NotBetween(Float value1, Float value2) {
            addCriterion("v29 not between", value1, value2, "v29");
            return (Criteria) this;
        }

        public Criteria andV30IsNull() {
            addCriterion("v30 is null");
            return (Criteria) this;
        }

        public Criteria andV30IsNotNull() {
            addCriterion("v30 is not null");
            return (Criteria) this;
        }

        public Criteria andV30EqualTo(Float value) {
            addCriterion("v30 =", value, "v30");
            return (Criteria) this;
        }

        public Criteria andV30NotEqualTo(Float value) {
            addCriterion("v30 <>", value, "v30");
            return (Criteria) this;
        }

        public Criteria andV30GreaterThan(Float value) {
            addCriterion("v30 >", value, "v30");
            return (Criteria) this;
        }

        public Criteria andV30GreaterThanOrEqualTo(Float value) {
            addCriterion("v30 >=", value, "v30");
            return (Criteria) this;
        }

        public Criteria andV30LessThan(Float value) {
            addCriterion("v30 <", value, "v30");
            return (Criteria) this;
        }

        public Criteria andV30LessThanOrEqualTo(Float value) {
            addCriterion("v30 <=", value, "v30");
            return (Criteria) this;
        }

        public Criteria andV30In(List<Float> values) {
            addCriterion("v30 in", values, "v30");
            return (Criteria) this;
        }

        public Criteria andV30NotIn(List<Float> values) {
            addCriterion("v30 not in", values, "v30");
            return (Criteria) this;
        }

        public Criteria andV30Between(Float value1, Float value2) {
            addCriterion("v30 between", value1, value2, "v30");
            return (Criteria) this;
        }

        public Criteria andV30NotBetween(Float value1, Float value2) {
            addCriterion("v30 not between", value1, value2, "v30");
            return (Criteria) this;
        }

        public Criteria andV31IsNull() {
            addCriterion("v31 is null");
            return (Criteria) this;
        }

        public Criteria andV31IsNotNull() {
            addCriterion("v31 is not null");
            return (Criteria) this;
        }

        public Criteria andV31EqualTo(Float value) {
            addCriterion("v31 =", value, "v31");
            return (Criteria) this;
        }

        public Criteria andV31NotEqualTo(Float value) {
            addCriterion("v31 <>", value, "v31");
            return (Criteria) this;
        }

        public Criteria andV31GreaterThan(Float value) {
            addCriterion("v31 >", value, "v31");
            return (Criteria) this;
        }

        public Criteria andV31GreaterThanOrEqualTo(Float value) {
            addCriterion("v31 >=", value, "v31");
            return (Criteria) this;
        }

        public Criteria andV31LessThan(Float value) {
            addCriterion("v31 <", value, "v31");
            return (Criteria) this;
        }

        public Criteria andV31LessThanOrEqualTo(Float value) {
            addCriterion("v31 <=", value, "v31");
            return (Criteria) this;
        }

        public Criteria andV31In(List<Float> values) {
            addCriterion("v31 in", values, "v31");
            return (Criteria) this;
        }

        public Criteria andV31NotIn(List<Float> values) {
            addCriterion("v31 not in", values, "v31");
            return (Criteria) this;
        }

        public Criteria andV31Between(Float value1, Float value2) {
            addCriterion("v31 between", value1, value2, "v31");
            return (Criteria) this;
        }

        public Criteria andV31NotBetween(Float value1, Float value2) {
            addCriterion("v31 not between", value1, value2, "v31");
            return (Criteria) this;
        }

        public Criteria andV32IsNull() {
            addCriterion("v32 is null");
            return (Criteria) this;
        }

        public Criteria andV32IsNotNull() {
            addCriterion("v32 is not null");
            return (Criteria) this;
        }

        public Criteria andV32EqualTo(Float value) {
            addCriterion("v32 =", value, "v32");
            return (Criteria) this;
        }

        public Criteria andV32NotEqualTo(Float value) {
            addCriterion("v32 <>", value, "v32");
            return (Criteria) this;
        }

        public Criteria andV32GreaterThan(Float value) {
            addCriterion("v32 >", value, "v32");
            return (Criteria) this;
        }

        public Criteria andV32GreaterThanOrEqualTo(Float value) {
            addCriterion("v32 >=", value, "v32");
            return (Criteria) this;
        }

        public Criteria andV32LessThan(Float value) {
            addCriterion("v32 <", value, "v32");
            return (Criteria) this;
        }

        public Criteria andV32LessThanOrEqualTo(Float value) {
            addCriterion("v32 <=", value, "v32");
            return (Criteria) this;
        }

        public Criteria andV32In(List<Float> values) {
            addCriterion("v32 in", values, "v32");
            return (Criteria) this;
        }

        public Criteria andV32NotIn(List<Float> values) {
            addCriterion("v32 not in", values, "v32");
            return (Criteria) this;
        }

        public Criteria andV32Between(Float value1, Float value2) {
            addCriterion("v32 between", value1, value2, "v32");
            return (Criteria) this;
        }

        public Criteria andV32NotBetween(Float value1, Float value2) {
            addCriterion("v32 not between", value1, value2, "v32");
            return (Criteria) this;
        }

        public Criteria andV33IsNull() {
            addCriterion("v33 is null");
            return (Criteria) this;
        }

        public Criteria andV33IsNotNull() {
            addCriterion("v33 is not null");
            return (Criteria) this;
        }

        public Criteria andV33EqualTo(Float value) {
            addCriterion("v33 =", value, "v33");
            return (Criteria) this;
        }

        public Criteria andV33NotEqualTo(Float value) {
            addCriterion("v33 <>", value, "v33");
            return (Criteria) this;
        }

        public Criteria andV33GreaterThan(Float value) {
            addCriterion("v33 >", value, "v33");
            return (Criteria) this;
        }

        public Criteria andV33GreaterThanOrEqualTo(Float value) {
            addCriterion("v33 >=", value, "v33");
            return (Criteria) this;
        }

        public Criteria andV33LessThan(Float value) {
            addCriterion("v33 <", value, "v33");
            return (Criteria) this;
        }

        public Criteria andV33LessThanOrEqualTo(Float value) {
            addCriterion("v33 <=", value, "v33");
            return (Criteria) this;
        }

        public Criteria andV33In(List<Float> values) {
            addCriterion("v33 in", values, "v33");
            return (Criteria) this;
        }

        public Criteria andV33NotIn(List<Float> values) {
            addCriterion("v33 not in", values, "v33");
            return (Criteria) this;
        }

        public Criteria andV33Between(Float value1, Float value2) {
            addCriterion("v33 between", value1, value2, "v33");
            return (Criteria) this;
        }

        public Criteria andV33NotBetween(Float value1, Float value2) {
            addCriterion("v33 not between", value1, value2, "v33");
            return (Criteria) this;
        }

        public Criteria andV34IsNull() {
            addCriterion("v34 is null");
            return (Criteria) this;
        }

        public Criteria andV34IsNotNull() {
            addCriterion("v34 is not null");
            return (Criteria) this;
        }

        public Criteria andV34EqualTo(Float value) {
            addCriterion("v34 =", value, "v34");
            return (Criteria) this;
        }

        public Criteria andV34NotEqualTo(Float value) {
            addCriterion("v34 <>", value, "v34");
            return (Criteria) this;
        }

        public Criteria andV34GreaterThan(Float value) {
            addCriterion("v34 >", value, "v34");
            return (Criteria) this;
        }

        public Criteria andV34GreaterThanOrEqualTo(Float value) {
            addCriterion("v34 >=", value, "v34");
            return (Criteria) this;
        }

        public Criteria andV34LessThan(Float value) {
            addCriterion("v34 <", value, "v34");
            return (Criteria) this;
        }

        public Criteria andV34LessThanOrEqualTo(Float value) {
            addCriterion("v34 <=", value, "v34");
            return (Criteria) this;
        }

        public Criteria andV34In(List<Float> values) {
            addCriterion("v34 in", values, "v34");
            return (Criteria) this;
        }

        public Criteria andV34NotIn(List<Float> values) {
            addCriterion("v34 not in", values, "v34");
            return (Criteria) this;
        }

        public Criteria andV34Between(Float value1, Float value2) {
            addCriterion("v34 between", value1, value2, "v34");
            return (Criteria) this;
        }

        public Criteria andV34NotBetween(Float value1, Float value2) {
            addCriterion("v34 not between", value1, value2, "v34");
            return (Criteria) this;
        }

        public Criteria andV35IsNull() {
            addCriterion("v35 is null");
            return (Criteria) this;
        }

        public Criteria andV35IsNotNull() {
            addCriterion("v35 is not null");
            return (Criteria) this;
        }

        public Criteria andV35EqualTo(Float value) {
            addCriterion("v35 =", value, "v35");
            return (Criteria) this;
        }

        public Criteria andV35NotEqualTo(Float value) {
            addCriterion("v35 <>", value, "v35");
            return (Criteria) this;
        }

        public Criteria andV35GreaterThan(Float value) {
            addCriterion("v35 >", value, "v35");
            return (Criteria) this;
        }

        public Criteria andV35GreaterThanOrEqualTo(Float value) {
            addCriterion("v35 >=", value, "v35");
            return (Criteria) this;
        }

        public Criteria andV35LessThan(Float value) {
            addCriterion("v35 <", value, "v35");
            return (Criteria) this;
        }

        public Criteria andV35LessThanOrEqualTo(Float value) {
            addCriterion("v35 <=", value, "v35");
            return (Criteria) this;
        }

        public Criteria andV35In(List<Float> values) {
            addCriterion("v35 in", values, "v35");
            return (Criteria) this;
        }

        public Criteria andV35NotIn(List<Float> values) {
            addCriterion("v35 not in", values, "v35");
            return (Criteria) this;
        }

        public Criteria andV35Between(Float value1, Float value2) {
            addCriterion("v35 between", value1, value2, "v35");
            return (Criteria) this;
        }

        public Criteria andV35NotBetween(Float value1, Float value2) {
            addCriterion("v35 not between", value1, value2, "v35");
            return (Criteria) this;
        }

        public Criteria andV36IsNull() {
            addCriterion("v36 is null");
            return (Criteria) this;
        }

        public Criteria andV36IsNotNull() {
            addCriterion("v36 is not null");
            return (Criteria) this;
        }

        public Criteria andV36EqualTo(Float value) {
            addCriterion("v36 =", value, "v36");
            return (Criteria) this;
        }

        public Criteria andV36NotEqualTo(Float value) {
            addCriterion("v36 <>", value, "v36");
            return (Criteria) this;
        }

        public Criteria andV36GreaterThan(Float value) {
            addCriterion("v36 >", value, "v36");
            return (Criteria) this;
        }

        public Criteria andV36GreaterThanOrEqualTo(Float value) {
            addCriterion("v36 >=", value, "v36");
            return (Criteria) this;
        }

        public Criteria andV36LessThan(Float value) {
            addCriterion("v36 <", value, "v36");
            return (Criteria) this;
        }

        public Criteria andV36LessThanOrEqualTo(Float value) {
            addCriterion("v36 <=", value, "v36");
            return (Criteria) this;
        }

        public Criteria andV36In(List<Float> values) {
            addCriterion("v36 in", values, "v36");
            return (Criteria) this;
        }

        public Criteria andV36NotIn(List<Float> values) {
            addCriterion("v36 not in", values, "v36");
            return (Criteria) this;
        }

        public Criteria andV36Between(Float value1, Float value2) {
            addCriterion("v36 between", value1, value2, "v36");
            return (Criteria) this;
        }

        public Criteria andV36NotBetween(Float value1, Float value2) {
            addCriterion("v36 not between", value1, value2, "v36");
            return (Criteria) this;
        }

        public Criteria andV37IsNull() {
            addCriterion("v37 is null");
            return (Criteria) this;
        }

        public Criteria andV37IsNotNull() {
            addCriterion("v37 is not null");
            return (Criteria) this;
        }

        public Criteria andV37EqualTo(Float value) {
            addCriterion("v37 =", value, "v37");
            return (Criteria) this;
        }

        public Criteria andV37NotEqualTo(Float value) {
            addCriterion("v37 <>", value, "v37");
            return (Criteria) this;
        }

        public Criteria andV37GreaterThan(Float value) {
            addCriterion("v37 >", value, "v37");
            return (Criteria) this;
        }

        public Criteria andV37GreaterThanOrEqualTo(Float value) {
            addCriterion("v37 >=", value, "v37");
            return (Criteria) this;
        }

        public Criteria andV37LessThan(Float value) {
            addCriterion("v37 <", value, "v37");
            return (Criteria) this;
        }

        public Criteria andV37LessThanOrEqualTo(Float value) {
            addCriterion("v37 <=", value, "v37");
            return (Criteria) this;
        }

        public Criteria andV37In(List<Float> values) {
            addCriterion("v37 in", values, "v37");
            return (Criteria) this;
        }

        public Criteria andV37NotIn(List<Float> values) {
            addCriterion("v37 not in", values, "v37");
            return (Criteria) this;
        }

        public Criteria andV37Between(Float value1, Float value2) {
            addCriterion("v37 between", value1, value2, "v37");
            return (Criteria) this;
        }

        public Criteria andV37NotBetween(Float value1, Float value2) {
            addCriterion("v37 not between", value1, value2, "v37");
            return (Criteria) this;
        }

        public Criteria andV38IsNull() {
            addCriterion("v38 is null");
            return (Criteria) this;
        }

        public Criteria andV38IsNotNull() {
            addCriterion("v38 is not null");
            return (Criteria) this;
        }

        public Criteria andV38EqualTo(Float value) {
            addCriterion("v38 =", value, "v38");
            return (Criteria) this;
        }

        public Criteria andV38NotEqualTo(Float value) {
            addCriterion("v38 <>", value, "v38");
            return (Criteria) this;
        }

        public Criteria andV38GreaterThan(Float value) {
            addCriterion("v38 >", value, "v38");
            return (Criteria) this;
        }

        public Criteria andV38GreaterThanOrEqualTo(Float value) {
            addCriterion("v38 >=", value, "v38");
            return (Criteria) this;
        }

        public Criteria andV38LessThan(Float value) {
            addCriterion("v38 <", value, "v38");
            return (Criteria) this;
        }

        public Criteria andV38LessThanOrEqualTo(Float value) {
            addCriterion("v38 <=", value, "v38");
            return (Criteria) this;
        }

        public Criteria andV38In(List<Float> values) {
            addCriterion("v38 in", values, "v38");
            return (Criteria) this;
        }

        public Criteria andV38NotIn(List<Float> values) {
            addCriterion("v38 not in", values, "v38");
            return (Criteria) this;
        }

        public Criteria andV38Between(Float value1, Float value2) {
            addCriterion("v38 between", value1, value2, "v38");
            return (Criteria) this;
        }

        public Criteria andV38NotBetween(Float value1, Float value2) {
            addCriterion("v38 not between", value1, value2, "v38");
            return (Criteria) this;
        }

        public Criteria andV39IsNull() {
            addCriterion("v39 is null");
            return (Criteria) this;
        }

        public Criteria andV39IsNotNull() {
            addCriterion("v39 is not null");
            return (Criteria) this;
        }

        public Criteria andV39EqualTo(Float value) {
            addCriterion("v39 =", value, "v39");
            return (Criteria) this;
        }

        public Criteria andV39NotEqualTo(Float value) {
            addCriterion("v39 <>", value, "v39");
            return (Criteria) this;
        }

        public Criteria andV39GreaterThan(Float value) {
            addCriterion("v39 >", value, "v39");
            return (Criteria) this;
        }

        public Criteria andV39GreaterThanOrEqualTo(Float value) {
            addCriterion("v39 >=", value, "v39");
            return (Criteria) this;
        }

        public Criteria andV39LessThan(Float value) {
            addCriterion("v39 <", value, "v39");
            return (Criteria) this;
        }

        public Criteria andV39LessThanOrEqualTo(Float value) {
            addCriterion("v39 <=", value, "v39");
            return (Criteria) this;
        }

        public Criteria andV39In(List<Float> values) {
            addCriterion("v39 in", values, "v39");
            return (Criteria) this;
        }

        public Criteria andV39NotIn(List<Float> values) {
            addCriterion("v39 not in", values, "v39");
            return (Criteria) this;
        }

        public Criteria andV39Between(Float value1, Float value2) {
            addCriterion("v39 between", value1, value2, "v39");
            return (Criteria) this;
        }

        public Criteria andV39NotBetween(Float value1, Float value2) {
            addCriterion("v39 not between", value1, value2, "v39");
            return (Criteria) this;
        }

        public Criteria andV40IsNull() {
            addCriterion("v40 is null");
            return (Criteria) this;
        }

        public Criteria andV40IsNotNull() {
            addCriterion("v40 is not null");
            return (Criteria) this;
        }

        public Criteria andV40EqualTo(Float value) {
            addCriterion("v40 =", value, "v40");
            return (Criteria) this;
        }

        public Criteria andV40NotEqualTo(Float value) {
            addCriterion("v40 <>", value, "v40");
            return (Criteria) this;
        }

        public Criteria andV40GreaterThan(Float value) {
            addCriterion("v40 >", value, "v40");
            return (Criteria) this;
        }

        public Criteria andV40GreaterThanOrEqualTo(Float value) {
            addCriterion("v40 >=", value, "v40");
            return (Criteria) this;
        }

        public Criteria andV40LessThan(Float value) {
            addCriterion("v40 <", value, "v40");
            return (Criteria) this;
        }

        public Criteria andV40LessThanOrEqualTo(Float value) {
            addCriterion("v40 <=", value, "v40");
            return (Criteria) this;
        }

        public Criteria andV40In(List<Float> values) {
            addCriterion("v40 in", values, "v40");
            return (Criteria) this;
        }

        public Criteria andV40NotIn(List<Float> values) {
            addCriterion("v40 not in", values, "v40");
            return (Criteria) this;
        }

        public Criteria andV40Between(Float value1, Float value2) {
            addCriterion("v40 between", value1, value2, "v40");
            return (Criteria) this;
        }

        public Criteria andV40NotBetween(Float value1, Float value2) {
            addCriterion("v40 not between", value1, value2, "v40");
            return (Criteria) this;
        }

        public Criteria andV41IsNull() {
            addCriterion("v41 is null");
            return (Criteria) this;
        }

        public Criteria andV41IsNotNull() {
            addCriterion("v41 is not null");
            return (Criteria) this;
        }

        public Criteria andV41EqualTo(Float value) {
            addCriterion("v41 =", value, "v41");
            return (Criteria) this;
        }

        public Criteria andV41NotEqualTo(Float value) {
            addCriterion("v41 <>", value, "v41");
            return (Criteria) this;
        }

        public Criteria andV41GreaterThan(Float value) {
            addCriterion("v41 >", value, "v41");
            return (Criteria) this;
        }

        public Criteria andV41GreaterThanOrEqualTo(Float value) {
            addCriterion("v41 >=", value, "v41");
            return (Criteria) this;
        }

        public Criteria andV41LessThan(Float value) {
            addCriterion("v41 <", value, "v41");
            return (Criteria) this;
        }

        public Criteria andV41LessThanOrEqualTo(Float value) {
            addCriterion("v41 <=", value, "v41");
            return (Criteria) this;
        }

        public Criteria andV41In(List<Float> values) {
            addCriterion("v41 in", values, "v41");
            return (Criteria) this;
        }

        public Criteria andV41NotIn(List<Float> values) {
            addCriterion("v41 not in", values, "v41");
            return (Criteria) this;
        }

        public Criteria andV41Between(Float value1, Float value2) {
            addCriterion("v41 between", value1, value2, "v41");
            return (Criteria) this;
        }

        public Criteria andV41NotBetween(Float value1, Float value2) {
            addCriterion("v41 not between", value1, value2, "v41");
            return (Criteria) this;
        }

        public Criteria andV42IsNull() {
            addCriterion("v42 is null");
            return (Criteria) this;
        }

        public Criteria andV42IsNotNull() {
            addCriterion("v42 is not null");
            return (Criteria) this;
        }

        public Criteria andV42EqualTo(Float value) {
            addCriterion("v42 =", value, "v42");
            return (Criteria) this;
        }

        public Criteria andV42NotEqualTo(Float value) {
            addCriterion("v42 <>", value, "v42");
            return (Criteria) this;
        }

        public Criteria andV42GreaterThan(Float value) {
            addCriterion("v42 >", value, "v42");
            return (Criteria) this;
        }

        public Criteria andV42GreaterThanOrEqualTo(Float value) {
            addCriterion("v42 >=", value, "v42");
            return (Criteria) this;
        }

        public Criteria andV42LessThan(Float value) {
            addCriterion("v42 <", value, "v42");
            return (Criteria) this;
        }

        public Criteria andV42LessThanOrEqualTo(Float value) {
            addCriterion("v42 <=", value, "v42");
            return (Criteria) this;
        }

        public Criteria andV42In(List<Float> values) {
            addCriterion("v42 in", values, "v42");
            return (Criteria) this;
        }

        public Criteria andV42NotIn(List<Float> values) {
            addCriterion("v42 not in", values, "v42");
            return (Criteria) this;
        }

        public Criteria andV42Between(Float value1, Float value2) {
            addCriterion("v42 between", value1, value2, "v42");
            return (Criteria) this;
        }

        public Criteria andV42NotBetween(Float value1, Float value2) {
            addCriterion("v42 not between", value1, value2, "v42");
            return (Criteria) this;
        }

        public Criteria andV43IsNull() {
            addCriterion("v43 is null");
            return (Criteria) this;
        }

        public Criteria andV43IsNotNull() {
            addCriterion("v43 is not null");
            return (Criteria) this;
        }

        public Criteria andV43EqualTo(Float value) {
            addCriterion("v43 =", value, "v43");
            return (Criteria) this;
        }

        public Criteria andV43NotEqualTo(Float value) {
            addCriterion("v43 <>", value, "v43");
            return (Criteria) this;
        }

        public Criteria andV43GreaterThan(Float value) {
            addCriterion("v43 >", value, "v43");
            return (Criteria) this;
        }

        public Criteria andV43GreaterThanOrEqualTo(Float value) {
            addCriterion("v43 >=", value, "v43");
            return (Criteria) this;
        }

        public Criteria andV43LessThan(Float value) {
            addCriterion("v43 <", value, "v43");
            return (Criteria) this;
        }

        public Criteria andV43LessThanOrEqualTo(Float value) {
            addCriterion("v43 <=", value, "v43");
            return (Criteria) this;
        }

        public Criteria andV43In(List<Float> values) {
            addCriterion("v43 in", values, "v43");
            return (Criteria) this;
        }

        public Criteria andV43NotIn(List<Float> values) {
            addCriterion("v43 not in", values, "v43");
            return (Criteria) this;
        }

        public Criteria andV43Between(Float value1, Float value2) {
            addCriterion("v43 between", value1, value2, "v43");
            return (Criteria) this;
        }

        public Criteria andV43NotBetween(Float value1, Float value2) {
            addCriterion("v43 not between", value1, value2, "v43");
            return (Criteria) this;
        }

        public Criteria andV44IsNull() {
            addCriterion("v44 is null");
            return (Criteria) this;
        }

        public Criteria andV44IsNotNull() {
            addCriterion("v44 is not null");
            return (Criteria) this;
        }

        public Criteria andV44EqualTo(Float value) {
            addCriterion("v44 =", value, "v44");
            return (Criteria) this;
        }

        public Criteria andV44NotEqualTo(Float value) {
            addCriterion("v44 <>", value, "v44");
            return (Criteria) this;
        }

        public Criteria andV44GreaterThan(Float value) {
            addCriterion("v44 >", value, "v44");
            return (Criteria) this;
        }

        public Criteria andV44GreaterThanOrEqualTo(Float value) {
            addCriterion("v44 >=", value, "v44");
            return (Criteria) this;
        }

        public Criteria andV44LessThan(Float value) {
            addCriterion("v44 <", value, "v44");
            return (Criteria) this;
        }

        public Criteria andV44LessThanOrEqualTo(Float value) {
            addCriterion("v44 <=", value, "v44");
            return (Criteria) this;
        }

        public Criteria andV44In(List<Float> values) {
            addCriterion("v44 in", values, "v44");
            return (Criteria) this;
        }

        public Criteria andV44NotIn(List<Float> values) {
            addCriterion("v44 not in", values, "v44");
            return (Criteria) this;
        }

        public Criteria andV44Between(Float value1, Float value2) {
            addCriterion("v44 between", value1, value2, "v44");
            return (Criteria) this;
        }

        public Criteria andV44NotBetween(Float value1, Float value2) {
            addCriterion("v44 not between", value1, value2, "v44");
            return (Criteria) this;
        }

        public Criteria andV45IsNull() {
            addCriterion("v45 is null");
            return (Criteria) this;
        }

        public Criteria andV45IsNotNull() {
            addCriterion("v45 is not null");
            return (Criteria) this;
        }

        public Criteria andV45EqualTo(Float value) {
            addCriterion("v45 =", value, "v45");
            return (Criteria) this;
        }

        public Criteria andV45NotEqualTo(Float value) {
            addCriterion("v45 <>", value, "v45");
            return (Criteria) this;
        }

        public Criteria andV45GreaterThan(Float value) {
            addCriterion("v45 >", value, "v45");
            return (Criteria) this;
        }

        public Criteria andV45GreaterThanOrEqualTo(Float value) {
            addCriterion("v45 >=", value, "v45");
            return (Criteria) this;
        }

        public Criteria andV45LessThan(Float value) {
            addCriterion("v45 <", value, "v45");
            return (Criteria) this;
        }

        public Criteria andV45LessThanOrEqualTo(Float value) {
            addCriterion("v45 <=", value, "v45");
            return (Criteria) this;
        }

        public Criteria andV45In(List<Float> values) {
            addCriterion("v45 in", values, "v45");
            return (Criteria) this;
        }

        public Criteria andV45NotIn(List<Float> values) {
            addCriterion("v45 not in", values, "v45");
            return (Criteria) this;
        }

        public Criteria andV45Between(Float value1, Float value2) {
            addCriterion("v45 between", value1, value2, "v45");
            return (Criteria) this;
        }

        public Criteria andV45NotBetween(Float value1, Float value2) {
            addCriterion("v45 not between", value1, value2, "v45");
            return (Criteria) this;
        }

        public Criteria andV46IsNull() {
            addCriterion("v46 is null");
            return (Criteria) this;
        }

        public Criteria andV46IsNotNull() {
            addCriterion("v46 is not null");
            return (Criteria) this;
        }

        public Criteria andV46EqualTo(Float value) {
            addCriterion("v46 =", value, "v46");
            return (Criteria) this;
        }

        public Criteria andV46NotEqualTo(Float value) {
            addCriterion("v46 <>", value, "v46");
            return (Criteria) this;
        }

        public Criteria andV46GreaterThan(Float value) {
            addCriterion("v46 >", value, "v46");
            return (Criteria) this;
        }

        public Criteria andV46GreaterThanOrEqualTo(Float value) {
            addCriterion("v46 >=", value, "v46");
            return (Criteria) this;
        }

        public Criteria andV46LessThan(Float value) {
            addCriterion("v46 <", value, "v46");
            return (Criteria) this;
        }

        public Criteria andV46LessThanOrEqualTo(Float value) {
            addCriterion("v46 <=", value, "v46");
            return (Criteria) this;
        }

        public Criteria andV46In(List<Float> values) {
            addCriterion("v46 in", values, "v46");
            return (Criteria) this;
        }

        public Criteria andV46NotIn(List<Float> values) {
            addCriterion("v46 not in", values, "v46");
            return (Criteria) this;
        }

        public Criteria andV46Between(Float value1, Float value2) {
            addCriterion("v46 between", value1, value2, "v46");
            return (Criteria) this;
        }

        public Criteria andV46NotBetween(Float value1, Float value2) {
            addCriterion("v46 not between", value1, value2, "v46");
            return (Criteria) this;
        }

        public Criteria andV47IsNull() {
            addCriterion("v47 is null");
            return (Criteria) this;
        }

        public Criteria andV47IsNotNull() {
            addCriterion("v47 is not null");
            return (Criteria) this;
        }

        public Criteria andV47EqualTo(Float value) {
            addCriterion("v47 =", value, "v47");
            return (Criteria) this;
        }

        public Criteria andV47NotEqualTo(Float value) {
            addCriterion("v47 <>", value, "v47");
            return (Criteria) this;
        }

        public Criteria andV47GreaterThan(Float value) {
            addCriterion("v47 >", value, "v47");
            return (Criteria) this;
        }

        public Criteria andV47GreaterThanOrEqualTo(Float value) {
            addCriterion("v47 >=", value, "v47");
            return (Criteria) this;
        }

        public Criteria andV47LessThan(Float value) {
            addCriterion("v47 <", value, "v47");
            return (Criteria) this;
        }

        public Criteria andV47LessThanOrEqualTo(Float value) {
            addCriterion("v47 <=", value, "v47");
            return (Criteria) this;
        }

        public Criteria andV47In(List<Float> values) {
            addCriterion("v47 in", values, "v47");
            return (Criteria) this;
        }

        public Criteria andV47NotIn(List<Float> values) {
            addCriterion("v47 not in", values, "v47");
            return (Criteria) this;
        }

        public Criteria andV47Between(Float value1, Float value2) {
            addCriterion("v47 between", value1, value2, "v47");
            return (Criteria) this;
        }

        public Criteria andV47NotBetween(Float value1, Float value2) {
            addCriterion("v47 not between", value1, value2, "v47");
            return (Criteria) this;
        }

        public Criteria andV48IsNull() {
            addCriterion("v48 is null");
            return (Criteria) this;
        }

        public Criteria andV48IsNotNull() {
            addCriterion("v48 is not null");
            return (Criteria) this;
        }

        public Criteria andV48EqualTo(Float value) {
            addCriterion("v48 =", value, "v48");
            return (Criteria) this;
        }

        public Criteria andV48NotEqualTo(Float value) {
            addCriterion("v48 <>", value, "v48");
            return (Criteria) this;
        }

        public Criteria andV48GreaterThan(Float value) {
            addCriterion("v48 >", value, "v48");
            return (Criteria) this;
        }

        public Criteria andV48GreaterThanOrEqualTo(Float value) {
            addCriterion("v48 >=", value, "v48");
            return (Criteria) this;
        }

        public Criteria andV48LessThan(Float value) {
            addCriterion("v48 <", value, "v48");
            return (Criteria) this;
        }

        public Criteria andV48LessThanOrEqualTo(Float value) {
            addCriterion("v48 <=", value, "v48");
            return (Criteria) this;
        }

        public Criteria andV48In(List<Float> values) {
            addCriterion("v48 in", values, "v48");
            return (Criteria) this;
        }

        public Criteria andV48NotIn(List<Float> values) {
            addCriterion("v48 not in", values, "v48");
            return (Criteria) this;
        }

        public Criteria andV48Between(Float value1, Float value2) {
            addCriterion("v48 between", value1, value2, "v48");
            return (Criteria) this;
        }

        public Criteria andV48NotBetween(Float value1, Float value2) {
            addCriterion("v48 not between", value1, value2, "v48");
            return (Criteria) this;
        }

        public Criteria andV49IsNull() {
            addCriterion("v49 is null");
            return (Criteria) this;
        }

        public Criteria andV49IsNotNull() {
            addCriterion("v49 is not null");
            return (Criteria) this;
        }

        public Criteria andV49EqualTo(Float value) {
            addCriterion("v49 =", value, "v49");
            return (Criteria) this;
        }

        public Criteria andV49NotEqualTo(Float value) {
            addCriterion("v49 <>", value, "v49");
            return (Criteria) this;
        }

        public Criteria andV49GreaterThan(Float value) {
            addCriterion("v49 >", value, "v49");
            return (Criteria) this;
        }

        public Criteria andV49GreaterThanOrEqualTo(Float value) {
            addCriterion("v49 >=", value, "v49");
            return (Criteria) this;
        }

        public Criteria andV49LessThan(Float value) {
            addCriterion("v49 <", value, "v49");
            return (Criteria) this;
        }

        public Criteria andV49LessThanOrEqualTo(Float value) {
            addCriterion("v49 <=", value, "v49");
            return (Criteria) this;
        }

        public Criteria andV49In(List<Float> values) {
            addCriterion("v49 in", values, "v49");
            return (Criteria) this;
        }

        public Criteria andV49NotIn(List<Float> values) {
            addCriterion("v49 not in", values, "v49");
            return (Criteria) this;
        }

        public Criteria andV49Between(Float value1, Float value2) {
            addCriterion("v49 between", value1, value2, "v49");
            return (Criteria) this;
        }

        public Criteria andV49NotBetween(Float value1, Float value2) {
            addCriterion("v49 not between", value1, value2, "v49");
            return (Criteria) this;
        }

        public Criteria andV50IsNull() {
            addCriterion("v50 is null");
            return (Criteria) this;
        }

        public Criteria andV50IsNotNull() {
            addCriterion("v50 is not null");
            return (Criteria) this;
        }

        public Criteria andV50EqualTo(Float value) {
            addCriterion("v50 =", value, "v50");
            return (Criteria) this;
        }

        public Criteria andV50NotEqualTo(Float value) {
            addCriterion("v50 <>", value, "v50");
            return (Criteria) this;
        }

        public Criteria andV50GreaterThan(Float value) {
            addCriterion("v50 >", value, "v50");
            return (Criteria) this;
        }

        public Criteria andV50GreaterThanOrEqualTo(Float value) {
            addCriterion("v50 >=", value, "v50");
            return (Criteria) this;
        }

        public Criteria andV50LessThan(Float value) {
            addCriterion("v50 <", value, "v50");
            return (Criteria) this;
        }

        public Criteria andV50LessThanOrEqualTo(Float value) {
            addCriterion("v50 <=", value, "v50");
            return (Criteria) this;
        }

        public Criteria andV50In(List<Float> values) {
            addCriterion("v50 in", values, "v50");
            return (Criteria) this;
        }

        public Criteria andV50NotIn(List<Float> values) {
            addCriterion("v50 not in", values, "v50");
            return (Criteria) this;
        }

        public Criteria andV50Between(Float value1, Float value2) {
            addCriterion("v50 between", value1, value2, "v50");
            return (Criteria) this;
        }

        public Criteria andV50NotBetween(Float value1, Float value2) {
            addCriterion("v50 not between", value1, value2, "v50");
            return (Criteria) this;
        }

        public Criteria andV51IsNull() {
            addCriterion("v51 is null");
            return (Criteria) this;
        }

        public Criteria andV51IsNotNull() {
            addCriterion("v51 is not null");
            return (Criteria) this;
        }

        public Criteria andV51EqualTo(Float value) {
            addCriterion("v51 =", value, "v51");
            return (Criteria) this;
        }

        public Criteria andV51NotEqualTo(Float value) {
            addCriterion("v51 <>", value, "v51");
            return (Criteria) this;
        }

        public Criteria andV51GreaterThan(Float value) {
            addCriterion("v51 >", value, "v51");
            return (Criteria) this;
        }

        public Criteria andV51GreaterThanOrEqualTo(Float value) {
            addCriterion("v51 >=", value, "v51");
            return (Criteria) this;
        }

        public Criteria andV51LessThan(Float value) {
            addCriterion("v51 <", value, "v51");
            return (Criteria) this;
        }

        public Criteria andV51LessThanOrEqualTo(Float value) {
            addCriterion("v51 <=", value, "v51");
            return (Criteria) this;
        }

        public Criteria andV51In(List<Float> values) {
            addCriterion("v51 in", values, "v51");
            return (Criteria) this;
        }

        public Criteria andV51NotIn(List<Float> values) {
            addCriterion("v51 not in", values, "v51");
            return (Criteria) this;
        }

        public Criteria andV51Between(Float value1, Float value2) {
            addCriterion("v51 between", value1, value2, "v51");
            return (Criteria) this;
        }

        public Criteria andV51NotBetween(Float value1, Float value2) {
            addCriterion("v51 not between", value1, value2, "v51");
            return (Criteria) this;
        }

        public Criteria andV52IsNull() {
            addCriterion("v52 is null");
            return (Criteria) this;
        }

        public Criteria andV52IsNotNull() {
            addCriterion("v52 is not null");
            return (Criteria) this;
        }

        public Criteria andV52EqualTo(Float value) {
            addCriterion("v52 =", value, "v52");
            return (Criteria) this;
        }

        public Criteria andV52NotEqualTo(Float value) {
            addCriterion("v52 <>", value, "v52");
            return (Criteria) this;
        }

        public Criteria andV52GreaterThan(Float value) {
            addCriterion("v52 >", value, "v52");
            return (Criteria) this;
        }

        public Criteria andV52GreaterThanOrEqualTo(Float value) {
            addCriterion("v52 >=", value, "v52");
            return (Criteria) this;
        }

        public Criteria andV52LessThan(Float value) {
            addCriterion("v52 <", value, "v52");
            return (Criteria) this;
        }

        public Criteria andV52LessThanOrEqualTo(Float value) {
            addCriterion("v52 <=", value, "v52");
            return (Criteria) this;
        }

        public Criteria andV52In(List<Float> values) {
            addCriterion("v52 in", values, "v52");
            return (Criteria) this;
        }

        public Criteria andV52NotIn(List<Float> values) {
            addCriterion("v52 not in", values, "v52");
            return (Criteria) this;
        }

        public Criteria andV52Between(Float value1, Float value2) {
            addCriterion("v52 between", value1, value2, "v52");
            return (Criteria) this;
        }

        public Criteria andV52NotBetween(Float value1, Float value2) {
            addCriterion("v52 not between", value1, value2, "v52");
            return (Criteria) this;
        }

        public Criteria andV53IsNull() {
            addCriterion("v53 is null");
            return (Criteria) this;
        }

        public Criteria andV53IsNotNull() {
            addCriterion("v53 is not null");
            return (Criteria) this;
        }

        public Criteria andV53EqualTo(Float value) {
            addCriterion("v53 =", value, "v53");
            return (Criteria) this;
        }

        public Criteria andV53NotEqualTo(Float value) {
            addCriterion("v53 <>", value, "v53");
            return (Criteria) this;
        }

        public Criteria andV53GreaterThan(Float value) {
            addCriterion("v53 >", value, "v53");
            return (Criteria) this;
        }

        public Criteria andV53GreaterThanOrEqualTo(Float value) {
            addCriterion("v53 >=", value, "v53");
            return (Criteria) this;
        }

        public Criteria andV53LessThan(Float value) {
            addCriterion("v53 <", value, "v53");
            return (Criteria) this;
        }

        public Criteria andV53LessThanOrEqualTo(Float value) {
            addCriterion("v53 <=", value, "v53");
            return (Criteria) this;
        }

        public Criteria andV53In(List<Float> values) {
            addCriterion("v53 in", values, "v53");
            return (Criteria) this;
        }

        public Criteria andV53NotIn(List<Float> values) {
            addCriterion("v53 not in", values, "v53");
            return (Criteria) this;
        }

        public Criteria andV53Between(Float value1, Float value2) {
            addCriterion("v53 between", value1, value2, "v53");
            return (Criteria) this;
        }

        public Criteria andV53NotBetween(Float value1, Float value2) {
            addCriterion("v53 not between", value1, value2, "v53");
            return (Criteria) this;
        }

        public Criteria andV54IsNull() {
            addCriterion("v54 is null");
            return (Criteria) this;
        }

        public Criteria andV54IsNotNull() {
            addCriterion("v54 is not null");
            return (Criteria) this;
        }

        public Criteria andV54EqualTo(Float value) {
            addCriterion("v54 =", value, "v54");
            return (Criteria) this;
        }

        public Criteria andV54NotEqualTo(Float value) {
            addCriterion("v54 <>", value, "v54");
            return (Criteria) this;
        }

        public Criteria andV54GreaterThan(Float value) {
            addCriterion("v54 >", value, "v54");
            return (Criteria) this;
        }

        public Criteria andV54GreaterThanOrEqualTo(Float value) {
            addCriterion("v54 >=", value, "v54");
            return (Criteria) this;
        }

        public Criteria andV54LessThan(Float value) {
            addCriterion("v54 <", value, "v54");
            return (Criteria) this;
        }

        public Criteria andV54LessThanOrEqualTo(Float value) {
            addCriterion("v54 <=", value, "v54");
            return (Criteria) this;
        }

        public Criteria andV54In(List<Float> values) {
            addCriterion("v54 in", values, "v54");
            return (Criteria) this;
        }

        public Criteria andV54NotIn(List<Float> values) {
            addCriterion("v54 not in", values, "v54");
            return (Criteria) this;
        }

        public Criteria andV54Between(Float value1, Float value2) {
            addCriterion("v54 between", value1, value2, "v54");
            return (Criteria) this;
        }

        public Criteria andV54NotBetween(Float value1, Float value2) {
            addCriterion("v54 not between", value1, value2, "v54");
            return (Criteria) this;
        }

        public Criteria andV55IsNull() {
            addCriterion("v55 is null");
            return (Criteria) this;
        }

        public Criteria andV55IsNotNull() {
            addCriterion("v55 is not null");
            return (Criteria) this;
        }

        public Criteria andV55EqualTo(Float value) {
            addCriterion("v55 =", value, "v55");
            return (Criteria) this;
        }

        public Criteria andV55NotEqualTo(Float value) {
            addCriterion("v55 <>", value, "v55");
            return (Criteria) this;
        }

        public Criteria andV55GreaterThan(Float value) {
            addCriterion("v55 >", value, "v55");
            return (Criteria) this;
        }

        public Criteria andV55GreaterThanOrEqualTo(Float value) {
            addCriterion("v55 >=", value, "v55");
            return (Criteria) this;
        }

        public Criteria andV55LessThan(Float value) {
            addCriterion("v55 <", value, "v55");
            return (Criteria) this;
        }

        public Criteria andV55LessThanOrEqualTo(Float value) {
            addCriterion("v55 <=", value, "v55");
            return (Criteria) this;
        }

        public Criteria andV55In(List<Float> values) {
            addCriterion("v55 in", values, "v55");
            return (Criteria) this;
        }

        public Criteria andV55NotIn(List<Float> values) {
            addCriterion("v55 not in", values, "v55");
            return (Criteria) this;
        }

        public Criteria andV55Between(Float value1, Float value2) {
            addCriterion("v55 between", value1, value2, "v55");
            return (Criteria) this;
        }

        public Criteria andV55NotBetween(Float value1, Float value2) {
            addCriterion("v55 not between", value1, value2, "v55");
            return (Criteria) this;
        }

        public Criteria andV56IsNull() {
            addCriterion("v56 is null");
            return (Criteria) this;
        }

        public Criteria andV56IsNotNull() {
            addCriterion("v56 is not null");
            return (Criteria) this;
        }

        public Criteria andV56EqualTo(Float value) {
            addCriterion("v56 =", value, "v56");
            return (Criteria) this;
        }

        public Criteria andV56NotEqualTo(Float value) {
            addCriterion("v56 <>", value, "v56");
            return (Criteria) this;
        }

        public Criteria andV56GreaterThan(Float value) {
            addCriterion("v56 >", value, "v56");
            return (Criteria) this;
        }

        public Criteria andV56GreaterThanOrEqualTo(Float value) {
            addCriterion("v56 >=", value, "v56");
            return (Criteria) this;
        }

        public Criteria andV56LessThan(Float value) {
            addCriterion("v56 <", value, "v56");
            return (Criteria) this;
        }

        public Criteria andV56LessThanOrEqualTo(Float value) {
            addCriterion("v56 <=", value, "v56");
            return (Criteria) this;
        }

        public Criteria andV56In(List<Float> values) {
            addCriterion("v56 in", values, "v56");
            return (Criteria) this;
        }

        public Criteria andV56NotIn(List<Float> values) {
            addCriterion("v56 not in", values, "v56");
            return (Criteria) this;
        }

        public Criteria andV56Between(Float value1, Float value2) {
            addCriterion("v56 between", value1, value2, "v56");
            return (Criteria) this;
        }

        public Criteria andV56NotBetween(Float value1, Float value2) {
            addCriterion("v56 not between", value1, value2, "v56");
            return (Criteria) this;
        }

        public Criteria andV57IsNull() {
            addCriterion("v57 is null");
            return (Criteria) this;
        }

        public Criteria andV57IsNotNull() {
            addCriterion("v57 is not null");
            return (Criteria) this;
        }

        public Criteria andV57EqualTo(Float value) {
            addCriterion("v57 =", value, "v57");
            return (Criteria) this;
        }

        public Criteria andV57NotEqualTo(Float value) {
            addCriterion("v57 <>", value, "v57");
            return (Criteria) this;
        }

        public Criteria andV57GreaterThan(Float value) {
            addCriterion("v57 >", value, "v57");
            return (Criteria) this;
        }

        public Criteria andV57GreaterThanOrEqualTo(Float value) {
            addCriterion("v57 >=", value, "v57");
            return (Criteria) this;
        }

        public Criteria andV57LessThan(Float value) {
            addCriterion("v57 <", value, "v57");
            return (Criteria) this;
        }

        public Criteria andV57LessThanOrEqualTo(Float value) {
            addCriterion("v57 <=", value, "v57");
            return (Criteria) this;
        }

        public Criteria andV57In(List<Float> values) {
            addCriterion("v57 in", values, "v57");
            return (Criteria) this;
        }

        public Criteria andV57NotIn(List<Float> values) {
            addCriterion("v57 not in", values, "v57");
            return (Criteria) this;
        }

        public Criteria andV57Between(Float value1, Float value2) {
            addCriterion("v57 between", value1, value2, "v57");
            return (Criteria) this;
        }

        public Criteria andV57NotBetween(Float value1, Float value2) {
            addCriterion("v57 not between", value1, value2, "v57");
            return (Criteria) this;
        }

        public Criteria andV58IsNull() {
            addCriterion("v58 is null");
            return (Criteria) this;
        }

        public Criteria andV58IsNotNull() {
            addCriterion("v58 is not null");
            return (Criteria) this;
        }

        public Criteria andV58EqualTo(Float value) {
            addCriterion("v58 =", value, "v58");
            return (Criteria) this;
        }

        public Criteria andV58NotEqualTo(Float value) {
            addCriterion("v58 <>", value, "v58");
            return (Criteria) this;
        }

        public Criteria andV58GreaterThan(Float value) {
            addCriterion("v58 >", value, "v58");
            return (Criteria) this;
        }

        public Criteria andV58GreaterThanOrEqualTo(Float value) {
            addCriterion("v58 >=", value, "v58");
            return (Criteria) this;
        }

        public Criteria andV58LessThan(Float value) {
            addCriterion("v58 <", value, "v58");
            return (Criteria) this;
        }

        public Criteria andV58LessThanOrEqualTo(Float value) {
            addCriterion("v58 <=", value, "v58");
            return (Criteria) this;
        }

        public Criteria andV58In(List<Float> values) {
            addCriterion("v58 in", values, "v58");
            return (Criteria) this;
        }

        public Criteria andV58NotIn(List<Float> values) {
            addCriterion("v58 not in", values, "v58");
            return (Criteria) this;
        }

        public Criteria andV58Between(Float value1, Float value2) {
            addCriterion("v58 between", value1, value2, "v58");
            return (Criteria) this;
        }

        public Criteria andV58NotBetween(Float value1, Float value2) {
            addCriterion("v58 not between", value1, value2, "v58");
            return (Criteria) this;
        }

        public Criteria andV59IsNull() {
            addCriterion("v59 is null");
            return (Criteria) this;
        }

        public Criteria andV59IsNotNull() {
            addCriterion("v59 is not null");
            return (Criteria) this;
        }

        public Criteria andV59EqualTo(Float value) {
            addCriterion("v59 =", value, "v59");
            return (Criteria) this;
        }

        public Criteria andV59NotEqualTo(Float value) {
            addCriterion("v59 <>", value, "v59");
            return (Criteria) this;
        }

        public Criteria andV59GreaterThan(Float value) {
            addCriterion("v59 >", value, "v59");
            return (Criteria) this;
        }

        public Criteria andV59GreaterThanOrEqualTo(Float value) {
            addCriterion("v59 >=", value, "v59");
            return (Criteria) this;
        }

        public Criteria andV59LessThan(Float value) {
            addCriterion("v59 <", value, "v59");
            return (Criteria) this;
        }

        public Criteria andV59LessThanOrEqualTo(Float value) {
            addCriterion("v59 <=", value, "v59");
            return (Criteria) this;
        }

        public Criteria andV59In(List<Float> values) {
            addCriterion("v59 in", values, "v59");
            return (Criteria) this;
        }

        public Criteria andV59NotIn(List<Float> values) {
            addCriterion("v59 not in", values, "v59");
            return (Criteria) this;
        }

        public Criteria andV59Between(Float value1, Float value2) {
            addCriterion("v59 between", value1, value2, "v59");
            return (Criteria) this;
        }

        public Criteria andV59NotBetween(Float value1, Float value2) {
            addCriterion("v59 not between", value1, value2, "v59");
            return (Criteria) this;
        }

        public Criteria andV60IsNull() {
            addCriterion("v60 is null");
            return (Criteria) this;
        }

        public Criteria andV60IsNotNull() {
            addCriterion("v60 is not null");
            return (Criteria) this;
        }

        public Criteria andV60EqualTo(Float value) {
            addCriterion("v60 =", value, "v60");
            return (Criteria) this;
        }

        public Criteria andV60NotEqualTo(Float value) {
            addCriterion("v60 <>", value, "v60");
            return (Criteria) this;
        }

        public Criteria andV60GreaterThan(Float value) {
            addCriterion("v60 >", value, "v60");
            return (Criteria) this;
        }

        public Criteria andV60GreaterThanOrEqualTo(Float value) {
            addCriterion("v60 >=", value, "v60");
            return (Criteria) this;
        }

        public Criteria andV60LessThan(Float value) {
            addCriterion("v60 <", value, "v60");
            return (Criteria) this;
        }

        public Criteria andV60LessThanOrEqualTo(Float value) {
            addCriterion("v60 <=", value, "v60");
            return (Criteria) this;
        }

        public Criteria andV60In(List<Float> values) {
            addCriterion("v60 in", values, "v60");
            return (Criteria) this;
        }

        public Criteria andV60NotIn(List<Float> values) {
            addCriterion("v60 not in", values, "v60");
            return (Criteria) this;
        }

        public Criteria andV60Between(Float value1, Float value2) {
            addCriterion("v60 between", value1, value2, "v60");
            return (Criteria) this;
        }

        public Criteria andV60NotBetween(Float value1, Float value2) {
            addCriterion("v60 not between", value1, value2, "v60");
            return (Criteria) this;
        }

        public Criteria andV61IsNull() {
            addCriterion("v61 is null");
            return (Criteria) this;
        }

        public Criteria andV61IsNotNull() {
            addCriterion("v61 is not null");
            return (Criteria) this;
        }

        public Criteria andV61EqualTo(Float value) {
            addCriterion("v61 =", value, "v61");
            return (Criteria) this;
        }

        public Criteria andV61NotEqualTo(Float value) {
            addCriterion("v61 <>", value, "v61");
            return (Criteria) this;
        }

        public Criteria andV61GreaterThan(Float value) {
            addCriterion("v61 >", value, "v61");
            return (Criteria) this;
        }

        public Criteria andV61GreaterThanOrEqualTo(Float value) {
            addCriterion("v61 >=", value, "v61");
            return (Criteria) this;
        }

        public Criteria andV61LessThan(Float value) {
            addCriterion("v61 <", value, "v61");
            return (Criteria) this;
        }

        public Criteria andV61LessThanOrEqualTo(Float value) {
            addCriterion("v61 <=", value, "v61");
            return (Criteria) this;
        }

        public Criteria andV61In(List<Float> values) {
            addCriterion("v61 in", values, "v61");
            return (Criteria) this;
        }

        public Criteria andV61NotIn(List<Float> values) {
            addCriterion("v61 not in", values, "v61");
            return (Criteria) this;
        }

        public Criteria andV61Between(Float value1, Float value2) {
            addCriterion("v61 between", value1, value2, "v61");
            return (Criteria) this;
        }

        public Criteria andV61NotBetween(Float value1, Float value2) {
            addCriterion("v61 not between", value1, value2, "v61");
            return (Criteria) this;
        }

        public Criteria andV62IsNull() {
            addCriterion("v62 is null");
            return (Criteria) this;
        }

        public Criteria andV62IsNotNull() {
            addCriterion("v62 is not null");
            return (Criteria) this;
        }

        public Criteria andV62EqualTo(Float value) {
            addCriterion("v62 =", value, "v62");
            return (Criteria) this;
        }

        public Criteria andV62NotEqualTo(Float value) {
            addCriterion("v62 <>", value, "v62");
            return (Criteria) this;
        }

        public Criteria andV62GreaterThan(Float value) {
            addCriterion("v62 >", value, "v62");
            return (Criteria) this;
        }

        public Criteria andV62GreaterThanOrEqualTo(Float value) {
            addCriterion("v62 >=", value, "v62");
            return (Criteria) this;
        }

        public Criteria andV62LessThan(Float value) {
            addCriterion("v62 <", value, "v62");
            return (Criteria) this;
        }

        public Criteria andV62LessThanOrEqualTo(Float value) {
            addCriterion("v62 <=", value, "v62");
            return (Criteria) this;
        }

        public Criteria andV62In(List<Float> values) {
            addCriterion("v62 in", values, "v62");
            return (Criteria) this;
        }

        public Criteria andV62NotIn(List<Float> values) {
            addCriterion("v62 not in", values, "v62");
            return (Criteria) this;
        }

        public Criteria andV62Between(Float value1, Float value2) {
            addCriterion("v62 between", value1, value2, "v62");
            return (Criteria) this;
        }

        public Criteria andV62NotBetween(Float value1, Float value2) {
            addCriterion("v62 not between", value1, value2, "v62");
            return (Criteria) this;
        }

        public Criteria andV63IsNull() {
            addCriterion("v63 is null");
            return (Criteria) this;
        }

        public Criteria andV63IsNotNull() {
            addCriterion("v63 is not null");
            return (Criteria) this;
        }

        public Criteria andV63EqualTo(Float value) {
            addCriterion("v63 =", value, "v63");
            return (Criteria) this;
        }

        public Criteria andV63NotEqualTo(Float value) {
            addCriterion("v63 <>", value, "v63");
            return (Criteria) this;
        }

        public Criteria andV63GreaterThan(Float value) {
            addCriterion("v63 >", value, "v63");
            return (Criteria) this;
        }

        public Criteria andV63GreaterThanOrEqualTo(Float value) {
            addCriterion("v63 >=", value, "v63");
            return (Criteria) this;
        }

        public Criteria andV63LessThan(Float value) {
            addCriterion("v63 <", value, "v63");
            return (Criteria) this;
        }

        public Criteria andV63LessThanOrEqualTo(Float value) {
            addCriterion("v63 <=", value, "v63");
            return (Criteria) this;
        }

        public Criteria andV63In(List<Float> values) {
            addCriterion("v63 in", values, "v63");
            return (Criteria) this;
        }

        public Criteria andV63NotIn(List<Float> values) {
            addCriterion("v63 not in", values, "v63");
            return (Criteria) this;
        }

        public Criteria andV63Between(Float value1, Float value2) {
            addCriterion("v63 between", value1, value2, "v63");
            return (Criteria) this;
        }

        public Criteria andV63NotBetween(Float value1, Float value2) {
            addCriterion("v63 not between", value1, value2, "v63");
            return (Criteria) this;
        }

        public Criteria andV64IsNull() {
            addCriterion("v64 is null");
            return (Criteria) this;
        }

        public Criteria andV64IsNotNull() {
            addCriterion("v64 is not null");
            return (Criteria) this;
        }

        public Criteria andV64EqualTo(Float value) {
            addCriterion("v64 =", value, "v64");
            return (Criteria) this;
        }

        public Criteria andV64NotEqualTo(Float value) {
            addCriterion("v64 <>", value, "v64");
            return (Criteria) this;
        }

        public Criteria andV64GreaterThan(Float value) {
            addCriterion("v64 >", value, "v64");
            return (Criteria) this;
        }

        public Criteria andV64GreaterThanOrEqualTo(Float value) {
            addCriterion("v64 >=", value, "v64");
            return (Criteria) this;
        }

        public Criteria andV64LessThan(Float value) {
            addCriterion("v64 <", value, "v64");
            return (Criteria) this;
        }

        public Criteria andV64LessThanOrEqualTo(Float value) {
            addCriterion("v64 <=", value, "v64");
            return (Criteria) this;
        }

        public Criteria andV64In(List<Float> values) {
            addCriterion("v64 in", values, "v64");
            return (Criteria) this;
        }

        public Criteria andV64NotIn(List<Float> values) {
            addCriterion("v64 not in", values, "v64");
            return (Criteria) this;
        }

        public Criteria andV64Between(Float value1, Float value2) {
            addCriterion("v64 between", value1, value2, "v64");
            return (Criteria) this;
        }

        public Criteria andV64NotBetween(Float value1, Float value2) {
            addCriterion("v64 not between", value1, value2, "v64");
            return (Criteria) this;
        }

        public Criteria andV65IsNull() {
            addCriterion("v65 is null");
            return (Criteria) this;
        }

        public Criteria andV65IsNotNull() {
            addCriterion("v65 is not null");
            return (Criteria) this;
        }

        public Criteria andV65EqualTo(Float value) {
            addCriterion("v65 =", value, "v65");
            return (Criteria) this;
        }

        public Criteria andV65NotEqualTo(Float value) {
            addCriterion("v65 <>", value, "v65");
            return (Criteria) this;
        }

        public Criteria andV65GreaterThan(Float value) {
            addCriterion("v65 >", value, "v65");
            return (Criteria) this;
        }

        public Criteria andV65GreaterThanOrEqualTo(Float value) {
            addCriterion("v65 >=", value, "v65");
            return (Criteria) this;
        }

        public Criteria andV65LessThan(Float value) {
            addCriterion("v65 <", value, "v65");
            return (Criteria) this;
        }

        public Criteria andV65LessThanOrEqualTo(Float value) {
            addCriterion("v65 <=", value, "v65");
            return (Criteria) this;
        }

        public Criteria andV65In(List<Float> values) {
            addCriterion("v65 in", values, "v65");
            return (Criteria) this;
        }

        public Criteria andV65NotIn(List<Float> values) {
            addCriterion("v65 not in", values, "v65");
            return (Criteria) this;
        }

        public Criteria andV65Between(Float value1, Float value2) {
            addCriterion("v65 between", value1, value2, "v65");
            return (Criteria) this;
        }

        public Criteria andV65NotBetween(Float value1, Float value2) {
            addCriterion("v65 not between", value1, value2, "v65");
            return (Criteria) this;
        }

        public Criteria andV66IsNull() {
            addCriterion("v66 is null");
            return (Criteria) this;
        }

        public Criteria andV66IsNotNull() {
            addCriterion("v66 is not null");
            return (Criteria) this;
        }

        public Criteria andV66EqualTo(Float value) {
            addCriterion("v66 =", value, "v66");
            return (Criteria) this;
        }

        public Criteria andV66NotEqualTo(Float value) {
            addCriterion("v66 <>", value, "v66");
            return (Criteria) this;
        }

        public Criteria andV66GreaterThan(Float value) {
            addCriterion("v66 >", value, "v66");
            return (Criteria) this;
        }

        public Criteria andV66GreaterThanOrEqualTo(Float value) {
            addCriterion("v66 >=", value, "v66");
            return (Criteria) this;
        }

        public Criteria andV66LessThan(Float value) {
            addCriterion("v66 <", value, "v66");
            return (Criteria) this;
        }

        public Criteria andV66LessThanOrEqualTo(Float value) {
            addCriterion("v66 <=", value, "v66");
            return (Criteria) this;
        }

        public Criteria andV66In(List<Float> values) {
            addCriterion("v66 in", values, "v66");
            return (Criteria) this;
        }

        public Criteria andV66NotIn(List<Float> values) {
            addCriterion("v66 not in", values, "v66");
            return (Criteria) this;
        }

        public Criteria andV66Between(Float value1, Float value2) {
            addCriterion("v66 between", value1, value2, "v66");
            return (Criteria) this;
        }

        public Criteria andV66NotBetween(Float value1, Float value2) {
            addCriterion("v66 not between", value1, value2, "v66");
            return (Criteria) this;
        }

        public Criteria andV67IsNull() {
            addCriterion("v67 is null");
            return (Criteria) this;
        }

        public Criteria andV67IsNotNull() {
            addCriterion("v67 is not null");
            return (Criteria) this;
        }

        public Criteria andV67EqualTo(Float value) {
            addCriterion("v67 =", value, "v67");
            return (Criteria) this;
        }

        public Criteria andV67NotEqualTo(Float value) {
            addCriterion("v67 <>", value, "v67");
            return (Criteria) this;
        }

        public Criteria andV67GreaterThan(Float value) {
            addCriterion("v67 >", value, "v67");
            return (Criteria) this;
        }

        public Criteria andV67GreaterThanOrEqualTo(Float value) {
            addCriterion("v67 >=", value, "v67");
            return (Criteria) this;
        }

        public Criteria andV67LessThan(Float value) {
            addCriterion("v67 <", value, "v67");
            return (Criteria) this;
        }

        public Criteria andV67LessThanOrEqualTo(Float value) {
            addCriterion("v67 <=", value, "v67");
            return (Criteria) this;
        }

        public Criteria andV67In(List<Float> values) {
            addCriterion("v67 in", values, "v67");
            return (Criteria) this;
        }

        public Criteria andV67NotIn(List<Float> values) {
            addCriterion("v67 not in", values, "v67");
            return (Criteria) this;
        }

        public Criteria andV67Between(Float value1, Float value2) {
            addCriterion("v67 between", value1, value2, "v67");
            return (Criteria) this;
        }

        public Criteria andV67NotBetween(Float value1, Float value2) {
            addCriterion("v67 not between", value1, value2, "v67");
            return (Criteria) this;
        }

        public Criteria andV68IsNull() {
            addCriterion("v68 is null");
            return (Criteria) this;
        }

        public Criteria andV68IsNotNull() {
            addCriterion("v68 is not null");
            return (Criteria) this;
        }

        public Criteria andV68EqualTo(Float value) {
            addCriterion("v68 =", value, "v68");
            return (Criteria) this;
        }

        public Criteria andV68NotEqualTo(Float value) {
            addCriterion("v68 <>", value, "v68");
            return (Criteria) this;
        }

        public Criteria andV68GreaterThan(Float value) {
            addCriterion("v68 >", value, "v68");
            return (Criteria) this;
        }

        public Criteria andV68GreaterThanOrEqualTo(Float value) {
            addCriterion("v68 >=", value, "v68");
            return (Criteria) this;
        }

        public Criteria andV68LessThan(Float value) {
            addCriterion("v68 <", value, "v68");
            return (Criteria) this;
        }

        public Criteria andV68LessThanOrEqualTo(Float value) {
            addCriterion("v68 <=", value, "v68");
            return (Criteria) this;
        }

        public Criteria andV68In(List<Float> values) {
            addCriterion("v68 in", values, "v68");
            return (Criteria) this;
        }

        public Criteria andV68NotIn(List<Float> values) {
            addCriterion("v68 not in", values, "v68");
            return (Criteria) this;
        }

        public Criteria andV68Between(Float value1, Float value2) {
            addCriterion("v68 between", value1, value2, "v68");
            return (Criteria) this;
        }

        public Criteria andV68NotBetween(Float value1, Float value2) {
            addCriterion("v68 not between", value1, value2, "v68");
            return (Criteria) this;
        }

        public Criteria andV69IsNull() {
            addCriterion("v69 is null");
            return (Criteria) this;
        }

        public Criteria andV69IsNotNull() {
            addCriterion("v69 is not null");
            return (Criteria) this;
        }

        public Criteria andV69EqualTo(Float value) {
            addCriterion("v69 =", value, "v69");
            return (Criteria) this;
        }

        public Criteria andV69NotEqualTo(Float value) {
            addCriterion("v69 <>", value, "v69");
            return (Criteria) this;
        }

        public Criteria andV69GreaterThan(Float value) {
            addCriterion("v69 >", value, "v69");
            return (Criteria) this;
        }

        public Criteria andV69GreaterThanOrEqualTo(Float value) {
            addCriterion("v69 >=", value, "v69");
            return (Criteria) this;
        }

        public Criteria andV69LessThan(Float value) {
            addCriterion("v69 <", value, "v69");
            return (Criteria) this;
        }

        public Criteria andV69LessThanOrEqualTo(Float value) {
            addCriterion("v69 <=", value, "v69");
            return (Criteria) this;
        }

        public Criteria andV69In(List<Float> values) {
            addCriterion("v69 in", values, "v69");
            return (Criteria) this;
        }

        public Criteria andV69NotIn(List<Float> values) {
            addCriterion("v69 not in", values, "v69");
            return (Criteria) this;
        }

        public Criteria andV69Between(Float value1, Float value2) {
            addCriterion("v69 between", value1, value2, "v69");
            return (Criteria) this;
        }

        public Criteria andV69NotBetween(Float value1, Float value2) {
            addCriterion("v69 not between", value1, value2, "v69");
            return (Criteria) this;
        }

        public Criteria andV70IsNull() {
            addCriterion("v70 is null");
            return (Criteria) this;
        }

        public Criteria andV70IsNotNull() {
            addCriterion("v70 is not null");
            return (Criteria) this;
        }

        public Criteria andV70EqualTo(Float value) {
            addCriterion("v70 =", value, "v70");
            return (Criteria) this;
        }

        public Criteria andV70NotEqualTo(Float value) {
            addCriterion("v70 <>", value, "v70");
            return (Criteria) this;
        }

        public Criteria andV70GreaterThan(Float value) {
            addCriterion("v70 >", value, "v70");
            return (Criteria) this;
        }

        public Criteria andV70GreaterThanOrEqualTo(Float value) {
            addCriterion("v70 >=", value, "v70");
            return (Criteria) this;
        }

        public Criteria andV70LessThan(Float value) {
            addCriterion("v70 <", value, "v70");
            return (Criteria) this;
        }

        public Criteria andV70LessThanOrEqualTo(Float value) {
            addCriterion("v70 <=", value, "v70");
            return (Criteria) this;
        }

        public Criteria andV70In(List<Float> values) {
            addCriterion("v70 in", values, "v70");
            return (Criteria) this;
        }

        public Criteria andV70NotIn(List<Float> values) {
            addCriterion("v70 not in", values, "v70");
            return (Criteria) this;
        }

        public Criteria andV70Between(Float value1, Float value2) {
            addCriterion("v70 between", value1, value2, "v70");
            return (Criteria) this;
        }

        public Criteria andV70NotBetween(Float value1, Float value2) {
            addCriterion("v70 not between", value1, value2, "v70");
            return (Criteria) this;
        }

        public Criteria andV71IsNull() {
            addCriterion("v71 is null");
            return (Criteria) this;
        }

        public Criteria andV71IsNotNull() {
            addCriterion("v71 is not null");
            return (Criteria) this;
        }

        public Criteria andV71EqualTo(Float value) {
            addCriterion("v71 =", value, "v71");
            return (Criteria) this;
        }

        public Criteria andV71NotEqualTo(Float value) {
            addCriterion("v71 <>", value, "v71");
            return (Criteria) this;
        }

        public Criteria andV71GreaterThan(Float value) {
            addCriterion("v71 >", value, "v71");
            return (Criteria) this;
        }

        public Criteria andV71GreaterThanOrEqualTo(Float value) {
            addCriterion("v71 >=", value, "v71");
            return (Criteria) this;
        }

        public Criteria andV71LessThan(Float value) {
            addCriterion("v71 <", value, "v71");
            return (Criteria) this;
        }

        public Criteria andV71LessThanOrEqualTo(Float value) {
            addCriterion("v71 <=", value, "v71");
            return (Criteria) this;
        }

        public Criteria andV71In(List<Float> values) {
            addCriterion("v71 in", values, "v71");
            return (Criteria) this;
        }

        public Criteria andV71NotIn(List<Float> values) {
            addCriterion("v71 not in", values, "v71");
            return (Criteria) this;
        }

        public Criteria andV71Between(Float value1, Float value2) {
            addCriterion("v71 between", value1, value2, "v71");
            return (Criteria) this;
        }

        public Criteria andV71NotBetween(Float value1, Float value2) {
            addCriterion("v71 not between", value1, value2, "v71");
            return (Criteria) this;
        }

        public Criteria andV72IsNull() {
            addCriterion("v72 is null");
            return (Criteria) this;
        }

        public Criteria andV72IsNotNull() {
            addCriterion("v72 is not null");
            return (Criteria) this;
        }

        public Criteria andV72EqualTo(Float value) {
            addCriterion("v72 =", value, "v72");
            return (Criteria) this;
        }

        public Criteria andV72NotEqualTo(Float value) {
            addCriterion("v72 <>", value, "v72");
            return (Criteria) this;
        }

        public Criteria andV72GreaterThan(Float value) {
            addCriterion("v72 >", value, "v72");
            return (Criteria) this;
        }

        public Criteria andV72GreaterThanOrEqualTo(Float value) {
            addCriterion("v72 >=", value, "v72");
            return (Criteria) this;
        }

        public Criteria andV72LessThan(Float value) {
            addCriterion("v72 <", value, "v72");
            return (Criteria) this;
        }

        public Criteria andV72LessThanOrEqualTo(Float value) {
            addCriterion("v72 <=", value, "v72");
            return (Criteria) this;
        }

        public Criteria andV72In(List<Float> values) {
            addCriterion("v72 in", values, "v72");
            return (Criteria) this;
        }

        public Criteria andV72NotIn(List<Float> values) {
            addCriterion("v72 not in", values, "v72");
            return (Criteria) this;
        }

        public Criteria andV72Between(Float value1, Float value2) {
            addCriterion("v72 between", value1, value2, "v72");
            return (Criteria) this;
        }

        public Criteria andV72NotBetween(Float value1, Float value2) {
            addCriterion("v72 not between", value1, value2, "v72");
            return (Criteria) this;
        }

        public Criteria andV73IsNull() {
            addCriterion("v73 is null");
            return (Criteria) this;
        }

        public Criteria andV73IsNotNull() {
            addCriterion("v73 is not null");
            return (Criteria) this;
        }

        public Criteria andV73EqualTo(Float value) {
            addCriterion("v73 =", value, "v73");
            return (Criteria) this;
        }

        public Criteria andV73NotEqualTo(Float value) {
            addCriterion("v73 <>", value, "v73");
            return (Criteria) this;
        }

        public Criteria andV73GreaterThan(Float value) {
            addCriterion("v73 >", value, "v73");
            return (Criteria) this;
        }

        public Criteria andV73GreaterThanOrEqualTo(Float value) {
            addCriterion("v73 >=", value, "v73");
            return (Criteria) this;
        }

        public Criteria andV73LessThan(Float value) {
            addCriterion("v73 <", value, "v73");
            return (Criteria) this;
        }

        public Criteria andV73LessThanOrEqualTo(Float value) {
            addCriterion("v73 <=", value, "v73");
            return (Criteria) this;
        }

        public Criteria andV73In(List<Float> values) {
            addCriterion("v73 in", values, "v73");
            return (Criteria) this;
        }

        public Criteria andV73NotIn(List<Float> values) {
            addCriterion("v73 not in", values, "v73");
            return (Criteria) this;
        }

        public Criteria andV73Between(Float value1, Float value2) {
            addCriterion("v73 between", value1, value2, "v73");
            return (Criteria) this;
        }

        public Criteria andV73NotBetween(Float value1, Float value2) {
            addCriterion("v73 not between", value1, value2, "v73");
            return (Criteria) this;
        }

        public Criteria andV74IsNull() {
            addCriterion("v74 is null");
            return (Criteria) this;
        }

        public Criteria andV74IsNotNull() {
            addCriterion("v74 is not null");
            return (Criteria) this;
        }

        public Criteria andV74EqualTo(Float value) {
            addCriterion("v74 =", value, "v74");
            return (Criteria) this;
        }

        public Criteria andV74NotEqualTo(Float value) {
            addCriterion("v74 <>", value, "v74");
            return (Criteria) this;
        }

        public Criteria andV74GreaterThan(Float value) {
            addCriterion("v74 >", value, "v74");
            return (Criteria) this;
        }

        public Criteria andV74GreaterThanOrEqualTo(Float value) {
            addCriterion("v74 >=", value, "v74");
            return (Criteria) this;
        }

        public Criteria andV74LessThan(Float value) {
            addCriterion("v74 <", value, "v74");
            return (Criteria) this;
        }

        public Criteria andV74LessThanOrEqualTo(Float value) {
            addCriterion("v74 <=", value, "v74");
            return (Criteria) this;
        }

        public Criteria andV74In(List<Float> values) {
            addCriterion("v74 in", values, "v74");
            return (Criteria) this;
        }

        public Criteria andV74NotIn(List<Float> values) {
            addCriterion("v74 not in", values, "v74");
            return (Criteria) this;
        }

        public Criteria andV74Between(Float value1, Float value2) {
            addCriterion("v74 between", value1, value2, "v74");
            return (Criteria) this;
        }

        public Criteria andV74NotBetween(Float value1, Float value2) {
            addCriterion("v74 not between", value1, value2, "v74");
            return (Criteria) this;
        }

        public Criteria andV75IsNull() {
            addCriterion("v75 is null");
            return (Criteria) this;
        }

        public Criteria andV75IsNotNull() {
            addCriterion("v75 is not null");
            return (Criteria) this;
        }

        public Criteria andV75EqualTo(Float value) {
            addCriterion("v75 =", value, "v75");
            return (Criteria) this;
        }

        public Criteria andV75NotEqualTo(Float value) {
            addCriterion("v75 <>", value, "v75");
            return (Criteria) this;
        }

        public Criteria andV75GreaterThan(Float value) {
            addCriterion("v75 >", value, "v75");
            return (Criteria) this;
        }

        public Criteria andV75GreaterThanOrEqualTo(Float value) {
            addCriterion("v75 >=", value, "v75");
            return (Criteria) this;
        }

        public Criteria andV75LessThan(Float value) {
            addCriterion("v75 <", value, "v75");
            return (Criteria) this;
        }

        public Criteria andV75LessThanOrEqualTo(Float value) {
            addCriterion("v75 <=", value, "v75");
            return (Criteria) this;
        }

        public Criteria andV75In(List<Float> values) {
            addCriterion("v75 in", values, "v75");
            return (Criteria) this;
        }

        public Criteria andV75NotIn(List<Float> values) {
            addCriterion("v75 not in", values, "v75");
            return (Criteria) this;
        }

        public Criteria andV75Between(Float value1, Float value2) {
            addCriterion("v75 between", value1, value2, "v75");
            return (Criteria) this;
        }

        public Criteria andV75NotBetween(Float value1, Float value2) {
            addCriterion("v75 not between", value1, value2, "v75");
            return (Criteria) this;
        }

        public Criteria andV76IsNull() {
            addCriterion("v76 is null");
            return (Criteria) this;
        }

        public Criteria andV76IsNotNull() {
            addCriterion("v76 is not null");
            return (Criteria) this;
        }

        public Criteria andV76EqualTo(Float value) {
            addCriterion("v76 =", value, "v76");
            return (Criteria) this;
        }

        public Criteria andV76NotEqualTo(Float value) {
            addCriterion("v76 <>", value, "v76");
            return (Criteria) this;
        }

        public Criteria andV76GreaterThan(Float value) {
            addCriterion("v76 >", value, "v76");
            return (Criteria) this;
        }

        public Criteria andV76GreaterThanOrEqualTo(Float value) {
            addCriterion("v76 >=", value, "v76");
            return (Criteria) this;
        }

        public Criteria andV76LessThan(Float value) {
            addCriterion("v76 <", value, "v76");
            return (Criteria) this;
        }

        public Criteria andV76LessThanOrEqualTo(Float value) {
            addCriterion("v76 <=", value, "v76");
            return (Criteria) this;
        }

        public Criteria andV76In(List<Float> values) {
            addCriterion("v76 in", values, "v76");
            return (Criteria) this;
        }

        public Criteria andV76NotIn(List<Float> values) {
            addCriterion("v76 not in", values, "v76");
            return (Criteria) this;
        }

        public Criteria andV76Between(Float value1, Float value2) {
            addCriterion("v76 between", value1, value2, "v76");
            return (Criteria) this;
        }

        public Criteria andV76NotBetween(Float value1, Float value2) {
            addCriterion("v76 not between", value1, value2, "v76");
            return (Criteria) this;
        }

        public Criteria andV77IsNull() {
            addCriterion("v77 is null");
            return (Criteria) this;
        }

        public Criteria andV77IsNotNull() {
            addCriterion("v77 is not null");
            return (Criteria) this;
        }

        public Criteria andV77EqualTo(Float value) {
            addCriterion("v77 =", value, "v77");
            return (Criteria) this;
        }

        public Criteria andV77NotEqualTo(Float value) {
            addCriterion("v77 <>", value, "v77");
            return (Criteria) this;
        }

        public Criteria andV77GreaterThan(Float value) {
            addCriterion("v77 >", value, "v77");
            return (Criteria) this;
        }

        public Criteria andV77GreaterThanOrEqualTo(Float value) {
            addCriterion("v77 >=", value, "v77");
            return (Criteria) this;
        }

        public Criteria andV77LessThan(Float value) {
            addCriterion("v77 <", value, "v77");
            return (Criteria) this;
        }

        public Criteria andV77LessThanOrEqualTo(Float value) {
            addCriterion("v77 <=", value, "v77");
            return (Criteria) this;
        }

        public Criteria andV77In(List<Float> values) {
            addCriterion("v77 in", values, "v77");
            return (Criteria) this;
        }

        public Criteria andV77NotIn(List<Float> values) {
            addCriterion("v77 not in", values, "v77");
            return (Criteria) this;
        }

        public Criteria andV77Between(Float value1, Float value2) {
            addCriterion("v77 between", value1, value2, "v77");
            return (Criteria) this;
        }

        public Criteria andV77NotBetween(Float value1, Float value2) {
            addCriterion("v77 not between", value1, value2, "v77");
            return (Criteria) this;
        }

        public Criteria andV78IsNull() {
            addCriterion("v78 is null");
            return (Criteria) this;
        }

        public Criteria andV78IsNotNull() {
            addCriterion("v78 is not null");
            return (Criteria) this;
        }

        public Criteria andV78EqualTo(Float value) {
            addCriterion("v78 =", value, "v78");
            return (Criteria) this;
        }

        public Criteria andV78NotEqualTo(Float value) {
            addCriterion("v78 <>", value, "v78");
            return (Criteria) this;
        }

        public Criteria andV78GreaterThan(Float value) {
            addCriterion("v78 >", value, "v78");
            return (Criteria) this;
        }

        public Criteria andV78GreaterThanOrEqualTo(Float value) {
            addCriterion("v78 >=", value, "v78");
            return (Criteria) this;
        }

        public Criteria andV78LessThan(Float value) {
            addCriterion("v78 <", value, "v78");
            return (Criteria) this;
        }

        public Criteria andV78LessThanOrEqualTo(Float value) {
            addCriterion("v78 <=", value, "v78");
            return (Criteria) this;
        }

        public Criteria andV78In(List<Float> values) {
            addCriterion("v78 in", values, "v78");
            return (Criteria) this;
        }

        public Criteria andV78NotIn(List<Float> values) {
            addCriterion("v78 not in", values, "v78");
            return (Criteria) this;
        }

        public Criteria andV78Between(Float value1, Float value2) {
            addCriterion("v78 between", value1, value2, "v78");
            return (Criteria) this;
        }

        public Criteria andV78NotBetween(Float value1, Float value2) {
            addCriterion("v78 not between", value1, value2, "v78");
            return (Criteria) this;
        }

        public Criteria andV79IsNull() {
            addCriterion("v79 is null");
            return (Criteria) this;
        }

        public Criteria andV79IsNotNull() {
            addCriterion("v79 is not null");
            return (Criteria) this;
        }

        public Criteria andV79EqualTo(Float value) {
            addCriterion("v79 =", value, "v79");
            return (Criteria) this;
        }

        public Criteria andV79NotEqualTo(Float value) {
            addCriterion("v79 <>", value, "v79");
            return (Criteria) this;
        }

        public Criteria andV79GreaterThan(Float value) {
            addCriterion("v79 >", value, "v79");
            return (Criteria) this;
        }

        public Criteria andV79GreaterThanOrEqualTo(Float value) {
            addCriterion("v79 >=", value, "v79");
            return (Criteria) this;
        }

        public Criteria andV79LessThan(Float value) {
            addCriterion("v79 <", value, "v79");
            return (Criteria) this;
        }

        public Criteria andV79LessThanOrEqualTo(Float value) {
            addCriterion("v79 <=", value, "v79");
            return (Criteria) this;
        }

        public Criteria andV79In(List<Float> values) {
            addCriterion("v79 in", values, "v79");
            return (Criteria) this;
        }

        public Criteria andV79NotIn(List<Float> values) {
            addCriterion("v79 not in", values, "v79");
            return (Criteria) this;
        }

        public Criteria andV79Between(Float value1, Float value2) {
            addCriterion("v79 between", value1, value2, "v79");
            return (Criteria) this;
        }

        public Criteria andV79NotBetween(Float value1, Float value2) {
            addCriterion("v79 not between", value1, value2, "v79");
            return (Criteria) this;
        }

        public Criteria andV80IsNull() {
            addCriterion("v80 is null");
            return (Criteria) this;
        }

        public Criteria andV80IsNotNull() {
            addCriterion("v80 is not null");
            return (Criteria) this;
        }

        public Criteria andV80EqualTo(Float value) {
            addCriterion("v80 =", value, "v80");
            return (Criteria) this;
        }

        public Criteria andV80NotEqualTo(Float value) {
            addCriterion("v80 <>", value, "v80");
            return (Criteria) this;
        }

        public Criteria andV80GreaterThan(Float value) {
            addCriterion("v80 >", value, "v80");
            return (Criteria) this;
        }

        public Criteria andV80GreaterThanOrEqualTo(Float value) {
            addCriterion("v80 >=", value, "v80");
            return (Criteria) this;
        }

        public Criteria andV80LessThan(Float value) {
            addCriterion("v80 <", value, "v80");
            return (Criteria) this;
        }

        public Criteria andV80LessThanOrEqualTo(Float value) {
            addCriterion("v80 <=", value, "v80");
            return (Criteria) this;
        }

        public Criteria andV80In(List<Float> values) {
            addCriterion("v80 in", values, "v80");
            return (Criteria) this;
        }

        public Criteria andV80NotIn(List<Float> values) {
            addCriterion("v80 not in", values, "v80");
            return (Criteria) this;
        }

        public Criteria andV80Between(Float value1, Float value2) {
            addCriterion("v80 between", value1, value2, "v80");
            return (Criteria) this;
        }

        public Criteria andV80NotBetween(Float value1, Float value2) {
            addCriterion("v80 not between", value1, value2, "v80");
            return (Criteria) this;
        }

        public Criteria andV81IsNull() {
            addCriterion("v81 is null");
            return (Criteria) this;
        }

        public Criteria andV81IsNotNull() {
            addCriterion("v81 is not null");
            return (Criteria) this;
        }

        public Criteria andV81EqualTo(Float value) {
            addCriterion("v81 =", value, "v81");
            return (Criteria) this;
        }

        public Criteria andV81NotEqualTo(Float value) {
            addCriterion("v81 <>", value, "v81");
            return (Criteria) this;
        }

        public Criteria andV81GreaterThan(Float value) {
            addCriterion("v81 >", value, "v81");
            return (Criteria) this;
        }

        public Criteria andV81GreaterThanOrEqualTo(Float value) {
            addCriterion("v81 >=", value, "v81");
            return (Criteria) this;
        }

        public Criteria andV81LessThan(Float value) {
            addCriterion("v81 <", value, "v81");
            return (Criteria) this;
        }

        public Criteria andV81LessThanOrEqualTo(Float value) {
            addCriterion("v81 <=", value, "v81");
            return (Criteria) this;
        }

        public Criteria andV81In(List<Float> values) {
            addCriterion("v81 in", values, "v81");
            return (Criteria) this;
        }

        public Criteria andV81NotIn(List<Float> values) {
            addCriterion("v81 not in", values, "v81");
            return (Criteria) this;
        }

        public Criteria andV81Between(Float value1, Float value2) {
            addCriterion("v81 between", value1, value2, "v81");
            return (Criteria) this;
        }

        public Criteria andV81NotBetween(Float value1, Float value2) {
            addCriterion("v81 not between", value1, value2, "v81");
            return (Criteria) this;
        }

        public Criteria andV82IsNull() {
            addCriterion("v82 is null");
            return (Criteria) this;
        }

        public Criteria andV82IsNotNull() {
            addCriterion("v82 is not null");
            return (Criteria) this;
        }

        public Criteria andV82EqualTo(Float value) {
            addCriterion("v82 =", value, "v82");
            return (Criteria) this;
        }

        public Criteria andV82NotEqualTo(Float value) {
            addCriterion("v82 <>", value, "v82");
            return (Criteria) this;
        }

        public Criteria andV82GreaterThan(Float value) {
            addCriterion("v82 >", value, "v82");
            return (Criteria) this;
        }

        public Criteria andV82GreaterThanOrEqualTo(Float value) {
            addCriterion("v82 >=", value, "v82");
            return (Criteria) this;
        }

        public Criteria andV82LessThan(Float value) {
            addCriterion("v82 <", value, "v82");
            return (Criteria) this;
        }

        public Criteria andV82LessThanOrEqualTo(Float value) {
            addCriterion("v82 <=", value, "v82");
            return (Criteria) this;
        }

        public Criteria andV82In(List<Float> values) {
            addCriterion("v82 in", values, "v82");
            return (Criteria) this;
        }

        public Criteria andV82NotIn(List<Float> values) {
            addCriterion("v82 not in", values, "v82");
            return (Criteria) this;
        }

        public Criteria andV82Between(Float value1, Float value2) {
            addCriterion("v82 between", value1, value2, "v82");
            return (Criteria) this;
        }

        public Criteria andV82NotBetween(Float value1, Float value2) {
            addCriterion("v82 not between", value1, value2, "v82");
            return (Criteria) this;
        }

        public Criteria andV83IsNull() {
            addCriterion("v83 is null");
            return (Criteria) this;
        }

        public Criteria andV83IsNotNull() {
            addCriterion("v83 is not null");
            return (Criteria) this;
        }

        public Criteria andV83EqualTo(Float value) {
            addCriterion("v83 =", value, "v83");
            return (Criteria) this;
        }

        public Criteria andV83NotEqualTo(Float value) {
            addCriterion("v83 <>", value, "v83");
            return (Criteria) this;
        }

        public Criteria andV83GreaterThan(Float value) {
            addCriterion("v83 >", value, "v83");
            return (Criteria) this;
        }

        public Criteria andV83GreaterThanOrEqualTo(Float value) {
            addCriterion("v83 >=", value, "v83");
            return (Criteria) this;
        }

        public Criteria andV83LessThan(Float value) {
            addCriterion("v83 <", value, "v83");
            return (Criteria) this;
        }

        public Criteria andV83LessThanOrEqualTo(Float value) {
            addCriterion("v83 <=", value, "v83");
            return (Criteria) this;
        }

        public Criteria andV83In(List<Float> values) {
            addCriterion("v83 in", values, "v83");
            return (Criteria) this;
        }

        public Criteria andV83NotIn(List<Float> values) {
            addCriterion("v83 not in", values, "v83");
            return (Criteria) this;
        }

        public Criteria andV83Between(Float value1, Float value2) {
            addCriterion("v83 between", value1, value2, "v83");
            return (Criteria) this;
        }

        public Criteria andV83NotBetween(Float value1, Float value2) {
            addCriterion("v83 not between", value1, value2, "v83");
            return (Criteria) this;
        }

        public Criteria andV84IsNull() {
            addCriterion("v84 is null");
            return (Criteria) this;
        }

        public Criteria andV84IsNotNull() {
            addCriterion("v84 is not null");
            return (Criteria) this;
        }

        public Criteria andV84EqualTo(Float value) {
            addCriterion("v84 =", value, "v84");
            return (Criteria) this;
        }

        public Criteria andV84NotEqualTo(Float value) {
            addCriterion("v84 <>", value, "v84");
            return (Criteria) this;
        }

        public Criteria andV84GreaterThan(Float value) {
            addCriterion("v84 >", value, "v84");
            return (Criteria) this;
        }

        public Criteria andV84GreaterThanOrEqualTo(Float value) {
            addCriterion("v84 >=", value, "v84");
            return (Criteria) this;
        }

        public Criteria andV84LessThan(Float value) {
            addCriterion("v84 <", value, "v84");
            return (Criteria) this;
        }

        public Criteria andV84LessThanOrEqualTo(Float value) {
            addCriterion("v84 <=", value, "v84");
            return (Criteria) this;
        }

        public Criteria andV84In(List<Float> values) {
            addCriterion("v84 in", values, "v84");
            return (Criteria) this;
        }

        public Criteria andV84NotIn(List<Float> values) {
            addCriterion("v84 not in", values, "v84");
            return (Criteria) this;
        }

        public Criteria andV84Between(Float value1, Float value2) {
            addCriterion("v84 between", value1, value2, "v84");
            return (Criteria) this;
        }

        public Criteria andV84NotBetween(Float value1, Float value2) {
            addCriterion("v84 not between", value1, value2, "v84");
            return (Criteria) this;
        }

        public Criteria andV85IsNull() {
            addCriterion("v85 is null");
            return (Criteria) this;
        }

        public Criteria andV85IsNotNull() {
            addCriterion("v85 is not null");
            return (Criteria) this;
        }

        public Criteria andV85EqualTo(Float value) {
            addCriterion("v85 =", value, "v85");
            return (Criteria) this;
        }

        public Criteria andV85NotEqualTo(Float value) {
            addCriterion("v85 <>", value, "v85");
            return (Criteria) this;
        }

        public Criteria andV85GreaterThan(Float value) {
            addCriterion("v85 >", value, "v85");
            return (Criteria) this;
        }

        public Criteria andV85GreaterThanOrEqualTo(Float value) {
            addCriterion("v85 >=", value, "v85");
            return (Criteria) this;
        }

        public Criteria andV85LessThan(Float value) {
            addCriterion("v85 <", value, "v85");
            return (Criteria) this;
        }

        public Criteria andV85LessThanOrEqualTo(Float value) {
            addCriterion("v85 <=", value, "v85");
            return (Criteria) this;
        }

        public Criteria andV85In(List<Float> values) {
            addCriterion("v85 in", values, "v85");
            return (Criteria) this;
        }

        public Criteria andV85NotIn(List<Float> values) {
            addCriterion("v85 not in", values, "v85");
            return (Criteria) this;
        }

        public Criteria andV85Between(Float value1, Float value2) {
            addCriterion("v85 between", value1, value2, "v85");
            return (Criteria) this;
        }

        public Criteria andV85NotBetween(Float value1, Float value2) {
            addCriterion("v85 not between", value1, value2, "v85");
            return (Criteria) this;
        }

        public Criteria andV86IsNull() {
            addCriterion("v86 is null");
            return (Criteria) this;
        }

        public Criteria andV86IsNotNull() {
            addCriterion("v86 is not null");
            return (Criteria) this;
        }

        public Criteria andV86EqualTo(Float value) {
            addCriterion("v86 =", value, "v86");
            return (Criteria) this;
        }

        public Criteria andV86NotEqualTo(Float value) {
            addCriterion("v86 <>", value, "v86");
            return (Criteria) this;
        }

        public Criteria andV86GreaterThan(Float value) {
            addCriterion("v86 >", value, "v86");
            return (Criteria) this;
        }

        public Criteria andV86GreaterThanOrEqualTo(Float value) {
            addCriterion("v86 >=", value, "v86");
            return (Criteria) this;
        }

        public Criteria andV86LessThan(Float value) {
            addCriterion("v86 <", value, "v86");
            return (Criteria) this;
        }

        public Criteria andV86LessThanOrEqualTo(Float value) {
            addCriterion("v86 <=", value, "v86");
            return (Criteria) this;
        }

        public Criteria andV86In(List<Float> values) {
            addCriterion("v86 in", values, "v86");
            return (Criteria) this;
        }

        public Criteria andV86NotIn(List<Float> values) {
            addCriterion("v86 not in", values, "v86");
            return (Criteria) this;
        }

        public Criteria andV86Between(Float value1, Float value2) {
            addCriterion("v86 between", value1, value2, "v86");
            return (Criteria) this;
        }

        public Criteria andV86NotBetween(Float value1, Float value2) {
            addCriterion("v86 not between", value1, value2, "v86");
            return (Criteria) this;
        }

        public Criteria andV87IsNull() {
            addCriterion("v87 is null");
            return (Criteria) this;
        }

        public Criteria andV87IsNotNull() {
            addCriterion("v87 is not null");
            return (Criteria) this;
        }

        public Criteria andV87EqualTo(Float value) {
            addCriterion("v87 =", value, "v87");
            return (Criteria) this;
        }

        public Criteria andV87NotEqualTo(Float value) {
            addCriterion("v87 <>", value, "v87");
            return (Criteria) this;
        }

        public Criteria andV87GreaterThan(Float value) {
            addCriterion("v87 >", value, "v87");
            return (Criteria) this;
        }

        public Criteria andV87GreaterThanOrEqualTo(Float value) {
            addCriterion("v87 >=", value, "v87");
            return (Criteria) this;
        }

        public Criteria andV87LessThan(Float value) {
            addCriterion("v87 <", value, "v87");
            return (Criteria) this;
        }

        public Criteria andV87LessThanOrEqualTo(Float value) {
            addCriterion("v87 <=", value, "v87");
            return (Criteria) this;
        }

        public Criteria andV87In(List<Float> values) {
            addCriterion("v87 in", values, "v87");
            return (Criteria) this;
        }

        public Criteria andV87NotIn(List<Float> values) {
            addCriterion("v87 not in", values, "v87");
            return (Criteria) this;
        }

        public Criteria andV87Between(Float value1, Float value2) {
            addCriterion("v87 between", value1, value2, "v87");
            return (Criteria) this;
        }

        public Criteria andV87NotBetween(Float value1, Float value2) {
            addCriterion("v87 not between", value1, value2, "v87");
            return (Criteria) this;
        }

        public Criteria andV88IsNull() {
            addCriterion("v88 is null");
            return (Criteria) this;
        }

        public Criteria andV88IsNotNull() {
            addCriterion("v88 is not null");
            return (Criteria) this;
        }

        public Criteria andV88EqualTo(Float value) {
            addCriterion("v88 =", value, "v88");
            return (Criteria) this;
        }

        public Criteria andV88NotEqualTo(Float value) {
            addCriterion("v88 <>", value, "v88");
            return (Criteria) this;
        }

        public Criteria andV88GreaterThan(Float value) {
            addCriterion("v88 >", value, "v88");
            return (Criteria) this;
        }

        public Criteria andV88GreaterThanOrEqualTo(Float value) {
            addCriterion("v88 >=", value, "v88");
            return (Criteria) this;
        }

        public Criteria andV88LessThan(Float value) {
            addCriterion("v88 <", value, "v88");
            return (Criteria) this;
        }

        public Criteria andV88LessThanOrEqualTo(Float value) {
            addCriterion("v88 <=", value, "v88");
            return (Criteria) this;
        }

        public Criteria andV88In(List<Float> values) {
            addCriterion("v88 in", values, "v88");
            return (Criteria) this;
        }

        public Criteria andV88NotIn(List<Float> values) {
            addCriterion("v88 not in", values, "v88");
            return (Criteria) this;
        }

        public Criteria andV88Between(Float value1, Float value2) {
            addCriterion("v88 between", value1, value2, "v88");
            return (Criteria) this;
        }

        public Criteria andV88NotBetween(Float value1, Float value2) {
            addCriterion("v88 not between", value1, value2, "v88");
            return (Criteria) this;
        }

        public Criteria andV89IsNull() {
            addCriterion("v89 is null");
            return (Criteria) this;
        }

        public Criteria andV89IsNotNull() {
            addCriterion("v89 is not null");
            return (Criteria) this;
        }

        public Criteria andV89EqualTo(Float value) {
            addCriterion("v89 =", value, "v89");
            return (Criteria) this;
        }

        public Criteria andV89NotEqualTo(Float value) {
            addCriterion("v89 <>", value, "v89");
            return (Criteria) this;
        }

        public Criteria andV89GreaterThan(Float value) {
            addCriterion("v89 >", value, "v89");
            return (Criteria) this;
        }

        public Criteria andV89GreaterThanOrEqualTo(Float value) {
            addCriterion("v89 >=", value, "v89");
            return (Criteria) this;
        }

        public Criteria andV89LessThan(Float value) {
            addCriterion("v89 <", value, "v89");
            return (Criteria) this;
        }

        public Criteria andV89LessThanOrEqualTo(Float value) {
            addCriterion("v89 <=", value, "v89");
            return (Criteria) this;
        }

        public Criteria andV89In(List<Float> values) {
            addCriterion("v89 in", values, "v89");
            return (Criteria) this;
        }

        public Criteria andV89NotIn(List<Float> values) {
            addCriterion("v89 not in", values, "v89");
            return (Criteria) this;
        }

        public Criteria andV89Between(Float value1, Float value2) {
            addCriterion("v89 between", value1, value2, "v89");
            return (Criteria) this;
        }

        public Criteria andV89NotBetween(Float value1, Float value2) {
            addCriterion("v89 not between", value1, value2, "v89");
            return (Criteria) this;
        }

        public Criteria andV90IsNull() {
            addCriterion("v90 is null");
            return (Criteria) this;
        }

        public Criteria andV90IsNotNull() {
            addCriterion("v90 is not null");
            return (Criteria) this;
        }

        public Criteria andV90EqualTo(Float value) {
            addCriterion("v90 =", value, "v90");
            return (Criteria) this;
        }

        public Criteria andV90NotEqualTo(Float value) {
            addCriterion("v90 <>", value, "v90");
            return (Criteria) this;
        }

        public Criteria andV90GreaterThan(Float value) {
            addCriterion("v90 >", value, "v90");
            return (Criteria) this;
        }

        public Criteria andV90GreaterThanOrEqualTo(Float value) {
            addCriterion("v90 >=", value, "v90");
            return (Criteria) this;
        }

        public Criteria andV90LessThan(Float value) {
            addCriterion("v90 <", value, "v90");
            return (Criteria) this;
        }

        public Criteria andV90LessThanOrEqualTo(Float value) {
            addCriterion("v90 <=", value, "v90");
            return (Criteria) this;
        }

        public Criteria andV90In(List<Float> values) {
            addCriterion("v90 in", values, "v90");
            return (Criteria) this;
        }

        public Criteria andV90NotIn(List<Float> values) {
            addCriterion("v90 not in", values, "v90");
            return (Criteria) this;
        }

        public Criteria andV90Between(Float value1, Float value2) {
            addCriterion("v90 between", value1, value2, "v90");
            return (Criteria) this;
        }

        public Criteria andV90NotBetween(Float value1, Float value2) {
            addCriterion("v90 not between", value1, value2, "v90");
            return (Criteria) this;
        }

        public Criteria andV91IsNull() {
            addCriterion("v91 is null");
            return (Criteria) this;
        }

        public Criteria andV91IsNotNull() {
            addCriterion("v91 is not null");
            return (Criteria) this;
        }

        public Criteria andV91EqualTo(Float value) {
            addCriterion("v91 =", value, "v91");
            return (Criteria) this;
        }

        public Criteria andV91NotEqualTo(Float value) {
            addCriterion("v91 <>", value, "v91");
            return (Criteria) this;
        }

        public Criteria andV91GreaterThan(Float value) {
            addCriterion("v91 >", value, "v91");
            return (Criteria) this;
        }

        public Criteria andV91GreaterThanOrEqualTo(Float value) {
            addCriterion("v91 >=", value, "v91");
            return (Criteria) this;
        }

        public Criteria andV91LessThan(Float value) {
            addCriterion("v91 <", value, "v91");
            return (Criteria) this;
        }

        public Criteria andV91LessThanOrEqualTo(Float value) {
            addCriterion("v91 <=", value, "v91");
            return (Criteria) this;
        }

        public Criteria andV91In(List<Float> values) {
            addCriterion("v91 in", values, "v91");
            return (Criteria) this;
        }

        public Criteria andV91NotIn(List<Float> values) {
            addCriterion("v91 not in", values, "v91");
            return (Criteria) this;
        }

        public Criteria andV91Between(Float value1, Float value2) {
            addCriterion("v91 between", value1, value2, "v91");
            return (Criteria) this;
        }

        public Criteria andV91NotBetween(Float value1, Float value2) {
            addCriterion("v91 not between", value1, value2, "v91");
            return (Criteria) this;
        }

        public Criteria andV92IsNull() {
            addCriterion("v92 is null");
            return (Criteria) this;
        }

        public Criteria andV92IsNotNull() {
            addCriterion("v92 is not null");
            return (Criteria) this;
        }

        public Criteria andV92EqualTo(Float value) {
            addCriterion("v92 =", value, "v92");
            return (Criteria) this;
        }

        public Criteria andV92NotEqualTo(Float value) {
            addCriterion("v92 <>", value, "v92");
            return (Criteria) this;
        }

        public Criteria andV92GreaterThan(Float value) {
            addCriterion("v92 >", value, "v92");
            return (Criteria) this;
        }

        public Criteria andV92GreaterThanOrEqualTo(Float value) {
            addCriterion("v92 >=", value, "v92");
            return (Criteria) this;
        }

        public Criteria andV92LessThan(Float value) {
            addCriterion("v92 <", value, "v92");
            return (Criteria) this;
        }

        public Criteria andV92LessThanOrEqualTo(Float value) {
            addCriterion("v92 <=", value, "v92");
            return (Criteria) this;
        }

        public Criteria andV92In(List<Float> values) {
            addCriterion("v92 in", values, "v92");
            return (Criteria) this;
        }

        public Criteria andV92NotIn(List<Float> values) {
            addCriterion("v92 not in", values, "v92");
            return (Criteria) this;
        }

        public Criteria andV92Between(Float value1, Float value2) {
            addCriterion("v92 between", value1, value2, "v92");
            return (Criteria) this;
        }

        public Criteria andV92NotBetween(Float value1, Float value2) {
            addCriterion("v92 not between", value1, value2, "v92");
            return (Criteria) this;
        }

        public Criteria andV93IsNull() {
            addCriterion("v93 is null");
            return (Criteria) this;
        }

        public Criteria andV93IsNotNull() {
            addCriterion("v93 is not null");
            return (Criteria) this;
        }

        public Criteria andV93EqualTo(Float value) {
            addCriterion("v93 =", value, "v93");
            return (Criteria) this;
        }

        public Criteria andV93NotEqualTo(Float value) {
            addCriterion("v93 <>", value, "v93");
            return (Criteria) this;
        }

        public Criteria andV93GreaterThan(Float value) {
            addCriterion("v93 >", value, "v93");
            return (Criteria) this;
        }

        public Criteria andV93GreaterThanOrEqualTo(Float value) {
            addCriterion("v93 >=", value, "v93");
            return (Criteria) this;
        }

        public Criteria andV93LessThan(Float value) {
            addCriterion("v93 <", value, "v93");
            return (Criteria) this;
        }

        public Criteria andV93LessThanOrEqualTo(Float value) {
            addCriterion("v93 <=", value, "v93");
            return (Criteria) this;
        }

        public Criteria andV93In(List<Float> values) {
            addCriterion("v93 in", values, "v93");
            return (Criteria) this;
        }

        public Criteria andV93NotIn(List<Float> values) {
            addCriterion("v93 not in", values, "v93");
            return (Criteria) this;
        }

        public Criteria andV93Between(Float value1, Float value2) {
            addCriterion("v93 between", value1, value2, "v93");
            return (Criteria) this;
        }

        public Criteria andV93NotBetween(Float value1, Float value2) {
            addCriterion("v93 not between", value1, value2, "v93");
            return (Criteria) this;
        }

        public Criteria andV94IsNull() {
            addCriterion("v94 is null");
            return (Criteria) this;
        }

        public Criteria andV94IsNotNull() {
            addCriterion("v94 is not null");
            return (Criteria) this;
        }

        public Criteria andV94EqualTo(Float value) {
            addCriterion("v94 =", value, "v94");
            return (Criteria) this;
        }

        public Criteria andV94NotEqualTo(Float value) {
            addCriterion("v94 <>", value, "v94");
            return (Criteria) this;
        }

        public Criteria andV94GreaterThan(Float value) {
            addCriterion("v94 >", value, "v94");
            return (Criteria) this;
        }

        public Criteria andV94GreaterThanOrEqualTo(Float value) {
            addCriterion("v94 >=", value, "v94");
            return (Criteria) this;
        }

        public Criteria andV94LessThan(Float value) {
            addCriterion("v94 <", value, "v94");
            return (Criteria) this;
        }

        public Criteria andV94LessThanOrEqualTo(Float value) {
            addCriterion("v94 <=", value, "v94");
            return (Criteria) this;
        }

        public Criteria andV94In(List<Float> values) {
            addCriterion("v94 in", values, "v94");
            return (Criteria) this;
        }

        public Criteria andV94NotIn(List<Float> values) {
            addCriterion("v94 not in", values, "v94");
            return (Criteria) this;
        }

        public Criteria andV94Between(Float value1, Float value2) {
            addCriterion("v94 between", value1, value2, "v94");
            return (Criteria) this;
        }

        public Criteria andV94NotBetween(Float value1, Float value2) {
            addCriterion("v94 not between", value1, value2, "v94");
            return (Criteria) this;
        }

        public Criteria andV95IsNull() {
            addCriterion("v95 is null");
            return (Criteria) this;
        }

        public Criteria andV95IsNotNull() {
            addCriterion("v95 is not null");
            return (Criteria) this;
        }

        public Criteria andV95EqualTo(Float value) {
            addCriterion("v95 =", value, "v95");
            return (Criteria) this;
        }

        public Criteria andV95NotEqualTo(Float value) {
            addCriterion("v95 <>", value, "v95");
            return (Criteria) this;
        }

        public Criteria andV95GreaterThan(Float value) {
            addCriterion("v95 >", value, "v95");
            return (Criteria) this;
        }

        public Criteria andV95GreaterThanOrEqualTo(Float value) {
            addCriterion("v95 >=", value, "v95");
            return (Criteria) this;
        }

        public Criteria andV95LessThan(Float value) {
            addCriterion("v95 <", value, "v95");
            return (Criteria) this;
        }

        public Criteria andV95LessThanOrEqualTo(Float value) {
            addCriterion("v95 <=", value, "v95");
            return (Criteria) this;
        }

        public Criteria andV95In(List<Float> values) {
            addCriterion("v95 in", values, "v95");
            return (Criteria) this;
        }

        public Criteria andV95NotIn(List<Float> values) {
            addCriterion("v95 not in", values, "v95");
            return (Criteria) this;
        }

        public Criteria andV95Between(Float value1, Float value2) {
            addCriterion("v95 between", value1, value2, "v95");
            return (Criteria) this;
        }

        public Criteria andV95NotBetween(Float value1, Float value2) {
            addCriterion("v95 not between", value1, value2, "v95");
            return (Criteria) this;
        }

        public Criteria andV96IsNull() {
            addCriterion("v96 is null");
            return (Criteria) this;
        }

        public Criteria andV96IsNotNull() {
            addCriterion("v96 is not null");
            return (Criteria) this;
        }

        public Criteria andV96EqualTo(Float value) {
            addCriterion("v96 =", value, "v96");
            return (Criteria) this;
        }

        public Criteria andV96NotEqualTo(Float value) {
            addCriterion("v96 <>", value, "v96");
            return (Criteria) this;
        }

        public Criteria andV96GreaterThan(Float value) {
            addCriterion("v96 >", value, "v96");
            return (Criteria) this;
        }

        public Criteria andV96GreaterThanOrEqualTo(Float value) {
            addCriterion("v96 >=", value, "v96");
            return (Criteria) this;
        }

        public Criteria andV96LessThan(Float value) {
            addCriterion("v96 <", value, "v96");
            return (Criteria) this;
        }

        public Criteria andV96LessThanOrEqualTo(Float value) {
            addCriterion("v96 <=", value, "v96");
            return (Criteria) this;
        }

        public Criteria andV96In(List<Float> values) {
            addCriterion("v96 in", values, "v96");
            return (Criteria) this;
        }

        public Criteria andV96NotIn(List<Float> values) {
            addCriterion("v96 not in", values, "v96");
            return (Criteria) this;
        }

        public Criteria andV96Between(Float value1, Float value2) {
            addCriterion("v96 between", value1, value2, "v96");
            return (Criteria) this;
        }

        public Criteria andV96NotBetween(Float value1, Float value2) {
            addCriterion("v96 not between", value1, value2, "v96");
            return (Criteria) this;
        }

        public Criteria andV97IsNull() {
            addCriterion("v97 is null");
            return (Criteria) this;
        }

        public Criteria andV97IsNotNull() {
            addCriterion("v97 is not null");
            return (Criteria) this;
        }

        public Criteria andV97EqualTo(Float value) {
            addCriterion("v97 =", value, "v97");
            return (Criteria) this;
        }

        public Criteria andV97NotEqualTo(Float value) {
            addCriterion("v97 <>", value, "v97");
            return (Criteria) this;
        }

        public Criteria andV97GreaterThan(Float value) {
            addCriterion("v97 >", value, "v97");
            return (Criteria) this;
        }

        public Criteria andV97GreaterThanOrEqualTo(Float value) {
            addCriterion("v97 >=", value, "v97");
            return (Criteria) this;
        }

        public Criteria andV97LessThan(Float value) {
            addCriterion("v97 <", value, "v97");
            return (Criteria) this;
        }

        public Criteria andV97LessThanOrEqualTo(Float value) {
            addCriterion("v97 <=", value, "v97");
            return (Criteria) this;
        }

        public Criteria andV97In(List<Float> values) {
            addCriterion("v97 in", values, "v97");
            return (Criteria) this;
        }

        public Criteria andV97NotIn(List<Float> values) {
            addCriterion("v97 not in", values, "v97");
            return (Criteria) this;
        }

        public Criteria andV97Between(Float value1, Float value2) {
            addCriterion("v97 between", value1, value2, "v97");
            return (Criteria) this;
        }

        public Criteria andV97NotBetween(Float value1, Float value2) {
            addCriterion("v97 not between", value1, value2, "v97");
            return (Criteria) this;
        }

        public Criteria andV98IsNull() {
            addCriterion("v98 is null");
            return (Criteria) this;
        }

        public Criteria andV98IsNotNull() {
            addCriterion("v98 is not null");
            return (Criteria) this;
        }

        public Criteria andV98EqualTo(Float value) {
            addCriterion("v98 =", value, "v98");
            return (Criteria) this;
        }

        public Criteria andV98NotEqualTo(Float value) {
            addCriterion("v98 <>", value, "v98");
            return (Criteria) this;
        }

        public Criteria andV98GreaterThan(Float value) {
            addCriterion("v98 >", value, "v98");
            return (Criteria) this;
        }

        public Criteria andV98GreaterThanOrEqualTo(Float value) {
            addCriterion("v98 >=", value, "v98");
            return (Criteria) this;
        }

        public Criteria andV98LessThan(Float value) {
            addCriterion("v98 <", value, "v98");
            return (Criteria) this;
        }

        public Criteria andV98LessThanOrEqualTo(Float value) {
            addCriterion("v98 <=", value, "v98");
            return (Criteria) this;
        }

        public Criteria andV98In(List<Float> values) {
            addCriterion("v98 in", values, "v98");
            return (Criteria) this;
        }

        public Criteria andV98NotIn(List<Float> values) {
            addCriterion("v98 not in", values, "v98");
            return (Criteria) this;
        }

        public Criteria andV98Between(Float value1, Float value2) {
            addCriterion("v98 between", value1, value2, "v98");
            return (Criteria) this;
        }

        public Criteria andV98NotBetween(Float value1, Float value2) {
            addCriterion("v98 not between", value1, value2, "v98");
            return (Criteria) this;
        }

        public Criteria andV99IsNull() {
            addCriterion("v99 is null");
            return (Criteria) this;
        }

        public Criteria andV99IsNotNull() {
            addCriterion("v99 is not null");
            return (Criteria) this;
        }

        public Criteria andV99EqualTo(Float value) {
            addCriterion("v99 =", value, "v99");
            return (Criteria) this;
        }

        public Criteria andV99NotEqualTo(Float value) {
            addCriterion("v99 <>", value, "v99");
            return (Criteria) this;
        }

        public Criteria andV99GreaterThan(Float value) {
            addCriterion("v99 >", value, "v99");
            return (Criteria) this;
        }

        public Criteria andV99GreaterThanOrEqualTo(Float value) {
            addCriterion("v99 >=", value, "v99");
            return (Criteria) this;
        }

        public Criteria andV99LessThan(Float value) {
            addCriterion("v99 <", value, "v99");
            return (Criteria) this;
        }

        public Criteria andV99LessThanOrEqualTo(Float value) {
            addCriterion("v99 <=", value, "v99");
            return (Criteria) this;
        }

        public Criteria andV99In(List<Float> values) {
            addCriterion("v99 in", values, "v99");
            return (Criteria) this;
        }

        public Criteria andV99NotIn(List<Float> values) {
            addCriterion("v99 not in", values, "v99");
            return (Criteria) this;
        }

        public Criteria andV99Between(Float value1, Float value2) {
            addCriterion("v99 between", value1, value2, "v99");
            return (Criteria) this;
        }

        public Criteria andV99NotBetween(Float value1, Float value2) {
            addCriterion("v99 not between", value1, value2, "v99");
            return (Criteria) this;
        }

        public Criteria andV100IsNull() {
            addCriterion("v100 is null");
            return (Criteria) this;
        }

        public Criteria andV100IsNotNull() {
            addCriterion("v100 is not null");
            return (Criteria) this;
        }

        public Criteria andV100EqualTo(Float value) {
            addCriterion("v100 =", value, "v100");
            return (Criteria) this;
        }

        public Criteria andV100NotEqualTo(Float value) {
            addCriterion("v100 <>", value, "v100");
            return (Criteria) this;
        }

        public Criteria andV100GreaterThan(Float value) {
            addCriterion("v100 >", value, "v100");
            return (Criteria) this;
        }

        public Criteria andV100GreaterThanOrEqualTo(Float value) {
            addCriterion("v100 >=", value, "v100");
            return (Criteria) this;
        }

        public Criteria andV100LessThan(Float value) {
            addCriterion("v100 <", value, "v100");
            return (Criteria) this;
        }

        public Criteria andV100LessThanOrEqualTo(Float value) {
            addCriterion("v100 <=", value, "v100");
            return (Criteria) this;
        }

        public Criteria andV100In(List<Float> values) {
            addCriterion("v100 in", values, "v100");
            return (Criteria) this;
        }

        public Criteria andV100NotIn(List<Float> values) {
            addCriterion("v100 not in", values, "v100");
            return (Criteria) this;
        }

        public Criteria andV100Between(Float value1, Float value2) {
            addCriterion("v100 between", value1, value2, "v100");
            return (Criteria) this;
        }

        public Criteria andV100NotBetween(Float value1, Float value2) {
            addCriterion("v100 not between", value1, value2, "v100");
            return (Criteria) this;
        }

        public Criteria andV101IsNull() {
            addCriterion("v101 is null");
            return (Criteria) this;
        }

        public Criteria andV101IsNotNull() {
            addCriterion("v101 is not null");
            return (Criteria) this;
        }

        public Criteria andV101EqualTo(Float value) {
            addCriterion("v101 =", value, "v101");
            return (Criteria) this;
        }

        public Criteria andV101NotEqualTo(Float value) {
            addCriterion("v101 <>", value, "v101");
            return (Criteria) this;
        }

        public Criteria andV101GreaterThan(Float value) {
            addCriterion("v101 >", value, "v101");
            return (Criteria) this;
        }

        public Criteria andV101GreaterThanOrEqualTo(Float value) {
            addCriterion("v101 >=", value, "v101");
            return (Criteria) this;
        }

        public Criteria andV101LessThan(Float value) {
            addCriterion("v101 <", value, "v101");
            return (Criteria) this;
        }

        public Criteria andV101LessThanOrEqualTo(Float value) {
            addCriterion("v101 <=", value, "v101");
            return (Criteria) this;
        }

        public Criteria andV101In(List<Float> values) {
            addCriterion("v101 in", values, "v101");
            return (Criteria) this;
        }

        public Criteria andV101NotIn(List<Float> values) {
            addCriterion("v101 not in", values, "v101");
            return (Criteria) this;
        }

        public Criteria andV101Between(Float value1, Float value2) {
            addCriterion("v101 between", value1, value2, "v101");
            return (Criteria) this;
        }

        public Criteria andV101NotBetween(Float value1, Float value2) {
            addCriterion("v101 not between", value1, value2, "v101");
            return (Criteria) this;
        }

        public Criteria andV102IsNull() {
            addCriterion("v102 is null");
            return (Criteria) this;
        }

        public Criteria andV102IsNotNull() {
            addCriterion("v102 is not null");
            return (Criteria) this;
        }

        public Criteria andV102EqualTo(Float value) {
            addCriterion("v102 =", value, "v102");
            return (Criteria) this;
        }

        public Criteria andV102NotEqualTo(Float value) {
            addCriterion("v102 <>", value, "v102");
            return (Criteria) this;
        }

        public Criteria andV102GreaterThan(Float value) {
            addCriterion("v102 >", value, "v102");
            return (Criteria) this;
        }

        public Criteria andV102GreaterThanOrEqualTo(Float value) {
            addCriterion("v102 >=", value, "v102");
            return (Criteria) this;
        }

        public Criteria andV102LessThan(Float value) {
            addCriterion("v102 <", value, "v102");
            return (Criteria) this;
        }

        public Criteria andV102LessThanOrEqualTo(Float value) {
            addCriterion("v102 <=", value, "v102");
            return (Criteria) this;
        }

        public Criteria andV102In(List<Float> values) {
            addCriterion("v102 in", values, "v102");
            return (Criteria) this;
        }

        public Criteria andV102NotIn(List<Float> values) {
            addCriterion("v102 not in", values, "v102");
            return (Criteria) this;
        }

        public Criteria andV102Between(Float value1, Float value2) {
            addCriterion("v102 between", value1, value2, "v102");
            return (Criteria) this;
        }

        public Criteria andV102NotBetween(Float value1, Float value2) {
            addCriterion("v102 not between", value1, value2, "v102");
            return (Criteria) this;
        }

        public Criteria andV103IsNull() {
            addCriterion("v103 is null");
            return (Criteria) this;
        }

        public Criteria andV103IsNotNull() {
            addCriterion("v103 is not null");
            return (Criteria) this;
        }

        public Criteria andV103EqualTo(Float value) {
            addCriterion("v103 =", value, "v103");
            return (Criteria) this;
        }

        public Criteria andV103NotEqualTo(Float value) {
            addCriterion("v103 <>", value, "v103");
            return (Criteria) this;
        }

        public Criteria andV103GreaterThan(Float value) {
            addCriterion("v103 >", value, "v103");
            return (Criteria) this;
        }

        public Criteria andV103GreaterThanOrEqualTo(Float value) {
            addCriterion("v103 >=", value, "v103");
            return (Criteria) this;
        }

        public Criteria andV103LessThan(Float value) {
            addCriterion("v103 <", value, "v103");
            return (Criteria) this;
        }

        public Criteria andV103LessThanOrEqualTo(Float value) {
            addCriterion("v103 <=", value, "v103");
            return (Criteria) this;
        }

        public Criteria andV103In(List<Float> values) {
            addCriterion("v103 in", values, "v103");
            return (Criteria) this;
        }

        public Criteria andV103NotIn(List<Float> values) {
            addCriterion("v103 not in", values, "v103");
            return (Criteria) this;
        }

        public Criteria andV103Between(Float value1, Float value2) {
            addCriterion("v103 between", value1, value2, "v103");
            return (Criteria) this;
        }

        public Criteria andV103NotBetween(Float value1, Float value2) {
            addCriterion("v103 not between", value1, value2, "v103");
            return (Criteria) this;
        }

        public Criteria andV104IsNull() {
            addCriterion("v104 is null");
            return (Criteria) this;
        }

        public Criteria andV104IsNotNull() {
            addCriterion("v104 is not null");
            return (Criteria) this;
        }

        public Criteria andV104EqualTo(Float value) {
            addCriterion("v104 =", value, "v104");
            return (Criteria) this;
        }

        public Criteria andV104NotEqualTo(Float value) {
            addCriterion("v104 <>", value, "v104");
            return (Criteria) this;
        }

        public Criteria andV104GreaterThan(Float value) {
            addCriterion("v104 >", value, "v104");
            return (Criteria) this;
        }

        public Criteria andV104GreaterThanOrEqualTo(Float value) {
            addCriterion("v104 >=", value, "v104");
            return (Criteria) this;
        }

        public Criteria andV104LessThan(Float value) {
            addCriterion("v104 <", value, "v104");
            return (Criteria) this;
        }

        public Criteria andV104LessThanOrEqualTo(Float value) {
            addCriterion("v104 <=", value, "v104");
            return (Criteria) this;
        }

        public Criteria andV104In(List<Float> values) {
            addCriterion("v104 in", values, "v104");
            return (Criteria) this;
        }

        public Criteria andV104NotIn(List<Float> values) {
            addCriterion("v104 not in", values, "v104");
            return (Criteria) this;
        }

        public Criteria andV104Between(Float value1, Float value2) {
            addCriterion("v104 between", value1, value2, "v104");
            return (Criteria) this;
        }

        public Criteria andV104NotBetween(Float value1, Float value2) {
            addCriterion("v104 not between", value1, value2, "v104");
            return (Criteria) this;
        }

        public Criteria andV105IsNull() {
            addCriterion("v105 is null");
            return (Criteria) this;
        }

        public Criteria andV105IsNotNull() {
            addCriterion("v105 is not null");
            return (Criteria) this;
        }

        public Criteria andV105EqualTo(Float value) {
            addCriterion("v105 =", value, "v105");
            return (Criteria) this;
        }

        public Criteria andV105NotEqualTo(Float value) {
            addCriterion("v105 <>", value, "v105");
            return (Criteria) this;
        }

        public Criteria andV105GreaterThan(Float value) {
            addCriterion("v105 >", value, "v105");
            return (Criteria) this;
        }

        public Criteria andV105GreaterThanOrEqualTo(Float value) {
            addCriterion("v105 >=", value, "v105");
            return (Criteria) this;
        }

        public Criteria andV105LessThan(Float value) {
            addCriterion("v105 <", value, "v105");
            return (Criteria) this;
        }

        public Criteria andV105LessThanOrEqualTo(Float value) {
            addCriterion("v105 <=", value, "v105");
            return (Criteria) this;
        }

        public Criteria andV105In(List<Float> values) {
            addCriterion("v105 in", values, "v105");
            return (Criteria) this;
        }

        public Criteria andV105NotIn(List<Float> values) {
            addCriterion("v105 not in", values, "v105");
            return (Criteria) this;
        }

        public Criteria andV105Between(Float value1, Float value2) {
            addCriterion("v105 between", value1, value2, "v105");
            return (Criteria) this;
        }

        public Criteria andV105NotBetween(Float value1, Float value2) {
            addCriterion("v105 not between", value1, value2, "v105");
            return (Criteria) this;
        }

        public Criteria andV106IsNull() {
            addCriterion("v106 is null");
            return (Criteria) this;
        }

        public Criteria andV106IsNotNull() {
            addCriterion("v106 is not null");
            return (Criteria) this;
        }

        public Criteria andV106EqualTo(Float value) {
            addCriterion("v106 =", value, "v106");
            return (Criteria) this;
        }

        public Criteria andV106NotEqualTo(Float value) {
            addCriterion("v106 <>", value, "v106");
            return (Criteria) this;
        }

        public Criteria andV106GreaterThan(Float value) {
            addCriterion("v106 >", value, "v106");
            return (Criteria) this;
        }

        public Criteria andV106GreaterThanOrEqualTo(Float value) {
            addCriterion("v106 >=", value, "v106");
            return (Criteria) this;
        }

        public Criteria andV106LessThan(Float value) {
            addCriterion("v106 <", value, "v106");
            return (Criteria) this;
        }

        public Criteria andV106LessThanOrEqualTo(Float value) {
            addCriterion("v106 <=", value, "v106");
            return (Criteria) this;
        }

        public Criteria andV106In(List<Float> values) {
            addCriterion("v106 in", values, "v106");
            return (Criteria) this;
        }

        public Criteria andV106NotIn(List<Float> values) {
            addCriterion("v106 not in", values, "v106");
            return (Criteria) this;
        }

        public Criteria andV106Between(Float value1, Float value2) {
            addCriterion("v106 between", value1, value2, "v106");
            return (Criteria) this;
        }

        public Criteria andV106NotBetween(Float value1, Float value2) {
            addCriterion("v106 not between", value1, value2, "v106");
            return (Criteria) this;
        }

        public Criteria andV107IsNull() {
            addCriterion("v107 is null");
            return (Criteria) this;
        }

        public Criteria andV107IsNotNull() {
            addCriterion("v107 is not null");
            return (Criteria) this;
        }

        public Criteria andV107EqualTo(Float value) {
            addCriterion("v107 =", value, "v107");
            return (Criteria) this;
        }

        public Criteria andV107NotEqualTo(Float value) {
            addCriterion("v107 <>", value, "v107");
            return (Criteria) this;
        }

        public Criteria andV107GreaterThan(Float value) {
            addCriterion("v107 >", value, "v107");
            return (Criteria) this;
        }

        public Criteria andV107GreaterThanOrEqualTo(Float value) {
            addCriterion("v107 >=", value, "v107");
            return (Criteria) this;
        }

        public Criteria andV107LessThan(Float value) {
            addCriterion("v107 <", value, "v107");
            return (Criteria) this;
        }

        public Criteria andV107LessThanOrEqualTo(Float value) {
            addCriterion("v107 <=", value, "v107");
            return (Criteria) this;
        }

        public Criteria andV107In(List<Float> values) {
            addCriterion("v107 in", values, "v107");
            return (Criteria) this;
        }

        public Criteria andV107NotIn(List<Float> values) {
            addCriterion("v107 not in", values, "v107");
            return (Criteria) this;
        }

        public Criteria andV107Between(Float value1, Float value2) {
            addCriterion("v107 between", value1, value2, "v107");
            return (Criteria) this;
        }

        public Criteria andV107NotBetween(Float value1, Float value2) {
            addCriterion("v107 not between", value1, value2, "v107");
            return (Criteria) this;
        }

        public Criteria andV108IsNull() {
            addCriterion("v108 is null");
            return (Criteria) this;
        }

        public Criteria andV108IsNotNull() {
            addCriterion("v108 is not null");
            return (Criteria) this;
        }

        public Criteria andV108EqualTo(Float value) {
            addCriterion("v108 =", value, "v108");
            return (Criteria) this;
        }

        public Criteria andV108NotEqualTo(Float value) {
            addCriterion("v108 <>", value, "v108");
            return (Criteria) this;
        }

        public Criteria andV108GreaterThan(Float value) {
            addCriterion("v108 >", value, "v108");
            return (Criteria) this;
        }

        public Criteria andV108GreaterThanOrEqualTo(Float value) {
            addCriterion("v108 >=", value, "v108");
            return (Criteria) this;
        }

        public Criteria andV108LessThan(Float value) {
            addCriterion("v108 <", value, "v108");
            return (Criteria) this;
        }

        public Criteria andV108LessThanOrEqualTo(Float value) {
            addCriterion("v108 <=", value, "v108");
            return (Criteria) this;
        }

        public Criteria andV108In(List<Float> values) {
            addCriterion("v108 in", values, "v108");
            return (Criteria) this;
        }

        public Criteria andV108NotIn(List<Float> values) {
            addCriterion("v108 not in", values, "v108");
            return (Criteria) this;
        }

        public Criteria andV108Between(Float value1, Float value2) {
            addCriterion("v108 between", value1, value2, "v108");
            return (Criteria) this;
        }

        public Criteria andV108NotBetween(Float value1, Float value2) {
            addCriterion("v108 not between", value1, value2, "v108");
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