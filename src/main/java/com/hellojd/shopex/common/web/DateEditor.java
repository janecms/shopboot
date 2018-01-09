package com.hellojd.shopex.common.web;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hellojd.shopex.common.CommonAttributes;
import org.apache.commons.lang.time.DateUtils;

public class DateEditor extends PropertyEditorSupport
{
  private static final String IIIllIlI = "yyyy-MM-dd HH:mm:ss";
  private boolean emptyAsNull;
  private String dateFormat = "yyyy-MM-dd HH:mm:ss";

  public DateEditor(boolean emptyAsNull)
  {
    this.emptyAsNull = emptyAsNull;
  }

  public DateEditor(boolean emptyAsNull, String dateFormat)
  {
    this.emptyAsNull = emptyAsNull;
    this.dateFormat = dateFormat;
  }

  @Override
  public String getAsText()
  {
    Date localDate = (Date)getValue();
    return localDate != null ? new SimpleDateFormat(this.dateFormat).format(localDate) : "";
  }

  @Override
  public void setAsText(String text)
  {
    if (text == null)
    {
      setValue(null);
    }
    else
    {
      String str = text.trim();
      if ((this.emptyAsNull) && ("".equals(str))) {
        setValue(null);
      } else {
        try {
          setValue(DateUtils.parseDate(str, CommonAttributes.DATE_PATTERNS));
        } catch (ParseException localParseException) {
          setValue(null);
        }
      }
    }
  }
}