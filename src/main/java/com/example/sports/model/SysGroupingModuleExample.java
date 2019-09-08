package com.example.sports.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-29 00:53
 **/
public class SysGroupingModuleExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<SysGroupingModuleExample.Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public SysGroupingModuleExample() {
        oredCriteria = new ArrayList<SysGroupingModuleExample.Criteria>();
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

    public List<SysGroupingModuleExample.Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(SysGroupingModuleExample.Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public SysGroupingModuleExample.Criteria or() {
        SysGroupingModuleExample.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public SysGroupingModuleExample.Criteria createCriteria() {
        SysGroupingModuleExample.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected SysGroupingModuleExample.Criteria createCriteriaInternal() {
        SysGroupingModuleExample.Criteria criteria = new SysGroupingModuleExample.Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<SysGroupingModuleExample.Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<SysGroupingModuleExample.Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<SysGroupingModuleExample.Criterion> getAllCriteria() {
            return criteria;
        }

        public List<SysGroupingModuleExample.Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new SysGroupingModuleExample.Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new SysGroupingModuleExample.Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new SysGroupingModuleExample.Criterion(condition, value1, value2));
        }


        public SysGroupingModuleExample.Criteria addCompetitionId(Integer value1){
            addCriterion("competition_id =", value1, "competitionId");
            return (SysGroupingModuleExample.Criteria) this;
        }

        public SysGroupingModuleExample.Criteria addId(Integer value1){
            addCriterion("id > ", value1, "id");
            return (SysGroupingModuleExample.Criteria) this;
        }

        public SysGroupingModuleExample.Criteria addProjectId(String value1){
            addCriterion("project_id =", value1, "projectId");
            return (SysGroupingModuleExample.Criteria) this;
        }

        public SysGroupingModuleExample.Criteria addTeamType(String value1){
            addCriterion("team_type =", value1, "teamType");
            return (SysGroupingModuleExample.Criteria) this;
        }

        public SysGroupingModuleExample.Criteria addPrinted(Integer value1){
            addCriterion("printed =", value1, "printed");
            return (SysGroupingModuleExample.Criteria) this;
        }

    }

    public static class Criteria extends SysGroupingModuleExample.GeneratedCriteria {

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
