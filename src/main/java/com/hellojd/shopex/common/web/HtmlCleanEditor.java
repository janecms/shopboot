package com.hellojd.shopex.common.web;
import java.beans.PropertyEditorSupport;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
/**
 * @author Administrator
 */
public class HtmlCleanEditor extends PropertyEditorSupport
{
  private boolean trim;
  private boolean emptyAsNull;
  private Whitelist IIIlllII = Whitelist.none();

  public HtmlCleanEditor(boolean trim, boolean emptyAsNull)
  {
    this.trim = trim;
    this.emptyAsNull = emptyAsNull;
  }

  public HtmlCleanEditor(boolean trim, boolean emptyAsNull, Whitelist whitelist)
  {
    this.trim = trim;
    this.emptyAsNull = emptyAsNull;
    this.IIIlllII = whitelist;
  }

  @Override
  public String getAsText()
  {
    Object localObject = getValue();
    return localObject != null ? localObject.toString() : "";
  }

  @Override
  public void setAsText(String text)
  {
    if (text != null)
    {
      String str = this.trim ? text.trim() : text;
      str = Jsoup.clean(str, this.IIIlllII);
      if ((this.emptyAsNull) && ("".equals(str))) {
        str = null;
      }
      setValue(str);
    }
    else
    {
      setValue(null);
    }
  }
}