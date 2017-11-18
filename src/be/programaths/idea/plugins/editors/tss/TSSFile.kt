package be.programaths.idea.plugins.editors.tss

import be.programaths.idea.plugins.editors.tss.language.tssLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.ClassFileViewProvider
import com.intellij.psi.FileViewProvider

class TSSFile(fileViewProvider: FileViewProvider) : PsiFileBase(fileViewProvider,tssLanguage) {
    override fun getFileType() = tssFileType
    override fun toString(): String = "TSS File"
}