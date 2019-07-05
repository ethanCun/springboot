package com.example.demo.common.filters;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyDateEditor extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (text == null || text.equals("") || Long.decode(text) == 0){

            //set null 配合javax validate @NotNull验证
            setValue(null);
        }else {

            //string -> long -> date
            setValue(new Date(Long.decode(text)));
        }
    }

    @Override
    public String getAsText() {

        //date -> long -> string
        Date date = (Date)getValue();

        return (date == null) ? "" : String.valueOf(TimeUnit.MILLISECONDS.toSeconds(date.getTime()));
    }
}
