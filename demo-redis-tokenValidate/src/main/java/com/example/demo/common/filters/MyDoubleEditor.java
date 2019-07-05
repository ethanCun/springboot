package com.example.demo.common.filters;

import java.beans.PropertyEditorSupport;

public class MyDoubleEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        //string -> double
        if (text == null || text.equals("")){

            text = "0";
        }

        setValue(Double.parseDouble(text));
    }

    @Override
    public String getAsText() {

        // double -> string
        return getValue().toString();
    }
}