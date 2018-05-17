package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class PinyinKind implements Serializable {
    private String pinyinkey;

    private Short times;

    private static final long serialVersionUID = 1L;

    public String getPinyinkey() {
        return pinyinkey;
    }

    public void setPinyinkey(String pinyinkey) {
        this.pinyinkey = pinyinkey == null ? null : pinyinkey.trim();
    }

    public Short getTimes() {
        return times;
    }

    public void setTimes(Short times) {
        this.times = times;
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
        PinyinKind other = (PinyinKind) that;
        return (this.getPinyinkey() == null ? other.getPinyinkey() == null : this.getPinyinkey().equals(other.getPinyinkey()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPinyinkey() == null) ? 0 : getPinyinkey().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        return result;
    }
}