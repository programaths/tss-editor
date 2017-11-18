package be.programaths.idea.plugins.editors.tss.psi


import be.programaths.idea.plugins.editors.tss.language.tssLanguage
import com.intellij.psi.tree.IElementType

class TSSTokenType(debugName:String) : IElementType(debugName,tssLanguage) {
    override fun toString(): String = "TSSTokenType " + super.toString()
}