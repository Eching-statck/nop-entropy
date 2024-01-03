package io.nop.excel.model._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [31:14:0:0]/nop/schema/excel/workbook.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _ExcelFont extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: bold
     * 
     */
    private boolean _bold  = false;
    
    /**
     *  
     * xml name: charSet
     * 
     */
    private java.lang.Integer _charSet ;
    
    /**
     *  
     * xml name: fontColor
     * 
     */
    private java.lang.String _fontColor ;
    
    /**
     *  
     * xml name: fontFamily
     * 
     */
    private java.lang.String _fontFamily ;
    
    /**
     *  
     * xml name: fontName
     * 
     */
    private java.lang.String _fontName ;
    
    /**
     *  
     * xml name: fontSize
     * 用来表示字体大小的半点，一个点等于两个半点，如表示 12pt 可以这样写
     * 24 = 12pt
     * 1pt = 20 twips, 1twips=1/1440英寸
     */
    private java.lang.Short _fontSize ;
    
    /**
     *  
     * xml name: italic
     * 
     */
    private boolean _italic  = false;
    
    /**
     *  
     * xml name: strikeout
     * 
     */
    private boolean _strikeout  = false;
    
    /**
     *  
     * xml name: underlineStyle
     * 
     */
    private io.nop.excel.model.constants.ExcelFontUnderline _underlineStyle ;
    
    /**
     *  
     * xml name: verticalAlign
     * 
     */
    private io.nop.excel.model.constants.ExcelFontVerticalAlign _verticalAlign ;
    
    /**
     * 
     * xml name: bold
     *  
     */
    
    public boolean isBold(){
      return _bold;
    }

    
    public void setBold(boolean value){
        checkAllowChange();
        
        this._bold = value;
           
    }

    
    /**
     * 
     * xml name: charSet
     *  
     */
    
    public java.lang.Integer getCharSet(){
      return _charSet;
    }

    
    public void setCharSet(java.lang.Integer value){
        checkAllowChange();
        
        this._charSet = value;
           
    }

    
    /**
     * 
     * xml name: fontColor
     *  
     */
    
    public java.lang.String getFontColor(){
      return _fontColor;
    }

    
    public void setFontColor(java.lang.String value){
        checkAllowChange();
        
        this._fontColor = value;
           
    }

    
    /**
     * 
     * xml name: fontFamily
     *  
     */
    
    public java.lang.String getFontFamily(){
      return _fontFamily;
    }

    
    public void setFontFamily(java.lang.String value){
        checkAllowChange();
        
        this._fontFamily = value;
           
    }

    
    /**
     * 
     * xml name: fontName
     *  
     */
    
    public java.lang.String getFontName(){
      return _fontName;
    }

    
    public void setFontName(java.lang.String value){
        checkAllowChange();
        
        this._fontName = value;
           
    }

    
    /**
     * 
     * xml name: fontSize
     *  用来表示字体大小的半点，一个点等于两个半点，如表示 12pt 可以这样写
     * 24 = 12pt
     * 1pt = 20 twips, 1twips=1/1440英寸
     */
    
    public java.lang.Short getFontSize(){
      return _fontSize;
    }

    
    public void setFontSize(java.lang.Short value){
        checkAllowChange();
        
        this._fontSize = value;
           
    }

    
    /**
     * 
     * xml name: italic
     *  
     */
    
    public boolean isItalic(){
      return _italic;
    }

    
    public void setItalic(boolean value){
        checkAllowChange();
        
        this._italic = value;
           
    }

    
    /**
     * 
     * xml name: strikeout
     *  
     */
    
    public boolean isStrikeout(){
      return _strikeout;
    }

    
    public void setStrikeout(boolean value){
        checkAllowChange();
        
        this._strikeout = value;
           
    }

    
    /**
     * 
     * xml name: underlineStyle
     *  
     */
    
    public io.nop.excel.model.constants.ExcelFontUnderline getUnderlineStyle(){
      return _underlineStyle;
    }

    
    public void setUnderlineStyle(io.nop.excel.model.constants.ExcelFontUnderline value){
        checkAllowChange();
        
        this._underlineStyle = value;
           
    }

    
    /**
     * 
     * xml name: verticalAlign
     *  
     */
    
    public io.nop.excel.model.constants.ExcelFontVerticalAlign getVerticalAlign(){
      return _verticalAlign;
    }

    
    public void setVerticalAlign(io.nop.excel.model.constants.ExcelFontVerticalAlign value){
        checkAllowChange();
        
        this._verticalAlign = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.put("bold",this.isBold());
        out.put("charSet",this.getCharSet());
        out.put("fontColor",this.getFontColor());
        out.put("fontFamily",this.getFontFamily());
        out.put("fontName",this.getFontName());
        out.put("fontSize",this.getFontSize());
        out.put("italic",this.isItalic());
        out.put("strikeout",this.isStrikeout());
        out.put("underlineStyle",this.getUnderlineStyle());
        out.put("verticalAlign",this.getVerticalAlign());
    }
}
 // resume CPD analysis - CPD-ON
