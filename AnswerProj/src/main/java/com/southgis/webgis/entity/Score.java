package com.southgis.webgis.entity;

public class Score {
    public String key;
    public float significance;

    @Override
    public String toString() {
        return "关键词=" + key +
                ", 重要程度=" + significance;}
}
