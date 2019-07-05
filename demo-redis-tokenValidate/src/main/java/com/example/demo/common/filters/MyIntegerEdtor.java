package com.example.demo.common.filters;

import java.beans.PropertyEditorSupport;

public class MyIntegerEdtor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        // string -> Integer
        if (text == null || text.equals("")){

            text = "0";
        }

        setValue(Integer.parseInt(text));
    }

    @Override
    public String getAsText() {

        return getValue().toString();
    }
}
