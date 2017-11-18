package be.programaths.idea.plugins.editors.tss

import be.programaths.idea.plugins.editors.tss.language.tssLanguage
import be.programaths.idea.plugins.editors.tss.psi.TSSTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class TSSParserDefinition : ParserDefinition{
    override fun createParser(project: Project?): PsiParser = TSSParser()

    override fun createFile(viewProvider: FileViewProvider?): PsiFile = TSSFile(viewProvider!!)

    override fun spaceExistanceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements = ParserDefinition.SpaceRequirements.MAY

    override fun getStringLiteralElements(): TokenSet = STRINGS

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getWhitespaceTokens(): TokenSet = WHITE_SPACES

    override fun createLexer(project: Project?): Lexer = TSSLexer()

    override fun createElement(node: ASTNode?): PsiElement = TSSTypes.Factory.createElement(node)

    override fun getCommentTokens(): TokenSet = COMMENTS

    val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
    val COMMENTS = TokenSet.create(TSSTypes.COMMENT)
    val STRINGS = TokenSet.create(TSSTypes.STRING)

    val FILE = IFileElementType(tssLanguage)


}