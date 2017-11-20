package be.programaths.idea.plugins.editors.tss

import be.programaths.idea.plugins.editors.tss.psi.TSSTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class TSSSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val ELEMENT = createTextAttributesKey("TSS_ELEMENT",DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)
        val STRING = createTextAttributesKey("TSS_STRING",DefaultLanguageHighlighterColors.STRING)
        val ID = createTextAttributesKey("TSS_ID",DefaultLanguageHighlighterColors.IDENTIFIER)
        val CLASS = createTextAttributesKey("TSS_ELEMENT",DefaultLanguageHighlighterColors.CLASS_NAME)
        val FUNC = createTextAttributesKey("TSS_FUNC",DefaultLanguageHighlighterColors.FUNCTION_CALL)
        val SEP = createTextAttributesKey("TSS_SEP",DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val COMMENT = createTextAttributesKey("TSS_COM",DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val DEC = createTextAttributesKey("TSS_DEC",DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    }

    val ELEM_KEYS = arrayOf(ELEMENT)
    val STRING_KEYS = arrayOf(STRING)
    val ID_KEYS = arrayOf(ID)
    val CLASS_KEYS = arrayOf(CLASS)
    val FUNC_KEYS = arrayOf(FUNC)
    val COMMENT_KEYS = arrayOf(COMMENT)
    val SEP_KEYS = arrayOf(SEP)
    val DEC_KEYS = arrayOf(DEC)
    val EMPTY_KEYS = arrayOf<TextAttributesKey>()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when(tokenType){
        TSSTypes.CLASS -> CLASS_KEYS
        TSSTypes.COMMENT -> COMMENT_KEYS
        TSSTypes.CONTENT -> DEC_KEYS
        TSSTypes.COLON -> SEP_KEYS
        TSSTypes.COMA -> SEP_KEYS
        TSSTypes.LBRACE -> SEP_KEYS
        TSSTypes.RBRACE -> SEP_KEYS
        TSSTypes.DATA_REF -> ID_KEYS
        TSSTypes.IDENT_PATH -> ID_KEYS
        TSSTypes.ELEM -> ELEM_KEYS
        TSSTypes.STRING -> STRING_KEYS
        TSSTypes.ID -> ID_KEYS
        else -> EMPTY_KEYS
    }

    override fun getHighlightingLexer(): Lexer = TSSLexer()
}