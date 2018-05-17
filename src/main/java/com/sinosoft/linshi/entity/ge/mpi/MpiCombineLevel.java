package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiCombineLevel implements Serializable {
    private Long combineRecLev;

    private String combineField;

    private Short srcLevel;

    private static final long serialVersionUID = 1L;

    public Long getCombineRecLev() {
        return combineRecLev;
    }

    public void setCombineRecLev(Long combineRecLev) {
        this.combineRecLev = combineRecLev;
    }

    public String getCombineField() {
        return combineField;
    }

    public void setCombineField(String combineField) {
        this.combineField = combineField == null ? null : combineField.trim();
    }

    public Short getSrcLevel() {
        return srcLevel;
    }

    public void setSrcLevel(Short srcLevel) {
        this.srcLevel = srcLevel;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MpiCombineLevel other = (MpiCombineLevel) that;
        return (this.getCombineRecLev() == null ? other.getCombineRecLev() == null : this.getCombineRecLev().equals(other.getCombineRecLev()))
            && (this.getCombineField() == null ? other.getCombineField() == null : this.getCombineField().equals(other.getCombineField()))
            && (this.getSrcLevel() == null ? other.getSrcLevel() == null : this.getSrcLevel().equals(other.getSrcLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCombineRecLev() == null) ? 0 : getCombineRecLev().hashCode());
        result = prime * result + ((getCombineField() == null) ? 0 : getCombineField().hashCode());
        result = prime * result + ((getSrcLevel() == null) ? 0 : getSrcLevel().hashCode());
        return result;
    }
}