package be.programaths.idea.plugins.editors.tss;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static be.programaths.idea.plugins.editors.tss.psi.TSSTypes.*;

%%

%{
  public _TSSLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _TSSLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COMMENT="/"\*(.|\n)+\*"/"
CLRF=\n
WS=[ \t\n\x0B\f\r]+
IDENT=[a-zA-Z]+
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
INT=[0-9]+

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  "iteration"        { return ITER; }
  "data"             { return DATA; }
  "("                { return LPAR; }
  ")"                { return RPAR; }
  "{"                { return LBRACE; }
  "}"                { return RBRACE; }
  ";"                { return SEMI; }
  ":"                { return COLON; }
  ","                { return COMA; }
  "="                { return EQ; }
  ">"                { return GT; }
  "+"                { return PLS; }
  "#"                { return POUND; }
  "."                { return DOT; }
  "["                { return LSQ; }
  "]"                { return RSQ; }
  "content"          { return CONTENT; }
  "repeat"           { return REPEAT; }
  "display"          { return DISPLAY; }
  "none"             { return NONE; }

  {COMMENT}          { return COMMENT; }
  {CLRF}             { return CLRF; }
  {WS}               { return WS; }
  {IDENT}            { return IDENT; }
  {STRING}           { return STRING; }
  {INT}              { return INT; }

}

[^] { return BAD_CHARACTER; }
